#!/usr/bin/env bash

set -o errexit
set -o nounset
set -o pipefail

function check_devops_ok(){
    echo "waiting for ks-devops system"
    kubectl wait --for=condition=ready pod --all -n kubesphere-devops-system --timeout=2m || kubectl get pods -n kubesphere-devops-system
    echo "waiting for DevOps Controller ready"
    while IFS= read -r line; do
        echo "$line"
        if [[ $line =~ "synced key:kubesphere-devops-system/jenkins-casc-config" ]]
            then
                return
        fi
    done < <(timeout 1800 kubectl logs -n kubesphere-devops-system deploy/ks-devops-controller -f --tail 1)
    echo "The Jenkins Configuration as Code is Not Synced"
    exit 1
}

function wait_status_ok(){
    for ((n=0;n<30;n++))
    do
        OK=`kubectl get pod -A| grep -E 'Running|Completed' | wc | awk '{print $1}'`
        Status=`kubectl get pod -A | sed '1d' | wc | awk '{print $1}'`
        echo "Success rate: ${OK}/${Status}"
        if [[ $OK == $Status ]]
        then
            n=$((n+1))
        else
            n=0
            kubectl get pod -A | grep -vE 'Running|Completed'
        fi
        sleep 1
    done
}

export -f wait_status_ok

check_devops_ok

timeout 1800 bash -c wait_status_ok