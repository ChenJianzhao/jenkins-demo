# 源码自动生成模板 java-demo

### 概述

* 模板: java-demo
* 模板使用时间: 2018-07-25 10:41:46

### Docker
* Image: registry.cn-beijing.aliyuncs.com/codebase/java-demo
* Tag: 3.1
* SHA256: df65d4a99d00a560e1ba2a41b3b41b4d936245b45c02c8b9525c206ffc10812d

### 用户输入参数
* repoUrl: "git@code.aliyun.com:27470-DemoProject/devops-demo.git" 
* needDockerfile: "N" 
* appName: "devops-demo" 
* operator: "aliyun_794396" 

### 上下文参数
* appName: devops-demo
* operator: aliyun_794396
* gitUrl: git@code.aliyun.com:27470-DemoProject/devops-demo.git
* branch: master


### 命令行
	sudo docker run --rm -v `pwd`:/workspace -e repoUrl="git@code.aliyun.com:27470-DemoProject/devops-demo.git" -e needDockerfile="N" -e appName="devops-demo" -e operator="aliyun_794396"  registry.cn-beijing.aliyuncs.com/codebase/java-demo:3.1

