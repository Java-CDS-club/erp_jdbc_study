select user(), database();


select title_no, title_name from title where title_no=1;

select * 
from employee e join department d on e.dept = d.dept_no 
     join title t on e.title = t.title_no;
     
select emp_no, emp_name, title, manager, salary, dept, hire_date, pic from employee where emp_no=1003;