node{
   stage('SCM Checkout'){
       git credentialsId: 'cred-git', url: 'https://github.com/cristhiancaldas/deploy-jenkins.git'
   }
   stage('Mvn Package'){
     def mvnHome = tool name: 'maven-3', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     sh "${mvnCMD} clean package"
   }
}