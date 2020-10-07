# Streams
Streams are an update to the java API that lets you manipulate collections of data in a declarative way. You can think of them as fancy iterators over a collection of data.

##### Streams Definition
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
        
##### Streams vs Collection
    - Collections are about data while Streams are about computations. 
    - collections are data structures, they are mostly about storing and accessing elements with specific time/space complexities.
    - Streams are about expressing computations such as filter, sorted and map. 
    
##### Intermediate vs Terminal
    Intermediate : Stream operations that returns a strean and can be connected are called intermediate operations. 
                   These don't perform any processing until a terminal operation is invoked on the stream pipeline-they're lazy.
    Terminal     : Operations that closes a stream are called terminal operations. 
    
#### Stream Operations

##### Filter
    This operation takes as argument a predicate and returns a stream including all elements that match the predicate (elements for which predicate returns true)

    Operation           :   filter        
    Type                :   Intermediate              
    Return Type         :   Stream<T>             
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean
    
    e.g.     List<Dish> vegetarian = menu.stream()
                                      .filter(Dish::isVegetarian)
                                      .collect(toList());

##### Distinct
    This returns a stream with unique elements (according to the implementation of the hashCode and equals methods of the objects produced by the stream).
    
    Operation   :   distinct        
    Type        :   Intermediate (stateful-unbounded)              
    Return Type :   Stream<T>
    
    e.g.      List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
              numbers.stream()
             .filter(i -> i % 2 == 0)
             .distinct()
             .forEach(System.out::println);

##### Limit
    This returns a stream that's no longer than a given size.
    
    Operation   :   limit        
    Type        :   Intermediate (stateful-bounded)	              
    Return Type :   Stream<T>             
    args        :   long  
    
    e.g.      List<Dish> dishes = menu.stream()
                                        .filter(d -> d.getCalories() > 300)
                                        .limit(3)
                                        .collect(toList());

##### Skip
    This a stream that discards the first n elements. If the stream has fewer elements than n, then an empty stream is returned
    
    Operation   :   skip        
    Type        :   Intermediate (stateful-bounded)	              
    Return Type :   Stream<T>             
    args        :   long   
    
    e.g.       List<Dish> dishes = menu.stream()
                                       .filter(d -> d.getCalories() > 300)
                                       .skip(2)
                                       .collect(toList());

##### Map
    The method map takes a function as argument. The function is applied to each element, mapping it into a new element
    
    Operation           :   map        
    Type                :   Intermediate              
    Return Type         :   Stream<R>             
    args                :   Function<T, R>  
    Function descriptor :   T -> R  
    
    e.g.        List<String> dishNames = menu.stream()                             
                                             .map(Dish::getName)                             
                                             .collect(toList());
    
##### Flat Map   
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
                                                     
##### Any Match   
    The anyMatch method can be used to answer the question “Is there an element in the stream matching the given predicate?" 
    
    Operation           :   anyMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean  
    
    eg.         boolean isThereAnyVegetarianDish = menu.stream()
                                                       .anyMatch(Dish::isVegetarian);

##### All Match   
    The allMatch method works similarly to anyMatch but will check to see if all the elements of the stream match the given predicate.  
    
    Operation           :   allMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean 
    
    eg.         boolean isHealthy = menu.stream()
                                        .allMatch(d -> d.getCalories() < 1000);
                                        
##### None Match   
    The opposite of allMatch is noneMatch. It ensures that no elements in the stream match the given predicate.  
     
    Operation           :   noneMatch        
    Type                :   Terminal              
    Return Type         :   boolean            
    args                :   Predicate<T>  
    Function descriptor :   T -> boolean 
     
    eg.         boolean isHealthy = menu.stream()                        
                                         .noneMatch(d -> d.getCalories() >= 1000);   
                                         
##### Find Any   
    The findAny method returns an arbitrary element of the current stream.  
     
    Operation   :   findAny        
    Type        :   Terminal              
    Return Type :   Optional<T>
     
    eg.         Optional<Dish> dish =  menu.stream()      
                                            .filter(Dish::isVegetarian)      
                                            .findAny(); 

##### Find First   
    The findFirst method returns the first element in the stream.  
     
    Operation   :   findFirst        
    Type        :   Terminal              
    Return Type :   Optional<T> 
     
    eg.        Optional<Integer> firstSquareDivisibleByThree =  someNumbers.stream()             
                                                                            .map(x -> x * x)             
                                                                            .filter(x -> x % 3 == 0)             
                                                                            .findFirst();

