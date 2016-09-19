--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 13:46
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 13:46:41.453' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::16::sebastian.lavie
ALTER TABLE franchise_branches MODIFY address_l1 VARCHAR(50);

ALTER TABLE franchise_branches MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('16', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 16, '7:525d72bad638605143d2d2b7e87f8acc', 'modifyDataType columnName=address_l1, tableName=franchise_branches; modifyDataType columnName=address_l2, tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4303602685');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::17::sebastian.lavie
ALTER TABLE franchiseds MODIFY address_l1 VARCHAR(50);

ALTER TABLE franchiseds MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('17', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 17, '7:fe7d7fb7b28f1e3f167ba25fe8c2e629', 'modifyDataType columnName=address_l1, tableName=franchiseds; modifyDataType columnName=address_l2, tableName=franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '4303602685');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::18::sebastian.lavie
ALTER TABLE employees MODIFY address_l1 VARCHAR(50);

ALTER TABLE employees MODIFY address_l2 VARCHAR(50);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('18', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 18, '7:1ae179d9ffebc6f36d1a29aa1a644244', 'modifyDataType columnName=address_l1, tableName=employees; modifyDataType columnName=address_l2, tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4303602685');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

