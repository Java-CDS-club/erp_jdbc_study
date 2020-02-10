-- 그래이들_프로젝
DROP SCHEMA IF EXISTS gradle_jdbc;

-- 그래이들_프로젝
CREATE SCHEMA gradle_jdbc;

-- 직책
CREATE TABLE gradle_jdbc.title (
	title_no   INTEGER     NOT NULL COMMENT '직책번호', -- 직책번호
	title_name VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE gradle_jdbc.title
	ADD CONSTRAINT PK_title -- 직책 기본키
		PRIMARY KEY (
			title_no -- 직책번호
		);

-- 부서
CREATE TABLE gradle_jdbc.department (
	dept_no   INTEGER     NOT NULL COMMENT '부서번호', -- 부서번호
	dept_name VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor     INTEGER     NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE gradle_jdbc.department
	ADD CONSTRAINT PK_department -- 부서 기본키
		PRIMARY KEY (
			dept_no -- 부서번호
		);

-- 사원
CREATE TABLE gradle_jdbc.employee (
	emp_no   INTEGER     NOT NULL COMMENT '사원번호', -- 사원번호
	emp_name VARCHAR(20) NOT NULL COMMENT '사원명', -- 사원명
	title    INTEGER     NULL     COMMENT '직책', -- 직책
	manager  INTEGER     NULL     COMMENT '직속상사', -- 직속상사
	salary   INTEGER     NULL     COMMENT '급여', -- 급여
	dept     INTEGER     NULL     COMMENT '부서', -- 부서
	pic      LONGBLOB    NULL     COMMENT '증명사진', -- 증명사진
	pass     CHAR(41)    NULL     COMMENT '비밀번호', -- 비밀번호
	hire_date DATETIME       null     COMMENT '입사일' -- 입사일
)
COMMENT '사원';

-- 사원
ALTER TABLE gradle_jdbc.employee
	ADD CONSTRAINT PK_employee -- 사원 기본키
		PRIMARY KEY (
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE gradle_jdbc.employee
	ADD CONSTRAINT FK_employee_TO_employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES gradle_jdbc.employee ( -- 사원
			emp_no -- 사원번호
		);

-- 사원
ALTER TABLE gradle_jdbc.employee
	ADD CONSTRAINT FK_department_TO_employee -- 부서 -> 사원
		FOREIGN KEY (
			dept -- 부서
		)
		REFERENCES gradle_jdbc.department ( -- 부서
			dept_no -- 부서번호
		);
		
-- 사용자 추가
drop user if exists 'user_gradle_jdbc'@'localhost';
grant all privileges on gradle_jdbc.* to 'user_gradle_jdbc'@'localhost' identified by 'rootroot';
flush privileges;
