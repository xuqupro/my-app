def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {

            stage('build') {
                steps {
                    sh 'mvn clean package -DskipTests=true'
                }
            }

            stage ('test') {
                steps {
                    parallel (
                        "unit tests": { sh 'mvn test' },
                        "integration tests": { sh 'mvn integration-test' }
                    )
                }
            }
        }
        post {
            failure {
                 mail to: pipelineParams.email,
                 subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
                 body: "${env.BUILD_URL} has result ${currentBuild.result}"
            }
        }
    }
}