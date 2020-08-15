package com.cvicse.vertxwithspring.domain;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@Document(collection = "ExpresswayToll")
public class ExpresswayTollInfo {
    @Id
    private String id;

    @NotNull
    private JSONObject content;


    public ExpresswayTollInfo() {
    }

    public ExpresswayTollInfo(JSONObject content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONObject getContent() {
        return content;
    }

    public void setContent(JSONObject content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "com.cvicse.ExpresswayTollInfo{" +
                "id='" + id + '\'' +
                ", content=" + content +
                '}';
    }
}