#!/usr/bin/env groovy
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    // node('master') {
    //     stage('Checkout') {
    //         checkout scm
    //     }
    // }
    pipeline {
        agent any
        stages {
           stage('Checkout') {
                checkout scm
            } 
        }
    }
}