package com.louis.kafka.common;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Setter
@Getter
public class MessageExt <Key extends Serializable, Value extends Serializable> extends Message<Key,Value>{

    private long offset;

    private int partition;

    private String messageId;

    private Map<String, String> headers;

    public MessageExt(Key key, Value value, String topic, String messageId) {
        super(key, value, topic, new Date());
        this.messageId = messageId;
    }
    public static class MessageWrapper {
        private String message;
        private String messageId;
        private Map<String, String> headers;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public Map<String, String> getHeaders() {
            return headers;
        }

        public void setHeaders(Map<String, String> headers) {
            this.headers = headers;
        }
    }
}
