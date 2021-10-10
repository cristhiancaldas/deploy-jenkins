pipeline {

    environment {
        registry = "crist/app-rest"
        registryCredential = 'cred-dockerHub'
        dockerImage = ''
    }

agent any

stages {

 stage('Cloning our Git') {
    steps {
          git credentialsId: 'cred-git', url: 'https://github.com/cristhiancaldas/deploy-jenkins.git'
    }
  }

 stage('Mvn Package'){
   steps{
        def mvnHome = tool name: 'maven', type: 'maven'
        def mvnCMD = "${mvnHome}/bin/mvn"
        sh "${mvnCMD} clean package -Dmaven.test.skip=true"
       }
   }
 }

  stage('Building our image') {
    steps{
        script {
            dockerImage = docker.build registry + ":$BUILD_NUMBER"
        }
    }
  }

  stage('Deploy our image') {
    steps{
        script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
    }
  }

  stage('Cleaning up') {
    steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
    }
  }
}
