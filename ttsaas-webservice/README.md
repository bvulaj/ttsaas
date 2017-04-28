# Running
* `mvn spring-boot:run`
* `java -jar ttsaas-webservice.jar`

# Talking
* `curl -H Content-Type:application/json -X POST http://localhost:8080/tts -d '{"text":"hello"}'`
* Form @ `http://localhost:8080`

# Tracking
* `http://localhost:8080/manage/textlog
  * Other useful endpoints listed underneath `/manage`
