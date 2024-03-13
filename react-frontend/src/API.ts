/* tslint:disable */
/* eslint-disable */
//  This file was automatically generated and should not be edited.

export type AuditCreateInput = {
  operation: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type Audit = {
  __typename: "Audit",
  id: number,
  operation: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type AuditUpdateInput = {
  id: number,
  operation: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type CategoryCreateInput = {
  name: string,
};

export type Category = {
  __typename: "Category",
  id: number,
  name: string,
  tickets?:  Array<Ticket | null > | null,
};

export type Ticket = {
  __typename: "Ticket",
  id: number,
  subject: string,
  markdown: string,
  status_id: number,
  priority_id: number,
  user_id: number,
  category_id: number,
  created_at: string,
  updated_at: string,
  completed_at?: string | null,
  audits?:  Array<Audit | null > | null,
  comments?:  Array<Comment | null > | null,
};

export type Comment = {
  __typename: "Comment",
  id: number,
  markdown: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type CategoryUpdateInput = {
  id: number,
  name: string,
};

export type CommentCreateInput = {
  markdown: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type CommentUpdateInput = {
  id: number,
  markdown: string,
  user_id: number,
  ticket_id: number,
  created_at: string,
  updated_at: string,
};

export type PriorityCreateInput = {
  name: string,
};

export type Priority = {
  __typename: "Priority",
  id: number,
  name: string,
  tickets?:  Array<Ticket | null > | null,
};

export type PriorityUpdateInput = {
  id: number,
  name: string,
};

export type StatusCreateInput = {
  name: string,
};

export type Status = {
  __typename: "Status",
  id: number,
  name: string,
  tickets?:  Array<Ticket | null > | null,
};

export type StatusUpdateInput = {
  id: number,
  name: string,
};

export type TicketCreateInput = {
  subject: string,
  markdown: string,
  status_id: number,
  priority_id: number,
  user_id: number,
  category_id: number,
  created_at: string,
  updated_at: string,
  completed_at?: string | null,
};

export type TicketUpdateInput = {
  id: number,
  subject: string,
  markdown: string,
  status_id: number,
  priority_id: number,
  user_id: number,
  category_id: number,
  created_at: string,
  updated_at: string,
  completed_at?: string | null,
};

export type UserCreateInput = {
  name: string,
};

export type User = {
  __typename: "User",
  id: number,
  name: string,
  audits?:  Array<Audit | null > | null,
  comments?:  Array<Comment | null > | null,
  tickets?:  Array<Ticket | null > | null,
};

export type UserUpdateInput = {
  id: number,
  name: string,
};

export type CreateAuditMutationVariables = {
  input?: AuditCreateInput | null,
};

export type CreateAuditMutation = {
  createAudit?:  Array< {
    __typename: "Audit",
    id: number,
    operation: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type UpdateAuditMutationVariables = {
  audit?: AuditUpdateInput | null,
};

export type UpdateAuditMutation = {
  updateAudit?:  Array< {
    __typename: "Audit",
    id: number,
    operation: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type CreateCategoryMutationVariables = {
  input?: CategoryCreateInput | null,
};

export type CreateCategoryMutation = {
  createCategory?:  Array< {
    __typename: "Category",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type UpdateCategoryMutationVariables = {
  category?: CategoryUpdateInput | null,
};

export type UpdateCategoryMutation = {
  updateCategory?:  Array< {
    __typename: "Category",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type CreateCommentMutationVariables = {
  input?: CommentCreateInput | null,
};

export type CreateCommentMutation = {
  createComment?:  Array< {
    __typename: "Comment",
    id: number,
    markdown: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type UpdateCommentMutationVariables = {
  comment?: CommentUpdateInput | null,
};

export type UpdateCommentMutation = {
  updateComment?:  Array< {
    __typename: "Comment",
    id: number,
    markdown: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type CreatePriorityMutationVariables = {
  input?: PriorityCreateInput | null,
};

export type CreatePriorityMutation = {
  createPriority?:  Array< {
    __typename: "Priority",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type UpdatePriorityMutationVariables = {
  priority?: PriorityUpdateInput | null,
};

export type UpdatePriorityMutation = {
  updatePriority?:  Array< {
    __typename: "Priority",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type CreateStatusMutationVariables = {
  input?: StatusCreateInput | null,
};

export type CreateStatusMutation = {
  createStatus?:  Array< {
    __typename: "Status",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type UpdateStatusMutationVariables = {
  status?: StatusUpdateInput | null,
};

export type UpdateStatusMutation = {
  updateStatus?:  Array< {
    __typename: "Status",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type CreateTicketMutationVariables = {
  input?: TicketCreateInput | null,
};

export type CreateTicketMutation = {
  createTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type UpdateTicketMutationVariables = {
  ticket?: TicketUpdateInput | null,
};

export type UpdateTicketMutation = {
  updateTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type CreateUserMutationVariables = {
  input?: UserCreateInput | null,
};

export type CreateUserMutation = {
  createUser?:  Array< {
    __typename: "User",
    id: number,
    name: string,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type UpdateUserMutationVariables = {
  user?: UserUpdateInput | null,
};

export type UpdateUserMutation = {
  updateUser?:  Array< {
    __typename: "User",
    id: number,
    name: string,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type SetStatusOfTicketMutationVariables = {
  id: string,
  statusId: number,
};

export type SetStatusOfTicketMutation = {
  setStatusOfTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type SetPriorityOfTicketMutationVariables = {
  id: string,
  priorityId: number,
};

export type SetPriorityOfTicketMutation = {
  setPriorityOfTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type SetUserOfTicketMutationVariables = {
  id: string,
  userId: number,
};

export type SetUserOfTicketMutation = {
  setUserOfTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type SetCategoryOfTicketMutationVariables = {
  id: string,
  categoryId: number,
};

export type SetCategoryOfTicketMutation = {
  setCategoryOfTicket?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type GetAuditByIdQueryVariables = {
  id: number,
};

export type GetAuditByIdQuery = {
  getAuditById?:  Array< {
    __typename: "Audit",
    id: number,
    operation: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type AuditsQueryVariables = {
};

export type AuditsQuery = {
  audits?:  Array< {
    __typename: "Audit",
    id: number,
    operation: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type GetCategoryByIdQueryVariables = {
  id: number,
};

export type GetCategoryByIdQuery = {
  getCategoryById?:  Array< {
    __typename: "Category",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type CategoriesQueryVariables = {
};

export type CategoriesQuery = {
  categories?:  Array< {
    __typename: "Category",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type GetCommentByIdQueryVariables = {
  id: number,
};

export type GetCommentByIdQuery = {
  getCommentById?:  Array< {
    __typename: "Comment",
    id: number,
    markdown: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type CommentsQueryVariables = {
};

export type CommentsQuery = {
  comments?:  Array< {
    __typename: "Comment",
    id: number,
    markdown: string,
    user_id: number,
    ticket_id: number,
    created_at: string,
    updated_at: string,
  } | null > | null,
};

export type GetPriorityByIdQueryVariables = {
  id: number,
};

export type GetPriorityByIdQuery = {
  getPriorityById?:  Array< {
    __typename: "Priority",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type PrioritiesQueryVariables = {
};

export type PrioritiesQuery = {
  priorities?:  Array< {
    __typename: "Priority",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type GetStatusByIdQueryVariables = {
  id: number,
};

export type GetStatusByIdQuery = {
  getStatusById?:  Array< {
    __typename: "Status",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type StatusesQueryVariables = {
};

export type StatusesQuery = {
  statuses?:  Array< {
    __typename: "Status",
    id: number,
    name: string,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type GetTicketByIdQueryVariables = {
  id: number,
};

export type GetTicketByIdQuery = {
  getTicketById?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type TicketsQueryVariables = {
};

export type TicketsQuery = {
  tickets?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type GetUserByIdQueryVariables = {
  id: number,
};

export type GetUserByIdQuery = {
  getUserById?:  Array< {
    __typename: "User",
    id: number,
    name: string,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type UsersQueryVariables = {
};

export type UsersQuery = {
  users?:  Array< {
    __typename: "User",
    id: number,
    name: string,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};

export type GetTicketsByStatusQueryVariables = {
  statusId: number,
};

export type GetTicketsByStatusQuery = {
  getTicketsByStatus?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type GetTicketsByUserQueryVariables = {
  userId: number,
};

export type GetTicketsByUserQuery = {
  getTicketsByUser?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type GetTicketsByCategoryQueryVariables = {
  categoryId: number,
};

export type GetTicketsByCategoryQuery = {
  getTicketsByCategory?:  Array< {
    __typename: "Ticket",
    id: number,
    subject: string,
    markdown: string,
    status_id: number,
    priority_id: number,
    user_id: number,
    category_id: number,
    created_at: string,
    updated_at: string,
    completed_at?: string | null,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
  } | null > | null,
};

export type SearchUserByNameQueryVariables = {
  name: string,
};

export type SearchUserByNameQuery = {
  searchUserByName?:  Array< {
    __typename: "User",
    id: number,
    name: string,
    audits?:  Array< {
      __typename: "Audit",
      id: number,
      operation: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    comments?:  Array< {
      __typename: "Comment",
      id: number,
      markdown: string,
      user_id: number,
      ticket_id: number,
      created_at: string,
      updated_at: string,
    } | null > | null,
    tickets?:  Array< {
      __typename: "Ticket",
      id: number,
      subject: string,
      markdown: string,
      status_id: number,
      priority_id: number,
      user_id: number,
      category_id: number,
      created_at: string,
      updated_at: string,
      completed_at?: string | null,
    } | null > | null,
  } | null > | null,
};
