#Kafka
Apache Kafka is an open source distributed event streaming platform used by thousands of companies for high-performance data pipelines,
streaming analytics, data integration, and mission-critical applications.

**Advantages of Kafka**
1. Distributed
2. Horizontally scalable
3. High Performs
4. Resilient architecture
5. Fault Tolerant

**Use Cases:**
1. De-coupling of system dependencies
2. Message System
3. Activity Tracking
4. Gathering metrics from different locations
5. Application logs gathering
6. Stream processing (with the Kafka Streams API or Spark for example) 
7. Integration with Spark, Flink, Storm, Hadoop and many other Big Data technologies.

Source Systems -> Producers -> Kafka -> Consumers -> Target Systems
                                
###Cluster: 

###Broker
A kafka cluster composed of multiple brokers (servers). Each broker is identified with its ID (integer). 
Each broker contains certain topic partitions. After connecting to any broker, you will be connected to the entire cluster.
###Topic
Topic is a particular stream of data. It is similar to a table in database. You can have as many topics as you want.
#####Partition
Topics are split in partitions. Each partition is ordered and each message within a partition gets an incremental id, called offsets.
Data is pushed to a topic and not to a partition. Data is assigned randomly to a partition unless a key is provided.
#####Replication
Topic should have a replication > 1 (usually between 2 and 3). This way is a broker is down  and another broker can serve the data
#####Leader
At any time only 1 broker can be a leader for a given partition. Only that leader can receive and serve data for a partition.
The other brokers will sync the data. Each partition has: one leader, and multiple ISR (in-sync replicas).

###Producers
Producers write data to topics. They only have to specify the topic name and one broker to connect to, and kafa will automatically take care of routing the data to the right brokers.
Producers can choose to receive acknowledgement of data writes:
- Acks=0: Producer won't wait for acknowledgement (possible data loss)
- Acks=1: Producer will wait for leader acknowledgement (limited data loss)
- Acks=all: Leader+replicas acknowledgement (no data loss)

Producers can choose to send a key with the message. If a key is sent, then the producer has the guarantee that all messages for that key will always go to the same partition.
This enables to guarantee ordering for a specific key.


###Consumers
Consumers read data from a topic.
They only have to specify the topic name and one broker to connect to, and kafka will autimatically take care of pulling the data from right brokers.
Data is read in order for each partition.

####Consumer Groups
Consumers read data in consumer groups
Each consumer within a group reads from exclusive partitions. You cannot have more consumers than partitions (otherwise some will be inactive)

#####Consumer Offsets 
Kafka stores the offset at which a consumer group has been reading
The offsets commit live in a kafka topic name "_conumer_offsets"
When a consumer has processed data received from kafka, it should be commiting the offsets
If a consumer process dies, it will be able to read back from where it felt off thanks to consumer offsets.





