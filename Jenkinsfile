pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/EdgarLemus/Automatizaci-n_Konex_Innovation.git', branch: 'main'
            }
        }

        stage('Build and Test') {
            steps {
                sh './gradlew clean test aggregate'
            }
        }
    }

    post {
        success {
            echo 'La construcción y las pruebas se ejecutaron con éxito.'
        }
        failure {
            echo 'La construcción o las pruebas fallaron.'
        }
    }
}
