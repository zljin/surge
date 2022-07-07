#!/usr/bin/groovy
node {

    def tag = "0.0.3-RELEASE-${env.BUILD_NUMBER}"

    def nexusRepo = "192.168.56.1:8082"
    def imageNameTag = "surge:$tag"
    def nexusUser = "admin"
    def nexusPassword = "admin"
    def promoteImageUrl = ""

    stage('Git Clone') {
        git credentialsId: 'b97dbaa3-d9f0-4f73-ae6a-bc46bae691d4', url: 'https://github.com/zljin/surge.git', branch: "${params.branch}"
    }
    stage('Test') {
        sh("mvn test")
    }
    stage('Build Image and Push') {
        sh("mvn clean install -DskipTests")
        sh("docker build -t ${imageNameTag} .")
        sh("docker images")
        sh("docker login -u ${nexusUser} -p ${nexusPassword} ${nexusRepo}")
        sh("docker tag ${imageNameTag} ${nexusRepo}/${imageNameTag}")
        sh("docker images")
        sh("docker push ${nexusRepo}/${imageNameTag}")
        promoteImageUrl = "${nexusRepo}/${imageNameTag}"
    }
    stage('Deploy') {
        sh("cat ./yaml/release.yaml > ${promoteImageUrl}.yaml ")
        sh("sed -i 's/IMAGE_PARAM/${promoteImageUrl}' ${promoteImageUrl}.yaml ")
        sh("kubectl -n ${params.NAMESPACE} -f ${promoteImageUrl}.yaml")
    }
}