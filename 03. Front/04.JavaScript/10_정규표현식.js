// 정규 표현식 객체 생성
let btn1 = document.getElementById('btn1');

btn1.addEventListener('click', () => {
    let area = document.getElementById('area1');

    // 생성자 함수를 이용한 정규 표현식 객체 생성
    // : 생성자의 매개값으로 패턴을 문자열(여기선 script)로 지정함
    let regExp = new RegExp('script');

    // 리터럴을 이용한 정규 표현식 객체 생성
    // : 패턴 양 쪽에 슬래시(/) 로 작성 (시작기호, 종료기호)
    let regExp2 = /script/;
    let str = 'javascript jqueryscript ajax';
    let str2 = 'java jquery ajax';

    console.log(regExp, typeof(regExp));
    console.log(regExp2, typeof(regExp2));


    /*
        RegExp 객체에서 제공하는 메소드
            객체.test(문자열) : 문자열에 정규식 패턴을 만족하는 값이 있으면 true
                                                                  없으면 false 리턴
            객체.exec(문자열) : 문자열에 정규식 패턴을 만족하는 값이 있으면
                                처음 매치된 문자열을 리턴함
            
        String 객체에서 정규 표현식 객체를 이용하는 메소드
            문자열.match(정규식) : 문자열에 정규식 패턴의 값과 일치하는 값을 리턴
            문자열.replace(정규식, 바꿀값) : 문자열에서 정규식 패턴의 값과 일치하는 부분을 바꿀값으로 변경
            문자열.saerch(정규식) : 문자열에서 정규식 패턴의 값과 일치하는 부분의 시작 인덱스 리턴
            문자열.split(정규식) : 문자열에서 정규식 패턴의 값과 일치하는 값을 구분자로하여 배열을 생성해 리턴
    */


    area.innerHTML = '<h3>정규 표현식 객체 생성</h3>';
    // RegExp 객체에서 제공하는 메소드 사용
    // ▼ str 문자 중 내가 지정한 패턴 ('script') 를 만족해서 true 반환
    area.innerHTML += `regExp.test(str) : ${regExp.test(str)} <br>`;   // true
    // ▼ str2 문자 중 내가 지정한 패턴 ('script') 를 만족하지 않아서 false 반환
    area.innerHTML += `regExp.test(str2) : ${regExp.test(str2)} <br>`; // false
    
    // ▼ str 문자 중 내가 지정한 패턴 ('script') 중 첫번재로 만족하는 패턴 반환
    area.innerHTML += `regExp.exec(str) : ${regExp.exec(str)} <br>`;   // script
    // ▼ str 문자 중 내가 지정한 패턴 ('script') 이 없으면 null 반환
    area.innerHTML += `regExp.exec(str2) : ${regExp.exec(str2)} <br>`; // null

    // String 객체에서 정규 표현식 객체를 이용하는 메소드
    area.innerHTML += `str.match(regExp) : ${str.match(regExp)} <br>`;
    area.innerHTML += `str.replace(regExp, '스크립트') : ${str.replace(regExp, '스크립트')} <br>`;
    area.innerHTML += `str.search(regExp) : ${str.search(regExp)} <br>`;
    area.innerHTML += `str.split(regExp) : ${str.split(regExp)} <br>`;

    console.log(str.split(regExp));
});


let btn2 = document.getElementById('btn2');

btn2.addEventListener('click', () => {
    let area = document.getElementById('area2');
    let str = 'JAvaScript jQuery Ajax';
    let regExp = /a/;
    let regExp1 = /a/i;
    let regExp2 = /a/g;
    let regExp3 = /a/ig; // 섞어 쓸 때, 순서는 상관없음
    let regExp4 = /a/gi; 
    
    area.innerHTML = '<h3>플래그 문자</h3>';
    
    // ▼ $& : 패턴을 만족하는 문자열을 가리킴
    area.innerHTML += `/a/ : ${str.replace(regExp, '($&)')} <br>`;
    // ▼ i : 대소문자 가리지않고 검색
    area.innerHTML += `/a/i : ${str.replace(regExp1, '($&)')} <br>`;
    area.innerHTML += `/a/g : ${str.replace(regExp2, '($&)')} <br>`;
    area.innerHTML += `/a/ig : ${str.replace(regExp3, '($&)')} <br>`;
    area.innerHTML += `/a/gi : ${str.replace(regExp4, '($&)')} <br>`;

    // let str2 = str.replace(regExp4, (match) => {
    //     return '+'+ match + '+';
    // });

    // console.log(str2);
});

// 메타 문자
let btn3 = document.getElementById('btn3');

