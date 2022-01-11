/*--------------------------------
    <SEQUENCE>
       정수값을 순차적으로 생성하는 역할을 하는 객체
       
       [표현법]
          CREATE SEQUENCE 시퀀스명
          [START WITH 숫자]
          [INCREMENT BY 숫자]
          [MAXVALUE 숫자]
          [MINVALUE 숫자]
          [CYCLE | NOCYCLE]
          [CACHE 바이트크기 | NOCACHE]; (기본값 20 바이트)
          
       [사용 구문]
          시퀀스명.CURRVAL : 현재 시퀀스의 값
          시퀀스명.NEXTVAL : 시퀀스 값을 증가시키고 증가된 시퀀스의 값
                           (기존 시퀀스 값에서 INCREMENT 값 만큼 증가된 값)
          
       * 캐시메모리
         - 미리 다음 값들을 생성해서 저장해 둠
         - 매번 호출할 때 마다 새로 생성을 하는 것이 아니고,
           캐시 메모리 공간에 미리 생성된 값들을 사용하는 것
*/-------------------------------- 

-- 1. 실험용시퀀스 생성
CREATE SEQUENCE SEQ_EMPNO
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;

-- 2. 현재 계정이 가지고 있는 시퀀스들에 대한 정보를 조회하는 데이터 딕셔너리
SELECT * FROM USER_SEQUENCES;

-- 3. 시퀀스 조회
SELECT SEQ_EMPNO.CURRVAL FROM DUAL;
-- △ 에러 : NEXTVAL을 한 번이라도 수행하지 않는 이상 CURRVAL을 가져올 수 없음
--          왜냐하면 CURRVAL는 마지막으로 수행된 NEXTVAL값을 저장해서 보여주는 값이기 때문

SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --  300
SELECT SEQ_EMPNO.CURRVAL FROM DUAL; --  300
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --  305
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; --  310
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; 
-- △ 에러 : 당연함. 지정한 MAXVALUE 값(310)을 넘었기 때문
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL; 
-- △ 에러 : 당연함. 지정한 MAXVALUE 값(310)을 넘었기 때문
SELECT SEQ_EMPNO.CURRVAL FROM DUAL; --  `310

-- 4. 현재 계정이 가지고 있는 시퀀스들에 대한 정보를 조회하는 데이터 딕셔너리
SELECT * FROM USER_SEQUENCES;


/*--------------------------------
    <SEQUENCE 수정>
       [표현법]
          ALTER SEQUENCE 시퀀스명
          [START WITH 숫자]
          [INCREMENT BY 숫자]
          [MAXVALUE 숫자]
          [MINVALUE 숫자]
          [CYCLE | NOCYCLE]
          [CACHE 바이트크기 | NOCACHE]; (기본값 20 바이트)
          
       - START WITH는 변경이 불가능함.
         즉, 재설정하고 싶다면 기존 시퀀스를 삭제 후 재생성해야 함.
*/-------------------------------- 

-- 1. 아까 만든 실험용 시퀀스 수정
ALTER SEQUENCE SEQ_EMPNO
-- START WITH 200 
-- △ 에러 : 구문 오류 발생
INCREMENT BY 10
MAXVALUE 400;

-- 2. 현재 계정이 가지고 있는 시퀀스들에 대한 정보를 조회하는 데이터 딕셔너리
--    : INCREMENT BY 와 MAXVALUE가 잘 수정되었음
SELECT * FROM USER_SEQUENCES;

-- 3. 시퀀스 조회
SELECT SEQ_EMPNO.CURRVAL FROM DUAL;  --  310
SELECT SEQ_EMPNO.NEXTVAL FROM DUAL;  --  320


/*--------------------------------
    <SEQUENCE 삭제>
       DROP SEQUENCE 시퀀스명;
*/-------------------------------- 

DROP SEQUENCE SEQ_EMPNO;

/*--------------------------------
*/-------------------------------- 
-- 1. 매번 새로운 사번이 발생되는 시퀀스 생성
CREATE SEQUENCE SEQ_EID
START WITH 910;

-- ★2. 매번 새로운 사번이 발생되는 시퀀스 사용
INSERT INTO EMPLOYEE
VALUES(SEQ_EID.NEXTVAL, '홍길동', '666666-6666666', 'HONG@OR.KR', 
        '01000001111', 'D2', 'J2', 5000000, 0.1, NULL, SYSDATE, NULL, DEFAULT);
 
INSERT INTO EMPLOYEE       
VALUES(SEQ_EID.NEXTVAL, '도깨비', '666666-6666666', 'HONG@OR.KR', 
        '01000001111', 'D2', 'J2', 5000000, 0.1, NULL, SYSDATE, NULL, DEFAULT);
        
ROLLBACK;



