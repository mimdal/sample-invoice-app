create table INVOICE
(
    ID                    BIGINT auto_increment
        primary key,
    INVOICE_ITEMS_HISTORY CLOB           not null,
    TOTAL_PRICE           DECIMAL(19, 2) not null
);

