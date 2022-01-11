
/*--------------------------------
       <PROCEDURE>
          PL/SQL문을 저장하는 객체
          필요할 때마다 복잡한 구문을 다시 입력할 필요 없이, 간단하게 호출해서 실행 결과를 얻음
          특정 로직을 처리하김나 하고 결과값을 반환하지 않음
          
          [표현법]
             CREATE OR REPLACE PROCEDURE 프로시저명
             (
                매개변수명1 [IN | OUT | INTOUT] 데이터타입 [:= DEFAULT값],
                매개변수명2 [IN | OUT | INTOUT] 데이터타입 [:= DEFAULT값],
                ...
                매개변수명N [IN | OUT | INTOUT] 데이터타입 [:= DEFAULT값]
              )
              IS
                선언부
              BEGIN  
                 실행부
              EXCEPTION
                 예외처리부
              END [프로시저명];
              /
              
          [실행 방법]
             EXECUTE(EXE) 프로시저명(매개값1, 매개값2,...);
*/--------------------------------

-- 1. 테스트용 테이블 생성 & 확인
CREATE TABLE EMP_DUP
AS SELECT * FROM EMPLOYEE;

SELECT * FROM EMP_DUP;

-- 2. 테스트 테이블의 데이터를 모두 지우는 프로시저 생성
--    : 바로 실행되는 것이 아니라 컴파일만 됨 (왼쪽 메뉴 프로시저에서 확인 가능)
CREATE OR REPLACE PROCEDURE DEL_ALL_EMP
IS 
BEGIN
    DELETE FROM EMP_DUP;
    
    COMMIT;
END;
/

-- 3. 테스트 테이블 확인해보기
--    : 모두모두 나옵니다. 당연함. 프로시저를 아직 실행하지 않았기 때문.
SELECT * FROM EMP_DUP;

-- 4. 프로시저 호출
EXECUTE DEL_ALL_EMP;

-- 5. 테스트 테이블 확인해보기
--    : 모두 삭제됨. 그리고 커밋 했으므로 롤백도 안됨
SELECT * FROM EMP_DUP;

-- 6. 프로시저를 관리하는 데이터 딕셔너리 확인
--    : 우리가 프로시저 생성할 때 썼던 구문이 한 행씩 들어감
SELECT * FROM USER_SOURCE;

-- 7. 테스트용 테이블 삭제
DROP TABLE EMP_DUP;

-- 8. 만든 프로시저도 삭제
DROP PROCEDURE DEL_ALL_EMP;

/*--------------------------------
          1) 매개변수가 있는 프로시저 
             프로시저 실행 시 매개변수로 인자값을 전달해야 함
*/--------------------------------

-- 1. 프로시저 생성
CREATE OR REPLACE PROCEDURE DEL_EMP_ID
(
    P_EMP_ID EMPLOYEE.EMP_ID%TYPE
)
IS
BEGIN
    DELETE FROM EMPLOYEE
    WHERE EMP_ID = P_EMP_ID;
END;
/

-- 2. 프로시저 실행 : 유재식 사원 삭제
EXEC DEL_EMP_ID(204);

-- 3. 테이블 확인
--    : 204번 유재식 사원 정상적으로 삭제
SELECT * FROM EMPLOYEE;

-- 4. 사용자가 입력한 값도 매개변수로 전달 가능
--    : 205번 정중하 사원 정상적으로 삭제
EXEC DEL_EMP_ID('&사번');

-- 5. 테이블 확인
SELECT * FROM EMPLOYEE;

-- 6. 롤백 & 확인
ROLLBACK;
SELECT * FROM EMPLOYEE;

/*--------------------------------
          2) IN/OUT 매개변수가 있는 프로시저 
             IN 매개변수 : 프로시저 내부에서 사용될 변수
             OUT 매개변수 : 프로시저 호출부(외부)에서 사용될 값을 담아줄 변수
*/--------------------------------
-- 1. 테스트용 프로시저 생성
CREATE OR REPLACE PROCEDURE SELECT_EMP_ID
(
    V_EMP_ID IN EMPLOYEE.EMP_ID%TYPE, -- 밖에서 값을 받아오는 변수
    V_EMP_NAME OUT EMPLOYEE.EMP_NAME%TYPE, -- 값을 담아오는 변수
    V_SALARY OUT EMPLOYEE.SALARY%TYPE,
    V_BONUS OUT EMPLOYEE.BONUS%TYPE
)
IS
BEGIN
    SELECT EMP_NAME, SALARY, NVL(BONUS, 0)
    INTO V_EMP_NAME, V_SALARY, V_BONUS
    FROM EMPLOYEE
    WHERE EMP_ID = V_EMP_ID;

