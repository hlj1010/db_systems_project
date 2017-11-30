select d.Doctor_Name , i.Illness_ID
from `Patient Contact Info` as p
join `Patient Personal Info` as pp on p.Phone = pp.Phone
join Doctor as d on pp.Doctor_ID = d.Doctor_ID
join Illness as i on pp.Illness_ID = i.Illness_ID
where p.Patient_Name = 'Erika Heuberger' and p.Phone = '2449717107';
