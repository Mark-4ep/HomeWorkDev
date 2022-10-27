SELECT
  project_id,
  SUM(salary * TIMESTAMPDIFF(MONTH, start_date, finish_date)) AS 'Price'
FROM
  project_worker
INNER JOIN project ON project.id = project_worker.project_id
INNER JOIN worker ON worker.id = project_worker.worker_id
GROUP BY project.id
ORDER BY price DESC;