/*--------------------------------
    <DDL (Data Definition Language>
       데이터 정의 언어로 오라클에서 제공하는 객체를 만들고 (CREATE),
       변경하고(ALTER), 삭제하는 (DROP)등 실제 데이터 값이 아닌
       데이터의 구조 자체를 정의하는 언어
       ▷ DB관리자, 설계자가 주로 사용함  
       
    <ALTER>
       오라클에서 제공하는 객체를 수정하는 구문
    
    <테이블 수정>
       [표현법]
          ALTER TABLE 테이블명 수정힐내용;
          
        * 수정할 내용
            1) 컬럼의 추가/수정/삭제
            2) 제약 조건 추가/삭제 
                - 제약 조건은 수정 불가하므로, 삭제한 후 새로 추가해야 함
            3) 테이블명/컬럼명/제약조건명 변경
       
*/--------------------------------

-- 1. 실습에 사용할 실습테이블 생성
-- 1-1) DEPARTMENT 테이블을 서브쿼리를 이용하여 생성 & 복사 & 조회
CREATE TABLE DEPT_COPY
AS SELECT *
   FROM DEPARTMENT;

SELECT * FROM DEPT_COPY;


-- 2. 컬럼 추가
-- 2-1) 컬럼 추가 (ADD, 기본값 X)
--         : ALTER TABLE 테이블명 ADD 컬럼명 데이터타입
--           ▷ CNAME 컬럼을 맨 뒤에 추가
--              기본값을 지정하지 않으면 새로 추가된 컬럼은 NULL 값으로 채워짐
ALTER TABLE DEPT_COPY ADD CNAME VARCHAR2(20);

-- 2-2) 컬럼 추가 후 조회
--         : CNAME 컬럼이 추가됨
SELECT * FROM DEPT_COPY;

-- 2-3) 컬럼 추가 (ADD, 기본값 O)
--         : ALTER TABLE 테이블명 ADD 컬럼명 데이터타입 [DEFAULT 기본값]
ALTER TABLE DEPT_COPY ADD LNAME VARCHAR2(40) DEFAULT '한국';

-- 2-4) 컬럼 추가 후 조회
--         : LNAME 컬럼이 추가되고, 기본 값 '한국'으로 채워짐
SELECT * FROM DEPT_COPY;


-- 3. 컬럼 수정
-- 3-1-1) 데이터 타입 변경 (MODIFY)
--         : ALTER TABLE 테이블명 MODIFY 컬럼명 변경할데이터타입 변경할기본값
--           ▷ DEPT_ID 컬럼의 데이터 타입을 CHAR(3)으로 변경 
--            * 단, 변경하려는 자료형의 크기보다 이미 큰 값이 존재하면 에러 발생
--            * 단, 값이 없으면 데이터 타입 변경이 가능
ALTER TABLE DEPT_COPY MODIFY DEPT_ID CHAR(3);
ALTER TABLE DEPT_COPY MODIFY DEPT_ID CHAR(2);
-- △ 에러 : 이미 모든 값들이 3byte로 지정되어 있으므로 에러
ALTER TABLE DEPT_COPY MODIFY DEPT_TITLE VARCHAR2(10);
-- △ 에러 : 이미 10byte 이상인 데이터들이 있어,
--          바꾸려는 크기 보다 사이즈가 크므로 에러
★ALTER TABLE DEPT_COPY MODIFY DEPT_ID NUMBER;
-- △ NULL 이면 사용 가능
--
★ALTER TABLE DEPT_COPY ADD NUM VARCHAR(20) DEFAULT '1';
★ALTER TABLE DEPT_COPY MODIFY NUM NUMBER;
-- △ 에러 : 

-- 3-1-2) 데이터 타입 변경 후 후 조회
--         : 데이터 타입이 바뀌고, 3byte 설정이라 데이터에 공백이 하나 더 생길 것임
SELECT * FROM DEPT_COPY;


-- 3-2) 다중 수정
--        : DEPT_TITLE 컬럼의 데이터 타입을 VARCHAR2(40)
--          LOCATION_ID 컬럼의 데이터 타입을 VARCHAR2(2),
--          LNAME 컬럼의 기본값을 미국으로 변경하기
-- 3-2-1) 데이터 타입 변경 (MODIFY)
ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE VARCHAR2(40)
MODIFY LOCATION_ID VARCHAR2(2)
MODIFY LNAME DEFAULT '미국';


