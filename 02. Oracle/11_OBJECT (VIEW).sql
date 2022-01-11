/*--------------------------------
    <VIEW>
       SELECT문을 저장할 수 있는 객체 (논리적인 가상 테이블)
       데이터를 저장하고 있지 않으며 테이블에 대한 SQL만 저장되어 있어
       VIEW에 접근할 떄 SQL을 수행하면서 결과값을 가져옴
       
       [표현식]
            CREATE [OR REPLACE] VIEW 뷰명
            AS 서브쿼리;
*/-------------------------------- 

-- 1. '한국'에서 근무하는 사원의 사번, 이름, 부서명, 급여, 근무 국가명 조회
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY, N.NATIONAL_NAME
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
JOIN NATIONAL N ON (L.NATIONAL_CODE = N.NATIONAL_CODE)
WHERE N.NATIONAL_NAME = '한국';

-- 2. '러시아'에서 근무하는 사원의 사번, 이름, 부서명, 급여, 근무 국가명 조회
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY, N.NATIONAL_NAME
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
JOIN NATIONAL N ON (L.NATIONAL_CODE = N.NATIONAL_CODE)
WHERE N.NATIONAL_NAME = '러시아';

-- 3. 뷰 만들기 (1, 2는 같은거니까 귀찮앙)
-- * 여기서 잠깐, 01_사용자계정생성 에서 권한을 받아왔음
CREATE OR REPLACE VIEW V_EMPLOYEE
AS SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY, N.NATIONAL_NAME
    FROM EMPLOYEE E
    JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
    JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
    JOIN NATIONAL N ON (L.NATIONAL_CODE = N.NATIONAL_CODE);

-- 4.한국에서 근무하는 사원의 사번, 이름, 부서명, 급여, 근무 국가명을 VIEW를 통해 확인
--   : 가상 테이블으로, 실제 데이터가 담겨있는 것은 아님
SELECT * 
FROM V_EMPLOYEE
WHERE NATIONAL_NAME = '한국';

-- 5.러시아에서 근무하는 사원의 사번, 이름, 부서명, 급여, 근무 국가명을 VIEW를 통해 확인
--   : 가상 테이블으로, 실제 데이터가 담겨있는 것은 아님
SELECT * 
FROM V_EMPLOYEE
WHERE NATIONAL_NAME = '러시아';

-- 6.총무부에서 근무하는 사원의 사원명, 급여를 VIEW를 통해 확인
--   : 가상 테이블으로, 실제 데이터가 담겨있는 것은 아님
SELECT EMP_NAME, SALARY
FROM V_EMPLOYEE
WHERE DEPT_TITLE = '총무부';

-- 7. 데이터 딕셔너리 보기
SELECT * FROM USER_TABLES;
SELECT * FROM USER_VIEWS;
-- △ 해당 계정이 가지고 있는 VIEW들에 대한 내용을 조회하는 데이터 딕셔너리

/*--------------------------------
    <VIEW 컬럼에 별칭 부여>
       서브쿼리의 SELECT절에 함수나 산술연산이 기술되어 있는 경우 반드시 별칭을 지정해야 함
*/-------------------------------- 

-- 1. 사원의 사번, 사원명, 성별, 근무년수를 조회할 수 있는 뷰 생성
-- 1-1) 서브쿼리 먼저 생성
SELECT EMP_ID,
       EMP_NAME,
       DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여'),
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE)
FROM EMPLOYEE;

-- 1-2) 서브쿼리를 넣은 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_EMP01
AS SELECT EMP_ID,
       EMP_NAME,
       DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여') 성별,
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 근무년수
   FROM EMPLOYEE;

SELECT * FROM V_EMP01;

-- 2. 모두 별칭이 있는 뷰 생성 & 확인
--    : 모든 컬럼에 별칭을 부여해야 함
CREATE OR REPLACE VIEW V_EMP02(사번, 사원명, 성별, 근무년수)
AS SELECT EMP_ID 사번,
       EMP_NAME 사원명,
       DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남', '2', '여') 성별,
       EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 근무년수
   FROM EMPLOYEE;

SELECT 사번, 사원명, 성별, 근무년수 FROM V_EMP02;

