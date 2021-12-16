// 함수 선언
// 1) 선언적 함수
function test1() {
    alert('test1() 함수 실행!!!');
}

// 2) 익명 함수
let btn1 = document.getElementById('btn1');

btn1.addEventListener('click', function() {
    alert('익명 함수 실행 (이벤트 핸들러를 통해 실행)');
});

// 3) 스스로 실행하는 함수
(
    function (a, b) {
        document.getElementById('p1').innerHTML = `a : ${a}, b : ${b} (자동으로 혼자 실행)`;
    } /*(10, 20)*/
) (30, 70);


// 함수의 매개변수
// 1) 매개변수
let btn2 = document.getElementById('btn2');
                            // ▼ 이름이 없는 익명함수
btn2.addEventListener('click', (e) => {
    // ▼ [ex1] 매개변수의 개수 만큼 값을 넘기는게 일반적
    // argTest('안녕하세요');

    // ▼ [ex2] 매개변수의 개수보다 작은 개수의 값을 넘기는 경우
    //   : 에러가 발생하지는 않고 undefined 출력
    //     혹은 매개변수 기본값을 지정하면 기본값을 출력 (ES6부터 적용)
    argTest();   

    // ▼ [ex3] 매개변수의 개수보다 많은 개수의 값을 넘기는 경우
    //   : 에러가 발생하지는 않고 맨 앞의 '안녕하세요' 출력
    // argTest('안녕하세요', '반갑습니다');

    // ▼ 윈도우 객체 출력
    console.log(this);

    // ▼ btn2 가져오기 ( + 매개변수에 e 넣어야함)
    //   : 스타일 바꾸기, 텍스트 변경 등 할 수 있음
    // console.log(e.target);

    // ▼ Pointer Event 를 출력할 것임 (이벤트에 대한 정보)
    console.log(e);

    // ▼ 화살표 함수는 arguments 를 생성하지 않으므로,
    //   arguments를 확인하고 싶으면 이름이 있는 함수로 조회할 것
    // console.log(arguments);
});

// ▼ 전달되는 매개변수가 없는 경우,
//   매개변수 기본값을 지정하면 기본값을 출력 (ES6부터 적용)
function argTest(value = '매개값 없음') {
    alert(value);
}

// 2) arguments
let btn3 = document.getElementById('btn3');

btn3.addEventListener('click', () => {
    let result = 0;
    result = sum(11, 22, 33, 44);
    // result = sum(125, 600);

    alert(`result : ${result}`);
});

function sum() {
    result = 0;
    
    console.log(arguments);
    console.log(arguments.length);
    console.log(typeof(arguments));

    // ▼ 가변 매개변수 모두 더하려면, 갯수(=argumnets.length) 만큼 반복문을 돌리면 됨
    for (let i = 0; i < arguments.length; i++) {
        result += arguments[i];
    }
        return result;
}

// 3) 매개변수에 this 요소 전달하기
function thisTest(element) {
    console.log(this);      // window 객체 (함수, 전역번수는 window에 포함되기 때문)
    console.log(element);   // 이벤트 발생했을 때의 이벤트가 발생한 요소 (여기서는 버튼)
}

// 함수의 리턴
// 1) 일반적인 값 리턴
function returnTest() {
    let random = ran();

    alert(`random : ${random}`);
}   

function ran() {
    
    // ▼ random은 0 ~ 1 사이의 숫자를 출력하고
    //   1 ~ 100 의 값을 뽑고싶어서 조작을 함
    return parseInt(Math.random() * 100 + 1);
}

// 2) 익명함수를 리턴하는 함수
let btn4 = document.getElementById('btn4');
btn4.addEventListener('click', () => {
    // 리턴값을 받아오는 방법 1 : 변수에 함수를 담음
    let func = returnTest2();
    
    func();

    // 리턴값을 받아오는 방법 2 : 리턴되는 함수를 바로 실행시키는 구문
    // returnTest2()();

});

function returnTest2() {
    // 클로저
    // : 내부함수가 사용하는 외부함수의 지역변수는
    //   내부함수가 소멸할 때 까지 소멸되지 않는 특성
    //   (여기서 내부함수는 function(), 외부함수의 지역변수는 userName)
    //   ▷ 원래 우리가 배웠던대로 라면, 
    //      returnTest2 가 한 번 호출되면 그 이후로는 userName 변수를 쓸 수 없음
    //      (유효 범위가 블록 범위이기 때문)
    let userName = document.getElementById.length('userName').value;

    return function() {
        alert(userName + '님 환영합니다.');
    }
}

// 내장 함수
// 1) eval()
let btn5 = document.getElementById('btn5');

btn5.addEventListener('click', () => {
    let p2 = document.getElementById('p2');
    let calc = document.getElementById('calc');

    p2.innerHTML += `실제 입력된 값 : ${calc.value} <br>`;
    p2.innerHTML += `eval() 호출 후  : ${eval(calc.value)} <br>`;
});







