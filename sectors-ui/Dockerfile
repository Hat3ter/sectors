FROM node:alpine
WORKDIR /app
COPY package.json ./
COPY package-lock.json ./
COPY ./ ./
ARG URL_SECTOR_API
ENV REACT_APP_URL_SECTOR_API $URL_SECTOR_API
RUN echo "DEBUG": $REACT_APP_URL_SECTOR_API
RUN npm i
CMD ["npm", "run", "start"]