pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout([
                $class: 'GitSCM',
                branches: [[name: '*/main']],
                userRemoteConfigs: [[url: 'https://github.com/EdgarLemus/Automatizaci-n_Konex_Innovation.git']]
                ])
            }
        }

        stage('Build and Test') {
            steps {
               bat 'gradle clean test aggregate'
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
