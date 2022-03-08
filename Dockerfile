FROM node:latest
# FROM nodejscn/node:latest

# RUN yum install epel-release -y && yum install nodejs -y && yum install npm -y

COPY . /server

WORKDIR /server

EXPOSE 5555

ENTRYPOINT [ "node"]

CMD [ "server.js" ]