#!/bin/bash
sudo service myapp stop
sleep 10;
cp -f ./target/hello-springboot-1.5.8.RELEASE.war /home/ec2-user/hello-springboot-1.5.8.RELEASE.war
chmod 755 /home/ec2-user/hello-springboot-1.5.8.RELEASE.war
sudo rm -rf /etc/init.d/myapp
sudo ln -s /home/ec2-user/hello-springboot-1.5.8.RELEASE.war /etc/init.d/myapp
sudo service myapp start
