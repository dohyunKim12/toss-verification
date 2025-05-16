FROM ncr.nmn.io/netmarbles/images/openjdk:17-slim

ENV TZ=Asia/Seoul

ENV JAVA_OPTS="-Xms512m -Xmx1024m \
  -Duser.timezone=Asia/Seoul \
  -Dapplication.monitor.enable=false \
  -Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.port=9090 \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dcom.sun.management.jmxremote.authenticate=false"

WORKDIR /app

COPY build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar $SPRING_OPTS"]
