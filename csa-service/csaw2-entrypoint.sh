#!/bin/sh

echo 'Starting the custom script'


while ! nc -z gateway 8080
do
    echo "Waiting for upcoming Gateway  Server"
    sleep 2
done


echo 'Pre-requisite done now moving on the execution of current container service'

nohup java -jar csaw2.jar