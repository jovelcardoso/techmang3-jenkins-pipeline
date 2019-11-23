##  Adding an application for CI/CD into our jenkins


- **We will add a freestyle project for polling.**   
  We should be ideally use github webhooks for simplicity of this project we will use scm polling.
  Click on the following url:
  [http://jenkins.server.local:8080/view/all/newJob](http://jenkins.server.local:8080/view/all/newJob)  
  
  Following window will be loaded:  
  
  ![New Freestyle Project](assets/screenshot_project_0.png  "getting_started")  
  Select on **Freestyle project** and Cick on **ok**
- Under **Source code Management** tab add the following settings:  

  ![Source Code management Tab](assets/screenshot_project_1.png  "getting_started") 

- Under **Build Triggers** tab add the following settings: 
  
  ![Build Triggers Tab](assets/screenshot_project_2.png  "getting_started") 

- Under **Build Environment** tab add the following settings: 
  
  ![Build Environment Tab](assets/screenshot_project_3.png  "getting_started") 
  
  Click on save.
---
[Back](/scripts/README.md) | [Home](/scripts/README.md)