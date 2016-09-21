--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 21-09-16 19:44
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Create Database Lock Table
CREATE TABLE DATABASECHANGELOGLOCK (ID INT NOT NULL, LOCKED BIT(1) NOT NULL, LOCKGRANTED datetime NULL, LOCKEDBY VARCHAR(255) NULL, CONSTRAINT PK_DATABASECHANGELOGLOCK PRIMARY KEY (ID));

--  Initialize Database Lock Table
DELETE FROM DATABASECHANGELOGLOCK;

INSERT INTO DATABASECHANGELOGLOCK (ID, LOCKED) VALUES (1, 0);

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-21 19:44:04.434' WHERE ID = 1 AND LOCKED = 0;

--  Create Database Change Log Table
CREATE TABLE DATABASECHANGELOG (ID VARCHAR(255) NOT NULL, AUTHOR VARCHAR(255) NOT NULL, FILENAME VARCHAR(255) NOT NULL, DATEEXECUTED datetime NOT NULL, ORDEREXECUTED INT NOT NULL, EXECTYPE VARCHAR(10) NOT NULL, MD5SUM VARCHAR(35) NULL, DESCRIPTION VARCHAR(255) NULL, COMMENTS VARCHAR(255) NULL, TAG VARCHAR(255) NULL, LIQUIBASE VARCHAR(20) NULL, CONTEXTS VARCHAR(255) NULL, LABELS VARCHAR(255) NULL, DEPLOYMENT_ID VARCHAR(10) NULL);

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::1::sebastian.lavie
CREATE TABLE employees (id BIGINT AUTO_INCREMENT NOT NULL, firstname VARCHAR(25) NULL, lastname VARCHAR(25) NULL, person_id VARCHAR(12) NOT NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, CONSTRAINT PK_EMPLOYEES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 1, '7:9ebac9ed4580337498796f06528b4b80', 'createTable tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::2::sebastian.lavie
CREATE TABLE countries (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, CONSTRAINT PK_COUNTRIES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 2, '7:996617f9d11d1023e36ede79f9e0621f', 'createTable tableName=countries', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::3::sebastian.lavie
CREATE TABLE states (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, country_id BIGINT NOT NULL, CONSTRAINT PK_STATES PRIMARY KEY (id), UNIQUE (id), UNIQUE (country_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 3, '7:884d7c119246890805e72506270d86e7', 'createTable tableName=states', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::4::sebastian.lavie
CREATE TABLE cities (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, state_id BIGINT NOT NULL, CONSTRAINT PK_CITIES PRIMARY KEY (id), UNIQUE (state_id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 4, '7:59513319c5846cf9591918075a7e3708', 'createTable tableName=cities', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::5::sebastian.lavie
ALTER TABLE employees ADD address_l1 VARCHAR(25) NULL, ADD address_l2 VARCHAR(25) NULL, ADD city_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 5, '7:4a2222284e04e2ab59131a0b760e820f', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::6::sebastian.lavie
CREATE TABLE positions (id BIGINT AUTO_INCREMENT NOT NULL, position VARCHAR(20) NULL, CONSTRAINT PK_POSITIONS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('6', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 6, '7:d46c8cfce6843def71898b3a64888f7b', 'createTable tableName=positions', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::7::sebastian.lavie
ALTER TABLE employees ADD position_id BIGINT NULL AFTER `person_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('7', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 7, '7:688017ffa9916a4d6ee1a9ac26ce6a22', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::8::sebastian.lavie
CREATE TABLE franchises (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, CONSTRAINT PK_FRANCHISES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 8, '7:099ce9d4f939987cad7a7a54452e4b16', 'createTable tableName=franchises', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::9::sebastian.lavie
CREATE TABLE franchise_branches (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, address_l1 VARCHAR(25) NULL, address_l2 VARCHAR(25) NULL, city_id BIGINT NULL, CONSTRAINT PK_FRANCHISE_BRANCHES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 9, '7:4698f543c326f45f1a2b750cf1c4a642', 'createTable tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::10::sebastian.lavie
CREATE TABLE franchiseds (id BIGINT AUTO_INCREMENT NOT NULL, firstname VARCHAR(25) NULL, lastname VARCHAR(25) NULL, person_id VARCHAR(12) NOT NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, address_l1 VARCHAR(25) NULL, address_l2 VARCHAR(25) NULL, city_id BIGINT NULL, franchise_id BIGINT NOT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_FRANCHISEDS PRIMARY KEY (id), UNIQUE (id), UNIQUE (franchise_branch_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('10', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 10, '7:b510a4280c04de41004130bb6daf68f0', 'createTable tableName=franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::11::sebastian.lavie
ALTER TABLE employees ADD franchise_branch_id BIGINT NOT NULL AFTER `position_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('11', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 11, '7:e27fe81b77b426307220640252636937', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::12::sebastian.lavie
CREATE TABLE franchises_by_countries (franchise_id BIGINT NOT NULL, country_id BIGINT NOT NULL, CONSTRAINT PK_FRANCHISES_BY_COUNTRIES PRIMARY KEY (franchise_id, country_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('12', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 12, '7:fae1d72d561c493b5616392f4eb6ecb5', 'createTable tableName=franchises_by_countries', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::13::sebastian.lavie
DROP INDEX state_id ON cities;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('13', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 13, '7:12d78c3b7086fe809a218e44970d778e', 'dropIndex indexName=state_id, tableName=cities', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::14::sebastian.lavie
ALTER TABLE franchises MODIFY name VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('14', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 14, '7:b583387a3a3c59adbd4dc30b652b3b34', 'modifyDataType columnName=name, tableName=franchises', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::15::sebastian.lavie
ALTER TABLE franchise_branches MODIFY name VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('15', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 15, '7:cf19fa155d30a816f0734175301f5c93', 'modifyDataType columnName=name, tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::16::sebastian.lavie
ALTER TABLE franchise_branches MODIFY address_l1 VARCHAR(50);

ALTER TABLE franchise_branches MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('16', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 16, '7:525d72bad638605143d2d2b7e87f8acc', 'modifyDataType columnName=address_l1, tableName=franchise_branches; modifyDataType columnName=address_l2, tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::17::sebastian.lavie
ALTER TABLE franchiseds MODIFY address_l1 VARCHAR(50);

ALTER TABLE franchiseds MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('17', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 17, '7:fe7d7fb7b28f1e3f167ba25fe8c2e629', 'modifyDataType columnName=address_l1, tableName=franchiseds; modifyDataType columnName=address_l2, tableName=franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::18::sebastian.lavie
ALTER TABLE employees MODIFY address_l1 VARCHAR(50);

ALTER TABLE employees MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('18', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 18, '7:1ae179d9ffebc6f36d1a29aa1a644244', 'modifyDataType columnName=address_l1, tableName=employees; modifyDataType columnName=address_l2, tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml::19::sebastian.lavie
ALTER TABLE employees ADD start_working_date date NULL AFTER `position_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('19', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-1.xml', NOW(), 19, '7:8fa2d331740cb467007033dd7b958a13', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

