# Spring Boot - ActiveMQ #

This is a sample for Spring JMS by using ActiveMQ implementation.

#### How to run

```bash
# Start docker container 
cd ./docker-compose && docker-compose up -d

# Start application
mvc spring-boot:run
```





#### Things need to know

- Message Sender : a scheduled service to send a text message to a queue by using JmsTemplate
- Message Consumer : a scheduled service to get text message from the queue by using JmsTemplate
- Message Publisher: 
- Message Subscriber: