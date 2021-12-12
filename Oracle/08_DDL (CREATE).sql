
/*--------------------------------
    <DDL (Data Definition Language>
       데이터 정의 언어로 오라클에서 제공하는 객체를 만들고 (CREATE),
       변경하고(ALTER), 삭제하는 (DROP)등 실제 데이터 값이 아닌
       데이터의 구조 자체를 정의하는 언어
       ▷ DB관리자, 설계자가 주로 사용함
       
       * 오라클에서의 객체 : 테이블, 뷰, 시퀀스, 인덱스, 트리거, 프로시져, 함수, 동의어, 사용자 등
    
    <CREATE>
       데이터 베이스 객체를 생성하는 구문
        
    <테이블 생성>
       행과 열로 구성되는 가장 기본적인 데이터베이스 객체로서,
       데이터베이스 내에서 모든 데이터는 테이블에 저장됨
       
       [표현식]
          CREATE TABLE 테이블명 (
               컬럼명 자료형(크기) [DEFAULT 기본값] [제약 조건],
               컬럼명 자료형(크기) [DEFAULT 기본값] [제약 조건],
               ...
          );     
*/--------------------------------

-- 1. 회원에 대한 데이터를 담을 수 있는 MEMBER 테이블 생성
CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(20),
    MEMBER_PWD VARCHAR2(20),
    MEMBER_NAME VARCHAR2(20),
    MEMBER_DATE DATE DEFAULT SYSDATE
);

--DROP TABLE MEMBER;

-- 2. 만든 테이블 확인
DESC MEMBER; -- DESCRIBE의 약자
             -- : 테이블의 구조를 표시해주는 구문
SELECT * FROM MEMBER;

/*--------------------------------
    <컬럼에 주석 달기>
       [표현법]
          COMMENT ON COLUMN 테이블명.컬럼명 IS '주석내용';
*/--------------------------------

COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.MEMBER_PWD IS '비밀번호';
COMMENT ON COLUMN MEMBER.MEMBER_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.MEMBER_DATE IS '회원가입일';

/*--------------------------------
    <데이터 딕셔너리>
       자원을 효율적으로 관리하기 위한 다양한 객체들의 정보를 저장하는 시스템 테이블
       사용자가 객체를 생성하거나객체를 변경하는 드으이 작업을 할 때, 
       데이터베이스에 의해 자동으로 갱신되는 테이블
       * 데이터에 관한 데이터가 저장되어 있다고 해서 메타 데이터라고도 함
       
       - USER_TABLES : 사용자가 가지고 있는 테이블의 전반적인 구조를 확인하는 뷰 테이블
       - USER_TAB_COLUMNS : 테이블, 부의 컬럼과 관려노딘 정보를 조회하는 뷰 테이블 
*/--------------------------------

SELECT * FROM USER_TABLES WHERE TABLE_NAME = 'MEMBER';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MEMBER';

-- 3. 테이블에 샘플 데이터 추가 (INSERT)
-- INSERT INTO 테이블명[(컬럼명, ..., 컬럼명)] VALUES (값, ...,값);
INSERT INTO MEMBER VALUES ('USER1','1234','홍길동','2021-10-06');
INSERT INTO MEMBER VALUES ('USER2','1234','김철수',SYSDATE);
INSERT INTO MEMBER VALUES ('USER2','1234','김철수',DEFAULT);
INSERT INTO MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME) VALUES ('USER2', '1234', '김철수');

SELECT * FROM MEMBER;

-- ▼ 위에서 테이블에 추가한 데이터를 실제 데이터베이스에 반영
--    (원래는 메모리 버퍼에 임시 저장된 데이터를 보여줌, 확정되기 전)
COMMIT;

-- ▼ COMMIT을 자동으로 시켜주는 명령어
SHOW AUTOCOMMIT;
--SET AUTOCOMMIT ON; 켜고
--SET AUTOCOMMIT OFF; 끈다

