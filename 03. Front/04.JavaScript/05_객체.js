// 객체 선언
let btn1 = document.getElementById('btn1');

btn1.addEventListener('click', () => {
    let area = document.getElementById('area1');

    // ▼ 객체 선언방법 1 : 객체 리터럴 사용 (0개 이상의 속성 추가 가능)
    //   - 자바 속성(프로퍼티) = 키 : 값
    //                * 문자형, 키는 명명규칙을 만족하면 '' 생략 가능
    let product = {   
        // ▼ 숫자 타입의 키를 추가해도 문자형으로 변환되어 저장됨
        // '0': '배열 흉내',
        0: '배열 흉내',
        // ▼ 명명규칙을 만족하므로 '' 생략 가능
        // 'pName': '아이폰 12 미니'
        pName: '아이폰 12 미니',
        price: 1000000,
        brand: '샘숭',  
        brand: '애플',    // 중복 선언 시, 마지막에 선언된 프로퍼티가 덮어씀
        spec: ['OLED', 'IOS15']
    };

    // 속성명을 제시할 때 공백이나 특수문자가 들어가야 하는 경우,
    // '' 나 ""로 묶어주어야 함
    let user = {
        // ▼ 공백 들어가서 에러
        // user name: '무닌수'
        'user name': '무닌수',

        // ▼ 특수문자 들어가서 에러
        // age!!: 20
        'age!!': 20
        
    }

    // ▼ 속성 하나씩 꺼내서 확인할 용도로 만듦
    console.log(product);

    area.innerHTML += `product : ${product} <br><br>`;

    area.innerHTML += `객체명['속성명']으로 접근하는 방법 <br>`;
    // ▼ 숫자형 속성의 경우 ' 생략해도 사용 가능
    // area.innerHTML += `product[0] : ${product['0']}`;
    area.innerHTML += `product[0] : ${product[0]} <br>`;
    
    // ▼ 문자형 속성이므로 ' 를 빼면 에러나서 출력이 안됨
    // area.innerHTML += `product['pName'] : ${product[pName]} <br>` ; 
    area.innerHTML += `product['pName'] : ${product['pName']} <br>` ; 

    area.innerHTML += `product['price'] : ${product['price']} <br>` ; 
    area.innerHTML += `product['brand'] : ${product['brand']} <br>` ; 
    area.innerHTML += `product['spec'][0] : ${product['spec'][0]} <br>` ; 
    area.innerHTML += `product['spec'][1] : ${product['spec'][1]} <br><br>` ; 
    
    area.innerHTML += `객체명.속성명으로 접근하는 방법 <br>`;
    // ▼ 숫자형 속성의 경우 무조건 객체명["속성명"] 으로 접근해야함
    //   '0' 이런것도 안됨
    // area.innerHTML += `product.0 : ${product.0} <br><br>`;    
    area.innerHTML += `product.pName : ${product.pName} <br>`;    
    area.innerHTML += `product.price : ${product.price} <br>`;    
    area.innerHTML += `product.brand : ${product.brand} <br>`;    
    area.innerHTML += `product.spec.0 : ${product.spec[0]} <br>`;    
    area.innerHTML += `product.spec.1 : ${product.spec[1]} <br><br>`;    

    area.innerHTML += `공백이나 특수문자가 속성명에 포함된 경우, 대괄호를 이용해 값을 가져올 수 있음 <br>`
    // ▼ 에러 (공백, 특수문자)
    // area.innerHTML += `user.user name : ${user.user name}`;
    // area.innerHTML += `user.user name : ${user."user name"}`;
    area.innerHTML += `user['user name'] : ${user['user name']} <br>`;
    area.innerHTML += `user['age!!'] : ${user['age!!']} <br>`;
});

// 객체의 메소드
let btn2 = document.getElementById('btn2');

btn2.addEventListener('click', () => {
    let name = '무닌수';

    let dog = {
        name: '백구',
        kind: '진돗개',
        eat: function(food) {
            // ▼ 객체 내부에서 객체 속성에 접근하려면 this 사용해야함
            //   그냥 속성값을 쓸 경우, undefiend 에러가 남
            // console.log(name);
            // console.log(kind);
            // console.log(eat);

            //                              ▼ this를 명시적으로 꼭 써야 함 (안그러면 무닌수가 나와요...)
            console.log(`${this.kind} 종류인 ${this.name}가 ${food}를 먹고있네요~!`);
        }
    };

    dog.eat('닭가슴살');
});

// 객체와 반복문
let btn3 = document.getElementById('btn3');

btn3.addEventListener('click', () => {
    let game = {
        title: '디아블로2',
        price: 40000,
        supportOS: ['win32', 'win64'],
        service: true
    };

    console.log(game);

    for (const key in game) {
        //                                      ▼ game의 속성의 값을 하나하나 가져오겠다
        console.log(`key : ${key}, game[key] : ${game[key]}`);
    }
});

// 객체의 속성 추가 및 삭제
let btn4 = document.getElementById('btn4');