##### Reduce   
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
     

##### Max and Min  (Using Reduce)
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
                                                
##### Count
    The count method gives the no of elements in the Stream.  
     
    Operation           :   count        
    Type                :   Terminal           
    Return Type         :   long           
     
    eg.        long noOfElements = numbers.stream()
                                           .count();      
                                                                                                                                  
##### Sorted
    The sorted method sorts the list based on the comparator given.  
     
    Operation           :   sorted        
    Type                :   Intermediate (stateful-unbounded)              
    Return Type         :   Stream<T>            
    args                :   Comparator<T>  
    Function descriptor :   (T, T) -> int         
     
    eg.          List<Dish> dishSortedByCalories = menu.stream()
                                                       .sorted(Comparator.comparingInt(Dish::getCalories))
                                                       .collect(toList());

##### ForEach
    The forEach performs an operation on each element of the strea.
     
    Operation           :   forEach        
    Type                :   Terminal              
    Return Type         :   void            
    args                :   Consumer<T>  
    Function descriptor :   T -> void         
     
    eg.          List<Dish> dishSortedByCalories = menu.stream()
                                                       .forEach(System.out::println);  
                                                       
##### Peek
      Its purpose is to execute an action on each element of a stream as it’s consumed, but it does not consume the whole stream as forEach does.
      
      e.g.      List<Integers> result = numbers.stream()
                                               .peek(System.out::println)
                                               .map(x->x+17)
                                               .peek(System.out::println)
                                               .collect(toList();                              

#### Primitive Stream
There are primitive streams to avoid autoboxing and to provide some specialized functions.

##### Converting General Stream to Primitive Stream
    int calories = menu.stream()
                       .mapToInt(Dish::getCalories) // converts to IntStream
                       .sum();
                       
##### Converting Primitive Stream to General Stream
    IntStream intStream = menu.stream().mapToInt(Dish::getCalories):
    Stream<Integer> stream1 = intStream.boxed();
    Stream<Integer> stream2 = intStream.mapToObj(Integer::new);   
    
##### Sum,Max
    int totalCalories = menu.stream()
                            .mapToInt(Dish::getCalories)
                            .sum();
    OptionalInt maxCalories = menu.stream()
                                  .mapToInt(Dish::getCalories)
                                  .max();

##### Numeric ranges (IntStream and LongStream)
    Java has two static methods available on IntStream and LongStream to help generate range of numbers:
    range       : range is exclusive 
                IntStream numbers = IntStream.range(1,5)      // generate numbers from 1 to 4
                
    rangeClosed : rangeClosed is inclusive
                IntStream numbers = IntStream.rangeClosed(1,5)    // generate numbers from 1 to 5
   
#### Building Streams
    Empty Stream :
                Stream<String> emptyStream = Stream.empty();
    From explicit values : 
            Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
    From arrays :
            int[] numbers = {1,2,3,4,5,6,7,8,9}; 
            Arrays.stream(numbers)
    From Files :
            try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset()))
            Files.lines returns a Stream of Lines as Strings from a given file. 

#### Infinite Streams
    iterate and generate methods allows you to create infinite stream: a stream which does not have a fixed size.
    
    Iterate :   The iterate method takes an initial value and a lambda of type Unary Operator<T> to apply  successively on each new value.
                In general you should use iterate when you need to produce a sequence of successive values, like a date followed by its next date.
                
                Stream.iterate(0, n->n+2) // initial value(0), unary operator(T)
                      .limit(10)
                      .forEach(System.out::println);
    
    Generate:   Similar to iterate generate method lets you produce an infinite stream of values. It takes a lambda of type Supplier<T> to provide new values.
                
                Stream.generate(Math::random)
                      .limit(5)
                      .forEach(System.out::println);

#### Collecting Data with stream
##### Reducing & Summarizing
##### ToList,ToSet & ToCollection
    toList : Gather all the stream’s items in a List.                                       
             List<Dish> dishes = menuStream.collect(toList());
                                                    
    toSet :  Gather all the stream’s items in a Set, eliminating duplicates.                                       
            Set<Dish> dishes = menuStream.collect(toSet());   
                                                
    toCollection : Gather all the stream’s items in the collection created by the provided supplier.
            Set<Dish> dishes = menuStream.collect(toCollection(HashSet::new));
            
##### Counting
    Use the collector returned by the counting factory method
    
    long howManyDishes = menu.stream()
                             .collect(Collectors.counting());
    Easy Way :
                long howManyDishes = menu.stream().count();   
                
