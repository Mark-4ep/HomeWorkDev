USE myHomeWork;

SELECT id as 'project_id',
TIMESTAMPDIFF(MONTH, start_date, finish_date)  AS 'month_count'
FROM project
WHERE
TIMESTAMPDIFF(MONTH, start_date, finish_date) = (SELECT MAX(TIMESTAMPDIFF(MONTH, start_date, finish_date))
FROM project)
;