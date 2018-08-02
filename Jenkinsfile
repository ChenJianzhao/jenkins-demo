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
                sh 'mvn package'
            }
            post {
                always {
                    junit 'target/**/*.xml' // 需要遵循 “/**/*.xml” 的格式，否则会报错
                }
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
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
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
                submitter "alice,bob"
                parameters {
                    string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                }
            }
            steps {
                echo "Hello, ${PERSON}, Manual Test Pass."
            }
        }
        stage('Deploy to Prod'){
            steps {
                echo "Deploy to Prod"
            }
        }
    }
}