/*

    <SELECT>
       [표현법]
        SELECT 컬럼, 컬럼, 컬럼 ...
        FROM 테이블명 ;
        
       - 데이터를 조회할 때 사용하는 구문
       - SELECT를 통해서 조회된 결과물을 Result Set이라고 부릅 (=조회된 행들의 집합)
       - 조회하고자 하는 컬럼들은 반드시 FROM 절에 기술한 테이블에 존재하는 컬럼이어야 함       
*/

-- EMPLOYEE 테이블의 전체 사원의 모든 컬럼(*) 정보 조회
SELECT *
FROM EMPLOYEE;

-- EMPLOYEE 테이블의 전체 사원들의 사번, 이름, 급여만을 조회
SELECT EMP_NO, EMP_NAME, SALARY
FROM EMPLOYEE;

-- QUERY문은 대소문자 가리지 않아서, 소문자로 써도 됨
-- 다만 관례 상 대문자로 사용
--select emp_no, emp_name, salary
--from employee;

---------- 실습 문제 ----------
-- 1. JOB 테이블의 모든 컬럼 정보 조회 (별표시 사용 가능)
SELECT *
FROM JOB;

-- 2. JOB 테이블의 직급명 컬럼만 조회
SELECT JOB_NAME
FROM JOB;

-- 3. DEPARTMENT 테이블의 모든 컬럼 정보 조회 (별표시 사용 X)
SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID
FROM DEPARTMENT;

-- 4. EMPLOYEE 테이블의 사원명, 이메일, 전화번호, 입사일(HIRE_DATE) 정보만 조회
SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE
FROM EMPLOYEE;
-------------------------------

/*
    <컬럼 값을 통한 산술 연산>
       SELECT절의 컬럼명 입력 부분에서, 산술연산자를 사용해 결과를 조회할 수 있음
*/

-- EMPLOYEE 테이블의 직원명, 직원의 연봉 조회
-- 연봉 = 급여 * 12
SELECT EMP_NAME, SALARY * 12
FROM EMPLOYEE;

-- EMPLOYEE 테이틀의 직원명, 급여, 연봉, 보너스가 포함된 연봉 조회
-- 보너스가 포함된 연봉 = ( 급여 + ( 급여 * 보너스) ) *12 
-- 단, 산술 연산 중 NULL 값이 존재할 경우 산술 연산한 결과 값은 무조건 NULL 임
SELECT EMP_NAME, SALARY, SALARY * 12, (SALARY + (SALARY * BONUS) *12)
FROM EMPLOYEE;

-- EMPLOYEE 테이블의 직원명, 입사일, 근무일수 조회
-- 근무 일수 = 오늘 날짜 - 입사일
-- DATE 형식끼리도 연산 가능
-- SYSDATE는 현재 날짜를 출력
-- CEIL(NUMBER)는 매개값으로 전달되는 수를 올림하는 함수
SELECT EMP_NAME, HIRE_DATE, CEIL(SYSDATE - HIRE_DATE)
FROM EMPLOYEE;


-------------------------------
/*
    <컬럼명의 별칭 지정하기>
      [표현법]
         컬럼 AS 별칭 / 컬럼 AS "별칭" / 컬럼 별칭 /  컬럼 "별칭"
      
       - 산술연산을 하게 되면 컬럼명이 지저분해 질 수 있으므로,
         컬럼명에 별칭을 부여해서 깔끔하게 보여줄 수 있음''
       - 별칭 부여 시, 띄어쓰기 혹은 특수문자가 별칭에 포함 될 경우
         반드시 " "로 감싸줘야 함
*/
SELECT EMP_NAME AS 이름, SALARY AS "급여", SALARY * 12 연봉, (SALARY + (SALARY * BONUS) *12) "총 소득(원)"
FROM EMPLOYEE;
-------------------------------
/*
    <리터럴>
       임의로 지정된 문자열(' ')을 SELECT 절에 사용하면 테이블에 존재하는 데이터처럼 조회 가능 
       리터럴은 Result Set의 모든 행에 반복적으로 출력 됨
      * 리터럴은 숫자도 가능
*/

-- EMPLOYEE 테이블의 직원 번호, 직원명, 급여, 단위(원) 조회
SELECT EMP_ID, EMP_NAME, SALARY, '원' AS "단위(원)"
FROM EMPLOYEE;
-------------------------------
/*
    <DISTINCT>
      컬럼에 포함된 중복 값을 한 번씩만 표시함
      SELECT 절에 한 번만 기술 가능
*/

-- EMPLOYEE 테이블의 직원 코드 조회
SELECT DISTINCT JOB_CODE
FROM EMPLOYEE;

