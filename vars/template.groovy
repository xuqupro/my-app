#!/usr/bin/env groovy

def call(Map pipelineParams) {

    pipeline {
        agent any
        stages {
            stage('build') {
                steps {
                    sh 'mvn clean package -DskipTests=true'
                }
            }
        }
    }
}