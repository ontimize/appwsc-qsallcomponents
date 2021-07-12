
# Ontimize Boot
This branch contains the project that generates the qsallcomponents backend project.

- Enter the parent directory and run an install:
	
		mvn install

## Start the database

- Enter the `model` folder and execute the command

		mvn exec:java -Prun_database
	
## Start the server: 
- Go to the `boot` folder and run the command

		mvn spring-boot:run
	
# Ontimize Web
- Download the project from repository: [https://github.com/OntimizeWeb/ontimize-web-ngx-quickstart](https://github.com/OntimizeWeb/ontimize-web-ngx-quickstart)
 
- Go to the `environment.ts` file
		
		src/main/environments/environment.ts
		
- Change the `apiEndpoit` url
 
		http://localhost:8080/qsallcomponents-jee](http://localhost:8080/qsallcomponents-jee) 
