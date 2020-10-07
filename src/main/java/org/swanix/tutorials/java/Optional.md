# Optional
##### Empty Optional:
            Optional<Car> optCar = Optional.empty();    
                                
##### Optional from a non null value:     
    If car were null, a NullPointerException would be immediately throws
        Optional<Car> optCar = Optional.of(car);
    
##### Optional from a nullable value:      
    If car were null, an empty Optional would be returned
        Optional<Car> optCar = Optional.ofNullable(car)
        
##### isPresent:
        This methods return true if the optional contains a value.
    
##### Map :
    Optional<String> name = optInsurance.map(Insurance::getName);
     
##### Get:
    It returns the wrapped value if present but throws a NoSuchElementException otherwise.
    
##### orElse:
    orElse(T other) 
            - it allows us to provide a default value for when the optional is empty.
        
##### orElseGet:
    orElseGet(Supplier<? extends T> other) 
            - This is the lazy counterpart of the orElse method, because the supplier is invoked only if the optional contains no value.
             
##### orElseThrow:    
    orElseThrow(Supplier< ? extends X> exceptionSupplier)
            - This is similar to the get method in that it throws an exception when the optional is empty.
            
##### ifPresent:
    ifPresent(Consumer<? super T> consumer)
            -  This lets you execute the action gives as argument if a value is present, otherwise no action is taken.
            
##### FlatMap: FlatMap method flattens this two-level optional into a single optional.
    String insuranceCompanyName = person.flatMap(Person::getCar)
                                        .flatMap(Car::getInsurance)
                                        .map(Insurance::getName)
                                        .orElse("Unknown");
                                                
    If you invoke flatMap on an empty optional, nothing is changed and it's returned as is.
    
    Combining two optionals without unwrapping them:
    
            Optional<Insurance> nullSafeFindCheapesetInsurance(Optional<Person> person, Optional<Car> car){
                        return person.flatMap(p-> car.map(c-> findCheapestInsurance(p,c)));
            }
    Here you invoke a flatMap on the first optional, so if this is empty, the lambda expression passed to it won't be executed at all 
    and this invocation will just return an emtpy optional.   

##### Filter:
    The filter method takes a predicate as an argument. If a value is present in the Optional object and it matches the predicate, 
    the filter method returns that value, otherwise it returns an empty Optional object.     
            
            optInsurance
                        .filter(insurance -> "CambridgeInsurance".equals(insurance.getName()))            
                        .ifPresent(x -> System.out.println("ok"));
            
#### Not Serializable
    Because the Optional class wasn't intended for use as a field type, it also doesn't implement the Serializable interface.
    For this reason, using Optionals in your domain model could break applications using tools or frameworks that require a serializable model to work.  

##### Primitive Optional:
    Like Stream, optionals also have primitive counterparts:
        OptionalInt
        OptionalLong
        OptionalDouble            
                        
            
            
   