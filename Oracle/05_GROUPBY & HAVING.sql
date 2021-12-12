/* 
    <GROUP BY>
       그룹 기준을 제시할 수 있는 구문
       여러 개의 값을 하나의 그룹으로 묶어서 처리할 목적으로 사용 
*/ 

-- 전체 사원을 하나의 그룹으로 묶어서
-- 급여의 총합을 구한 결과 조회
SELECT SUM(SALARY)
FROM EMPLOYEE;

-- 각 부서별 그룹으로 묶어서
-- 부서별 급여의 총 합을 구한 결과 조회
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY DEPT_CODE NULLS FIRST;

-- 전체 사원수
SELECT COUNT(*)
FROM EMPLOYEE;

-- 각 부서 별 사원수
SELECT DEPT_CODE, COUNT(*)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT JOB_CODE, bonus
FROM EMPLOYEE;

-- 각 직급 별 보너스를 받는 사원 수 조회
SELECT JOB_CODE,COUNT(BONUS)
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY 1;

-- 각 직급별 급여 평균 조회
SELECT JOB_CODE AS "직급 코드",
       TO_CHAR(FLOOR(AVG(NVL(SALARY, 0))), 'L999,999,999') AS "급여 평균"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY 1;

-- 부서 별 사원 수, 보너스를 받는 사원 수, 급여의 합계, 평균 급여,
-- 최고 급여, 최저 급여를 부서별 내림차순으로 조회

SELECT DEPT_CODE AS "부서 명", COUNT(*) AS "사원 수",
       COUNT(BONUS) AS "보너스 받는 사원 수",
       TO_CHAR(SUM(SALARY), 'L999,999,999') AS "급여 합계",
       TO_CHAR(FLOOR(AVG(NVL(SALARY, 0))), 'L999,999,999') AS "평균 급여",
       TO_CHAR(MAX(SALARY), 'L999,999,999') AS "최고 급여",
       TO_CHAR(MIN(SALARY), 'L999,999,999') AS "최저 급여"
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY 1 DESC NULLS LAST;

-- 성별 별 사원 수
SELECT SUBSTR(EMP_NO, 8, 1) AS "성별"
FROM EMPLOYEE
GROUP BY SUBSTR(EMP_NO, 8, 1)   -- 컬럼명, 계산식, 함수 호출 만 올 수있음
ORDER BY 1;

-- 여러 컬럼을 제시해서 그룹 기준 선정
SELECT DEPT_CODE AS "부서 코드", 
       JOB_CODE AS "직급 코드", 
       COUNT(*) AS "직원 수", 
       SUM(SALARY) AS "급여의 합"
FROM EMPLOYEE
GROUP BY DEPT_CODE, JOB_CODE
ORDER BY DEPT_CODE;

/* 
    <HAVING>
       그룹에 대한 조건을 제시할 떄 사용하는 구문
       주로 그룹 함수의 결과를 가지고 비교 수행
       
    * 실행 순서
      5 SELECT        조회하고자 하는 컬럼명 AS "별칭" | 계산식 | 함수식
      1 FROM          조회하고자 하는 테이블명
      2 WHERE         조건식 
      3 GROUP BY      그룹 기준에 해당하는 컬럼명 | 계산식 | 함수식
      4 HAVING        그룹에 대한 조건식
      6 ORDER BY      컬럼명 | 별칭 | 컬럼순번 [ASC|DESC] [NULLS FIRST|LAST]
*/

-- 각 부서별 급여가 300만원 이상인 직원의 평균 급여 조회
SELECT DEPT_CODE, AVG(NVL(SALARY, 0))
FROM EMPLOYEE
WHERE SALARY >= 3000000 -- 일단 급여가 300 이상인 사람만 뽑아와서 그룹핑할게~
GROUP BY DEPT_CODE
ORDER BY DEPT_CODE;

-- 각 부서별 평균 급여가 300만원 이상인 부서들만 조회
-- ▷ 부서별 평균 급여를 내고, 300만원 이상인 애들만 조회할거임
SELECT DEPT_CODE, FLOOR(AVG(NVL(SALARY, 0)))
FROM EMPLOYEE
--WHERE FLOOR(AVG(NVL(SALARY, 0))) >= 3000000  
-- ▲ WHERE절에는 그룹함수 못쓴다고 에러
--   그룹 함수 수행보다 WHERE절이 먼저 수행되기 때문
GROUP BY DEPT_CODE
HAVING FLOOR(AVG(NVL(SALARY, 0))) >= 3000000
ORDER BY DEPT_CODE;

-- 직급 별 총 급여의 합이 1000만원 이상인 직급들만 조회
SELECT JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING SUM(SALARY) >= 10000000
ORDER BY 1;

-- 부서별 보너스를 받는 사원이 없는 부서만을 조회
SELECT DEPT_CODE,
       COUNT(BONUS)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) = 0
ORDER BY 1;

/* 
    <집계 함수>
       그룹 별 산출한 결과 값의 중간 집계를 계산해주는 함수
       
       ROLLUP, CUBE

*/

-- 각 직급 별 급여의 합계를 조회
SELECT JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY JOB_CODE;

-- 마지막 행에 전체 총 급여의 합계까지 조희
-- 그룹핑 컬럼이 하나면 ROLLUP, CUBE 둘 다 같은 결과가 나올것임
SELECT JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY ROLLUP(JOB_CODE)
ORDER BY JOB_CODE;

SELECT JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY CUBE(JOB_CODE)
ORDER BY JOB_CODE;

-- 부서 코드도 같고 직급 코드도 같은 사원들을
-- 그룹지어서 해당 급여의 합계 조회
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE, JOB_CODE
ORDER BY 1;

