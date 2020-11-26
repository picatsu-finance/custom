package com.achraf.finance.web;

import com.achraf.finance.model.IndiceModel;
import com.achraf.finance.repository.IndicesRepository;
import com.achraf.finance.service.IndicesService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping(value = "/api/v1/indices")
@RestController("IndicesController")
@Slf4j
@CrossOrigin
public class IndicesController {


    @Autowired
    private IndicesService indicesService;

    @Autowired
    private IndicesRepository indicesRepository;

    @GetMapping(value = "/get-global")
    public Object getPaginated()  {

        return  this.indicesService.loadIndices();
    }

    @PostMapping(value = "/loadMultiple")
    public void loadIndices(@RequestBody IndiceModel[] indices, HttpServletRequest request) {
        log.info("load multiple");
        for(IndiceModel i : indices) {
            this.addIndices(i, request);
        }

    }





    @GetMapping(value = "/paginate")
    public Page<IndiceModel> getPaginated(@RequestParam int page, @RequestParam int size, HttpServletRequest request)  {

        return  this.indicesRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping(value = "/search-indices/{str}")
    public List<IndiceModel> findCode(@PathVariable String str, HttpServletRequest request) {

        return this.indicesRepository.findByValueContainingOrLabelContaining(str.toUpperCase());
        /*
        return Stream.concat(this.indicesRepository.findByValueContainsIgnoreCase(str.toUpperCase()).stream(),
                this.indicesRepository.findByLabelContainsIgnoreCase(str.toUpperCase()).stream()
        ).collect(Collectors.toList());*/

    }

    @PostMapping(value = "/create")
    public IndiceModel addIndices(@RequestBody IndiceModel indice, HttpServletRequest request) {

        indice.setLabel(indice.getLabel().toUpperCase());
        indice.setMic(indice.getMic().toUpperCase());
        indice.setValue(indice.getValue().toUpperCase());
        if(! this.indicesRepository.existsByMicAndValue(indice.getMic(), indice.getValue()))
        return this.indicesRepository.insert(indice);

        return null;
    }

    @DeleteMapping(value= "/{indice-code}/delete")
    public Boolean deleteIndice(@PathVariable(value= "indice-code") String code, HttpServletRequest request) {

        try {
            indicesRepository.deleteById(code);
            return indicesRepository.existsById(code);
        } catch (Exception e) {
            return null;
        }
    }
}
