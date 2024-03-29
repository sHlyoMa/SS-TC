SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE ROLE_ACTION;
DROP TABLE ACTION;
DROP TABLE COMMENTS;
DROP TABLE ISSUES;
DROP TABLE ISSUE_ENVIRONMENT;
DROP TABLE ISSUE_PRIORITY;
DROP TABLE ISSUE_STATUS;
DROP TABLE ISSUE_TYPE;
DROP TABLE USER_PROJECT;
DROP TABLE PROJECTS;
DROP TABLE USER_INFO;
DROP TABLE USER;
DROP TABLE ROLE;




/* Create Tables */

CREATE TABLE ACTION
(
	ACTION_ID INT NOT NULL,
	ACTION VARCHAR(60),
	PRIMARY KEY (ACTION_ID)
);


CREATE TABLE COMMENTS
(
	COMMENT_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
	ISSUE_ID INT NOT NULL UNIQUE,
	USER_ID INT NOT NULL UNIQUE,
	CREATING_DATE DATE,
	VALUE VARCHAR(255),
	PRIMARY KEY (COMMENT_ID)
);


CREATE TABLE ISSUES
(
	ISSUE_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
	REPORTER_ID INT NOT NULL,
	ASSIGNER_ID INT NOT NULL,
	TYPE_ID INT NOT NULL,
	STATUS_ID INT NOT NULL,
	PRIORITY_ID INT NOT NULL,
	PROJECT_ID INT NOT NULL,
	ENVIRONMENT_ID INT NOT NULL,
	BLOCKED VARCHAR(10),
	NAME VARCHAR(50),
	I_KEY VARCHAR(10) NOT NULL,
	DESCRIPTION VARCHAR(255),
	CREATED DATE,
	UPDATED DATE,
	CLOSE_DATE DATE,
	PRIMARY KEY (ISSUE_ID)
);


CREATE TABLE ISSUE_ENVIRONMENT
(
	ENVIRONMENT_ID INT NOT NULL UNIQUE,
	ENVIRONMENT VARCHAR(20),
	PRIMARY KEY (ENVIRONMENT_ID)
);


CREATE TABLE ISSUE_PRIORITY
(
	PRIORITY_ID INT NOT NULL UNIQUE,
	PRIORITY VARCHAR(20) NOT NULL,
	PRIMARY KEY (PRIORITY_ID)
);


CREATE TABLE ISSUE_STATUS
(
	STATUS_ID INT NOT NULL UNIQUE,
	STATUS VARCHAR(20) NOT NULL,
	PRIMARY KEY (STATUS_ID)
);


CREATE TABLE ISSUE_TYPE
(
	TYPE_ID INT NOT NULL UNIQUE,
	TYPE VARCHAR(20) NOT NULL,
	PRIMARY KEY (TYPE_ID)
);


CREATE TABLE PROJECTS
(
	PROJECT_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	DESCRIPTION VARCHAR(255),
	PM_USER_ID INT,
	PRIMARY KEY (PROJECT_ID)
);


CREATE TABLE ROLE
(
	ID INT(1) NOT NULL UNIQUE AUTO_INCREMENT,
	ROLE_NAME VARCHAR(20) NOT NULL,
	DESCRIPTION VARBINARY(255),
	PRIMARY KEY (ID)
);


CREATE TABLE ROLE_ACTION
(
	ROLE_ID INT NOT NULL,
	ACTION_ID INT NOT NULL
);


CREATE TABLE USER
(
	USER_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
	LOGIN VARCHAR(45) NOT NULL UNIQUE,
	PASSWORD VARCHAR(45) NOT NULL,
	CR_DATE DATE,
	ENABLED INT(2),
	ROLE_ID INT(1),
	PRIMARY KEY (USER_ID)
);


CREATE TABLE USER_INFO
(
	USER_INFO_ID INT NOT NULL UNIQUE,
	FIRST_NAME VARCHAR(50),
	SECOND_NAME VARCHAR(50),
	EMAIL VARCHAR(50)
);


CREATE TABLE USER_PROJECT
(
	USER_PROJECT_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
	PROJECT_ID INT NOT NULL UNIQUE,
	USER_ID INT NOT NULL UNIQUE,
	PRIMARY KEY (USER_PROJECT_ID)
);



/* Create Foreign Keys */

