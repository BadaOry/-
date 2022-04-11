# ▼ 베이스 이미지를 tomcat:8.5.72-jdk8 로 지정
FROM tomcat:8.5.72-jdk8
# ▼ 작성자 라벨 생성 (이미지에 대한 정보, maintainer=작성자)
LABEL maintainer "kh<kh@iei.com>"
# ▼ 버전 라벨 생성
LABEL version="1.0"
# ▼ /usr/local/tomcat/webapps 디렉토리에 ROOT.war 파일 추가
ADD ROOT.war /usr/local/tomcat/webapps
# ▼ Timezone 환경변수를 Asia/Seoul 로 변경
ENV TZ=Asia/Seoul
# ▼ 이미지에서 8080 포트 사용
EXPOSE 8080
# ▼ 컨테이너 실행 시 catalina.sh 파일 자동 실행
CMD ["catalina.sh","run"]
