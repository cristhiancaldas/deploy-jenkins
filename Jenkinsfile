pipeline {

    environment {
        registry = "crist/app-rest"
        registryCredential = 'cred-dockerhub'
        dockerImage = ''
        mvnHome = tool name: 'maven', type: 'maven'
        mvnCMD = "${mvnHome}/bin/mvn"
    }

agent any

triggers {
      pollSCM '* * * * *'
    }

stages {

  stage("Cloning our Git") {
    steps {
          git credentialsId: 'cred-git', url: 'https://github.com/cristhiancaldas/deploy-jenkins.git'
    }
  }

  stage("Mvn Package"){
   steps{
        sh "${mvnCMD} clean package -Dmaven.test.skip=true"
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
             //dockerImage.push("latest")
             }
         }
     }
  }

  stage("Cleaning up") {
    steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
    }
  }

/*   stage("Deploy in k8s"){
    steps{
                kubernetesDeploy(
                    kubeconfigId: 'cred-eks',
                    configs: 'deployment-k8s.yml',
                    enableConfigSubstitution: true
                )
    }
  }*/

  }
}