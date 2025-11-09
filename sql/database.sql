use research_management_db;

show tables;

drop table if exists `common_data`;
create table common_data (
    id varchar(64) primary key,
    name varchar(255) not null,
    type varchar(64) not null,
    
    home_file_id varchar(64),
    fulltext_file_id varchar(64)
);

drop table if exists `project`;
create table project (
    id varchar(64) primary key,
    project_type varchar(32),
    amount decimal(19,4),
    start_date date,
    end_date date,
    project_id varchar(64),
    approval_number varchar(64),
    card_number varchar(64),
    project_category varchar(128),
    funding_person varchar(64)
);

drop table if exists `patent`;
create table patent (
    id varchar(64) primary key,
    application_date date,
    authorization_date date
);

drop table if exists `paper`;
create table paper (
    id varchar(36) primary key,
    publicate_location varchar(256),
    sci boolean,
    ei boolean,
    ccf varchar(16),
    jcr varchar(16),
    publicate_date date
);

drop table if exists `attachment`;
create table attachment (
    id varchar(36) primary key,
    file_name varchar(256) not null,
    storage_location varchar(255) not null,
    mime_type varchar(128),
    file_size bigint,
    file_order int,
    project_id varchar(36)
);

drop table if exists `arrival`;
create table arrival (
    id varchar(36) primary key,
    project_id varchar(36),
    arrival_time date,
    arrival_amount decimal(18,4),
    index idx_project_id(project_id)
);

drop table if exists `other`;
create table other (
    id varchar(36) primary key,
    notes varchar(256)
);