#!/usr/bin/env bash

# Installing lxc and lxd to setup kubernetes.
sudo apt-get install lxc lxd -y

# Creating a profile
sudo lxc profile copy default techmang3

# Edit the profile
sudo lxc profile edit techmang3

# Launching ubuntu 18.04 instance
sudo lxc launch ubuntu:18.04 jenkinsserver --profile techmang3
sleep 5
sudo lxc file push setup.sh jenkinsserver/root/setup.sh

sudo lxc exec jenkinsserver bash setup.sh

sudo lxc exec jenkinsserver service jenkins restart
sleep 10

sudo lxc exec jenkinsserver -- su - jenkins -c 'mkdir -p .kube'

sudo lxc file push /etc/kubernetes/admin.conf jenkinsserver/var/lib/jenkins/.kube/config

sudo lxc exec jenkinsserver chown jenkins:jenkins /var/lib/jenkins/.kube/config

# Replacing the kube config
hostname -I | awk '{print $1}' | sudo lxc exec jenkinsserver -- su - jenkins -c  "sed -i -e 's/127.0.0.1/$(tee)/g' ~/.kube/config"

# address for jenkins
sudo echo $(sudo lxc exec jenkinsserver -- su - jenkins -c  "hostname -I" | awk '{print $2 " jenkins.server.local"}') | sudo tee -a /etc/hosts

# Configuring docker
sudo lxc exec jenkinsserver -- su - jenkins -c 'docker login'

# Cloning the kube renderer
sudo lxc exec jenkinsserver -- su - jenkins -c 'git clone https://github.com/amithapa/kube_renderer.git'