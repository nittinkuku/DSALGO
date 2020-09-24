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

##### Comparison and Bounds
    interface Comparable<T> {  
        public int compareTo(T o);
    }
    
    Find max of a collection :
    
    public static <T extends Comparable<T>> T max(Collection<T> coll) {    
        T candidate = coll.iterator().next();    
        for (T elt : coll) {        
            if (candidate.compareTo(elt) < 0) candidate = elt;    
        }    return candidate;
    }
    
    As with wildcards, bounds for type variables are always indicated by the keyword extends, even when the bound is an interface rather than a class
    
    Unlike wildcards, type variables must always be bounded using extends, never super.
    
##### Multiple Bounds
    public static <S extends Readable & Closeable, T extends Appendable & Closeable> 
    
    When multiple bounds appear, the first bound is used for erasure.   
    
    public static <T extends Object & Comparable<? super T>>  T max(Collection<? extends T> coll)
    
    Without the T extends Object, the erased type signature for max would have Comparable as the return type, whereas in legacy libraries the return type is Object

##### Bridges
    interface Comparable {  
        public int compareTo(Object o);
    }class 
    
    Integer implements Comparable {  
        private final int value;  
        public Integer(int value) { 
            this.value = value; 
        }  
        
        public int compareTo(Integer i) {    
            return (value < i.value) ? -1 : (value == i.value) ? 0 : 1;  
        }  
        
        public int compareTo(Object o) {    
            return compareTo((Integer)o);  
        }
    }
    
    Above shows the Comparable interface and a simplified version of the Integer class in Java before generics. 
    In the nongeneric interface, the compareTo method takes an argument of type Object. In the nongeneric class, there are two compareTo methods. 
    The first is the naïve method you might expect, to compare an integer with another integer.    
    The second compares an integer with an arbitrary object: it casts the object to an integer and calls the first method. 
    The second method is necessary in order to override the compareTo method in the Comparable interface, because overriding occurs only when the method signatures are identical. 
    
    This second method is called a bridge.
    
##### Declarations
    In a generic class, type parameters appear in the header that declares the class, but not in the constructor:
    
    class Pair<T, U> {  
        private final T first;  
        private final U second;  
        
        public Pair(T first, U second) { 
            this.first=first; this.second=second; 
        }  
        public T getFirst() { 
            return first; 
        }  
        public U getSecond() { 
            return second; 
        }
    }
    
    actual type parameters are passed to the constructor whenever it is invoked:
        Pair<String, Integer> pair = new Pair<String, Integer>("one",2);
        
    Look Out for This! 
    A common mistake is to forget the type parameters when invoking the constructor:
        Pair<String, Integer> pair = new Pair("one",2);
    This mistake produces a warning, but not an error. It is taken to be legal, because Pair is treated as a raw type.

    Static Members : 
    
    Because generics are compiled by erasure, at run time the classes List<Integer>, List<String>, and List<List<String>> are all    implemented by a single class, namely List
    
    static members of a generic class are shared across all instantiations of that class, including instantiations at different types.    
    Static members of a class cannot refer to the type parameter of a generic class, and when accessing a static member the class name should not be parameterized.

    class Cell<T> {  
        private final int id;  
        private final T value;  
        private static int count = 0;
        private static int nextId() { 
            return count++; 
        }
        public static int getCount() { 
            return count; 
        }
    }
    
    Because static members are independent of any type parameters, we are not permitted to follow the class name with type parameters when accessing a static member:
    Cell.getCount();            // ok
    Cell<Integer>.getCount();  // compile-time error
    Cell<?>.getCount();        // compile-time error
    
    The count is static, so it is a property of the class as a whole, not any particular instance. For the same reason, you may not refer to a type parameter anywhere within a static member.  

##### Nested classes
    Java permits nesting one class inside another. If the outer class has type parameters and the inner class is not static, then type parameters of the outer class are visible within the inner class.

    1.  Type parameters are in scope for nested, nonstatic classes
    
    public class LinkedCollection<E> extends AbstractCollection<E> {
      private class Node {
        private E element;
        private Node next = null;
        private Node(E elt) { element = elt; }
      }
      .....
     } 
     
    E is in scope of Node class.
    
    If the node classes had been made public rather than private, you would refer to the node class in the first example as LinkedCollection<E>.Node, 
    whereas you would refer to the node class in the second example as LinkedCollection.Node<E>
    
    2.  Type parameters are not in scope for nested, static classes
        
    class LinkedCollection<E> extends AbstractCollection<E> {  
        private static class Node<T> {    
            private T element;    
            private Node<T> next = null;    
            private Node(T elt) { element = elt; }  
        }  
        ..............
    } 
    
