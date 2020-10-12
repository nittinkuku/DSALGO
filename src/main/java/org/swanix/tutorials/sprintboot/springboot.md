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

#####Service

    @Service
    public class RestApiTutorialService{
    }

    @RequestMapping(“/topics/{id}“)
    public Topics getTopic(@PathVariable(“id") String id){
    }

    @RequestMapping(method=RequestMethod.POST, value=“/topics”)
    public void addTopic(@RequestBody Topic topic){
    }

**Application Properties:**

    server.port=8081 	//deault is 8080

#####Repository
    @Entity
    public class Topic{
    	@Id
    	private String id;
    	private String name;
    	private String description;
    }

    @Repository
    public interface TopicRepository extends CrudRepository<Topic, String>{
    		// All crud operations implementation already provided by Spring, don’t need to write a single line of code
    }


    @Service
    public class RestApiTutorialService{
    	
    	@Autowired
    	private TopicRespository topicRespository;
    
    	//topicRespository.findAll();
    	//topicRepository.findOne(id);
    	//topicRepository.save(topic);   		
    	//topicRepository.delete(id);
    }
    
    save can be used for both insert and update. Based on the id of the object, it will check if the entry is present or not. In case it is present it will update it else it will insert.

    @Entity
    public class Course{
    	@Id
    	private String id;
    	private String name;
    	private String description;
    
    	@ManyToOne  				// many courses can be attached to one topic
    	private Topic topic;
    }

    @Repository
    public interface CourseRepository extends CrudRepository<Course, String>{
    	// All crud operations implementation already provided by Spring, don’t need to write a single line of code
    
    	//more complicated method can also be defined using a set of rules and spring will provide the implementation for them
    	public List<Course> findByName(String name);
    	public List<Course> findByTopicId(String topicId);
    }

    @Service
    public class RestApiTutorialService{
    	
    	@Autowired
    	private CourseRepository courseRespository;
    
    	//courseRespository.findAll();
    	//courseRepository.findOne(id);
    	//courseRepository.delete(id);
    	//Course course = new Course(new Topic(id));
    	//courseRepository.save(course);   	
    }


##Actuator

    management.port=9001            //default is 8080
    http://localhost:8080/health



#JWT

JWT (Json Web Token) is used for authorization. After the user is authenticated by server, it creates a JWT token for the client and give it. This JWT is then sent by client with every subsequent request to the server.

**Headers:**
	Authorization: Bearer JWT


**JWT Structure:**

It has 3 parts separated by **.** (DOT)

    Header.Payload.Signature
    
    Header    		//encoded in base 64 
    Payload 		//encoded in base 64
    Signature		//signature given by server based on the values of header, payload and a secret key which only server knows	

Even if a malicious client changes the values in payload or header, the server would be able to tell as the signature for these new values will not match with what is already there in the JWT.
JWT does not contain any sensitive information like password etc. but just enough to identify the user.

**Notes:** 
1. Encoded base 64 - base 64 allows you to convert normal string into a stream of characters.
2. There should not be any sensitive information in the payload as it can be easily decoded and read.
