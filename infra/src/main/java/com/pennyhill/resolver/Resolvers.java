package com.pennyhill.resolver;

import java.util.List;

import static com.pennyhill.resolver.ResolverType.*;

public class Resolvers {
    public static List<Resolver> getResolvers() {
        return List.of(
                // Queries
                new Resolver("getTicket", Query, "select * from tickets where id = '$ctx.args.id'"),
                new Resolver("getTicketsByStatus", Query, "select * from tickets where status_id = '$ctx.args.statusId'"),
                new Resolver("getTicketsByUser", Query, "select * from tickets where user_id = '$ctx.args.userId'"),
                new Resolver("getTicketsByCategory", Query, "select * from tickets where category_id = '$ctx.args.categoryId'"),
                new Resolver("statuses", Query,"select * from statuses"),
                new Resolver("categories", Query,"categories * from statuses"),
                new Resolver("searchUserByName", Query, "select * from users where name like = '%$ctx.args.name%'"),
                new Resolver("getUser", Query, "select * from users where id = '$ctx.args.id'"),
                new Resolver("tickets", Status,"select * from tickets where status_id = '$ctx.args.id'"),
                new Resolver("tickets", Priority,"select * from tickets where priority_id = '$ctx.args.id'"),
                new Resolver("tickets", Category,"select * from tickets where category_id = '$ctx.args.id'"),
                new Resolver("tickets", User,"select * from tickets where user_id = '$ctx.args.id'"),
                new Resolver("comments", User,"select * from comments where user_id = '$ctx.args.id'"),
                new Resolver("audits", User,"select * from audits where user_id = '$ctx.args.id'"),
                // Mutations
                new Resolver(
                        "createTicket",
                        Creation,
                        "insert into tickets(id, subject, markdown, status_id, priority_id, user_id, category_id) " +
                                "values ('$id', '$ctx.args.subject', '$ctx.args.markdown','$ctx.args.statusId','$ctx.args.priorityId','$ctx.args.userId','$ctx.args.categoryId')",
                        "select * from tickets where id = '$id'"
                ),
                new Resolver(
                        "createAudit",
                        Creation,
                        "insert into audits(id, operation, user_id, ticket_id) " +
                                "values ('$id', '$ctx.args.operation', '$ctx.args.userId','$ctx.args.ticketId')",
                        "select * from audits where id = '$id'"
                ),
                new Resolver(
                        "createComment",
                        Creation,
                        "insert into comments(id, markdown, user_id, ticket_id) " +
                                "values ('$id', '$ctx.args.markdown', '$ctx.args.userId','$ctx.args.ticketId')",
                        "select * from comments where id = '$id'"
                ),
                new Resolver(
                        "setStatusOfTicket",
                        Mutation,
                        "update tickets set status_id = '$ctx.args.statusId' where id = '$ctx.args.id'",
                        "select * from tickets where id = '$ctx.args.id'"
                ),
                new Resolver(
                        "setPriorityOfTicket",
                        Mutation,
                        "update tickets set priority_id = '$ctx.args.priorityId' where id = '$ctx.args.id'",
                        "select * from tickets where id = '$ctx.args.id'"
                ),
                new Resolver(
                        "setUserOfTicket",
                        Mutation,
                        "update tickets set user_id = '$ctx.args.userId' where id = '$ctx.args.id'",
                        "select * from tickets where id = '$ctx.args.id'"
                ),
                new Resolver(
                        "setCategoryOfTicket",
                        Mutation,
                        "update tickets set category_id = '$ctx.args.categoryId' where id = '$ctx.args.id'",
                        "select * from tickets where id = '$ctx.args.id'"
                )
        );

    }
}
