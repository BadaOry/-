// window 객체
// 1) window.open()
let btn1 = document.getElementById('btn1');

btn1.addEventListener('click', () => {
    // window.open();       // 새 탭

    // window.open('https://www.naver.com');

    // window.open('https://www.naver.com', '네이버');
    
    // ▼ 콘솔에 입력하면, 새 탭이나 창을 열지 않고
    //   naver를 띄워준 '네이버' 창에서 구글이 열림
    // window.open('https://www.google.com', '네이버');  

    // ▼ 특성의 경우 브라우저마다 다르게 동작하므로
    //   정상적으로 동작하는지는 꼭 확인해야 함
    window.open('https://www.naver.com', '네이버', 'width=500', 'height=600', 'resizable=no');  
});

// 2) window.setTimeout()
let btn2 = document.getElementById('btn2');

btn2.addEventListener('click', () => {
    let timerId = 0;
    // ▼ 윈도우 창을 띄우고, 그 새로운 윈도우 창 객체에 대한 정보를 전달해주는 변수 
    let newWindow = window.open();

    // ▼ window 객체를 넘겨주고, 오픈되는 새 창에서 alert 주기
    newWindow.alert('3초 후 이 페이지는 종료됩니다.');

    // ▼ 일정 시간 후에 콜백 함수 한 번 실행
    //    * alert 는 흐름을 멈춤
    // window.setTimeout(() => {
    //     newWindow.close();
    // }, 3000);

    // ▼ setTimeout() 실행이 완료되면 (= 타이머 설정이 완료되면)
    //   timeId 값을 넘겨줄것임
    timerId = window.setTimeout(() => {
                    newWindow.close();
              }, 3000);

    console.log(timerId);

    // ▼ 이거 적용하면 3초 후에 닫히는거 적용 안됨
    // clearTimeout(timerId);

    // ▼ opener : 앞에서 열린 페이지 (여기서는 BOM  페이지)
    // newWindow.opener.close();
});

// 3) window.setInterval()
let timerId = 0;
let btnStart = document.getElementById('btnStart');
let btnStop = document.getElementById('btnStop');

btnStart.addEventListener('click', () => {
    let area = document.getElementById('area1');

    // ▼ 일정 시간마다 콜백 함수를 반복 실행
    //   그리고 setInterval() 실행이 완료되면 timeId 값을 넘겨줄것임
    timerId = window.setInterval(() => {
            let date = new Date();

            // ▼ 1초마다 갱신되어 시간이 표시되는 타이머가 짜잔 !
            area.innerHTML = `<span id='timer'>${date.getHours()} : ${date.getMinutes()} : <span id='second'>${date.getSeconds()}`
            }, 1000);
});


btnStop.addEventListener('clcik', () => {
    let area = document.getElementById('area1');

    console.log(timerId);

    // ▼ 이거 적용하면 setInterval 적용 안됨
    window.clearInterval(timerId);

    // ▼ clearInterval 후 박스를 공란으로 만들 것임
    area.innerHTML = '';
});




// BOM
// 1) location 객체
let btn3 = document.getElementById('btn3');

btn3.addEventListener('click', () => {
    let area = document.getElementById('area2');

    area.innerHTML = '<h4>location 객체</h4>';

    // ▼ location 객체의 속성과 속성값 출력
    for (const key in location) {
        area.innerHTML += `<b>key</b> : ${key} <br> <b>value</b> : ${location[key]}<br><br>`;
        }
});

// 2) history 객체
//    는 html 파일에 내용이 있습니다.

// 3) navigator 객체
let btn4 = document.getElementById('btn4');

btn4.addEventListener('click', () => {
    let area = document.getElementById('area3');

    console.log(navigator);

    area.innerHTML = '<h4>navigator 객체</h4>'

    // navigator 객체의 속성과 속성값 출력
    for (const key in navigator) {
        area.innerHTML += `<b>key</b> : ${key} <br> <b>value</b> : ${navigator[key]}<br><br>`;
        }
});

// 4) screen 객체
let btn5 = document.getElementById('btn5');

btn5.addEventListener('click', () => {
    let area = document.getElementById('area4'); 
    
    // screen 객체의 속성과 속성값 출력
    for (const key in screen) {
        area.innerHTML += `<b>key</b> : ${key} <br> <b>value</b> : ${screen[key]}<br><br>`;
        }
});



















