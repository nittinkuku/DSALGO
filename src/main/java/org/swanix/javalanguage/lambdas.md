# Lambda
A lambda expression can be understood as a concise representation of an anonymous function that can be passed around. 
It does not have a name, but it has a list of parameters, a body, a return type, and also possibly a list of exceptions that can be thrown.
                                                                                   
    Anonymous    :  We say anonymous because it does not have an explicit name like a method would normally have
    Function     :  We say function because a lambda is not associated with a particular class like a method is but like a method, 
                    a lambda has a list of parameters, a body, a return type, and a possible list of exceptions that can be thrown.
    Passed Around:  A lambda expression can be passed as argument to a method or stored in a variable.
    Concise      :  You don’t need to write a lot of boilerplate like you do for anonymous classes.

##### Lambda's Syntax:

    Syntax                :  (args) -> (body)
    A list of parameters  :  list of parameters e.g 
                             () ->           // No Parameter
                             (s) ->          // One Parameter without explicit type
                             (String s) ->   // One parameter with explicity type
                              
    An arrow              :  The arrow (->) separates the list of parameters from the body of the lambda.
    The body of the lambda:  Compare two Apples using their weights. The expression is considered the lambda’s return value.
     eg.                  :  (s) -> Integer.parseInt(s);
                          :  (String s) -> Integer.parseInt(s) 
                          :  (s) -> {return Integer.parseInt(s);}
    
##### Functional Interface:
A Functional interface is an interface which has only one abstract method. It can have one or more default methods as well but only one abstract method should be there. They are annotated
with @FunctionalInterface.

##### Where to Use Lambds:
    a) A lambda can be used in the context of a functional interface. 
    b) Lambda expressions let you provide the implementation of the abstract method of a functional interface directly inline and 
       treat the whole expression as an instance of a functional interface (more technically speaking, an instance of a concrete implementation of the functional interface). 
    c) The signature of the abstract method of the functional interface essentially describes the signature of the lambda expression. We call this abstract method a function descriptor.
    d) A lambda expression can be assigned to a variable or passed to a method expecting a functional interface as argument, provided the lambda expression has the same signature 
       as the abstract method of the functional interface. 
         
##### Special void-compatibility rule: 
    If a lambda has a statement expression as its body, it’s compatible with a function descriptor that returns void (provided the parameter list is compatible too). 
      For example, both of the following lines are legal even though the method                               
        a) Predicate has a boolean return
           Predicate<String> p = s -> list.add(s);
        b) Consumer has a void return
           Consumer<String> b = s -> list.add(s);
         


               
#### Functional Interfaces in Java

##### Predicate
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :

##### Consumer
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### Supplier
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :        

##### Function
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### Unary Operator
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### Binary Operator
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### BiPredicate
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### BiConsumer
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :
    
##### BiFunction
    Method Name      :
    Signature        :
    Input Parameters :
    Return Types     :
    Variants         :

##### Lambda and Exception:
    None of the predefined functional interfaces in Java allow for a checked exception to be thrown. 
    You have two options if you need a lambda expression to throw an exception 
     a) define your own functional interface that declares the checked exception, 
                        or 
     b) wrap the lambda with a try/catch block.   

##### Variable Capture:
    a) Lambdas are allowed to capture (that is, to reference in their bodies) instance variables and static variables without restrictions. 
    b) But local variables have to be explicitly declared final or are effectively final. 
       In other words, lambda expressions can capture local variables that are assigned to them only once and they can't change the value of local variables inside their body.
    Reason:
        Reason for not allowing this is if a lambda could access the local variable directly and the lambda were used in a thread, then the thread using the lambda could try to access the variable after the thread that allocated the variable had deallocated it. 
        Hence, Java implements access to a free local variable as access to a copy of it rather than access to the original variable.

##### Method Reference 
    a) ClassName::staticMethod
       (args) -> ClassName.staticMethod(args) 
       args will go as parameters to the staticMethod
    
    b) expr::instanceMethod
       (args) -> expr.instanceMethod(args) 
       expr will be used to call the instance method and args will go as parameters to it
    
    c) ClassName::instanceMethod
       (arg0,rest) -> arg0.instanceMethod(res)
       arg0 will be used for calling the instance method while rest of the args will go as parameters to it
   
