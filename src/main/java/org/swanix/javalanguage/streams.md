#Streams
Streams are an update to the java API that lets you manipulate collections of data in a declarative way. You can think of them as fancy iterators over a collection of data.

#####Streams Definition
    A sequence of elements from a source that supports data processing operations.
    
    Sequence of elements : A stream is a sequence of elements from a source
    Source               : Streams consume from a data-providing source such as collections, arrays, or I/O resources. 
                           Generating a stream from an ordered collection preserves the ordering.
    Data processing ops  : Streams support database like operations and common operations from functional programming languages to manipulate data such as filter, map, reduce, find, match, sort and so on.  
                           Stream operations can be executed either sequentially or in parallel.  
        
        
    Important characteristics of streams operations:
       Pipelining        : Many Stream operations return a stream allowing operations to be chained and form a larger pipeline.
       Interal Iteration : In contrast to collections, stream operations do the iteration behind the scenes implicitely. 
       
    similarly to iterators, a stream can be traversed only once. After that a stream is said to be consumed.       
        
#####Streams vs Collection
    - Collections are about data while Streams are about computations. 
    - collections are data structures, they are mostly about storing and accessing elements with specific time/space complexities.
    - Streams are about expressing computations such as filter, sorted and map. 
    
#####Intermediate vs Terminal
    Intermediate : Stream operations that returns a strean and can be connected are called intermediate operations. 
                   These don't perform any processing until a terminal operation is invoked on the stream pipeline-they're lazy.
    Terminal     : Operations that closes a stream are called terminal operations. 
    
####Stream Operations

#####Filter
    This operation takes as argument a predicate and returns a stream including all elements that match the predicate (elements for which predicate returns true)

    Operation           :   filter        
    Type                :   Intermediate              
    Return Type         :   Stream<T>             
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean
    
    e.g.     List<Dish> vegetarian = menu.stream()
                                      .filter(Dish::isVegetarian)
                                      .collect(toList());

#####Distinct
    This returns a stream with unique elements (according to the implementation of the hashCode and equals methods of the objects produced by the stream).
    
    Operation   :   distinct        
    Type        :   Intermediate (stateful-unbounded)              
    Return Type :   Stream<T>
    
    e.g.      List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
              numbers.stream()
             .filter(i -> i % 2 == 0)
             .distinct()
             .forEach(System.out::println);

#####Limit
    This returns a stream that's no longer than a given size.
    
    Operation   :   limit        
    Type        :   Intermediate (stateful-bounded)	              
    Return Type :   Stream<T>             
    args        :   long  
    
    e.g.      List<Dish> dishes = menu.stream()
                                        .filter(d -> d.getCalories() > 300)
                                        .limit(3)
                                        .collect(toList());

#####Skip
    This a stream that discards the first n elements. If the stream has fewer elements than n, then an empty stream is returned
    
    Operation   :   skip        
    Type        :   Intermediate (stateful-bounded)	              
    Return Type :   Stream<T>             
    args        :   long   
    
    e.g.       List<Dish> dishes = menu.stream()
                                       .filter(d -> d.getCalories() > 300)
                                       .skip(2)
                                       .collect(toList());

#####Map
    The method map takes a function as argument. The function is applied to each element, mapping it into a new element
    
    Operation           :   map        
    Type                :   Intermediate              
    Return Type         :   Stream<R>             
    args                :   Function<T, R>  
    Function descriptor :   T -> R  
    
    e.g.        List<String> dishNames = menu.stream()                             
                                             .map(Dish::getName)                             
                                             .collect(toList());
    
#####Flat Map   
    In a nutshell, the flatMap method lets you replace each value of a stream with another stream and then concatenates all the generated streams into a single stream. 
    
    Operation           :   flatMap        
    Type                :   Intermediate              
    Return Type         :   Stream<R>             
    args                :   Function<T, Stream<R>>  
    Function descriptor :   T -> Stream<R> 
    
    eg.         List<String> uniqueCharacters = words.stream()
                                                     .map(w->w.split(""))
                                                     .flatMap(Arrays::stream)
                                                     .distinct()
                                                     .collect(Collectors.toList());
                                                     
