#DevOps 
Devops is a software development approach which involved continuous development, continuous testing, continuous integration, continuous deployment and continuous monitoring throughout its development lifecycle

##Devops Stages
1. **Version Control** - Maintains different versions of the code.
2. **Continuous Integration** - Compile, Validate, Code Review, Unit Testing, Integration Testing.
3. **Continuous Delivery** - Deploying the build application to test servers, QA/UAT or even prod. It can involve one or more manual steps like approving the version which is going to get deployed. 
                             Even though it contains a manual step, the deployment is still fully automated
4. **Continuous Deployment** - Deploying the tested application on the prod server for release. No manual step, every code change is deployed all the way to prod.
5. **Continuous Monitoring**

  Version Control -> Build -> Unit/Integration Test -> Deploy to Staging -> Auto Test -> Deploy to Production  | Measure + Validate

##Jenkins 
Jenkins is an open source automation tool written in Java with plugins built for Continuous Integration purpose.
Plugins allows integration of various DevOps stages.
Commit -> Build -> Test -> Stage -> Deploy Dev/QA


