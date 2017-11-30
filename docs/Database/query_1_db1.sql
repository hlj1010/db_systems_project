select `Patient_ID`, `Patient_Name`
from `Patient`
join `Doctor` on Doctor.Doctor_ID = Patient. Doctor_ID
where 
Doctor. Doctor_Name = 'Taren Batarse'
