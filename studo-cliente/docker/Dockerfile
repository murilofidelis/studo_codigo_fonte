FROM nginx:1.11-alpine

COPY /dist /usr/share/nginx/html/

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]

# cat Dockerfile
# sudo docker build -t studo-client .
# sudo docker run -d --name studo-clinet-container -p 80:80 studo-client
