## Springboot/Micronaut comparison

### Tasks list
#### Setup and simple test of endpoints

1. Add starter java 11 initializer for mn and springboot

2. Add `Greeting` Bean and `GreetingController`. Setup to application.yml and port to 8081.

3. Setup and test `Greeting` bean methods

4. Unit test `HomeControllerTest` to check for response

5. Integration test `GreetingControllerTest` to check for response

6. Setup and test `GreetingTest` to check for response ok and content

7. Implement UserService
   7.1 Create a User bean
   7.2 Create a UserDAOService
   DAO stands for Data Access Object. It’s a design pattern in which a data access object (DAO) 
   is an object that provides an abstract interface to some type of database or other persistence
   mechanisms. By mapping application calls to the persistence layer, DAOs provide some specific 
   data operations without exposing details of the database. This isolation supports the Single 
   responsibility principle. It separates what data accesses the application needs, in terms of 
   domain-specific objects and data types (the public interface of the DAO), and how these needs 
   can be satisfied with a specific DBMS, database schema, etc. (the implementation of the DAO).
   
```
    - Retrieve all Users    - GET       /users
    - Create a User         - POST      /users
    - Retrive one User      - GET       /users/{id}
    - Delete a User         - DELETE    /users/{id}
```