-- 4. 컬럼 삭제 (DROP COLUMN)
--         : ALTER TABLE 테이블명 DROP COLUMN 컬럼명
--           ▷ 데이터 값이 기록되어 있어도 같이 삭제 함. 
--              * 단, 삭제된 컬럼 복구는 불가능
--              * 테이블에는 최소 한 개의 컬럼이 존재해야 함
--              * 참조되고 있는 컬럼이 있다면 삭제 불가능
-- 4-1) DEPT_ID 컬럼 삭제하기 & 조회
ALTER TABLE DEPT_COPY DROP COLUMN DEPT_ID;
SELECT * FROM DEPT_COPY;

-- 4-2) 롤백 해보기 : DDL 구문은 복구 불가
ROLLBACK;

-- 4-3) 모든 컬럼들 삭제해보기
ALTER TABLE DEPT_COPY DROP COLUMN DEPT_TITLE;
ALTER TABLE DEPT_COPY DROP COLUMN LOCATION_ID;
ALTER TABLE DEPT_COPY DROP COLUMN CNAME;
ALTER TABLE DEPT_COPY DROP COLUMN LNAME;
ALTER TABLE DEPT_COPY DROP COLUMN NUM;
-- △ 에러 : 최소한 하나의 컬럼은 존재해야 함

-- 4-4) 부모 테이블의 제약조건 걸린 컬럼 삭제해보기
ALTER TABLE MEMBER_GRADE DROP COLUMN GRADE_CODE;
-- △ 에러 : 참조되고 있는 컬럼이 있어 삭제 불가
ALTER TABLE MEMBER_GRADE DROP COLUMN GRADE_CODE CASCADE CONSTRAINTS;
-- △ 삭제 됨 : 그러나 자식 테이블에 영향을 주지는 않음

-- 4-5) 컬럼 삭제 후 부모, 자식 테이블 조회
SELECT * FROM MEMBER_GRADE;
SELECT * FROM MEMBER;

-- 5. 제약조건 추가 / 삭제
-- 5-1) 제약 조건 추가
--        PRIMARY KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] PRIAMARY KEY(컬럼명);
--        FOREIGN KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] FOREIGN KEY(컬럼명) REFERENCES 테이블명 [(컬럼명)];
--        UNIQUE      : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] UNIQUE(컬럼명);
--        CHECK       : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] CHECK(컬럼에 대한 조건);
--        NOT NULL    : ALTER TABLE 테이블명 MODIFY 컬럼명 NOT NULL;

-- 5-1-1) 먼저 만들었던 DEPT_COPY 테이블 삭제 후 다시 만들기 & 생성 확인
DROP TABLE DEPT_COPY;

CREATE TABLE DEPT_COPY
AS SELECT *
   FROM DEPARTMENT;
   
SELECT * FROM DEPT_COPY;

-- 5-1-2) DEPT_COPY 테이블에
--        DEPT_ID는 PK, DEPT_TITLE은 UNIQUE와 NOT NULL 제약조건 추가
ALTER TABLE DEPT_COPY 
ADD CONSTRAINT DEPT_COPY_DEPT_ID_PK PRIMARY KEY(DEPT_ID)
ADD CONSTRAINT DEPT_COPY_DEPT_TITLE_UQ UNIQUE(DEPT_TITLE)
MODIFY DEPT_TITLE CONSTRAINT DEPT_COPY_DEPT_TITLE_NN NOT NULL;

-- 5-1-3) 제약조건 확인 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE,
       UC.TABLE_NAME,
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';

-- 5-2-1) EMPLOYEE 테이블에 DEPT_CODE, JOB_CODE에 FK 제약 조건 적용
ALTER TABLE EMPLOYEE
ADD CONSTRAINT EMPLOYEE_DEPT_CODE_FK FOREIGN KEY(DEPT_CODE) REFERENCES DEPARTMENT
ADD CONSTRAINT EMPLOYEE_JOB_CODE_FK FOREIGN KEY(JOB_CODE) REFERENCES JOB(JOB_CODE);

