INSERT INTO account (balance, number) VALUES (5000, '1234-1234');
INSERT INTO account (balance, number) VALUES (10, '1234-5678');
INSERT INTO account (balance, number) VALUES (123123, '9876-9876');

INSERT INTO customer (name, email_address, phone_number) VALUES ('Biro Janos', 'biro@janos.hu', '70 123-4567');
INSERT INTO customer (name, email_address, phone_number) VALUES ('Zemen Zsolt', 'zemen@zsolt.hu', '20 123-4567');
INSERT INTO customer (name, email_address, phone_number) VALUES ('Kanyasi Eszter', 'kanyasi@eszter.hu', '30 123-4567');

INSERT INTO accounts (customer_id, account_id) VALUES (1, 1);
INSERT INTO accounts (customer_id, account_id) VALUES (2, 2);
INSERT INTO accounts (customer_id, account_id) VALUES (3, 3);