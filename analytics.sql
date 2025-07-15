SELECT d.name AS department_name, COUNT(e.id) AS total_employees
FROM departments d
         LEFT JOIN employees e ON d.id = e.department_id
GROUP BY d.name
ORDER BY total_employees DESC;
