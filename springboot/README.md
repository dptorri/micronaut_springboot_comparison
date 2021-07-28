## Springboot/Micronaut comparison

### Tasks list
##### Setup and simple test of endpoints

##### 1. Add starter java 11 initializer for mn and springboot

##### 2. Add `Greeting` Bean and `GreetingController`. Setup to application.yml and port to 8081.

##### 3. Setup and test `Greeting` bean methods

##### 4. Unit test `HomeControllerTest` to check for response

##### 5. Integration test `GreetingControllerTest` to check for response

##### 6. Setup and test `GreetingTest` to check for response ok and content

##### 7. Implement UserService

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

7.11 Validation of user input using Java Validation API
For that we go to creation of new users and include the @Valid annotation on the resource
and also in the bean.
```
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    ...
    @Size(min=2)
    private String name;
```
7.12  Override HandleMethodArgumentNotValid to give additional details.
When binding to a specific method fails, HandleMethodArgumentNotValid gets called. So we 
override this method to give the consumer the details of what has been thrown.
```
{
    "timestamp": "2021-07-20T20:07:52.667+00:00",
    "message": "Validation failed for argument [0] in public org.springframework.http.ResponseEntity<java.lang.Object> springboot.rest.user.UserResource.createUser(springboot.rest.user.User): [Field error in object 'user' on field 'birthDate': rejected value [Sat Aug 05 14:32:13 CEST 2090]; codes [Past.user.birthDate,Past.birthDate,Past.java.util.Date,Past]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.birthDate,birthDate]; arguments []; default message [birthDate]]; default message [must be a past date]] ",
    "detail": "org.springframework.validation.BeanPropertyBindingResult: 1 errors\nField error in object 'user' on field 'birthDate': rejected value [Sat Aug 05 14:32:13 CEST 2090]; codes [Past.user.birthDate,Past.birthDate,Past.java.util.Date,Past]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [user.birthDate,birthDate]; arguments []; default message [birthDate]]; default message [must be a past date]"
}
```

7.13 Enhance `greeting` with Springboot starter HATEOAS
Add a link to the resource that retrieves all users is not the same as the URI.
Hypermedia as the Engine of Application State (HATEOAS) is a constraint of the REST 
application architecture that distinguishes it from other network application architectures.
With HATEOAS, a client interacts with a network application whose application servers provide 
information dynamically through hypermedia.
````
http://localhost:8081/greeting?name=User

{
    "content": "Hello, User!",
        "_links": {
            "self": {
            "href": "http://localhost:8081/greeting?name=User"
        }
    }
}
````
##### 8 Internationalization

Configuration

- Local Resolver
    - Default Locale - Locale.US
- ResourceBundleMessageSource

Usage:
- Autowire MessageSource
- `@RequestHeader(value = "Accept-Language", required = false) Locale locale`
- `messageSource.getMessage("helloWorld.message", null, locale)`

8.1 Create an international greeting for GreetingController

8.2 Create a Bean for the localeResolver and messageSource and update greetingI18n endpoint.
The locale es passed in the request header and default to english when not give.

8.3 Refactor SessionLocaleResolver and remove logic of Bean messageSource 

The implementation requires to include the "Accept-Language" Header in the controller. This 
can be accomplished by using the `LocaleContextHolder` provided by Spring

##### 9 Content negotiation

9.1 Generate content type XML/JSON depending on Accept Header

At the moment we are not setting how the content will be displayed in the headers.
- Make the jar available in Gradle Jackson XML
- Accept Header should be set to `application/xml` 

##### 10 Documentation using Swagger

10.1 Add SwaggerUI dependency
Swagger 
- Develop
- Interact
- Document

SOAP has WSDL for documentation

REST has no formal documentation in spec

Swagger autocreates documentation from metadata in JSON
```
http://localhost:8081/swagger-ui/index.html
...
http://localhost:8081/v3/api-docs
"openapi": "3.0.1",
"info": {},         // High level information
"servers": [
  {
    "url": "http://localhost:8081",
    "description": "Generated server url"
  }
],
"paths": {},        // all exposed resources
"components": {}
```

##### 11 Add Actuator and Hal browser

Add dependency `implementation 'org.springframework.boot:spring-boot-starter-actuator'` 
Monitoring our app, gathering metrics, understanding traffic, or the state of our 
database become trivial with this dependency.
by default, all Actuator endpoints are now placed under the /actuator path.
```
{
  "_links": {
    "self": {
      "href": "http://localhost:8081/actuator",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8081/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8081/actuator/health/{*path}",
      "templated": true
    }
  }
}
```

Add dependency 	`implementation 'org.springframework.data:spring-data-rest-hal-explorer:3.5.3'`

Redirect application to port 8080 and hal will be available


##### 12 Static filtering using @JsonIgnore and @JsonIgnoreProperties

12.1 Add sample Bean with 3 fields called mockbean

12.2 Add  sample filter and filter-list endpoint

12.3 Filter field1 with @JsonIgnore and field3 with @JsonIgnoreProperties

12.4 Filter with @JsonFilter("MockBeanFilter"), SimpleBeanPropertyFilter and MappingJacksonValue

```
{
    "field1": "value1",
    "field2": "value2"
}
```

12.5 Filter filtering-list endpoint @JsonFilter("MockBeanFilter")

```
2021-07-28 15:11:19.436  WARN 69953 --- [nio-8080-exec-2] .m.m.a.ExceptionHandlerExceptionResolver : 
Resolved [org.springframework.http.converter.HttpMessageConversionException: Type definition error: 
[simple type, class springboot.rest.mock.MockBean]; nested exception is com.fasterxml.jackson.databind.
exc.InvalidDefinitionException: Cannot resolve PropertyFilter with id 'MockBeanFilter'; 
no FilterProvider configured (through reference chain: java.util.Arrays$ArrayList[0])]

here you have to change the return type from List<MockBean> to MappingJacksonValue mapping

[
    {
        "field2": "fieldValue2a",
        "field3": "fieldValue3a"
    },
    {
        "field2": "fieldValue2b",
        "field3": "fieldValue3b"
    }
]
```

##### 13 Versioning 

13.1 Versioning with PersonVersioningController

TODO: Enhancing Swagger documentation for client API