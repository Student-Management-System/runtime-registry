pipeline {
  agent any
  environment {
    DOCKER_TARGET = 'e-learning-by-sse/infrastructure-registry'
    DOCKER_REGISTRY = 'ghcr.io'
    JENKINS_DOCKER_CREDS = 'github-ssejenkins'
    DOCKER_IMAGE_NAME = "${DOCKER_REGISTRY}/${DOCKER_TARGET}"
  }
  
  tools {
    maven 'Maven 3.8.6' 
  }
  
  stages {
  
    stage ('Clone') {
      steps {
        checkout scm
      }
    }

    stage ('Build & Tests') {
      steps {
        sh "mvn spring-boot:build-image -Dspring-boot.build-image.imageName=${DOCKER_IMAGE_NAME}"
        junit '**/target/surefire-reports/*.xml'
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
      }
    }

    stage ('Analysis') {
      steps {
        jacoco()
        script {
          def checkstyle = scanForIssues tool: [$class: 'CheckStyle'], pattern: '**/target/checkstyle-result.xml'
          publishIssues issues:[checkstyle]
        } 
      }
    }

    stage ('Publish') {
      when { 
        expression {
          currentBuild.result == null || currentBuild.result == 'SUCCESS' 
        }
      }
      steps {
       script {
            docker.withRegistry("https://${DOCKER_REGISTRY}", "${JENKINS_DOCKER_CREDS}") {
            docker.image("${DOCKER_IMAGE_NAME}").push()
          }
        }
      }
    }

  }
}
