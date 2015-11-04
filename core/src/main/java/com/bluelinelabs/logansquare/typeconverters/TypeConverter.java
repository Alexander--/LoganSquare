package com.bluelinelabs.logansquare.typeconverters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Implement this interface in order to create a way to custom parse and serialize @JsonFields.
 *
 * If you don't need extra type information to instantiate a field, you should implement
 * {@link SimpleValueConverter} instead!
 */
public interface TypeConverter<T, E extends Type> {
    /**
     * Called to parse the current object in the jsonParser to an object of type T
     *
     * @param jsonParser The JsonParser that is pre-configured for this field.
     * @param type variable type, determined at compile time
     *             (currently only pure Class instances are supported)
     */
    T parse(JsonParser jsonParser, E type) throws IOException;

    /**
     * Called to serialize an object of type T to JSON using the JsonGenerator and field name.
     *
     * @param object The object to serialize
     * @param fieldName The JSON field name of the object when it is serialized
     * @param writeFieldNameForObject If true,  you're responsible for calling jsonGenerator.writeFieldName(fieldName) before writing the field
     * @param jsonGenerator The JsonGenerator object to which the object should be written
     */
    void serialize(T object, String fieldName, boolean writeFieldNameForObject, JsonGenerator jsonGenerator) throws IOException;
}
