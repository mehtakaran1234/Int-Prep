Table Name : Student

Column : Name, Subject, Marks

Rama Math 50
Rama Physics 60
Rama English 45
Hari Math 70
Hari Physics 65
Hari English 85
Gita Math 90
Gita Physics 55
Gita English 80

Expected Output:
Gita 225
Hari 220
Rama  155

Print the Students In Notice board format.
 (i.e Calculate the Toal mark for an individual student,
 and print in descending order of total marks.)

Select name, total from
(Select name, sum(marks) from Student
Group by name, marks ) as total
Order by total desc;



