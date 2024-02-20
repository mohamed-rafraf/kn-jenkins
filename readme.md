# kn-jenkins Helm Chart

Welcome to the repository of the kn-jenkins Helm chart! This chart is a specialized Jenkins distribution designed to integrate seamlessly with the KubeSphere DevOps system. kn-jenkins extends the capabilities of the standard Jenkins server by pre-installing essential plugins and configurations, offering an out-of-the-box solution tailored for modern CI/CD pipelines.

## Why kn-jenkins ? 

kn-jenkins is derived from the robust ks-jenkins chart, inheriting its stability and flexibility while using the latest jenkins version and plugins that make it ready to deploy in a KubeSphere environment. With kn-jenkins, you get a Jenkins server that includes:

* Pre-installed plugins optimized for performance and functionality.
* Custom configurations that cater to KubeSphere DevOps workflows.
* Easy scalability within Kubernetes clusters.

## Getting Started

### Installation 

Add the kn-jenkins Helm repository:

```bash
helm repo add kn-jenkins https://mohamed-rafraf.github.io/kn-jenkins
helm repo update
```

Install the kn-jenkins chart:

```bash
helm install my-kn-jenkins kn-jenkins/kn-jenkins
```

### Upgrading 

To upgrade your kn-jenkins release to a newer version:

```bash
helm upgrade my-kn-jenkins kn-jenkins/kn-jenkins
```

### Uninstallation

To remove the kn-jenkins deployment from your cluster:

```bash
helm delete my-kn-jenkins
```