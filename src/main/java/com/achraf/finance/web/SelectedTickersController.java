package com.achraf.finance.web;

import com.achraf.finance.model.SelectedTickers;
import com.achraf.finance.repository.SelectedTickersRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public List<SelectedTickers> getMySelectedTickersList(HttpServletRequest request,
                                                          @PathVariable String id)  {

         return  selectedTickersRepository.findAllByUserId(id);
    }

    @PostMapping(value = "/create")
    public SelectedTickers addTickers(@RequestBody SelectedTickers ticker, HttpServletRequest request) {

        return this.selectedTickersRepository.insert(ticker);

    }

    @DeleteMapping(value= "/{userID}/delete")
    public Boolean deleteTicker(@PathVariable(value= "userID") String userID,
                                @RequestBody SelectedTickers ticker,
                                HttpServletRequest request) {

        try {

            this.selectedTickersRepository.delete(ticker);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
