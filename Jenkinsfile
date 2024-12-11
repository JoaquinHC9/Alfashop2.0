pipeline {
    agent any
    tools {
        jdk 'JAVA'
        maven 'maven'
    }
    environment {
        SCANNER_HOME = tool 'sonar-scanner'
        BACKUP_PATH = "${WORKSPACE}/back/src/main/resources/backup.sql"
        MYSQL_CONTAINER_NAME = 'mysqldb'
        MYSQL_PASSWORD = 'root'
    }
    stages {
        stage("Git Checkout") {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/JoaquinHC9/Alfashop2.0.git'
            }
        }
        stage('Clean and Build Backend') {
            steps {
                script {
                    dir('back'){
                        // Limpiar e instalar el proyecto con Maven (backend en la carpeta 'back')
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Build Backend Docker Image') {
            steps {
                script {
                    dir('back'){
                        // Construir la imagen de Docker para el backend (en la carpeta 'back')
                        bat 'docker build -t alfashop .'
                    }
                }
            }
        }

        stage('Start Docker Compose (Backend + DB)') {
            steps {
                script {
                    dir('back'){
                        // Levantar los contenedores con Docker Compose
                        bat 'docker-compose up -d'
                    }
                }
            }
        }

        stage('Copy Backup to MySQL Container') {
            steps {
                script {
                    dir('back'){
                        // Copiar el archivo de backup al contenedor de MySQL
                        bat "docker cp ${BACKUP_PATH} ${MYSQL_CONTAINER_NAME}:/tmp/backup.sql"
                    }
                }
            }
        }

        stage('Restore Database') {
            steps {
                script {
                    dir('back'){
                    // Restaurar la base de datos desde el backup
                    bat """
                        docker exec ${MYSQL_CONTAINER_NAME} bash -c \"
                        mysql -u root -p${MYSQL_PASSWORD} alfashop < /tmp/backup.sql\"
                    """
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
