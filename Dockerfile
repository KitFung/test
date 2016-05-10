FROM maven:3.3.9-jdk-8

MAINTAINER KitFung <kitfung@oursky.com>

# copy project to container
RUN mkdir -p /usr/src/app
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -Dmaven.test.skip=true clean install

CMD ["./runtest.sh"]