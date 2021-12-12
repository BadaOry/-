/*--------------------------------
    <TCL (Transaction Control Language>
       트렌젝션을 제어하는 언어
       
       * 트렌젝션 
         - 하나의 논리적인 작업 단위
            ex. ATM에서 현금 인출
                1. 카드 삽입
                2. 메뉴 선택
                3. 금액 확인 및 인증
                4. 실제 계좌에서 금액만큼 인출
                5. 실제 현금 인출
                6. 완료
         - 각각의 업무들을 묶어서 하나의 작업 단위로 만들어 버리는 것
         - 데이터의 변경 사항(DML)들을 묶어서 하나의 트렌젝션에 담아 처리함
           ▷ COMMIT하기 전 까지의 변경사항들을 하나의 트랜젝션에 담게 됨
         - 트렌젝션은 DML (INSERT, UPDATE, DELETE)를 대상으로 적용됨
         - COMMIT(트렌젝션을 종료 처리 후 저장), ROLLBACK(트렌젝션 취소), 
           SAVEPOINT(임시저장)을 통해 트렌젝션 제어
           
     <COMMIT>     
        메모리 버퍼에 임시 저장된 데이터를 DB에 반영함
        모든 작업들을 정상적으로 처리하겠다고 확정하는 구문
        ▷ 하나의 트렌젝션 과정을 종료
     <ROLLBACK>
        메모리 버퍼에 임시 저장된 데이터를 삭제한 후 마지막 COMMIT 시점으로 돌아감
        모든 작업들을 취소 처리하겠다고 확정하는 구문
        ▷ 하나의 트렌젝션 과정을 취소
     <SAVEPOINT>
        저장점을 정의해두면 ROLLBACK 진행할 때 전체 작업을 ROLLBACK하는 것이 아닌
        SAVEPOINT 까지의 일부만 롤백 함
            SAVEPOINT 포인트명; -- 저장점 지점
            ROLLBACK TO 포인트명 ; 
*/-------------------------------- 


-- 1. 새로운 테이블 생성 & 확인
CREATE TABLE EMP_01
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE
   FROM EMPLOYEE E
   LEFT OUTER JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID);
   
COMMIT;
   
SELECT * FROM EMP_01;

-- 2. EMP_ID가 900, 901인 사원 지우기  & 확인
DELETE FROM EMP_01
WHERE EMP_ID IN (900,901);

SELECT * FROM EMP_01;
-- △ 공유, 무닌수 삭제됨

-- 3. 두 개의 행이 삭제된 시점에 SAVEPOINT 지정
SAVEPOINT SP;

-- 4. EMP_ID가 200인 사원 지우기 & 확인
DELETE FROM EMP_01
WHERE EMP_ID = 200;

SELECT * FROM EMP_01;
-- △ 선동일 삭제됨

-- 5. SAVEPOINT로 ROLLBACK
--    : 200 사원 복구
ROLLBACK TO SP;

-- 6. 그냥 ROLLBACK
--    : SAVEPOINT는 의미가 없어지고 900, 901, 200 사원까지 복구 & 확인 &커밋
ROLLBACK;

SELECT * FROM EMP_01;

COMMIT;

-- 7. EMP_ID가 213인 사원 지우기 & 확인
DELETE FROM EMP_01
WHERE EMP_ID = 213;

SELECT * FROM EMP_01;
-- △ 하동운 삭제됨


-- 8. DDL 구문과 ROLLBACK
-- 8-1) 테스트용 아무 테이블 생성 
CREATE TABLE TEST (
    TID NUMBER
);

-- 8-2) 롤백 & 확인
--      : 예상대로라면 COMMIT된 직후에 지운 213 하동운이 돌아와야하는데 안돌아옴..
--        ▷ DDL 구문을 실행하는 순간
--           기존의 메모리 버퍼에 임시 저장된 변경사항들이 무조건 DB에 반영됨
--           ▷ COMMIT 시켜버림
ROLLBACK;

SELECT * FROM EMP_01;

-- 9. 실험용 테이블들 다 드랍
DROP TABLE EMP_01;
DROP TABLE TEST;
