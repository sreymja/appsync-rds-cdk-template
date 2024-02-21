package com.pennyhill.resolver;

import lombok.Getter;
import software.amazon.awscdk.services.appsync.BaseResolverProps;
import software.amazon.awscdk.services.appsync.MappingTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static com.pennyhill.resolver.ResolverType.*;

@Getter
public class Resolver {
    private static final String NULL_IF_EMPTY_RESPONSE_TEMPLATE = """
            #if($ctx.error)
                $utils.error($ctx.error.message, $ctx.error.type)
            #end
            #set($output = $utils.rds.toJsonObject($ctx.result)[0])
            #if ($output.isEmpty())
              null
            #else\s
              $utils.toJson($output[0])
            #end""";
    private static final String RESPONSE_TEMPLATE = """
            #if($ctx.error)
                $utils.error($ctx.error.message, $ctx.error.type)
            #end

            $utils.toJson($utils.rds.toJsonObject($ctx.result)%s)""";

    private final BaseResolverProps props;


    public Resolver(String fieldName, ResolverType type, String query){
        this(fieldName, type, null, query);
    }

    public Resolver(String fieldName, ResolverType type, String mutation, String query){
        String responseTemplate = switch (type){
            case Query, Status, Priority, User, Category -> {
                if (query.contains("count(*)")) {
                    yield RESPONSE_TEMPLATE.formatted("[0][0]");
                } else {
                    yield RESPONSE_TEMPLATE.formatted("[0]");
                }
            }
            case Mutation, Creation -> RESPONSE_TEMPLATE.formatted("[1][0]");
            case NullQuery -> NULL_IF_EMPTY_RESPONSE_TEMPLATE;
        };

        var queries = mutation == null ? List.of(query) : List.of(mutation, query);

        props = BaseResolverProps.builder()
                .fieldName(fieldName)
                .typeName(type.getType())
                .requestMappingTemplate(MappingTemplate.fromString(
                        requestTemplate(type, queries)
                ))
                .responseMappingTemplate(MappingTemplate.fromString(responseTemplate))
                .build();
    }

    private static String requestTemplate(ResolverType type, List<String> sqlQueries){
        var statements = sqlQueries.stream().map(q ->
                "        \"" + q + "\""
        ).collect(Collectors.joining(",\n"));
        var idLine = type == Creation? "#set($id=$utils.autoId())\n": "";
        return idLine +
                """
                {
                    "version": "2018-05-29",
                    "statements": [
                        %s
                    ]
                }
                """.formatted(statements);
    }
}

