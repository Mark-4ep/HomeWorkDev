USE myHomeWork;

SELECT
  'ELDEST' AS 'Type',
   name,
   birthday
FROM worker
WHERE  birthday = (SELECT MIN(birthday)  FROM worker)
UNION
SELECT
   'YOUNGEST' AS 'Type',
    name,
    birthday
FROM worker
WHERE birthday = (SELECT MAX(birthday) FROM worker)
;
