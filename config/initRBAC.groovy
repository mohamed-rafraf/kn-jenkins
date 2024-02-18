import hudson.*
import hudson.model.*
import hudson.security.*
import jenkins.*
import jenkins.model.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*
import com.synopsys.arc.jenkins.plugins.rolestrategy.*

def env = System.getenv()

// Roles
def globalRoleRead = "kubesphere-user"
def globalRoleAdmin = "admin"
def ldapUserNameAdmin = "admin"


// Initialize Jenkins and RoleBasedAuthorizationStrategy
def jenkinsInstance = Jenkins.getInstance()
RoleBasedAuthorizationStrategy rbas = new RoleBasedAuthorizationStrategy()



// Create admin set of permissions
Set<Permission> adminPermissions = new HashSet<Permission>();
adminPermissions.add(Permission.fromId("hudson.model.View.Delete"));
println "Passing hudson.model.View.Delete"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Connect"));
println "Passing hudson.model.Computer.Connect"
adminPermissions.add(Permission.fromId("hudson.model.Run.Delete"));
println "Passing hudson.hudson.model.Run.Delete"
adminPermissions.add(Permission.fromId("com.cloudbees.plugins.credentials.CredentialsProvider.ManageDomains"));
println "Passing com.cloudbees.plugins.credentials.CredentialsProvider.ManageDomains"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Create"));
println "Passing hudson.model.Computer.Create"
adminPermissions.add(Permission.fromId("hudson.model.View.Configure"));
println "Passing hudson.model.View.Configure"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Build"));
println "Passing hudson.model.Computer.Build"
adminPermissions.add(Permission.fromId("hudson.model.Item.Configure"));
println "Passing hudson.model.Item.Configure"
adminPermissions.add(Permission.fromId("hudson.model.Hudson.Administer"));
println "Passing hudson.model.Hudson.Administer"
adminPermissions.add(Permission.fromId("hudson.model.Item.Cancel"));
println "Passing hudson.model.Item.Cancel"
adminPermissions.add(Permission.fromId("hudson.model.Item.Read"));
println "Passing hudson.model.Item.Read"
adminPermissions.add(Permission.fromId("com.cloudbees.plugins.credentials.CredentialsProvider.View"));
println "Passing com.cloudbees.plugins.credentials.CredentialsProvider.View"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Delete"));
println "Passing hudson.model.Computer.Delete"
adminPermissions.add(Permission.fromId("hudson.model.Item.Build"));
println "Passing hudson.model.Item.Build"
adminPermissions.add(Permission.fromId("hudson.scm.SCM.Tag"));
println "Passing hudson.scm.SCM.Tag"
adminPermissions.add(Permission.fromId("hudson.model.Item.Discover"));
println "Passing hudson.model.Item.Discover"
adminPermissions.add(Permission.fromId("hudson.model.Hudson.Read"));
println "Passing hudson.model.Hudson.Read"
adminPermissions.add(Permission.fromId("com.cloudbees.plugins.credentials.CredentialsProvider.Update"));
println "Passing com.cloudbees.plugins.credentials.CredentialsProvider.Update"
adminPermissions.add(Permission.fromId("hudson.model.Item.Create"));
println "Passing hudson.model.Item.Create"
adminPermissions.add(Permission.fromId("hudson.model.Item.Move"));
println "Passing hudson.model.Item.Move"
adminPermissions.add(Permission.fromId("hudson.model.Item.Workspace"));
println "Passing hudson.model.Item.Workspace"
adminPermissions.add(Permission.fromId("com.cloudbees.plugins.credentials.CredentialsProvider.Delete"));
println "Passing com.cloudbees.plugins.credentials.CredentialsProvider.Delete"
adminPermissions.add(Permission.fromId("hudson.model.View.Read"));
println "Passing hudson.model.View.Read"
adminPermissions.add(Permission.fromId("hudson.model.View.Create"));
println "Passing hudson.model.View.Create"
adminPermissions.add(Permission.fromId("hudson.model.Item.Delete"));
println "Passing hudson.model.Item.Delete"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Configure"));
println "Passing hudson.model.Computer.Configure"
adminPermissions.add(Permission.fromId("com.cloudbees.plugins.credentials.CredentialsProvider.Create"));
println "com.cloudbees.plugins.credentials.CredentialsProvider.Create"
adminPermissions.add(Permission.fromId("hudson.model.Computer.Disconnect"));
println "hudson.model.Computer.Disconnect"
adminPermissions.add(Permission.fromId("hudson.model.Run.Update"));
println "hudson.model.Run.Update"
adminPermissions.add(Permission.fromId("hudson.model.Run.Replay"));
println "Passing hudson.model.Run.Replay"

// Create and add the admin role
Role adminRole = new Role("admin", adminPermissions)
rbas.getRoleMap(RoleType.Global).addRole(adminRole)

// Assign admin role to the specified user
rbas.getRoleMap(RoleType.Global).assignRole(adminRole, new PermissionEntry(AuthorizationType.USER, "admin"))

// Define permissions for read access role
Set<Permission> readPermissions = new HashSet<>()
readPermissions.add(Permission.fromId("hudson.model.Hudson.Read"))
// Add more permissions as needed...

// Create and add the read access role
Role readRole = new Role(globalRoleRead, readPermissions)
rbas.getRoleMap(RoleType.Global).addRole(readRole)

// Optionally, assign the read role to a group or another user
// rbas.getRoleMap(RoleType.Global).assignRole(readRole, new PermissionEntry(AuthorizationType.USER, "someUserOrGroup"))

// Set the new authorization strategy
jenkinsInstance.setAuthorizationStrategy(rbas)
jenkinsInstance.save()

println "Roles and permissions have been configured and saved."
