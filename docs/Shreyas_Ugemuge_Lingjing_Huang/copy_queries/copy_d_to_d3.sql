.open d.db
ATTACH 'd3.db' as d2;

INSERT INTO d2.'Patient Personal Info'
SELECT Patient_Name, Phone, Gender, Birthday,Allergies_History,Doctor_ID,Illness_ID FROM Patient;

INSERT INTO d2.'Patient Contact Info'
SELECT Patient_Name, Phone, Address, Patient_ID FROM Patient;

INSERT INTO d2.Illness
SELECT * FROM Illness;

INSERT INTO d2.Doctor
SELECT * FROM Doctor;

