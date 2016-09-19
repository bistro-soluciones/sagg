--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 12:55
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 12:55:36.856' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::12::sebastian.lavie
CREATE TABLE franchises_by_countries (franchise_id BIGINT NOT NULL, country_id BIGINT NOT NULL, CONSTRAINT PK_FRANCHISES_BY_COUNTRIES PRIMARY KEY (franchise_id, country_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('12', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 12, '7:fae1d72d561c493b5616392f4eb6ecb5', 'createTable tableName=franchises_by_countries', '', 'EXECUTED', NULL, NULL, '3.5.1', '4300538111');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

