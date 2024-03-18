package com.pennyhill.gen;

import java.util.List;
import java.util.stream.Collectors;

import software.amazon.awscdk.services.appsync.BaseResolverProps;
import software.amazon.awscdk.services.appsync.MappingTemplate;

public class Resolvers {

    public static List<BaseResolverProps> getResolverProps() {
        return List.of(
            // Queries
            BaseResolverProps.builder()
                .fieldName("getAuditById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from audits where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from audits" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getCategoryById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from categories where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("categories")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from categories" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getCommentById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from comments where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from comments" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getPriorityById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from priorities where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("priorities")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from priorities" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getStatusById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from statuses where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("statuses")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from statuses" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getTicketById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from tickets where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from tickets" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getUserById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from users where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("users")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "select * from users" ),
                List.of())))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,
            // Mutations
            BaseResolverProps.builder()
                .fieldName("createAudit")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into audits(operation, user_id, ticket_id) values (:OPERATION, :USER_ID, :TICKET_ID) returning id;",
                              "select * from audits order by created_at desc limit 1" ),
                List.of(                            "\":ID\":$util.toJson($id)",
                              "\":TICKET_ID\":$util.toJson($ctx.args.input.ticket_id)",
                              "\":OPERATION\":$util.toJson($ctx.args.input.operation)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateAudit")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update audits set operation = :OPERATION, user_id = :USER_ID, ticket_id = :TICKET_ID where id = :ID;",
                              "select * from audits where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.input.id)",
                              "\":TICKET_ID\":$util.toJson($ctx.args.input.ticket_id)",
                              "\":OPERATION\":$util.toJson($ctx.args.input.operation)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createCategory")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into categories(name) values (:NAME) returning id;",
                              "select * from categories order by created_at desc limit 1" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateCategory")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update categories set name = :NAME where id = :ID;",
                              "select * from categories where id = :ID" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($ctx.args.input.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createComment")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into comments(content, user_id, ticket_id) values (:CONTENT, :USER_ID, :TICKET_ID) returning id;",
                              "select * from comments order by created_at desc limit 1" ),
                List.of(                            "\":ID\":$util.toJson($id)",
                              "\":CONTENT\":$util.toJson($ctx.args.input.content)",
                              "\":TICKET_ID\":$util.toJson($ctx.args.input.ticket_id)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateComment")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update comments set content = :CONTENT, user_id = :USER_ID, ticket_id = :TICKET_ID where id = :ID;",
                              "select * from comments where id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.args.input.id)",
                              "\":CONTENT\":$util.toJson($ctx.args.input.content)",
                              "\":TICKET_ID\":$util.toJson($ctx.args.input.ticket_id)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createPriority")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into priorities(name) values (:NAME) returning id;",
                              "select * from priorities order by created_at desc limit 1" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updatePriority")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update priorities set name = :NAME where id = :ID;",
                              "select * from priorities where id = :ID" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($ctx.args.input.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createStatus")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into statuses(name) values (:NAME) returning id;",
                              "select * from statuses order by created_at desc limit 1" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateStatus")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update statuses set name = :NAME where id = :ID;",
                              "select * from statuses where id = :ID" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($ctx.args.input.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createTicket")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into tickets(subject, content, status_id, priority_id, user_id, category_id) values (:SUBJECT, :CONTENT, :STATUS_ID, :PRIORITY_ID, :USER_ID, :CATEGORY_ID) returning id;",
                              "select * from tickets order by created_at desc limit 1" ),
                List.of(                            "\":PRIORITY_ID\":$util.toJson($ctx.args.input.priority_id)",
                              "\":ID\":$util.toJson($id)",
                              "\":SUBJECT\":$util.toJson($ctx.args.input.subject)",
                              "\":CONTENT\":$util.toJson($ctx.args.input.content)",
                              "\":STATUS_ID\":$util.toJson($ctx.args.input.status_id)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)",
                              "\":CATEGORY_ID\":$util.toJson($ctx.args.input.category_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateTicket")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update tickets set subject = :SUBJECT, content = :CONTENT, status_id = :STATUS_ID, priority_id = :PRIORITY_ID, user_id = :USER_ID, category_id = :CATEGORY_ID where id = :ID;",
                              "select * from tickets where id = :ID" ),
                List.of(                            "\":PRIORITY_ID\":$util.toJson($ctx.args.input.priority_id)",
                              "\":ID\":$util.toJson($ctx.args.input.id)",
                              "\":SUBJECT\":$util.toJson($ctx.args.input.subject)",
                              "\":CONTENT\":$util.toJson($ctx.args.input.content)",
                              "\":STATUS_ID\":$util.toJson($ctx.args.input.status_id)",
                              "\":USER_ID\":$util.toJson($ctx.args.input.user_id)",
                              "\":CATEGORY_ID\":$util.toJson($ctx.args.input.category_id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createUser")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "insert into users(name) values (:NAME) returning id;",
                              "select * from users order by created_at desc limit 1" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(INSERT_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateUser")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                            "update users set name = :NAME where id = :ID;",
                              "select * from users where id = :ID" ),
                List.of(                            "\":NAME\":$util.toJson($ctx.args.input.name)",
                              "\":ID\":$util.toJson($ctx.args.input.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(UPDATE_TEMPLATE))
                .build()
,
            // Relations
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("Ticket")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from audits where ticket_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from audits where user_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
 , 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("Ticket")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from comments where ticket_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from comments where user_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
 , 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Category")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from tickets where category_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Priority")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from tickets where priority_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Status")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from tickets where status_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false,
                List.of(                        "select * from tickets where user_id = :ID" ),
                List.of(                            "\":ID\":$util.toJson($ctx.source.id)" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
 
        );
    }

    public static final String RESPONSE_TEMPLATE = """
                        #if($ctx.error)
    $utils.error($ctx.error.message, $ctx.error.type)
#end

$utils.toJson($utils.rds.toJsonObject($ctx.result)[0])
            """;
    public static final String UPDATE_TEMPLATE = """
                                            #if($ctx.error)
    $utils.error($ctx.error.message, $ctx.error.type)
#end

$utils.toJson($utils.rds.toJsonObject($ctx.result)[1][0])

            """;
    public static final String INSERT_TEMPLATE = """
                                                    #if($ctx.error)
    $utils.error($ctx.error.message, $ctx.error.type)
#end

$utils.toJson($utils.rds.toJsonObject($ctx.result)[1][0])

            """;

    public static String requestTemplate(Boolean generateId, List<String> sqlQueries, List<String> variables) {
        var statements = sqlQueries.stream().map(q ->
                "        \"" + q + "\""
        ).collect(Collectors.joining(",\n"));
        var variablesMap = String.join(",\n", variables);
        var idLine = generateId ? "#set($id=$utils.autoId())\n" : "";
        return idLine +
                """
                        {
                            "version": "2018-05-29",
                            "statements": [
                                %s
                            ],
                            "variableMap": {
                                %s
                            }
                        }
                        """.formatted(statements, variablesMap);
    }
}