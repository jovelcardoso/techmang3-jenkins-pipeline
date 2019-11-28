def call(gitUsername, repositoryName) {

    stage('checkout') {
        node('master') {
            // Clear all the files and directory for the clean build
            sh "rm -rf *";
            // Cloning the repostiroy which stores the data to build the docker file and the kube deployment files
            sh "git clone --single-branch -b master https://github.com/${gitUsername}/${repositoryName}.git";
            // Pulling the changes to the renderer repository.
//            sh "cd ~/kube_renderer && git pull";

//            sh "cd ~/home/mis/"

            sh "cd ${repositoryName}/"
            sh "ls -sh"
            // Setting up the minor build version
            Date date = new Date()
            // Populating the image version.
            imageVersion = date.format("yyddMMHHmm");


        }
    }

    stage('Build and Test') {
        parallel {
            stage('Test On Windows') {
                agent {
                    label "windows"
                }
                steps {
                    bat "run-tests.bat"
                }
                post {
                    always {
                        junit "**/TEST-*.xml"
                    }
                }
            }
            stage('Test On Linux') {
                agent {
                    label "linux"
                }
                steps {
                    sh "run-tests.sh"
                }
                post {
                    always {
                        junit "**/TEST-*.xml"
                    }
                }
            }
//        node('master') {
//            sh "ls -sh"
//        }
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