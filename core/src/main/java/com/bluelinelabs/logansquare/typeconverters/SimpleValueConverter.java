package com.bluelinelabs.logansquare.typeconverters;

import com.fasterxml.jackson.core.JsonParser;

import java.io.IOException;
import java.lang.reflect.Type;

public abstract class SimpleValueConverter<T> implements TypeConverter<T, Type> {
    @Override
    public final T parse(JsonParser jsonParser, Type type) throws IOException {
        return parse(jsonParser);
    }

    public abstract T parse(JsonParser jsonParser) throws IOException;
}
