package com.bluelinelabs.logansquare;

import com.bluelinelabs.logansquare.typeconverters.TypeConverter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.ResolvedType;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

public class LoganSquareCodec extends ObjectCodec {
    @Override
    public <T> T readValue(JsonParser jp, Class<T> clazz) throws IOException {
        final TypeConverter<T, Type> converter = LoganSquare.getConverter(clazz);

        return converter != null ? converter.parse(jp, clazz) : LoganSquare.mapperFor(clazz).parse(jp);
    }

    @Override
    public <T> T readValue(JsonParser jp, TypeReference<?> valueTypeRef) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: runtime type resolution is not implemented");
    }

    @Override
    public <T> T readValue(JsonParser jp, ResolvedType valueType) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: runtime type resolution is not implemented");
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jp, Class<T> valueType) throws IOException {
        return LoganSquare.mapperFor(valueType).parseList(jp).iterator();
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jp, TypeReference<?> valueTypeRef) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: runtime type resolution is not implemented");
    }

    @Override
    public <T> Iterator<T> readValues(JsonParser jp, ResolvedType valueType) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: runtime type resolution is not implemented");
    }

    @Override
    public void writeValue(JsonGenerator jgen, Object value) throws IOException {
        final Class<?> clazz = value.getClass();

        TypeConverter<Object, ?> converter = LoganSquare.getConverter(clazz);

        if (converter != null) {
            converter.serialize(value, "", false, jgen);
        } else {
            LoganSquare.mapperFor(clazz).serialize(value, jgen, true);
        }
    }

    @Override
    public <T extends TreeNode> T readTree(JsonParser jp) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }

    @Override
    public void writeTree(JsonGenerator jg, TreeNode tree) throws IOException {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }

    @Override
    public TreeNode createObjectNode() {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }

    @Override
    public TreeNode createArrayNode() {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }

    @Override
    public JsonParser treeAsTokens(TreeNode n) {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }

    @Override
    public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        throw new UnsupportedOperationException("LoganSquare: JSON tree API is not supported");
    }
}