/*--------------------------------
    <제약 조건 (CONSTRAINTS)>
       사용자가 원하는 조건의 데이터만 유지하기 위해서,
       테이블 작성 시 각 컬럼에 대해 제약조건을 설정할 수 있음
       ▶ 데이터 무결성 보장이 목적
           * 데이터 무결성 : 데이터의 정확성과 일관성을 유지시키는 것
           
       * 종류 : NOT NULL, UNIQUE, CHECK, PRIMARY KEY, FOREIGN KEY
       
       [표현법]
           1) 컬럼 레벨
                CREATE TABLE 테이블명 (
                    컬럼명 자료형(크기) [CONSTRAINT 제약조건명] 제약조건,
                    ...
                );
           2) 테이블 레벨
                CREATE TABLE 테이블명 (
                     컬럼명 자료형(크기),
                     ...
                     [CONSTRAINT 제약조건명] 제약조건 (컬럼명)
                );     
*/--------------------------------

-- 1. 제약 조건 확인
-- ▽ 사용자가 작성한 제약 조건을 확인하는 뷰
SELECT * FROM USER_CONSTRAINTS;
-- * CONSTRAINT_NAME 중 SYS 어쩌구는 내가 설정안하면 오라클에서 자동으로 만들어주는 이름

-- ▽ 제약조건이 걸려있는 컬럼을 확인하는 뷰
SELECT * FROM USER_CONS_COLUMNS;

/*--------------------------------
    <NOT NULL 제약 조건>
       해당 컬럼이 반드시 값이 있어야만 하는 경우 사용
       삽입 / 수정 시 NULL 값을 허용하지 않도록 제한함
*/--------------------------------

-- 1. 기존 MEMBER 테이블은 값에 NULL이 있어도 삽입 가능
INSERT INTO MEMBER VALUES(NULL, NULL, NULL, NULL);
SELECT * FROM MEMBER;

-- 2. NOT NULL 제약조건을 설정한 테이블 만들기
--    : NOT NULL 제약조건은 컬럼 레벨에서만 설정 가능
DROP TABLE MEMBER; -- 먼저 만들었던거 삭제하고 다시만들게요 ~

CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20)NOT NULL,
    MEMBER_DATE DATE DEFAULT SYSDATE
);

-- ▽ NOT NULL 제약 조건에 위배되어 당연히 에러 남 
INSERT INTO MEMBER VALUES(NULL, NULL, NULL, NULL);

-- ▽ NOT NULL 제약 조건이 걸려있는 컬럼에는 반드시 값이 있어야 함
INSERT INTO MEMBER VALUES('USER1', '1234', '홍길동', NULL);
INSERT INTO MEMBER VALUES('USER2', '1234', '길동이', DEFAULT);

DESC MEMBER;
SELECT * FROM MEMBER;

/*--------------------------------
    <UNIQUE 제약 조건>
       컬럼의 입력 값에 중복 값을 제한하는 제약 조건
       데이터를 삽입 / 수정 시, 기존에 있는 데이터 값 중에 중복이 있을 경우, 
       삽입 / 수정되지 않음
       제약조건 지정 박식으로 컬럼 레벨, 테이블 레벨 방식 모두 사용 가능
*/--------------------------------

-- 아이디가 중복됐음에도 성공적으로 데이터가 삽입됨
INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);
INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);

SELECT * FROM MEMBER;

DROP TABLE MEMBER;

-- 새로 테이블 만들어 볼게요
-- 그리고 UNIQUE를 컬럼 단계에서 만들어볼게요
CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(20) NOT NULL UNIQUE,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20)NOT NULL,
    MEMBER_DATE DATE DEFAULT SYSDATE
);

INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);
INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);

-- 제약 조건 확인하는 뷰
--   : 에러가 나도 어느 컬럼에서 에러가 나는지 안알려주기 때문
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

-- ▶ 그러면 유지보수의 편리성을 위해 제약 조건에 이름을 붙여볼게요

