CREATE DATABASE DepartmentServiceDB
CREATE DATABASE EmployeeServiceDB
CREATE DATABASE ClothingServiceDB
CREATE DATABASE CustomerServiceDB
CREATE DATABASE OrderServiceDB
CREATE DATABASE OrderItemServiceDB

DROP DATABASE DepartmentServiceDB
DROP DATABASE EmployeeServiceDB
DROP DATABASE ClothingServiceDB
DROP DATABASE CustomerServiceDB
DROP DATABASE OrderServiceDB
DROP DATABASE OrderItemServiceDB

USE DepartmentServiceDB
USE EmployeeServiceDB
USE ClothingServiceDB
USE CustomerServiceDB
USE OrderServiceDB
USE OrderItemServiceDB

SELECT * FROM departments
SELECT * FROM employees
SELECT * FROM customers
SELECT * FROM clothings
SELECT * FROM orders
SELECT * FROM order_items

DROP TABLE departments
DROP TABLE employees
DROP TABLE customers
DROP TABLE clothings
DROP TABLE orders
DROP TABLE order_items

DELETE FROM departments
DELETE FROM employees
DELETE FROM customers
DELETE FROM clothings
DELETE FROM orders
DELETE FROM order_items