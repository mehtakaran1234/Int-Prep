Name Subject Marks
Ram Math  50
Ram Math  60
Ram Science  50
Shyam Math 70
Shyam Math 90

highest mark per subject per student

SELECT name, SUM(marks) AS total_marks
FROM (
    SELECT name, subject, MAX(marks) AS marks
    FROM Student
    GROUP BY name, subject
) AS sub
GROUP BY name;



SELECT Name, SUM(Marks) AS total_marks
FROM Student
GROUP BY Name
ORDER BY total_marks DESC;



Result for your data:
name	total_marks
Ram	110
Shyam	90