-- DISTINCT는 SELECT절에서 한 번만 쓸 수 있으므로 오류
--SELECT DISTINCT JOB_CODE, DISTINCT DEPT_CODE
--FROM EMPLOYEE;

-- 컬럼을 여러개 쓸 경우, 이 여러개의 묶음을 하나로 보고 중복 처리
SELECT DISTINCT JOB_CODE, DEPT_CODE
FROM EMPLOYEE;

-------------------------------
-------------------------------
/*
    <WHERE 절>
      [표현법]
        SELECT 컬럼, 컬럼, ..., 컬럼
        FROM 테이블명
        WHERE 조건식;
      
      - 조회하자 하는 테이블에서 해당 조건에 만족하는 결과만을 조회하고자 할 때 사용
      - 조건식에는 다양한 연산자 사용 가능
      
    <비교 연산자>
      =            : 동등 비교 
      >, <, >=, <= : 대소 비교 
      <>, !=, ^=   : 같지 않다
*/

-- EMPLOYEE 테이블에서 부서 코드가 D9와 일치하는 사원들의 모든 컬럼 조회
SELECT *
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- EMPLOYEE 테이블에서 부서 코드가 D9가 아닌
-- 사원들의 사번, 사원명, 부서 코드만 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE != 'D9';

---------- 실습 문제 ----------
-- 1. EMPLOYEE 테이블에서 급여 (SALARY)가 300만원 이상인 
--    직원의 직원명, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY >= 3000000;


-- 2. EMPLOYEE 테이블에서 연봉이 500만원 이상인
--    직원의 직원명, 급여, 연봉, 입사일 조회
SELECT EMP_NAME AS "이름", 
       SALARY AS "급여", 
       SALARY*12 AS "연봉", 
       HIRE_DATE AS "입사일"
FROM EMPLOYEE
WHERE (SALARY * 12) >= 50000000;
-------------------------------

/* 
    <논리 연산자>
       여러 개의 조건을 엮을 때 사용
       AND (~이면서, 그리고)
       OR (~이거나, 또는)
*/

-- EMPLOYEE 테이블에서 부서 코드가 D6이면서 급여가 300만원 이상인
-- 직원들의 사번, 직원명, 부서코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6' AND SALARY >= 3000000;

-- EMPLOYEE 테이블에서 직급 코드가 J2이거나 급여가 400만원 이상인
-- 직원들의 모든 컬럼 조회
SELECT * 
FROM EMPLOYEE
WHERE JOB_CODE = 'J2' OR SALARY >= 4000000;

-- EMPLOYEE 테이블에서 급여가 350만원 이상 600만원 이하를 받는
-- 직원의 사번, 직원명, 부서 코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000 OR SALARY <= 6000000;

-------------------------------
/*
    <BETWEEN AND>
       [표현법]
          WHERE 비교대상컬럼 BETWEEN 하한값 AND 상한값
          
       - WHERE 절에서 사용되는 구문으로 범위에 대한 조건을 제시할 때 사용
       - 비교대상컬럼 값이 하한값 이상이고, 상한값 이하인 경우 TRUE 리턴
*/

-- EMPLOYEE 테이블에서 급여가 350만원 이상 600만원 이하를 받는
-- 직원의 사번, 직원명, 부서 코드, 급여 조회 (BETWEEN AND 이용)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY BETWEEN 3500000 AND 6000000;

-- EMPLOYEE 테이블에서 급여가 350만원 이상 600만원 이하가 아닌
-- 직원의 사번, 직원명, 부서 코드, 급여 조회 (BETWEEN AND 이용)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY NOT BETWEEN 3500000 AND 6000000;
--WHERE NOT SALARY BETWEEN 3500000 AND 6000000;

-- EMPLOYEE 테이블에서 입사일이 '90/01/01' ~ '01/01/01'인
-- 사원의 모든 컬럼 조회
SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '90/01/01' AND '01/01/01';

-- EMPLOYEE 테이블에서 입사일이 '90/01/01' ~ '01/01/01'이 아닌
-- 사원의 모든 컬럼 조회
SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE NOT BETWEEN '90/01/01' AND '01/01/01';

