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
    stages {
        stage('Init') {
            steps {
                sh "export PATH=$PATH:$MAVEN_HOME/bin"
            }
        }
        stage('Build') {
//            steps {
//                sh "mvn package"
//            }
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true package'
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