##### Max and Min
    We can use two collectors Collectors.maxBy() and Collectors.minBy() to calculate the max and min value in a stream
    
    Optional<Dish> mostCalorieDish =  menu.stream()
                                          .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
    Optional<Dish> leastCalorieDish =  menu.stream()
                                              .collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)));                       

##### Sum,Avg and Statistics
    Collectors class provides a specific factory method for summing. Collectors.summingInt that accepts a function 
    that converts an object to int that has to be summed. 
    
    Sum :
    int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    
    There is also a method called summingDouble which behave exactly the same way
    
    Avg :
    int avgCalories = menu.stream().collect(Collectors.averageInt(Dish::getCalories));
    
    Statistics :
    IntSummaryStatistics menuStatistics = menu.stream()
                                              .collect(Collectors.summarizingInt(Dish::Calories));
                                              
    IntSummaryStatistics{count=9, sum=4300, min=120, average=477.256, max=800};
    
    There are corresponding summarizingLong and summarizingDouble methods with associated types for LongSummaryStatistics and DoubleSummaryStatistics

##### Joining Strings
    The collector returned by the joining factory method concatenates into a single string all strings resulting from invoking the toString method on each object.

    String shortMenu = menu.stream()
                           .map(Dish::getName)
                           .collect(Collectors.joining());
    Delimiter
    String shortMenu = menu.stream()
                               .map(Dish::getName)
                               .collect(Collectors.joining(","));
                            
##### Reduction
    int totalCalories = menu.stream()
                            .collect(Collectors.reducing(0,Dish::getCalories,(i,j)->i+j));
    Simplified
    int totalCalories = menu.stream()
                            .collect(Collectors.reducing(0,Dish::getCalories,Integer::sum));
    
    It takes three arguments
    1) Starting Value : This will also be the value returned in the case of empty stream
    2) Function<T,R>  : Mapping function which provides the value for reduction
    3) Binary Operator: That aggregators two items to a single

    Collector with one argument can be think of as a particular case of three-argument constructor, which used the first item as a starting point
    and an identity function as a transformation function
    
    Option<Dish> mostCalorieDish = menu.stream()
                                       .collect(Collectors.reducing((d1,d2)->d1.getCalories()>d2.getCalories() ? d1:d2));
    
##### Grouping elements
##### Simple Grouping :   
    The result of this grouping operation is a Map having a key the value returned by the classification function and 
    as corresponding map value a list of all the items in the stream having that classified value.
    
    Map<Dish.Type,List<Dish>> dishesByType = menu.stream()
                                                 .collect(Collectors.groupingBy(Dish::getType));
    
##### Multilevel Grouping : 
    Collectors.groupingBy factory method has a two-argument version which accepts a second argument of type collector besides the usual classification function
        
    Map<Dish.Type, Map<String, List<Dish>> dishesByCaloricLevel = menu.stream()
                    .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(Dish::getOrigin)));
    
    one-argument groupingBy(f) where f is the classification function, is in reality just shorthand for groupingBy(f,toList())     
    
##### CollectingAndThen :
    The method takes two arguments, the collector to be adapted and a transformation function and then returns another collector
    
    Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
                .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));      
                                  
    //using the maxBy collector to find the highest calorie dish but this is not very useful as it gives Optional<Dish>
    
    Map<Dish.Type, Dish> mostCaloricDishByType = menu.stream()
                                                     .collect(Collectors.groupingBy(Dish::getType, 
                                                     Collectors.collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),Optional::get)));   
                                                     
    This factory method takes two arguments, the collector to be adapted and a transformation function, and returns another collector. 
    This additional collector acts as a wrapper for the old one and maps the value it returns using the transformation function as the last step of the collect operation.                                                                
    
    More generally, the collector passes as second argument to the groupingBy factory method will be used 
    to perform a further reduction operation on all the elements in the stream classified into the same group
    
    More examples :
                Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
                                                                  .collect(groupingBy(Dish::getType,summingInt(Dish::getCalories)));
                                                                  
    mapping function:
                
                Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =menu.stream()
                                        .collect(groupingBy(Dish::getType, 
                                        Collectors.mapping(
                                        dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                                  else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                                  else return CaloricLevel.FAT; },    
                                        toSet())));
                                        
    //This method takes two arguments: a function transforming the elements in a stream and a further collector accumulating the objects resulting from this transformation.
    
    Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =menu.stream()
                                                               .collect(groupingBy(Dish::getType, 
                                                               Collectors.mapping(    
                                                               dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;            
                                                                         else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;          
                                                                         else return CaloricLevel.FAT; },    
                                                               toCollection(HashSet::new) )));
                                                               
    In the previous example there is no guarantee what kind of set will be returned.                                                               
    