-- 다시 만들어볼게요
DROP TABLE MEMBER;

-- 이번엔 UNIQUE를 테이블 단계에서 만들어볼게요
CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20)CONSTRAINT MEMBER_NAME_NN NOT NULL,
    MEMBER_DATE DATE DEFAULT SYSDATE, -- △ 이름 설정해 봄ㅎㅎ
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);            -- △ 통상적으로 테이블명_컬럼명_UQ 정도로 설정함 

-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);
INSERT INTO MEMBER VALUES('USER1', '1234', '아무개', DEFAULT);

-- 다시 만들어볼게요
DROP TABLE MEMBER;

-- 이번엔 여러개의 컬럼을 묶어서 하나의 UNIQUE 제약 조건을 설정해볼게요
-- 단, 반드시 테이블 레벨으로만 설정 가능
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER  NOT NULL,
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20)CONSTRAINT MEMBER_NAME_NN NOT NULL,
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_NO, MEMBER_ID)
);            

-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

-- 여러 칼럼을 묶어서 UNIQUE 제약 조건이 설정되어있으면
-- 제약 조건이 설저되어 있는 컬럼 값이 모~두 중복되는 경우에만 오류가 발생함
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '아무개', DEFAULT);
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '아무개', DEFAULT);
                        -- ▲ MEMBER_NO, MEMBER_ID 쌍으로 보는거라 이건 안됨
INSERT INTO MEMBER VALUES(2, 'USER1', '1234', '아무개', DEFAULT);
                        -- ▲ MEMBER_NO, MEMBER_ID 쌍으로 보는거라 이건 됨
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '아무개', DEFAULT);
                        -- ▲ MEMBER_NO, MEMBER_ID 쌍으로 보는거라 이건 됨
                        
SELECT * FROM MEMBER;
                      
/*--------------------------------
    <CHECK 제약 조건>
       컬럼에 기륵되는 값에 조건을 설정하고, 조건을 만족하는 값만 기록할 수 있음
       비교 값은 리터럴만 사용 가능 (변하는 값, 함수 사용 불가)
       
       [표현법]
          CHECK(비교연산자)
             CHECK(컬럼 [NOT] IN (값, 값, ...))
             CHECK(컬럼 = 값)
             CHECK(컬럼 BEWTWEEN 값 AND 값)
             CHECK(컬럼 LIKE '_문자' OR 컬럼 LIKE '문자%')
             ...    
*/--------------------------------  
       
-- 없애고 다시 만들어볼게요
DROP TABLE MEMBER;

-- CHECK 제약 조건을 넣을거에요
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER NOT NULL,
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_NO, MEMBER_ID)
);                

-- 작성한 제약조건 확인
DESC MEMBER;

-- 데이터 입력
-- : 성별이나 나이에 유효한 값이 아닌 값들도 INSERT 됨....
--    ▶ CHECK 걸어서 유효한 값이 아니면 안받을래
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '아무개', '남', 25, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '춘향이', '여', 101, DEFAULT);
INSERT INTO MEMBER VALUES(3, 'USER3', '1234', '무닌수', '강', 30, DEFAULT);
--                                                      ▲ 에러 : GENDER 컬럼 CHECK 제약조건 
INSERT INTO MEMBER VALUES(4, 'USER4', '1234', '홍길동', '남', -30, DEFAULT);
--                                                          ▲ 에러 : AGE 컬럼 CHECK 제약조건
                      
-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

SELECT * FROM MEMBER;

/*--------------------------------
    <PRIMARY KEY(기본 키) 제약 조건>
       - 테이블에서 한 행의 정보를 식별하기 위해 사용할 컬럼에 부여하는 제약 조건
       - 각 행들을 구분할 수 있는 식별자 역할 
         ( ex. 회원 번호, 부서 코드, 직급 코드, ...)
       - PRIMARY KEY 제약 조건을 설정하게 되면 
         자동으로 해당 컬럼에 NOT NULL + UNIQUE 제약 조건 설정 됨
       - 한 테이블에 한 개만 설정 가능 
         단, 한 개 이상의 컬럼을 묶어서 PRIMARY KEY로 제약 조건 설정 가능
       - 컬럼 레벨, 테이블 레벨 방식 모두 설정 가능
*/-------------------------------- 

