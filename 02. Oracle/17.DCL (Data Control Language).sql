/*--------------------------------
       <DCL (Data Control Language)>
          계정에게 시스템 권한 또는 객체에 대한 접근 권한을 
          부여(GRANT)하거나 회수(REVOKE)하는 언어
          
          시스템 권한의 종류
             - CREATE SESSION   : 데이터베이스에 접속할 수 있는 권한
             - CREATE TABLE     : 테이블을 생성할 수 있는 권한
             - CREATE VIEW      : 뷰를 생성할 수 있는 권한
             - CREATE SEQUENCE  : 시퀀스를 생성할 수 있는 권한
             - CREATE USER      : 계정을 생성할 수 있는 권한
             ...
          
          [표현법]
             GRANT 권한1, 권한2, .. TO 사용자계정명;
             REVOKE 권한1, 권한2, .. FROM 사용자계정명;
*/--------------------------------
-- 1. 계정 생성
CREATE USER SAMPLE IDENTIFIED BY SAMPLE;

-- 2. 계정에 접속하기 위해 CREATE SESSION 권한 부혀
GRANT CREATE SESSION TO SAMPLE;

-- 3. 계정에서 테이블을 생성할 수 있는 CREATE TABLE 권한 부여
GRANT CREATE TABLE TO SAMPLE;

-- 4. 테이블 스페이스 할당 : 테이블, 뷰, 인덱스 등 객체들이 저장되는 공간
ALTER USER SAMPLE QUOTA 2M ON SYSTEM;


/*--------------------------------
       객체 권한
          특정 객체를 조작할 수 있는 권한
       권한 종류     설정 객체
       SELECT       TABLE, VIEW, SEQUENCE
       INSERT       TABLE, VIEW
       UPDATE       TABLE, VIEW
       DELETE       TABLE, VIEW
       ALTER        TABLE, SEQUENCE
       ...
       
       [표현법]
          GRANT 권한종류 ON 특정 객체 TO 사용자계정;
*/--------------------------------
-- 5. KH 계정의 테이블들을 조회할 수 있는 권한 부여
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;
GRANT SELECT ON KH.DEPARTMENT TO SAMPLE;

-- 6. KH 계정의 DEPARTMENT 테이블에 데이터를 삽입할 수 있는 권한 부여;
GRANT INSERT ON KH.DEPARTMENT TO SAMPLE;

-- 7. KH 계정의 DEPARTMENT 테이블에 데이터를 삽입할 수 있는 권한을 뺏음;
REVOKE INSERT ON KH.DEPARTMENT FROM SAMPLE;