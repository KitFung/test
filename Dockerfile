FROM maven:3.3.9-jdk-8

MAINTAINER KitFung <kitfung@oursky.com>

# USER root

# copy project to container
RUN mkdir -p /usr/src/app
# RUN chown -R seluser /usr/src/app
COPY . /usr/src/app
WORKDIR /usr/src/app

CMD ["./runtest.sh"]