--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 13:42
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 13:42:57.172' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::14::sebastian.lavie
ALTER TABLE franchises MODIFY name VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('14', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 14, '7:b583387a3a3c59adbd4dc30b652b3b34', 'modifyDataType columnName=name, tableName=franchises', '', 'EXECUTED', NULL, NULL, '3.5.1', '4303378392');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::15::sebastian.lavie
ALTER TABLE franchise_branches MODIFY name VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('15', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 15, '7:cf19fa155d30a816f0734175301f5c93', 'modifyDataType columnName=name, tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4303378392');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