-- 없애고 다시 만들어볼게요
DROP TABLE MEMBER;

-- PRIMARY KEY 제약 조건을 넣을거에요
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY, -- 컬럼 레벨
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    MEMBER_DATE DATE DEFAULT SYSDATE,
--    CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY(MEMBER_NO),  -- 테이블 레벨
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);                

-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

-- 데이터 입력
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '아무개', '남', 25, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '춘향이', '여', 20, DEFAULT);
INSERT INTO MEMBER VALUES(3, 'USER3', '1234', '무닌수', '남', 30, DEFAULT);
INSERT INTO MEMBER VALUES(3, 'USER4', '1234', '유관순', '여', 15, DEFAULT);
--                        ▲ PK 동일해서 에러남 
INSERT INTO MEMBER VALUES(NULL, 'USER5', '1234', '홍길동', '남', 25, DEFAULT);
--                        ▲ PK에 NULL 이 들어가려고 해서 에러남 

SELECT * FROM MEMBER;

-------------------------------
-- 없애고 다시 만들어볼게요
DROP TABLE MEMBER;

-- PRIMARY KEY 제약 조건을 넣을거에요 (테이블 레벨)
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER,
    MEMBER_ID VARCHAR2(20),
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY(MEMBER_NO, MEMBER_ID)  
    -- △  테이블 레벨, 컬럼을 묶어서 하나의 기본 키를 설정 = 복합키
);                

-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

-- 데이터 입력
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '아무개', '남', 25, DEFAULT);
INSERT INTO MEMBER VALUES(1, 'USER2', '1234', '춘향이', '여', 20, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '무닌수', '남', 30, DEFAULT);
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '유관순', '여', 15, DEFAULT);
--                        ▲ 아무개랑 MEMBER_NO, MEMBER_ID가 같아서 에러남 
INSERT INTO MEMBER VALUES(NULL, 'USER5', '1234', '홍길동', '남', 25, DEFAULT);
--                        ▲ PK에 NULL 이 들어가려고 해서 에러남 

SELECT * FROM MEMBER;

/*--------------------------------
    <FOREIGN KEY(외래 키) 제약 조건>
       - 다른 테이블에 존재하는 값 만을 가져야 하는 컬럼에 부여하는 제약 조건
         단, NULL값도 가질 수 있음
       - 즉, 참조된 다른 테이블이 제공하는 값만 기록할 수 잇음
         ▷ FOREIGN KEY 제약 조건에 의해서 테이블간의 관계가 형성 됨
       
       [표현법]
          1) 컬럼 레벨
                 컬럼명 자료형(크기) [CONSTRAINT 제약조건명] REFERENCES 참조할 테이블명 [(기본 키)] [삭제 룰]
          
          2) 테이블 레벨
                 [CONSTRAINT 제약조건명] FOREIGN KEY(컬럼명) REFERENCES 참조할 테이블명 [(기본 키)] [삭제 룰]

       [삭제룰]
          부모테이블의 데이터가 삭제 됐을 때 옵션을 지정 가능
             1) ON DELETE RESTRICT 
                : 자식 테이블의 참조 키가 부모 테이블의 키 값을 참조하는 경우
                  주어진 상위 행을 삭제할 수 없음 ▶ 기본적으로 적용되는 옵션
             2) ON DELETE SET NULL 
                : 부모 테이블의 데이터를 삭제 시
                  참조하고 있는 자식 테이블의 컬럼 값이 NULL로 변경 됨
             3) ON DELETE CASCADE 
                : 부모 테이블의 데이터를 삭제 시
                  참조하고 있는 자식 테이블의 컬럼 값이 존재하는 행 전체가 삭제 됨

*/-------------------------------- 

