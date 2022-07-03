FROM java:8
COPY target/surge.jar /data/surge.jar
#设置工作目录
WORKDIR /data
#设置环境变量
ENV JAVA_OPTS="-server -Xms1g -Xmx1g"
#声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 9884
#指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java $JAVA_OPTS ","-jar","/data/surge.jar"]

# docker build -t='mysurge' .
