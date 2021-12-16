// 이벤트 모델의 종류
// 1) 고전 이벤트 모델
let btn1 = document.getElementById('btn1');
let btn2 = document.getElementById('btn2');

btn1.onclick = () => {
    alert('btn1 이 클릭되었습니다.');
}

btn2.onclick = () => {
    alert('btn2이 클릭되면서, btn1 의 이벤트를 제거하였습니다.');

    // ▼ btn1 의 이벤트 제거
    btn1.onclick = null;
}

// 2) 인라인 이벤트 모델
function test1() {
    alert('안녕하세요. 만나서 반가워요');
}

// 3) 표준 이벤트 모델
let btn3 = document.getElementById('btn3');

// ▼ 함수 호출 : 반환해주는 리턴값이 없고,
//               click 이란 이벤트에도 아무런 지정을 하지 않는 것임
// btn3.addEventListener('click', clickEventHnadler());

// ▼ 콜백 함수를 함수로서 전달할때는 
//   함수에 대한 이름, 정보만 넘겨주면 됨 (레퍼런스 지정) 
btn3.addEventListener('click', clickEventHnadler);

btn3.addEventListener('mouseenter', (event) => {
    event.target.style.backgroundcColor = 'red';

    console.log(event);
});

btn3.addEventListener('mouseout', (event) => {
    event.target.style.backgroundcColor = 'green';

    console.dir(event);
});

// ▼ 리턴값 없음 : undifined 로 나옴
function clickEventHnadler() {
    alert('표준 이벤트 모델')
}

// 이벤트가 발생한 해당 요소 객체에 접근하는 방법
// 1) 고전 이벤트 방식
//    : this 사용 시 기본 함수와 애로우 펑션의 차이점에 주의해야함
let btn4 = document.getElementById('btn4');

// 1-1) 기본 함수의 경우
btn4.onclick = function(event) {
    console.log(event.target);
    console.log(window.event.target);
    // ▼ 함수 내부에서 btn4를 가리키는 this
    console.log(this);  
}

// 1-2) 애로우 펑션의 경우
btn4.onclick = (event) => {
    console.log(event.target);
    console.log(window.event.target);
    // console.log(this);              // window 객체 가리킴

}

// 2) 인라인 방식
function test2(event) {
    // ▼ 실행을 html 파일에서 하므로, 매개값으로 이벤트 객체를 가져올 수 없음
    //   : window.event 혹은 this를 html에서 넘겨줘야 함
    console.log(event); 
    console.log(window.event); 
     // ▼ 객체 내부에 있는 것이 아니므로 window 객체 가리킴
    console.log(this);             
}

// 3) 표준 이벤트 방식
//    : this 사용 시 기본 함수와 애로우 펑션의 차이점에 주의해야함
// 3-1) 기본 함수의 경우
btn5.onclick = function(event) {
    console.log(event.target);
    console.log(window.event.target);
    // ▼ 함수 내부에서 btn5를 가리키는 this
    console.log(this);  
}
let btn5 = document.getElementById('btn5');

// 3-2) 애로우 펑션의 경우
btn5.addEventListener('click', (event) => {
    console.log(event.target);     
    console.log(window.event.target); 
    // console.log(this);         // window 객체 가리킴     
});

// 태그별 기본적으로 가지고 있는 이벤트 제거
// 1) 기본 이벤트 제거 1
function test3() {
    let pass1 = document.getElementById('pass1').value;
    let pass2 = document.getElementById('pass2').value;

    console.log(pass1);
    console.log(pass2);
    if(pass1 !== pass2) {
        alert('비밀번호가 일치하지 않습니다');

        // ▼ pass1 !== pass2 인 경우 기본 이벤트(subimt)이 동작 안하도록 false를 리턴함
        return false;
    }

    alert('test3() 제출 완료');
}

// 2) 기본 이벤트 제거 2
let submit = document.getElementById('submit');

// submit.onclick = () => {
//     alert('아이디를 정상적으로 입력해주세요.');

//     return false;
// }

submit.addEventListener('click', (event) => {
    let userId = document.getElementById('userId').value;
    let regExp = /^[a-zA-Z0-9]{5, 12}$/;

    if(!regExp.test(userId)) {
        alert('아이디를 정상적으로 입력해주세요.');

        event.preventDefault();
    }

});