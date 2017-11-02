#!/bin/bash
sudo service myapp stop
sleep 5;
echo `pwd`
cp -f spring/spring-boot-jsp/target/hello-springboot-1.5.8.RELEASE.war /home/ec2-user/hello-springboot-1.5.8.RELEASE.war
chmod 755 /home/ec2-user/hello-springboot-1.5.8.RELEASE.war
sudo rm -rf /etc/init.d/myapp
sudo ln -s /home/ec2-user/hello-springboot-1.5.8.RELEASE.war /etc/init.d/myapp
sudo service myapp start
