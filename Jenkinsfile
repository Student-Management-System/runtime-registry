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
        withCredentials([usernamePassword(
          credentialsId: "${JENKINS_DOCKER_CREDS}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')
        ]) {
          sh "mvn spring-boot:build-image \
              -Ddocker.registry=https://DOCKER_REGISTRY \
              -Ddocker.user=${USERNAME} \
              -Ddocker.secret=${PASSWORD} \
              -Ddocker.publish=true"
        }
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


  }
}
