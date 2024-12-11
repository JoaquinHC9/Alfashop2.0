pipeline {
    agent any
    tools {
        jdk 'JAVA'
        maven 'maven'
    }
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        BACKUP_PATH = "${WORKSPACE}\\back\\src\\main\\resources\\backup.sql"
        SCRIPT_PATH = "${WORKSPACE}\\back\\restore_db.bat"
        SONAR_HOST_URL = 'http://localhost:9000'
        SONAR_TOKEN_BACK = 'sqp_14e0f96ce9faf919cbef6eca0a33c59c935975e1'
        SONAR_TOKEN_FRONT = 'sqp_8f4bbab464f0cf77f97a2724d5230ed5727a619d'
    }
    stages {
        stage("Git Checkout") {
            steps {
                git branch: 'jenkins', changelog: false, poll: false, url: 'https://github.com/JoaquinHC9/Alfashop2.0.git'
            }
        }
        stage('Limpiar y Construir Backend') {
            steps {
                script {
                    dir('back') {
                        bat 'mvn clean compile'
                    }
                }
            }
        }
        stage('Test unitarios') {
            steps {
                script {
                    dir('back') {
                        bat 'mvn test'
                    }
                }
            }
        }
        stage("SonarQube Analisis Backend") {
            steps {
                script {
                    dir('back') {
                        bat "mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=AlfashopBackend \
                        -Dsonar.projectName='AlfashopBackend' \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=${SONAR_TOKEN_BACK} \
                        -Dsonar.language=java \
                        -Dsonar.tests=src/test \
                        -Dsonar.dynamicAnalysis=reuseReports \
                        -Dsonar.junit.reportsPath=target/test-classes \
                        -Dsonar.java.coveragePlugin=jacoco \
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco-report/jacoco.xml"
                    }
                }
            }
        }
        stage("SonarQube Analisis Frontend") {
            steps {
                script {
                    dir('front') {
                        bat " ${SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=AlfashopFrontend \
                        -Dsonar.sources=. \
                        -Dsonar.host.url=${SONAR_HOST_URL} \
                        -Dsonar.login=${SONAR_TOKEN_FRONT}"
                    }
                }
            }
        }
        stage('Construir Imagen Docker Backend') {
            steps {
                script {
                    dir('back') {
                        bat 'docker build -t alfashop .'
                    }
                }
            }
        }

        stage('Levantar Docker') {
            steps {
                script {
                    dir('back') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }
        

        stage('Restaurar Database') {
            steps {
                script {
                    bat "\"${SCRIPT_PATH}\" \"${BACKUP_PATH}\""
                }
            }
        }

        stage('Construir Imagen Docker Frontend') {
            steps {
                script {
                    dir('front') {
                        bat 'docker build -t alfashop-front .'
                    }
                }
            }
        }

        stage('Levantar Docker Frontend') {
            steps {
                script {
                    dir('front') {
                        bat 'docker-compose up -d'
                    }
                }
            }
        }

        stage('Ejecutar pruebas Funcionales') {
            steps {
                script {
                    dir('front') {
                        bat 'npm run cy:run'
                    }
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
