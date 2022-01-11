// ▼ 만약 html 파일에서 script 가 맨위에 있었으면 동작 안할 것임
//   : 그려지기 전에 접근하려고 하기 때문
//     따라서 DOM 요소가 모두 다 로드 된 후에 실행되어야 함
// $('#id1').css('color', 'red');


$(document).ready(function() {
    // 1. 아이디 선택자
    // 1) 순수한 JavaScript를 활용한 방식
    //    : 요소의 속성을 통해서 접근 후 수정 
    //      ▷ 새로고침을 빠르게 누르면 ID1 이 검정색이었다가 빨간색으로 바뀜
    //      ▷ DOM 이 모두 로드된 후 스타일이 적용된다는 뜻
    let id1 = document.getElementById('id1');
    
    id1.style.color = 'red';

    // 2) jQuery를 활용한 방식
    //    : 메소드를 통해 접근 후 수정
    let id2 = $('#id2');
    
    id2.css('color', 'hotpink');
    
    

    // 2. 태그 선택자
    // 1) 순수한 JavaScript를 활용한 방식
    // let p = document.getElementsByTagName('p'); // 요소를 담은 배열 객체 리턴
    
    // ▼ 배열이기 때문에, 반복문을 사용해서 각 요소에 접근함
    // for (const index in p) {
        //     p[index].style.color = 'beige';
        //     p[index].style.backgroundColor = 'tomato';
    // }
        
    // 2) jQuery를 활용한 방식
    let h5 = $('h5');
    
    h5.css('color', 'white');
    // h5.css('background-color', 'yellowgreen');  // 카멜케이스 안해도 적용은 됨
    h5.css('backgroundColor', 'yellowgreen');
    
    
    
    // 3. 클래스 선택자
    // 1) 순수한 JavaScript를 활용한 방식
    let items = document.getElementsByClassName('item');  // 요소를 담은 배열 객체 리턴

    for (let index  = 0; index < items.length; index++) {
        items[index].style.color = 'orange';
    }
    
    // 2) jQuery를 활용한 방식
    $('.item').css('background-color', 'yellow');
});