CREATE PROCEDURE Login(@id_teacher NCHAR(10),@password_teacher NCHAR(20))
AS
BEGIN
	SELECT id_teacher FROM Teacher
	WHERE LTRIM(id_teacher) LIKE @id_teacher AND LTRIM(password_teacher) LIKE @password_teacher
END;