btn4.addEventListener('click', () => {
    // 빈 객체 선언
    let student = {};

    // 객체에 속성 추가
    student.name = '홍길동';
    student.age = 20;
    student['job'] = '의적';

    // 객체에 메소드 추가
    //                   ▼ => function은 안됨
    student.whoAreYou = function () {
        let str = '';
        //                ▼ 객체 자신 (student)
        for (const key in this) {
            // if(key !== 'whoAreYou') {

            // ▼ 여러개의 메소드가 존재할 수도 있으므로
            //   메소드가 아닌 속성들만 가져오는 식으로 X`변경
            if(typeof(this[key]) !== 'function') {
                str += `${key} : ${this[key]} `;
            }
        }

        return str;
    };

    console.log(student);
    console.log(student.whoAreYou());
    // ▼ job 이라는 속성이 student 객체에 있는가?  
    console.log('job' in student);  // ▷ true

    delete(student.job);

    console.log(student);
    console.log(student.whoAreYou());
    console.log('job' in student);  // ▷ false
});

// 객체 배열
let btn5 = window.document.getElementById('btn5');

btn5.addEventListener('click', () => {
    let area = document.getElementById('area2');

    // ▼ 배열을 사용하지 않았을 경우
    // : 파일 용량 증가, 가독성 저하, 
    let student1 = {name: '무닌수', java:100, oracle: 70};
    let student2 = {name: '홍길동', java:70, oracle: 60};
    let student3 = {name: '백구', java:100, oracle: 100};
    let student4 = {name: '누렁이', java:80, oracle: 80};
    let student5 = {name: '이몽룡', java:20, oracle: 20};
    
    // console.log(student1);
    // console.log(student2);
    // console.log(student3);
    // console.log(student4);
    // console.log(student5);

    // ▼ 배열 선언 및 초기화
    let students = [
        {name: '무닌수', java: 100, oracle: 70},
        {name: '홍길동', java: 70, oracle: 60}
    ];

    // 배열에 요소 추가
    students.push({name: '백구', java:100, oracle: 100});
    students.push(student4);
    students.push(student5);

    // 배열에 담겨있는 모든 객체에 메소드 추가
    for (let i = 0; i < students.length; i++) {
        students[i].getSum = function() {
        //         ▼ students 안에서 students의 속성인 java, oracle을 호출해서 this !
            return this.java + this.oracle;
        };

        students[i].getAvg = function() {
            return this.getSum() / 2;
        };
    }

    console.log(students);

    // 모든 학생의 정보가 담긴 배열을 출력  (이름, 총점, 평균)
    for (const index in students) {
        with(students[index]) {
            area.innerHTML += `이름 : ${name}, 총점 : ${getSum()}, 평균 : ${getAvg()} <br><br>`;
        }
    }
});

// 생성자 함수
function Student(name, java, oracle) {
    // 속성 정의
    this.name = name;
    this.java = java;
    this.oracle = oracle;

    // 메소드 정의
    // this.getSum = function() {
    // //          ▼ 개체 내부에서 개체의 속성에 접근하므로 this 사용    
    //     return this.java + this.oracle;
    // };
    //
    // this.getAvg = function() {
    //     return this.getSum() / 2;
    // };
}

Student.prototype.getSum = function () {
    return this.java + this.oracle;
    
};

Student.prototype.getAvg = function () {
    return this.getSum() / 2;
};

let btn6 = document.getElementById('btn6');


btn6.addEventListener('click', () => {
    let area = document.getElementById('area3');
    let student= new Student('무닌수', 80, 80);
    let students = [];

    students.push(student);
    students.push(new Student('스댕', 100, 100));
    students.push(new Student('행진', 100, 100));
    students.push(new Student('이몽룡', 40, 40));

    // ▼ instanceof 연산자 : 생성자 함수로 만들어진 객체의 경우
    //                       해당 객체가 어떤 생성자 함수로 생성되었는지 검사
    console.log(student instanceof Student);     // true
    console.log(typeof(student));                // object
    console.log(students instanceof Student);    // false
    console.log(typeof(students));               // object

    // 모든 학생의 정보가 담긴 배열 출력 (이름, 총점, 평균)
    for (const index in students) {
        area.innerHTML += `이름 : ${students[index].name}, ` +
        `총점 : ${students[index].getSum()}, 평균 : ${students[index].getAvg()} <br><br>`;
    }

    console.log(students);
});

// 캡슐화
function IdolGroup(n, m) {
    // this.name = name;
    let name = n;
    let members = m;

    this.getGroupName = function() {
        return name;
    }

    this.getMembers = function() {
        return members;
    }
    
    this.getMemberCount = function() {
        return members.length;
    }

    this.setGroupName = function(n) {
        name = n;
    }

    this.setMembers = function(m) {
        members = m;
    }
}

let btn7 = document.getElementById('btn7');

btn7.addEventListener('click', () => {
    let bts = new IdolGroup('방탄소년단', ['지민', '뷔', '정국', '랩몬', '슈가', '진', '제이홉']);
    let area = document.getElementById('area4');

    console.log(bts);
    // console.log(bts.name, bts.members); // undefined : 직접 접근할 수 없음
    console.log(bts.getGroupName(), bts.getMembers(), bts.getMemberCount());

    // 그룹 이름, 멤버 바꿔보기
    bts.setGroupName('레드벨벳');
    bts.setMembers(['아이린', '슬기', '조이', '웬디', '예리']);

    area.innerHTML += `그룹명 : ${bts.getGroupName()}, 멤버 : ${bts.getMembers()}, 멤버수 : ${bts.getMemberCount()} <br><br>`; 
});