-------------------------------
/*
    <LIKE>
       [표현법]
          WHERE 비교대상컬럼 LIKE '특정 패턴'
    
       - 비교하려는 컬럼 값이 지정된 특정 패턴에 만족할 경우 TRUE 리턴
       - 특정 패턴에는 '%', '_'를 와일드 카드로 사용 가능
          ▷ % : 0 글자 이상
             ex ) 비교대상컬럼 LIKE '문자%' : 비교대상컬럼 컬럼 값 중에 '문자'로 시작하는 모든 행 조회
                  비교대상컬럼 LIKE '%문자' : 비교대상컬럼 컬럼 값 중에 '문자'로 끝나는 모든 행 조회
                  비교대상컬럼 LIKE '%문자%' : 비교대상컬럼 값 중에 '문자'로 시작, 중간, 끝인 모든 행 조회
                      * % 는 0글자 이상입니다 !
          ▷ _ : 1 글자 
             ex ) 비교대상컬럼 LIKE '_문자' : 비교대상컬럼 값 중에 '문자'앞에 무조건 한 글자가 오는 모든 행 조회
             ex ) 비교대상컬럼 LIKE '__문자' : 비교대상컬럼 값 중에 '문자'앞에 무조건 두 글자가 오는 모든 행 조회
*/
-- EMPLOYEE 테이블에서 서잉 전 씨인 
-- 사원의 사원명, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '전%';

-- EMPLOYEE 테이블에서 이름 중에 '하'가 포함된
-- 사원의 사원명, 주민번호, 부서 코드 조회
SELECT EMP_NAME, EMP_NO, DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%하%';

-- EMPLOYEE 테이블에서 전화번호 4번째 자리가 9로 시작하는 
-- 사원의 사번, 사원명, 전화번호, 이메일 조회
SELECT EMP_ID, EMP_NAME, PHONE, EMAIL
FROM EMPLOYEE
WHERE PHONE LIKE '____9%';

-- EMPLOYEE 테이블에서 이메일 중 _ 앞 글자가 3자리인 이메일 주소를 가진
-- 사원의 사번, 사원명, 이메일 조회
-- ex ) sun_di@kh.or.kr 이런 애들만 조회 하겠다는 뜻
SELECT EMP_ID, EMP_NAME, EMAIL
FROM EMPLOYEE
WHERE EMAIL LIKE '___\_%' ESCAPE '\'; 
-- ▲ 데이터로 처리할 값 앞에 임의의 문자를 제시하고 임의의 문자를 ESCAPE 옵션에 등록함

-- EMPLOYEE 테이블에서 김씨 성이 아닌
-- 직원의 사번, 사원명, 입사일 조회
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE 
WHERE EMP_NAME NOT LIKE '김%';
-- WHERE NOT EMP_NAME LIKE '김%';
-- WHERE NOT EMP_NAME NOT LIKE '김%';
-- ▲ NOT NOT도 되긴 합니다만.....


---------- 실습 문제 ----------
-- 1. EMPLOYEE 테이블에서 이름 끝이 '연'으로 끝나는 
-- 사원의 사원명, 입사일
SELECT EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%연';

-- 2. EMPLOYEE 테이블에서 전화번호 처음 세 자리가 010이 아닌
-- 사원의 이름, 전화번호 조회
SELECT EMP_NAME, PHONE
FROM EMPLOYEE
WHERE PHONE NOT LIKE '010%';

-- 3. DEPARTMENT 테이블에서 해외영업부에 대한 모든 컬럼 조회
SELECT *
FROM DEPARTMENT
WHERE DEPT_TITLE LIKE '해외영업%';

-------------------------------
/*
    <IS NULL / IS NOT NULL>
       [표현법]
          WHERE 비교대상컬럼 IS [NOT] NULL
          
       - 컬럼 값에 NULL이 있을 경우 NULL 값 비교에 사용
       - 비교대상컬럼 IS NULL : 컬럼 값이 NULL인 경우 TRUE 리턴
       - 비교대상컬럼 IS NOT NULL : 컬럼 값이 NULL이 아닌 경우 TRUE 리턴
*/

-- EMPLOYEE 테이블에서 보너스를 받지 않는
-- 사원의 사번, 사원명, 급여 조회
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE
WHERE BONUS IS NULL;
-- WHERE 절에 있는 컬럼이 무조건 SELECT 절에 있어야 하는 건 아님
-- WHERE BONUS = NULL;
-- ▲ NULL은 연산자로 비교할 수 없어서 에러

-- EMPLOYEE 테이블에서 관리자(사수)가 없는
-- 사원의 이름, 부서 코드 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL;

-- EMPLOYEE 테이블에서 관리자도 없고, 부서도 배치 받지 않은
-- 사원의 이름, 부서 코드 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL AND DEPT_CODE IS NULL;

-- EMPLOYEE 테이블에서 부서 배치를 받진 않았지만 보너스를 받는
-- 사원의 사원명, 부서 코드, 보너스 조회
SELECT EMP_NAME, DEPT_CODE, BONUS
FROM EMPLOYEE
WHERE DEPT_CODE IS NULL AND BONUS IS NOT NULL;

-------------------------------
/*
    <IN>
       [표현법]
          WHERE 비교대상컬럼 IN('', '', '', ..., '값')
          
       - 값 목록 중에 일치하는 값이 있을 때 TRUE를 리헌
*/

