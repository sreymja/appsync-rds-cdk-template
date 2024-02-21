package com.pennyhill.resolver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ResolverType {
    Query("Query"),
    Mutation("Mutation"),
    Status("Status"),
    Priority("Priority"),
    User("User"),
    Category("Category"),
    Creation("Mutation"),
    NullQuery("Query");

    private final String type;
}
