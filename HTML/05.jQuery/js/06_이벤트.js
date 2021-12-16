$(() => {
    // 1. 이벤트 연결
    //  1) on() 과 off()

    // // ▼ 마우스가 올라가면 색, 텍스트가 변하는 이벤트 추가
    // $('#area1').on('mouseenter',() => {
    //     $('#area1').css('background', 'beige').text('마우스가 올라감');
    // });
    
    // // ▼ 마우스가 내려가면 색, 텍스트가 변하는 이벤트 추가
    // $('#area1').on('mouseleave', () => {
    //     $(window.event.target).css('background', 'hotpink').text('마우스가 내려감');
    // });

    // // ▼ 위 두 식을 합친 것
    // $('#area1').on('mouserenter mouseleave', (event) => {
    //     console.log(event);

    //     if(event.type === 'mouseeneter') {
    //         $('#area1').css('background', 'beige').text('마우스가 올라감');
    //     } else if (event.type === 'mouseleave') {
    //         $(event.target).css('background', 'hotpink').text('마우스가 내려감');
    //     }
    // })

    // // ▼ 클릭 시 모든 이벤트가 삭제되는 이벤트 추가
    // $('#area1').on('click', (event) => {
    //     $(event.target)
    //         .css('background', 'white')
    //         .text('')
    //         // ▼ 띄어쓰기 해서 한 줄로 삭제하거나
    //         .off('mouseenter mouseleave');
    //         // ▼ 하나씩 삭제해도 괜찮음
    //         .off('mouseenter')
    //         .off('mouseleave');

    // ▼ 여러 개의 이벤트를 객체 형태로 한번에 전달
    //   : mouseenter, mouseleave, off 를 한번에 전달할거임
    $('#area1').on({
        mouseenter: () => {
            $('#area1').css('background', 'beige').text('마우스가 올라감');
        },
        mouseleave: () => {
            $(window.event.target).css('background', 'hotpink').text('마우스가 내려감');
        }, 
        click: (event) => {
            $(event.target).css('background', 'white').text('').off('mouseenter mouseleave');
        }
    });


    //  2) one()
    // ▼ area2 를 누르면 alert 실행 후,
    //   area2 영역에 커서를 갖다대면 손 모양으로 바뀜
    $('#area2').one('click', () => {
        alert('처음이자 마지막으로 이벤트 핸들러 실행');
    }).css('cursor', 'pointer')




    // 2. 키보드 이벤트
    //  1) keydown(), keypress(), keyup()
    $('#tarea1').on({
        // ▼ 글자와 상관 없이 키보드가 눌릴 때 발생하는 이벤트 
        keydown: (event) => {
            console.log(`keydown > event.key : ${event.key}, event.keyCode : ${event.keyCode}`);
        },
        // ▼ 글자가 입력될 때 발생하는 이벤트 (한글, 펑션키, 기능키 인식 못함)
        keypress: (event) => {
            console.log(`keypress > event.key : ${event.key}, event.keyCode : ${event.keyCode}`);
        },
        // ▼ 글자와 상관 없이 키보드가 떼어질 때 발생하는 이벤트
        keyup: (event) => {
            console.log(`keyup > event.key : ${event.key}, event.keyCode : ${event.keyCode}`);
        }
    });


    //  2) 동적으로 글자수 세기
    $('#tarea2').on('keyup', (event) => {
        let target = $(event.target);
        let counter = $('#counter');
        let maxLength = parseInt($('#maxLength').text());
        
        // ▼ maxLength가 써진 글자 수 보다 작아지면 (= 쓴 글자수가 maxLength를 넘으면)
        //   글씨를 빨간색으로 변경
        if((maxLength - target.val().length) < 0) {
            counter.css('color', 'red');
            // ▼ 글자 수를 넘기면 더이상 입력 못하게 함
            //   : substr을 써서 0에서부터 maxLength 까지 자른것을 val에 넣어줌
            target.val(target.val().substr(0, maxLength));
        } else {
            counter.css('color','black');
        }

        // ▼ 입력할 때 마다 입력된 것의 길이를 알려주는 콘솔 로그
        console.log(target.val().length);

        // ▼ 입력할 때 마다 counter 의 숫자가 변경되는 코드 
        counter.text(target.val().length);
    });

    
    // 3) 동적으로 아이디 조건 확인
    // ▼ 첫 글자는 반드시 영문자로 입력되고 
    // 영문 소문자, 숫자를 포함한 총 4~12자의 아이디 옵션을 포함한 정규표현식
    let regExp = /^[a-z][a-z\d]{3,11}$/;

    $('#userId').keyup((event) => {
        let userId = $(event.target).val();

        if(userId === null || userId === '') {
            $('#idCheck').css('color', 'white').text('');
        } else if (regExp.test(userId)) {
            $('#idCheck').css({'color': 'green', 'font-weight': 'bold'}).text('사용 가능한 아이디 형식입니다.'); 
        } else {
            $('#idCheck').css({'color': 'red', 'font-weight': 'bold'}).text('사용 할 수 없는 아이디 형식입니다.'); 
        }
    })
});


