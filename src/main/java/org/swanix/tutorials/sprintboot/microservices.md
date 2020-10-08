#Micro Service Architecture using Spring Boot

Monolithic Architecture is like a big container wherein all the software components of an application are assembled together and tightly packaged.

#####Disadvantages of Monolithic Architecture:

1. **Large and Complex:** 
	As the size of the application grows, 
	modularity breaks down
	becomes very complex to understand.
	Makes the developement slower and it is difficult to understand the application and implement the change

2. 	**Slow developement:**
	Application becomes difficult to understand and implement the change
	With so much of code even the tools like ide is slower and makes developer less productive.
	
3.  **Less Frequent Developments:**
	In order to update one component, entire application needs to be redeployed. 
	Also there is a chance that if any component has not been updated, the application will fail to start completely.
	As the risk increases, it will discourage frequent deployments.	

4.  **Less Scalable and Less Reliable:**
	We can't scale each component individually. Entire applicatin needs to be replicated.
	Even if one component breaks down, entire application will be down.
	One component's bug can bring down the entire application.
	
5.	**Less Flexible:**
	If you want to change the underlying language/framework/tools, the entire application needs to rewritten with that
	You can't use different frameworks and different languages for different components within the application.
	

####Microservices:

Microservices, aka Microservice Architecture is an architectural style that structures an application as a collection
of small autonomous services, modelled around a Business Domain.
	
In Microservice Architecture, each service is self-contained and implements a Single Business capability.

**Management:**
		It is responsible for placing services on nodes, identifying failures and rebalancing services on nodes and so forth.
			
**Service Discovery:**
		It maintains the list of services and enables services to look up and find other services and which nodes they are located.
	
**API Gateway:** 
		It forwards the call to appropritate service on the backend. 
		It can even aggregate the response from multiple services and return the response.
		

#####Features of Micro Service:

- **Small Focused**:
	Services are written with one business logic or for one functionality. keeping them small and manageable.
- **Loosely coupled**: 
	services can be developed and deployed individually. 
- **Language Neutral**: 
	We can even use different languages, frameworks and tools to build the individual services.
- **Bounded Context**:
	One service does not need to know the functionality of other service.
	


#####Advantages of Microservices:

- Independent Development
- Independent Deployment
- Fault Isolation  
- Mixed Technology Stack  
- Granular Scaling









#Microservice with Spring Boot :

#####Eureka Server and Client
    @EnableEurekaServer  
    @EnableEurekaClient

#####Load Balancing
    @LoadBalanced

#####Circuit Breaker - Hystrix

    @EnableCircuitBreaker
    
    @HystrixCommand(fallbackMethod="fallbackMethodName")   //fallback method should have same signature as the original method
        @HystrixCommand(fallbackMethod = "fallbackMethodName",
        		commandProperties = {
        				@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
        				@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
        				@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
        				@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
        		}
        )
    
    @HystrixCommand(fallbackMethod="fallbackMethodName",
    	threadPoolKey = "movieInfoPool",
    	threadPoolProperties = {
    		@HystrixProperty(name = "coreSize", value = "20"),
    		@HystrixProperty(name = "maxQueueSize", value = "10")
    	}
    )

    @EnableHystrixDashboard
    
    management.endpoints.web.exposure.include=hystrix.stream



##SpringBoot Configuration:
#####Properties File:

**In code**  

    @Value("${my.greeting}")  
    private String greetingMessage;

**In properties File**  

    app.name=My app  
    app.description=Welcome to ${app.name}

**using command line**  

    java -jar jarfilename.jar --my.greeting=Hello

**external file**
if there is an application.properties file in the same folder where the jar file is present, 
then values in that file will overwrite the values which are present in the inside application properties file


    @Value("some static value")
    private String staticMessage;

    @Value("${my.greeting: default value}")
    private String defaultValue;


    my.list.value=one,two,three

    $Value("${my.list.value}")
    private List<String> listValues;

    dbValues={connectionString:'connectionString',username:'foo', password:'bar'}

    $Value("#{${dbValues}}")
    private Map<String, String> dbValues;


#####Configuration Properties

    @ConfigurationProperties
    
    db.connection={connectionString:'connectionString',username:'foo', password:'bar'}
    db.host=127.0.0.1
    db.port=1200
    
    @Configuration
    @ConfigurationProperties("db")
    public class DBSettings{
    	private String connection;
    	private String host;
    	private int port;
    	
    	//Getters and Setters
    }

    @Autowired
    private DBSettings dbsettings;


**Using Actuator to explore all the configured properties:**

    management.endpoints.web.exposure.include=*
    
    localhost:8080/actuator/configprops



####YAML :

    app:
    	name: My app	
    	description: Welcome to ${app.name}
    my:
    	greeeting: Hello World
    	list:
    		values: One,Two,Three
		
    management.endpoints.web.exposure.include: "*"


###Spring Profiles:

    applicatin-<profilename>.extn

    spring.profiles.active=profilename

The default profile is always active. so we can safely put the spring.profile.active=test in our application.properties or application.yaml
active profile overrides the default value if the key is same and if some property is not there in active profile, it will be picked up by default profile.

    java -jar jarfilename.jar --spring.profiles.active=test


**selecting different beans by profile:**

    @Repository
    @Profile("prod")
    public class DataSourceBean{
    
    }
    
    @Repository
    @Profile("dev")
    public class LocalDataSourceBean{
    
    }


Environment is a bean that can be used to get info about the running environment at runtime.

    @Autowired
    private Environment env;
    
    env.getActiveProfiles();


**Spring Colud Config Server:** 

Configuration as a separate microservice 

spring cloud config server will connect to a git repo and read configuration from there 
and all the microservices take the configuration from  spring cloud config server.

    @EnableConfigServer

    spring.cloud.config.server.git.uri=url
    server.port=8888                             //by convention port no for spring cloud config server is 8888
    http://localhost:8888/<file-name>/profile


**Spring Cloud Config Client:**

    spring.cloud.config.uri: http://localhost:8888

    spring.application.name: spring-boot-config
    if the git repo has a properties file with same name as the application, that will be choosen.


**Dynamic Configuration Refresh:**

Actuator exposes an end point that can be called to refresh properties without restarting application.
You need to make a post request to this end point.
localhost:8080/actuator/refresh

    @RefreshScope
    All the beans and properties which are annotated with @RefreshScope will be refreshed


**Security for property values or configuration:**

save the encrypted values in the properties file

    application.yml
    password: '{cipher}GJYTCS595FJHFSJHGFJHR2S'

Spring cloud config server has the ability to encrypt and decrypt the values. encrypt the values using spring cloud and save it







		