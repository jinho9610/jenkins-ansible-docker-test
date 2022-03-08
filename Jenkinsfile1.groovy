pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-cred')
    }

    stages {
        stage('before pull') {
            steps {
                echo 'Before pull codes from git......'
            }
        }

        stage('docker build') {
            steps {
                sh script: "docker build -t jinho9610/node-express-app:latest ."
                
            }
        }

        stage('docker login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('docker push') {
            steps {
                sh script: "docker push jinho9610/node-express-app:latest"
            }
        }

        stage('deploy api server') {
            steps {
                echo 'deploy-stage-before'
                dir('ansible') {
                    ansiblePlaybook(
                        playbook: 'deploy.yml',
                        inventory: 'environments/hosts',
                        colorized: true,
                        disableHostKeyChecking: true,
                        extraVars: [
                            mode: 'deploy',
                            docker_imag_tag: 'jinho9610/node-express-app:latest'
                        ]
                    )
                }
                echo 'deploy-stage-after'
            }
        }
    }
}