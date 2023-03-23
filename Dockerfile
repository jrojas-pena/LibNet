FROM        java:7-jdk

ENV         JAVA_HOME         /usr/lib/jvm/java-7-openjdk-amd64
ENV         GLASSFISH_HOME    /usr/local/glassfish4
ENV         PATH              $PATH:$JAVA_HOME/bin:$GLASSFISH_HOME/bin


RUN         curl -L -o /tmp/glassfish-4.0.zip http://download.java.net/glassfish/4.0/release/glassfish-4.0.zip && \
            unzip /tmp/glassfish-4.0.zip -d /usr/local && \
            rm -f /tmp/glassfish-4.0.zip

COPY ./app/build/libs/app.jar /opt/glassfish/glassfish/domains/domain1/autodeploy/


EXPOSE      8080 4848 8181

WORKDIR     /usr/local/glassfish4

# verbose causes the process to remain in the foreground so that docker can track it
CMD         asadmin start-domain --verbose

