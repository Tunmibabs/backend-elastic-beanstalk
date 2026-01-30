# Deploying a Dockerized Spring Boot Application to AWS ECS (Fargate)

## Overview
In this phase, the Docker image built in previous phases was deployed to AWS ECS using the Fargate launch type. Fargate allows containers to run without managing EC2 instances, enabling a fully serverless container deployment.

## Architecture
Spring Boot Application  
→ Docker Image  
→ Amazon ECR  
→ ECS Task Definition  
→ ECS Service (Fargate)

## Steps Performed

### 1. ECS Cluster Creation
An ECS cluster named `backend-app-cluster` was created using the Fargate infrastructure. The default VPC was used to simplify networking.

### 2. Task Definition
A Fargate task definition was created to describe how the container should run:
- CPU: 0.5 vCPU
- Memory: 1 GB
- Container image pulled from Amazon ECR
- Container port 8080 exposed
- Environment variable `PORT=8080` configured

### 3. ECS Service
An ECS service was created to ensure the container remains running:
- Desired count set to 1
- Public IP enabled
- Security group configured to allow inbound traffic on port 8080

### 4. Verification
The application was accessed using the public IP assigned to the running task, confirming successful deployment.

## Key Learnings
- Difference between ECS tasks and services
- How Fargate abstracts server management
- Container networking in AWS
- Secure image storage using ECR

## Outcome
The application was successfully deployed in a production-style environment using AWS ECS Fargate and is accessible over the internet.


## Troubleshooting: Task Reachability (The "504/Timeout" Issue)
Issue: Task status is RUNNING, but Public IP is unreachable in the browser.

Diagnostic Steps Taken:

    Security Group Audit: Verified that the Fargate Task Security Group explicitly allows inbound traffic on port 8080.

    Logs Inspection: Checked CloudWatch logs to confirm the Spring Boot application started successfully without PortAlreadyInUse or ConnectionRefused errors.