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

    stages {
        stage('Build') {
//            steps {
//                sh "mvn package"
//            }
            steps {
//                sh 'echo "$MAVEN_HOME"'
//                sh 'echo "$PATH"'
                sh 'mvn -version'
                sh 'mvn package'
            }
            post {
                success {
                    junit 'target/reports/**/*.xml'
                }
            }
        }
        stage('Test'){
            steps {
                sh "echo 'some test'"
            }
        }
        stage('Deploy') {
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
    }
//
//    post {
//        always {
//            junit '**/target/*.xml'
//        }
//        failure {
//            mail to: 'team@example.com', subject: 'The Pipeline failed :('
//        }
//    }
}