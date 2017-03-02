#!/usr/bin/env groovy

// pipeline links:
//   https://github.com/jenkinsci/kubernetes-plugin#pipeline-support
//   https://github.com/GoogleCloudPlatform/continuous-deployment-on-kubernetes/blob/master/sample-app/Jenkinsfile
//   https://jenkins.io/doc/pipeline/steps/credentials-binding/
//   https://medium.com/garbage-collection/jenkins-pipelines-what-i-wish-i-knew-starting-out-6e3d4eb2ff5b#.plbehje7t

// sonar maven links:
//   https://docs.sonarqube.org/display/PLUG/GitHub+Plugin
//   https://docs.sonarqube.org/display/SONAR/Analysis+Parameters


podTemplate(label: 'jenkins-worker', containers: [
    containerTemplate(name: 'maven-docker-kubectl', image: 'tjkemper/maven-docker-kubectl:0.1', ttyEnabled: true, command: 'cat')
  ],
  volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]
  ) {

    node ('jenkins-worker') {
        try {
 
        stage ('Checkout Source Code') {
            checkout scm
            notifyBuild("STARTED")            
            gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
            imageTag = "revature/interview-evaluations:${gitCommit}"
            sh "env" //print all environment variables
        }

        container('maven-docker-kubectl') {
            stage ('Test') {

                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "8301f826-3f5d-4b20-8902-f34ea98aebd7",
                                      usernameVariable: 'INTEVAL_USER', passwordVariable: 'INTEVAL_PASS']]) {
                withCredentials([string(credentialsId: 'f9ca6321-287d-4402-b0f6-9f661e06d399', variable: 'INTEVAL_URL')]) {
                  
            	  sh "mvn clean \
                      -DINTEVAL_URL=${INTEVAL_URL}   \
                      -DINTEVAL_USER=${INTEVAL_USER} \
                      -DINTEVAL_PASS=${INTEVAL_PASS} \
                      package"
                  
                } // withCredentials
                } // withCredentials

            }

            if (BRANCH_NAME != "master") {
                stage ('SonarQube Analysis') {
                    withSonarQubeEnv("My SonarQube") {
                      withCredentials([string(credentialsId: '504252c1-ed1f-42cb-9208-8d9b355158bd', variable: 'GITHUB_ACCESS_TOKEN')]) {
                        
                        sh "mvn ${SONAR_MAVEN_GOAL} \
                              -Dsonar.analysis.mode=preview \
                              -Dsonar.host.url=${SONAR_HOST_URL} \
                              -Dsonar.login=${SONAR_AUTH_TOKEN} \
                              -Dsonar.github.oauth=${GITHUB_ACCESS_TOKEN} \
                              -Dsonar.github.repository=1701-jan09-java/interview-evaluations \
                              -Dsonar.github.pullRequest=${CHANGE_ID} \
                           "
                      
                      }
                    }
                }
            }

            stage ('Docker Build') {
            	sh "docker build -t ${imageTag} ."
            }

            if (BRANCH_NAME == "master") {
                
                stage ('Docker Push') {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: "43d121d5-8c7c-4234-b2d9-2b4279f04053",
                                      usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                        sh "docker login -u ${USERNAME} -p ${PASSWORD}"
                        sh "docker push ${imageTag}"
                    }
                }

                stage ('Kubernetes Deploy') {
                    sh "sed -i.bak 's#revature/interview-evaluations:0.1#${imageTag}#' ./k8s/deployment.yaml"
                    sh "kubectl apply -f ./k8s/ingress.yaml -f ./k8s/service.yaml -f ./k8s/deployment.yaml"
                }
              
            }
        }

        notifyBuild("SUCCESSFUL")
        } catch(Exception e) {
            notifyBuild("FAILURE")
            throw e
        }
    }
}

def notifyBuild(String buildStatus) {

    def colorCode
 
    // Override default color based on build status
    switch (buildStatus) {
        case "STARTED":
            colorCode = "#FFFF00"
            break
        case "SUCCESSFUL":
            colorCode = "#00FF00"
            break
        case "FAILURE":
            colorCode = "#FF0000"
            break
        default:
            colorCode = "#000000"
    }
   
    buildType = BRANCH_NAME == "master" ? "DEPLOYMENT" : ""
    if (buildType == "DEPLOYMENT") {
      colorCode = '#0000FF'
    }

    // vars
    def commitAuthor = sh script: "git show -s --pretty=%an", returnStdout: true
    def message = "$buildType $buildStatus\n*Job:* ${JOB_NAME} [${BUILD_NUMBER}]\n*Job Link:* ${BUILD_URL}\n*Author:* $commitAuthor"

    slackSend (color: colorCode, message: message)

}
