$(document).ready(() => {
    // 1. 자손 선택자와 후손 선택자
    //   1) 자손 선택자 : 부모>자손
    $('div>h3').css('color', 'violet');  // 자손 2, 3이 변경됨
    $('div>li').css('color', 'violet');  // li는 div의 바로 아래 자손이 아니라 적용 안됨
    $('div>ul h3').css('color', 'green');

    //   2) 후손 선택자 : 부모 후손
    $('div h3').css('background-color', 'skyblue');
    $('div .test').css('background-color', 'tomato');



    // 2. 기본 속성 선택자
    // ▼ input 태그 중 class 속성을 가진 요소만 가져옴
    // $('input[class]').css('background-color', 'tomato');

    //▼ input 태그 중 type의 속성값이 text인 요소만 가져옴
    // $('input[type=text]').val('Change Value');
    
    //▼ input 태그 중 class의 속성값에 test가 들어간 요소만 가져옴
    // $('input[class~=test]').val('123456');

    // ▼ ra 로 시작하는 type 만 가져옴
    // $('input[type^=ra]').prop('checked', 'true');

    // ▼ box로 끝나는 type 만 가져옴
    // $('input[type$=box]').prop('checked', 'true');

    // ▼ st2 가 앞이든 뒤든 포함되어있는 type만 가져옴
    // $('input[class*=st2]').css('width', '100px').css('height', '100px');



    // 3. 입력 양식 선택자
    // ▼ 모든 input type=text 태그가 분홍색으로 변함
    // $(':text').css('background-color', 'pink');

    // ▼ 모든 버튼을 왕버튼으로 만듦
    //   css 는 {} 로 객체 리터럴을 만들어서 속성을 넘겨줌
    // $(':button').val('왕버튼').css({width: '200px', height: '200px', backgroundColor: 'skyblue'});

    // ▼ 모든 체크박스를 왕크기로 만듦
    // $(':checkbox').prop('checked', true).css({width: '50px', height: '50px'});
    
    // ▼ 모든 file을 노란색으로 변경
    // $(':file').css('background-color', 'yellow');


    // ▼ 마우스를 이미지에 올리면 이미지가 변하는 이벤트
    // $(':image').mouseenter((event) => {
    //     console.log(event);

    //     $(event.target).attr('src', '../resources/image/flower2.PNG');
    // });
    
    // $(':image').mouseout((event) => {
    //     console.log(event);

    //     $(event.target).attr('src', '../resources/image/flower1.PNG');
    // });

    // ▼ hover() : mouseenter 와 mouseout이 합쳐진 것
    //     $(':image').hover(
    //     (event) => {
    //         $(event.target).attr('src', '../resources/image/flower2.PNG');
    //     },
    //     (event) => {
    //         $(event.target).attr('src', '../resources/image/flower1.PNG');
    //     }
    // );



    // 4. checkbox 상태에 대한 선택자
    // ▼ input 태그 중에 체그가 된 객체 선택
    $('input:checked').css({width: '50px', height: '50px'});
    
    // ▼ input 태그 중에 checked 속성값을 가지는 것만 크게 만들도록 변경
    $('input:checkbox').change((event) => {
        let target = $(event.target);

        console.log(event.target);
        console.log(target);

        if(target.prop('checked')) {
            target.css({width: '50px', height: '50px'});
        } else {
            target.css({width: '15px', height: '15px'});
        }
    });

    

    // 5. select, option 태그의 상태에 대한 선택자
    $('#national').change(() => {
        //▼ select 태그의 option 태그 중 선택된 객체를 선택하여 value값을 가져옴
        let value = $('#national>option:selected').val();

        // ▼ id 가 result 인 요소의 값을 value로 변경
        $('#result').val(value);
    }); 



    // 6. input 상태에 대한 선택자
    // $('input:enabled').css('background-color', 'tomato');
    $('input:disabled').css('background-color', 'tomato');



    // 7. attr()와 prop()
    // - attr : 요소가 가진 속성값이나 새탕값을 조회 (true, false로 할 수 없는 것)
    // - prop : attr(속성)이 존재하는지 여부를 true, false 로 리턴
    let cb1 = $('#cb1');
    let cb2 = $('#cb2');

    console.log(`cb1.attr('checked') : ${cb1.attr('checked')}`);   // checked
    console.log(`cb2.attr('checked') : ${cb2.attr('checked')}`);   // undefined

    console.log(`cb1.prop('checked') : ${cb1.prop('checked')}`);  // true
    console.log(`cb2.prop('checked') : ${cb2.prop('checked')}`);  // false


    // ▼ 메소드를 통해 속성값 추가
    // cb2.attr('checked', 'checked');
    cb2.prop('checked', true);

});