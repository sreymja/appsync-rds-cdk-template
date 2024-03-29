create table if not exists statuses
(
    id   int primary key not null,
    name varchar not null
);

create table if not exists priorities
(
    id   int primary key not null,
    name varchar not null
);

create table if not exists users
(
    id   int primary key not null,
    name varchar not null
);

create table if not exists categories
(
    id   int primary key not null,
    name varchar not null
);

create table if not exists tickets
(
    id           int primary key generated always as identity,
    subject      varchar   not null,
    content      varchar   not null,
    status_id    int       not null,
    priority_id  int       not null,
    user_id      int       not null,
    category_id  int       not null,
    created_at   timestamp not null DEFAULT Now(),
    updated_at   timestamp not null DEFAULT Now(),
    completed_at timestamp,
    foreign key (status_id) references statuses (id) on delete restrict,
    foreign key (priority_id) references priorities (id) on delete restrict,
    foreign key (user_id) references users (id) on delete restrict,
    foreign key (category_id) references categories (id) on delete restrict
);

create table if not exists audits
(
    id         int primary key generated always as identity,
    operation  varchar   not null,
    user_id    int       not null,
    ticket_id  int       not null,
    created_at timestamp not null DEFAULT Now(),
    updated_at timestamp not null DEFAULT Now(),
    foreign key (user_id) references users (id) on delete restrict,
    foreign key (ticket_id) references tickets (id) on delete restrict
);

create table if not exists comments
(
    id         int primary key generated always as identity,
    content    varchar   not null,
    user_id    int       not null,
    ticket_id  int       not null,
    created_at timestamp not null DEFAULT Now(),
    updated_at timestamp not null DEFAULT Now(),
    foreign key (user_id) references users (id) on delete restrict,
    foreign key (ticket_id) references tickets (id) on delete restrict
);

CREATE FUNCTION trigger_set_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp_tickets
    BEFORE
        UPDATE ON tickets
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TRIGGER set_timestamp_audits
    BEFORE
        UPDATE ON audits
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

CREATE TRIGGER set_timestamp_comments
    BEFORE
        UPDATE ON comments
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

