package com.achraf.finance.repository;

import com.achraf.finance.model.SelectedTickers;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SelectedTickersRepository extends MongoRepository<SelectedTickers, String> {

    List<SelectedTickers> findAllByUserId(String userId);
}
