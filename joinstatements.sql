/*
 * Join NAME, CONTACT
 */

SELECT NAME.NAME_ID, NAME.FIRST,  NAME.MIDDLE, NAME.LAST FROM NAME INNER JOIN CONTACT ON NAME.NAME_ID = CONTACT.NAME_ID;

/*
 * Join CONTACT, ADDRESS
 */

SELECT CONTACT.CONTACT_ID, ADDRESS.ADDRESS_ID, ADDRESS.STREET,  ADDRESS.CITY, ADDRESS.STATE, ADDRESS.ZIP,  FROM ADDRESS INNER JOIN CONTACT ON ADDRESS.ADDRESS_ID = CONTACT.ADDRESS_ID;

/*
 * Join CONTACT, ADDRESS, NAME tables
 */
SELECT CONTACT.CONTACT_ID, NAME.FIRST, NAME.LAST, ADDRESS.ADDRESS_ID, ADDRESS.STREET,  ADDRESS.CITY, ADDRESS.STATE, ADDRESS.ZIP,  FROM ADDRESS INNER JOIN CONTACT ON ADDRESS.ADDRESS_ID = CONTACT.ADDRESS_ID INNER JOIN NAME ON CONTACT.NAME_ID = NAME.NAME_ID;

/*
 * Show all tables
 */
SELECT * FROM CONTACT;
SELECT * FROM PHONE;
SELECT * FROM ADDRESS;
SELECT * FROM NAME;