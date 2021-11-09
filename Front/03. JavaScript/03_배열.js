// 배열 테스트
function test1() {
    let area = document.getElementById('area1');
    let arr = [
        '무닌수',          // 문자
        20,                // 숫자
        true,              // 논리값
        [1, 2, 3, 4],      // 배열
        function() {       // 함수
            return 1;
        },
        {}                 // 객체
    ];

    // ▼ 대괄호 []에 묶여서 우리가 입력한 값이 콘솔에 찍힘
    console.log(arr);

    area.innerHTML = arr;
}

// 배열 선언 및 초기화
function test2() {
    let arr1 = new Array();
    let arr2 = new Array(3);
    let arr3 = new Array('빨강', '파랑', '노랑', '초록');
    let arr4 = new Array('Java', 'Oracle', 'html5', 'css3', 'JavaScript');
    let area = document.getElementById('area2');

    // ▼ arr1의 길이를 지정하지 않아도 추가해줌
    //   Java 였으면 OutOfBoundException 에러가 났을 것임
    arr1[0] = '귤';
    arr1[1] = '사과';
    arr1[2] = '딸기';

    area.innerHTML = `arr1에 값 대입 : [ ${arr1} ]<br> arr1.length : ${arr1.length}`;
    area.innerHTML += '<br><br>';

    // ▼ arr2의 길이를 지정했어도, 
    // 갯수를 초과하면 length를 갱신하여 추가해줌
    arr2[0] ='자동차';
    arr2[1] ='비행기';
    arr2[2] ='기차';
    arr2[3] ='배 (타는 배)';
    
    area.innerHTML += `arr2에 값 대입 : [ ${arr2} ]<br> arr2.length : ${arr2.length}`;
    area.innerHTML += '<br><br>';

    area.innerHTML += `arr3에 값 대입 : [ ${arr3} ]<br> arr3.length : ${arr3.length}`;
    area.innerHTML += '<br><br>';

    area.innerHTML += `arr4에 값 대입 : [ ${arr4} ]<br> arr4.length : ${arr4.length}`;
    area.innerHTML += '<br><br>';

    console.log(arr1);  // ▷ []
    console.log(arr2);  // ▷ (3) [비어있음 x 3]
}

// 배열 메소드 테스트
// 1) indexOf()
function test3() {
    let arr = ['귤', '복숭아', '딸기', '맹고', '포도'];
    let area = document.getElementById('area3');

    area.innerHTML = `arr : [ ${arr} ] <br>`;
    area.innerHTML += `복숭아가 있는 배열의 인덱스 :  ${arr.indexOf('복숭아')}  <br>`;
    area.innerHTML += `맹고가 있는 배열의 인덱스 : ${arr.indexOf('맹고')}  <br>`;
    // ▼ 없는 요소를 찾으면 -1 리턴
    area.innerHTML += `수박이 있는 배열의 인덱스 : ${arr.indexOf('수박')}  <br>`;
}

// 2) concat()
function test4() {
    let arr1 = ['귤', '복숭아', '딸기', '맹고', '포도'];    
    let arr2 = ['메론', '키위', '두리안', '망고스틴', '리치', '용안'];
    let area = document.getElementById('area4');

    area.innerHTML = `arr1 : [ ${arr1} ] <br>`;
    area.innerHTML += `arr2 : [ ${arr2} ] <br><br>`;

    // ▼ concat 메소드는 원본 배열에는 영향을 주지 않음
    area.innerHTML += `arr1 기준으로 배열을 결합 : [ ${arr1.concat(arr2)} ] <br>`;
    // ▼ 원본 배열 arr1은 변하지 않은 것 확인 가능
    area.innerHTML += `arr1 : [ ${arr1} ] <br><br>`;

    // ▼ concat 메소드는 원본 배열에는 영향을 주지 않음
    //   마찬가지로 원본 배열 arr2은 변하지 않은 것 확인 가능
    area.innerHTML += `arr2 기준으로 배열을 결합 : [ ${arr2.concat(arr1)} ] <br>`;
    area.innerHTML += `arr2 : [ ${arr2} ] <br><br>`;

}


// 3) join()
function test5() {
    let arr = ['강남', '역삼', '선릉', '삼성'];
    let area = document.getElementById('area5');

    area.innerHTML = `arr : [ ${arr} ] <br><br>`;
    area.innerHTML += `arr의 자료형 : ${typeof(arr)} <br><br>`;
    area.innerHTML += `arr.join() : ${arr.join()} <br><br>`;
    // ▼ join 메소드에 파라미터를 넣으면 요소들 간의 구분자로 사용
    area.innerHTML += `arr.join('▶') : ${arr.join('▶')} <br><br>`;
    area.innerHTML += `arr.join()의 자료형 : ${typeof(arr.join())} <br><br>`;
}

// 4) reverse() 
function test6() {
    // ▼ Array.of() : es6부터 추가된 배열 생성 메소드
    let arr = Array.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10); 
    let area = document.getElementById('area6');

    area.innerHTML = `arr : [ ${arr} ] <br><br>`;
    // ▼ 원본 배열에 영향을 미치는 메소드
    area.innerHTML += `arr.reverse() : [ ${arr.reverse()} ] <br><br>`;
    // ▼ 원본 배열의 순서가 거꾸로 변한 것을 확인 가능
    area.innerHTML += `arr : [ ${arr} ] <br><br>`;
}

