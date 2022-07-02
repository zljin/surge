pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git credentialsId: 'b97dbaa3-d9f0-4f73-ae6a-bc46bae691d4', url: 'https://github.com/zljin/surge.git'
            }
        }
        stage('Test') {
            steps {
                echo 'Test'
                bat "mvn test"
            }
        }
        stage('Build and Push') {
            steps {
                echo 'Build'
                bat 'mvn clean package docker:build -DpushImage'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy'
            }
        }
    }
}
