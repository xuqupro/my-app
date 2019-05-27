#!/usr/bin/env groovy
import groovy.json.JsonSlurper
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

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