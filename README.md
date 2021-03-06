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
   
7.3 Create a UserResource to list information
```
    - Retrieve all Users    - GET       /users
    - Retrive one User      - GET       /users/{id}
```
7.4 Create a UserResource to input details of a user
```
    - Delete a User         - DELETE    /users/{id}
    - Create a User         - POST      /users
```
7.5 Create a UserResource handle not found user

7.6 Return status CREATED after input details of a user

7.7 Create a custom ExceptionResponse based on ResponseEntityExceptionHandler
```
class ExceptionResponse {
       @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(                   // new instance of our exception bean
                        new Date(),                      // timestamp
                        ex.getMessage(),                 // message
                        request.getDescription(false));  // detail

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    } 
}
```

7.8 Return a different `HttpStatus.NOT_FOUND` for `UserNotFoundException`

7.9 Improve exception handling using @ControllerAdvice

We don't need to catch any exception at each method or class separately instead you can just
throw the exception from the method and then it will be caught under the central exception 
handler class annotated by @ControllerAdvice
```
   - Retrieve all Users    - GET       /users
   - Retrive one User      - GET       /users/{id}
   - Create a User         - POST      /users
```
WITHOUT @ControllerAdvice we have endless try catch blocks
```
   @GetMapping(path="/{userId}", produces = "application/json")
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public ResponseEntity<User> getEmployees(@PathVariable Long userId) {
        ResponseEntity<User> response = null;
        try { 
            if (something is wrong) {
               throw new SomeException("User Id is not valid");
            }
            return proper ResponseEntity
        }
        catch(SomeException e) {
            Logger.error("Something is invalid Input:",e.getMessage());
            response = new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {        
            Logger.error("System Error:",e.getMessage());
            response = new ResponseEntity<User>(user,HttpStatus.INTERNAL_SERVER_ERROR);
        }
```   
WITH @ControllerAdvice we can reuse the logic for different entities User, Post, etc
```
@ControllerAdvice
public class ExceptionHelper {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
    
    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        LOGGER.error("Invalid Input Exception: ",ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
 ```
7.10 Delete a resource from UserDaoService

