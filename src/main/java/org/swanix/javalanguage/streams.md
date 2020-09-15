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
    