##### How Erasure Works
    The erasure of a type is defined as follows: 
    
    Drop all type parameters from parameterized types, and replace any type variable with the erasure of its bound, or with Object if it has no bound, or with the erasure of the leftmost bound if it has multiple bounds
    
    -   The erasure of List<Integer>, List<String>, and List<List<String>> is List.
    -   The erasure of List<Integer>[] is List[].
    -   The erasure of List is itself, similarly for any raw type.
    -   The erasure of int is itself, similarly for any primitive type.
    -   The erasure of Integer is itself, similarly for any type without type parameters.
    -   The erasure of T in the definition of is Object, because T has no bound.
    -   The erasure of T in the definition of max is Comparable, because T has bound Comparable<? super T>.
    -   The erasure of T in the final definition of max is Object, because T has bound Object & Comparable<T> and we take the erasure of the leftmost bound.
    -   The erasures of S and T in the definition of copy are Readable and Appendable, because S has bound Readable & Closeable and T has bound Appendable & Closeable.
    -   The erasure of LinkedCollection<E>.Node or LinkedCollection.Node<E> is LinkedCollection.Node.
    
    class Overloaded2 {  
        // compile-time error, cannot overload two methods with same erasure  
        public static boolean allZero(List<Integer> ints) {    
            for (int i : ints) if (i != 0) 
                return false;    
               return true;  
            }  
        public static boolean allZero(List<String> strings) {    
            for (String s : strings) 
            if (s.length() != 0) 
                return false;    
                return true;  
            }
        }
        
    In this case the erasures of the signatures of both methods are identical: boolean allZero (List)

##### Generic Library with Legacy Client
    Every parameterized type is a subtype of the corresponding raw type, so a value of the parameterized type can be passed where a raw type is expected. 
    Usually, it is an error to pass a value of a supertype where a value of its subtype is expected, but Java does permit a value of a raw type to be passed where a parameterized type is expected
    however, it flags this circumstance by generating an unchecked conversion warning. 
    For instance, you can assign a value of type Stack<E> to a variable of type Stack, since the former is a subtype of the latter. 
    You can also assign a value of type Stack to a variable of type Stack<E>, but this will generate an unchecked conversion warning.

     When we invoke a method on a receiver of a raw type, the method is treated as if the type parameter is a wildcard, so getting a value from a raw type is safe (hence, no warning for the invocation of pop), 
     but putting a value into a raw type issues a warning (hence, the warning for the invocation of push); this is an instance of the Get and Put Principle.
    
#### Reifiable Types
    In Java, the type of an array is reified with its component type, while the type of a parameterized type is reified without its type parameters.
    
    In Java, we say that a type is reifiable if the type is completely represented at run time — that is, if erasure does not remove any useful information. 
    
    A type is reifiable if it is one of the following:
    
    1.  A primitive type    (such as int)
    2.  A nonparameterized class or interface type  (such as Number, String, or Runnable)
    3.  A parameterized type in which all type arguments are unbounded wildcards    (such as List<?>, ArrayList<?>, or Map<?, ?>)
    4.  A raw type      (such as List, ArrayList, or Map)
    5.  An array whose component type is reifiable      (such as int[], Number[], List<?>[], List[], or int[][])
    
    
    A type is not reifiable if it is one of the following:
    
    1.  A type variable     (such as T)
    2.  A parameterized type with actual parameters     (such as List<Number>, ArrayList<String>, or Map<String, Integer>)
    3.  A parameterized type with a bound       (such as List<? extends Number> or Comparable<? super String>)

    Instance tests and casts depend on examining types at run time, and hence depend on reification. 
    For this reason, an instance test against a type that is not reifiable reports an error, and a cast to a type that is not reifiable usually issues a warning.

    if (o instanceof List<E>)                       // compile-time error
    Iterator<E> it2 = ((List<E>)o).iterator();      // unchecked cast
    
    To fix the problem, we replace the nonreifiable type List<E> with the reifiable type List<?>.
    
    if (o instanceof List<?>) 
    Iterator<?> it2 = ((List<?>)o).iterator();
     
    Nonreifiable Casts An instance test against a type that is not    reifiable is always an error. However, in some circumstances a cast to a type that is not reifiable is permitted.
    
    public static <T> List<T> asList(Collection<T> c)  throws InvalidArgumentException {
        if (c instanceof List<?>) {    
            return (List<T>)c;  
        }
    } 
    
    class Promote {
      public static List<String> promote(List<Object> objs) {
        for (Object o : objs)
          if (!(o instanceof String))
            throw new ClassCastException();
        return (List<String>)(List<?>)objs;         // unchecked cast
      }
    }
      
    It is illegal to cast a list of objects to a list of strings, so the cast must take place in two steps. First, cast the list of objects into a list of wildcard type; this cast is safe. 
    Second, cast the list of wildcard type into a list of strings; this cast is permitted but generates an unchecked warning:
    
    Exactly the same technique can be used to promote a raw list to a list of strings if the raw list contains only strings. 
    This technique is important for fitting together legacy and generic code, and is one of the chief reasons for using erasure to implement generics
    
    If a method deliberately contains unchecked casts, you may wish to precede it with the annotation @SuppressWarnings("unchecked") in order to avoid spurious warnings.
    