##### Partitioning
    Partitioning is a special case of grouping: having a predicate (a function returning a boolean), called a partitioning function, as a classification function. 
    The fact that the partitioning function returns a boolean means the resulting grouping Map will have a Boolean as a key type 
    and therefore there can be at most two different groups—one for true and one for false. 
    It has the advantage of keeping both lists of the stream elements, for which the application of partitioning function returns true or false.
    
    Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                                                   .collect(partitioningBy(Dish::isVegetarian));
                 List<Dish> vegetarianDishes = partitionedMenu.get(true);

    It has an overloaded version to which we can pass a second collector.
    
    Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream.collect(
                                                                            partitioningBy(Dish::isVegetarian,
                                                                                groupingBy(Dish::getType)));
                                           
    Another example :
    
    Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream.collect(
                                                                    partitioningBy(Dish::isVegetarian,
                                                                        collectingAndThen(
                                                                            maxBy(comparingint(Dish::getCalories)),
                                                                            Optional::get)));                                           
                                                                            
##### Custom Collector                                                       
    The Collector interface consists of a set of methods that provide a blueprint for how to implement specific reduction operations (that is, collectors)
    
    public interface Collector<T, A, R> {    
            Supplier<A> supplier();    
            BiConsumer<A, T> accumulator();    
            Function<A, R> finisher();    
            BinaryOperator<A> combiner();    
            Set<Characteristics> characteristics();
    }
    
    T is the generic type of the items in the stream to be collected.                                       
    A is the type of the accumulator, the object on which the partial result will be accumulated during the collection process.                                       
    R is the type of the object (typically, but not always, the collection) resulting from the collect operation.      
    
    1.  The supplier method has to return a Supplier of an empty result—a parameterless function that when invoked creates an instance of an empty accumulator used during the collection process
    
            public Supplier<List<T>> supplier() {    
                //return () -> new ArrayList<T>();
                  return ArrayList::new;
            }                                 
    
    2.  The accumulator method returns the function that performs the reduction operation. When traversing the nth element in the stream, this function is applied with two arguments, 
        the accumulator being the result of the reduction (after having collected the first n–1 items of the stream) and the nth element itself.
    
            public BiConsumer<List<T>, T> accumulator() {
                //return (list, item) -> list.add(item);    
                return List::add;
            }
    
    3.  The finisher method has to return a function that’s invoked at the end of the accumulation process, 
        after having completely traversed the stream, in order to transform the accumulator object into the final result of the whole collection operation.
    
            public Function<List<T>, List<T>> finisher() {    
                return Function.identity();
            }
    
    4.  The combiner method return a function used by the reduction operation, defines how the accumulators resulting from the reduction of different subparts of the stream are combined 
        when the subparts are processed in parallel.
    
            public BinaryOperator<List<T>> combiner() {   
                return (list1, list2) -> {        
                    list1.addAll(list2);        
                    return list1; 
                }
            }
    
    5.  The last method, characteristics, returns an immutable set of Characteristics, defining the behavior of the collector—in particular providing hints about 
        whether the stream can be reduced in parallel and which optimizations are valid when doing so.
    
        UNORDERED       :   The result of the reduction isn’t affected by the order in which the items in the stream are traversed and accumulated.                                       
        CONCURRENT      :   The accumulator function can be called concurrently from multiple threads, and then this collector can perform a parallel reduction of the stream. 
                            If the collector isn’t also flagged as UNORDERED, it can perform a parallel reduction only when it’s applied to an unordered data source.                                       
        IDENTITY_FINISH :  This indicates the function returned by the finisher method is the identity one, and its application can be omitted. 
                           In this case, the accumulator object is directly used as the final result of the reduction process.
        
        public Set<Characteristics> characteristics(){
            return Collections.unmodifiableList(EnumSet.of(
                        IDENTITY_FINISH,CONCURRENT));
        }
        
        In the case of an IDENTITY_FINISH collection operation, Stream has an overloaded collect method accepting the three other functions—supplier, accumulator, and combiner.
        
            List<Dish> dishesList =  menuStream().collect(
                                                 ArrayList::new,
                                                 List::add,
                                                 List::addAll);
                                                 
