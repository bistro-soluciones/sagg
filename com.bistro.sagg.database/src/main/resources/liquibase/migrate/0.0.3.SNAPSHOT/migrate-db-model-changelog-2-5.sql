--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 10:41
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 10:41:48.862' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::2::sebastian.lavie
CREATE TABLE countries (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, CONSTRAINT PK_COUNTRIES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 2, '7:996617f9d11d1023e36ede79f9e0621f', 'createTable tableName=countries', '', 'EXECUTED', NULL, NULL, '3.5.1', '4292509828');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::3::sebastian.lavie
CREATE TABLE states (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, country_id BIGINT NOT NULL, CONSTRAINT PK_STATES PRIMARY KEY (id), UNIQUE (id), UNIQUE (country_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 3, '7:884d7c119246890805e72506270d86e7', 'createTable tableName=states', '', 'EXECUTED', NULL, NULL, '3.5.1', '4292509828');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::4::sebastian.lavie
CREATE TABLE cities (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, state_id BIGINT NOT NULL, CONSTRAINT PK_CITIES PRIMARY KEY (id), UNIQUE (id), UNIQUE (state_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 4, '7:59513319c5846cf9591918075a7e3708', 'createTable tableName=cities', '', 'EXECUTED', NULL, NULL, '3.5.1', '4292509828');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::5::sebastian.lavie
ALTER TABLE employees ADD address_l1 VARCHAR(25) NULL, ADD address_l2 VARCHAR(25) NULL, ADD city_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 5, '7:4a2222284e04e2ab59131a0b760e820f', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4292509828');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

