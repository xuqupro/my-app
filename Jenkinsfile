pipeline {
    agent any
    tools { 
       maven 'myMaven' 
        jdk 'myJDK' 
    }

    environment {
        branch = 'master'
        scmUrl = 'ssh://git@192.168.56.108:quocnx/maven.git'
        serverPort = '8080'
        developmentServer = '192.168.56.108'
        stagingServer = 'staging-myproject.mycompany.com'
        productionServer = 'production-myproject.mycompany.com'
    }

    stages {
        stage('checkout git') {
             steps {
                checkout scm
            }
        }

         stage('Build with unit testing') {
             steps {
                script {
                    sh "mvn -Dintegration-tests.skip=true clean package"
                }
             }
         }

        stage('deploy development'){
            steps {
                // deploy(developmentServer, serverPort)
                script {
                    template([])
                }
            }
        }
    }
    post {
        success {
             echo 'Success'
             mail to: 'thegioiitjob@gmail.com',
            subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
            body: "${env.BUILD_URL} has result ${currentBuild.result}"
        }
        failure {
             echo 'Fail'
             mail to: params.email, subject: 'Pipeline failed', body: "${env.BUILD_URL}"
            
        }
    }
}

def getDevVersion() {
    def gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    def versionNumber;
    if (gitCommit == null) {
        versionNumber = env.BUILD_NUMBER;
    } else {
        versionNumber = gitCommit.take(8);
    }
    print 'build  versions...'
    print versionNumber
    return versionNumber
}

def deploy(def server, def port) {
    // httpRequest httpMode: 'POST', url: "http://${server}:${port}/shutdown", validResponseCodes: '200,408'
    sshagent(['ssh_key_root']) {
        echo "success login"
        sh "scp -r target/my-app-*.jar quocnx@192.168.56.108:/app/jenkins-demo.jar"
        sh "ssh quocnx@${server} nohup java -Dserver.port=${port} -jar /app/jenkins-demo.jar &"
    }
    // retry (3) {
    //     sleep 5
    //     httpRequest url:"http://${server}:${port}/health", validResponseCodes: '200', validResponseContent: '"status":"UP"'
    // }
}
