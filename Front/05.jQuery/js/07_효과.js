$(document).ready(() => {
    // 효과
    // 1. show()와 hide()
    $('#show').on('click', () => {
        // ▼ 시간을 설정할 수 있음
        $('#imgFlower').show(1500, 'linear');
    })
    
    $('#hide').on('click', () => {
        $('#imgFlower').hide(1500, 'swing');
    })

    $('#toggle').on('click', () => {
        // ▼ toggle 없이 효과를 구현하는 방법
        if($('#imgFlower').css('display') === 'none') {
            $('#imgFlower').show(1500, 'linear');
        } else { 
            $('#imgFlower').hide(1500, 'swing');
        }

        // ▼ toggle 을 사용하여 한번에 효과 구현
        // $('#imgFlower').toggle(1500, 'swing');
    })


    // 2. slideDown()과 slideUp()
    $('.menu').on('click', (event) => {
        // let content = $(event.target).next();

        // ▼ 보여주는게 없으면 slideDown, 아니면 slideUp 실행 
        // if(content.css('display') === 'none') {
        //     content.slideDown(500, 'swing');
        // } else {
        //     content.slideUp(500, 'swing');
        // }


        // ▼ toggle 메소드를 사용하여 slideDown, slideUp을 실행
        // $(event.target).next().slideToggle(500, 'swing');
        
        // ▼ 하나의 컨텐츠만 slideDown 되도록 수정
        $('.contents').slideUp(500, 'swing');
        $(event.target).next().slideDown(500, 'swing');
    });

    


    // 3. fadeIn() 과 fadeOut()
    $('#fadeIn').on('click', () => {
        $('#imgForest').fadeIn(1500, 'linear');
    });

    $('#fadeOut').on('click', () => {
        $('#imgForest').fadeOut(1500, 'swing');
    });

    $('#fadeToggle').on('click', () => {
        $('#imgForest').fadeToggle(1500, 'swing');
    });

    // ▼ 위의 내용을 하나로 처리함
    $('#imgForest').hover (
        (event) => {
            if(event.type === 'mouseenter') {
            $(event.target).fadeTo(500, 0.5);
        } else if(event.type === 'mouseleave') {
            $(event.target).fadeTo(500, 1);
            }
        }
    )
});