btn3.addEventListener('click', () => {
    let regExp;
    let str = '';
    let area = document.getElementById('area3');

    // 반복 검색
    // - {m,n} : 앞선 패턴이 최소 m번, 최대 n 번 반복되는 문자열 의미
    // -   +   : 앞선 패턴이 최소 한 번 이상 반복되는 문자열 의미
    // -   ?   : 앞선 패턴이 최대 한 번 이상 반복되는 문자열 의미
    str = 'a aa aaa aaaa';
    regExp  = /a{1,2}/g; 

    area.innerHTML = '<h3>반복 검색</h3>';
    area.innerHTML += `/a{1,2}/g : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /a{3}/g;      // {3, 3} 과 같은 의미
    area.innerHTML += `/a{3}/g : ${str.replace(regExp, '($&)')} <br>`;

    regExp = /a{2,}/g;     // 앞선 패턴이 최소 2번 이상 만족하는 문자열 의미
    area.innerHTML += `/a{2,}/g : ${str.replace(regExp, '($&)')} <br>`;

    regExp = /a+/g;       // {1,} 과 같은 의미
    area.innerHTML += `/a+/g : ${str.replace(regExp, '($&)')} <br>`;

    regExp = /a?/g;       // {0,1} 과 같은 의미
    area.innerHTML += `/a+/g : ${str.replace(regExp, '($&)')} <br><br>`;

    // 앵커 문자
    // :문자열의 앞과 뒤를 구분해주는 정규 표현식
    // - ^ : 문자열의 시작 의미
    // - $ : 문자열의 마지막 의미
    str = 'Javascript\nJquery\nShellScript\nAjax';
    
    area.innerHTML += `<h3>문자열의 앞, 뒤 구분</h3>`;
    
    regExp = /^j/ig;      
    area.innerHTML += `/^j/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /^j/igm;      
    area.innerHTML += `/^j/igm : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /ipt$/igm;      
    area.innerHTML += `/ipt$/igm : ${str.replace(regExp, '($&)')} <br>`;
    
    // OR 검색
    // [...] : 대괄호 안의 문자 중 하나라도 존재할 경우
    str = 'Javascript Jquery Ajax';
    
    area.innerHTML += `<h3>OR 검색</h3>`;  

    regExp = /[aj]/ig;
    area.innerHTML += `/[aj]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[^aj]/igm;      // *주의 : [] 안의 ^는 not 의 의미를 가짐
    area.innerHTML += `/[^aj]/igm : ${str.replace(regExp, '($&)')} <br>`;
    
    str = 'Javascript\nJquery\nAjax';
    regExp = /^[aj]/igm;      // 이렇게 해야 aj 로 시작하는 문자를 찾을 수 있음
    area.innerHTML += `/^[aj]/igm : ${str.replace(regExp, '($&)')} <br>`;
    
    str = '123 Javascript,';
    regExp = /[a-z]/g;  // [] 안에서 -를 사용하여 범위 지정
    area.innerHTML += `/[a-z]/g : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[a-z]/ig;  // 대소문자 구분없이 다 () 해줌
    area.innerHTML += `/[a-z]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[A-Z]/ig;  // 대문자 A ~ Z 모두 () 해줌
    area.innerHTML += `[A-Z]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[0-9]/ig;  // 0 ~ 9 까지 () 해줌
    area.innerHTML += `[0-9]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[0-9A-Z]/ig;  // 0 ~ 9, A ~ Z 까지 () 해줌
    area.innerHTML += `/[0-9A-Z]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /[0-9A-Z]/ig;  // , 까지 () 해줌
    area.innerHTML += `//[0-9,A-Z]/ig : ${str.replace(regExp, '($&)')} <br>`;
    
    // 임의의 문자열 검색
    // - . : 임의의 문자 한 개를 의미하고, 문자 내용은 무엇이든 상관 없음
    area.innerHTML += `<h3>임의의 문자열 검색</h3>`;  

    regExp = /..../g;       // 공백을 포함해서 글자를 4개씩 묶어줌
    area.innerHTML += `/..../g : ${str.replace(regExp, '($&)')} <br>`;
});

// 추가 메타 문자
// - \d : 숫자 의미
// - \D : 숫자가 아닌 문자 의미
// - \w : 알파벳, 숫자, 언더 스코어(_) 의미
// - \W : 알파벳, 숫자, 언더 스코어(_)가 아닌 문자 의미
// - \s : 공백 문자 의미 (띄어쓰기, 탭, 줄바꿈)
// - \S : 공백 문자가 아닌 문자 의미 (띄어쓰기, 탭, 줄바꿈 제외)

let btn4 = document.getElementById('btn4');

btn4.addEventListener('click', () => {
    let regExp;
    let str = '';
    let area = document.getElementById('area4');
    
    str = 'A1 B2 C3 d_4 e:5 ` ffgg77--__-- \t가\n나\n다';
    
    area.innerHTML = `<h3>추가 메타 문자</h3>`;

    regExp = /\d/g;      // /[0-9/g 와 결과가 같음
    area.innerHTML += `/\\d/g : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /\D/g;      // /[^0-9]/g 와 결과가 같음
    area.innerHTML += `/\\D/g : ${str.replace(regExp, '($&)')} <br>`;
   
    regExp = /\w/g;      // /[a-zA-Z0-9_]/g 와 결과가 같음
    area.innerHTML += `/\\w/g : ${str.replace(regExp, '($&)')} <br>`;
    
    regExp = /\W/g;      // /[^a-zA-Z0-9_]/g 와 결과가 같음
    area.innerHTML += `/\\W/g : ${str.replace(regExp, '($&)')} <br>`;

    regExp = /\s/g;      // /[ \n\t]/g 와 결과가 같음 (*공백도 포함 가능)
    area.innerHTML += `/\\s/g : ${str.replace(regExp, '($&)')} <br>`;

    regExp = /\S/g;      // /[ \n\t]/g 와 결과가 같음 (*공백도 포함 가능)
    area.innerHTML += `/\\S/g : ${str.replace(regExp, '($&)')} <br>`;
});