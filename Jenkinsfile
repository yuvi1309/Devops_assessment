pipeline {
    agent any
    
    tools {
       maven 'Maven 3.8.0'
    }

    triggers {
        githubPush()
    }

    environment {
        GIT_REPO = 'https://github.com/yuva-shree-a/Devops_assessment'
        DEVELOPERS_EMAIL = 'athiyuva2513@gmail.com'
        BRANCH_NAME = 'master' // You need to define BRANCH_NAME
    }
 
    stages {
        stage('Checkout') {
            steps {
                git branch: "${BRANCH_NAME}", url: "${GIT_REPO}"
            }
        }
 
        stage('Build') {
            steps {
                bat 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
 
        stage('Merge to Master') {
            when {
                branch 'dev'
                expression {
                    return currentBuild.resultIsBetterOrEqualTo('SUCCESS')
                }
            }
            steps {
                script {
                    bat '''
                    git checkout -f master
                    git merge dev
                    git push
                    '''
                }
            }
        }
    }
 
    post {
        always {
            cleanWs()
        }
 
        failure {
            script {
                if (env.BRANCH_NAME == 'dev') {
                    emailext subject: "Build failed in Jenkins: ${currentBuild.fullDisplayName}",
                             body: "Something is wrong with ${env.BRANCH_NAME} branch.\n\nCheck console output at ${env.BUILD_URL} to view the results.",
                             to: "${env.DEVELOPERS_EMAIL}"
                }
            }
        }
    }
}