ALTER TABLE ROLE_ACTION
	ADD FOREIGN KEY (ACTION_ID)
	REFERENCES ACTION (ACTION_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMENTS
	ADD FOREIGN KEY (ISSUE_ID)
	REFERENCES ISSUES (ISSUE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (ENVIRONMENT_ID)
	REFERENCES ISSUE_ENVIRONMENT (ENVIRONMENT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (PRIORITY_ID)
	REFERENCES ISSUE_PRIORITY (PRIORITY_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (STATUS_ID)
	REFERENCES ISSUE_STATUS (STATUS_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (TYPE_ID)
	REFERENCES ISSUE_TYPE (TYPE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECTS (PROJECT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER_PROJECT
	ADD FOREIGN KEY (PROJECT_ID)
	REFERENCES PROJECTS (PROJECT_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ROLE_ACTION
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES ROLE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES ROLE (ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE COMMENTS
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (REPORTER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE ISSUES
	ADD FOREIGN KEY (ASSIGNER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE PROJECTS
	ADD FOREIGN KEY (PM_USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER_INFO
	ADD FOREIGN KEY (USER_INFO_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE USER_PROJECT
	ADD FOREIGN KEY (USER_ID)
	REFERENCES USER (USER_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;

INSERT INTO ISSUE_TYPE (TYPE_ID,TYPE) VALUES(1,'Task'),(2,'Bug');

INSERT INTO ISSUE_STATUS (STATUS_ID,STATUS) VALUES(1,'Open'),
(2,'In Progress'),(3,'Resolved'),(4,'Closed'),(5,'Reopened');

INSERT INTO ISSUE_PRIORITY (PRIORITY_ID,PRIORITY) VALUES(1,'None'),
(2,'Blocker'),(3,'Critical'),(4,'Major'), (5,'Minor'), (6,'Trivial');

INSERT INTO ISSUE_ENVIRONMENT (ENVIRONMENT_ID,ENVIRONMENT) VALUES(1,'None');
(2,'Development'), (3,'Stage'), (4,'Test'), (5,'Production');

INSERT INTO ROLE (ID,ROLE_NAME) VALUES(1,'Administrator');
(2,'Project manager'), (3,'Product owner'), (4,'Developer'), (5,'QC');

INSERT INTO ACTION (ACTION_ID, ACTION) VALUES(1,'Add project'),
(2,'Edit project'), (3,'Delete project'), (4,'Add issue'), (5,'Edit issue'),
(6,'Delete issue'), (7,'Add user'), (8,'Edit user'), (9,'Delete user'),
(10,'Add role'), (11,'Edit role'), (12,'Delete role'), (13,'Add comment'),
(14,'Edit comment'), (15,'Delete comment'), (16,'Assign to me'), (17,'Assign to'),
(18,'Start progress'), (19,'Resolve issue'), (20,'Close issue'), (21,'Reopen issue'),
(22,'Blocked'), (23,'Send E-mail'), (24,'View issues'), (25,'View projects'), 
(26,'View users'), (27,'View roles'), (28, 'Reset issue');

INSERT INTO ROLE_ACTION ( ID, ACTION_ID ) VALUES
( 1, 10 ), ( 1, 11 ), ( 1, 12 ), ( 1, 7 ), ( 1, 8 ), ( 1, 9 ), ( 1, 26 ), ( 1, 27 ), 
( 2, 1 ), ( 2, 2 ), ( 2, 3 ), ( 2, 4 ), ( 2, 5 ), ( 2, 6 ), ( 2, 13 ), ( 2, 14 ), ( 2, 15 ), 
     ( 2, 16 ), ( 2, 17 ), ( 2, 18 ), ( 2, 19 ), ( 2, 20 ), ( 2, 21 ), ( 2, 22 ), ( 2, 23 ),
     ( 2, 24 ), ( 2, 25 ),
( 3, 4 ), ( 3, 13 ), ( 3, 24 ), ( 3, 25 ), 
( 4, 4 ), ( 4, 13 ), ( 4, 16 ), ( 4, 18 ), ( 4, 19 ), ( 4, 22 ), ( 4, 24 ), ( 4, 25 ), 
( 5, 4 ), ( 5, 13 ), ( 5, 16 ), ( 5, 17 ), ( 5, 18 ), ( 5, 19 ), ( 5, 20 ), ( 5, 21 ), ( 5, 22 ), 
	 ( 5, 24 ), ( 5, 25 );

INSERT INTO USER (LOGIN,PASSWORD,ROLE_ID,ENABLED, CR_DATE) VALUES('admin','ff33af02c70e247dc5de4506432afe89dbc89ae4c4ff5f2b4c86c5e3e48c76ad',1,1,'2013-06-30 17:06:13');