-- 5-2-2) 제약조건 확인 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE,
       UC.TABLE_NAME,
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'EMPLOYEE';

-- 5-3) 제약 조건 삭제
--        NOT NULL : MODIFY 컬럼명 NULL
--        나머지 : DROP CONSTRAINT 제약조건명
-- 5-3-1) DEPT_COPY_DEPT_ID_PK 제약 조건 삭제
ALTER TABLE DEPT_COPY DROP CONSTRAINT DEPT_COPY_DEPT_ID_PK;

-- 5-3-2) DEPT_COPY_DEPT_TITLE_UQ
--        DEPT_COPY_DEPT_TITLE_NN 제약 조건 한꺼번ㄴ에 삭제
ALTER TABLE DEPT_COPY 
DROP CONSTRAINT DEPT_COPY_DEPT_TITLE_UQ
MODIFY DEPT_TITLE NULL;

-- 5-3-3) 제약조건 확인 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE,
       UC.TABLE_NAME,
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING (CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'DEPT_COPY';

-- * 제약 조건 수정은 불가. 즉, 삭제 후 다시 제약 조건을 추가해야 함


-- 6.테이블명 / 컬럼명 / 제약조건명 변경
-- 6-1) 컬럼명 변경     : ALTER TABLE 테이블명 RENAME COLUMN 기존컬럼명 TO 변경할컬럼명
-- 6-1-1) DEPT_COPY 테이블에 DEPT_TITLE 컬럼명을 DEPT_NAME으로 변경
ALTER TABLE DEPT_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;

-- 6-2) 제약 조건명 변경 : ALTER TABLE 테이블명 RENAME CONSTRAINT 기존제약조건명 TO 변경할제약조건명
-- 6-2-1) 제약 조건 하나 추가
ALTER TABLE DDPT_COPY MODIFY DEPT_NAME NOT NULL;

-- 6-2-2) 방금 추가한 제약조건의 이름 변경
ALTER TABLE DEPT_COPY RENAME CONSTRAINT SYS_C007191 TO DEPT_COPY_DEPT_ID_NN;

-- 6-3) 테이블 명 변경   
--       1) ALTER TABLE 테이블명 RENAME TO 변경할테이블명
--       2) RENAME 기존테이블명 TO 변경할테이블명
-- 6-3-1)  이름 변경 후 확인
ALTER TABLE DEPT_COPY RENAME TO DEPT_TEST;
RENAME DEPT_COPY TO DEPT_TEST;

SELECT * FROM DEPT_TEST;

/*--------------------------------
    <DROP>
       데이터베이스 객체를 삭제하는 구문
*/--------------------------------
-- 1. 테이블 삭제 
DROP TABLE DETP_TEST

-- 1. 부모 테이블 만들기
-- 1-1) 먼저 만들었던 MEMBER_GRADE 테이블 삭제 후 다시 만들기
DROP TABLE MEMBER_GRADE;

CREATE TABLE MEMBER_GRADE (
    GRADE_CODE NUMBER PRIMARY KEY,
    GRADE_NAME VARCHAR2(30) NOT NULL
);

-- 1-2) 부모 테이블에 데이터 입력
INSERT INTO MEMBER_GRADE VALUES(10, '일반회원');
INSERT INTO MEMBER_GRADE VALUES(20, '우수회원');
INSERT INTO MEMBER_GRADE VALUES(30, '특별회원');

-- 1-3) 부모 테이블 조회
SELECT * FROM MEMBER_GRADE;


-- 2. 자식 테이블 만들기
-- 2-1) 먼저 만들었던 MEMBER 테이블 삭제 후 다시 만들기
DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    MEMBER_NO NUMBER CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY, 
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20) NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    GRADE_ID NUMBER REFERENCES MEMBER_GRADE(GRADE_CODE), 
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);                

-- 2-2) 데이터 입력
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '춘향이', '여', 20, 10, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '홍길동', '남', 25, NULL, DEFAULT);


-- 3. 테이블 삭제
DROP TABLE MEMBER_GADE;
DROP TABLE MEMBER;