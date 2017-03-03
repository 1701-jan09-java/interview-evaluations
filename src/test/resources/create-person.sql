CREATE TABLE ie_person (
    p_id INTEGER PRIMARY KEY,
    p_firstname VARCHAR(4000),
    p_lastname VARCHAR(4000)
);

CREATE SEQUENCE person_seq
START WITH 100
INCREMENT BY 1;