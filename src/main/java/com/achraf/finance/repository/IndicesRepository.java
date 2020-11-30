package com.achraf.finance.repository;

import com.achraf.finance.model.IndiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IndicesRepository extends MongoRepository<IndiceModel, String> {

    boolean existsByMicAndValue(String mic, String value);
    List<IndiceModel> findByValueContainingOrLabelContaining(String value);
    List<IndiceModel> findByValueContainsIgnoreCase(String value);
    List<IndiceModel> findByLabelContainsIgnoreCase(String label);
    long deleteAllByMic(String mic);
 }
