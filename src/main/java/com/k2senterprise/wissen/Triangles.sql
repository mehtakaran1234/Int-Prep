Write a query identifying the type of each record in the TRIANGLES table using its three side lengths. Output one of the following statements for each record in the table:

Equilateral: It's a triangle with  sides of equal length.
Isosceles: It's a triangle with  sides of equal length.
Scalene: It's a triangle with  sides of differing lengths.
Not A Triangle: The given values of A, B, and C don't form a triangle.

Table Name : Triangles
A 	B 	C
1	1	1
1	2	2
1	4	3
1   2   3
3	4	5




SELECT
  CASE
    WHEN (A + B <= C) OR (A + C <= B) OR (B + C <= A) THEN 'Not A Triangle: The given values of A, B, and C don''t form a triangle.'
    WHEN (A = B AND B = C) THEN 'Equilateral: It''s a triangle with sides of equal length.'
    WHEN (A = B OR B = C OR A = C) THEN 'Isosceles: It''s a triangle with sides of equal length.'
    ELSE 'Scalene: It''s a triangle with sides of differing lengths.'
  END AS TriangleType
FROM Triangles;