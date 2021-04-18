pipeline {
    agent any
    tools {
        maven 'Maven 3.6.3'
    }
    environment {
        IMAGE_NAME = 'laweta-mail-image'
    }
    stages {
        stage('Preparation') {
            steps {
                git branch: 'master', url: 'https://github.com/piotrowicki/laweta-mail.git'
            }
        }
        stage('Build application') {
            steps {
                sh 'mvn clean compile package'
            }
        }
        stage('Build image') {
            steps {
                sh 'docker build -f src/main/docker/Dockerfile.jvm -t ${IMAGE_NAME} .'
            }
        }
    }
}
