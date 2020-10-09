
-- Transaction 실습 DB (은행 - Account)
-- Oracle 11 - 자동번호 생성 테이블 정의
-- Table 생성 (FOODMENU_TBL)
-- NEW.ID (Table의 id를 가리킴)
CREATE TABLE account_tbl
(
    idx NUMBER PRIMARY KEY,
    name VARCHAR2(30),
    balance NUMBER,
    regidate TIMESTAMP
);

-- Sequence 정의
CREATE SEQUENCE account_sequence
START WITH 1
INCREMENT BY 1;

-- Trigger 생성
-- BEFORE INSERT on '테이블명'
CREATE OR REPLACE TRIGGER account_trigger
BEFORE INSERT
    ON account_tbl
REFERENCING NEW AS NEW
FOR EACH ROW
BEGIN
SELECT account_sequence.nextval INTO :NEW.IDX FROM dual;
END;