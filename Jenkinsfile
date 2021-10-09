node{
   stage('SCM Checkout'){
       git credentialsId: 'cred-git', url: 'https://github.com/cristhiancaldas/deploy-jenkins.git'
   }
   stage('Mvn Package'){
     def mvnHome = tool name: 'maven', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     sh "${mvnCMD} clean package"
   }
   stage('Build Docker Image'){
     sh 'docker build -t kammancist/my-app:2.0.0 .'
   }
}