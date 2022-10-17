DROP TABLE IF EXISTS employees CASCADE;
CREATE TABLE IF NOT EXISTS employees(
username varchar(20) NOT NULL PRIMARY KEY,
passwrod varchar(30) NOT NULL,
manager boolean NOT NULL
);

DROP TABLE IF EXISTS tickets CASCADE;
CREATE TABLE IF NOT EXISTS tickets(
	creator varchar(20),
	amount decimal, 
	description varchar,
	status varchar(10) DEFAULT 'PENDING',
	FOREIGN KEY(creator) REFERENCES employees(username)
);

INSERT INTO employees VALUES ('testemployee', 'empolyeepassword', FALSE);
INSERT INTO employees VALUES ('testmanager', 'managerpassword', TRUE);
INSERT INTO tickets VALUES ('testemployee', 2658.12, 'Business trip expenses during weeks 12-14');

SELECT * FROM tickets;
SELECT * FROM employees;