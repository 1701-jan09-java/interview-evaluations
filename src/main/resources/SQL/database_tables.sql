-- Create tables
CREATE TABLE ie_batch (
    b_id INTEGER PRIMARY KEY,
    b_name VARCHAR UNIQUE
);

CREATE TABLE ie_eval_type (
    et_id INTEGER PRIMARY KEY,
    et_description VARCHAR UNIQUE NOT NULL
);

CREATE TABLE ie_person (
    p_id INTEGER PRIMARY KEY,
    p_firstname VARCHAR NOT NULL,
    p_lastname VARCHAR NOT NULL
);

CREATE TABLE ie_eval (
    e_id INTEGER PRIMARY KEY,
    e_week INTEGER NOT NULL,
    e_date DATE NOT NULL,
    e_eval_type INTEGER NOT NULL,FOREIGN KEY(e_eval_type) REFERENCES ie_eval_type(et_id),
    e_pid_trainee INTEGER NOT NULL, FOREIGN KEY(e_pid_trainee) REFERENCES ie_person(p_id),
    e_batch INTEGER NOT NULL,FOREIGN KEY(e_batch) REFERENCES ie_batch(b_id)
);

CREATE TABLE ie_eval_comment (
    ec_id INTEGER PRIMARY KEY,
    ec_comment_text VARCHAR NOT NULL,
    ec_eid INTEGER NOT NULL,FOREIGN KEY(ec_eid) REFERENCES ie_eval(e_id)
);

CREATE TABLE ie_subject(
    s_id INTEGER PRIMARY KEY,
    s_subject VARCHAR(150) NOT NULL
);

CREATE TABLE ie_question_pool( 
    qp_id INTEGER PRIMARY KEY,
    qp_question_text VARCHAR(150) NOT NULL,
    qp_max_communication_score INTEGER NOT NULL,
    qp_max_knowledge_score INTEGER NOT NULL,
    qp_sid INTEGER, FOREIGN KEY(qp_sid) REFERENCES ie_subject(s_id),
    qp_count INTEGER NOT NULL
);

CREATE TABLE ie_question_eval (
    qe_id INTEGER PRIMARY KEY,
    qe_communication_score INTEGER NOT NULL,
    qe_knowledge_score INTEGER NOT NULL,
    qe_eid INTEGER NOT NULL,FOREIGN KEY(qe_eid) REFERENCES ie_eval(e_id),
    qe_qpid INTEGER NOT NULL,FOREIGN KEY(qe_qpid) REFERENCES ie_question_pool(qp_id)
);

CREATE TABLE ie_question_comment (
    qc_id INTEGER PRIMARY KEY,
    qc_comment_text VARCHAR NOT NULL,
    qc_eid INTEGER NOT NULL,FOREIGN KEY(qc_eid) REFERENCES ie_question_eval(qe_id)
);

CREATE TABLE ie_person_batch (
  join_id INTEGER,
  person_id INTEGER NOT NULL,
  batch_id INTEGER NOT NULL,
  PRIMARY KEY(join_id),
  FOREIGN KEY(person_id) REFERENCES ie_person(p_id),
  FOREIGN KEY(batch_id) REFERENCES ie_batch(b_id)
);

-- Sequences
CREATE SEQUENCE batch_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE eval_seq 
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE eval_type_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE person_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE eval_comment_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE question_eval_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE question_comment_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE question_pool_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE subject_seq
START WITH 100
INCREMENT BY 1;

CREATE SEQUENCE person_batch_seq
START WITH 100
INCREMENT BY 1;
 
ALTER TABLE ONLY ie_person_batch ALTER COLUMN join_id SET DEFAULT nextval('person_batch_seq');