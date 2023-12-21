FROM registry.access.redhat.com/ubi8/ubi
RUN yum install -y java-11-openjdk-devel
WORKDIR /app
EXPOSE 6200
COPY containerized-overlay.jar /app
CMD java -jar /app/containerized-overlay.jar node 6200 localhost
