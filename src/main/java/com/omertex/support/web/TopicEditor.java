package com.omertex.support.web;


import com.omertex.support.domain.Topic;

import java.beans.PropertyEditorSupport;

public class TopicEditor extends PropertyEditorSupport
{
    // Converts a String to a Category (when submitting form)
    @Override
    public void setAsText(String text) {
        Topic t = new Topic();
        t.setTopicId(Integer.parseInt(text));
        this.setValue(t);
    }

    // Converts a Category to a String (when displaying form)
    @Override
    public String getAsText() {
        Topic t = (Topic) this.getValue();
        return t.getTopicName();
    }
}
