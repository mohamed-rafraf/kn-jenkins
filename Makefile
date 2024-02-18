# Default Jenkins version
JENKINS_VERSION := 2.426.3-jdk17

# DEFAULT ADMIN
ADMIN_USER := "admin"

# DEFAULT ADMIN_PASS
ADMIN_PASS := "admin"

# Name of the custom Jenkins image
IMAGE_NAME := ghcr.io/mohamed-rafraf/kn-jenkins

build:
	docker build --build-arg JENKINS_VERSION=$(JENKINS_VERSION) -t $(IMAGE_NAME):$(JENKINS_VERSION) .

# Example: make build-jenkins JENKINS_VERSION=2.289.1-jdk11
build-jenkins:
	@$(MAKE) build JENKINS_VERSION=$(JENKINS_VERSION)

push:

	docker push $(IMAGE_NAME):$(JENKINS_VERSION)

docker-run:
	docker run --rm --name kn-jenkins \
		-e ADMIN_USER=$(ADMIN_USER) \
		-e JENKINS_PASSWORD=$(ADMIN_PASS) \
		-p 8080:8080 $(IMAGE_NAME):$(JENKINS_VERSION)