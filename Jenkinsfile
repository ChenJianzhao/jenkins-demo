#!groovy
pipeline {
    agent any
//    agent {
//        docker {
//            image 'maven'
//            label 'my-defined-label'
//            args  '-v /tmp:/tmp'
//        }
//    }

//    // for docker base jenkins
//    environment {
//        PATH = "$PATH:$MAVEN_HOME"
//    }
    tools {
        maven 'Maven 3.5.3' // 需要现在全局配置中设置，可以选取已安装的，也可以配置自动安装
    }
    //properties([parameters([text(defaultValue: '', description: 'changed nginx.conf', name: 'nginxConfigName')])])
    stages {
        stage('Check Environment') {
            steps {
                sh 'echo "$MAVEN_HOME"'
                sh 'echo "$PATH"'
                sh "mvn -version"
            }
        }
        stage('Build Project') {
            steps {
                echo "code static analyse"
                sh 'mvn package'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/**/*.war', fingerprint: true
                    junit 'target/**/*.xml' // 需要遵循 “/**/*.xml” 的格式，否则会报错
                }
            }
        }
        stage('Collect Config'){
            steps {
                git branch: 'jwt', credentialsId: 'username_password_for_gitlab', url: 'http://chenjz@gitlab.dinghuo123.com/Test/nginx.git'
                cd nginx
                //echo $nginxConfigName
            }
        }
        stage('Deploy Dev'){
            steps {
                echo "Deploy to Dev Environment"
            }
        }
        stage('Smoke Test'){
            input {
                message "Should we continue?"
                ok "Yes, we should."
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
                submitter "tester"
            }
            steps {
                echo "Hello, ${PERSON}, Smoke Test Pass."
            }
        }
        stage('Deploy Test') {
            steps {
//                // sh 'make publish'
//                withCredentials([usernamePassword(credentialsId: 'jenkins-username-password-for-aliyun',
//                                                passwordVariable: 'PASSWORD_FOR_ALIYUN',
//                                                usernameVariable: 'USERNAME_FOR_ALIYUN')]) {
//                    // some block
//                }

                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'aliyun',
                            transfers: [
                                sshTransfer(excludes: '',
                                    execCommand: '''if [ -f "/home/admin/devops-demo/deploy.sh" ]; then /home/admin/devops-demo/deploy.sh stop; fi
                                    /home/admin/devops-demo/deploy.sh start''',
                                execTimeout: 120000,
                                flatten: false,
                                makeEmptyDirs: false,
                                noDefaultExcludes: false,
                                patternSeparator: '[, ]+',
                                remoteDirectory: 'home/admin/devops-demo',
                                remoteDirectorySDF: false,
                                removePrefix: 'target',
                                sourceFiles: '**/*.war')
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
            }
        }
        stage('Auto Test'){
            steps {
                echo "Auto Test"
            }
        }
        stage('Manual Test') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, Manual Test Pass."
            }
        }
        stage('Deploy to Stage'){
            steps {
                echo "Deploy to Prod"
            }
        }
        stage('Staging Test') {
            input {
                message "Should we continue?"
                ok "Yes, we should."
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, Staging Test Pass."
            }
        }
        stage('Deploy to Prod'){
            steps {
                echo "Deploy to Prod"
            }
        }
    }
}