### Parallel Streams    
    Making a stream parallel is as simple as calling parallel() on a stream.
        
        e.g.  Stream.iterator(1,i->i+1)
                    .limit(n)
                    .parallel()
                    .reduce(0,Long::sum);    
            
    Calling the method parallel on a sequential stream doesn’t imply any concrete transformation on the stream itself. 
    Internally, a boolean flag is set to signal that you want to run in parallel all the operations that follow the invocation to parallel. 
    Similarly, you can turn a parallel stream into a sequential one by just invoking the method sequential on it.                                             

    the last call to parallel or sequential wins and affects the pipeline globally

    Parallel streams internally use the default ForkJoinPool, which by default has as many threads as you have processors as returned by Runtime.getRuntime().availableProcessors().
            System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");   // to change the default threads for fork join pool
    This is a global setting, so it will affect all the parallel streams in your code
    
    Always keep a mental mode that some stream operations are more parallelizable than others.
    
    e.g.
    
    Parallel : 168 ms
                public static long parallelIterateSum(){
                                    return Stream.iterate(1L,(n)->n+1)
                                                 .limit(1000000)
                                                 .parallrl()
                                                 .reduce(0L,Long::sum);
    }
                    
    Sequential : 17 ms
                public static long rangedSum(long n) {    
                                    return LongStream.rangeClosed(1, n)                     
                                                    .reduce(0L, Long::sum);
                }
    Here the sequential version will most probably run faster for below reaons:
                
    1) iterate generates boxed objects, which have to be unboxed to numbers before they can be added. 
    2) Iterate is difficult to divide into independent chunks to execute in parallel. It is hard to split into chunks that can be executed independently 
       because the input of one function application always depends on the result of the previous application.
    
    Parallel : 1 ms
                public static long parallelRangedSum(long n) {    
                                   return LongStream.rangeClosed(1, n)                    
                                                    .parallel()                     
                                                    .reduce(0L, Long::sum);
                }
                
    operations such as limit and findFirst that rely on the order of the elements are expensive in a parallel stream. 
    For example, findAny will perform better than findFirst because it isn’t constrained to operate in the encounter order. 
    You can always turn an ordered stream into an unordered stream by invoking the method unordered on it. 
    So, for instance, if you need N elements of your stream and you’re not necessarily interested in the first N ones, 
    calling limit on an unordered parallel stream may execute more efficiently than on a stream with an encounter order (for example, when the source is a List).

