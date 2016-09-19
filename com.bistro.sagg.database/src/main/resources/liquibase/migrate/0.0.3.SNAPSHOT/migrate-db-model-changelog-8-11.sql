--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 19-09-16 11:30
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-19 11:30:17.244' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::8::sebastian.lavie
CREATE TABLE franchises (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, CONSTRAINT PK_FRANCHISES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 8, '7:099ce9d4f939987cad7a7a54452e4b16', 'createTable tableName=franchises', '', 'EXECUTED', NULL, NULL, '3.5.1', '4295418218');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::9::sebastian.lavie
CREATE TABLE franchise_branches (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(25) NULL, address_l1 VARCHAR(25) NULL, address_l2 VARCHAR(25) NULL, city_id BIGINT NULL, CONSTRAINT PK_FRANCHISE_BRANCHES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 9, '7:4698f543c326f45f1a2b750cf1c4a642', 'createTable tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '4295418218');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::10::sebastian.lavie
CREATE TABLE franchiseds (id BIGINT AUTO_INCREMENT NOT NULL, firstname VARCHAR(25) NULL, lastname VARCHAR(25) NULL, person_id VARCHAR(12) NOT NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, address_l1 VARCHAR(25) NULL, address_l2 VARCHAR(25) NULL, city_id BIGINT NULL, franchise_id BIGINT NOT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_FRANCHISEDS PRIMARY KEY (id), UNIQUE (franchise_branch_id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('10', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 10, '7:b510a4280c04de41004130bb6daf68f0', 'createTable tableName=franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '4295418218');

--  Changeset src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml::11::sebastian.lavie
ALTER TABLE employees ADD franchise_branch_id BIGINT NOT NULL AFTER `position_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('11', 'sebastian.lavie', 'src/main/resources/liquibase/0.0.3-SNAPSHOT/db-model-changelog-1.xml', NOW(), 11, '7:e27fe81b77b426307220640252636937', 'addColumn tableName=employees', '', 'EXECUTED', NULL, NULL, '3.5.1', '4295418218');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

