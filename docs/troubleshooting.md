### Issue: 502 Bad Gateway on Elastic Beanstalk

**Cause**

- Application was listening on port 8080
- Elastic Beanstalk routes traffic on port 80

**Fix**

- Updated Spring Boot config:
  server.port=${PORT:8080}

**Verification**

- Confirmed Java process listening on EB port
