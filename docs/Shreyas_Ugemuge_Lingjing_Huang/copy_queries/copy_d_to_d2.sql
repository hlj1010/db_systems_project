.open d.db
ATTACH 'd2.db' as d2;

INSERT INTO d2.'Doctor Personal Info'
SELECT Doctor_ID, Doctor_Name, Phone, Department, Birthday FROM Doctor;

INSERT INTO d2.'Doctor Contact Info'
SELECT Doctor_Name, Phone, Address, Department FROM Doctor;

INSERT INTO d2.Illness
SELECT * FROM Illness;

INSERT INTO d2.Patient
SELECT * FROM Patient;

