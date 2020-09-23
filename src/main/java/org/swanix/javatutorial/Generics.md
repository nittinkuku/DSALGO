# Generics
    //With Generics
    
    List<String> words = new ArrayList<String>();
    words.add("Hello ");
    words.add("world!");
    String s = words.get(0)+words.get(1);
    assert s.equals("Hello world!");
    
    //Without Generics
    
    List words = new ArrayList();
    words.add("Hello ");
    words.add("world!");
    String s = ((String)words.get(0))+((String)words.get(1));
    assert s.equals("Hello world!");
    
    The bytecode compiled from the two sources above will be identical. 
    We say that generics are implemented by erasure because the types List<Integer>, List<String> and List<List<String>> are all represented at run-time by the same type, List. 
    We also use erasure to describe the process that converts the List<String> to List. 
    The term erasure is a slight misnomer, since the process erases type parameters but adds casts.
    
    Generics implicitly perform the same cast that is explicitly performed without generics
    
    Cast-iron guarantee: the implicit casts added by the compilation of generics never fail. It applies only when no unchecked warnings have been issued by the compiler.

##### Caching
    The call Integer.valueOf(1) is similar in effect to the expression new Integer(1), but may cache some values for improved performance.
    
    Caching is required when boxing an int or short value between -128 and 127, a char value between '\u0000' and '\u007f', a byte or a boolean 
    and caching is permitted when boxing other values.
    
##### ForEach
    The foreach loop can be applied to any object that implements the interface Iterable<E>, which in turn refers to the interface Iterator<E>.
    
    interface Iterable<E> {  
        public Iterator<E> iterator();
    }
    interface Iterator<E> {  
        public boolean hasNext();  
        public E next();  
        public void remove();
    }
    
##### Type Parameter - when to explicitly specify
    List<Integer> ints = Lists.<Integer>toList();
    List<Object> objs = Lists.<Object>toList(1, "two");
   
    In general, the following rule of thumb suffices: 
    in a call to a generic method, if there are one or more arguments that correspond to a type parameter and they all have the same type then the type parameter may be inferred; 
    if there are no arguments that correspond to the type parameter or the arguments belong to different subtypes of the intended type then the type parameter must be given explicitly.
    
    The Java grammar requires that type parameters may appear only in method invocations that use a dotted form.
    List<Integer> ints = <Integer> toList();  //compile-time error even if the method toList() belong to the same class.
    
#### Subtyping
    It may seem reasonable to expect that since Integer is a subtype of Number, it follows that List<Integer> is a subtype of List<Number>. 
    But this is not the case, because the Substitution Principle would rapidly get us into trouble. It is not always safe to assign a value of type List<Integer> to a variable of type List<Number>.    
    
    Consider the following code fragment:
    
    List<Integer> ints = new ArrayList<Integer>();
    ints.add(1);
    ints.add(2);
    List<Number> nums = ints;  // compile-time error
    nums.add(3.14);           // If the above statement was allowed, we would be able to add double to a list of integer.
    assert ints.toString().equals("[1, 2, 3.14]");  // uh oh!
    
    Subtyping relation for generics is invariant, meaning that type List<S> is not considered to be a subtype of List<T>, except in the trivial case where S and T are identical.
    
    Subtyping does not work inside <>.
    
    ArrayList<Integer> is a subtype of List<Integer> which is a subtype of Collection<Integer> 
                                            but
    ArrayList<Integer> is not a subtype of List<Number> or even ArrayList<Number>.
    
##### Wildcard    
    interface Collection<E> {  
        ...  public boolean addAll(Collection<? extends E> c);  
        ...
    }
    
    Given a collection of elements of type E, it is OK to add all members of another collection with elements of type E. 
    The quizzical phrase "? extends E" means that it is also OK to add all members of a collection with elements of any type that is a subtype of E. 
    The question mark is called a wildcard, since it stands for some type that is a subtype of E.
    
    List<? extends Number> nums = ints;
    We can also add wildcards when declaring variables.
    
    List<Integer> ints = new ArrayList<Integer>();
    ints.add(1);
    ints.add(2);
    List<? extends Number> nums = ints;
    nums.add(3.14);             // compile-time error
    assert ints.toString().equals("[1, 2, 3.14]");  // uh oh!
    
    Before, the fourth line caused a compile-time error (because List<Integer> is not a subtype of List<Number>), but the fifth line was fine (because a double is a number, so you can add a double to a List<Number>). 
    Now, the fourth line is fine (because List<Integer> is a subtype of List<? extends Number>), but the fifth line causes a compile-time error (because you cannot add a double to a List<? extends Number>, 
    since it might be a list of some other subtype of number). 
    
    In general, if a structure contains elements with a type of the form ? extends E, we can get elements out of the structure, but we cannot put elements into the structure.
    
    Collection<?> stands for Collection<? extends Object> . It is just a shortcut for the common use case.
        
