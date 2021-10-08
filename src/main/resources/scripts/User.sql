create table USER
(
    ID            BIGINT auto_increment
        primary key,
    EMAIL         VARCHAR(255),
    INVOICE_STATE INTEGER,
    NAME          VARCHAR(255),
    REGISTER_DATE TIMESTAMP,
    CUSTOMER_ID   BIGINT not null,
    constraint FKDPTX0I3KY01SVOFWJYTQ5IRY0
        foreign key (CUSTOMER_ID) references CUSTOMER (ID)
);