END;
/

-- 2. 바인드 변수 생성
--    : VARIABLE 혹은 VAR 키워드를 사용하여 생성
VAR VAR_EMP_NAME VARCHAR2(30);
VAR VAR_SALARY NUMBER;
VAR VAR_BONUS NUMBER;

-- 3. 프로시저 호출이 끝나면 바인드 변수 값을 자동으로 켜주는 옵션 ON
--    단, 옆에 주석 넣으면 안돌아감...
SET AUTOPRINT ON;

-- 4. 프로시저 실행
--    단, 바인드 변수는 ':'를 앞에 붙여줘야 참조 가능
EXEC SELECT_EMP_ID('200', :VAR_EMP_NAME, :VAR_SALARY, :VAR_BONUS)

PRINT VAR_EMP_NAME;
PRINT VAR_SALARY;
PRINT VAR_BONUS;




/*--------------------------------
       <FUNCTION>
          프로시저와 사용 용도가 거의 비슷하지만
          프로시저와 다르게 OUT 변수를 사용하지 않아도 실행 결과를 되돌려받을 수 있음 (RETURN)
          
          [표현법]
             CREATE OR REPLACE FUNCTION 함수명
             (
                매개변수 1 타입,
                매개변수 2 타입,
                ...
              )
              RETURN 데이터타입
              IS 
                 선언부
              BEGIN
                 실행부
                 RETURN 반환값; -- 프로시저와 다르게 RETURN 구문 추가                 
              EXCEPTION 
                 예외처리부
              END [함수명];
              / 
*/--------------------------------

-- 1. 사번을 입력받아 해당 사원의 보너스를 포함하는 연봉을 계산 후 리턴하는 함수 생성
-- 1-1) 함수 생성
--    : 바로 실행되는 것이 아니라 컴파일만 됨 (왼쪽 메뉴 함수에서 확인 가능)
CREATE OR REPLACE FUNCTION BONUS_CALC
(
    V_EMP_ID EMPLOYEE.EMP_ID%TYPE
)
RETURN NUMBER
IS 
    V_SAL EMPLOYEE.SALARY%TYPE;
    V_BONUS EMPLOYEE.BONUS%TYPE;
BEGIN
    SELECT SALARY, NVL(BONUS,0)
    INTO V_SAL, V_BONUS
    FROM EMPLOYEE
    WHERE EMP_ID = V_EMP_ID;

    RETURN (V_SAL + (V_SAL * V_BONUS)) * 12;
END;
/

-- 1-2) 프로시저를 관리하는 데이터 딕셔너리 확인
--    : 우리가 프로시저 생성할 때 썼던 구문이 한 행씩 들어감
SELECT * FROM USER_SOURCE;

-- 1-3) 함수 결과를 반환받아 저장할 바인드 변수 선언
VAR VAR_CALC_SALARY NUMBER;

-- 1-4) 함수 호출
-- EXEC BONUS_CALC('&사번') 
-- △ 에러 : 반환값이 있으므로, 반환값을 받아주는 변수가 있어야 함
EXEC :VAR_CALC_SALARY := BONUS_CALC('200');



-- 2.함수를 SELECT 문에서 사용하기
--  : 함수는 RETURN값이 있어서 SELECT문에서 사용 가능 (EXEC 생략 가능)
SELECT EMP_ID, EMP_NAME, SALARY, BONUS, BONUS_CALC(EMP_ID) AS "연봉"
FROM EMPLOYEE
WHERE BONUS_CALC(EMP_ID) >= 40000000
ORDER BY BONUS_CALC(EMP_ID) DESC;





