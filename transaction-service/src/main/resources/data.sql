-- Insert data into ware_transaction table
INSERT INTO t_ware_transaction (ware_transaction_type, description, transaction_date, stock_clerk_id)
VALUES ('IMPORT', 'Received new stock', '2023-09-15 10:00:00', 1),
       ('EXPORT', 'Shipped to customer', '2023-09-15 14:30:00', 2),
       ('IMPORT', 'Received returned items', '2023-09-16 09:15:00', 3),
       ('EXPORT', 'Shipped to distributor', '2023-09-17 11:45:00', 1);

-- Insert data into ware_transaction_detail table
INSERT INTO t_ware_transaction_detail (ware_transaction_id, product_id, shelf_id, quantity)
VALUES (1, 1, 1, 100),
       (1, 2, 2, 150),
       (2, 3, 1, 75),
       (3, 4, 3, 50),
       (3, 2, 2, 25),
       (4, 1, 1, 120),
       (4, 3, 3, 80);
