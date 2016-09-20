--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 21:16
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 21:16:07.460' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::19::sebastian.lavie
ALTER TABLE employees ADD start_working_date date NULL AFTER `position_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('19', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 19, '7:8fa2d331740cb467007033dd7b958a13', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4330568536');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