/*--------------------------------
       <CURSOR>
          SQL문의 처리 결과(처리결과가 여러 행(ROW))를 담고있는 객체
          커서 사용 시, 여러 행으로 나타난 처리 결과에 순차적으로 접근이 가능
          
          * 커서 종류
             묵시정/명시적 커서 두 종류가 존재
             
          * 커서 속성 (단, 묵시적 커서의 경우 커서명은 SQL로 사용됨)
             - 커서명%NOT FOUND : 커서 영역에 남아있는 ROW 수가 없으면 TRUE
                                                          있으면 FALSE
             - 커서명%FOUND     : 커서 영역에 남아있는 ROW 수가 한 개 이상일 경우 TRUE
                                                                   아니면 FALSE 
             - 커서명%ISOPEN    : 커서가 OPNE 상태인 경우 TRUE
                                                아니면 FALSE
                                 단, 묵시적 커서는 항상 FALSE 리턴
             - 커서명%ROWCOUNT  : SQL 처리 결과로 얻어온 행(ROW)의 갯수
             
          1) 묵시적 커서 : 오라클에서 자동으로 생성되어 사용하는 커서
             - PL / SQL 블록에서 SQL문 실행 시 마다 자동으로 만들어져서 사용 됨
             - 사용자는 생성 유무를 알 수 없지만, 커서 속성을 활용하여 커서의 정보를 얻어올 수 있음
*/--------------------------------

-- 1. BONUS가 NULL인 사원의 BONUS를 0으로 수정
-- 1-1) 롤백 밑작업 & 세팅 온
SELECT * FROM EMPLOYEE;
COMMIT;

SET SERVEROUTPUT ON;

-- 1-2) PL/SQL문 & 확인 & 롤백
BEGIN
    UPDATE EMPLOYEE
    SET BONUS = 0
    WHERE BONUS IS NULL;
    
    -- 묵시적 커서 사용(ROWOCUNT)
    DBMS_OUTPUT.PUT_LINE(SQL%ROWCOUNT || '행이 수정됨');
END;
/

SELECT * FROM EMPLOYEE;



/*--------------------------------
          2) 명시적 커서 : 사용자가 직접 선언해서 사용할 수 있는 커서
          
             [사용 방법]
                1) CURSOR 커서명 IS ..       : 커서 선언
                2) OPEN 커서명;              : 커서 오픈
                3) FETCH 커서명 INTO 변수 ... : 커서에서 데이터 추출(한 행씩 데이터를 가져옴)
                4) CLODE 커서명;             : 커서 닫기
    
             [표현법]
                CURSOR 커서명 IS [SELECT문]
                
                OPEN 커서명;
                FETCH 커서명 INTO 변수;
                ...
                CLOSE 커서명;
*/--------------------------------
-- 1. 급여가 3000000이상인 사원의 사번, 이름, 급여 출력 (PL/SQL)
DECLARE
    EID EMPLOYEE.EMP_ID%TYPE;
    ENAME EMPLOYEE.EMP_NAME%TYPE;
    SAL EMPLOYEE.SALARY%TYPE;
    
    CURSOR C1 IS  -- 커서 선언 : 서브쿼리를 가지고만 있음 (결과 X)
        SELECT EMP_ID, EMP_NAME, SALARY
        FROM EMPLOYEE
        WHERE SALARY >= 3000000;
BEGIN
    OPEN C1; -- 커서 오픈 : 서브쿼리를 실행해서 결과값을 담을 것임
             --           또한 FETCH 전에는 아무 행도 가리키지 않음
    LOOP
        -- ▽ 서브쿼리의 결과에서 한 행씩 차례로 데이터를 가져옴
        FETCH C1 INTO EID, ENAME, SAL;
        
        EXIT WHEN C1%NOTFOUND;
        -- ▽ LOOP를 빠져나오지 못하면 아래 DBMS 실행
        DBMS_OUTPUT.PUT_LINE(EID || ' ' || ENAME || ' ' || SAL);
    END LOOP;
    
    CLOSE C1;  -- 커서 종료
END;
/

-- 2. 전체 부서에 대해 부서 코드, 부서명, 지역 조회 (PROCEDURE)
-- 2-1) 우선 테이블 조회
SELECT * FROM DEPARTMENT;

-- 2-2) PL/SQL 작성해서 프로시저 컴파일
CREATE OR REPLACE PROCEDURE CURSOR_DEPT
IS
    V_DEPT DEPARTMENT%ROWTYPE;
    
    CURSOR C1 IS
        SELECT * FROM DEPARTMENT;
BEGIN
    OPEN C1;
    
    LOOP
        FETCH C1 INTO V_DEPT.DEPT_ID, V_DEPT.DEPT_TITLE, V_DEPT.LOCATION_ID;
        
        EXIT WHEN C1%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE( '부서 코드 : ' || V_DEPT.DEPT_ID || 
                               ', 부서명 :  ' || V_DEPT.DEPT_TITLE || 
                               ', 지역 : ' || V_DEPT.LOCATION_ID);
    END LOOP;
    
    CLOSE C1;

END;
/