-- 3. 뷰 삭제
DROP VIEW V_EMP01;
DROP VIEW V_EMP02;

/*--------------------------------
    <VIEW를 이용해서 DML(INSERT, UPDATE, DELETE) 사용>
       뷰를 통해 데이터를 변경하게 되면 실제 데이터가 담겨있는 기본 테이블에도 적용됨
*/-------------------------------- 
-- 1. 테스트용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_JOB
AS SELECT * FROM JOB;

SELECT * FROM V_JOB;

-- 2. VIEW에 SELECT
SELECT JOB_CODE, JOB_NAME
FROM V_JOB;

-- 3. VIEW에 INSERT & 확인
INSERT INTO V_JOB VALUES('J8','알바');

SELECT * FROM V_JOB;

-- 4. VIEW에 UPDATE : J8의 직급명을 인턴으로 변경 & 확인
UPDATE V_JOB
SET JOB_NAME = '인턴'
WHERE JOB_CODE = 'J8';

SELECT * FROM V_JOB;

-- 5. VIEW에서 삭제하기 & 확인
DELETE FROM V_JOB
WHERE JOB_CODE = 'J8';

SELECT * FROM V_JOB;
SELECT * FROM JOB;

/*--------------------------------
    <DML 구문으로 VIEW 조작이 불가능한 경우>
       1. 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우 
       2. 뷰에 포함되지 않은 컬럼 중 기본 테이블에서 NOT NULL 제약 조건이 지정된 경우
       3. 산술 표현식으로 정의된 경우 
       4. 그룹 함수나 GROUP BY 절을 포함한 경우
       5. DISTINCT를 포함한 경우 
       6. JOIN을 이용해 여러 테이블을 연결한 경우
*/-------------------------------- 

-- 1. 뷰 정의에 포함되지 않은 컬럼을 조작하는 경우
-- 1-1) 실험용 뷰 생성
CREATE OR REPLACE VIEW V_JOB2
AS SELECT JOB_CODE
   FROM JOB;
   
-- 1-2) INSERT 실험 & 확인
INSERT INTO V_JOB2 VALUES ('J8', '알바');
-- △ 에러 : too many values
INSERT INTO V_JOB2 VALUES ('J8');
-- △ 성공

SELECT * FROM V_JOB2;
SELECT * FROM JOB;
-- △ 뷰에 포함되지 않는 컬럼에는 자동으로 NULL 값이 들어감 

-- 1-3) UPDATE 실험 & 확인
UPDATE V_JOB2
SET JOB_NAME = '알바'
WHERE JOB_CODE = 'J8';
-- △ 에러 : 뷰에는 JOB_CODE컬럼만 가지고 정의되어있기 때문

UPDATE V_JOB2
SET JOB_CODE = 'J0'
WHERE JOB_CODE = 'J8';
-- △ 성공

SELECT * FROM V_JOB2;
SELECT * FROM JOB;

-- 1-4) DELETE 실험 & 확인
DELETE FROM V_JOB2
WHERE JOB_NAME = '사원';
-- △ 에러 : JOB_NAME은 뷰에서 정의되어있지 않으므로 찾을수 없다고 에러남

DELETE FROM V_JOB2
WHERE JOB_CODE = 'J0';
-- △ 성공

SELECT * FROM V_JOB2;
SELECT * FROM JOB;

-- 2. 뷰에 포함되지 않은 컬럼 중 기본 테이블에서 NOT NULL 제약 조건이 지정된 경우
-- 2-1) 실험용 뷰 생성
CREATE OR REPLACE VIEW V_JOB3
AS SELECT JOB_NAME
   FROM JOB;
   


-- 2-2) INSERT 실험 & 확인
INSERT INTO V_JOB3 VALUES('J8','알바');
-- △ 에러 : too many values
INSERT INTO V_JOB3 VALUES('알바');
-- △ 에러 : JOB_CODE가 NULL 값으로 들어가서 에러

-- 2-3) UPDATE 실험 & 확인
UPDATE V_JOB3 
SET JOB_NAME = '인턴'
WHERE JOB_NAME = '사원';

