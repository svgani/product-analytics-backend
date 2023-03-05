package com.gani.analytics;

import com.gani.analytics.model.Track;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackRepository extends MongoRepository<Track,String> {
}