-- 1. 부모테이블 만들기
--    : 회원 등급에 대한 데이터를 보관하는 테이블
CREATE TABLE MEMBER_GRADE (
    GRADE_CODE NUMBER PRIMARY KEY,
    GRADE_NAME VARCHAR2(30) NOT NULL
);

-- 부모테이블에 데이터 넣기
INSERT INTO MEMBER_GRADE VALUES(10, '일반회원');
INSERT INTO MEMBER_GRADE VALUES(20, '우수회원');
INSERT INTO MEMBER_GRADE VALUES(30, '특별회원');

SELECT * FROM MEMBER_GRADE;

-- 2. 지우고 다시만들기
DROP TABLE MEMBER;

-- 자식 테이블을 만들거에요 ~
CREATE TABLE MEMBER (
    MEMBER_NO NUMBER CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY, 
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    GRADE_ID NUMBER REFERENCES MEMBER_GRADE(GRADE_CODE), -- 컬럼 레벨 FOREIGN KEY
    MEMBER_DATE DATE DEFAULT SYSDATE,
--    CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY(MEMBER_NO),  -- 테이블 레벨 PRIMARY KEY
--    FOREIGN KEY(GRADE_ID) REFERENCES MEMBER_GRADE /*(GRADE_CODE)*/,
--                ▲ 테이블 레벨 FOREIGN KEY, 자동으로 부모 테이블의 PRIMARY KEY 로 연결됨
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);                

-- 제약 조건 확인하는 뷰
SELECT CONSTRAINT_NAME, 
       CONSTRAINT_TYPE, 
       UC.TABLE_NAME, 
       COLUMN_NAME
FROM USER_CONSTRAINTS UC
JOIN USER_CONS_COLUMNS UCC USING(CONSTRAINT_NAME)
WHERE UC.TABLE_NAME = 'MEMBER';

-- 데이터 넣어서 확인해보기
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '춘향이', '여', 20, 10, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '무닌수', '남', 30, 50, DEFAULT);
--                                                              ▲ 부모테이블의 키 값이 없어서 에러남
INSERT INTO MEMBER VALUES(3, 'USER3', '1234', '홍길동', '남', 25, NULL, DEFAULT);
--                                     GRADE_ID 컬럼에 NULL 사용 가능 △                                                      

-- 3. MEMBER 테이블과 MEMBER_GRADE 테이블을 조인하여
--    MEMBER_ID, MEMBER_NAME, GRADE_NAME 조회
-- 3-1-1)ANSI 구문 
SELECT MEMBER_ID,
       MEMBER_NAME, 
       GRADE_NAME
FROM MEMBER M
LEFT OUTER JOIN MEMBER_GRADE G ON (M.GRADE_ID = G.GRADE_CODE);
--                    자식테이블 외래키 = 부모테이블 기본키 △ 

-- 3-1-2) 오라클 구문

-- 3-2) MEMBER_GRADE 테이블에서 GRADE_CODE가 10인 데이터 삭제하기
---       : 에러 ▷ 자식테이블의 행 들 중에 10을 사용하는 행이 있기 때문
DELETE FROM MEMBER_GRADE WHERE GRADE_CODE = 10;

-- 3-3) MEMBER_GRADE 테이블에서 GRADE_CODE가 20인 데이터 삭제하기  
---       : 삭제 됨 ▷ 자식테이블의 행 들 중에 30을 사용하는 행이 없기 때문
DELETE FROM MEMBER_GRADE WHERE GRADE_CODE = 30;

-- 4. 삭제 룰(ON DELETE SET NULL) 적용 후 삭제해 보기
-- 4-1) ROLLBACK(실행 취소) 후 MEMBER_GRADE 테이블 조회
--        * ROLLBACK : 메모리 버퍼에 임시 삭제한 데이터를 
--                     실제 테이블에 반영하지 않고 작업을 취소함
ROLLBACK;
SELECT * FROM MEMBER_GRADE;

