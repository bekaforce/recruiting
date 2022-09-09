FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/cc_question-0.0.1-SNAPSHOT.jar /app/app.jar

ENV JAVA_OPTS="\
-server \
-Xmx4g \
-Xms4g \
-Xmn2g \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"
ENTRYPOINT java -Dhttps.proxyHost=172.28.142.230 -Dhttps.proxyPort=3128 -Dhttp.proxyHost=172.28.142.230 -Dhttp.proxyPort=3128 -jar /app/app.jar
CMD ["java","-jar","/app/app.jar"]