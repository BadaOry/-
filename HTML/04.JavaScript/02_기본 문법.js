// 1. 변수 테스트
// 전역 변수 선언

str1 = '전역변수';
var str2 = 'var 전역변수';
// ▲ 함수 외부에 선언한 변수는 var를 붙여도 전역변수임

// onload : JavaScript 에서 페이지가 모두 로드되면 
//          자동으로 실행 되는 함수를 구현시 사용 (이벤트의 일종)
window.onload = function () {
    // console.log(str3); // undefined : 선언하기 전이라서

    var str1 = '지역변수 1';
    var str3 = '지역변수 2';
    var str4; // 초기화 안한 지역변수

    console.log(str1);         // 지역변수 1
    console.log(this.str1);    // 전역변수
    console.log(window.str1);  // 전역변수

    console.log(str2);         // var 전역변수
    console.log(this.str2);    // var 전역변수
    console.log(window.str2);  // var 전역변수
    
    console.log(str3);         // 지역변수 2
    console.log(this.str3);    // undifeind 
    console.log(window.str3);  // undefiend

    console.log(str4);         // undefined :초기화 안해서 

    console.log('---------------------------------------------');
    // var, let, const
    // 1) 중복선언

    // 1-1) 중복선언 - var
    var num = 0;
    console.log(num);       // 0

    var num = 10;
    console.log(num);       // 10

    var num = 20;
    console.log(num);       // 20

    // 1-2) 중복선언 - let
    let num2 = 10;
    console.log(num2);      // 10
    
    // ▼ 에러 발생 : Uncaught SyntaxError: Identifier 'num2' has already been declared
    // let num2 = 20;
    // console.log(num2);
    
    // 1-3) 중복선언 - const
    const num3 = 10;
    console.log(num3);
    
    // ▼ 에러 발생 : Uncaught SyntaxError: Identifier 'num3' has already been declared
    // const num3 = 20;
    // console.log(num3);

    // ▼ 에러 발생 : Uncaught TypeError: Assignment to constant variable.
    //              ▷ 값의 재할당이 불가능함
    // num3 = 20; 

    console.log('---------------------------------------------');

    // 2) 유효 범위 (스코프)
    // 2-1) 유효 범위 - var
    //    : 함수 안에서 var 키워드로 선언된 변수는 "함수" 유효 범위를 가짐
    if(true) {
        var num4 = 10;

        console.log(num4);
    }

    // ▼ 함수 전체에서 사용 가능
    console.log(num4);

    // 2-2) 유효 범위 - let, const
    //    : 함수 안에서 let, const 키워드로 선언된 변수는 "블록" 유효 범위를 가짐
    if(true) {
        let num5 = 20;
        const num6 = 30;
    }
    // ▼ 에러 발생 : Uncaught ReferenceError: num5 is not defined
    // console.log(num5);

    // ▼ 에러 발생 : Uncaught ReferenceError: num6 is not defined
    // console.log(num6);
}


// ---------------------------------------------
// 2. 자료형 테스트
function typeTest() {
    // 객체
    let name = '이왱도';                      // 문자열
    let age = 13;                            // 숫자
    let height = 30;                         // 숫자
    let check = false;                       // Boolean
    let hobbies = ['축구', '야구', '농구'];   // 배열
    let user = {
        name : '왱도',
        age : 13,
        height : 30,
        id : 'angdoo',
        // hobbies : ['축구', '야구', '농구']
        // hobbies : hobbies
        hobbies
    };
    // 함수
    let testFunction = function(num1, num2) {
        console.log(num1, num2);    // undifined : 초기화를 안했기 때문 
        alert(num1 + num2);         // NaN 팝업 : 초기화를 안했기 때문
    };
    
    // testFunction(10, 20);           // 30을 출력하는 팝업

    // testFunction();                 // NaN 팝업 : 매개값이 없기 때문

    // ▼ 내용을 콘솔에 출력한 것
    // console.log(hobbies);
    // console.log(user);
    // console.log(testFunction());

    let area = document.getElementById('area1');

    // ■ 연산자 typeof()
    // : 값의 자료형을 확인하는 연산자로서, 
    //   변수 선언 시 자료형을 지정하지 않기 때문에
    //   변수명 만으로는 데이터의 자료형을 알 수 없음
    //   ▷ 따라서 변수의 자료형을 확인하려면 typeof() 연산자를 사용해야 함
    area.innerHTML = '<h4>안녕하세요~!</h4>';
    area.innerHTML += `${name}, ${typeof(name)} <br>`;
    area.innerHTML += `${age}, ${typeof(age)} <br>`;
    area.innerHTML += `${height}, ${typeof(height)} <br>`;
    area.innerHTML += `${check}, ${typeof(check)} <br>`;
    area.innerHTML += `${hobbies}, ${typeof(hobbies)} <br>`;
    area.innerHTML += `${user.id}, ${typeof(user)} <br>`;
    area.innerHTML += `${testFunction()}, ${typeof(testFunction())} <br>`;
}

