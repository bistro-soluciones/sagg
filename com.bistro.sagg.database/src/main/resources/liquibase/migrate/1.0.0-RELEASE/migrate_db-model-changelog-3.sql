--  *********************************************************************
--  Update Database Script
--  *********************************************************************
--  Change Log: src/main/resources/liquibase/master-changelog.xml
--  Ran at: 08-10-16 17:12
--  Against: root@localhost@jdbc:mysql://localhost:3306/sagg_dev?createDatabaseIfNotExist=true
--  Liquibase version: 3.5.1
--  *********************************************************************

--  Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 1, LOCKEDBY = 'SEBASTIAN-ENVI (192.168.1.115)', LOCKGRANTED = '2016-10-08 17:12:57.236' WHERE ID = 1 AND LOCKED = 0;

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::1::sebastian.lavie
ALTER TABLE product_categories ADD franchise_branch_id BIGINT NOT NULL AFTER `name`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('1', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 37, '7:25e50ae7efae5129d8f01d0a20e2dfaf', 'addColumn tableName=product_categories', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::2::sebastian.lavie
CREATE TABLE payment_methods (id BIGINT AUTO_INCREMENT NOT NULL, name VARCHAR(20) NULL, CONSTRAINT PK_PAYMENT_METHODS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('2', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 38, '7:e47be38d4ff1393952e80b279d7aa89a', 'createTable tableName=payment_methods', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::3::sebastian.lavie
CREATE TABLE sale_orders (id BIGINT AUTO_INCREMENT NOT NULL, order_number VARCHAR(18) NULL, order_datetime timestamp NULL, order_status VARCHAR(20) NULL, employee_id BIGINT NOT NULL, billing_document_id BIGINT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_SALE_ORDERS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('3', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 39, '7:734dfe5afd20a0658cf0817e059c378c', 'createTable tableName=sale_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::4::sebastian.lavie
CREATE TABLE sale_order_items (id BIGINT AUTO_INCREMENT NOT NULL, product_id BIGINT NOT NULL, quantity INT DEFAULT 0 NULL, order_id BIGINT NOT NULL, CONSTRAINT PK_SALE_ORDER_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('4', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 40, '7:18e7e265c183ee7412e5239d2d65d021', 'createTable tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::5::sebastian.lavie
CREATE TABLE purchase_orders (id BIGINT AUTO_INCREMENT NOT NULL, order_number VARCHAR(18) NULL, order_datetime timestamp NULL, order_status VARCHAR(20) NULL, supplier_id BIGINT NOT NULL, requestor_id BIGINT NOT NULL, receiver_id BIGINT NULL, billing_document_id BIGINT NULL, franchise_branch_id BIGINT NOT NULL, CONSTRAINT PK_PURCHASE_ORDERS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('5', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 41, '7:6240fa1b25aabf79cb9241c58f854b42', 'createTable tableName=purchase_orders', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::6::sebastian.lavie
CREATE TABLE purchase_order_items (id BIGINT AUTO_INCREMENT NOT NULL, product_id BIGINT NOT NULL, quantity INT DEFAULT 0 NULL, order_id BIGINT NOT NULL, CONSTRAINT PK_PURCHASE_ORDER_ITEMS PRIMARY KEY (id), UNIQUE (id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('6', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 42, '7:edb0fd89410ca4105a50061c1976bb68', 'createTable tableName=purchase_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::7::sebastian.lavie
ALTER TABLE billing_documents DROP COLUMN supplier_id;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('7', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 43, '7:e0a2416554c874a31c44dfaf8a9ea9d7', 'dropColumn columnName=supplier_id, tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::8::sebastian.lavie
ALTER TABLE billing_documents DROP COLUMN franchise_branch_id;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('8', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 44, '7:06284278f94c39f82e322f9e0853572c', 'dropColumn columnName=franchise_branch_id, tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::9::sebastian.lavie
ALTER TABLE billing_documents ADD document_datetime timestamp NULL AFTER `document_number`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('9', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 45, '7:e180545395c660478bae170a0c4ee8df', 'addColumn tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::10::sebastian.lavie
ALTER TABLE billing_documents ADD payment_method_id BIGINT NOT NULL AFTER `document_datetime`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('10', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 46, '7:19a75502f38e58d3bc6b98e8276585f6', 'addColumn tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '5957578635');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::11::sebastian.lavie
ALTER TABLE sale_order_items ADD amount DECIMAL NULL AFTER `quantity`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('11', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 47, '7:fc0c6f84cafc90a1f9af21758fc3e495', 'addColumn tableName=sale_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '6035213417');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::12::sebastian.lavie
ALTER TABLE franchises ADD code VARCHAR(4) NOT NULL AFTER `name`;

ALTER TABLE franchises ADD UNIQUE (code);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('12', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 48, '7:77d9e9c61436a96684dcba26a0fac7c8', 'addColumn tableName=franchises', '', 'EXECUTED', NULL, NULL, '3.5.1', '6053258132');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::13::sebastian.lavie
ALTER TABLE franchise_branches ADD code VARCHAR(4) NOT NULL AFTER `name`;

ALTER TABLE franchise_branches ADD UNIQUE (code);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('13', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 49, '7:ed758c34ae7dac7658f6dc8804ba26be', 'addColumn tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '6053258132');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::14::sebastian.lavie
CREATE TABLE branches_of_franchiseds (franchise_id BIGINT NOT NULL, franchised_id BIGINT NOT NULL, CONSTRAINT PK_BRANCHES_OF_FRANCHISEDS PRIMARY KEY (franchise_id, franchised_id));

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('14', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 50, '7:7af0be3ea0ae5ef482370399408af40f', 'createTable tableName=branches_of_franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '6107445158');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::15::sebastian.lavie
ALTER TABLE franchiseds DROP COLUMN franchise_id;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('15', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 51, '7:c9cb2ca41d650f90fafa2a7f9271b0df', 'dropColumn columnName=franchise_id, tableName=franchiseds', '', 'EXECUTED', NULL, NULL, '3.5.1', '6107445158');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::16::sebastian.lavie
ALTER TABLE franchise_branches ADD franchise_id BIGINT NOT NULL AFTER `code`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('16', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 52, '7:aacb903442961e394b5a6570c537cca1', 'addColumn tableName=franchise_branches', '', 'EXECUTED', NULL, NULL, '3.5.1', '6108713873');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::17::sebastian.lavie
ALTER TABLE payment_methods ADD method_type VARCHAR(15) NULL AFTER `id`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('17', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 53, '7:8ea52ef448aa536790f6c2826b5ad21d', 'addColumn tableName=payment_methods', '', 'EXECUTED', NULL, NULL, '3.5.1', '6126364071');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::18::sebastian.lavie
ALTER TABLE billing_documents MODIFY document_number VARCHAR(18);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('18', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 54, '7:34b05808aa7c008c1be3f86158288e8b', 'modifyDataType columnName=document_number, tableName=billing_documents', '', 'EXECUTED', NULL, NULL, '3.5.1', '6141726865');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::19::sebastian.lavie
ALTER TABLE suppliers ADD franchise_branch_id BIGINT NOT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('19', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 55, '7:b5cc974175d1d0a0bec6b6600f9e14cb', 'addColumn tableName=suppliers', '', 'EXECUTED', NULL, NULL, '3.5.1', '6566326526');

--  Changeset src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml::20::sebastian.lavie
ALTER TABLE purchase_order_items ADD purchase_unit_price DECIMAL NULL AFTER `quantity`;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE, DEPLOYMENT_ID) VALUES ('20', 'sebastian.lavie', 'src/main/resources/liquibase/1.0.0-RELEASE/db-model-changelog-3.xml', NOW(), 56, '7:c61f2ed8eb2456798d89edd22bfd4bae', 'addColumn tableName=purchase_order_items', '', 'EXECUTED', NULL, NULL, '3.5.1', '7748390894');

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