####Fork-Join Framework

    The fork/join framework was designed to recursively split a parallelizable task into smaller tasks and then combine the results of each subtask to produce the overall result. 
    It’s an implementation of the ExecutorService interface, which distributes those subtasks to worker threads in a thread pool, called ForkJoinPool.
    
    To submit tasks to this pool, you have to create a subclass of RecursiveTask<R>, where R is the type of the result produced by the parallelized task (and each of its subtasks) or 
    of RecursiveAction if the task returns no result (it could be updating other nonlocal structures, though)
    
    if (task is small enough or no longer divisible) {    
        compute task sequentially
    } 
    else {    
        split task in two subtasks    
        call this method recursively possibly further splitting each subtask    
        wait for the completion of all subtasks    
        combine the results of each subtask
    }

    It doesn’t make sense to use more than one ForkJoinPool. 
    For this reason, what you typically should do is instantiate it only once and keep this instance in a static field, making it a singleton, 
    so it could be conveniently reused by any part of your software.

    e.g.
    
    public class ForkJoinSumCalculator extends RecursiveTask<Long> {    //extends RecursiveTask to create a task usable with the fork/join framework
        private final long[] numbers;    //the array to be summed
        private final int start;         // the intial postions of the portion of array processes by this subtask
        private final int end;
        
        public static final long THRESHOLD = 10_000;  //the size of the array under which this task is no longer split into subtasks
        
        public ForkJoinSumCalculator(long[] numbers){   //public constructor used to create the main task
            this(numbers, 0, numbers.length);
        }
        
        private ForkJoinSumCalculator(long[] numbers, int start, int end){  //private constructor used to recursively create subtasks of the main task
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }
        
        @Override               //Override the abstract method of RecursiveTask
        protected Long compute(){
            int length = end - start;   //The size of the portion of the array summed by this task
            if(length<=THRESHOLD){
                retun computeSequentially();  //If the size is less than or equal to the Threshold, compute the result sequentially.
                long sum = 0;
                for(int i=start; i<end; i++){    //Simple algorithm calculating the result of a subtask when its no longer divisible.
                    sum +=numbers[i];
                }
                return sum;
            }
            
            ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);   // creates a subtask to sum the first half of the array
            leftTask.fork();        //Asynchronously execute the newly created subtask using another thread of the ForkJoinPool
            
            ForJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start, length/2, end);     // creates a subtask to sum the second half of the array 
            //rightTask.fork()  we are not forking the right task as we can use the same thread instead of unncessary creating another task on the fork join pool.
              
            Long rightResult = rightTask.compute();      //Execute this second subtask synchronously, potentially allowing further recursive splits.             
            
            Long leftResult = leftTask.join();           //read the result of the first subtask or wait for it if isn't ready
            
            return leftResult + rightResult;             //The result of this task is the combination of the results of the two subtasks.
        }
    }
    
    public static long forkJoinSum(long n) {    
            long[] numbers = LongStream.rangeClosed(1, n).toArray();    
            ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);    
            return new ForkJoinPool().invoke(task);
    }
    
    you’re using its default no-argument constructor, meaning that you want to allow the pool to use all the processors available to the JVM. 
    More precisely, this constructor will use the value returned by Runtime.availableProcessors to determine the number of threads used by the pool. 
    Note that the availableProcessors method, despite its name, in reality returns the number of available cores, including any virtual ones due to hyperthreading.
    
    Best Practices :
    
    1. Invoking the join method on a task blocks the caller until the result produced by that task is ready. 
       For this reason, it’s necessary to call it after the computation of both subtasks has been started.
    2. The invoke method of a ForkJoinPool shouldn’t be used from within a RecursiveTask. Instead, you should always call the methods compute or fork directly; 
       only sequential code should use invoke to begin parallel computation.
    3. Calling the fork method on a subtask is the way to schedule it on the ForkJoinPool. 
       It might seem natural to invoke it on both the left and right subtasks, but this is less efficient than just directly calling compute on one of them. 
       Doing this allows you to reuse the same thread for one of the two subtasks and avoid the overhead caused by the unnecessary allocation of a further task on the pool. 

    Work Stealing:
    Tasks are more or less evenly divided on all the threads in the ForkJoinPool. 
    Each of these threads holds a doubly linked queue of the tasks assigned to it, and as soon as it completes a task it pulls another one from the head of the queue and starts executing it. 
    one thread might complete all the tasks assigned to it much faster than the others, which means its queue will become empty while the other threads are still pretty busy. 
    In this case, instead of becoming idle, the thread randomly chooses a queue of a different thread and “steals” a task, taking it from the tail of the queue.
    
    That’s why having many smaller tasks, instead of only a few bigger ones, can help in better balancing the workload among the worker threads.

####Spliterator
    Like Iterators, Spliterators are used to traverse the elements of a source, but they’re also designed to do this in parallel
    Java already provides a default Spliterator implementation for all the data structures included in its Collections Framework. 
    Collections now implements the interface Spliterator, which provides a method spliterator
    
    public interface Spliterator<T> {    
            boolean tryAdvance(Consumer<? super T> action);    
            Spliterator<T> trySplit();    
            long estimateSize();    
            int characteristics();
    }
    

     1. tryAdvance method behaves in a way similar to a normal Iterator in the sense that it’s used to sequentially consume the elements of the Spliterator one by one, 
        returning true if there are still other elements to be traversed. 
     2. The trySplit method is more specific to the Spliterator interface because it’s used to partition off some of its elements to a second Spliterator 
        (the one returned by the method), allowing the two to be processed in parallel. 
     3. A Spliterator may also provide an estimation of the number of the elements remaining to be traversed via its estimateSize method.
     4. The last abstract method is characteristics, which returns an int encoding the set of characteristics of the Spliterator itself. 
        The Spliterator clients can use these characteristics to better control and optimize its usage
        a) ORDERED     Elements have a defined order (for example, a List), so the Spliterator enforces this order when traversing and partitioning them.
        b) DISTINCT    For each pair of traversed elements x and y, x.equals(y) returns false.                                       
        c) SORTED      The traversed elements follow a predefined sort order.                                       
        d) SIZED       This Spliterator has been created from a source with a known size (for example, a Set), so the value returned by estimatedSize() is precise.
        e) NONNULL     It’s guaranteed that the traversed elements won’t be null.                                       
        f) IMMUTABLE   The source of this Spliterator can’t be modified. This implies that no elements can be added, removed, or modified during their traversal.                                                      
        g) CONCURRENT  The source of this Spliterator may be safely concurrently modified by other threads without any synchronization.                                       
        h) SUBSIZED    Both this Spliterator and all further Spliterators resulting from its split are SIZED.



























                                                 