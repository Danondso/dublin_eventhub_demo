## Event Hub Tech Demo ##


### What is this? ###

This app I put together to teach myself about event hub. It's a Spring Boot web service that sends a message to event hub, then consumes it and prints it to console.

### How do I run it? ###

You'll need to setup an azure account and event hub first. Plug in your namespace, key and all that magic into the yml file. Once that's out of the way. There's a Dockerfile in the repo. Run the following at the project root and you should be good to go.

~~~~
mvn package
docker build -t dublin_eventhub_demo_container .
docker run -p 8080:8080 dublin_eventhub_demo_container
~~~~

### Sample Message Format ###

```json
{
  "firstName": "test",
  "lastName": "testLastName",
  "email": "anotherTestEmail@dogpile.com",
  "favoriteFood": "bananas"
}
```