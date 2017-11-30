select p.Patient_Name, d.Doctor_Name
from Patient as p
join Doctor as d on d.Doctor_ID = p.Doctor_ID
join Illness as i on i.Illness_ID = p.Illness_ID
where p.Illness_ID = '1';