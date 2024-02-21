import jenkins.model.*

def env = System.getenv()

def emailFromName = env.EMAIL_FROM_NAME
def emailFromAddr = env.EMAIL_FROM_ADDR

def locationConfig = JenkinsLocationConfiguration.get()
locationConfig.adminAddress = "${emailFromName} <${emailFromAddr}>"
locationConfig.save()

def mailer = Jenkins.instance.getDescriptor("hudson.tasks.Mailer")
mailer.setSmtpAuth(emailFromAddr, env.EMAIL_FROM_PASS)
mailer.setReplyToAddress("no-reply@k8s.kubesphere.io")
mailer.setSmtpHost(env.EMAIL_SMTP_HOST)
mailer.setUseSsl(env.EMAIL_USE_SSL.toBoolean())
mailer.setSmtpPort(env.EMAIL_SMTP_PORT)
mailer.save()
