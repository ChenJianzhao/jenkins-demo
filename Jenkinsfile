pipeline {
  agent any
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