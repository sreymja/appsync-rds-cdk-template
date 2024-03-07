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
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from audits where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from audits" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getCategoryById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from categories where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("categories")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from categories" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getCommentById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from comments where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from comments" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getPriorityById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from priorities where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("priorities")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from priorities" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getStatusById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from statuses where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("statuses")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from statuses" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getTicketById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from tickets where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from tickets" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("getUserById")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from users where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("users")
                .typeName("Query")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "select * from users" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,
            // Mutations
            BaseResolverProps.builder()
                .fieldName("createAudit")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, operation, user_id, ticket_id, created_at, updated_at) values ('$id', '$ctx.args.input.id', '$ctx.args.input.operation', '$ctx.args.input.user_id', '$ctx.args.input.ticket_id', '$ctx.args.input.created_at', '$ctx.args.input.updated_at');",
                              "select * from audits where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateAudit")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update audits set id = '$ctx.args.audit.id', operation = '$ctx.args.audit.operation', user_id = '$ctx.args.audit.user_id', ticket_id = '$ctx.args.audit.ticket_id', created_at = '$ctx.args.audit.created_at', updated_at = '$ctx.args.audit.updated_at' where id = '$ctx.args.id';",
                              "select * from audits where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createCategory")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, name) values ('$id', '$ctx.args.input.id', '$ctx.args.input.name');",
                              "select * from categories where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateCategory")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update categories set id = '$ctx.args.category.id', name = '$ctx.args.category.name' where id = '$ctx.args.id';",
                              "select * from categories where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createComment")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, markdown, user_id, ticket_id, created_at, updated_at) values ('$id', '$ctx.args.input.id', '$ctx.args.input.markdown', '$ctx.args.input.user_id', '$ctx.args.input.ticket_id', '$ctx.args.input.created_at', '$ctx.args.input.updated_at');",
                              "select * from comments where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateComment")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update comments set id = '$ctx.args.comment.id', markdown = '$ctx.args.comment.markdown', user_id = '$ctx.args.comment.user_id', ticket_id = '$ctx.args.comment.ticket_id', created_at = '$ctx.args.comment.created_at', updated_at = '$ctx.args.comment.updated_at' where id = '$ctx.args.id';",
                              "select * from comments where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createPriority")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, name) values ('$id', '$ctx.args.input.id', '$ctx.args.input.name');",
                              "select * from priorities where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updatePriority")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update priorities set id = '$ctx.args.priority.id', name = '$ctx.args.priority.name' where id = '$ctx.args.id';",
                              "select * from priorities where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createStatus")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, name) values ('$id', '$ctx.args.input.id', '$ctx.args.input.name');",
                              "select * from statuses where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateStatus")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update statuses set id = '$ctx.args.status.id', name = '$ctx.args.status.name' where id = '$ctx.args.id';",
                              "select * from statuses where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createTicket")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, subject, markdown, status_id, priority_id, user_id, category_id, created_at, updated_at, completed_at) values ('$id', '$ctx.args.input.id', '$ctx.args.input.subject', '$ctx.args.input.markdown', '$ctx.args.input.status_id', '$ctx.args.input.priority_id', '$ctx.args.input.user_id', '$ctx.args.input.category_id', '$ctx.args.input.created_at', '$ctx.args.input.updated_at', '$ctx.args.input.completed_at');",
                              "select * from tickets where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateTicket")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update tickets set id = '$ctx.args.ticket.id', subject = '$ctx.args.ticket.subject', markdown = '$ctx.args.ticket.markdown', status_id = '$ctx.args.ticket.status_id', priority_id = '$ctx.args.ticket.priority_id', user_id = '$ctx.args.ticket.user_id', category_id = '$ctx.args.ticket.category_id', created_at = '$ctx.args.ticket.created_at', updated_at = '$ctx.args.ticket.updated_at', completed_at = '$ctx.args.ticket.completed_at' where id = '$ctx.args.id';",
                              "select * from tickets where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,             BaseResolverProps.builder()
                .fieldName("createUser")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "insert into tickets(id, name) values ('$id', '$ctx.args.input.id', '$ctx.args.input.name');",
                              "select * from users where id = '$id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("updateUser")
                .typeName("Mutation")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                            "update users set id = '$ctx.args.user.id', name = '$ctx.args.user.name' where id = '$ctx.args.id';",
                              "select * from users where id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
,
            // Relations
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("Ticket")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from audits where ticket_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("audits")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from audits where user_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
 , 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("Ticket")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from comments where ticket_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("comments")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from comments where user_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build()
 , 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Category")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from tickets where category_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Priority")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from tickets where priority_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("Status")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from tickets where status_id = '$ctx.args.id'" ))))
                .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                .build(), 
            BaseResolverProps.builder()
                .fieldName("tickets")
                .typeName("User")
                .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                        "select * from tickets where user_id = '$ctx.args.id'" ))))
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

    public static String requestTemplate(Boolean generateId, List<String> sqlQueries) {
        var statements = sqlQueries.stream().map(q ->
                "        \"" + q + "\""
        ).collect(Collectors.joining(",\n"));
        var idLine = generateId ? "#set($id=$utils.autoId())\n" : "";
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