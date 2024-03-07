import {
    LazyLoading,
    LazyLoadingDisabled,
    ModelInit,
    MutableModel,
    PersistentModelConstructor,
    initSchema
} from "@aws-amplify/datastore";

import {schema} from "./schema";


type EagerAuditModel = {
    readonly id: string;
    readonly operation: string;
    readonly user_id: number;
    readonly ticket_id: number;
    readonly created_at: string;
    readonly updated_at: string;
}

type LazyAuditModel = {
    readonly id: string;
    readonly operation: string;
    readonly user_id: number;
    readonly ticket_id: number;
    readonly created_at: string;
    readonly updated_at: string;
}

export declare type AuditModel = LazyLoading extends LazyLoadingDisabled ? EagerAuditModel : LazyAuditModel

export declare const AuditModel: (new (init: ModelInit<AuditModel>) => AuditModel)

type EagerCategoryModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

type LazyCategoryModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

export declare type CategoryModel = LazyLoading extends LazyLoadingDisabled ? EagerCategoryModel : LazyCategoryModel

export declare const CategoryModel: (new (init: ModelInit<CategoryModel>) => CategoryModel)

type EagerCommentModel = {
    readonly id: string;
    readonly markdown: string;
    readonly user_id: number;
    readonly ticket_id: number;
    readonly created_at: string;
    readonly updated_at: string;
}

type LazyCommentModel = {
    readonly id: string;
    readonly markdown: string;
    readonly user_id: number;
    readonly ticket_id: number;
    readonly created_at: string;
    readonly updated_at: string;
}

export declare type CommentModel = LazyLoading extends LazyLoadingDisabled ? EagerCommentModel : LazyCommentModel

export declare const CommentModel: (new (init: ModelInit<CommentModel>) => CommentModel)

type EagerPriorityModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

type LazyPriorityModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

export declare type PriorityModel = LazyLoading extends LazyLoadingDisabled ? EagerPriorityModel : LazyPriorityModel

export declare const PriorityModel: (new (init: ModelInit<PriorityModel>) => PriorityModel)

type EagerStatusModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

type LazyStatusModel = {
    readonly id: string;
    readonly name: string;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

export declare type StatusModel = LazyLoading extends LazyLoadingDisabled ? EagerStatusModel : LazyStatusModel

export declare const StatusModel: (new (init: ModelInit<StatusModel>) => StatusModel)

type EagerTicketModel = {
    readonly id: string;
    readonly subject: string;
    readonly markdown: string;
    readonly status_id: number;
    readonly priority_id: number;
    readonly user_id: number;
    readonly category_id: number;
    readonly created_at: string;
    readonly updated_at: string;
    readonly completed_at?: string | null;
    readonly audits?: (typeof Audit | null)[] | null;
    readonly comments?: (typeof Comment | null)[] | null;
}

type LazyTicketModel = {
    readonly id: string;
    readonly subject: string;
    readonly markdown: string;
    readonly status_id: number;
    readonly priority_id: number;
    readonly user_id: number;
    readonly category_id: number;
    readonly created_at: string;
    readonly updated_at: string;
    readonly completed_at?: string | null;
    readonly audits?: (typeof Audit | null)[] | null;
    readonly comments?: (typeof Comment | null)[] | null;
}

export declare type TicketModel = LazyLoading extends LazyLoadingDisabled ? EagerTicketModel : LazyTicketModel

export declare const TicketModel: (new (init: ModelInit<TicketModel>) => TicketModel)

type EagerUserModel = {
    readonly id: string;
    readonly name: string;
    readonly audits?: (typeof Audit | null)[] | null;
    readonly comments?: (typeof Comment | null)[] | null;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

type LazyUserModel = {
    readonly id: string;
    readonly name: string;
    readonly audits?: (typeof Audit | null)[] | null;
    readonly comments?: (typeof Comment | null)[] | null;
    readonly tickets?: (typeof Ticket | null)[] | null;
}

export declare type UserModel = LazyLoading extends LazyLoadingDisabled ? EagerUserModel : LazyUserModel

export declare const UserModel: (new (init: ModelInit<UserModel>) => UserModel)

const {Audit, Category, Comment, Priority, Status, Ticket, User} = initSchema(schema) as {
    Audit: PersistentModelConstructor<AuditModel>;
    Category: PersistentModelConstructor<CategoryModel>;
    Comment: PersistentModelConstructor<CommentModel>;
    Priority: PersistentModelConstructor<PriorityModel>;
    Status: PersistentModelConstructor<StatusModel>;
    Ticket: PersistentModelConstructor<TicketModel>;
    User: PersistentModelConstructor<UserModel>;
};

export {};