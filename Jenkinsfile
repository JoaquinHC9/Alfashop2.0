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
        SONAR_TOKEN_BACK = 'sqp_e0a6342d53db0751ca639d2e125bd33028396290'
        SONAR_TOKEN_FRONT = 'sqp_d777cff13bf260ed22d7e063d59092b4f1745def'
        ZAP_DOCKER_IMAGE = 'ghcr.io/zaproxy/zaproxy:stable'
    }
    stages {
        stage("Git Checkout") {
            steps {
                checkout scmGit(branches: [[name: '*/jenkins']], extensions: [], userRemoteConfigs: [[credentialsId: 'GithubToken', url: 'https://github.com/JoaquinHC9/Alfashop2.0']])
            }
        }
        stage('Limpiar y Construir Backend') {
            steps {
                script {
                    dir('back') {
                        bat 'mvn clean install'
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
                        -Dsonar.exclusions=**/node_modules/**,Dockerfile,docker-compose.yml \
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
        stage('Pruebas Funcionales') {
            steps {
                script {
                    dir('front') {
                        bat 'npm run cy:run'
                    }
                }
            }
        }
        stage('Pruebas de Rendimiento') {
            steps {
                script {
                    bat "${JMETER_HOME}/bin/jmeter.bat -n -t PruebasAlfashop.jmx -l results.jtl -e -o report"
                }
            }
        }
        stage('Ejecutar Pruebas ZAP') {
            steps {
                script {
                    bat "docker run -d -u zap --name zap --network host -v ${WORKSPACE}/zap/wrk:/zap/wrk/ -v ${WORKSPACE}/session:/zap/session/ ghcr.io/zaproxy/zaproxy:stable zap-baseline.py -t http://localhost:5173 -g gen.conf -r /zap/wrk/report.html"
                    bat "docker wait zap"
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
