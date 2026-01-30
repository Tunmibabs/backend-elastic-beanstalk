## Container Registry (Amazon ECR)
In this stage, the focus was on moving the application image from the local Docker environment to the AWS cloud using Amazon Elastic Container Registry (ECR). This serves as the secure bridge to future deployment on ECS

## Key Activities
    Private Repository Creation: Provisioned a private ECR repository named backend-app to store versioned Docker images.

    IAM & CLI Authentication: Used the AWS CLI to retrieve an authentication token and log the local Docker client into the AWS registry.

    Image Tagging: Re-tagged the local Spring Boot image to match the ECR repository URI format: ${AWS_ACCOUNT_ID}.dkr.ecr.${REGION}.amazonaws.com/backend-app:latest.

    Image Migration: Successfully pushed the image layers to the cloud registry.


## Troubleshooting: Obstacles & Solutions
## Error: AccessDeniedException (GetAuthorizationToken)
    The Error: An error occurred (AccessDeniedException) when calling the GetAuthorizationToken operation: User: arn:aws:iam::...:user/vprofile-s3-admin is not authorized to perform: ecr:GetAuthorizationToken on resource: *

        The Cause: The IAM user being used (vprofile-s3-admin) had administrative rights for S3 but lacked permissions for ECR. In AWS, GetAuthorizationToken is a global permission required before any other ECR action can be taken.

            The Solution: * Attached the AmazonEC2ContainerRegistryPowerUser managed policy to the IAM user.

                Verified the policy allowed ecr:GetAuthorizationToken on Resource: "*".


## Verification
        Command: aws ecr describe-images --repository-name backend-app

        Result: Confirmed the image digest and the latest tag are now visible in the AWS Console, ready for deployment.