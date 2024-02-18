mkdir -p /usr/share/jenkins/ref/secrets/;
echo "false" > /usr/share/jenkins/ref/secrets/slave-to-master-security-kill-switch;
cp --no-clobber /var/jenkins_config/config.xml /var/jenkins_home;
cp --no-clobber /var/jenkins_config/jenkins.CLI.xml /var/jenkins_home;
cp --no-clobber /var/jenkins_config/jenkins.model.JenkinsLocationConfiguration.xml /var/jenkins_home;
mkdir -p /var/jenkins_home/init.groovy.d/;
yes | cp -i /var/jenkins_config/*.groovy /var/jenkins_home/init.groovy.d/
