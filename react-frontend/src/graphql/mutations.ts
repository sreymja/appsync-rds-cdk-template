/* tslint:disable */
/* eslint-disable */
// this is an auto generated file. This will be overwritten

import * as APITypes from "../API";
type GeneratedMutation<InputType, OutputType> = string & {
  __generatedMutationInput: InputType;
  __generatedMutationOutput: OutputType;
};

export const createAudit = /* GraphQL */ `mutation CreateAudit($input: AuditCreateInput) {
  createAudit(input: $input) {
    id
    operation
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedMutation<
  APITypes.CreateAuditMutationVariables,
  APITypes.CreateAuditMutation
>;
export const updateAudit = /* GraphQL */ `mutation UpdateAudit($input: AuditUpdateInput) {
  updateAudit(input: $input) {
    id
    operation
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedMutation<
  APITypes.UpdateAuditMutationVariables,
  APITypes.UpdateAuditMutation
>;
export const createCategory = /* GraphQL */ `mutation CreateCategory($input: CategoryCreateInput) {
  createCategory(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.CreateCategoryMutationVariables,
  APITypes.CreateCategoryMutation
>;
export const updateCategory = /* GraphQL */ `mutation UpdateCategory($input: CategoryUpdateInput) {
  updateCategory(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.UpdateCategoryMutationVariables,
  APITypes.UpdateCategoryMutation
>;
export const createComment = /* GraphQL */ `mutation CreateComment($input: CommentCreateInput) {
  createComment(input: $input) {
    id
    content
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedMutation<
  APITypes.CreateCommentMutationVariables,
  APITypes.CreateCommentMutation
>;
export const updateComment = /* GraphQL */ `mutation UpdateComment($input: CommentUpdateInput) {
  updateComment(input: $input) {
    id
    content
    user_id
    ticket_id
    created_at
    updated_at
    __typename
  }
}
` as GeneratedMutation<
  APITypes.UpdateCommentMutationVariables,
  APITypes.UpdateCommentMutation
>;
export const createPriority = /* GraphQL */ `mutation CreatePriority($input: PriorityCreateInput) {
  createPriority(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.CreatePriorityMutationVariables,
  APITypes.CreatePriorityMutation
>;
export const updatePriority = /* GraphQL */ `mutation UpdatePriority($input: PriorityUpdateInput) {
  updatePriority(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.UpdatePriorityMutationVariables,
  APITypes.UpdatePriorityMutation
>;
export const createStatus = /* GraphQL */ `mutation CreateStatus($input: StatusCreateInput) {
  createStatus(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.CreateStatusMutationVariables,
  APITypes.CreateStatusMutation
>;
export const updateStatus = /* GraphQL */ `mutation UpdateStatus($input: StatusUpdateInput) {
  updateStatus(input: $input) {
    id
    name
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.UpdateStatusMutationVariables,
  APITypes.UpdateStatusMutation
>;
export const createTicket = /* GraphQL */ `mutation CreateTicket($input: TicketCreateInput) {
  createTicket(input: $input) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.CreateTicketMutationVariables,
  APITypes.CreateTicketMutation
>;
export const updateTicket = /* GraphQL */ `mutation UpdateTicket($input: TicketUpdateInput) {
  updateTicket(input: $input) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.UpdateTicketMutationVariables,
  APITypes.UpdateTicketMutation
>;
export const createUser = /* GraphQL */ `mutation CreateUser($input: UserCreateInput) {
  createUser(input: $input) {
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.CreateUserMutationVariables,
  APITypes.CreateUserMutation
>;
export const updateUser = /* GraphQL */ `mutation UpdateUser($input: UserUpdateInput) {
  updateUser(input: $input) {
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    tickets {
      id
      subject
      content
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
` as GeneratedMutation<
  APITypes.UpdateUserMutationVariables,
  APITypes.UpdateUserMutation
>;
export const setStatusOfTicket = /* GraphQL */ `mutation SetStatusOfTicket($id: ID!, $statusId: Int!) {
  setStatusOfTicket(id: $id, statusId: $statusId) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.SetStatusOfTicketMutationVariables,
  APITypes.SetStatusOfTicketMutation
>;
export const setPriorityOfTicket = /* GraphQL */ `mutation SetPriorityOfTicket($id: ID!, $priorityId: Int!) {
  setPriorityOfTicket(id: $id, priorityId: $priorityId) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.SetPriorityOfTicketMutationVariables,
  APITypes.SetPriorityOfTicketMutation
>;
export const setUserOfTicket = /* GraphQL */ `mutation SetUserOfTicket($id: ID!, $userId: Int!) {
  setUserOfTicket(id: $id, userId: $userId) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.SetUserOfTicketMutationVariables,
  APITypes.SetUserOfTicketMutation
>;
export const setCategoryOfTicket = /* GraphQL */ `mutation SetCategoryOfTicket($id: ID!, $categoryId: Int!) {
  setCategoryOfTicket(id: $id, categoryId: $categoryId) {
    id
    subject
    content
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
      content
      user_id
      ticket_id
      created_at
      updated_at
      __typename
    }
    __typename
  }
}
` as GeneratedMutation<
  APITypes.SetCategoryOfTicketMutationVariables,
  APITypes.SetCategoryOfTicketMutation
>;
