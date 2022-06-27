CREATE TABLE CONTACT(
CONTACT_ID BIGINT PRIMARY KEY,
NAME_ID BIGINT NOT NULL,
ADDRESS_ID BIGINT,
EMAIL VARCHAR(64)
);

CREATE TABLE PHONE(
PHONE_ID BIGINT PRIMARY KEY,
NUMBER VARCHAR(64),
PHONE_TYPE ENUM('home', 'work', 'mobile'),
CONTACT_ID BIGINT NOT NULL
);

CREATE TABLE ADDRESS(
ADDRESS_ID BIGINT PRIMARY KEY,
STREET VARCHAR(64),
CITY VARCHAR(64),
STATE VARCHAR(64),
ZIP VARCHAR(64)
);

CREATE TABLE NAME(
NAME_ID BIGINT PRIMARY KEY,
FIRST VARCHAR(64) NOT NULL,
MIDDLE VARCHAR(64),
LAST VARCHAR(64)
);



ALTER TABLE PHONE ADD FOREIGN KEY (CONTACT_ID) REFERENCES CONTACT(CONTACT_ID);
ALTER TABLE CONTACT ADD FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID);
ALTER TABLE CONTACT ADD FOREIGN KEY (NAME_ID) REFERENCES NAME(NAME_ID);

