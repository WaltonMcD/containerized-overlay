FROM registry.access.redhat.com/ubi8/ubi
RUN yum install -y java-11-openjdk-devel
WORKDIR /app
EXPOSE 6200
COPY containerized-overlay.jar /app
CMD ./app/start-nodes.sh 10
