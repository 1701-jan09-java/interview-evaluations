#!/usr/bin/env groovy



node {

	stage ('Checkout') {
		checkout scm
			sh "echo Checkout"
	}
	stage ('Pre Build') {
		sh "echo Pre Build"
	}


	stage ('Build') {

		sh "echo Build"
	}

	if (env.BRANCH_NAME == 'master') {
		stage ('Deploy to Production') {
			echo 'Deploying master to production'

		}
	}
}	





def notifyBuild(String buildStatus, String buildType) {
	//build status of null means successful
	buildStatus = buildStatus ?: 'SUCCESSFUL'
		echo "$currentBuild"


		// default values
		def commitAuthor = sh script: "git show -s --pretty=%an", returnStdout: true
		def message = "$buildType $buildStatus\n*Job:* ${env.JOB_NAME} [${env.BUILD_NUMBER}]\n*Job Link:* ${env.BUILD_URL}\n*Author:* $commitAuthor"
		def colorCode = '#FF0000'

		// Override default values based on build status
		if (buildStatus == 'STARTED') {
			colorCode = '#FFFF00'
		} else if (buildStatus == 'SUCCESSFUL') {
			colorCode = '#00FF00'
		} else {
			colorCode = '#FF0000'
		}

	if (buildType == 'DEPLOYMENT') {
		colorCode = '#000000'
	}

	slackSend (color: colorCode, message: message)

}


