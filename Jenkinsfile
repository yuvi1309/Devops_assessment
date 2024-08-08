pipeline {
    agent any
    
    tools {
       maven 'Maven 3.8.5'
    }

    triggers {
        githubPush()
    }

    environment {
        GIT_REPO = 'https://github.com/yuvi1309/Devops_assessment.git'
        DEVELOPERS_EMAIL = 'athiyuva2513@gmail.com'
        BRANCH_NAME = 'master' 
    }
 
    stages {
        stage('Checkout') {
            steps {
                //git branch: "${BRANCH_NAME}", url: "${GIT_REPO}"
                bat 'git clone https://github.com/yuvi1309/Devops_assessment.git'
            }
        }
 
        stage('Build') {
            steps {
                //bat 'cd exercise-bt-conditionalstatements-ifelse'
                dir('C://ProgramData//Jenkins//.jenkins//workspace//jenkins_multibranch_master//exercise-bt-conditionalstatements-ifelse'){
                bat 'mvn -B -DskipTests clean package'
                }
            }
        }

        stage('Test') {
            steps {
                dir('C://ProgramData//Jenkins//.jenkins//workspace//jenkins_multibranch_master//exercise-bt-conditionalstatements-ifelse'){
                bat 'mvn test'}
            }
            
        }
    
    stage('SonarQube Analysis'){
        steps{
            withSonarQubeEnv('sonarCloud'){
                 sh 'mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io  -Dsonar.login=<yuva-shree-a> -Dsonar.password=${f49edfb454a8ef3be6d52657686348d9cbbfb119} -Dsonar.projectKey=<yuva-shree-a_Devops_assessment> '
            //Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
            }
        }
    }
    }

 
    post {
        always {
            cleanWs()
            //sonarQualityGate()
        }
 
        failure {
            script {
                emailext(
                    subject: "Build failed in Jenkins: ${currentBuild.fullDisplayName}",
                    body: "Something is wrong with ${env.BRANCH_NAME} branch.\n\nCheck console output at ${env.BUILD_URL} to view the results.",
                    to: "${env.DEVELOPERS_EMAIL}")
                
            }
        }
    }
    
}