##### Wildcard with Super    
    public static <T> void copy(List<? super T> dst, List<? extends T> src) {  
        for (int i = 0; i < src.size(); i++) {    
                dst.set(i, src.get(i)); 
        }
    }
    
    The quizzical phrase ? super T means that the destination list may have elements of any type that is a supertype of T just as the source list may have elements of any type that is a subtype of T
    
##### Get and Put Principle
    Use an extends wildcard when you only get values out of a structure.
    Use a super wildcard when you only put values into a structure.
    Don’t use a wildcard when you both get and put.
    
    Whenever you use an iterator, you get values out of a structure, so use an extends wildcard
    Whenever you use the add method, you put values into a structure, so use a super wildcard
    
    You cannot put anything into a type declared with an extends wildcard—except for the value null, which belongs to every reference type.
    You cannot get anything out from a type declared with a super wildcard—except for a value of type Object, which is a supertype of every reference type
    
##### Capturing Wildcard
    public static void reverse(List<?> list);
    public static void <T> reverse(List<T> list);
    
    Both of these are equivalent, which one should be used. If we use the second one, it is easy to implement the following method.
    
    public static void <T> reverse(List<T> list) {  
        List<T> tmp = new ArrayList<T>(list);  
        for (int i = 0; i < list.size(); i++) {    
            list.set(i, tmp.get(list.size()-i-1));  
        }
    }
    
    If copies the argument in a temp list and then writes from the copy back into the original.
    If we try to the same with first signature, it will give us an error, because we are trying to write from a list of objects into a list of unknown type.
        list.set(i, tmp.get(list.size()-i-1)); // compile time error
    
    We can implement the method with the first signature by implementing a private method with the second signature, and calling the second from the first:
        
        public static void reverse(List<?> list) { 
            rev(list); 
        }
        
        private static <T> void rev(List<T> list) {  
            List<T> tmp = new ArrayList<T>(list);  
            for (int i = 0; i < list.size(); i++) {    
                list.set(i, tmp.get(list.size()-i-1));  
            }
        }
        
        Here we say that the type variable T has captured the wildcard. This is a generally useful technique when dealing with wildcards, and it is worth knowing.    
           
#### Arrays
    Array subtyping is covariant, meaning that type S[] is considered to be a subtype of T[] whenever S is a subtype of T. 
    
    Integer[] ints = new Integer[] {1,2,3};
    Number[] nums = ints;
    nums[2] = 3.14;         // array store exception
    assert Arrays.toString(ints).equals("[1, 2, 3.14]");  // uh oh!

    Since Integer[] is considered a subtype of Number[], according to the Substitution Principle the assignment on the second line must be legal. 
    Instead, the problem is caught on the third line, and it is caught at run time. 
    When an array is allocated (as on the first line), it is tagged with its reified type (a run-time representation of its component type, in this case, Integer), 
    and every time an array is assigned into (as on the third line), an array store exception is raised if the reified type is not compatible with the assigned value (in this case, a double cannot be stored into an array of Integer).

##### Restrictions on Wildcards 
    Wildcards may not appear at the top level in class instance creation expressions (new), in explicit type parameters in generic method calls, or in supertypes (extends and implements).
        
    Instance Creation In a class instance creation expression, if the type is a parameterized type, then none of the type parameters may be wildcards.
    
    List<?> list = new ArrayList<?>();                                              // compile-time error
    Map<String, ? extends Number> map  = new HashMap<String, ? extends Number>();  // compile-time error  

    Only top-level parameters in instance creation are prohibited from containing wildcards. Nested wildcards are permitted. Hence, the following is legal:
        List<List<?>> lists = new ArrayList<List<?>>();
        lists.add(Arrays.asList(1,2,3));
        lists.add(Arrays.asList("four","five"));
        assert lists.toString().equals("[[1, 2, 3], [four, five]]");     
        
    Even though the list of lists is created at a wildcard type, each individual list within it has a specific type.
    
    Generic Method Calls :
    If a generic method call includes explicit type parameters, those type parameters must not be wildcards. For example, say we have the following generic method:
        class Lists {  
            public static <T> List<T> factory() { 
                return new ArrayList<T>(); 
            }
        }
    You may choose for the type parameters to be inferred, or you may pass an explicit type parameter. Both of the following are legal:
     
    List<?> list = Lists.factory();
    List<?> list = Lists.<Object>factory();      
     
    If an explicit type parameter is passed, it must not be a wildcard:
        List<?> list = Lists.<?>factory();  // compile-time error
    As before, nested wildcards are permitted:
        List<List<?>> = Lists.<List<?>>factory();  // ok
        
    SuperTypes :
    
    Supertypes When a class instance is created, it invokes the initializer for its supertype. Hence, any restriction that applies to instance creation must also apply to supertypes. 
    In a class declaration, if the supertype or any superinterface has type parameters, these types must not be wildcards.
    
    For example, this declaration is illegal:
        class AnyList extends ArrayList<?> {...} // compile-time error    
        class AnotherList implements List<?> {...} // compile-time error    
        
    But, as before, nested wildcards are permitted
        class NestedList extends ArrayList<List<?>> {...} // ok     