##### Constructor Reference 
    ClassName::new
    All the parameters will go as parameters to the constructor of the class
    e.g.
    1. Apple constructor does not take any parameter (fits the signature of a supplier)
       Supplier<Apple> s1 = Apple::new           //method reference
                 Apple a1 = c1.get();
       
       Supplier<Apple> s1 = () -> new Apple()    //lambda expression
                 Apple a1 = c1.get();
       
    2. Apple constructor takes an int as constructor argument  (fits the signature of a function)
       Function<Integer,Apple>  f1 = Apple::new        //method reference
                          Apple a1 = f1.apply(110);
                         
       Function<Integer,Apple>  f1 = Apple::new        //lambda expression
                          Apple a1 = f1.apply(110);

##### Using Default Methods:
    We can use default methods provided in the functional interfaces to extend their functionalities
    e.g.
    1. Comparator:
        Comparator<Apple> c1 = Comparator.comparing(Apple::getWeight);    // gives a basic comparator that compares apples on weights in ascending order
        Comparator<Apple> c2 = Comparator.comparing(Apple::getWeight)     // calling reversed() method on the comparator gives us a new comparator that is basically opposite of the original one.  
                                         .reversed();                     // This will sort the apples on weights but in descending order      
                                                                            
        Comparator<Apple> c3 = Comparator.comparing(Apple::getWeight)    // if two objects are equal using first comparator then, a second comparator can be given for further processing using thenComparing method.
                                         .reversed()                     // here apples will be sorted by weight in reversed order and then if two apples are of same weight, they will be further sorted using country.       
                                         .thenComparing(Apple::getCountry);
        
    2. Predicate:
        Predicate<Apple> redApple = Apple::isRed           // will filter red apples   
        Predicate<Apple> notRedApple = redApple.negate()  // we can call an existing predicate and use negate method to create a new predicate which is opposite of the original
        Predicate<Apple> redAndHeavyApple = redApple      //we can use and to filter on more than 2 criteria
                                           .and(Apple::isHeavy)
        Predicate<Apple> redAndHeavyAppleOrGreenApple = redApple    // we can use or to filter red and Heavy apple or green apples
                                                        .and(Apple::isHeavy)
                                                        .or(Apple::isGreen)                                   
        Note that the precedence of methods and and or is managed from left to right using their positions in the chain. So a.or(b).and(c) can be seen as (a || b) && c.
    
    3. Function:
        The method "andThen" returns a function that first applies a given function to an input and then applies another function to the result
                Function<Integer,Integer> f = x-> x+1;
                Function<Integer,Integer> g = x-> x*2;
                Function<Integer,Integer> h = f.andThen(g);
                int result = h.apply(1);      //returns 4
        
        You can also use the method "compose" similarly to first apply the function gives as argument to compose and the apply the function to the result.
                Function<Integer,Integer> f = x-> x+1;
                Function<Integer,Integer> g = x-> x*2;
                Function<Integer,Integer> h = f.compose(g);
                int result = h.apply(1);      // returns 3
        
#### Anonymous Class vs Lambda
    The meanings of this and super are different for anonymous classes and lambda expressions. 
    Inside an anonymous class, this refers to the anonymous class itself, but inside a lambda it refers to the enclosing class. 
    Second, anonymous classes are allowed to shadow variables from the enclosing class. Lambda expressions can’t (they’ll cause a compile error), as shown in the following code:
            
    int a=10;
    Runnable r1=()->{
        int a=2;                    //compile error in lambda
        System.out.println(a);
    }
    
    Runnable r2 = new Runnable(){
        public void run(){
            int a=2;                //fine in anonymous class
            System.out.println(a);
        }
    }
    
#### Default Methods
    Java now allows you to have static as well as default methods inside interfaces. A default method has new default modifier before the return type.
    
    There are three rules to follow when a class inherits a method with the same signature from multiple places (such as another class or interface):
    1.  Classes always win. A method declaration in the class or a superclass takes priority over any default method declaration.
    2.  Otherwise, sub-interfaces win: the method with the same signature in the most specific default-providing interface is selected. (If B extends A, B is more specific than A).
    3.  Finally, if the choice is still ambiguous, the class inheriting from multiple interfaces has to explicitly select which default method implementation to use by overriding it 
        and calling the desired method explicitly.