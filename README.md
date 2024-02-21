# kn-jenkins for Kubesphere DevOps System

Welcome to the `kn-jenkins` repository, where we fine-tune Jenkins to operate within the KubeSphere DevOps system. Here, you'll find essential settings, groovy scripts, and deployment manifests required to set up a Jenkins environment that is optimized for Kubernetes and integrated with LDAP for authentication.

This repository streamlines the process of testing and implementing changes in your local environment before updating the charts for a new release, ensuring a smooth and reliable development workflow.

You will find the hardcoded configurations and kubernetes manifests inside `config/` and `kube/` directories. You can test and implement changes easily using these files before start working on helm charts.

## Quickstart with kn-jenkins

To get started with kn-jenkins, you can explore the following directories:

* **`config/`**: Contains the core Jenkins configuration files and groovy scripts.
* **`kube/`**: Holds Kubernetes manifests for deploying and managing Jenkins on a local cluster, complete with LDAP integration.

These resources are intended to ease the local development and testing phases, enabling you to verify your Jenkins configurations before proceeding with broader deployments

## Getting Started with Local Testing
 

To begin local testing:

* **Set up a Kubernetes Cluster**: Make sure you have a Kubernetes cluster available for local development.

* **Configuration Changes**: Dive into the `config/` directory to tweak the groovy scripts and XML files. These are the very scripts and configurations that Jenkins will utilize, so any modifications here will reflect in your Jenkins setup.

* **Kubernetes Manifest Adjustments**: Visit the `kube/` directory if you need to make changes to the Kubernetes manifests.

* **Jenkins Configuration as Code**: The setup integrates Jenkins' configuration as a code manifest directly as a ConfigMap within the Kubernetes manifests. This means you can make direct changes to these manifests to alter the Jenkins configuration.

## Applying & Testing Changes

Once you've made your desired changes in the config/ directory or the Jenkins Configuration as Code (CasC) files:

**Test Your Changes:** Deploy your Jenkins instance on your local Kubernetes cluster by running:

```bash
make kube-run
```
This command will apply your changes and start Jenkins with the new configurations.

**Reset Your Environment:** If you need to start over or reapply changes, you can delete your current setup with:

```bash
make kube-delete
```

It's recommended to use this command to ensure a clean state before redeploying, especially when testing new configurations. After cleaning up, redeploy with `make kube-run` to see your changes in action.


## KN-Jenkins Helm Chart

For a seamless deployment of Jenkins tailored for KubeSphere DevOps, check out the Helm chart:

- **Helm Chart Repository**: [kn-jenkins Helm Chart](https://mohamed-rafraf.github.io/kn-jenkins)
- **Purpose**: This Helm chart provides a Jenkins server with pre-installed plugins and configurations, optimized for immediate use within KubeSphere DevOps systems.
