$(document).ready(() => {
    // 1. Content 관련 메소드
    //  1) html() 메소드

    // ▼ 컨텐츠 영역의 태그와 텍스트를 모두 가져옴   ▷  <a>네이버로</a>
    console.log(`$('#content1').html() : ${$('#content1').html()}`);

    // ▼ content1의 콘텐츠 영역의 태그와 텍스트를
    //   content2의 콘텐츠 영역에 넣어줄 것임       ▷ 네이버로 가 생김
    $('#content2').html($('#content1').html()); 
    
    // ▼ content2의 자손 중 a인 요소에 href 속성을 넣어줌
    $('#content2').children('a').attr('href', 'https://www.naver.com');

    // ▼ 테스트 1 만 가져옴 
    console.log(`$('.content').html() : ${$('.content').html()}`);

    // ▼ 콜백함수를 사용하여 content 클래스의 인덱스와 콘텐츠를 가져옴
    $('.content').html((index, content) => {
        console.log(index, content);

        // ▼ 원래 있던 내용들을 변경함              ▷ 인덱스 0, 내용 html() 테스트 1 ... 
        //   단, <h1>을 요소로 인식하여 적용함
        return `<h1>인덱스 ${index}, 내용 ${content}</h1>`;
    });
    
    // ▼ 원래 있던 내용들을 변경함
    //   단, 모든 content 클래스의 변경되는 내용은 매개값으로 통일됨
    // $('.content').html('ㅋㅋㅋㅋㅋㅋㅋㅋ');


    //  2) text() 메소드

    // ▼ 컨텐츠 영역의 문자열 정보만 가져옴     ▷ 네이버로
    console.log(`$('#content3').text() : ${$('#content3').text()}`);
   
    // ▼ content3의 콘텐츠 영역의 태그와 텍스트를
    //   content4의 콘텐츠 영역에 넣어줄 것임   ▷ 네이버로 가 생김
    $('#content4').text($('#content3').text());

    // ▼ content4의 내용을 변경함              ▷ <b>안녕</b>
    //$('#content4).text('<b>안녕</b>');

    // ▼ 모든 테스트가 출력됨 ▷ text() 테스트 1text() 테스트 2text() 테스트 3
    console.log(`$('.content2').text() : ${$('.content2').text()}`);
    
    // ▼ 콜백함수를 사용하여 content 클래스의 인덱스와 콘텐츠를 가져옴
    $('.content2').text((index, content) => {
        console.log(index, content);

        // ▼ 원래 있던 내용들을 변경함 
        //   단, <h1>을 요소로 인식하지 않고 문자열로 내보냄
        return `<h1>인덱스 ${index}, 내용 ${content}</h1>`;
    });




    // 2. 요소 생성 및 제거 메소드
    //  1) 요소 생성
    //     - 문자열로 요소를 생성하는 방법
    let p1 = '<p>문자열로 요소를 생성</p>';
    
    //     - 자바스크립트 DOM 메소드로 요소를 생성하는 방법
    let p2 = document.createElement('p');
    let textNode = document.createTextNode('DOM 메소드로 요소 생성');
    
    p2.appendChild(textNode);
    
    //     - jQquery로 요소를 생성하는 방법 1
    let p3 = $('<p>').text('jQuery로 요소를 생성 1');
    
    //     - jQquery로 요소를 생성하는 방법 2
    let p4 = $('<p>jQuery로 요소를 생성 2</p>');
    
    // ▼ p1, p2는 태그까지 포함하여 콘솔에 출력
    console.log(p1);    // ▷ <p>문자열로 요소를 생성</p>
    console.log(p2);    // ▷ <p>DOM 메소드로 요소 생성</p>
    console.log(p3);    // ▷ 0 : p
    console.log(p4);    // ▷ 0 : p

    // ▼ area1 영역에 p1, p2, p3, p4를 추가하여, 화면에 내용이 출력 됨
    $('#area1').append(p1, p2, p3, p4);


    //  2-1) 요소 추가 1
    //       - $(A).append(B) : A 요소의 자식 요소로서 B 요소를 뒷부분에 추가함
    // ▼ 첫 번째 A 뒤쪽에 B가 추가되어 AB로 변경됨
    $('#add1').append('<span>B</span>');
    
    //       - $(A).prepend(B) : A 요소의 자식 요소로서 B 요소를 앞부분에 추가함
    // ▼ 두 번째 A 앞쪽에 B가 추가되어 BA로 변경됨
    $('#add2').prepend('<span>B</span>');

    //       - $(A).after(B) : A 요소의 형제 요소로서 B 요소를 뒷부분에 추가함
    // ▼ 세 번째 A 다음에 형제요소로서 B가 뒷부분에 추가되어 A / B로 변경됨
    $('#add3').after('<span>B</span>');
    
    //       - $(A).before(B) : A 요소의 형제 요소로서 B 요소를 앞부분에 추가함
    // ▼ 네 번째 A 다음에 형제요소로서 B가 앞부분에 추가되어 B / A로 변경됨
    $('#add3').before('<span>B</span>');


    //  2-2) 요소 추가 2
    //       - $(A).appendTo(B) : A 요소를 B 요소의 자식 요소로서 뒷부분에 추가함
    // ▼ 첫 번째 A 뒤쪽에 B가 추가되어 AB로 변경됨
    $('<span>B</span>').appendTo('#add5');
    
    //       - $(A).prependTo(B) : A 요소를 B 요소의 자식 요소로서 앞부분에 추가함
    // ▼ 두 번째 A 앞쪽에 B가 추가되어 AB로 변경됨
    $('<span>B</span>').prependTo('#add6');

    //       - $(A).insertAfter(B) : A 요소를 B 요소의 형제 요소로서 뒷부분에 추가함
    // ▼ 세 번째 A 다음에 형제요소로서 B가 뒷부분에 추가되어 A / B로 변경됨
    $('<span>B</span>').insertAfter('#add7');

    //       - $(A).insertBefore(B) : A 요소를 B 요소의 형제 요소로서 앞부분에 추가함
    // ▼ 네 번째 A 다음에 형제요소로서 B가 뒷부분에 추가되어 A / B로 변경됨
    $('<span>B</span>').insertBefore('#add8');




    // 3. 요소 복제 메소드
    $('#item1').hover(
        // () => {
        //     // ▼ 선택된 요소에 클래스 속성값을 추가하는 메소드
        //     $('#item1').addClass('bg-hotpink');
        // }, 
        // () => {
        //     // ▼ 선택된 요소에 클래스 속성값을 제거하는 메소드
        //     $('#item1').removeClass('bg-hotpink');
        // }

        // ▼ hover에서 매개값으로 콜백함수를 하나만 넘기는 경우
        (event) => {  
            console.log(event);  //▷ mouseenter,mouseleave 콘솔에 찍힘
            // ▼ 선택된 요소의 클래스 속성값을 추가 또는 제거하는 메소드
            $(event.target).toggleClass('bg-hotpink');
        }
    );

    // 버튼 클릭 시 요소를 복제
    $('#btn1').click(() => {
        // ▼ clone 매개값으로 true를 넣어줘야 이벤트까지 복사 됨 (생략 시 false)
        let copyItem = $('#item1').clone(true);
        
        // ▼ clone-rsult 공간에 안녕(+이벤트)가 누를때마다 복사됨
        // $('#clone-result').append(copyItem);

        // ▼ conle-result 공간으로 item1 요소가 이동됨
        $('#clone-result').append($('#item1'));
    });

    


    // 4. 요소 제거 메소드
    $('#item2').hover(() => {
        $('#item2').toggleClass('bg-hotpink');
    });

    //  1) empty 테스트
    // - 요소.empty() : 선택된 요소의 자식 요소들을 제거함
    $('#empty').click(() => {
        // ▼ '잘가' 글씨를 제거함
        $('#item2').empty();

        // ▼ 연두색 박스를 제거함
        $('#remove-test').empty();
    });

    //  2) remove 테스트
    // - 요소.remove() : 선택된 요소를 제거 후 제거된 요소 리턴
    //                 단, 리턴되는 요소의 이벤트도 삭제됨
    $('#remove').click(() => {
        let el= $('#item2').remove();

        // ▼ 제거된 요소를 리턴해줌    ▷ div#item2.item
        console.log(el);
        
        // ▼ 글씨와 박스 모두 삭제 후 리턴되는 요소를 remove-result 공간에 담음
        //   단, 이벤트도 함께 삭제 됨
        $('#remove-result').append(el);
    });
    
    //  3) detach 테스트
    // -  요소.detach() : 선택된 요소를 제거 후 제거된 요소 리턴
    //                 단, 리턴되는 요소의 이벤트는 삭제하지 않음
    $('#detach').click(() => {
        let el = $('#item2').detach();
        
        // ▼ 제거된 요소를 리턴해줌    ▷ div#item2.item
        console.log(el);
        

        // ▼ 글씨와 박스 모두 삭제 후 리턴되는 요소를 remove-result 공간에 담음
        //   단, 이벤트도 함께 삭제 되지 않고 남아있음
        $('#remove-result').append(el);
    })

    
    

    // 5. 추가적인 메소드
    //  1) each 메소드
    let output = '';
    
    // ▼객체 배열 생성
    let arr = [ 
        {name: '네이버', link: 'https://www.naver.com'},
        {name: '구글', link: 'https://www.google.com'},
        {name: 'w3school', link: 'https://www.w3schools.com'}
    ];

    // ▼ 배열명과 콜백함수의 매개값으로 index, item을 넘겨줌
    $.each(arr, (index, item) => {
        console.log(`index: ${index}, item.name : ${item.name}, item.link: ${item.link}`);
        
        output += `<h4><a href=${item.link}>${item.name}</a></h4>`;
    });
        
    // $(arr).each((index, item) => {
    //     console.log(`index: ${index}, item.name : ${item.name}, item.link: ${item.link}`);
        
    //     output += `<h4><a href=${item.link}>${item.name}</a></h4>`;

    // ▼ 지금은 아무 내용도 없었어서 html도 가능
    //    * append는 갖다 붙이는 것, html은 바꾸는 것
    $('#each-test').html(output);
    // $('#each-test').append(output);

    // ▼ each-test 밑의 h4를 찍으면 세가지 요소 (네이버, 구글, w3스쿨)를 가져오고
    //   여기에 bg-hotpink를 가져와서 바탕색이 바뀌는 속성 추가
    $('#each-test>h4').each((index, item) => {
        console.log(index, item);

        $(item).addClass('bg-hotpink');
    });

    // ▼ 자바스크립트에서도 동일한 기능을 하는 메소드가 추가되었음
    arr.forEach((item, index, origin)=> {
        console.log(item, index, origin);
    });


    //  2) extend 메소드
    let user1 = {
        name: '무닌수',
        age: 21
    }

    console.log(user1);
    
    // ▼ user 객체에 객체 추가 
    $.extend(user1, {job: '강사'});
    
    // ▼ 객체 추가 후 user 조회
    console.log(user1);

    let user2 = {
        // ▼ name 이라는 동일한 속성값 존재
        name: '홍길동',
        hobby: ['음악듣기', '달리기', '국밥먹기']
    }

    let result = $.extend(user1, user2);

    // ▼ user의 name 속성이 뒤에 쓴 '홍길동'으로 덮여 씌워짐
    console.log(user1);
    console.log(user2);
    // ▼ 반환값은 실제로 합쳐진 값
    console.log(result);


    //  3) noConflict 메소드
    let jq = $.noConflict();

    jq('#ncTest').css('color', 'orange');
});