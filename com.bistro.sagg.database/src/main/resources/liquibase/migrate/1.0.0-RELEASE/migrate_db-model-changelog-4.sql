--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 31-10-16 20:47
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg_dev?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-11-06 15:49:15.666' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::1::sebastian.lavie
CREATE TABLE product_formats (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(10) NULL, CONSTRAINT PK_PRODUCT_FORMATS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 58, '7:7a64ce7096d68a4d315c0d3c68851047', 'createTable tableName=product_formats', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::2::sebastian.lavie
ALTER TABLE products ADD product_format_id BIGINT NULL AFTER `product_category_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 59, '7:fd2afdce03192a2ba42750b3df2926c2', 'addColumn tableName=products', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::3::sebastian.lavie
ALTER TABLE product_formats ADD unit INT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 60, '7:b27dc5d404fb596a930858dd4f730f7f', 'addColumn tableName=product_formats', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::4::sebastian.lavie
CREATE TABLE recipes (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(30) NULL, description VARCHAR(500) NULL, product_category_id BIGINT NOT NULL, unit_sales_price DECIMAL NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_RECIPES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 61, '7:3c0fd68a4b28020302d7684c4340f02b', 'createTable tableName=recipes', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::5::sebastian.lavie
CREATE TABLE recipe_lines (id BIGINT AUTO_INCREMENT NOT NULL, recipe_id BIGINT NOT NULL, supply_id BIGINT NOT NULL, portion INT NULL, CONSTRAINT PK_RECIPE_LINES PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 62, '7:f006758590e9a08fb26f8812795f56e5', 'createTable tableName=recipe_lines', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::6::sebastian.lavie
CREATE TABLE combos (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(30) NULL, description VARCHAR(500) NULL, unit_sales_price DECIMAL NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_COMBOS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('6', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 63, '7:2505a544e1301a1f179bde72f0f107dd', 'createTable tableName=combos', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::7::sebastian.lavie
CREATE TABLE combo_items (id BIGINT AUTO_INCREMENT NOT NULL, combo_id BIGINT NOT NULL, product_id BIGINT NULL, recipe_id BIGINT NULL, CONSTRAINT PK_COMBO_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('7', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 87, '7:e25cf04ab8cf3c876d9a7b6f2929261c', 'createTable tableName=combo_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '9043396373');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::8::sebastian.lavie
ALTER TABLE combo_items ADD quantity INT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 65, '7:9b24825e3bd745b5e9cd99a81cbeed05', 'addColumn tableName=combo_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::9::sebastian.lavie
ALTER TABLE recipes ADD combo_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 66, '7:3315b9be58d81d17f75d11218ac174ed', 'addColumn tableName=recipes', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::10::sebastian.lavie
ALTER TABLE sale_order_items ADD recipe_id BIGINT NULL AFTER `product_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('10', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 67, '7:2ab7f0227b9f2f0f93994c94be3b8a38', 'addColumn tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::11::sebastian.lavie
ALTER TABLE sale_order_items MODIFY product_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('11', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 68, '7:cad7488ab12bfd9b2f658d4661a6244d', 'dropNotNullConstraint columnName=product_id, tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::12::sebastian.lavie
ALTER TABLE sale_order_items ADD comno_id BIGINT NULL AFTER `recipe_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('12', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 69, '7:a7207c930563e89988ab0011b1c15147', 'addColumn tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::13::sebastian.lavie
ALTER TABLE sale_order_items ADD combo_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('13', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 70, '7:ba4d18ce2c7dad8a33c602d4bcba1d07', 'addColumn tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::14::sebastian.lavie
ALTER TABLE billing_items ADD recipe_id BIGINT NULL AFTER `product_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('14', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 71, '7:08296a4442192eba13c19342d50ec347', 'addColumn tableName=billing_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8458156973');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::15::sebastian.lavie
ALTER TABLE billing_items ADD comno_id BIGINT NULL AFTER `recipe_id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('15', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 72, '7:1b2e47ba7dff6cff89f6b9311bbbef74', 'addColumn tableName=billing_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::16::sebastian.lavie
DROP TABLE billing_items;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('16', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 73, '7:a8a6eed600180e3b541a37552ebeb624', 'dropTable tableName=billing_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::17::sebastian.lavie
DROP TABLE billing_documents;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('17', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 74, '7:5db2f22743e2b89ebc66293923d9e662', 'dropTable tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::18::sebastian.lavie
CREATE TABLE purchase_billing_documents (id BIGINT AUTO_INCREMENT NOT NULL, document_type_id BIGINT NOT NULL, document_number VARCHAR(30) NULL, document_datetime timestamp NULL, payment_method_id BIGINT NOT NULL, supplier_id BIGINT NOT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_PURCHASE_BILLING_DOCUMENTS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('18', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 75, '7:ed6125bc8f4fa45a9490d4090ede4525', 'createTable tableName=purchase_billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::19::sebastian.lavie
CREATE TABLE purchase_billing_items (id BIGINT AUTO_INCREMENT NOT NULL, product_id BIGINT NOT NULL, quantity INT NULL, unit_price DECIMAL NULL, tax FLOAT NULL, tax_amount DECIMAL NULL, billing_document_id BIGINT NOT NULL, CONSTRAINT PK_PURCHASE_BILLING_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('19', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 76, '7:73247804726e23332fa7ecd9e9b2c34b', 'createTable tableName=purchase_billing_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::20::sebastian.lavie
CREATE TABLE sale_billing_documents (id BIGINT AUTO_INCREMENT NOT NULL, document_type_id BIGINT NOT NULL, document_number VARCHAR(30) NULL, document_datetime timestamp NULL, payment_method_id BIGINT NOT NULL, employee_id BIGINT NOT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_SALE_BILLING_DOCUMENTS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('20', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 77, '7:cc0bc7f2fa82498b6b4333cdee77073f', 'createTable tableName=sale_billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::21::sebastian.lavie
CREATE TABLE sale_billing_items (id BIGINT AUTO_INCREMENT NOT NULL, combo_id BIGINT NULL, product_id BIGINT NULL, recipe_id BIGINT NULL, quantity INT NULL, unit_price DECIMAL NULL, tax FLOAT NULL, tax_amount DECIMAL NULL, billing_document_id BIGINT NOT NULL, CONSTRAINT PK_SALE_BILLING_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('21', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 85, '7:d1bed45d3fbb05178ffa455402550983', 'createTable tableName=sale_billing_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8981096847');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::22::sebastian.lavie
ALTER TABLE sale_orders DROP COLUMN billing_document_id;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('22', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 79, '7:59c77868746e6667dd5cfe44f44e63d3', 'dropColumn columnName=billing_document_id, tableName=sale_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::23::sebastian.lavie
ALTER TABLE sale_orders ADD billing_document_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('23', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 80, '7:115a80871b9c73f89b82044183bd0a63', 'addColumn tableName=sale_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::24::sebastian.lavie
ALTER TABLE purchase_orders DROP COLUMN billing_document_id;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('24', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 81, '7:f161e280145d5a518e9f751b340a95f2', 'dropColumn columnName=billing_document_id, tableName=purchase_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::25::sebastian.lavie
ALTER TABLE purchase_orders ADD billing_document_id BIGINT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('25', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 82, '7:6642d8c92ed82b6b3c50e7157c643e82', 'addColumn tableName=purchase_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '8962903369');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::26::sebastian.lavie
ALTER TABLE purchase_order_items ADD available_stock INT NULL AFTER `purchase_unit_price`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('26', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 83, '7:01805deeaad00356bdda34a90b8024ae', 'addColumn tableName=purchase_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '8990166164');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml::27::sebastian.lavie
ALTER TABLE product_categories ADD category_type VARCHAR(20) NULL AFTER `id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('27', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-4.xml', NOW(), 86, '7:37a17fd98c2fed0eb3b1279bcb52e473', 'addColumn tableName=product_categories', '', 'EXECUTED', NULL, NULL, '3.5.1', '8989987468');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

