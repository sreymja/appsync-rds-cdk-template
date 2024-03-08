package com.pennyhill;

import com.pennyhill.introspect.TableSchema;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.sql.DataSource;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.pennyhill.FileUtils.writeToFile;

public class Main {

    public static void main(String[] args) {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        velocityEngine.init();

        var project = "infra/";
        var basePath = "src/main/";
        var resourcesPath = project + basePath + "resources/gen/";
        var javaPath = project + basePath + "java/com/pennyhill/gen/";

        writeToFile(resourcesPath + "schema.graphql", getStringWriter(velocityEngine, "schema.vm"));
        writeToFile(javaPath + "Resolvers.java", getStringWriter(velocityEngine, "Resolvers.vm"));

    }

    private static String getStringWriter(VelocityEngine velocityEngine, String templateName) {
        Template t = velocityEngine.getTemplate(templateName);

        VelocityContext context = new VelocityContext();
        var schemas = getTableSchemas();
        context.put("schemas", schemas);
        context.put("responseTemplate", TableSchema.RESPONSE_TEMPLATE);
        context.put("schemasWithFk", schemas.stream().filter(s -> !s.getForeignKeys().isEmpty()).toList());
        context.put("newline", "\n");

        StringWriter writer = new StringWriter();
        t.merge( context, writer );
        return writer.toString();
    }

    private static List<TableSchema> getTableSchemas() {
        var tableSchema = new ArrayList<TableSchema>();
        var ds = getDatasource();
        try{
            var conn = ds.getConnection();
            var metaData = conn.getMetaData();
            var tables = metaData.getTables("public", null, null, new String[]{"TABLE"});


                while (tables.next()) {
                    var catalog = tables.getString(2);
                    var name = tables.getString(3);
                    String EXCLUDED_TABLE = "excluded";
                    if (!name.contains(EXCLUDED_TABLE)) {
                        var columns = new ArrayList<TableSchema.Column>();
                        var columnSet = metaData.getColumns(catalog, null, name, null);
                            while (columnSet.next()) {
                                String columnName = columnSet.getString("COLUMN_NAME");
//                                String columnSize = columnSet.getString("COLUMN_SIZE");
                                String datatype = columnSet.getString(6);
                                String isNullable = columnSet.getString("IS_NULLABLE");
//                                String isAutoIncrement = columnSet.getString("IS_AUTOINCREMENT");
                                columns.add( new TableSchema.Column( columnName, datatype, isNullable));
                            }
                        var foreignKeys = new ArrayList<TableSchema.ForeignKey>();
                        try(ResultSet foreignKeySet = metaData.getImportedKeys(null, null, name)){
                            while(foreignKeySet.next()){
                                String pkTableName = foreignKeySet.getString("PKTABLE_NAME");
                                String fkTableName = foreignKeySet.getString("FKTABLE_NAME");
                                String pkColumnName = foreignKeySet.getString("PKCOLUMN_NAME");
                                String fkColumnName = foreignKeySet.getString("FKCOLUMN_NAME");
                                foreignKeys.add( new TableSchema.ForeignKey(pkTableName,fkTableName, pkColumnName, fkColumnName));
                            }
                        }
                        tableSchema.add(new TableSchema(name, columns, foreignKeys));
                    }
                }

                conn.close();
        } catch (Exception ex){
            System.out.println("Failed to get schema: " + ex.getMessage());
        }

        tableSchema.forEach(s -> setOneToMany(s, tableSchema));

        return tableSchema;
    }

    private static void setOneToMany(TableSchema schema, List<TableSchema> schemas) {
        var oneToManyFks = schemas
                .stream()
                .filter(s ->
                        s.getForeignKeys().stream()
                                .anyMatch(k -> k.getPkTableName().equals(schema.getName())))
                .map(s -> new TableSchema.OneToMany(s.getName(), s.getClassName())).toList();
        schema.setOneToManyRelations(oneToManyFks);
    }

    private static DataSource getDatasource() {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/testDB");
        hikariConfig.setUsername("test");
        hikariConfig.setPassword("testpassword");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setConnectionTimeout(140000);
        return new HikariDataSource(hikariConfig);
    }
}