FROM java:8
COPY target/*.jar /data/app.jar
#设置工作目录
WORKDIR /data
CMD java -jar /data/app.jar