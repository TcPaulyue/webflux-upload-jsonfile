package com.cvicse.highway.service;

import com.cvicse.highway.domain.ExpresswayTollInfo;
import org.springframework.context.ApplicationEvent;


public class ExpresswayEvent extends ApplicationEvent {

    private ExpresswayTollInfo expresswayTollInfo;

    public ExpresswayEvent(Object source,ExpresswayTollInfo expresswayTollInfo) {
        super(source);
        this.expresswayTollInfo = expresswayTollInfo;
    }

    public ExpresswayTollInfo getExpresswayTollInfo() {
        return expresswayTollInfo;
    }

    public void setExpresswayTollInfo(ExpresswayTollInfo expresswayTollInfo) {
        this.expresswayTollInfo = expresswayTollInfo;
    }
}
