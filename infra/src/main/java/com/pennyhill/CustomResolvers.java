package com.pennyhill;

import software.amazon.awscdk.services.appsync.BaseResolverProps;
import software.amazon.awscdk.services.appsync.MappingTemplate;

import java.util.List;

import static com.pennyhill.gen.Resolvers.RESPONSE_TEMPLATE;
import static com.pennyhill.gen.Resolvers.requestTemplate;

public class CustomResolvers {
    public static List<BaseResolverProps> getCustomResolverProps() {
        return List.of(
                // Queries
                BaseResolverProps.builder()
                        .fieldName("getTicketsByStatus")
                        .typeName("Query")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "select * from tickets where status_id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.statusId)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("getTicketsByUser")
                        .typeName("Query")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "select * from tickets where user_id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.userId)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("getTicketsByCategory")
                        .typeName("Query")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "select * from tickets where category_id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.categoryId)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("searchUserByName")
                        .typeName("Query")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "select * from users where name like = '%:NAME%'" ), List.of(
                                "':NAME': $util.toJson($ctx.args.name)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                // Mutations
                BaseResolverProps.builder()
                        .fieldName("setStatusOfTicket")
                        .typeName("Mutation")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "update tickets set status_id = '$ctx.args.statusId' where id = :ID",
                                "select * from tickets where id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.id)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("setPriorityOfTicket")
                        .typeName("Mutation")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "update tickets set priority_id = '$ctx.args.priorityId' where id = :ID",
                                "select * from tickets where id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.id)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("setUserOfTicket")
                        .typeName("Mutation")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "update tickets set user_id = '$ctx.args.userId' where id = :ID",
                                "select * from tickets where id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.id)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build(),
                BaseResolverProps.builder()
                        .fieldName("setCategoryOfTicket")
                        .typeName("Mutation")
                        .requestMappingTemplate(MappingTemplate.fromString(requestTemplate(false, List.of(
                                "update tickets set category_id = '$ctx.args.categoryId' where id = :ID",
                                "select * from tickets where id = :ID" ), List.of(
                                "':ID': $util.toJson($ctx.args.:ID)" ))))
                        .responseMappingTemplate(MappingTemplate.fromString(RESPONSE_TEMPLATE))
                        .build()
        );

    }
}
