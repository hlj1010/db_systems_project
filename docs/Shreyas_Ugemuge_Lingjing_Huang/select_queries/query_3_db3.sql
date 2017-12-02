select p.Patient_Name , d.Doctor_Name
from `Patient Personal Info` as pp
join `Patient Contact Info` as p on p.Phone = pp.Phone
join Doctor as d on pp.Doctor_ID = d.Doctor_ID
join Illness as i on i.Illness_ID = pp.Illness_ID
where i.Illness_ID = '1';