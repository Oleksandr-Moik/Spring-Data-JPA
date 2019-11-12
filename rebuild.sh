cd ./DockerSBServise1_Age/ && gradle clean build && docker build . -t service-1-age:v1 &
cd ./DockerSBServise3_FirstName/ && gradle clean build && docker build . -t service-3-firsname:v1 &
cd ./DockerSBServise2_LastName/ && gradle clean build && docker build . -t service-2-lastname:v1