SELECT * FROM V_JOB3;
SELECT * FROM JOB;

-- 2-4) DELETE 실험 & 확인
DELETE FROM V_JOB3
WHERE JOB_NAME = '인턴';

SELECT * FROM V_JOB3;
SELECT * FROM JOB;

ROLLBACK;
-- △ FK 제약 조건으로 인해 삭제되지 않음



-- 3. 산술 표현식으로 정의된 경우 
-- 3-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_EMP_SAL
AS SELECT EMP_ID,
          EMP_NAME,
          SALARY,
          SALARY * 12 AS "연봉"
   FROM EMPLOYEE;
   
SELECT * FROM V_EMP_SAL;

-- 3-2) INSERT 실험 
INSERT INTO V_EMP_SAL VALUES(800, '홍길동', 3000000, 36000000);
-- △ 에러 : 연산식으로 만들어진 가상 컬럼에 실제 값을 대입하려고 해서 에러

-- 3-3) UPDATE 실험 & 확인 & 롤백
UPDATE V_EMP_SAL
SET "연봉" = 80000000
WHERE EMP_ID = 200;
-- △ 에러 : 연산식으로 만들어진 가상 컬럼에 실제 값을 대입하려고 해서 에러

UPDATE V_EMP_SAL
SET SALARY = 5000000
WHERE EMP_ID = 200;
-- △ 성공

SELECT * FROM V_EMP_SAL;
SELECT * FROM EMPLOYEE;

ROLLBACK;

-- 3-4) DELETE 실험 & 확인 & 롤백
DELETE FROM V_EMP_SAL
WHERE "연봉" = 96000000;
-- △ 성공 : 삭제는 가능함

SELECT * FROM V_EMP_SAL;
SELECT * FROM EMPLOYEE;
 
ROLLBACK;



-- 4. 그룹 함수나 GROUP BY 절을 포함한 경우
-- 4-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_DEPT
AS SELECT DEPT_CODE, SUM(SALARY) 합계,
          FLOOR(AVG(NVL(SALARY, 0))) 평균
    FROM EMPLOYEE
    GROUP BY DEPT_CODE;

SELECT * FROM V_DEPT;

-- 4-2) INSERT 실험 
INSERT INTO V_DEPT VALUES('D0', 8000000, 4000000);
-- △ 에러 : 연산식으로 만들어진 가상 컬럼에 실제 값을 대입하려고 해서 에러

-- 4-3) UPDATE 실험
UPDATE V_DEPT
SET "합계" = 12000000
WHERE DEPT_CODE = 'D1';
-- △ 에러 : 연산식으로 만들어진 가상 컬럼에 실제 값을 대입하려고 해서 에러

UPDATE V_DEPT
SET DEPT_CODE = 'D0'
WHERE DEPT_CODE = 'D1';
-- △ 에러 : GROUP BY로 뷰를 생성했기 때문에 에러 
--        (원본 테이블의 D1을 다 바꿔야하는데 지원안해줌)

-- 4-4) DELETE 실험 & 확인
DELETE FROM V_DEPT
WHERE DEPT_CODE = 'D1';
-- △ 에러 : GROUP BY로 뷰를 생성했기 때문에 에러
--        (원본 테이블의 D1을 다 삭제해야하는데 지원안해줌)


        
-- 5. DISTINCT를 포함한 경우
-- 5-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_DT_JOB
AS SELECT DISTINCT JOB_CODE
   FROM EMPLOYEE;

SELECT * FROM V_DT_JOB;
   
-- 5-2) INSERT 실험
INSERT INTO V_DT_JOB VALUES('J8');
-- △ 에러 : V_DT_JOB 은 중복제거를 했고, 오라클은 중복한걸 쫒아가서 바꾸는걸 지원하지 않음

-- 5-3) UPDATE 실험
UPDATE V_DT_JOB
SET JOB_CODE = 'J8'
WHERE JOB_CODE = 'J7';
-- △ 에러 : V_DT_JOB 은 중복제거를 했고, 오라클은 중복한걸 쫒아가서 바꾸는걸 지원하지 않음

