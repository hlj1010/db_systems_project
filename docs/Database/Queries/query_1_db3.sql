select p.Patient_ID, p.Patient_Name 
from 'Patient Personal Info' as pp
join Doctor as d on d.Doctor_ID = pp.Doctor_ID
join 'Patient Contact info' as p on pp.Patient_Name = p.Patient_Name
where d.Doctor_Name='Taren Batarse';