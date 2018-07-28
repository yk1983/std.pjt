DROP TABLE TB_SAMPLE;
CREATE TABLE TB_SAMPLE (
	USER_NO NUMBER(5) PRIMARY KEY
, 	USER_NAME VARCHAR2(100) NOT NULL
, 	USER_EMAIL VARCHAR2(200) NOT NULL
, 	USER_PASSWD VARCHAR2(20) NOT NULL
, 	REG_DTM DATE
);


CREATE SEQUENCE SEQ_SAMPLE START WITH 1 INCREMENT BY 1 MAXVALUE 10000;

SELECT * FROM TB_SAMPLE;

INSERT INTO TB_SAMPLE(USER_NO,USER_NAME,USER_EMAIL,USER_PASSWD,REG_DTM) VALUES(SEQ_SAMPLE.NEXTVAL,'������','admin@gmail.com','12345',SYSDATE);