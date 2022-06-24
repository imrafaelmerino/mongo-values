<img src="./logo/package_twitter_if9bsyj4/base/full/coverphoto/base_logo_white_background.png" alt="logo"/>

[![Maven](https://img.shields.io/maven-central/v/com.github.imrafaelmerino/mongo-values/0.9.2)](https://search.maven.org/artifact/com.github.imrafaelmerino/mongo-values/0.9.2/jar)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=imrafaelmerino_json-values&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=imrafaelmerino_mongo-values)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=imrafaelmerino_json-values&metric=alert_status)](https://sonarcloud.io/dashboard?id=imrafaelmerino_mongo-values)

- [Introduction](#intro)
- [Requirements](#requirements)
- [Installation](#installation)

## <a name="intro"><a/> Introduction 

It provides a set of codecs to work with [json-values](https://github.com/imrafaelmerino/json-values)
The codecs abstracts the processes of decoding a BSON value into JsValue  using a 
BsonReader and encoding a JsValue into a BSON value using a BsonWriter.

Please find below the supported BSON types and their json-value equivalents:

```java    
    
Map<BsonType, Class<?>> map = new HashMap<>();
map.put(BsonType.NULL, JsNull.class);
map.put(BsonType.ARRAY, JsArray.class);
map.put(BsonType.BINARY, JsBinary.class);
map.put(BsonType.BOOLEAN, JsBool.class);
map.put(BsonType.DATE_TIME, JsInstant.class);
map.put(BsonType.DOCUMENT, JsObj.class);
map.put(BsonType.DOUBLE, JsDouble.class);
map.put(BsonType.INT32, JsInt.class);
map.put(BsonType.INT64, JsLong.class);
map.put(BsonType.DECIMAL128, JsBigDec.class);
map.put(BsonType.STRING, JsStr.class);

``` 

A CodecRegistry contains a set of Codec instances that are accessed according to the Java classes
that they encode from and decode to

Just register the registry **JsValuesRegistry**, that contains the necessary codecs, and
when defining the collection, specify the generic type **JsObj**.
You can use the MongoDB API **without doing any kind of conversion between BSON and JsObj**:


```   java
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import mongovalues.JsValuesRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jsonvalues.JsObj

ConnectionString connString = ???;
 
MongoClientSettings  settings =
             MongoClientSettings.builder()
                                .applyConnectionString(connString)
                                .codecRegistry(JsValuesRegistry.INSTANCE)
                                .build();

MongoClient mongoClient = result = MongoClients.create(settings);

MongoCollection<JsObj> collection = mongoClient.getCollection("db","collection");
                                                                                             
``` 


## <a name="requirements"><a/> Requirements 

   - Java 8 or greater
   - mongodb-driver-core dependency

## <a name="installation"><a/> Installation 


```xml

<dependency>
  <groupId>com.github.imrafaelmerino</groupId>
  <artifactId>mongo-values</artifactId>
  <version>0.9.2</version>
</dependency>

```