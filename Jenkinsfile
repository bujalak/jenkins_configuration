pipeline {
    agent any
    triggers {
    githubPush()
}


    tools {
        jdk 'JDK_21'       // Name from Global Tool Configuration
        maven 'Maven'      // Name from Global Tool Configuration
        git 'Git'          // Name from Global Tool Configuration
    }

    parameters {
        string(
            name: 'cucumber_tags', 
            defaultValue: '@Smoke', 
            description: 'Cucumber tag(s) to run, e.g., @Smoke or @Regression'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out default branch"
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']], // Replace 'main' with your default branch if needed
                    userRemoteConfigs: [[url: 'https://github.com/bujalak/jenkins_configuration.git']]
                ])
            }
        }

        stage('Verify Tools') {
            steps {
                bat 'git --version'
                bat 'mvn -v'
                bat 'java -version'
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    echo "Running tests with tags: ${params.cucumber_tags}"
                    bat "mvn clean test -Dcucumber.filter.tags=\"${params.cucumber_tags}\""
                }
            }
        }

        stage('Publish Reports') {
            steps {
                // TestNG HTML report
                publishHTML(target: [
                    reportDir: 'test-output',
                    reportFiles: 'emailable-report.html',
                    reportName: 'TestNG HTML Report',
                    allowMissing: true,
                    alwaysLinkToLastBuild: true
                ])

                // Cucumber JSON report
                cucumber fileIncludePattern: '**/test-output/json-report/cucumber.json', 
                         reportTitle: 'Cucumber Report', 
                         sortingMethod: 'ALPHABETICAL'

                // Cucumber HTML report
                publishHTML(target: [
                    reportDir: 'test-output',
                    reportFiles: '*.html',
                    reportName: 'Cucumber HTML Report',
                    allowMissing: true,
                    alwaysLinkToLastBuild: true
                ])
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo '✅ Build succeeded'
        }
        failure {
            echo '❌ Build failed'
        }
    }
}
