package mongovalues;

import jsonvalues.JsObj;
import jsonvalues.JsObjPair;
import jsonvalues.JsValue;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;


class JsObjCodec extends JsonCodec implements Codec<JsObj> {

    public JsObjCodec(final CodecRegistry registry,
                      final BsonTypeClassMap bsonTypeClassMap
                     ) {
        super(registry,
              bsonTypeClassMap
             );
    }

    @Override
    public JsObj decode(final BsonReader reader,
                        final DecoderContext context
                       ) {

        JsObj obj = JsObj.empty();

        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            obj = obj.set(fieldName,
                          readValue(reader,
                                    context
                                   )
                         );
        }

        reader.readEndDocument();

        return obj;
    }

    @Override
    public void encode(final BsonWriter writer,
                       final JsObj obj,
                       final EncoderContext context
                      ) {

        if (obj.containsKey("$oid")) {
            writer.writeObjectId(new ObjectId(obj.getStr("$oid")));
        }
        else {

            writer.writeStartDocument();

            encodeObj(writer,
                      obj,
                      context
                     );

            writer.writeEndDocument();
        }
    }

    private void encodeObj(final BsonWriter writer,
                           final JsObj obj,
                           final EncoderContext context
                          ) {
        for (final JsObjPair entry : obj) {
            writer.writeName(entry.key());
            @SuppressWarnings("unchecked")
            Codec<JsValue> codec = (Codec<JsValue>) registry.get(entry.value().getClass());
            if (codec == null) throw new IllegalStateException("No codec were found for " + entry.value().getClass());
            context.encodeWithChildContext(codec,
                                           writer,
                                           entry.value()
                                          );
        }
    }

    @Override
    public Class<JsObj> getEncoderClass() {
        return JsObj.class;
    }
}
