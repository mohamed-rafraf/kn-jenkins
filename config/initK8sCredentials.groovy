import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.domains.Domain
import org.csanchez.jenkins.plugins.kubernetes.ServiceAccountCredential

def addKubeCredential(String credentialId) {
  def kubeCredential = new ServiceAccountCredential(CredentialsScope.GLOBAL, credentialId, 'Kubernetes service account')
  SystemCredentialsProvider.getInstance().getStore().addCredentials(Domain.global(), kubeCredential)
}

addKubeCredential('k8s-service-account')
