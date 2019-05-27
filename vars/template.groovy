#!/usr/bin/env groovy
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    node('master') {
        stage('Checkout') {
            checkout scm
        }
        
        stage('Run tests') {
            try {
                withMaven(maven: 'myMaven') {
                    dir('bobcat') {
                        sh 'mvn -Dintegration-tests.skip=true clean package'
                    }
                }
            } finally {
                echo "Fuck"
            }
        }
    }
}