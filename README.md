
### Setting up Kubernetes steps:
- Clone the github repository
  ```bash
  git clone https://github.com/amithapa/techmang3-jenkins-pipeline.git 
  ```
- Run the following command to setup a jenkins server
  ```bash
  cd techmang3-jenkins-pipeline/kubernetes
  ```
  ```bash
  ./setup-softwares.sh
  ```
  
### Setting up Jenkins steps:
- Clone the github repository
  ```bash
  git clone https://github.com/amithapa/techmang3-jenkins-pipeline.git 
  ```
- Run the following command to setup a jenkins server
  ```bash
  cd techmang3-jenkins-pipeline/jenkins
  ```
  ```bash
  ./setup-jenkins.sh
  ```
  
  
  This command will install lxc package to spin off a lxc container in which we will do a setup of our jenkins server.  
  You will be prompted to alter the config for the lxc profile this is very important to run a docker container inside a lxc containers.   
  Kindly paste the following portion to the **config** section.
  ```yaml
  config:
    linux.kernel_modules: ip_tables,ip6_tables,netlink_diag,nf_nat,overlay
    raw.lxc: "lxc.apparmor.profile=unconfined\nlxc.cap.drop= \nlxc.cgroup.devices.allow=a\nlxc.mount.auto=proc:rw
    sys:rw"
    security.nesting: "true"
    security.privileged: "true"
  ```
- You will be prompted to enter your docker login credentials so that we can build and deploy docker container during the CI/CD process:
```bash
Login with your Docker ID to push and pull images from Docker Hub. If you don't have a Docker ID, head over to https://hub.docker.com to create one.
Username: amithapa
Password: 

```

- [Setting up Jenkins server](./jenkins/docs/setting_up_jenkins_server.md)  
- [Installing Jenkins Plugins](./jenkins/docs/installing_plugins.md)  
- [Setting Up Global Pipeline libraries](./jenkins/docs/setting_global_pipeline_libraries.md)  
- [Setting Up A Pipline Project](./jenkins/docs/adding_an_application.md)  
- [Setting Up Freestyle project for polling](./jenkins/docs/adding_a_deployment_trigger.md)  

  
---
[Back](/scripts/README.md) | [Home](/scripts/README.md)
