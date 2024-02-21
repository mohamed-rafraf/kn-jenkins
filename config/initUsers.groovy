import jenkins.model.*
import hudson.security.*

def adminUsername = System.getenv('ADMIN_USER')
def adminPassword = System.getenv('ADMIN_PASSWORD')

// avoid reset the user
if (hudson.model.User.getById(adminUsername, false) == null) {
  def hudsonRealm = new HudsonPrivateSecurityRealm(false)
  hudsonRealm.createAccount(adminUsername, adminPassword)

  def instance = Jenkins.getInstance()
  instance.setSecurityRealm(hudsonRealm)
  instance.save()
}
