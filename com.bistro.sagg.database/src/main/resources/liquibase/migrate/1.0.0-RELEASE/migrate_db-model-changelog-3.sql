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

--  Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = 0, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

