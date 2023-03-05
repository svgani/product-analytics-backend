package com.gani.analytics;

import com.gani.analytics.model.Identify;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentifyRepository extends MongoRepository<Identify,String> {
}
