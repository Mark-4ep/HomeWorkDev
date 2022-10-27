SELECT name, COUNT(*) AS 'project_count'
FROM client
JOIN project ON project.client_id = client.id
GROUP BY project.client_id
ORDER BY COUNT(*) DESC;