SET foo "Hello World!!"                     // set the value of foo to "Hello World!!"
                
GET foo                                     // gets the value of foo which is "Hello World!!"
                            
SET foo 100                                 // set foo value to 100
INCR foo                                    // increment foo value to 101
DECR foo                                    // decrement foo value to 100
                            
EXISTS foo                                  // checks if foo exists, if exists return 1 else 0
                            
DEL foo                                     // deletes foo
                            
GET bar                                     // returns nil if bar does not exist
                
FLUSHALL                                    // remove everything

SET server:name mainserver
SET server:port 8000

GET server:name                             // get mainserver
SET server:port                             // get 8000
            
SET greeting "Hello World"                  // set greeting to "Hello World"
GET greeting                                // get greeting
EXPIRE greeting 50                          // expires in 50 seconds
TTL greeting                                // gets the number of seconds remaining before it expires
GET greeting                                // after 50 seconds, it will return nil as it would have expired
SETEX greeting 30 "Hello World"             // sets the value as well as the expiry
PERSIST greeting                            // if we want to persist a value which was earlier set to expire, we can do that using persist before the value expires 

MSET key1 "Hello" key2 "World"              // set multiple values
GET key1                                    // get Hello
GET key2                                    // get World
APPEND key1 " World"                        // appends the given value to key1
GET key1                                    // after appending key1 returns Hello World

RENAME key1 greeting                        // rename key1 to greeting
GET key1                                    // returns nil as it has been updated to another key1
GET greeting                                // greetings return Hello World

----------------------- LIST --------------------------------------------------------
                        
                -- index in the list starts from 0

LPUSH people "Brad"                         // list with one value Brad
LPUSH people "Jen"                          // add Jen to the left/head of the list
LPUSH people "Tom"                          // add Tom to the left/head of the list

LRANGE people 0 -1                          // get element in the range 0 to x, -1 means get everyone : Tom,Jen,Bred
LRANGE people 1 2                           // get element in the range 1 2, gives : Jen, Brad

RPUSH people "Harry"                        // add Harry to the right/end of the list

LLEN people                                 // gets length/size of the list

LPOP people                                 // gets and removes from left/head, removes Tom
RPOP people                                 // gets and removes from right/end, removes Harry

LINSERT people BEFORE "Brad" "Tom"          // insert Tom before Brad, list : Jen, Tom, Brad


---------------------- SET -----------------------------------------------------------

SADD cars "ford"                            // add to the set
SADD cars "honda"                           
SADD cars "bmw"

SISMEMBER cars "ford"                       // check if the given value is a member of the set, returns 1 if its else 0

SMEMBERS cars                               // returns all the members of the set : honda, bmw, ford

SCARD cars                                  // returns the no of elements in the set

SMOVE cars mycars "ford"                    // moves ford from set "cars" to set "mycars"
SMEMBERS cars                               // gives honda, bmw as ford is moved to another set "mycars"
SMEMBERS mycars                             // gives ford

SREM cars "bmw"                             // removes an element from the set


------------------------ SORTED SET ---------------------------------------------------------



