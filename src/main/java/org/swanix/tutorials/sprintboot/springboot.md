#SpringBoot
####SpringApplication
    @SpringBootApplication
    public class RestApiTutorial{
        public static void main(String[] args){
            SpringApplication.run(RestApiTutorial.class, args);
        }
    }
    
SpringApplication.run(Class.class,args) will do the following 
1. Set up default configuration
2. Starts Spring application context
3. Performs class path scan
4. Starts Tomcat Server
    
#####Controller
    @RestController
    public class RestApiController{
  
        @RequestMapping("/hello")
        public String sayHello(){
            return "Hello";
        }
    }
    
**@RestController** marks the class as a controller so that can it can serve the incoming request by invoking the correct method.
It also automatically going to convert everything we return to a jsonResponse.

