package com.cvicse.highway.service;

import com.cvicse.highway.repository.ExpresswayRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ExpresswayListener{

    private final ExpresswayRepository expresswayRepository;

    public ExpresswayListener(ExpresswayRepository expresswayRepository) {
        this.expresswayRepository = expresswayRepository;
    }

    @EventListener
    @Async
    public void onApplicationEvent(ExpresswayEvent expresswayEvent) {
            if(expresswayEvent.getQueueSize()>2){
                expresswayRepository.saveAll(Flux.fromStream(expresswayEvent.getExpresswayTollInfoQueue().stream())).subscribe();
                expresswayEvent.getExpresswayTollInfoQueue().clear();
                System.out.println("存入1000条数据到mongodb");
            }

    }

}
