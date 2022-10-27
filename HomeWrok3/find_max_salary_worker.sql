SELECT name, salary
FROM worker
WHERE salary = (SELECT MAX(salary) FROM worker )
;