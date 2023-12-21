FROM        maven:3.8.5-openjdk-17-slim as builder

WORKDIR     /app

COPY        target/spring-boot-template.jar .

RUN echo "#!/bin/bash" > /bin/entrypoint.sh

RUN echo "nohup java -jar /app/spring-boot-template.jar" >> /bin/entrypoint.sh

RUN chmod +x /bin/entrypoint.sh
CMD ["entrypoint.sh"]