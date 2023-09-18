package com.example.find.controller;


import com.example.find.Entity.Accomdation;
import com.example.find.dto.request.AccomRequest;
import com.example.find.dto.response.AccomResponse;
import com.example.find.service.AccomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/find/accom")
public class AccomController {

    private final AccomService accomService;

    //기본적인 CRUD 구현

    @PostMapping
    public void save(@RequestBody AccomRequest accomRequest){
        accomService.save(accomRequest);
    }

    @GetMapping
    public Page<AccomResponse> findAll(@RequestParam(required = false,defaultValue = "0")
            Integer page,
                                       @RequestParam(required = false,defaultValue = "10")
            Integer size){


        System.out.println("find all 요청이 들어옴");

        return accomService.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/sale")
    public Page<AccomResponse> findAllOrderBySale(@RequestParam(required = false,defaultValue = "0")
                                       Integer page,
                                       @RequestParam(required = false,defaultValue = "4")
                                       Integer size){


        System.out.println("find all 요청이 들어옴");

        return accomService.findAllOrderBySale(PageRequest.of(page,size));
    }

    @GetMapping("/hotel")
    public List<AccomResponse> findAllHotel(){

        return accomService.findAllHotel();
    }

    @GetMapping("/pension")
    public List<AccomResponse> findAllPension(){

        return accomService.findAllPension();
    }

    //숙박 시설 아이디
    @GetMapping("{id}")
    public AccomResponse findAccom(@PathVariable Long id ){

        return AccomResponse.of(accomService.findAccom(id));
    }

    //Update 로직
    @PutMapping("{id}")
    public void updateAccom(@PathVariable Long id,
                            @RequestBody AccomRequest accomRequest){
        accomService.updateAccom(id,accomRequest);
    }

    //Delete 로직

    @DeleteMapping("{id}")
    public void deleteAccom(@PathVariable Long id){
        accomService.deleteAccom(id);
    }
}
