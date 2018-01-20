FROM tomcat:8.5.24-jre8-alpine

ADD config/tomcat-users.xml /usr/local/tomcat/conf/
ADD config/context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD build/libs/cinema-1.0.war /usr/local/tomcat/webapps/

CMD ["catalina.sh", "run"]

EXPOSE 8080