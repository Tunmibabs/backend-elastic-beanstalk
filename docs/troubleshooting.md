2026-01-29 | Troubleshooting: 502 Bad Gateway (Port Mismatch)


The Issue
The Elastic Beanstalk (EB) environment returned a 502 Bad Gateway despite the Java process being active.

        Symptom: Nginx error logs reported (111: Connection refused) while connecting to upstream.

        Root Cause: Architecture mismatch. By default, the EB Nginx proxy forwards traffic to 127.0.0.1:5000. The Spring Boot application was listening on 8080, leaving port 5000 unresponsive.



The Fix
The goal was to align the application port with the environment's expectations without hardcoding.

        Code Update: Modified src/main/resources/application.properties to use the dynamic PORT variable provided by the EB platform, defaulting to 8080 for local development.

        Config: server.port=${PORT:8080}

        Infrastructure Update: (If applicable) Added an Environment Property in the EB Console:

        PORT = 8080

    Note: This forces Nginx to rebuild its config to point to 8080 instead of 5000.



Verification Checklist
Use these commands to confirm the bridge between Nginx and Java is closed:

        Check Nginx Routing: cat /etc/nginx/conf.d/elasticbeanstalk/webapp.conf | grep proxy_pass (Should now show the port your app is using.)

        Check Java Socket: sudo ss -tulnp | grep java (Ensure the PID is listening on the expected port.)

        Local Loopback Test: curl -I http://localhost:8080/health (Confirm the app responds to HTTP requests internally.)



Key Takeaway
On Amazon Linux platforms, the Proxy (Nginx) and App (Java) must agree on a port. If Nginx is looking at 5000 and Java is at 8080, a 502 is inevitable.