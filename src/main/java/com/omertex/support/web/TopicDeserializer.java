package com.omertex.support.web;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.omertex.support.domain.Topic;

import java.io.IOException;

public class TopicDeserializer extends com.fasterxml.jackson.databind.JsonSerializer<Topic>
{
    @Override
    public Topic deserialize (JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
    {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        Topic t = new Topic();
        t.setTopicId(Integer.parseInt(node.asText()));
        return t;
    }

    @Override
    public void serialize(Topic value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException, JsonProcessingException
    {
        jgen.writeStartObject();
        jgen.writeNumberField("topicId", value.getTopicId());
        jgen.writeStringField("topicName", value.getTopicName());
        jgen.writeEndObject();
    }
}


