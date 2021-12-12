// 텍스트 노드가 있는 요소 노드 생성
let btn1 = document.getElementById('btn1');

btn1.addEventListener('click', () => {
    // element 생성
    let title = document.createElement('h3');

    // 텍스트 노드 생성
    let textNode = document.createTextNode('안녕하세요~');

    // ▼ 안녕하세요를 h3 속에 넣음
    title.appendChild(textNode);

    // ▼ div에 'h3에 안녕하세요 넣은 것' 을 넣을 것임
    document.getElementById('area1').appendChild(title);
    // document.getElementById('area1').innerHtML = '<h3>안녕하세요~</h3>'; 

});

// 텍스트 노드가 없는 요소 노드 생성
let btn2 = document.getElementById('btn2');

btn2.addEventListener('click', () => {
    // element 생성
    let img = document.createElement('img');

    // 속성 지정
    img.src = '../resources/image/flower1.PNG';
    img.setAttribute('width', '150px');
    img.setAttribute('height', '100px');
    // ▼ 사용자가 필요한 속성 임의 지정
    img.setAttribute('myAttr', 'P123');

    document.getElementById('area2').appendChild(img);
    
    document.getElementById('area2').innerHTML +=
        '<img src="../resources/image/flower2.PNG" width="150px" height="100px" myAttr="P123">';
    });

// Node 객체 삭제
let btn3 = document.getElementById('btn3');

btn3.addEventListener('click', (event) => {
    // ▼ area1 지우기
    document.getElementById('area1').remove();

    // ▼ body 태그 내의 자식 노드 (area2) 지우기
    document.body.removeChild(document.getElementById('area2'));

    // ▼ 상위노드에게 요청하여 자식노드 (area3) 지우기
    //   : 여기서 event.target 은 btn3 의미
    event.target.parentNode.removeChild(document.getElementById('area3'));
});