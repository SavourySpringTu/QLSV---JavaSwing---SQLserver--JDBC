-- Them teacher

INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv01', 'Lưu Nguyễn Kỳ Thư','132');
INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv02', 'Trần Thư Đạt','abc');
INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv03', 'Đỗ Văn Mạnh','a789');
INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv04', 'Lê Thị Hương','huong1974');
INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv05', 'Bùi Xuân Đoan','vipvip');
INSERT INTO Teacher (id_teacher, name_teacher,password_teacher)
VALUES('gv06', 'Lê Kiều Trang','trang123');



-- Them calss

INSERT INTO Class (id_class, name_class,id_teacher)
VALUES('cntt01', 'Công Nghệ Thông Tin 01', 'gv01');
INSERT INTO Class (id_class, name_class,id_teacher)
VALUES('cntt02', 'Công Nghệ Thông Tin 02', 'gv05');
INSERT INTO Class (id_class, name_class,id_teacher)
VALUES('dtvt01', 'Điện Tử Viễn Thông 01', 'gv03');
INSERT INTO Class (id_class, name_class,id_teacher)
VALUES('antt03', 'An Toàn Thông Tin 03', 'gv01');

-- Them student

INSERT INTO Student (id_sv, name_sv, age, password_sv, id_class)
VALUES('n19dccn173', 'Bùi Xuân Tú', '21', 'tu123', 'cntt02');
INSERT INTO Student (id_sv, name_sv, age, password_sv, id_class)
VALUES('n19dccn016', 'Tô Gia Bảo', '21', 'bao123', 'cntt02');
INSERT INTO Student (id_sv, name_sv, age, password_sv, id_class)
VALUES('n18dccn127', 'Lê Đình Mỹ', '22', 'zzz', 'cntt01');
INSERT INTO Student (id_sv, name_sv, age, password_sv, id_class)
VALUES('n22dccn099', 'Bùi Tăng Diễn Linh', '18', 'btdiemlinh', 'dtvt01');
INSERT INTO Student (id_sv, name_sv, age, password_sv, id_class)
VALUES('n15dccn456', 'Nguyễn Văn Anh', '25', '456', 'antt03');