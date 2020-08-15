package com.cvicse.highway.service;

import com.cvicse.highway.domain.ExpresswayTollInfo;
import com.cvicse.highway.repository.ExpresswayRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ExpresswayListener{

    private final ExpresswayRepository expresswayRepository;

    private ReentrantLock lock = new ReentrantLock();

    private Queue<ExpresswayTollInfo> expresswayTollInfoQueue = new ConcurrentLinkedQueue<>();


    public ExpresswayListener(ExpresswayRepository expresswayRepository) {
        this.expresswayRepository = expresswayRepository;
    }

    @EventListener
    @Async
    public void onApplicationEvent(ExpresswayEvent expresswayEvent) {
        expresswayTollInfoQueue.add(expresswayEvent.getExpresswayTollInfo());
        lock.lock();
        try {
            if (expresswayTollInfoQueue.size() == 1000) {
                expresswayRepository.saveAll(Flux.fromStream(expresswayTollInfoQueue.stream())).subscribe();
                expresswayTollInfoQueue.clear();
                System.out.println("存入1000条数据到mongodb");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
