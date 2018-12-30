#/bin/bash

docker network create studo-network

docker stop studo-bd
docker stop studo-service
docker stop studo-client
echo -e "\n -> OFF"

docker rm studo-service
docker rm studo-client
echo -e "\n -> CONTAINERS REMOVIDOS"

docker rmi -f studo-service
docker rmi -f studo-client
echo -e "\n -> IMAGENS REMOVIDAS"

cd ./studo-service
mvn clean package -DskipTests -U
docker build -t studo-service .
echo -e "\n -> Imagem serviço construida."

cd ../
cd ./studo-cliente
sudo npm install
npm run build
docker build -t studo-client .
echo -e "\n -> Imagem cliente construida."

cd ../
cd ./docker
docker-compose -f docker-compose.yml up

echo -e "\n -> Concluído."