-- EMPLOYEE 테이블에서 D5, D6, D8 부서의
-- 부서원들의 사원명, 부서 코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5', 'D6', 'D8');
-- WHERE DEPT_CODE = 'D5' OR DEPT_CODE = 'D6' OR DEPT_CODE - 'D8';

-------------------------------
/* 
    <연결 언산자>
       여러 컬럼 값들을 하나의 컬럼인 것처럼 연결하거나,
       컬럼과 리터럴을 연결할 수 있는 연산자
*/

-- EMPLOYEE 테이블에서
-- 사원, 사원명, 급여를 연결해서 조회
SELECT EMP_ID || EMP_NAME || SALARY
FROM EMPLOYEE;

-- EMPLOYEE 테이블에서 
-- 사원명, 급여를 리터럴과 연결해서 조회
SELECT EMP_NAME || '의 월급은 ' || SALARY || '원 입니다.' AS "급여 정보"
FROM EMPLOYEE;


-------------------------------
/*
    <연산자 우선순위>
       0. ()
       1. 산술 연산자
       2. 연결 연산자
       3. 비교 연산자
       4. IS NULL, LIKE, IN
       5. BETWEEN AND
       6. 논리 연산자 - NOT
       7. 논리 연산자 - AND
       8. 논리 연산자 - OR
*/

-- EMPLOYEE 테이블에서 직급 코드가 J2 또는 J7 사원들 중
-- 급여가 200만원 이상인 사원들의 모든 컬럼 조회

SELECT * 
FROM EMPLOYEE
-- WHERE JOB_CODE = 'J7' OR JOB_CODE = 'J2' AND SALARY >= 2000000;
-- ▲ 이거는 우리가 원하는 값이 안나옴
WHERE (JOB_CODE = 'J7' OR JOB_CODE = 'J2') AND SALARY >= 2000000;
-- WHERE JOB_CODE IN ('J2', 'J7') AND SALARY >= 2000000;
-- ▲ 이거는 우리가 원하는 값이 나오고, IN의 우선순위가 높아서 정상적으로 결과 도출함


-------------------------------
/*
    <ORDER BY>
       [표현법]
          SELECT 컬럼, 컬럼, 컬럼, ..., 컬럼
          FROM  테이블명
          WHERE 조건식
          ORDER BY 정렬시키고자 하는 컬럼명|별칭|컬럼 순번 [ASC|DESC] [NULLS FIRST|NULLS LAST];
    
       - ASC : 오름차순 정렬 (ASC 또는 DESC 생략 시 기본 값)
       - DESC : 내림차순 정렬 
       - NULLS FIRST : 정렬하고자 하는 컬럼 값에 NULL이 있는 경우 NULL을 맨 앞으로 정렬
       - NULLS LAST : 정렬하고자 하는 컬럼 값에 NULL이 있는 경우 NULL을 맨 뒤로 정렬
       
       - 정렬 시에는 숫자 뿐만 아니라 문자, 날짜 정렬도 가능
*/

-- EMPLOYEE 테이블에서 BONUS로 오름차순 정렬
SELECT *
FROM EMPLOYEE
ORDER BY BONUS;
-- ▲ 오름차순 정렬은 기본적으로 NULLS LAST
-- ORDER BY EMP_NAME;
-- ORDER BY HIRE_DATE;

-- EMPLOYEE 테이블에서 BONUS로 내림차순 정렬
SELECT *
FROM EMPLOYEE
ORDER BY BONUS DESC;
-- ▲ 내림차순 정렬은 기본적으로 NULLS FIRST 

-- EMPLOYEE 테이블에서 BONUS로 내림차순 정렬
-- 단, BONUS가 같은 경우 SALARY가 높은 순으로 정렬
SELECT *
FROM EMPLOYEE
ORDER BY BONUS DESC, SALARY;

-- EMPLOYEE 테이블에서 BONUS로 내림차순 정렬 (+ NULL을 뒤로 뺌)
-- 단, BONUS 값이 일치하는 경우, SALARY 값을 오름차순으로 정렬
SELECT *
FROM EMPLOYEE
ORDER BY BONUS DESC NULLS LAST , SALARY ASC;

-- EMPLOYEE 테이블에서 연봉별 내림차순으로 정렬된
-- 사원의 사원명, 연봉 조회
SELECT EMP_NAME, SALARY *12 AS "연봉"
FROM EMPLOYEE
-- ORDER BY (SALARY * 12) DESC ;
-- ▲ 초심자 레벨
--ORDER BY 2 DESC ;
-- ▲ 컬럼 순번 사용 : SELECT 문의 2번째 식을 가져와서 정렬
ORDER BY "연봉" DESC ;
-- ▲ 별칭 사용
