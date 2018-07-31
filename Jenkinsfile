pipeline {
  agent any
  tools {
    maven 'apache-maven-3.5.3'
  }
  stages {
    stage('Built') {
      steps {
        echo 'hello world'
        sh 'mvn package'
      }
    }
    stage('Test') {
      steps {
        echo 'Hello Test'
      }
    }
  }
}