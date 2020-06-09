####Size Of A Collection :

We can maintain a variable N for keeping count of the no of items in a collection. Increase N when inserting and decrease it when removing. In this way we don't need to iterate through the whole collection to find no of items.

####Throwing Concurrent Modification Exception:

We can maintain  a variable N for keeping count of the no of items in a collection. When creating an iterator, copy this value of N and before calling hasNext always check if current value has changed from this value. If it has changed, throw the Concurrent Modification Exception.