-- 5-4) DELETE 실험
DELETE FROM V_DT_JOB
WHERE JOB_CODE = 'J7';
-- △ 에러 : V_DT_JOB 은 중복제거를 했고, 오라클은 중복한걸 쫒아가서 바꾸는걸 지원하지 않음



-- 6. JOIN을 이용해 여러 테이블을 연결한 경우
-- 6-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_EMP
AS SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE
   FROM EMPLOYEE E
   JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID;
   
SELECT * FROM V_EMP;

-- 6-2) INSERT 실험 & 확인
INSERT INTO V_EMP VALUES(800, '홍홍홍', '총무부');
-- △ 에러 : 조인 된 테이블을 하나하나 찾아가서 바꿔주는 연산을 지원해주지 않음

-- 6-3) UPDATE 실험 & 확인
UPDATE V_EMP
SET EMP_NAME = '서동일'
WHERE EMP_ID = 200;
-- △ 성공

UPDATE V_EMP
SET DEPT_TITLE = '총무1팀'
WHERE EMP_ID = 200;
-- △ 에러 : 조인 된 테이블을 하나하나 찾아가서 바꿔주는 연산을 지원해주지 않음

-- 6-4) DELETE 실험 & 확인 & 롤백
DELETE FROM V_EMP
WHERE EMP_ID = 200;
-- △ 성공

DELETE FROM V_EMP
WHERE DEPT_TITLE = '총무부';
-- △ 성공 : 서브쿼리의 FROM 절인 EMPLOYEE 테이블에서만 삭제함
--          (EMPLOYEE 테이블에서 해당되는 총무부 애들을 지우고 DEPARTMENT 테이블에는 영향 없음)

SELECT * FROM V_EMP;
SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;

ROLLBACK;

/*--------------------------------
    <VIEW 옵션>
       [표현법]
          CREATE [OR REPLACE] [FORCE | NOFRCE] VIEW 뷰명
          AS 서브쿼리
          [WITH CHECK OPTION]
          [WITH READ ONLY];
       - OR REPLACE : 기존에 동일한 뷰가 있을 경우 덮어쓰고, 존재하지 않으면 뷰를 새로 생성함
                          * 웬만하면 실무에서는 잘 안씁니다
       - FORCE : 서브쿼리에 기술된 테이블이 존재하지 않아도 뷰가 생성됨
       - NOFORCE : 서브쿼리에 기술된 테이블이 존재해야만 뷰가 생성됨 (기본값)
       - WITH CHECK OPTION : 서브쿼리에 기술된 조건에 부합하지 않는 값으로 수정하는 경우 오류를 발생시킴
       - WITH READ ONLY : 뷰에 대한 조회만 가능 (DML 수행 불가)
                          * 거의 모든 뷰에 포함되는 기능
*/-------------------------------- 
-- 1. OR REPLACE
-- 1-1) 실험용 뷰 생성 & 확인
CREATE VIEW V_EMP01
AS SELECT EMP_ID, EMP_NAME
   FROM EMPLOYEE;
   
SELECT * FROM USER_VIEWS WHERE VIEW_NAME = 'V_EMP01';
   
-- ▽ 에러 : SALARY를 추가했어도, 이름이 동일한 뷰가 있으므로 생성해주지 않음
CREATE VIEW V_EMP01
AS SELECT EMP_ID, EMP_NAME, SALARY
   FROM EMPLOYEE;
   
-- ▽ 성공 : OR REPLACE 옵션 추가 
CREATE OR REPLACE VIEW V_EMP01
AS SELECT EMP_ID, EMP_NAME, SALARY
   FROM EMPLOYEE;

SELECT * FROM USER_VIEWS WHERE VIEW_NAME = 'V_EMP01';



-- 2. FORCE / NOFORCE
-- 2-1) NOFORCE (기본값)
CREATE OR REPLACE NOFORCE VIEW V_EMP02
AS SELECT TCODE, TANME, TCONTENT
   FROM TT;
-- △ 에러 : 당연함. TT라는 이름의 테이블은 없음.

-- 2-2) FORCE
-- 2-2-1) FORCE를 사용한 뷰 생성
CREATE OR REPLACE FORCE VIEW V_EMP02
AS SELECT TCODE, TNAME, TCONTENT
   FROM TT;
