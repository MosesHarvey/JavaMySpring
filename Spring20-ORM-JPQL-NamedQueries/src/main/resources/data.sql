INSERT INTO departments (department_name, division)
VALUES ('Underwear','Clothing'),
       ( 'Potato','Grocery'),
       ('Decoration', 'Home'),
       ('Decoration', 'Office'),
       ('Accessory', 'Office'),
       ('Accessory', 'School'),
       ('Accessory', 'Auto'),
       ('Accessory', 'Laptop'),
       ('Daily Goods', 'Iron-smith'),
       ('Accessory', 'Garden'),
       ( 'Games','Entertainments'),
       ('Toys','Entertainments'),
       ( 'Summer','Seasonal'),
       ( 'Sports','Seasonal'),
       ( 'Art','Seasonal'),
       ( 'Film','Seasonal'),
       ( 'Birthdays','Holiday')
ON CONFLICT DO NOTHING ;

INSERT INTO regions (id, region, country)
VALUES (1, 'Southwest','USA'),
       (2, 'Southeast','USA'),
       (3, 'Northwest','USA'),
       (4, 'Northeast','USA'),
       (5, 'Quebec','Canada'),
       (6, 'Ontario','Canada'),
       (7, 'Central','Asia')
ON CONFLICT DO NOTHING ;

INSERT INTO employees (id, first_name, last_name, email, hire_date, department, gender, salary, region_id)
VALUES (1, 'Mike', 'Tyson', 'mike.tyson@abc.com', '1977-05-08', 'Sports', 'MALE', 25000, 4),
       (2, 'Mike', 'Smith', 'mike.smith@abc.com', '1977-05-08', 'Sports', 'MALE', 23000, 3),
       (3, 'Jennifer', 'Tyson', 'Jennifer.tyson@abc.com', '1977-05-08', 'Art', 'FEMALE', 22000, 1),
       (4, 'Mike', 'John', 'mike.john@abc.com', '1977-05-08', 'Film', 'MALE', 21000, 2),
       (5, 'Robin', 'Schebatiski', 'robin.schebatiski@abc.com', '1977-05-08', 'Film', 'FEMALE', 21500, 5),
       (6, 'Mike', 'Lee', 'mike.Lee@abc.com', '1977-05-08', 'Film', 'MALE', 27900, 7),
       (7, 'Jessica', 'Person', 'jessica.person@abc.com', '1977-05-08', 'Sports', 'FEMALE', 27300, 6),
       (8, 'John', 'Adam', 'john.adam@abc.com', '1977-05-08', 'Sports', 'MALE', 26400, 4),
       (9, 'Sofie', 'Darsi', 'sofie.darsi@abc.com', '1987-05-08', 'Sports', 'MALE', 24200, 3),
       (10, 'Jimmy', 'Taylang', 'jimmy.taylang@abc.com', '1977-05-08', 'Sports', 'MALE', 23300, 7),
       (11, 'Taylor', 'Swift', 'taylor.swift@abc.com', '1977-05-08', 'Art', 'MALE', 23000, 3)
ON CONFLICT DO NOTHING ;
