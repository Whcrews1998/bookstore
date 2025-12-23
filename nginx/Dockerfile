FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/* \ && rm -f /etc/nginx/conf.d/default.conf/

COPY nginx/conf/nginx.conf /etc/nginx/nginx.conf