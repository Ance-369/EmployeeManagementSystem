-- Department Table
CREATE TABLE departments
(
    id                 SERIAL PRIMARY KEY,
    name               VARCHAR(100) NOT NULL,
    creation_date      DATE         NOT NULL,
    department_head_id INT
);

-- Employee Table
CREATE TABLE employees
(
    id                      SERIAL PRIMARY KEY,
    name                    VARCHAR(100)     NOT NULL,
    date_of_birth           DATE             NOT NULL,
    salary                  NUMERIC(12, 2)   NOT NULL,
    address                 TEXT             NOT NULL,
    role                    VARCHAR(50)      NOT NULL,
    joining_date            DATE             NOT NULL,
    yearly_bonus_percentage DOUBLE PRECISION NOT NULL,
    manager_id              INT,
    department_id           INT,

    CONSTRAINT fk_manager FOREIGN KEY (manager_id) REFERENCES employees (id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES departments (id)
);

-- Add foreign key for department_head_id (after employee table is created)
ALTER TABLE departments
    ADD CONSTRAINT fk_department_head
        FOREIGN KEY (department_head_id)
            REFERENCES employees (id);


-- Insert Departments
INSERT INTO departments (name, creation_date)
VALUES ('Engineering', '2020-01-01'),
       ('HR', '2021-05-15'),
       ('Finance', '2022-03-10');

-- Insert Employees
-- Format: (name, dob, salary, address, role, joining date, bonus, manager_id, department_id)
INSERT INTO employees (name, date_of_birth, salary, address, role, joining_date, yearly_bonus_percentage, manager_id,
                       department_id)
VALUES ('Alice', '1990-06-01', 90000.00, 'Bangalore', 'Lead Engineer', '2020-01-15', 10.0, NULL, 1),
       ('Bob', '1992-04-12', 85000.00, 'Bangalore', 'Senior Engineer', '2021-03-20', 8.0, 1, 1),
       ('Charlie', '1995-07-24', 70000.00, 'Hyderabad', 'Engineer', '2022-01-10', 7.0, 2, 1),
       ('David', '1985-09-15', 95000.00, 'Mumbai', 'Engineering Manager', '2018-09-01', 12.0, NULL, 1),
       ('Eve', '1991-12-11', 65000.00, 'Chennai', 'HR Executive', '2021-06-01', 5.0, NULL, 2),
       ('Frank', '1988-11-22', 60000.00, 'Chennai', 'HR Assistant', '2022-02-14', 4.0, 5, 2),
       ('Grace', '1993-03-17', 75000.00, 'Delhi', 'Accountant', '2020-07-01', 6.0, NULL, 3),
       ('Hank', '1987-08-29', 80000.00, 'Delhi', 'Senior Accountant', '2019-05-01', 7.0, 7, 3),
       ('Ivy', '1994-06-30', 72000.00, 'Delhi', 'Finance Analyst', '2022-08-10', 6.0, 8, 3),
       ('Jack', '1990-01-02', 82000.00, 'Bangalore', 'Tech Lead', '2019-01-10', 9.0, 1, 1);

-- Add more to make total 25 employees
INSERT INTO employees (name, date_of_birth, salary, address, role, joining_date, yearly_bonus_percentage, manager_id,
                       department_id)
VALUES ('Kira', '1991-05-21', 78000.00, 'Mumbai', 'Engineer', '2021-11-01', 6.5, 4, 1),
       ('Leo', '1986-07-14', 89000.00, 'Hyderabad', 'Architect', '2017-10-10', 11.0, 4, 1),
       ('Maya', '1993-09-25', 63000.00, 'Bangalore', 'HR Executive', '2021-01-12', 4.5, 5, 2),
       ('Nina', '1995-10-10', 56000.00, 'Delhi', 'Finance Assistant', '2022-04-01', 4.0, 7, 3),
       ('Omar', '1992-01-22', 87000.00, 'Chennai', 'Senior Engineer', '2018-02-02', 9.0, 1, 1),
       ('Paul', '1989-03-05', 92000.00, 'Hyderabad', 'Engineering Manager', '2016-09-09', 12.5, NULL, 1),
       ('Quinn', '1996-12-20', 58000.00, 'Bangalore', 'Engineer', '2022-06-01', 6.0, 16, 1),
       ('Rose', '1990-08-18', 69000.00, 'Mumbai', 'HR Assistant', '2021-08-15', 4.5, 5, 2),
       ('Steve', '1994-02-03', 64000.00, 'Delhi', 'Finance Analyst', '2020-09-20', 5.5, 7, 3),
       ('Tina', '1993-05-13', 79000.00, 'Hyderabad', 'Lead Engineer', '2019-03-01', 9.5, 4, 1),
       ('Uma', '1997-04-01', 55000.00, 'Delhi', 'Account Assistant', '2023-01-01', 3.5, 8, 3),
       ('Victor', '1988-10-10', 96000.00, 'Bangalore', 'Director', '2015-01-01', 15.0, NULL, 1),
       ('Wendy', '1992-06-06', 85000.00, 'Chennai', 'HR Manager', '2017-06-06', 8.5, NULL, 2),
       ('Xavier', '1995-07-07', 69000.00, 'Bangalore', 'Engineer', '2021-07-07', 6.8, 10, 1),
       ('Yara', '1990-08-08', 73000.00, 'Hyderabad', 'Engineer', '2020-08-08', 7.2, 10, 1),
       ('Zane', '1991-09-09', 77000.00, 'Delhi', 'Senior Engineer', '2019-09-09', 8.0, 10, 1);

