$(document).ready(() => {
    // $('*').css('color', 'red');

    // 1. Filtering 메소드
    //  - Filtering 방법으로는 선택자와 메소드 제공
    //  - Filtering 관련 선택자
    //    선택자:first       : 첫 번째 위치한 요소 선택
    //    선택자:last        : 마지막에 위치한 요소 선택
    //    선택자:odd         : 홀수 번째에 위치한 요소 선택
    //    선택자:even        : 짝수 번째에 위치한 요소 선택
    //    선택자:eq(index)   : 인덱스에 해당하는 요소 선택
    //    선택자:not(인자)   : 선택자와 일치하지 않는 요소 선택 

    // ▼ h4 요소들 중 첫 번째 요소를 가져와서 스타일 변경 ▷ filtering 1
    // $('h4').first().css('font-size', '20pt'); 
    $('h4:first').css('font-size', '20pt');
    
    // ▼ h4요소들 중 마지막 요소를 가져와서 스타일 변경   ▷ filtering 6
    // $('h4')last().css('font-size', '20pt');                   
    $('h4:last').css('font-size', '20pt');

    // ▼ 홀수 인덱스 요소를 가져와서 스타일 변경          ▷ filtering 2, 4
    // $('.test').filter(':odd').css({backgroundColor: 'tomato', color: 'white'})
    $('.test:odd').css({backgroundColor: 'tomato', color: 'white'});
    
    // ▼ 짝수 인덱스 요소를 가져와서 스타일 변경          ▷ filtering 1, 3, 6
    // $('.test').filter(':even').css({backgroundColor: 'tomato', color: 'white'})
    $('.test:even').css({backgroundColor: 'yellowgreen', color: 'white'});

    // ▼ 리턴 결과가 true인 요소만 필터링 함              ▷ filtering 4
    $('.test').filter((index) => {
        // ▼ 인덱스가 0인 요소 제외후 인덱스 3의 배수인 요소만 적용
        if(index === 0) {
            return false;
        }
        return index % 3 === 0;
    }).css({backgroundColor: 'skyblue', color: 'white'});

    // ▼ 선택된 요소들 중 인덱스에 해당하는 요소를 가져와서 텍스트 변경
    // $('h4').eq(4).text('eq()에 의해 선택되었습니다.');
    $('h4:eq(4)').text('eq()에 의해 선택되었습니다.');

    // ▼ h4 클래스 중 'test' 클래스 요소를 갖지 않은 것 선택
    // $('h4').not('.test').css({backgroundColor: 'orange', color: 'white'})
    $('h4:not(.test)').css({backgroundColor: 'orange', color: 'white'})

    // ▼ h4를 가져와서 텍스트를 변경
    $('h4').filter('.test').text('ㅋㅋㅋㅋㅋㅋㅋ');



    // 2. Ancestor 메소드
    // ▼ 2개 나옴 : 선택된 요소의 바로 상위의 부모 요소
    console.log($('span').parent());      
    // ▼ 8개 나옴 : 선택된 요소의 모든 상위 요소
    console.log($('span').parents());    
    
    // ▼ span 태그의 부모 요소에 스타일 설정                  ▷ li, p
    // $('span').parent().css({color: 'red', border: '2px solid'});
    
    // ▼ li 태그의 모든 상위 요소에 스타일 설정               ▷ ul, div, div.wrap, body, html
    // $('li').parents().css('color', 'blue');
    
    // ▼ li 태그의 상위 요소중 div만 선택하여 스타일 설정      ▷ div, div.wrap
    // $('li').parents('div').css('border', '2px dashed tomato' )

    // ▼ span 태그부터 div 태그 이전까지 상위 요소 스타일 설정 ▷ p, li, ul
    // $('span').parentsUntil('div').css('background-color', 'lightgreen');



    // 3. Descendants 메소드
    console.log($('.wrap').children());
    // ▼ 2개 나옴 : 선택된 요소의 모든 하위의 자식 요소 중 span 만 찾기
    console.log($('.wrap').find('span'));
    
    // ▼ 클래스 명이 .wrap 인 태그의 자손의 스타일 변경                       ▷ div, div
    $('.wrap').children().css({color: 'red', border: '2px solid'});
    
    // ▼ 클래스 명이 .wrap 인 태그의 자손의 자손들의 스타일 변경               ▷ ul, p 
    $('.wrap').children().children().css({color: 'blue', border: '2px solid'});
    
    // ▼ 클래스 명이 .wrap 인 태그의 자손의 ul 태그의 스타일 변경              ▷ ul
    $('.wrap').children().children('ul').css({color: 'yellow', border: '2px solid'});
    
    // ▼ 클래스 명이 .wrap 인 태그의 자손의 자손들 중 li태그의 스타일 변경      ▷ li
    $('.wrap').children().children().children('li').css({color: 'green', border: '2px solid'});
    
    // ▼ 클래스 명이 .wrap 인 모든 후손 중 span 태그의 스타일 변경             ▷ span, span      ▷ 
    $('.wrap').find('span').css({color: 'purple', border: '2px solid'});
    

    
    // 4. Sideways 메소드
    // ▼ 4개 나옴  ▷ p, span.class1, h3, p
    console.log($('.wrap1>h2').siblings());

    // ▼ h2 태그의 형제 요소들에 스타일 적용                              ▷ p, span.class1, h3, p
    $('.wrap1>h2').siblings().css({color: 'red', border: '2px solid'});
    
    // ▼ h2 태그의 형제 요소 중 p 태그에 스타일 적용                       ▷ p, p
    $('.wrap1>h2').siblings('p').css({color: 'blue', border: '2px solid'});

    // ▼ h2 태그의 바로 다음 형제 요소에 스타일 적용                       ▷ h3
    $('.wrap1>h2').next().css({color: 'green', border: '2px solid'});
    
    // ▼ h2 태그의 다음에 오는 모든 형제 요소에 스타일 적용                ▷ h3, p
    $('.wrap1>h2').nextAll().css('backgroundColor', 'aqua');
    
    // ▼ h2 태그의 p 태그 이전에 오는 모든 형제 요소에 스타일 적용         ▷ h3
    $('.wrap1>h2').nextUntil('p').css('font-size', '3em');
    $('.wrap1>h2').nextUntil('p').css('border', 'dashed');
    
    // ▼ h2 태그의 이전 형제 요소에 스타일 적용                           ▷ span.class1
    $('.wrap1>h2').prev().css('backgroundColor', 'orange');

    // ▼ h2 태그의 이전에 있는 모든 형제 요소에 스타일 적용                ▷ span.class1, p
    $('.wrap1>h2').prevAll().css('backgroundColor', 'aqua');
    
    // ▼ h2 태그의 이전부터 p 태그 이후까지의 모든 형제 요소에 스타일 적용  ▷ span.class1
    $('.wrap1>h2').prevUntil('p').css('border', 'dotted');



    // 5. 요소가 있는지 찾는 메소드
    // ▼ wrap1 의 자손 span 의 다음에 오는 형제 중 h4를 찾음    ▷ false
    console.log(`$('.wrap1>span').nextAll().is('h4') : ${$('.wrap1>span').nextAll().is('h4')}`);
    
    // ▼ wrap1 의 자손 span 의 다음에 오는 형제 중 class1을 찾음    ▷ true
    console.log(`$('.wrap1>span').nextAll().is('.class1') : ${$('.wrap1>span').nextAll().is('.class1')}`);





});