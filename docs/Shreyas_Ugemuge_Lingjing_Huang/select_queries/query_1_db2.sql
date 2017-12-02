select p.Patient_ID , p.Patient_Name
from Patient as p
join `Doctor Personal Info` as d on d.Doctor_ID = p.Doctor_ID
where d.Doctor_Name = 'Taren Batarse';
