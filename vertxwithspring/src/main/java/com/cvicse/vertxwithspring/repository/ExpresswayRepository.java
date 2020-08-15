package com.cvicse.vertxwithspring.repository;

import com.cvicse.vertxwithspring.domain.ExpresswayTollInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpresswayRepository extends MongoRepository<ExpresswayTollInfo,String> {
}