function opTest() {
    // Infinity : 양의 무한대
    // -Infinity : 음의 무한대
    // NaN : 산술 연산 불가
    let num1 = 10 / 0;
    let num2 = 10 / 'a';
    let area = document.getElementById('area2');

    area.innerHTML = `10 / 0 : ${num1} <br>`                             // Infinity
    area.innerHTML += `10 / a : ${num2} <br>`                            // NaN
    area.innerHTML += `num1 == Infinity :  ${num1 === Infinity} <br>`    // true
    area.innerHTML += `isFinite(num1) : ${isFinite(num1)} <br>`          // ◁ 자바스크립트 내장함수
    // area.innerHTML += `num2 == NaN :  ${num2 === NaN} <br>`           // 이렇게 쓰면 안됨. isNaN으로 판별해야 함
    area.innerHTML += `isNaN(num2) : ${isNaN(num2)} <br>`                // ◁ 자바스크립트 내장함수
}




// ---------------------------------------------
// 3. 데이터 형변환
// 1) 문자열과 숫자의 '+' 연산
//    : + 는 문자열을 이어주는 연산과 산술 연산 두가지 역할을 함
//      -, /, *은 산술 연산밖에 없어서 산술 연산을 하고, 아니면 NaN 리턴
function testPlus() {
    let test1 = 7 + 7;            // 14
    let test2 = 7 + '7';          // '77'
    let test3 = '7' + 7;          // '77'
    let test4 = '7' + '7';        // '77'
    let test5 = 7 + 7 + '7';      // '147'
    let test6 = 7 + '7' + 7;      // '777'
    let test7 = '7' + 7 + 7;      // '777' (문자+숫자=문자 ▷ 문자+숫자= 문자 ▶ 문자)
    let test8 = '7' + (7 + 7);    // '714'
    let test9 = 7 * '7';          // 49
    let test10 = '7' - 3 ;        // 4
    let test11 = 4 / '2' + 3;     // 5
    let test12 = '3' * '7';       // 21
    let test13 = '3' * 'a';       // NaN
    let area = document.getElementById('area3');

    area.innerHTML = `test1 : ${test1} <br>`;
    area.innerHTML += `test2 : ${test2} <br>`;
    area.innerHTML += `test3 : ${test3} <br>`;
    area.innerHTML += `test4 : ${test4} <br>`;
    area.innerHTML += `test5 : ${test5} <br>`;
    area.innerHTML += `test6 : ${test6} <br>`;
    area.innerHTML += `test7 : ${test7} <br>`;
    area.innerHTML += `test8 : ${test8} <br>`;
    area.innerHTML += `test9 : ${test9} <br>`;
    area.innerHTML += `test10 : ${test10} <br>`;
    area.innerHTML += `test11 : ${test11} <br>`;
    area.innerHTML += `test12 : ${test12} <br>`;
    area.innerHTML += `test13 : ${test13} <br>`;
}

function castingTest() {
    let area = document.getElementById('area4');

    area.innerHTML = `${2 + '3'} <br>`;                    // '23'
    area.innerHTML += `${2 + Number('3')} <br>`;           // 5
    area.innerHTML += `${String(2) + Number('3')} <br>`;   // '23'
    area.innerHTML += `${2 + parseInt('3')} <br>`;         // 5
    area.innerHTML += `${2 + parseFloat('3')} <br>`;       // 5
    area.innerHTML += `${parseInt('0xff', 16)} <br>`;      // 255
    
}


// ---------------------------------------------
// 4. 연산자 테스트
function opTest2() {
    let area =  document.getElementById('area5');

    area.innerHTML = '"==" 연산자 테스트 <br>';
    area.innerHTML += `'7' == 7 : ${'7' == 7} <br>`;               // true
    area.innerHTML += `'7' == '7' : ${'7' == '7'} <br>`;           // true
    area.innerHTML += `7 == 7 : ${7 == 7} <br>`;                   // true
    area.innerHTML += `'7' != 8 : ${'7' != 8} <br>`;               // true
    area.innerHTML += `'7' != '8' : ${'7' != '8'} <br>`;           // true
    area.innerHTML += `7 != 8 : ${7 != 8} <br>`;                   // true

    area.innerHTML += '<br>';

    area.innerHTML += '"===" 연산자 테스트 <br>';
    area.innerHTML += `'7' === 7 : ${'7' === 7} <br>`;             // false
    area.innerHTML += `'7' === '7' : ${'7' === '7'} <br>`;         // true
    area.innerHTML += `7 === 7 : ${7 === 7} <br>`;                 // true
    area.innerHTML += `'7' !== 8 : ${'7' !== 8} <br>`;             // true
    area.innerHTML += `'7' !== '8' : ${'7' !== '8'} <br>`;         // true
    area.innerHTML += `7 !== 8 : ${7 !== 8} <br>`;                 // true
}

// ---------------------------------------------
// 5. 제어문 테스트
function forInTest() {
    let result = '';
    // let array = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
    let array = [10, 9, 8, 7, 6, 5, 4, 3, 2, 1];

    // ▼ 반복 변수에 array의 index를 순서대로 i 에 담아서 반복
    //   10번 반복할거고, 반복할 때 마다 i에 인덱스가 담길 것임
    for (const i in array) {
        // console.log(index);
        console.log(array[i]);

        result += array[i] + ' ';
    }

    alert(result);
}