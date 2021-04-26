package com.achraf.finance.web;

import com.achraf.finance.model.IndiceModel;
import com.achraf.finance.repository.IndicesRepository;
import com.achraf.finance.service.IndicesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequestMapping(value = "/api/v1/indices")
@RestController("IndicesController")
@Slf4j
@CrossOrigin
public class IndicesController {


    @Autowired
    private IndicesService indicesService;

    @Autowired
    private IndicesRepository indicesRepository;

    @GetMapping(value = "/global")
    @Operation(summary = "Load all indices liste")
    public Object loadIndices()  {

        return  this.indicesService.loadIndices();
    }

    @PostMapping(value = "/loadMultiple")
    @Operation(summary = "add multiples indices at once")
    public void loadIndices(@RequestBody IndiceModel[] indices ) {
        log.info("load multiple");
        for(IndiceModel i : indices) {
            this.addIndices(i);
        }

    }





    @GetMapping(value = "/paginate")
    @Operation(summary = "retieve all liste of indices from db")
    public Page<IndiceModel> getPaginated(@RequestParam int page, @RequestParam int size )  {

        return  this.indicesRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping(value = "/search-indices/{str}")
    @Operation(summary = "search by charater")
    public List<IndiceModel> findCode(@PathVariable String str ) {

        // return this.indicesRepository.findByValueContainingOrLabelContaining(str.toUpperCase());

        return new ArrayList<>(
                Stream.of(this.indicesRepository.findByValueContainsIgnoreCase(str.toUpperCase()),
                        this.indicesRepository.findByLabelContainsIgnoreCase(str.toUpperCase()))
                        .flatMap(List::stream)
                        .collect(Collectors.toMap(IndiceModel::getMic,
                                d -> d,
                                (IndiceModel x, IndiceModel y) -> x == null ? y : x))
                        .values());
    }

    @PostMapping(value = "/create")
    @Operation(summary = "add new indice to db ")
    public IndiceModel addIndices(@RequestBody IndiceModel indice ) {

        indice.setLabel(indice.getLabel().toUpperCase());
        indice.setMic(indice.getMic().toUpperCase());
        indice.setValue(indice.getValue().toUpperCase());
        if(! this.indicesRepository.existsByMicAndValue(indice.getMic(), indice.getValue()))
        return this.indicesRepository.insert(indice);

        return null;
    }

    @DeleteMapping(value= "/{indice-code}")
    @Operation(summary = "delete indice from db")
    public ResponseEntity<?> deleteIndice(@PathVariable(value= "indice-code") String code ) {



        long val =  indicesRepository.deleteAllByMic(code.toUpperCase());

        if ( val == 1) {
            return new ResponseEntity<>("Deleted successfully ", HttpStatus.OK);
        }
        if( val == 0 ) {
            return new ResponseEntity<>("Cannot find Symbol : " + code, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Obscure error ", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
