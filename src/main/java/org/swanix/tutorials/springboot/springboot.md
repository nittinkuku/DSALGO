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