// 5) sort() 
function test7() {
    let arr1 = [10, 543, 34, 450, 123, 885];
    let arr2 = ['Cherry', 'Apple', 'Banana'];
    let area = document.getElementById('area7');

    area.innerHTML = `arr1 : [ ${arr1} ] <br>`;
    area.innerHTML += `arr2 : [ ${arr2} ] <br><br>`;
    // ▼ 원본 배열에 영향을 미치는 메소드
    area.innerHTML += `arr1.sort() : [ ${arr1.sort()} ] <br>`;
    area.innerHTML += `arr2.sort() : [ ${arr2.sort()} ] <br><br>`;
    // ▼ 원본 배열의 순서가 오름차순 순서로 변한 것을 확인 가능
    area.innerHTML += `arr1 : [ ${arr1} ] <br>`;
    area.innerHTML += `arr2 : [ ${arr2} ] <br><br>`;

    // ▼ sort() 메소드에 함수를 파라미터로 전달하면 정렬 기준 변경 가능
    /* 동작 원리
        함수에서 리턴하는 값을 기준으로
        0보다 작은값 리턴하면 첫번째 매개값이 두번째 매개값 전
        0보다 큰값 리턴하면 첫번째 매개값이 두번째 매개값의 후에 온다
        [ex] arr1.sort(function(left, right)) {
            return left - right;     // ▶ 오름차순 정렬
            return right - left;     // ▶ 내림차순 정렬
        } 
    */
    arr1.sort(function(left, right) {
        return left - right;                       // 오름차순 정렬
    });

    area.innerHTML += `arr1 오름차순으로 정렬 : [ ${arr1} ] <br>`;
    
    arr1.sort((left, right) => right - left);      // 내림차순 정렬                                    
    area.innerHTML += `arr1 내림차순으로 정렬 : [ ${arr1} ] <br><br>`;
    
    area.innerHTML += `arr2 내림차순으로 정렬 ( arr2.sort().reverse() ): [ ${arr2.sort().reverse()} ] <br>`;
}

// 6) push(), pop() 
function test8() {
    let arr = ['잇지', '엔시티', '방탄소년단', '소녀시대'];
    let area = document.getElementById('area8');
    
    area.innerHTML = `arr : [ ${arr} ] <br><br>`;
    arr.push('레드벨벳');   // push 후 요소의 개수 리턴
    area.innerHTML += `push 후의 arr : [ ${arr} ] <br><br>`;
    arr.push('박명수');     // push 후 요소의 개수 리턴
    area.innerHTML += `push 후의 arr : [ ${arr} ] <br><br>`;
    
    
    area.innerHTML += `arr.pop() : [ ${arr.pop()} ] <br><br>`;  // 반환값 : 맨 마지막 요소 (박명수)
    area.innerHTML += `pop 후의 arr : [ ${arr} ] <br><br>`;

    area.innerHTML += `arr.pop() : [ ${arr.pop()} ] <br><br>`;  // 반환값 : 맨 마지막 요소 (레드벨벳)
    area.innerHTML += `pop 후의 arr : [ ${arr} ] <br><br>`;
}

// 7) shift(), unshift() 
function test9() {
    let arr = ['잇지', '엔시티', '방탄소년단', '소녀시대', '아이유'];
    let area = document.getElementById('area9');

    area.innerHTML = `arr : [ ${arr} ] <br><br>`;
    arr.unshift('레드벨벳');   // unshift 후 요소의 개수 리턴
    area.innerHTML += `unshift 후의 arr : [ ${arr} ] <br><br>`;
    arr.unshift('박명수');     // unshift 후 요소의 개수 리턴
    area.innerHTML += `unshift 후의 arr : [ ${arr} ] <br><br>`;

    area.innerHTML += `arr.shift() : [ ${arr.shift()} ] <br><br>`;  // 반환값 : 맨 앞 요소 (박명수)
    area.innerHTML += `shift 후의 arr : [ ${arr} ] <br><br>`;

    area.innerHTML += `arr.shift() : [ ${arr.shift()} ] <br><br>`;  // 반환값 : 맨 앞 요소 (레드벨벳)
    area.innerHTML += `shift 후의 arr : [ ${arr} ] <br><br>`;
}


// 8) slice(), splice()
function test10() {
    let arr = ['Java', 'db', 'html', 'css', 'js'];
    let area = document.getElementById('area10');

    area.innerHTML = `arr : [ ${arr} ] <br><br>`;
    // ▼ begin 인덱스부터 end 인덱스를 미포함한 요소 까지를 리턴
    area.innerHTML += `arr.slice(2, 4) : [ ${arr.slice(2, 4) } ] <br><br>`; 
    // ▼  원본 배열에는 영향을 주지 않음
    area.innerHTML += `arr : [ ${arr} ] <br><br>`;
    
    // ▼ begin 인덱스부터 n 개의 요소를 잘라냄
    //   원본 배열에 영향을 미치는 메소드
    area.innerHTML += `arr.splice(2, 2) : [ ${arr.splice(2, 2)} ] <br><br>`; 
    area.innerHTML += `arr : [ ${arr} ] <br><br>`;

    // ▼ begin 인덱스부터 n 개의 요소를 잘라내고 그 자리에 추가값을 넣음
    //   원본 배열에 영향을 미치는 메소드
    area.innerHTML += `arr.splice(2, 0, [추가값]) : [ ${arr.splice(2, 0, 'spring')} ] <br><br>`; 
    area.innerHTML += `arr : [ ${arr} ] <br><br>`;
}
