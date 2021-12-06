echo %1
docker build . -t %1
(Get-Content .\k8s\deployment.yaml -Raw).replace('$appname','%1') | kubectl apply -f -
kubectl apply -f ./k8s/deployment.yaml
kubectl apply -f ./k8s/service.yaml
minikube service my-simple-app