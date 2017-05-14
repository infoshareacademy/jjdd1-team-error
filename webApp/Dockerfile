FROM jboss/wildfly:latest

MAINTAINER "Krystian Skrzyszewski"

COPY target/ROOT.war /opt/jboss/wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


#ADD config /opt/jboss/wildfly/config/

#COPY target/ROOT.war /opt/jboss/wildfly/config/

#CMD ["/opt/jboss/wildfly/config/execute.sh"]