DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
                     id BIGINT AUTO_INCREMENT NOT NULL,
                     dob DATE NULL,
                     first_name VARCHAR(100) NULL,
                     last_name VARCHAR(100) NULL,
                     gender VARCHAR(100),
                     user_name VARCHAR(100) NOT NULL,
                     password VARCHAR(100) NOT NULL,
                     role VARCHAR(100) NOT NULL,
                     uuid char(36) NOT NULL,
                     department_id BIGINT NULL,
                     date_created timestamp NOT NULL,
                     last_updated timestamp NOT NULL,
                     CONSTRAINT PK_EMPLOYEE PRIMARY KEY (id)
) AUTO_INCREMENT=10000;
DROP TABLE IF EXISTS address;
CREATE TABLE address (
                       id BIGINT AUTO_INCREMENT NOT NULL,
                       address_type VARCHAR(100) NULL,
                       city VARCHAR(100)  NULL,
                       postal_code VARCHAR(100) NULL,
                       country VARCHAR(100) NULL,
                       street VARCHAR(100) NULL,
                       state VARCHAR(100) NULL,
                       employee_id BIGINT NOT NULL,
                       date_created timestamp NOT NULL,
                       last_updated timestamp NOT NULL,
                       CONSTRAINT PK_ADDRESS PRIMARY KEY (id)
) AUTO_INCREMENT=10000;
DROP TABLE IF EXISTS bank_account;
CREATE TABLE bank_account (
                          id BIGINT AUTO_INCREMENT NOT NULL,
                          account_holder_name VARCHAR(100)  NULL,
                          account_number BIGINT NOT NULL,
                          account_type VARCHAR(100) NOT NULL,
                          bank_name VARCHAR(100)  NULL,
                          branch_name VARCHAR(100)  NULL,
                          ifsc_code VARCHAR(100)  NULL,
                          employee_id BIGINT NOT NULL,
                          date_created timestamp NOT NULL,
                          last_updated timestamp NOT NULL,
                          CONSTRAINT PK_BANK_ACCOUNT PRIMARY KEY (id)
) AUTO_INCREMENT=10000;
DROP TABLE IF EXISTS department;
CREATE TABLE department (
                               id BIGINT AUTO_INCREMENT NOT NULL,
                               department_name VARCHAR(100) NOT NULL,
                               manager char(36) NULL,
                               date_created timestamp NOT NULL,
                               last_updated timestamp NOT NULL,
                               CONSTRAINT PK_DEPARTMENT PRIMARY KEY (id)
) AUTO_INCREMENT=10000;
DROP TABLE IF EXISTS employee_details;
CREATE TABLE employee_details (
                          id BIGINT AUTO_INCREMENT NOT NULL,
                          aadhar_card  BIGINT NOT NULL,
                          degree VARCHAR(100) NOT NULL,
                          education VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NULL,
                          emergency_phone_no BIGINT NULL,
                          pancard VARCHAR(100) NULL,
                          joining_date DATE NULL,
                          working_phone_no BIGINT NOT NULL,
                          employee_id BIGINT NOT NULL,
                          date_created timestamp NOT NULL,
                          last_updated timestamp NOT NULL,
                          CONSTRAINT PK_EMPLOYEE_DETAILS PRIMARY KEY (id)
) AUTO_INCREMENT=10000;
DROP TABLE IF EXISTS skill_set;
CREATE TABLE skill_set (
                            id BIGINT AUTO_INCREMENT NOT NULL,
                            skill_name VARCHAR(100) NOT NULL,
                            date_created timestamp NOT NULL,
                            last_updated timestamp NOT NULL,
                            CONSTRAINT PK_SKILL_SET PRIMARY KEY (id)
) AUTO_INCREMENT=10000;



CREATE TABLE employee_skillset (
                            mapping_id  BIGINT AUTO_INCREMENT NOT NULL,
                            employee_id BIGINT NOT NULL,
                            skill_set_id BIGINT NOT NULL,
                            CONSTRAINT PK_EMPLOYEE_SKILLSET PRIMARY KEY (mapping_id)
) AUTO_INCREMENT=10000;

ALTER TABLE employee_skillset
    ADD CONSTRAINT fk_employee_skillset_employee_id FOREIGN KEY (employee_id) REFERENCES employee (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE employee_skillset
    ADD CONSTRAINT fk_employee_skillset_skillset_id FOREIGN KEY (skill_set_id) REFERENCES skill_set (id) ON UPDATE NO ACTION ON DELETE NO ACTION;


ALTER TABLE employee ADD CONSTRAINT fk_employee_department_id FOREIGN KEY (department_id) REFERENCES department (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE address ADD CONSTRAINT fk_address_employee_id FOREIGN KEY (employee_id) REFERENCES employee (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE bank_account ADD CONSTRAINT fk_bank_account_employee_id FOREIGN KEY (employee_id) REFERENCES employee(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE employee_details ADD CONSTRAINT fk_employee_details_employee_id FOREIGN KEY (employee_id) REFERENCES employee(id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE skill_set ADD CONSTRAINT unique_skill_name UNIQUE (skill_name);