#####Any Match   
    The anyMatch method can be used to answer the question “Is there an element in the stream matching the given predicate?" 
    
    Operation           :   anyMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean  
    
    eg.         boolean isThereAnyVegetarianDish = menu.stream()
                                                       .anyMatch(Dish::isVegetarian);

#####All Match   
    The allMatch method works similarly to anyMatch but will check to see if all the elements of the stream match the given predicate.  
    
    Operation           :   allMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean 
    
    eg.         boolean isHealthy = menu.stream()
                                        .allMatch(d -> d.getCalories() < 1000);
                                        
#####None Match   
    The opposite of allMatch is noneMatch. It ensures that no elements in the stream match the given predicate.  
     
    Operation           :   noneMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean 
     
    eg.         boolean isHealthy = menu.stream()                        
                                         .noneMatch(d -> d.getCalories() >= 1000);   
                                         
#####Find Any   
    The findAny method returns an arbitrary element of the current stream.  
     
    Operation   :   findAny        
    Type        :   Terminal              
    Return Type :   Optional<T>
     
    eg.         Optional<Dish> dish =  menu.stream()      
                                            .filter(Dish::isVegetarian)      
                                            .findAny(); 

#####Find First   
    The findFirst method returns the first element in the stream.  
     
    Operation   :   findFirst        
    Type        :   Terminal              
    Return Type :   Optional<T> 
     
    eg.        Optional<Integer> firstSquareDivisibleByThree =  someNumbers.stream()             
                                                                            .map(x -> x * x)             
                                                                            .filter(x -> x % 3 == 0)             
                                                                            .findFirst();

#####Reduce   
    The reduce method is used to reduce the stream to a single element by combinging all elements. 
     
    Operation           :   reduce        
    Type                :   Terminal (stateful-bounded)              
    Return Type         :   Optional<T>            
    args                :   BinaryOperator<T>  
    Function descriptor :   (T, T) -> T  
     
    eg.        int sum = numbers.stream()
                                 .reduce(0, (a, b) -> a + b);
                                 
     In Java 8 the Integer class now comes with a static sum method to add two numbers    
                
                int sum = numbers.stream().reduce(0, Integer::sum);     
                                        
     No Initial Value :
     
                Optional<Integer> sum = numbers.stream()
                                               .reduce((a, b) -> (a + b));
     

#####Max and Min
    Max and Min value of a stream can be deduced using reduce method.  
     
    Operation           :   reduce        
    Type                :   Terminal (stateful-bounded)              
    Return Type         :   Optional<T>            
    args                :   BinaryOperator<T>  
    Function descriptor :   (T, T) -> T
     
    eg.        Optional<Integer> max = numbers.stream()
                                                .reduce(Integer::max);
                                 
                Optional<Integer> min = numbers.stream()
                                                .reduce(Integer::min);
                                                
#####Count
    The count method gives the no of elements in the Stream.  
     
    Operation           :   count        
    Type                :   Terminal           
    Return Type         :   long           
     
    eg.        long noOfElements = numbers.stream()
                                           .count;      
                                                                                                                                  
#####Sorted
    The sorted method ...  
     
    Operation           :   sorted        
    Type                :   Intermediate (stateful-unbounded)              
    Return Type         :   Stream<T>            
    args                :   Comparator<T>  
    Function descriptor :   (T, T) -> int         
     
    eg.        long noOfElements = numbers.stream()
                                           .count; 

#####ForEach
    The forEach method ...  
     
    Operation           :   forEach        
    Type                :   Terminal              
    Return Type         :   void            
    args                :   Consumer<T>  
    Function descriptor :   T -> void         
     
    eg.        long noOfElements = numbers.stream()
                                           .count; 
                                                            
#####Collect
     The collect method ....
     
    Operation           :   collect        
    Type                :   Terminal              
    Return Type         :   R            
    args                :   Collector<T, A, R>            
     
    eg.        long noOfElements = numbers.stream()
                                           .count;                                              















































                                                