-- 4-2) 먼저 만들었던 MEMBER 테이블 삭제 후, 자식 테이블 다시 만들기
DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    MEMBER_NO NUMBER CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY, 
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    GRADE_ID NUMBER REFERENCES MEMBER_GRADE(GRADE_CODE) ON DELETE SET NULL, 
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);   

-- 4-3) 데이터 입력
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '춘향이', '여', 20, 10, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '홍길동', '남', 25, NULL, DEFAULT);

-- 4-4) 데이터가 잘 들어갔는지 테이블 조회
SELECT * FROM MEMBER_GRADE;
SELECT * FROM MEMBER;

-- 4-5) 데이터 삭제 후 테이블에서 결과 조회
DELETE FROM MEMBER_GRADE WHERE GRADE_CODE = 10;
-- △ 삭제 완료 : 춘향이의 GRADE_ID가 NULL으로 변경 됨
SELECT * FROM MEMBER_GRADE; -- 10번 삭제됨
SELECT * FROM MEMBER;



-- 5. 삭제 룰(ON DELETE CASCADE) 적용 후 삭제해 보기
-- 5-1) ROLLBACK(실행 취소) 후 테이블 조회
ROLLBACK;
SELECT * FROM MEMBER_GRADE; -- 10번 복구
SELECT * FROM MEMBER;
-- △ INSERT 후 커밋을 안해서, 4-3부터 롤백되어 공란으로 나올 것임

-- 5-2) 먼저 만들었던 MEMBER 테이블 삭제 후, 자식 테이블 다시 만들기
DROP TABLE MEMBER;

CREATE TABLE MEMBER (
    MEMBER_NO NUMBER CONSTRAINT MEMBER_MEMBER_NO_PK PRIMARY KEY, 
    MEMBER_ID VARCHAR2(20) NOT NULL,
    MEMBER_PWD VARCHAR2(20)NOT NULL,
    MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NAME_NN NOT NULL,
    GENDER CHAR(3) CHECK(GENDER IN ('남', '여')),
    AGE NUMBER CHECK(AGE > 0),
    GRADE_ID NUMBER REFERENCES MEMBER_GRADE(GRADE_CODE) ON DELETE CASCADE, 
    MEMBER_DATE DATE DEFAULT SYSDATE,
    CONSTRAINT MEMBER_MEMBER_ID_UQ UNIQUE(MEMBER_ID)
);   

-- 5-3) 데이터 입력
INSERT INTO MEMBER VALUES(1, 'USER1', '1234', '춘향이', '여', 20, 10, DEFAULT);
INSERT INTO MEMBER VALUES(2, 'USER2', '1234', '홍길동', '남', 25, NULL, DEFAULT);

-- 5-4) 데이터가 잘 들어갔는지 테이블 조회
SELECT * FROM MEMBER_GRADE;
SELECT * FROM MEMBER;

-- 5-5) 커밋 : 테이블들의 데이터 확정
COMMIT;

-- 5-6) 데이터 삭제 후 테이블에서 결과 조회
DELETE FROM MEMBER_GRADE WHERE GRADE_CODE = 10;
-- △ 삭제 완료 : 춘향이 행들이 모두 삭제 됨
SELECT * FROM MEMBER_GRADE; -- 10번 삭제됨
SELECT * FROM MEMBER;

-- 5-7) 롤백 후 테이블 조회
ROLLBACK;
SELECT * FROM MEMBER_GRADE; -- 10번 복구
SELECT * FROM MEMBER;
-- △ INSERT 후 커밋을 해서, 5-5부터 롤백되어 
--    데이터 입력된 것이 정상적으로 나올 것임


