# Employee Management System

A Spring Boot RESTful application to manage Employees and Departments with proper JPA-based DB interaction, validation rules, and analytics support.

## 🔧 Technologies Used
- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- PostgreSQL
- MapStruct
- Postman
- Lombok

---

## 🚀 Features

- CRUD APIs for Employees and Departments
- Validation:
    - Prevent employee from being their own manager
    - Prevent mutual (circular) manager relationships
    - Prevent department deletion if employees are assigned
- Reporting Chain API
- Paginated Employee and Department listing
- Lookup endpoint for employee name/ID
- JSON error handling with @ControllerAdvice





- ✅ **Create, update, delete, get employees and departments**
- ✅ **Paginated GET endpoints**
- ✅ **Lookup endpoint** for employee ID-name
- ✅ **Prevent self-management**
- ✅ **Prevent mutual manager relationships**
- ✅ **Prevent deleting department with employees**
- ✅ **Reporting chain** endpoint
- ✅ **Structured error responses** via `@ControllerAdvice`




