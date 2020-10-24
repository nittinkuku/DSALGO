#Big O
It is equation that describes how time scales with respect to some input variables.

Many BigO times do not use "N". It's just a variable and any letter will do!!!    

#####If you have two different steps add them


        function something(){
            doStep1(); //O(a)
            doStep2(); //O(b) 
            // O(a+b)
        }
#####Drop Constants
    
        
        function minMax(array){
            min,max=null
            for each e in array
                min = MIN(e, min)
            
            for each e in array
                max = MAX(e,max)
            
            // O(2n) --wrong
            // O(n) --right
        }
        
        function minMax2(array){
            min,max=null
                for each e in array
                    min = MIN(e,min)
                    max = MAX(e,max)
            // O(n)
        }
        
#####Different input = Different Variable


    int intersectionSize(arrayA, arrayB){
    int const=0;
        for a in arrayA{
            for b in arrayB{
                if a==b{
                    count = count+1;
                    }
                }
            }        
            return count
        }
        
    // O(n^2) --wrong
    // O(a*b) -- right     
    
#####Drop non-dominate terms

        
        function whyWouldIdoThis(array){
            max=NULL
            for each a in array{
                max = MAX(a, max)
            }
            //O(n)
            for each a in array{
                for each b in array{
                    print a,b
                }
            }
            
            //O(n^2)
        }
        
        // O(n^2) <= O(n+n^2) <= O(n^2+n^2)
