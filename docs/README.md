<img src="./logo/package_twitter_if9bsyj4/base/full/coverphoto/base_logo_white_background.png" alt="logo"/>

[![Maven](https://img.shields.io/maven-central/v/com.github.imrafaelmerino/mongo-values/1.0.3)](https://search.maven.org/artifact/com.github.imrafaelmerino/mongo-values/1.0.3/jar)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=imrafaelmerino_json-values&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=imrafaelmerino_mongo-values)

- [Introduction](#intro)
- [Requirements](#requirements)
- [Installation](#installation)

## <a name="intro"><a/> Introduction 

It provides a set of codecs to work with [json-values](https://github.com/imrafaelmerino/json-values)
The codecs abstract the processes of going from BSON to JsValue and the other way around by providing 
a BsonReader and a BsonWriter.

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

Just register the registry **JsValuesRegistry**, which contains the necessary codecs, and
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

- For versions prior to 1.0.0, json-values requires Java 8 or later. Please note that only fixes are accepted for these
  versions.
- For versions starting from 1.0.0 and beyond, json-values mandates Java 17 or later.

## <a name="inst"><a/> Installation

To include json-values in your project, add the corresponding dependency to your build tool based on your Java version:

For Java 8 or higher:

```xml

<dependency>
    <groupId>com.github.imrafaelmerino</groupId>
    <artifactId>mongo-values</artifactId>
    <version>0.9.6</version>
</dependency>
```

For Java 17 or higher:

```xml

<dependency>
    <groupId>com.github.imrafaelmerino</groupId>
    <artifactId>mongo-values/artifactId>
    <version>1.0.3</version>
</dependency>
```

Choose the appropriate version according to your Java runtime.

