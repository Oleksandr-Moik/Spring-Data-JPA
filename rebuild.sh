cd ./DockerSBServise_Age/ && gradle clean build && docker build . -t service-age:v1 &
cd ./DockerSBServise_FirstName/ && gradle clean build && docker build . -t service-firsname:v1 &
cd ./DockerSBServise_LastName/ && gradle clean build && docker build . -t service-lastname:v1