node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/ZOSOpenTools/cronieport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/ZOSOpenTools/cronieport.git'), string(name: 'PORT_DESCRIPTION', value: 'Cronie contains the standard UNIX daemon crond that runs specified programs at scheduled times and related tools' ), string(name: 'BUILD_LINE', value: 'DEV') ]
  }
}
