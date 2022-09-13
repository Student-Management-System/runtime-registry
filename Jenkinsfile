pipeline {
  agent any
  environment {
    DOCKER_TARGET = 'e-learning-by-sse/infrastructure-registry'
    DOCKER_REGISTRY = 'https://ghcr.io'
    JENKINS_DOCKER_CREDS = 'github-ssejenkins'
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
        sh "mvn spring-boot:build-image -Dspring-boot.build-image.imageName=${DOCKER_REGISTRY}/${DOCKER_TARGET}"
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
		sh "docker push ${DOCKER_REGISTRY}/${DOCKER_TARGET}"
      }
    }

  }
}
