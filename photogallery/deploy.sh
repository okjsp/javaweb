#!/bin/bash
sudo service photoapp stop
sleep 5;
echo `pwd`
cp -f photogallery/target/photogallery.war /home/ec2-user/photogallery.war
chmod 755 /home/ec2-user/photogallery.war
sudo rm -rf /etc/init.d/photoapp
sudo ln -s /home/ec2-user/photogallery.war /etc/init.d/photoapp
sudo service photoapp start
