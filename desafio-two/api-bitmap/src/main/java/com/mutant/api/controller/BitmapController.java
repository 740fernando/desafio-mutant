package com.mutant.api.controller;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.BitmapResponse;
import com.mutant.api.model.entities.response.ValuesSearchListResponse;
import com.mutant.api.service.BitmapService;
import com.mutant.api.service.ValuesSearchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bitmap/v1")
public class BitmapController {

    private final BitmapService bitmapService;
    private final ValuesSearchListService valuesSearchListService;

    @Autowired
    public BitmapController(BitmapService bitmapService, ValuesSearchListService valuesSearchListService) {
        this.bitmapService = bitmapService;
        this.valuesSearchListService = valuesSearchListService;
    }

    @GetMapping(value = "/image",produces = "application/json")
    public BitmapResponse getImageMatrix(){
        return bitmapService.getImageMatrix();
    }

    @PostMapping(value = "/search/values",consumes ="application/json", produces = "application/json")
    public ValuesSearchListResponse searchValues(@RequestBody ValuesSearchListRequest requestDTO){
        return valuesSearchListService.getOccorencies(requestDTO);
    }
}
