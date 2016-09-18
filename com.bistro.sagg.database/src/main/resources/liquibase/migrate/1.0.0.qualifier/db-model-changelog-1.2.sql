--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 17-09-16 16:59
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-17 16:59:05.803' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml::2::sebastian.lavie
ALTER TABLE employees CHANGE name firstname VARCHAR(25);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml', NOW(), 2, '7:81334d9a4e78839a9f8e1890499becec', 'renameColumn newColumnName=firstname, oldColumnName=name, tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4142346813');

--  Changeset src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml::3::sebastian.lavie
ALTER TABLE employees ADD lastname VARCHAR(25) NULL AFTER `firstname`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml', NOW(), 5, '7:e38bfebdef2bd3f7a280afc1d1b64a81', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4154788564');

--  Changeset src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml::4::sebastian.lavie
CREATE TABLE suppliers (id BIGINT AUTO_INCREMENT NOT NULL, business_name VARCHAR(50) NULL, supplier_id VARCHAR(12) NOT NULL, contact_name VARCHAR(50) NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, CONSTRAINT PK_SUPPLIERS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0.qualifier/db-model-changelog-1.xml', NOW(), 4, '7:f53f6d7d19c1a9e732c960994fe20073', 'createTable tableName=suppliers', '', 'EXECUTED', NULL, NULL, '3.5.1', '4142346813');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

