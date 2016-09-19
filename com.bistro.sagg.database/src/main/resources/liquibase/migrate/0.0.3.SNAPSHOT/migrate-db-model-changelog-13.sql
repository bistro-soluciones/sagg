--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 13:36
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 13:36:07.956' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::13::sebastian.lavie
DROP INDEX state_id ON cities;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('13', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 13, '7:12d78c3b7086fe809a218e44970d778e', 'dropIndex indexName=state_id, tableName=cities', '', 'EXECUTED', NULL, NULL, '3.5.1', '4302969392');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

