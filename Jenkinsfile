pipeline {

    environment {
        registry = "crist/app-rest"
        registryCredential = 'cred-dockerHub'
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
 
  stage("Building our image") {
    steps{
           //sh "docker buildx ls"
           //sh "docker buildx inspect default"
            //sh "docker buildx create --name mybuilder"
            sh "docker buildx use mybuilder"
            //sh "docker buildx build  $registry:$BUILD_NUMBER  --platform linux/amd64,linux/arm64  ." 
            sh "docker buildx build --platform linux/arm64,linux/amd64 -t $registry:$BUILD_NUMBER . --load"
          // dockerImage = docker.build registry + ":$BUILD_NUMBER"
    }
  }

  stage("Push our image") {
    steps{
        script {
            docker.withRegistry( '', registryCredential ) {
            dockerImage.push()
            }
        }
    }
  }

  stage("Cleaning up") {
    steps{
        sh "docker rmi $registry:$BUILD_NUMBER"
    }
  }


}
}
