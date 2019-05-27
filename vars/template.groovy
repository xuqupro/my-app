#!/usr/bin/env groovy
import groovy.json.JsonSlurper
def call(body) {
    // evaluate the body block, and collect configuration into the object
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    sayHello.hello("Guy!")
}