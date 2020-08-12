package com.cvicse.highway.repository;

import com.cvicse.highway.domain.ExpresswayTollInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpresswayRepository extends ReactiveMongoRepository<ExpresswayTollInfo, String> {

}
