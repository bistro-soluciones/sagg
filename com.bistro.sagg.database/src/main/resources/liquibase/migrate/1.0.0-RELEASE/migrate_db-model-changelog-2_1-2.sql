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

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::1::sebastian.lavie
CREATE TABLE supplier_contacts (id BIGINT AUTO_INCREMENT NOT NULL, firstname VARCHAR(25) NULL, lastname VARCHAR(25) NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, CONSTRAINT PK_SUPPLIER_CONTACTS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 20, '7:a5760bee1e4627b79e12dd55f5bce249', 'createTable tableName=supplier_contacts', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::2::sebastian.lavie
CREATE TABLE suppliers (id BIGINT AUTO_INCREMENT NOT NULL, business_name VARCHAR(50) NULL, supplier_id VARCHAR(12) NOT NULL, contact_id BIGINT NOT NULL, CONSTRAINT PK_SUPPLIERS PRIMARY KEY (id), UNIQUE (id), UNIQUE (contact_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 21, '7:6f1b5b789292d3ce6f38105b2921548c', 'createTable tableName=suppliers', '', 'EXECUTED', NULL, NULL, '3.5.1', '4497845735');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

