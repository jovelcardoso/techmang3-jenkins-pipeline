def call(gitUsername, repositoryName) {

//    stage('checkout') {
//        node('master') {
//            // Clear all the files and directory for the clean build
//            sh "rm -rf *";
//            // Cloning the repostiroy which stores the data to build the docker file and the kube deployment files
//            sh "git clone --single-branch -b master https://github.com/${gitUsername}/${repositoryName}.git";
//            // Pulling the changes to the renderer repository.
////            sh "cd ~/kube_renderer && git pull";
//
////            sh "cd ~/home/mis/"
//
//            sh "cd ${repositoryName}/"
//            sh "ls -sh"
//            // Setting up the minor build version
//            Date date = new Date()
//            // Populating the image version.
//            imageVersion = date.format("yyddMMHHmm");
//
//
//        }
//    }


    stage('run-parallel-branches') {
        steps {
            parallel(
                    a: {
                        echo "This is branch a"
                    },
                    b: {
                        echo "This is branch b"
                    }
            )
        }
    }
}
