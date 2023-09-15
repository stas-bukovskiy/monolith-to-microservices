-- Insert data into w_warehouse table
INSERT INTO w_warehouse (description)
VALUES ('Warehouse A'),
       ('Warehouse B'),
       ('Warehouse C');

-- Insert data into w_shelf table
INSERT INTO w_shelf (code, warehouse_id)
VALUES ('A001', 1), -- Shelf A in Warehouse A
       ('A002', 1), -- Shelf B in Warehouse A
       ('B001', 2), -- Shelf A in Warehouse B
       ('C001', 3); -- Shelf A in Warehouse C