#### Exception Handling
    In a try statement, each catch clause checks whether the thrown exception matches a given type. This is the same as the check performed by an instance test, 
    so the same restriction applies: the type must be reifiable. Further, the type in a catch clause is required to be a subclass of Throwable. 
    Since there is little point in creating a subclass of Throwable that cannot appear in a catch clause, 
    the Java compiler complains if you attempt to create a parameterized subclass of Throwable.    


#### Array Creation
    Arrays reify their component types, meaning that they carry run-time information about the type of their components. This reified type information is used in instance tests and casts, 
    and also used to check whether assignments into array components are permitted.
    
    Integer[] ints = new Integer[] {1,2,3};
    Number[] nums = ints;
    nums[2] = 3.14;             // array store exception
    
    Because arrays must reify their component types, it is an error to create a new array unless its component type is reifiable.
    
    T[] a = new T[c.size()];  // compile-time error as type variable is not a refiable type
     
    The Principle of Truth in Advertising: the reified type of an array must be a subtype of the erasure of its static type.

     class Right {  
        public static <T> T[] Array(toCollection<T> c, T[] a) {    
            if (a.length < c.size())      
            a = (T[])java.lang.reflect.Array.newInstance(a.get Class().getComponentType(), c.size());    
            int i=0; 
            for (T x : c) 
            a[i++] = x;    
            if (i < a.length) 
            a[i] = null;    
            return a;  
        }  
     }
     
    A subtle point: 
                   in the call to newInstance, why is the result type Object rather than Object[]? Because, in general, 
    newInstance may return an array of a primitive type such as int[], which is a subtype of Object but not of Object[]. 
    However, that won’t happen here because the type variable T must stand for a reference type.
     
    The first method returns an array with the reified component type Object, while the second copies the reified component type from the argument array.
    A call to the first method, c.toArray(), returns the same result as a call to the second method with an empty array of objects, c.toArray(new Object[0]). 
    
    Often on encountering this design, programmers presume that the array argument exists mainly for reasons of efficiency, 
    in order to minimize allocations by reusing the array. This is indeed a benefit of the design, but its main purpose is to get the reified types correct!

    Instances of the class Class represent information about a class at run time; there are also instances of this class that represent primitive types and arrays.

    List<Integer>[] intLists = (List<Integer>[])new List[] {Arrays.asList(1)};  // unchecked cast
    List<? extends Number>[] numLists = intLists;
    numLists[0] = Arrays.asList(1.01);
    int n = intLists[0].get(0);                     // class cast exception!
    
    This time the attempted store does not fail, even though it should, because the check against the reified type is inadequate: 
    the reified information contains only the erasure of the type, indicating that it is an array of List, not an array of List<Integer>.

    Principle of Indecent Exposure: never publicly expose an array where the components do not have a reifiable type.

    List<Integer> a = Arrays.asList(new Integer[] { 1, 2, 3 });
    List<Integer> b = Arrays.asList(new Integer[] { 4, 5, 6 });
    List<List<Integer>> x = Arrays.asList(new List<Integer>[] { a, b });  // generic array creation
    
    The first two calls are fine, but since List<Integer> is not a reifiable type, the third warns of an unchecked generic array creation at compile time.
    
    A similar problem occurs if one attempts to create a list of a generic type.
    
    public static List<E> singleton(E elt) {  
        return Arrays.asList(elt);  // generic array creation
    }
    
    Normally, generic array creation reports an error. As a workaround, one can create the array at a reifiable type and perform an unchecked cast. 
    That workaround is not available for the array creation that is implicit in the use of varargs, so in this case generic array creation issues a warning rather than an error. 
    A generic array creation warning is just like an unchecked warning, in that it invalidates the cast-iron guarantee that accompanies generics. 

    List.class is permitted but List<?>.class is illegal.
    
    Currently, array creation is restricted to arrays of reifiable type. But it is permitted to declare an array of nonreifiable type or to cast to an array type that is not reifiable, 
    at the cost of an unchecked warning somewhere in the code
    
