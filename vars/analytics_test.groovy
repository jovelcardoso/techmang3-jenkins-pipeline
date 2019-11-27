def call() {

    stage('checkout') {
        node('master') {
            // Clear all the files and directory for the clean build
            sh "rm -rf *";
            // Cloning the repostiroy which stores the data to build the docker file and the kube deployment files
//            sh "git clone --single-branch -b master https://github.com/${gitUsername}/${repositoryName}.git";
            // Pulling the changes to the renderer repository.
//            sh "cd ~/kube_renderer && git pull";
            sh "pwd"
            sh "cd /home/mis/"

            sh "cd insight/"
            sh "ls"
            // Setting up the minor build version
            Date date = new Date()
            // Populating the image version.
            imageVersion = date.format("yyddMMHHmm");


        }
    }

    stage('Build and Test') {
        node('master') {
            // Runing the test cases on the docker image if it is applicable.
            sh "echo 'Running test cases'";

        }
    }
    stage('Deploy on Staging') {
        node('master') {
            def userInput = input(
                    id: 'userInput', message: 'This is PRODUCTION!', parameters: [
                    [$class: 'BooleanParameterDefinition', defaultValue: false, description: '', name: 'Please confirm you sure to proceed']
            ])

            if(!userInput) {
                error "Build was canceled";
            }
            sh "echo 'Deploy on staging'";
        }
    }

    stage('Confirm') {
        def userInput = input(
                id: 'userInput', message: 'This is PRODUCTION!', parameters: [
                [$class: 'BooleanParameterDefinition', defaultValue: false, description: '', name: 'Please confirm you sure to proceed']
        ])

        if(!userInput) {
            error "Build was canceled";
        }
    }

    stage('Deploy on Production') {
        node('master') {
            sh "echo 'Deploy on Production'";
        }
    }
}