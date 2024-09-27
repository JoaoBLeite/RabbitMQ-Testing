# RabbitMQ-Testing

This repository contains a simple setup for testing RabbitMQ message exchanges between services.

## Running RabbitMQ

To start RabbitMQ using Docker, run the following command:

```sh
docker run -d \
  --hostname rmq \
  --name rabbit-server \
  -p 5672:5672 \
  -p 15672:15672 \
  rabbitmq:3.8.6-management
```

If the container is already created but just stopped, you can start it using:

```sh
docker start rabbit-server
```

This will bring the existing container back up without needing to create a new one.

### Access the Admin Console

- URL: localhost:15672
- Default credentials:
  - Username: ``` guest ```
  - Password: ``` guest ```

## RabbitMQ Setup

### Queues
Create the following queues:

- ``` email_notification_queue ```
- ``` email_notification_response_success_queue ```
- ``` email_notification_response_error_queue ```

### Exchanges
Create the following exchanges:

- ``` email_notification_exchange ```
- ``` email_notification_response_success_exchange ```
- ``` email_notification_response_error_exchange ```

### Bindings
Bind the exchanges to the queues using the following routing keys:

- ``` email_notification_rout_key ```
- ``` email_notification_response_success_rout_key ```
- ``` email_notification_response_error_rout_key ```

## Service demo-backend-notification-worker Setup

In the ``` application.yml ``` file make sure to replace the environment variables or create them with your own personal values. This way the application will have access to an email to send emails.

```sh
username: ${MAIL_USERNAME}
password: ${MAIL_PASSWORD}
```

## Testing the Setup

### Run services

Ensure that ``` demo-backend-api ``` and ``` demo-backend-notification-worker ``` are up and running. These services will communicate through RabbitMQ.

### Sending a test request

Use the following cURL command to send a test request to ``` demo-backend-api ```:

```sh
curl --location 'http://localhost:8080/notification' \
  --header 'Content-Type: application/json' \
  --data-raw '{
    "email": "yourEmailAddress@gmail.com",
    "title": "Testing RabbitMQ",
    "content": "Sending this email from async API"
  }'
```

Make sure to replace ``` yourEmailAddress@gmail.com ``` for your own email address.

### Check Logs

Inspect the logs of both applications to verify the message flow and ensure that the setup is working as expected.

``` demo-backend-api ``` Logs: Check for the outgoing messages to RabbitMQ.

``` demo-backend-api ``` Logs: Check for the incoming messages and email sending status.

## Finishing Project execution

### After finishing testing the project, run the following command to stop RabbitMQ Docker container

```sh
docker stop rabbit-server
```

## Troubleshooting

- RabbitMQ Not Starting: Ensure Docker is running and there are no port conflicts.
- Authentication Issues: Verify that you are using the correct default credentials (guest/guest).
- Queue/Exchange Configuration: Double-check the queue and exchange names and bindings.