-- ROLLUP(컬럼1, 컬럼2, ...) : 컬럼 1을 가지고 중간 집계를 내는 함수
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY ROLLUP(DEPT_CODE, JOB_CODE)
ORDER BY DEPT_CODE;

-- CUBE(컬럼1, 컬럼2, ...) : 컬럼 1을 가지고 중간 집계를 내고
--                         컬럼 2를 가지고도 중간 집계를 내고,
--                         전달되는 컬럼 모두에 대해 중간 집계를 내줌
SELECT DEPT_CODE, JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY CUBE(DEPT_CODE, JOB_CODE)
ORDER BY DEPT_CODE;

/*
    <GROUPING>
       ROLLUP이나 CUBE에 의해 산출된 값이
       해당 컬럼의 집합의 산출물이면 0, 아니면 1을 반환하는 함수
*/

-- ROLLUP 버전
SELECT DEPT_CODE, 
       JOB_CODE, 
       SUM(SALARY),
       GROUPING(DEPT_CODE),
       GROUPING(JOB_CODE)
FROM EMPLOYEE
GROUP BY ROLLUP(DEPT_CODE, JOB_CODE)
ORDER BY DEPT_CODE;

-- CUBE 버전
SELECT DEPT_CODE, 
       JOB_CODE, 
       SUM(SALARY),
       GROUPING(DEPT_CODE),
       GROUPING(JOB_CODE)
FROM EMPLOYEE
GROUP BY CUBE(DEPT_CODE, JOB_CODE)
ORDER BY DEPT_CODE;


/* 
    <집합 연산자>
       여러 개의 쿼리문을 가지고 하나의 쿼리문으로 만드는 연산자
       
       UNION : 두 쿼리문을 수행한 결과값을 더한 후, 중복되는 행은 제거
       UNION ALL : UNION과 동일하게 두 쿼리문을 수행한 결과값을 더한 후, 중복 허용 (=합집합)
       INTERSECT : 두 쿼리문을 수행한 결과값에 중복된 결과값만 추출 (교집합)
       MINUS : 선행 결과값에서 후행 결과값을 뺀 나머지 결과값만 추출 (차집합)
       
       * 과정이 많아 프로그램 성능이 떨어지므로
          실무에서는 UNION ALL 빼고는 대부분 연산자로 처리가 가능해서 잘 안씀
*/

-- EMPLOYEE 테이블에서 부서코드가 D5인 사원들만 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5';

-- EMPLOYEE 테이블에서 급여가 300만원 초과인 사원들만 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY > 3000000;

-- 1. UNION
-- EMPLOYEE 테이블에서 부서 코드가 D5인 사원 또는 급여가 300만원 초과인 사원 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

UNION

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY > 3000000;

-- 근데 사실 위의 두 쿼리문은 OR나 IN을 써서 하나로 합칠 수 있음
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE DEPT_CODE = 'D5' OR SALARY > 3000000;


-- 2. UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

UNION ALL

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY > 3000000;

-- 3. INTERSECT
-- EMPLOYEE 테이블에서 부서 코드가 D5이면서 급여가 300만원 초과인 사원 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

INTERSECT

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY > 3000000;

-- 근데 사실 위의 두 쿼리문은 AND를 써서 하나로 합칠 수 있음
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE DEPT_CODE = 'D5' AND SALARY > 3000000;

-- 4. MINUS
-- 부서 코드가 D5인 사원들 중에서 급여가 300만원 초과인 사원들을 제외해서 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'

MINUS

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY > 3000000;

-- 근데 사실 위의 두 쿼리문은 AND를 써서 하나로 합칠 수 있음
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE 
WHERE DEPT_CODE = 'D5' AND SALARY < 3000000;


/*
    <GROUPING SET>
       그룹 별로 처리된 여러 개의 SELECT 문을 하나로 합친 결과를 원할 때 사용
*/

-- 부서 별 사원 수 
SELECT DEPT_CODE, COUNT(*)
FROM EMPLOYEE
GROUP BY DEPT_CODE

UNION ALL 

-- 직급 별 사원 수 
SELECT JOB_CODE, COUNT(*) -- UNION ALL으로 합치면 컬럼은 DEPT_CODE로 나옴
FROM EMPLOYEE
GROUP BY JOB_CODE;


SELECT DEPT_CODE, JOB_CODE, COUNT(*)
FROM EMPLOYEE
GROUP BY GROUPING SETS(DEPT_CODE, JOB_CODE);
-- ▲ 실행하면 부서별 사원수 / 직급별 사원수가
--    하나의 Result Set으로 묶여 아래위로 나옴


--SELECT DEPT_CODE, JOB_CODE, MANAGER_ID, FLOOR(AVG(NVL(SALARY, 0)))
--FROM EMPLOYEE
--GROUP BY DEPT_CODE, JOB_CODE, MANAGER_ID;
--
--SELECT DEPT_CODE, MANAGER_ID, FLOOR(AVG(NVL(SALARY, 0)))
--FROM EMPLOYEE
--GROUP BY DEPT_CODE, MANAGER_ID;
--
--SELECT JOB_CODE, MANAGER_ID, FLOOR(AVG(NVL(SALARY, 0)))
--FROM EMPLOYEE
--GROUP BY JOB_CODE, MANAGER_ID;

-- ▼ 위의 세개를 쓰까볼게요
SELECT DEPT_CODE, JOB_CODE, MANAGER_ID, FLOOR(AVG(NVL(SALARY, 0)))
FROM EMPLOYEE
GROUP BY GROUPING SETS((DEPT_CODE, JOB_CODE, MANAGER_ID), 
                        (DEPT_CODE, MANAGER_ID),
                        (JOB_CODE, MANAGER_ID) );