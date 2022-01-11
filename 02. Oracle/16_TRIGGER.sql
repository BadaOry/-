/*--------------------------------
       <TRIGGER>
          테이블이 INSERT, UPDATE, DELETE 등 DML 구문에 의해 변경될 경우(테이블에 이벤트 발생 시)
          자동으로 실행될 내용을 정의해놓는 객체
          
        * 트리거의 종류
           1) SQL문의 실행 시기에 따른 분류
              - BEFORE TRIGGER : 해당 SQL 문장 실행 전 트리거를 실행
              - AFTER TRIGGER  : 해당 SQL 문장 실행 후 트리거 실행
           
           2) SQL문에 의해 영향을 받는 행에 따른 분류
              - 문장 트리거(STATEMENT TRIGGER) : 해당 SQL문에 대해 한 번만 트리거 실행
              - 행 트리거 (ROW TRIGGER)        : 해당 SQL문에 영향을 받는 행마다 트리거 실행
                                               (FOR EACH ROW 옵션을 기술)
       
        [표현법]
            CRAETE OR REPLACE TRIGGER 트리거명
            BEFORE|AFTER INSERT|UPDATE|DELETE ON 테이블명
            [FOR EACH ROW]
            [DECLARE
                선언부]
            BEGIN
                실행부
            [EXCEPTION
                예외처리부]
            END;
            /
*/--------------------------------
-- 1. 문장 & 행 트리거 테스트
-- 1-1) 문장 트리거 생성
CREATE OR REPLACE TRIGGER TRG_01
AFTER UPDATE ON EMPLOYEE
BEGIN
    DBMS_OUTPUT.PUT_LINE('업데이트 실행!!');
END;
/

-- 1-2) 업데이트 해보기 
--    ▷ 문장 트리거도 실행 됨
UPDATE EMPLOYEE
SET DEPT_CODE = 'D1'
WHERE DEPT_CODE = 'D9';

-- 1-3) 업데이트 된 내용 확인 & 롤백
SELECT * FROM EMPLOYEE;

ROLLBACK;

-- 1-4) 행 트리거 생성
CREATE OR REPLACE TRIGGER TRG_02
AFTER UPDATE ON EMPLOYEE
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('변경 전 : ' || :OLD.DEPT_CODE || 
                         ', 변경 후 : ' || :NEW.DEPT_CODE);
END;
/

-- 1-5) 업데이트 해보기 
--    ▷ TRG_02 세 번 실행 후, 문장 트리거 TRG_01 한 번 실행함 
UPDATE EMPLOYEE
SET DEPT_CODE = 'D1'
WHERE DEPT_CODE = 'D9';

-- 1-6) 업데이트 된 내용 확인 & 롤백
SELECT * FROM EMPLOYEE;

ROLLBACK;


-- 2. 상품 입고, 출고 관련 예시
-- 2-1) 상품에 대한 데이터를 보관할 테이블 생성 (PRODUCT)
CREATE TABLE PRODUCT(
    PCODE NUMBER PRIMARY KEY,    -- 상품 코드
    PNAME VARCHAR2(30),          -- 상품명
    BRAND VARCHAR2(30),          -- 브랜드명
    PRICE NUMBER,                -- 가격
    STOCK NUMBER DEFAULT 0       -- 재고
);

-- 2-2) 상품 코드가 중복되지 않게 새로운 번호를 발생하는 시퀀스 객체 생성
--      & 데이터 입력 & 확인
CREATE SEQUENCE SEQ_PCODE;

INSERT INTO PRODUCT VALUES(SEQ_PCODE.NEXTVAL, 'Z플립', '삼성', 15000000, DEFAULT);
INSERT INTO PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '아이폰13', '애플', 10000000, DEFAULT);
INSERT INTO PRODUCT VALUES(SEQ_PCODE.NEXTVAL, '샤오미폰', '샤오미', 8000000, DEFAULT);

SELECT * FROM PRODUCT;

-- 2-2) 상품 입출고 상세 이력을 보관할 테이블 (PRODETAIL)
CREATE TABLE PRODETAIL(
    DCODE NUMBER PRIMARY KEY,                  -- 입출력 이력 코드
    PDATE DATE,                                -- 상품 입/출고일
    AMOUNT NUMBER,                             -- 수량
    STATUS VARCHAR2(10),                       -- 상태 (입고, 출고)
    PCODE NUMBER,                              -- 상품 코드(외래키, PRODUCT테이블 참조)
    CHECK(STATUS IN ('입고', '출고')),
    FOREIGN KEY(PCODE) REFERENCES PRODUCT 
);

