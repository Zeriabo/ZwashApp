FROM openjdk@sha256:a996cdcc040704ec6badaf5fecf1e144c096e00231a29188596c784bcf858d05
COPY build/libs/Zwash-0.0.1.jar Zwash-0.0.1.jar
ENTRYPOINT ["java","-jar","/Zwash-0.0.1.jar"]