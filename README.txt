To Compile the Project:
	mvn clean install

Using exec to run the WebCrawler Alone:
	mvn exec:java -Dexec.args="seed limit"

To run Front-end: (Front End is just static css tbh so no point in running this)
	npm install
	npm install axios
	mvn spring-boot:run
	npm start

To see the results of the data from the crawler:
	Download MongoDB Compass Community
	Connect to localhost on port 27017