-- △ 성공 : 그러나 경고 (컴파일 오류와 함께 뷰가 생성되었습니다) 

-- 2-2-2) 뷰 생성 확인 
SELECT * FROM USER_VIEWS WHERE VIEW_NAME = 'V_EMP02'; 
-- △ 뷰가 만들어 진 것을 확인
SELECT * FROM V_EMP02;
-- △ 에러 : 뷰는 생성 됐지만 조회는 안됨 (당연함. 테이블이 없음)

-- 2-2-3) TT 테이블을 생성 후 뷰 조회 
CREATE TABLE TT(
    TCODE NUMBER,
    TNAME VARCHAR2(10),
    TCONTENT VARCHAR2(20)
);

SELECT * FROM V_EMP02;
-- △ 이제 조회 가능



-- 3. WITH CHECK OPTION
-- 3-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_EMP03
AS SELECT *
   FROM EMPLOYEE
   WHERE SALARY >= 3000000;

SELECT * FROM V_EMP03;

--3-2) 200번 사원의 급여를 200만원으로 변경 & 확인 & 롤백
UPDATE V_EMP03 
SET SALARY = 2000000
WHERE EMP_ID = 200;

SELECT * FROM V_EMP03;
SELECT * FROM EMPLOYEE;
-- △ 기본 테이블도 200만원으로 바뀜

ROLLBACK;

-- 3-3)WITH CHECK OPTION을 추가한 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_EMP03
AS SELECT *
   FROM EMPLOYEE
   WHERE SALARY >= 3000000
WITH CHECK OPTION;

SELECT * FROM V_EMP03;
SELECT * FROM USER_VIEWS WHERE VIEW_NAME = 'V_EMP03';

--3-4) 200번 사원의 급여를 200만원으로 변경 & 확인 & 롤백
UPDATE V_EMP03 
SET SALARY = 2000000
WHERE EMP_ID = 200;
-- △ 에러 : SALARY 가 200만원 이므로, 뷰의 서브쿼리 WHERE 조건에 부합하지 않음 (SALARY >= 3000000)

UPDATE V_EMP03 
SET SALARY = 3000000
WHERE EMP_ID = 200;
-- △ 성공 : SALARY 가 300만원 이므로, 뷰의 서브쿼리 WHERE 조건에 부합 (SALARY >= 3000000)

SELECT * FROM V_EMP03;
SELECT * FROM EMPLOYEE;
-- △ 기본 테이블도 300만원으로 바뀜

ROLLBACK;



-- 4. WITH READ ONLY 
-- 4-1-1) 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_DEPT02
AS SELECT *
   FROM DEPARTMENT;
   
SELECT * FROM V_DEPT02;
SELECT * FROM USER_VIEWS WHERE VIEW_NAME = 'V_DEPT02';

-- 4-1-2) INSERT & 확인
INSERT INTO V_DEPT02 VALUES('D0', '해외영업4부', 'L5');
-- △ 성공

SELECT * FROM V_DEPT02;

-- 4-1-3) UPDATE & 확인
UPDATE V_DEPT02
SET LOCATION_ID = 'L2'
WHERE DEPT_TITLE = '해외영업4부';
-- △ 성공

SELECT * FROM V_DEPT02;

-- 4-1-4) DELETE & 확인
DELETE FROM V_DEPT02
WHERE DEPT_ID = 'D0';

SELECT * FROM V_DEPT02;

-- 4-2-1) WITH READ ONLY 실험용 뷰 생성 & 확인
CREATE OR REPLACE VIEW V_DEPT02
AS SELECT *
   FROM DEPARTMENT
WITH READ ONLY;

SELECT * FROM V_DEPT02;

-- 4-2-2) DML
--        : READ ONLY 이므로 DML 구문은 모두 에러 남
INSERT INTO V_DEPT02 VALUES('D0', '해외영업4부', 'L5');

UPDATE V_DEPT02
SET LOCATION_ID = 'L2'
WHERE DEPT_TITLE = '해외영업4부';

DELETE FROM V_DEPT02
WHERE DEPT_ID = 'D0';

