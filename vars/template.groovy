#!/usr/bin/env groovy

def call(Map args) {
  node {
    stage('Build') {
       echo "Command: ${args.command}"
    }
  }
  return this
}

def build(Map args) {
  node {
    stage('Build') {
      echo "Command: ${args.command}"
    }
  }
  return this
}

def unitTest(Map args) {
  node {
    stage('Unit Test') {
      echo "Command: ${args.command}"
    }
  }
  return this
}

def deploy(Map args) {
  node {
    stage('Deploy') {
      echo "Command: ${args.command}"
    }
  }
  return this
}