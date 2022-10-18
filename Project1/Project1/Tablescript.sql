DROP TABLE IF EXISTS employees CASCADE;
CREATE TABLE IF NOT EXISTS employees(
username varchar(20) NOT NULL PRIMARY KEY,
passwrod varchar(30) NOT NULL,
manager int NOT NULL
);

DROP TABLE IF EXISTS tickets CASCADE;
CREATE TABLE IF NOT EXISTS tickets(
	creator varchar(20),
	amount decimal, 
	description varchar,
	status varchar(10) DEFAULT 'PENDING',
	id SERIAL UNIQUE,
	FOREIGN KEY(creator) REFERENCES employees(username),
	PRIMARY KEY(id)
);

INSERT INTO employees VALUES ('testemployee', 'employeepassword', 0);
INSERT INTO employees VALUES ('testmanager', 'managerpassword', 1);
INSERT INTO tickets VALUES ('testemployee', 2658.12, 'Business trip expenses during weeks 12-14');

SELECT * FROM tickets;
SELECT * FROM employees;