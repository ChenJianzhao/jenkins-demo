#!groovy
//pipeline{
//    agent any
//
//    stages {
//
//        stage('BUILD'){
//            steps {
//                echo "Start BUILD..."
//                sh "mvn package"
//            }
//        }
//
//        post {
//            allways {
//                echo 'Success! Begin deploy'
//
//                sh "if [ -f '/home/admin/devops-demo/deploy.sh' ]; +" +
//                        "then /home/admin/devops-demo/deploy.sh stop; +" +
//                        "fi"
//
//                /home/admin/devops-demo/deploy.sh start
//            }
//        }
//    }
//}

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn package"
            }
        }
        stage('Test'){
            steps {
                sh 'make check'
                junit 'reports/**/*.xml'    //
            }
        }
        stage('Deploy') {
            steps {
                // sh 'make publish'
                withCredentials([usernamePassword(credentialsId: 'jenkins-username-password-for-aliyun',
                                                passwordVariable: 'PASSWORD_FOR_ALIYUN',
                                                usernameVariable: 'USERNAME_FOR_ALIYUN')]) {
                    // some block
                }
            }
        }
        post {
            always {
                junit '**/target/*.xml'
            }
            failure {
                mail to: 'team@example.com', subject: 'The Pipeline failed :('
            }
        }
    }
}