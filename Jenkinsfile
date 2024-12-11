pipeline {
    agent any
    tools {
        jdk 'JAVA'
        maven 'maven'
    }
    environment {
        BACKUP_PATH = "${WORKSPACE}\\back\\src\\main\\resources\\backup.sql"
        SCRIPT_PATH = "${WORKSPACE}\\back\\restore_db.bat"
    }
    stages {
        stage("Git Checkout") {
            steps {
                git branch: 'jenkins', changelog: false, poll: false, url: 'https://github.com/JoaquinHC9/Alfashop2.0.git'
            }
        }
        stage('Clean and Build Backend') {
            steps {
                script {
                    dir('back') {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    dir('back') {
                        bat 'docker build -t alfashop .'
                    }
                }
            }
        }

        stage('Start Docker Compose (Backend + DB)') {
            steps {
                script {
                    dir('back') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }

        stage('Restore Database') {
            steps {
                script {
                    bat "\"${SCRIPT_PATH}\" \"${BACKUP_PATH}\""
                }
            }
        }
    }
    post {
        success {
            echo 'Proceso completado con éxito!'
        }
        failure {
            echo 'Hubo un error en el proceso.'
        }
    }
}