/*--------------------------------
    <SUBQUERY를 이용한 테이블 생성>
       - SUBQUERY를 사용해 테이블 생성
       - 컬럼명, 데이터 타입, 값이 복사되고 제약 조건은 NOT NULL만 복사 됨
       
       [표현법] 
          CREATE TABLE 테이블명 
          AS 서브쿼리;
*/-------------------------------- 

-- 1. EMPLOYEE 테이블을 복사한 새로운 테이블 생성
--    : 컬럼, 데이터 타입, 값, 제약 조건 NOT NULL 까지 모두 복사
-- 1-1) 카피 테이블 생성
CREATE TABLE EMPLOYEE_COPY
AS SELECT * 
   FROM EMPLOYEE;

-- 1-2) 생성 된 카피 테이블 조회
--      : 원래 제약 조건인 PRIMARY KEY가 사라진 채로 복사 됨
SELECT * FROM EMPLOYEE_COPY;


-- 2. MEMBER 테이블을 복사한 새로운 테이블 생성
--    : 컬럼, 데이터 타입, 값, 제약 조건 NOT NULL 까지 모두 복사
-- 2-1) 카피 테이블 생성
CREATE TABLE MEMBER_COPY
AS SELECT *
   FROM MEMBER;
   
-- 2-2) 생성 된 카피 테이블 조회
--      : 원래 제약 조건인 PRIMARY KEY, FOREIGN KEY, UNIQUE가 사라진 채로 복사 됨
SELECT * FROM MEMBER_COPY;
   

-- 3. EMPLOYEE 테이블을 복사한 새로운 테이블 생성
--    : 컬럼, 데이터 타입, 제약 조건을 복사 (= 구조만 복사해오겠다는 뜻)
-- 3-1) 카피 테이블 생성 
CREATE TABLE EMPLOYEE_COPY2
AS SELECT * 
   FROM EMPLOYEE
   WHERE 2 = 1;
--  △ 모든 행에 대해 매번 결과가 FALSE 이므로, 
--     데이터 값은 복사되지 않고 구조만 복사하게 됨

-- 3-2) 생성 된 카피 테이블 조회
--      : 원래 제약 조건인 PRIMARY KEY가 사라지고,
--        데이터 없이 구조만 가져와서 복사함
SELECT * FROM EMPLOYEE_COPY2;


-- 4. MEMBER 테이블을 복사한 새로운 테이블 생성
--    : 컬럼, 데이터 타입, 제약 조건을 복사 (= 구조만 복사해오겠다는 뜻)
-- 4-1) 카피 테이블 생성 
CREATE TABLE MEMBER_COPY2
AS SELECT * 
   FROM MEMBER
   WHERE 23123 = 1;
--  △ 모든 행에 대해 매번 결과가 FALSE 이므로, 
--     데이터 값은 복사되지 않고 구조만 복사하게 됨

-- 4-2) 생성 된 카피 테이블 조회
--      : 원래 제약 조건인 PRIMARY KEY가 사라지고,
--        데이터 없이 구조만 가져와서 복사함
SELECT * FROM MEMBER_COPY2;


-- 5. EMPLOYEE 테이블의 사번, 사원명, 급여, 연봉을 저장하는 테이블을 
--    서브쿼리를 사용해서 생성
-- 5-1) 카피 테이블 생성 
CREATE TABLE EMPLOYEE_COPY3
AS SELECT EMP_ID , EMP_NAME, SALARY, SALARY * 12 AS "연봉"
-- SELECT 절에 산술 연산 또는 함수식이 기술 된 경우별칭 지정 필요 △ 
   FROM EMPLOYEE;
   
-- 4-2) 생성 된 카피 테이블 조회
SELECT * FROM EMPLOYEE_COPY3;

-- 지금까지 만든거 다 삭제
DROP TABLE EMPLOYEE_COPY;
DROP TABLE EMPLOYEE_COPY2;
DROP TABLE EMPLOYEE_COPY3;
DROP TABLE MEMBER_COPY;
DROP TABLE MEMBER_COPY2;


