# Quiz Your Skill

Quiz Your Skill is a simple quiz app which was made as part of the "Plattformen und Systeme fürs eLearning" course at the Goethe University Frankfurt.

## Current Features
By starting the app and logging in with the username "user" and the password "user", 
you can see an example course including a learning unit and a quiz. There is no way to create new courses or learning units inside the app yet. 
In the first version the app is only able to display the courses without an evaluation of the answers.

## Deploying and running the application

The project can be built using the `mvn clean package -Pproduction` command. 
Then start the application using Spring Boot and open http://localhost:8080 in your browser.

## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
  [App Layout](https://vaadin.com/docs/components/app-layout).
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

## Database structure
![Show database structure](https://user-images.githubusercontent.com/15930548/205279259-bbbb5957-be79-42cf-8055-80cc18f48aa1.png)

## Useful links

- Project was created by using [start.vaadin.com](https://start.vaadin.com/).
- [Vaadin Flow](https://vaadin.com/flow) documentation.
- [PostgreSQL](https://www.postgresql.org/docs/) documentation.