-- 2-3) 프로시저 실행
EXEC CURSOR_DEPT;


-- 3. FOR LOOP를 이용한 커서 사용
-- 3-1) PL/SQL문 작성
CREATE OR REPLACE PROCEDURE CURSOR_DEPT
IS
    V_DEPT DEPARTMENT%ROWTYPE;
    
    CURSOR C1 IS 
        SELECT * FROM DEPARTMENT;
BEGIN
    FOR V_DEPT IN C1 -- 자동으로 CURSOR를 OPEN 시켜주고, 
                     -- 자동으로 하나씩 담아주고, 자동으로 CLOSE 시켜줄것임
    LOOP
        DBMS_OUTPUT.PUT_LINE('부서 코드 : ' || V_DEPT.DEPT_ID || 
                               ', 부서명 :  ' || V_DEPT.DEPT_TITLE || 
                               ', 지역 : ' || V_DEPT.LOCATION_ID);
    END LOOP;
END;
/

-- 3-3) 프로시저 실행
EXEC CURSOR_DEPT;


-- 4. FOR IN LOOP를 이용한 커서 사용
-- 4-1) PL/SQL문 작성
CREATE OR REPLACE PROCEDURE CURSOR_DEPT
IS
    V_DEPT DEPARTMENT%ROWTYPE;

--    ▽ 커서 선언 생략 가능    
--    CURSOR C1 IS 
--        SELECT * FROM DEPARTMENT;
BEGIN
--  ▽ LOOP 시작 시 자동으로 커서를 생성(선언)하고 커서를 OPNE함
--     반복할 때마다 FETCH 자동 실행 후
--     LOOP 종료 시 커서가 자동으로 CLOSE됨
    FOR V_DEPT IN (SELECT * FROM DEPARTMENT)
    LOOP
        DBMS_OUTPUT.PUT_LINE('부서 코드 : ' || V_DEPT.DEPT_ID || 
                               ', 부서명 :  ' || V_DEPT.DEPT_TITLE || 
                               ', 지역 : ' || V_DEPT.LOCATION_ID);
    END LOOP;
END;
/

-- 4-3) 프로시저 실행
EXEC CURSOR_DEPT;


/*--------------------------------
       <PACKAGE>
          프로시저와 함수를 효율적으로 관리하기 위해 묶는 단위로
          패키지는 선언부, 본문(BODY)로 나뉨
*/--------------------------------
-- 1. 패키지 선언부에 변수, 상수 선언 및 사용법
-- 1-1) 패키지 선언
CREATE OR REPLACE PACKAGE TSET_PACKAGE
IS
    NAME VARCHAR2(20); -- 변수
    PI CONSTANT NUMBER := 3.14; -- 상수
END;
/

-- 1-2) 패키지 사용
BEGIN
    TEST_PACKAGE.NAME := '홍길동'; 
    
    DBMS_OUTPUT.PUT_LINE('이름 : '|| TEST_PACKAGE.NAME);
    DBMS_OUTPUT.PUT_LINE('PI : '|| TEST_PACKAGE.PI);
    
END;
/

-- 2. 패키지 선언부에 프로시저, 함수, 커서 및 사용 방법
-- 2-1) 프로시저 생성 
--      : 컴파일 완료
CREATE OR REPLACE PACKAGE TSET_PACKAGE
IS
    NAME VARCHAR2(20); -- 변수
    PI CONSTANT NUMBER := 3.14; -- 상수
    PROCEDURE SHOW_EMP;
    
END;
/
-- 2-2) 실행하기 : 패키지 BODY가 없어서 에러
EXEC TEST_PACKAGE.SHOW_EMP;

-- 2-3) 패키지 BODY 생성
CREATE OR REPLACE PACKAGE BODY TEST_PACKAGE
IS
    PROCEDURE SHOW_EMP
    IS
        V_EMP EMPLOYEE%ROWYPE;
    BEGIN
        FOR V_EMP IN (SELECT EMP_ID, EMP_NAME, EMP_NO FROM EMPLOYEE)
        LOOP
            DBMS_OUTPUT.PUT_LINE('사번 : ' || V_EMP.EMP_ID ||
                                 ', 이름 : ' || V_EMP.EMP_NAME ||
                                 ', 주민번호 : ' || V_EMP.EMP_NO);
        END LOOP;
    END;
END;
/

-- 2-4) 실행하기 : BODY까지 만든 패키지 실행
EXEC TEST_PACKAGE.SHOW_EMP;