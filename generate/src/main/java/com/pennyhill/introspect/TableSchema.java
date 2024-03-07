package com.pennyhill.introspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.pennyhill.introspect.Utils.singularCapitalize;

@Data
public class TableSchema {

    public static final String RESPONSE_TEMPLATE = """
            #if($ctx.error)
                $utils.error($ctx.error.message, $ctx.error.type)
            #end

            $utils.toJson($utils.rds.toJsonObject($ctx.result)[0])""";
    private String name;
    private List<Column> columns;
    private List<ForeignKey> foreignKeys;
    private List<OneToMany> oneToManyRelations;
    private String className;
    private List<Query> queries;
    private List<Query> mutations;
    public TableSchema(String name, List<Column> columns, List<ForeignKey> foreignKeys) throws Exception {
        this.name = name;
        this.columns = columns;
        this.foreignKeys = foreignKeys;
        this.className = singularCapitalize(name);
        this.queries = generateQueries();
        this.mutations = generateMutations();
    }

    private List<Query> generateMutations() {
        var create = new Query(
                "create" + this.className,
                "(input: " + this.className + "CreateInput)",
                this.className,
                insertQuery(this),
                "select * from " + this.name + " where id = '$id'",
                true);

        var update = new Query(
                "update" + this.className,
                "(" + this.className.toLowerCase() + ": " + this.className + "UpdateInput)",
                this.className,
                updateQuery(this),
                "select * from " + this.name + " where id = '$ctx.args.id'",
                false);


        return List.of(create, update);
    }

    private String updateQuery(TableSchema tableSchema) {
        return "update " + tableSchema.name + " set " + tableSchema.columns.stream()
                .filter(c -> !name.equals("id"))
                .map(c -> c.name + " = '$ctx.args." + tableSchema.className.toLowerCase() + "." + c.name + "'")
                .collect(Collectors.joining(", ", "", " ")) +
                "where id = '$ctx.args.id';";

    }

    private String insertQuery(TableSchema tableSchema) {
        var line1 = "insert into tickets(" + tableSchema.columns.stream()
                .map(c -> c.name)
                .collect(Collectors.joining(", ")) + ") ";
        var line2 = "values ('$id', " + tableSchema.columns.stream()
                .filter(c -> !name.equals("id"))
                .map(c -> "'$ctx.args.input." + c.name + "'")
                .collect(Collectors.joining(", ")) + ");";
        return line1 + line2;
    }

    private List<Query> generateQueries() {
        var getById = new Query(
                "get" + this.className + "ById",
                "(id: ID!)",
                this.className,
                "select * from " + this.name + " where id = '$ctx.args.id'");

        var getAll = new Query(
                this.name,
                "",
                "[" + this.className + "]",
                "select * from " + this.name);

        return List.of(getById, getAll);
    }

    @Data
    public static class Column {
        private String name;
        private String sqlType;
        private String type;

        public Column(String name, String sqlType, String isNullable) {
            this.name = name;
            this.sqlType = sqlType;
            switch (sqlType) {
                case "int4":
                    if (name.equals("id")) this.type = "ID";
                    else this.type = "Int";
                    break;
                case "varchar", "timestamp":
                    this.type = "String";
                    break;
                default:
                    this.type = this.sqlType;
                    break;
            }
            if (isNullable.equals("NO")) this.type += "!";
        }
    }

    @Getter
    public static class Query {

        private final String name;
        private final String paramsStr;
        private final String returnTypeStr;
        private final List<String> queries;
        private final Boolean generateId;
        public Query(String name, String paramsStr, String returnTypeStr, String selectQuery) {
            this(name, paramsStr, returnTypeStr, null, selectQuery, false);
        }
        public Query(String name, String paramsStr, String returnTypeStr, String mutateQuery, String selectQuery, Boolean generateId) {
            this.name = name;
            this.paramsStr = paramsStr;
            this.returnTypeStr = returnTypeStr;

            this.generateId = generateId;
            this.queries = mutateQuery == null ?
                    List.of(selectQuery) : List.of(mutateQuery, selectQuery);
        }


    }

    @Getter
    public static class ForeignKey {
        private final String pkTableName;
        private final String fkTableName;
        private final String pkColumnName;
        private final String fkColumnName;
        private final String typeName;
        private final List<String> queries;

        public ForeignKey(String pkTableName, String fkTableName, String pkColumnName, String fkColumnName) throws Exception {
            this.pkTableName = pkTableName;
            this.fkTableName = fkTableName;
            this.pkColumnName = pkColumnName;
            this.fkColumnName = fkColumnName;

            this.typeName = singularCapitalize(this.pkTableName);


            var query = "select * from " + fkTableName + " where " + fkColumnName + " = '$ctx.args.id'";
            this.queries = List.of(query);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class OneToMany {
        private final String name;
        private final String type;
    }


//    private static String requestTemplate(Boolean generateId, List<String> sqlQueries) {
//        var statements = sqlQueries.stream().map(q ->
//                "        \"" + q + "\""
//        ).collect(Collectors.joining(",\n"));
//        var idLine = generateId ? "#set($id=$utils.autoId())\n" : "";
//        return idLine +
//                """
//                        {
//                            "version": "2018-05-29",
//                            "statements": [
//                                %s
//                            ]
//                        }
//                        """.formatted(statements);
//    }
}
