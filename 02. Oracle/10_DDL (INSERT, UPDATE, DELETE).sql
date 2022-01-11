/*--------------------------------
    <DML (Data Manipulation Language>
       데이터 조작 언어로, 테이블에 값을 삽입(INSERT), 수정(UPDATE), 삭제(DELETE)하는 구문
       
    <INSERT>
       테이블에 새로운 행을 추가하는 구문
       
       [표현법]
          1) INSERT INTO 테이블명 VALUES(값, 값, 값, ..., 값);
                - 테이블의 모든 컬럼에 값을 INSERT 하고자 할 때 사용
                - 컬럼 순번을 지켜서 VALUES에 값을 나열해야 함
          2) INSERT INTO 테이블명(컬럼명,컬럼명,..., 컬럼명) VALUES(값, 값, 값, ..., 값);
                - 테이블에 내가 선택한 컬럼에 대한 값만 INSERT 하고자 할 떄 사용
                - 선택이 안된 컬럼들은 기본적으로 NULL 값이 들어감
                  NOT NULL 제약조건이 있는 경우 반드시 선택해서 값을 제시해야 함
                  단, 기본값(DEFAULT)이 지정되어 있으면 NULL이 아닌 기본값이 들어감
          3) INSERT INTO 테이블명 (서브쿼리);
                - VALUES를 대신해서 서브쿼리로 조회한 결과값을 통째로 INSERT 함 
                  ▷ 여러 행을 INSERT 할 수 있음
                - 서브쿼리의 결과값이 INSERT문에 지정된 컬럼 개수, 데이터 타입이 같아야 함
*/--------------------------------    

-- 표현법 1) 사용
INSERT INTO EMPLOYEE
--VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, 
--              NULL, NULL, NULL, NULL, NULL, NULL);
-- △ 에러 : EMP_ID 부터 NOT NULL이 걸려있는 컬럼들 때문에..
VALUES ('900', '공유', '901008-1080503', 'kong@kh.or.kr', '01055556666', 
        'D1', 'J7', 43000000, 0.2, '200', SYSDATE, NULL, DEFAULT);
        --     △ J8이면 에러 : FK에 위배됨, 부모테이블에 J8은 없음


-- 표현법 2) 사용
INSERT INTO EMPLOYEE(EMP_ID)
VALUES(901);
-- △ 에러 : EMP_NAME에 NULL 못넣는다는 에러 발생

INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME)
VALUES (901, '무닌수');
-- △ 에러 : EMP_NO에 NULL 못넣는다는 에러 발생

INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO)
VALUES (901, '무닌수', '991008-1111999');
-- △ 에러 : JOB_CODE에 NULL 못넣는다는 에러 발생

INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO, JOB_CODE)
VALUES (901, '무닌수', '991008-1111999', 'J7' );
--                                       △ J8이면 에러 
--                                          : FK에 위배됨, 부모테이블에 J8은 없음
-- ▷ 삽입 완료 : 입력한 값 빼고는 모두 NULL로 채워져서 INSERT


-- 표현법 3) 사용
-- 1. 새로운 테이블 생성
CREATE TABLE EMP_01(
    EMP_ID NUMBER,
    EMP_NAME VARCHAR2(20),
    DEPT_TITLE VARCHAR2(35)
);

-- 2-1. 데이터 입력 - 전체
--      : 전체 사원의 사번, 이름, 부서명 조회하여 
--        그 결과 값을 EMP_01 테이블에 INSERT
INSERT INTO EMP_01 (
    SELECT EMP_ID, EMP_NAME, DEPT_TITLE
    FROM EMPLOYEE E
    LEFT OUTER JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
);
-- 2-2. 데이터 입력 - 컬럼 지정
INSERT INTO EMP_01(EMP_ID, EMP_NAME) (
    SELECT EMP_ID, EMP_NAME
    FROM EMPLOYEE E
    LEFT OUTER JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
);

-- 3. 데이터 입력이 잘 되었는지 확인
SELECT * FROM EMP_01;

-- 4. 다음 실습을 위해 테이블 삭제 (안녕~~)
DROP TABLE EMP_01;