#### Reflection
    the method getClass is defined on every object and returns a class token that represents the reified type information carried by that object at run-time. 
    Here is an example:
        Class ki = Integer.class;
        Number n = new Integer(42);
        Class kn = n.getClass();
        assert ki == kn;

    Class now takes a type parameter, so Class<T> is the type of the class token for the type T. The preceding code is now written as follows:
    
    Class<Integer> ki = Integer.class;
    Number n = new Integer(42);
    Class<? extends Number> kn = n.getClass();
    assert ki == kn;
    
    If T is a type without type parameters, then T.class has type Class<T>, and if e is an expression of type T then e.getClass() has type Class<? extends T>. 
    The wildcard is needed because the type of the object referred to by the variable may be a subtype of the type of the variable.

    class Class<T> {  
        public T newInstance();  
        public T cast(Object o);  
        public Class<? super T> getSuperclass();  
        public <U> Class<? extends U> asSubclass(Class<U> k);  
        public <A extends Annotation> A getAnnotation(Class<A> k);  
        public boolean isAnnotationPresent(Class<? extends Annotation> k);  
        ...
    }
    
    The first returns a new instance of the class, which will, of course, have type T. 
    The second casts an arbitrary object to the receiver class, and so it either throws a class cast exception or returns a result of type T. 
    The third returns the superclass, which must have the specified type. 
    The fourth checks that the receiver class is a subclass of the argument class, and either throws a class cast exception or returns the receiver with its type suitably changed.
    
    The convenience class Collections contains a method that builds a wrapper that checks whether every element added to or extracted from the given list belongs to the given class.
    
    public static <T> List<T> checkedList(List<T> l, Class<T> k)
    
##### Reflected Types are Reifiable Types
    List<Integer> ints = new ArrayList<Integer>();
    List<String> strs = new ArrayList<String>();
    assert ints.get Class() == strs.getClass();
    assert ints.getClass() == ArrayList.class;
    
    Here the type list of integers and the type list of strings are both represented by the same class token, the class literal for which is written ArrayList.class.
    Because the class always represents a reifiable type, there is no point in parameterizing the class Class with a type that is not reifiable. 
    Hence, the two main methods for producing a class with a type parameter, namely the getClass method and class literals, 
    are both designed to yield a reifiable type for the type parameter in all cases.
    
    Class<? extends List> k = ints.getClass();

    Here the expression ints has type List<Integer>, so the expression int.getClass() has type Class<? extends List>; 
    this is the case because erasing List<Integer> yields the raw type List. The actual value of k is ArrayList.class, 
    which has type Class<ArrayList>, which is indeed a subtype of Class<? extends List>.Class literals are also restricted; 
    it is not even syntactically valid to supply a type parameter to the type in a class literal. 
    
    public Class<?> k = List<Integer>.class; // syntax error

     Wherever a type of the form Class<T> appears, the type T should be a reifiable type. The same is true for types of the form T[]

##### Reflection for Primitive Types
    Every type in Java, including primitive types and array types, has a class literal and a corresponding class token.

    Unchecked casts are required because the methods in the Java reflection library cannot return sufficiently accurate types, for various reasons. 
    The method getComponentType is in the class Class<T>, and Java provides no way to restrict the receiver type to be Class<T[]> in the signature of the method 
    (though the call raises an exception if the receiver is not a class token for an array type). 
    The method newInstance in java.lang.reflect.Array must have the return type Object rather than the return type T[], because it may return an array of a primitive type. 
    The method getClass, when called on a receiver of type T, returns a token not of type Class<? extends T> but of type Class<?>, 
    because of the erasure that is required to ensure that class tokens always have a reifiable type.
    
    As an example of the use of the first method, here is a method that copies a collection into a fresh collection of the same kind, preserving the type of the argument:
    
    public static <T, C extends Collection<T>> C copy(C coll) {  
        C copy = GenericReflection.newInstance(coll);  
        copy.addAll(coll);  
        return copy;
    }
    
    The information about generic types is essentially a comment. It is ignored when running the code, and it is preserved only for use in reflection.   

#### Effective Generics
    It is important to be aware that the guarantees offered by generic types apply only if there are no unchecked warnings. 
    This means that generic types are useless for ensuring security in code written by others, since you have no way of knowing whether that code raised unchecked warnings when it was compiled.

// TO DO

##### Maintain Binary Compatibility
    Binary compatibility is guaranteed if the erasure of the signature of the generic code is identical to the signature of the legacy code and if both versions compile to the same bytecode. 
    Usually, this is a natural consequence of generification.
    
    Adjusting the Erasure :

    // legacy version
    public static Object max(Collection coll)
    
    // generic version -- breaks binary compatibility
    public static <T extends Comparable<? super T>>T max(Collection<? extends T> coll)
   
    this signature has the wrong erasure—its return type is Comparable rather than Object. In order to get the right signature, we need to fiddle with the bounds on the type parameter, using multiple bounds
    
    When there are multiple bounds, the leftmost bound is taken for the erasure. So the erasure of T is now Object, giving the result type we require.






























