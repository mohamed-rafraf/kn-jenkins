import hudson.model.*
import jenkins.model.*
import hudson.plugins.sonar.*
import hudson.plugins.sonar.model.TriggersConfig
import hudson.tools.*


def env = System.getenv()
def instance = Jenkins.getInstance()
// Check sonarqube enable
if (env['SONAR_ENABLED'] == null || !env['SONAR_ENABLED'].toBoolean()) {
    println "--> SonarQube Disabled"
    return
}

def sonar_server_url = env['SONAR_SERVER_URL']
def sonar_auth_token = env['SONAR_AUTH_TOKEN']
def sonar_plugin_version = env['SONAR_PLUGIN_VERSION']
def sonar_additional_props = env['SONAR_ADDITIONAL_PROPS']


def SonarGlobalConfiguration sonar_conf = instance.getDescriptor(SonarGlobalConfiguration.class)

def sonar_inst = new SonarInstallation(
        "sonar", // Name
        sonar_server_url,
        sonar_auth_token,
        sonar_plugin_version,
        sonar_additional_props,
        new TriggersConfig(),
        "" // Additional Analysis Properties
)

def sonar_installations = sonar_conf.getInstallations()
def sonar_inst_exists = false
def sonar_exists_index = 0
sonar_installations.eachWithIndex {
    it, index ->
    installation = (SonarInstallation) it
    if (sonar_inst.getName() == installation.getName()) {
      sonar_inst_exists = true
      sonar_exists_index = index
      println("Found existing installation: " + installation.getName() + " : " +index)
    }
}

if (sonar_inst_exists) {
    sonar_installations[sonar_exists_index] = sonar_inst
    sonar_conf.setInstallations((SonarInstallation[]) sonar_installations)
    sonar_conf.save()
}else{
    sonar_installations += sonar_inst
    sonar_conf.setInstallations((SonarInstallation[]) sonar_installations)
    sonar_conf.save()
}


def sonar_runner_version = env['SONAR_RUNNER_VERSION']

println "--> Configuring SonarRunner"
def desc_SonarRunnerInst = instance.getDescriptor("hudson.plugins.sonar.SonarRunnerInstallation")

def sonarRunnerInstaller = new ZipExtractionInstaller('',"http://uc-jenkins-update-center/sonar-scanner-cli/sonar-scanner-cli-"+sonar_runner_version+".zip","sonar-scanner-"+sonar_runner_version)
def installSourceProperty = new InstallSourceProperty([sonarRunnerInstaller])
def sonarRunner_inst = new SonarRunnerInstallation("sonar", "", [installSourceProperty])

def sonar_runner_installations = desc_SonarRunnerInst.getInstallations()
def sonar_runner_inst_exists = false
def sonar_runner_exists_index = 0
sonar_runner_installations.eachWithIndex {
    it, index ->
    installation = (SonarRunnerInstallation) it
    if (sonarRunner_inst.getName() == installation.getName()) {
        sonar_runner_inst_exists = true
        sonar_runner_exists_index = index
        println("Found existing installation: " + installation.getName())
    }
}

if (sonar_runner_inst_exists) {
    sonar_runner_installations[sonar_runner_exists_index] = sonarRunner_inst
    desc_SonarRunnerInst.setInstallations((SonarRunnerInstallation[]) sonar_runner_installations)
    desc_SonarRunnerInst.save()
} else{
    sonar_runner_installations += sonarRunner_inst
    desc_SonarRunnerInst.setInstallations((SonarRunnerInstallation[]) sonar_runner_installations)
    desc_SonarRunnerInst.save()
}

// Save the state
instance.save()
