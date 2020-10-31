package com.achraf.finance.repository;

import com.achraf.finance.model.IndiceModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IndicesRepository extends MongoRepository<IndiceModel, String> {

    List<IndiceModel> findByValueOrMicOrLabelContains(String value);

 }
