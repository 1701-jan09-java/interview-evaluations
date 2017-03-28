-- Sequences, auto-generated id's start at 100 to leave room for manually created entries

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

CREATE TABLE ie_batch
(
	b_id INT4 PRIMARY KEY,
	b_name VARCHAR(5000) UNIQUE
);


CREATE TABLE ie_eval_type
(
	et_id INT4 PRIMARY KEY,
	et_description VARCHAR(5000) NOT NULL
);

CREATE TABLE ie_person_role
(
	pr_id INT4 PRIMARY KEY,
	pr_title VARCHAR(5000) NOT NULL
);

CREATE TABLE ie_person
(
	p_id INT4 PRIMARY KEY,
	p_firstname VARCHAR(5000) NOT NULL,
	p_lastname VARCHAR(5000) NOT NULL,
	p_role INT4 NOT NULL, FOREIGN KEY(p_role) REFERENCES ie_person_role(pr_id)
);

CREATE TABLE ie_eval
(
	e_id INT4 PRIMARY KEY,
	e_week INT4 NOT NULL,
	e_date DATE NOT NULL,
	e_eval_type INT4 NOT NULL,FOREIGN KEY(e_eval_type) REFERENCES ie_eval_type(et_id),
	e_pid_trainee INT4 NOT NULL, FOREIGN KEY(e_pid_trainee) REFERENCES ie_person(p_id),
	e_batch INT4 NOT NULL, FOREIGN KEY(e_batch) REFERENCES ie_batch(b_id)
);

CREATE TABLE ie_eval_comment
(
	ec_id INT4 PRIMARY KEY,
	ec_text VARCHAR(5000) NOT NULL,
	ec_eid INT4 NOT NULL, FOREIGN KEY(ec_eid) REFERENCES ie_eval(e_id)
);

CREATE TABLE ie_person_batch
(
	join_id INT4 DEFAULT nextval('person_batch_seq'::regclass) PRIMARY KEY,
	person_id INT4 NOT NULL, FOREIGN KEY(person_id) REFERENCES ie_person(p_id),
	batch_id INT4 NOT NULL, FOREIGN KEY(batch_id) REFERENCES ie_batch(b_id)
);

CREATE TABLE ie_subject
(
	s_id INT4 PRIMARY KEY,
	s_subject VARCHAR(150) NOT NULL
);

CREATE TABLE ie_question_pool
(
	qp_id INT4 PRIMARY KEY,
	qp_question_text VARCHAR(5000) NOT NULL,
	qp_max_communication_score INT4 NOT NULL,
	qp_max_knowledge_score INT4 NOT NULL,
	qp_sid INT4 NOT NULL, FOREIGN KEY(qp_sid) REFERENCES ie_subject(s_id),
	qp_count INT4 DEFAULT 0 NOT NULL,
	qp_last_date_used DATE
);

CREATE TABLE ie_question_eval
(
	qe_id INT4 PRIMARY KEY,
	qe_communication_score INT4 NOT NULL,
	qe_knowledge_score INT4 NOT NULL,
	qe_eid INT4 NOT NULL, FOREIGN KEY(qe_eid) REFERENCES ie_eval(e_id),
	qe_qpid INT4 NOT NULL, FOREIGN KEY(qe_qpid) REFERENCES ie_question_pool(qp_id)
);

CREATE TABLE ie_question_comment
(
	qc_id INT4 PRIMARY KEY,
	qc_comment_text VARCHAR(5000) NOT NULL,
	qc_eid INT4 NOT NULL, FOREIGN KEY(qc_eid) REFERENCES ie_question_eval(qe_id)
);
