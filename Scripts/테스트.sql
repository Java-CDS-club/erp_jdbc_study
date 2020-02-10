select user(), database();

select * 
from employee e join department d on e.dept = d.dept_no 
     join title t on e.title = t.title_no;