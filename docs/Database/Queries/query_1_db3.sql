select c.Patient_ID, p.Patient_Name from 'Patient Personal Info' as p
join Doctor as d on d.Doctor_ID = p.Doctor_ID
join 'Patient Contact info' as c on c.Patient_Name = p.Patient_Name
where d.Doctor_Name='Taren Batarse';

