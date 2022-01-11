-- CREATE TABLE 권한이 없어서 오류 발생
-- CREATE TABLE 권한을 부여하면 됨 
CREATE TABLE TEST(
    TID NUMBER
);

-- 본인이 소유하고 있는 테이블들은 바로 조작이 가능
SELECT * FROM TEST;
INSERT INTO TEST VALUES(1);


-- 다른 계정의 테이블에 접근할 수 있다? 없다?
--  ▶ 5.권한을 부여받으면 접근 가능
SELECT * FROM KH.EMPLOYEE;
SELECT * FROM KH.DEPARTMENT;

-- 6. KH 계정의 DEPARTMENT 테이블에 데이터를 삽입할 수 있는 권한을 받아
--    데이터를 삽입해보는 테스트 & 롤백
--    단, REVOKE 이후에는 불가능
INSERT INTO KH.DEPARTMENT
VALUES('D0', '개발부', 'L2');

ROLLBACK;