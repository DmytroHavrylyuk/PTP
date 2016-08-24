FROM jetty
USER jetty
RUN mkdir -p /var/lib/jetty/logs
ENV JAVA_OPTIONS=-DLOG_PATH=/var/lib/jetty/logs
COPY ./build/libs/Service.war /var/lib/jetty/webapps