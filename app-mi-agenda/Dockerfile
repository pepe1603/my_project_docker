# STAGE 1: Build
FROM node:20-alpine AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# STAGE 2: Production
FROM node:20-alpine
WORKDIR /app
COPY --from=build /app/dist ./dist
RUN npm install -g http-server

EXPOSE 5175
CMD [ "http-server", "dist", "-p", "5175" ]
