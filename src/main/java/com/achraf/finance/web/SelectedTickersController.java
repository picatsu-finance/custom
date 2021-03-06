package com.achraf.finance.web;

import com.achraf.finance.model.SelectedTickers;
import com.achraf.finance.repository.SelectedTickersRepository;
import io.swagger.v3.oas.annotations.Operation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping(value = "/api/v1/custom/selected")
@RestController("SelectedTickersController")
@Slf4j
@CrossOrigin
public class SelectedTickersController {

    @Autowired
    private SelectedTickersRepository selectedTickersRepository;

    @GetMapping(value = "/{id}")
    @Operation(summary = "get selected liste of selectedTickers from DB ")
    public List<SelectedTickers> getMySelectedTickersList(HttpServletRequest request,
                                                          @PathVariable String id)  {

         return  selectedTickersRepository.findAllByUserId(id);
    }

    @PostMapping(value = "/create")
    @Operation(summary = "add custom followed tickers to db")
    public SelectedTickers addTickers(@RequestBody SelectedTickers ticker, HttpServletRequest request) {

        return this.selectedTickersRepository.insert(ticker);

    }

    @DeleteMapping(value= "/{userID}")
    @Operation(summary = "delete selected ticker from specific user")
    public ResponseEntity<?> deleteTicker(@PathVariable(value= "userID") String userID,
                                @RequestBody SelectedTickers ticker,
                                HttpServletRequest request) {


        long val =  selectedTickersRepository.deleteAllByUserId(userID);

        if ( val == 1) {
            return new ResponseEntity<>("Deleted successfully ", HttpStatus.OK);
        }
        if( val == 0 ) {
            return new ResponseEntity<>("Cannot find User : " + userID, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Obscure error ", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
