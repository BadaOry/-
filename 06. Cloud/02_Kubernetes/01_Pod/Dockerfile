FROM tomcat:8.5.72-jdk8
# 작업 디렉터리 변경
WORKDIR /usr/local/tomcat/webapps
# 작업 디렉터리 내 ROOT 디렉터리 생성
RUN mkdir ./ROOT
# ROOT 디렉터리에 index.jsp 파일 추가
ADD index.jsp ./ROOT
ENV TZ=Asia/Seoul
EXPOSE 8080
CMD ["catalina.sh","run"]