/*--------------------------------
    <INSERT ALL>
       두 개 이상의 테이블에 INSERT 하려고 하는데, 동일한 서브쿼리가 사용되는 경우
       INSERT ALL을 이용해서 여러 테이블에 한 번에 데이터를 삽입할 수 있음
       
       [표현법]
          1) INSERT ALL 
             INTO 테이블명1[(컬럼, 컬럼, ...)] VALUES(값, 값, 값, ..., 값)
             INTO 테이블명2[(컬럼, 컬럼, ...)] VALUES(값, 값, 값, ..., 값)  
                    서브쿼리 ;
          2) INSERT ALL
             WHEN 조건1 THEN
                  INTO 테이블명1[(컬럼, 컬럼, ...)] VALUES(값, 값, 값, ..., 값) 
             WHEN 조건2 THEN 
                  INTO 테이블명2[(컬럼, 컬럼, ...)] VALUES(값, 값, 값, ..., 값)     
                     서브쿼리 ;
*/-------------------------------- 

-- 표현법 1) 사용
-- 1. 테스트 테이블 EMP_DEPT, EMP_MANEGER 만들기 (테이블 구조만 복사)
CREATE TABLE EMP_DEPT
AS SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
   FROM EMPLOYEE
   WHERE 1 = 0;
   
CREATE TABLE EMP_MANAGER
AS SELECT EMP_ID, EMP_NAME, MANAGER_ID
   FROM EMPLOYEE
   WHERE 1 = 0;
   
-- 2. 그 전에 공통적인 내용을 추출해서 서브쿼리 작성
SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRR_DATE, MANAGER_ID
FROM EMPLOYEE
WHERE DEPT_CODE = 'D1';

-- 3. EMP_DEPT 테이블에 EMPLOYEE 테이블의 부서 코드가 D1인 직원의
--    사번, 이름, 부서 코드, 입사일 조회하여 삽입하고, 
--    EMP_MANEGER 테이블에 EMPLOYEE 테이블의 부서 코드가 D1인 직원의
--    사번, 이름, 관리자 사번 조회하여 삽입
INSERT ALL
INTO EMP_DEPT VALUES (EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE)
INTO EMP_MANAGER VALUES (EMP_ID, EMP_NAME, MANAGER_ID)
    SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE, MANAGER_ID
    FROM EMPLOYEE
    WHERE DEPT_CODE = 'D1';

-- 4. 다음 실습을 위해 테이블 삭제 (안녕~~)
DROP TABLE EMP_DEPT;
DROP TABLE EMP_MANAGER;


-- 표현법 2) 사용
-- 1. 테스트 테이블 EMP_OLD, EMP_NEW 만들기 (테이블 구조만 복사)
CREATE TABLE EMP_OLD
AS SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
   FROM EMPLOYEE
   WHERE 1 = 0;
   
CREATE TABLE EMP_NEW
AS SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
   FROM EMPLOYEE
   WHERE 1 = 0;

-- 2. 그 전에 공통적인 내용을 추출해서 서브쿼리 작성
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE;

-- 3. EMPLOYEE 테이블의 입사일 기준으로
--    2000년 1월 1일 이전에 입사한 사원의 정보는 EMP_OLE 테이블에 삽입하고,
--    2000년 1월 1일 이후에 입사한 사원의 정보는 EMP_NEW 테이블에 삽입함
INSERT ALL
WHEN HIRE_DATE < '2000/01/01' THEN
    INTO EMP_OLD VALUES(EMP_ID, EMP_NAME, HIRE_DATE, SALARY)
WHEN HIRE_DATE > '2000/01/01' THEN
    INTO EMP_NEW VALUES(EMP_ID, EMP_NAME, HIRE_DATE, SALARY)
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE;
    
-- 4. 삽입된 데이터 확인
SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;

-- 5. 다음 실습을 위해 테이블 삭제 (안녕~~)
DROP TABLE EMP_OLD;
DROP TABLE EMP_NEW;


