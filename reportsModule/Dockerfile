FROM jboss/wildfly:latest

MAINTAINER "Sebastian Łoś"

ADD target/reportsModule-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/

RUN wildfly/bin/add-user.sh admin admin --silent

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
