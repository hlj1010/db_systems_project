select d.Doctor_Name , i.symptom
from Patient as p
join `Doctor Personal Info` as d on d.Doctor_ID = p.Doctor_ID
join Illness as i on i.Illness_ID = p.Illness_ID
where p.Patient_Name = 'Erika Heuberger' and p.Phone = '2449717107'