/*--------------------------------
    <UPDATE>
       테이블에 기록된 데이터를 수정하는 구문
       
       [표현법]
          1) UPDATE 테이블명
             SET 컬럼명 = 변경하려는 값,
                 컬럼명 = 변경하려는 값,
                 ...
             [WHERE 조건];
             
          2) UPDATE 테이블명
             SET 컬럼명 = (서브쿼리),
                 컬럼명 = (서브쿼리).
                 ...
             [WHERE 조건];
             
                - SET 절에서 여러 개의 컬럼을 콤마(,)로 나열해서 값을 동시에 변경할 수 있음
                - WHERE 절을 생략하면 모든 행의 데이터가 변경 됨
                - UPDATE 시에 서브쿼리를 이용해서 서브쿼리를 수행한 결과값으로 컬럼 값 변경 가능
                  (WHERE 절에도 서브쿼리 사용 가능)
*/-------------------------------- 

-- 1. 테스트 테이블 DEPT_COPY, EMP_SALARY 만들기 & 확인
CREATE TABLE DEPT_COPY
AS SELECT * FROM DEPARTMENT;

CREATE TABLE EMP_SALARY
AS SELECT EMP_ID, EMP_NAME, SALARY, BONUS  
   FROM EMPLOYEE;

SELECT * FROM DEPT_COPY;
SELECT * FROM EMP_SALARY;

-- 2-1). DEPT_ID가 'D9'인 부서명을 '전략기획팀' 으로 수정
UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀';

-- 2-2). 확인해 보니 DEPT_TITLE의 모든 데이터가 바뀜 ▷ (옥상) ▷ 롤백
SELECT * FROM DEPT_COPY;
ROLLBACK;

-- 2-3). WHERE절을 사용해 DEPT_ID가 'D9'인 부서명을 '전략기획팀' 으로 수정
UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀'
WHERE DEPT_ID = 'D9';

-- 2-4). 확인해 보니 제대로 바뀌었습니다 !
SELECT * FROM DEPT_COPY;

-- 3. 이번엔 노옹철 사원의 급여를 100만원 으로 변경 & 확인
UPDATE EMP_SALARY
SET SALARY = '1000000'
WHERE EMP_NAME = '노옹철';

SELECT * FROM EMP_SALARY;

-- 4. 이번엔 선동일 대표의 급여를 700만원, 보너스를 0.2 로 변경 & 확인
UPDATE EMP_SALARY
SET SALARY = '7000000',
    BONUS = '0.2'
WHERE EMP_NAME = '선동일';

SELECT * FROM EMP_SALARY;

-- 5. 모든 사원의 급여를 기존 급여에서 10프로 인상한 금액으로 변경 & 확인
UPDATE EMP_SALARY
SET SALARY = (SALARY * 1.1);

SELECT * FROM EMP_SALARY;

-- * UPDATE 주의사항 : 변경할 값은 해당 컬럼에 대한 제약 조건에 위배되면 안됨
-- 6. 사번이 200번인 사원의 사원번호를 NULL로 변경 & 확인
UPDATE EMP_SALARY
SET EMP_ID = NULL
-- △ 에러 : NOT NULL 제약 조건에 위배됨
WHERE EMP_NO = '200';

SELECT * FROM EMP_SALARY;

-- 7. EMPLOYEE 테이블에서 노옹철 사원의 부서 코드를 'D0'으로 변경 & 확인 & 롤백
UPDATE EMPLOYEE
SET DEPT_CODE = 'D0'
WHERE EMP_NAME = '노옹철';
-- △ 에러 : FOREIGN KEY 제약 조건에 위배됨 (parent key not found)

SELECT * FROM EMP_SALARY;

ROLLBACK;

-- 8. 방명수 사원의 급여와 보너스율을 유재식 사원과 동일하게 변경
--  8-1) 유재식 사원의 급여와 보너스 조회
SELECT SALARY, BONUS
FROM EMP_SALARY
WHERE EMP_NAME = '유재식';

-- 8-2) 단일행 서브쿼리를 각각의 컬럼에 적용
UPDATE EMP_SALARY
SET SALARY = (
        SELECT SALARY
        FROM EMP_SALARY
        WHERE EMP_NAME = '유재식'
        ),
    BONUS = (
        SELECT  BONUS
        FROM EMP_SALARY
        WHERE EMP_NAME = '유재식'
        )
