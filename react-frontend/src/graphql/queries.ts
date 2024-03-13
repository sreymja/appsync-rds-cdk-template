/* tslint:disable */
/* eslint-disable */
// this is an auto generated file. This will be overwritten

import * as APITypes from "../API";
type GeneratedQuery<InputType, OutputType> = string & {
  __generatedQueryInput: InputType;
  __generatedQueryOutput: OutputType;
};

export const getAuditById = /* GraphQL */ `query GetAuditById($id: Int!) {
  getAuditById(id: $id) {
    id
    operation
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetAuditByIdQueryVariables,
  APITypes.GetAuditByIdQuery
>;
export const audits = /* GraphQL */ `query Audits {
  audits {
    id
    operation
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedQuery<APITypes.AuditsQueryVariables, APITypes.AuditsQuery>;
export const getCategoryById = /* GraphQL */ `query GetCategoryById($id: Int!) {
  getCategoryById(id: $id) {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetCategoryByIdQueryVariables,
  APITypes.GetCategoryByIdQuery
>;
export const categories = /* GraphQL */ `query Categories {
  categories {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.CategoriesQueryVariables,
  APITypes.CategoriesQuery
>;
export const getCommentById = /* GraphQL */ `query GetCommentById($id: Int!) {
  getCommentById(id: $id) {
    id
    markdown
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetCommentByIdQueryVariables,
  APITypes.GetCommentByIdQuery
>;
export const comments = /* GraphQL */ `query Comments {
  comments {
    id
    markdown
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedQuery<APITypes.CommentsQueryVariables, APITypes.CommentsQuery>;
export const getPriorityById = /* GraphQL */ `query GetPriorityById($id: Int!) {
  getPriorityById(id: $id) {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetPriorityByIdQueryVariables,
  APITypes.GetPriorityByIdQuery
>;
export const priorities = /* GraphQL */ `query Priorities {
  priorities {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.PrioritiesQueryVariables,
  APITypes.PrioritiesQuery
>;
export const getStatusById = /* GraphQL */ `query GetStatusById($id: Int!) {
  getStatusById(id: $id) {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetStatusByIdQueryVariables,
  APITypes.GetStatusByIdQuery
>;
export const statuses = /* GraphQL */ `query Statuses {
  statuses {
    id
    name
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<APITypes.StatusesQueryVariables, APITypes.StatusesQuery>;
export const getTicketById = /* GraphQL */ `query GetTicketById($id: Int!) {
  getTicketById(id: $id) {
    id
    subject
    markdown
    status_id
    priority_id
    user_id
    category_id
    created_at
    updated_at
    completed_at
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetTicketByIdQueryVariables,
  APITypes.GetTicketByIdQuery
>;
export const tickets = /* GraphQL */ `query Tickets {
  tickets {
    id
    subject
    markdown
    status_id
    priority_id
    user_id
    category_id
    created_at
    updated_at
    completed_at
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<APITypes.TicketsQueryVariables, APITypes.TicketsQuery>;
export const getUserById = /* GraphQL */ `query GetUserById($id: Int!) {
  getUserById(id: $id) {
    id
    name
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetUserByIdQueryVariables,
  APITypes.GetUserByIdQuery
>;
export const users = /* GraphQL */ `query Users {
  users {
    id
    name
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<APITypes.UsersQueryVariables, APITypes.UsersQuery>;
export const getTicketsByStatus = /* GraphQL */ `query GetTicketsByStatus($statusId: Int!) {
  getTicketsByStatus(statusId: $statusId) {
    id
    subject
    markdown
    status_id
    priority_id
    user_id
    category_id
    created_at
    updated_at
    completed_at
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetTicketsByStatusQueryVariables,
  APITypes.GetTicketsByStatusQuery
>;
export const getTicketsByUser = /* GraphQL */ `query GetTicketsByUser($userId: Int!) {
  getTicketsByUser(userId: $userId) {
    id
    subject
    markdown
    status_id
    priority_id
    user_id
    category_id
    created_at
    updated_at
    completed_at
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetTicketsByUserQueryVariables,
  APITypes.GetTicketsByUserQuery
>;
export const getTicketsByCategory = /* GraphQL */ `query GetTicketsByCategory($categoryId: Int!) {
  getTicketsByCategory(categoryId: $categoryId) {
    id
    subject
    markdown
    status_id
    priority_id
    user_id
    category_id
    created_at
    updated_at
    completed_at
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.GetTicketsByCategoryQueryVariables,
  APITypes.GetTicketsByCategoryQuery
>;
export const searchUserByName = /* GraphQL */ `query SearchUserByName($name: String!) {
  searchUserByName(name: $name) {
    id
    name
    audits {
      id
      operation
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    comments {
      id
      markdown
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    tickets {
      id
      subject
      markdown
      status_id
      priority_id
      user_id
      category_id
      created_at
      updated_at
      completed_at
      __typename
    }
    __typename
  }
}
` as GeneratedQuery<
  APITypes.SearchUserByNameQueryVariables,
  APITypes.SearchUserByNameQuery
>;
