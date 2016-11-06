--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 31-10-16 20:47
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg_dev?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-10-31 20:47:29.548' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::1::sebastian.lavie
CREATE TABLE product_formats (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(10) NULL, CONSTRAINT PK_PRODUCT_FORMATS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 58, '7:7a64ce7096d68a4d315c0d3c68851047', 'createTable tableName=product_formats', '', 'EXECUTED', NULL, NULL, '3.5.1', '7957650848');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::2::sebastian.lavie
ALTER TABLE products ADD product_format_id BIGINT NULL AFTER `product_category_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 59, '7:fd2afdce03192a2ba42750b3df2926c2', 'addColumn tableName=products', '', 'EXECUTED', NULL, NULL, '3.5.1', '7957650848');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::3::sebastian.lavie
ALTER TABLE product_formats ADD unit INT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 60, '7:b27dc5d404fb596a930858dd4f730f7f', 'addColumn tableName=product_formats', '', 'EXECUTED', NULL, NULL, '3.5.1', '8014904945');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::4::sebastian.lavie
CREATE TABLE recipes (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(30) NULL, description VARCHAR(500) NULL, product_category_id BIGINT NOT NULL, unit_sales_price DECIMAL NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_RECIPES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 61, '7:3c0fd68a4b28020302d7684c4340f02b', 'createTable tableName=recipes', '', 'EXECUTED', NULL, NULL, '3.5.1', '8349787077');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::5::sebastian.lavie
CREATE TABLE recipe_lines (id BIGINT AUTO_INCREMENT NOT NULL, recipe_id BIGINT NOT NULL, supply_id BIGINT NOT NULL, portion INT NULL, CONSTRAINT PK_RECIPE_LINES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 62, '7:f006758590e9a08fb26f8812795f56e5', 'createTable tableName=recipe_lines', '', 'EXECUTED', NULL, NULL, '3.5.1', '8349787077');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::6::sebastian.lavie
CREATE TABLE combos (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(30) NULL, description VARCHAR(500) NULL, unit_sales_price DECIMAL NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_COMBOS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('6', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 63, '7:2505a544e1301a1f179bde72f0f107dd', 'createTable tableName=combos', '', 'EXECUTED', NULL, NULL, '3.5.1', '8372870163');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::7::sebastian.lavie
CREATE TABLE combo_items (id BIGINT AUTO_INCREMENT NOT NULL, combo_id BIGINT NOT NULL, product_id BIGINT NOT NULL, recipe_id BIGINT NOT NULL, CONSTRAINT PK_COMBO_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('7', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 64, '7:360a8c40d0d6e40824f006fd002d8401', 'createTable tableName=combo_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8385063169');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::8::sebastian.lavie
ALTER TABLE combo_items ADD quantity INT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 65, '7:9b24825e3bd745b5e9cd99a81cbeed05', 'addColumn tableName=combo_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8385909674');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::9::sebastian.lavie
ALTER TABLE recipes ADD combo_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 66, '7:3315b9be58d81d17f75d11218ac174ed', 'addColumn tableName=recipes', '', 'EXECUTED', NULL, NULL, '3.5.1', '8393421331');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