WHERE EMP_NAME = '방명수';

-- 8-3) 다중열 서브쿼리를 사용해서 SALARY, BONUS 컬럼을 한 번에 변경 & 확인
UPDATE EMP_SALARY
SET (SALARY, BONUS) = (
        SELECT SALARY, BONUS
        FROM EMP_SALARY
        WHERE EMP_NAME = '유재식'
        )
WHERE EMP_NAME = '방명수';

SELECT * FROM EMP_SALARY;

-- 9. 노옹철, 전형돈, 정중하, 하동운 사원들의 급여와 보너스를 유재식 사원과 동일하게 변경 & 확인
UPDATE EMP_SALARY
SET (SALARY, BONUS) = (
        SELECT SALARY, BONUS
        FROM EMP_SALARY
        WHERE EMP_NAME = '유재식'
        )
WHERE EMP_NAME IN ('노옹철', '전형돈', '정중하', '하동운');

SELECT * FROM EMP_SALARY;

-- 10. EMP_SALARY 테이블에서 아시아 지역에 근무하는 직원의 보너스를 0.3으로 변경
-- 10-1) 아시아 지역에 근무하는 사원을 조회하는 서브쿼리 작성
SELECT EMP_ID
FROM EMPLOYEE E
JOIN DEPARTMENT D ON(E.DEPT_CODE = D.DEPT_ID)
JOIN LOCATION L ON(D.LOCATION_ID = L.LOCAL_CODE)
WHERE L.LOCAL_NAME LIKE 'ASIA%';

-- 10-2) 메인쿼리 작성 & 확인
UPDATE EMP_SALARY
SET BONUS = 0.3
WHERE EMP_ID IN (
    SELECT EMP_ID
    FROM EMPLOYEE E
    JOIN DEPARTMENT D ON(E.DEPT_CODE = D.DEPT_ID)
    JOIN LOCATION L ON(D.LOCATION_ID = L.LOCAL_CODE)
    WHERE L.LOCAL_NAME LIKE 'ASIA%'
);

SELECT * FROM EMP_SALARY;

/*--------------------------------
    <DELETE>
       테이블에 기록 된 데이터를 삭제하는 구문 (행 단위로 삭제)
       
       [표현식]
            DELETE FROM 테이블명
            [WHERE 조건식];
            
        - WHERE 절을 제시하지 않으면 전체 행이 삭제됨
*/-------------------------------- 
COMMIT;

-- 1. 공유 사원의 데이터 지우기
DELETE FROM EMP_SALARY
WHERE EMP_NAME = '공유';

-- 2. 무닌수 사원의 데이터 지우기
DELETE FROM EMP_SALARY
WHERE EMP_NAME = '무닌수';

SELECT * FROM EMP_SALARY;

COMMIT;

-- 3. DEPT_ID가 D1인 부서 삭제
--     :D1의 값을 참조하는 자식 테이블의 데이터가 있으므로 삭제 불가
DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D1';


-- 4. DEPT_ID가 D3인 부서 삭제
--     :D3의 값을 참조하는 자식 테이블의 데이터가 없어서 삭제 가능
DELETE FROM DEPARTMENT
WHERE DEPT_ID = 'D3';


-- 5. 롤백 & 확인
ROLLBACK;
SELECT * FROM EMP_SALARY;

/*--------------------------------
    <TURNCATE>
       테이블의 전체 행을 삭제할 때 사용
       DELETE보다 수행 속도가 더 빠름
       단, 별도의 조건 제시가 불가능하고 ROLLBACK 불가능
       
       [표현식]
            TRUNCATE TABLE 테이블명;
*/-------------------------------- 

-- 테스트용 테이블들 조회
SELECT * FROM EMP_SALARY;
SELECT * FROM DEPT_COPY;

DELETE FROM EMP_SALARY;
SELECT * FROM DEPT_COPY;

ROLLBACK;
-- △ 롤백 됨

TRUNCATE TABLE EMP_SALARY;
TRUNCATE TABLE DEPT_COPY;
-- △ TABLE이 '잘렸다' 라는 문구가 나옴

ROLLBACK;
-- △ 롤백 안됨


