package com.cvicse.highway.repository;

import com.cvicse.highway.domain.ExpresswayTollInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ExpresswayRepository extends ReactiveMongoRepository<ExpresswayTollInfo, String> {

   Flux<ExpresswayTollInfo> saveAll(Flux<ExpresswayTollInfo> just);
}
