pipeline {
    agent any

    tools {
        jdk 'JDK_21'       // Name from Global Tool Configuration
        maven 'Maven'    // Name from Global Tool Configuration
        git 'Git'        // Name from Global Tool Configuration
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
                    userRemoteConfigs: [[url: 'https://github.com/your-org/your-repo.git']]
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
                // TestNG XML
                testNGPublisher 'test-output/testng-results.xml'

                // TestNG HTML
                publishHTML(target: [
                    reportDir: 'test-output',
                    reportFiles: 'emailable-report.html',
                    reportName: 'TestNG HTML Report',
                    allowMissing: true,
                    alwaysLinkToLastBuild: true
                ])

                // Cucumber JSON
                cucumber fileIncludePattern: '**/test-ouput/json-report/cucumber.json'

                // Cucumber HTML
                publishHTML(target: [
                    reportDir: 'test-ouput',
                    reportFiles: 'overview-features.html',
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
