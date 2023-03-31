CREATE TABLE Teacher(
	id_teacher nchar(10) NOT NULL PRIMARY KEY,
	name_teacher nvarchar(20),
	password_teacher nchar(20),
);
CREATE TABLE Class(
	id_class nchar(10) NOT NULL PRIMARY KEY,
	name_class nvarchar(30),
	id_teacher nchar(10),
	FOREIGN KEY (id_teacher) REFERENCES Teacher(id_teacher)
);
CREATE TABLE Student(
	id_sv nchar(10) NOT NULL PRIMARY KEY,
	name_sv nvarchar(20),
	age int,
	password_sv nchar(20),
	id_class nchar(10),
	FOREIGN KEY (id_class) REFERENCES Class(id_class)
);
