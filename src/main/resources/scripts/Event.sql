create table EVENT
(
    ID      BIGINT auto_increment
        primary key,
    DATE    DATE,
    TYPE    VARCHAR(255) not null,
    USER_ID BIGINT       not null,
    constraint FKI8BSVLTHQR8LNGSYSHIQSODAK
        foreign key (USER_ID) references USER (ID)
);

