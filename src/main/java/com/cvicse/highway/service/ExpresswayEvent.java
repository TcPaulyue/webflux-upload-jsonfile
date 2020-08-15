package com.cvicse.highway.service;

import com.cvicse.highway.domain.ExpresswayTollInfo;
import org.springframework.context.ApplicationEvent;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ExpresswayEvent extends ApplicationEvent {

    private  Queue<ExpresswayTollInfo> expresswayTollInfoQueue = new ConcurrentLinkedQueue<>();

    public ExpresswayEvent(Object source) {
        super(source);
    }

    public Queue<ExpresswayTollInfo> getExpresswayTollInfoQueue() {
        return expresswayTollInfoQueue;
    }

    public void addExpresswayTollInfo(ExpresswayTollInfo expresswayTollInfo) {
        this.expresswayTollInfoQueue.add(expresswayTollInfo);
    }

    public Integer getQueueSize(){
        return expresswayTollInfoQueue.size();
    }
}
