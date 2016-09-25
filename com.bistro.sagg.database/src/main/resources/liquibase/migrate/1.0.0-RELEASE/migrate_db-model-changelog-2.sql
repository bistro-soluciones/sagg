--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 21-09-16 19:44
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-09-24 19:42:33.978' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::1::sebastian.lavie
CREATE TABLE supplier_contacts (id BIGINT AUTO_INCREMENT NOT NULL, firstname VARCHAR(25) NULL, lastname VARCHAR(25) NULL, email VARCHAR(50) NULL, phone VARCHAR(15) NULL, cellphone VARCHAR(15) NULL, CONSTRAINT PK_SUPPLIER_CONTACTS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 20, '7:a5760bee1e4627b79e12dd55f5bce249', 'createTable tableName=supplier_contacts', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::2::sebastian.lavie
CREATE TABLE suppliers (id BIGINT AUTO_INCREMENT NOT NULL, business_name VARCHAR(50) NULL, supplier_id VARCHAR(12) NOT NULL, contact_id BIGINT NOT NULL, CONSTRAINT PK_SUPPLIERS PRIMARY KEY (id), UNIQUE (id), UNIQUE (contact_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 21, '7:6f1b5b789292d3ce6f38105b2921548c', 'createTable tableName=suppliers', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::3::sebastian.lavie
CREATE TABLE product_categories (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NULL, CONSTRAINT PK_PRODUCT_CATEGORIES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 22, '7:84f4bd1819ef6c8ad359e997bb6ab0ba', 'createTable tableName=product_categories', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::4::sebastian.lavie
CREATE TABLE supplies (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NULL, product_category_id BIGINT NOT NULL, stock INT NULL, stock_min INT NULL, unit_price DECIMAL NULL, CONSTRAINT PK_SUPPLIES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 23, '7:79b97b01523f5590f3e383ee19ced5f4', 'createTable tableName=supplies', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::5::sebastian.lavie
CREATE TABLE marketable_products (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(50) NULL, product_category_id BIGINT NOT NULL, stock INT NULL, stock_min INT NULL, unit_price DECIMAL NULL, sell_unit_price DECIMAL NULL, CONSTRAINT PK_MARKETABLE_PRODUCTS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 24, '7:ff6190121863b27ad421aa551e46b9bf', 'createTable tableName=marketable_products', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::6::sebastian.lavie
ALTER TABLE marketable_products CHANGE sell_unit_price unit_sales_price DECIMAL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('6', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 25, '7:7aaadf8cf38c16fc25cb4a810e757457', 'renameColumn newColumnName=unit_sales_price, oldColumnName=sell_unit_price, tableName=marketable_products', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::7::sebastian.lavie
ALTER TABLE supplies DROP COLUMN unit_price;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('7', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 26, '7:53bd252609bee8310c489c4a6fb2626b', 'dropColumn columnName=unit_price, tableName=supplies', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::8::sebastian.lavie
ALTER TABLE marketable_products DROP COLUMN unit_price;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 27, '7:5d0a0269babc892832fe1ef31bddba69', 'dropColumn columnName=unit_price, tableName=marketable_products', '', 'EXECUTED', NULL, NULL, '3.5.1', '4756955265');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::9::sebastian.lavie
CREATE TABLE id_generator (generator_name VARCHAR(20) NOT NULL, generator_value BIGINT NULL, CONSTRAINT PK_ID_GENERATOR PRIMARY KEY (generator_name), UNIQUE (generator_name));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 28, '7:7cc322c3af3a5ce6d69f431191f7523b', 'createTable tableName=id_generator', '', 'EXECUTED', NULL, NULL, '3.5.1', '4763067637');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml::10::sebastian.lavie
CREATE TABLE suppliers_for_categories (supplier_id BIGINT NOT NULL, product_category_id BIGINT NOT NULL, CONSTRAINT PK_SUPPLIERS_FOR_CATEGORIES PRIMARY KEY (supplier_id, product_category_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('10', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-2.xml', NOW(), 29, '7:f18a8c2da55d4d476647b148f13c193a', 'createTable tableName=suppliers_for_categories', '', 'EXECUTED', NULL, NULL, '3.5.1', '4825523997');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

