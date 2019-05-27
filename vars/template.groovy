#!/usr/bin/env groovy
@Library('library_demo') _
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    sayHello.hello("Guy!")
}