-- 2-3) 시퀀스 생성
CREATE SEQUENCE SEQ_DCODE;

-- 2-4) 데이터 입력 : 1번 상품이 오늘 날짜로 10개 입고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 10, '입고', 1);

-- 2-5) 테이블 확인 
--      : PRODUCT 테이블의 재고가 업데이트되지 않음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; -- DCODE가 다른 숫자부터 시작되면 에러까지 포함되서 그렇다

-- 2-6) PRODUCT 테이블의 재고 수량도 업데이트
UPDATE PRODUCT
SET STOCK = STOCK + 10
WHERE PCODE = 1;

-- 2-7) 테이블 확인 
--      : PRODUCT 테이블의 재고를 수동으로 업데이트 했음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL;

-- 2-8) 2번 상품이 오늘 날자로 20개 입고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 20, '입고', 2);

-- 2-9) 테이블 확인 
--      : PRODUCT 테이블의 재고가 업데이트되지 않음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-10) PRODUCT 테이블의 재고 수량도 업데이트
UPDATE PRODUCT
SET STOCK = STOCK + 20
WHERE PCODE = 2;

-- 2-11) 3번 상품이 오늘 날자로 5개 입고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 5, '입고', 3);

-- 2-12) 테이블 확인 
--      : PRODUCT 테이블의 재고가 업데이트되지 않음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-13) PRODUCT 테이블의 재고 수량도 업데이트
UPDATE PRODUCT
SET STOCK = STOCK + 5
WHERE PCODE = 3;

-- 2-14) 테이블 확인 
--      : PRODUCT 테이블의 재고를 수동으로 업데이트 했음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-15) 2번 상품이 오늘 날짜로 5개 출고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 5, '출고', 2);

-- 2-16) 테이블 확인 
--      : PRODUCT 테이블의 재고가 업데이트되지 않음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-13) PRODUCT 테이블의 재고 수량도 업데이트
UPDATE PRODUCT
SET STOCK = STOCK -5
WHERE PCODE = 2;

-- 2-14) 테이블 확인 
--      : PRODUCT 테이블의 재고를 수동으로 업데이트 했음
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-15) PRODETAIL 테이블에 데이터 삽입시
--       PRODUCT 테이블에 재고 수량이 자동 업테이트 되는 트리거 생성
CREATE OR REPLACE TRIGGER TRG_PRO_STOCK
AFTER INSERT ON PRODETAIL
FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE(:NEW.STATUS || ' ' || :NEW.AMOUNT || ' ' || :NEW.PCODE);
    
    -- 상품이 입고된 경우
        IF (:NEW.STATUS = '입고') THEN 
            UPDATE PRODUCT
            SET STOCK = STOCK + :NEW.AMOUNT
            WHERE PCODE = :NEW.PCODE;
        END IF;
        
    -- 상품이 출고된 경우
        IF (:NEW.STATUS = '출고') THEN 
            UPDATE PRODUCT
            SET STOCK = STOCK - :NEW.AMOUNT
            WHERE PCODE = :NEW.PCODE;
        END IF;

END;
/

-- 2-16) 롤백 : 안됨. 당연함. DDL 구문이라 자동으로 커밋 됨.
ROLLBACK;

-- 2-17) 2번 상품이 오늘 날짜로 20개 입고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 20, '입고', 2);

-- 2-18) 테이블 확인 
--      : PRODUCT 테이블의 재고가 우왕~ 자동으로 업데이트 되었습니다 !
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-19) 2번 상품이 오늘 날짜로 25개 출고
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 25, '출고', 2);

-- 2-20) 테이블 확인 
--      : PRODUCT 테이블의 재고가 우왕~ 자동으로 업데이트 되었습니다 !
SELECT * FROM PRODUCT;
SELECT * FROM PRODETAIL; 

-- 2-19) 2번 상품이 오늘 날짜로 25개 출고..되면.. 재고가 -인데 어쩌죠 !
--       ▶ 직접 해보세용~
INSERT INTO PRODETAIL VALUES(SEQ_DCODE.NEXTVAL, SYSDATE, 25, '출고', 2);

