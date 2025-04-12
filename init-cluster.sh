#!/bin/bash

# Inicializar el clúster de Minikube
echo "Iniciando el clúster de Kubernetes en Minikube..."
minikube start --driver=docker

# Establecer kubectl para que se conecte al clúster de Minikube
echo "Configurando kubectl para conectarse al clúster..."
kubectl config use-context minikube

# Esperar a que Minikube se inicie correctamente
echo "Esperando a que Minikube esté listo..."
sleep 30  # Ajusta este tiempo según sea necesario para dar tiempo a Minikube

# Desplegar RabbitMQ
echo "Desplegando RabbitMQ en el clúster..."
kubectl apply -f ./k8s/rabbitmq-deployment.yaml
kubectl apply -f ./k8s/rabbitmq-service.yaml

# Desplegar la aplicación de lógica (logic)
echo "Desplegando la aplicación lógica..."
kubectl apply -f ./k8s/logic-deployment.yaml
kubectl apply -f ./k8s/logic-service.yaml

# Desplegar el HorizontalPodAutoscaler (si se requiere escalabilidad)
echo "Desplegando el HorizontalPodAutoscaler para la aplicación lógica..."
kubectl apply -f ./k8s/logic-hpa.yaml

# Verificar que todos los pods estén corriendo
echo "Verificando los pods desplegados..."
kubectl get pods

# Comprobar que los servicios estén expuestos correctamente
echo "Verificando los servicios expuestos..."
kubectl get services

# Imprimir el estado de los nodos
echo "Verificando el estado de los nodos de Kubernetes..."
kubectl get nodes

# Si es necesario, obtener la URL de la aplicación lógica
echo "Accediendo a la URL de la aplicación lógica..."
minikube service logic --url
