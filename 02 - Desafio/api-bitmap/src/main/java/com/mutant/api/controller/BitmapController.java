package com.mutant.api.controller;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.BitmapResponse;
import com.mutant.api.model.entities.response.SearchValuesListResponse;
import com.mutant.api.service.BitmapService;
import com.mutant.api.service.SearchValuesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bitmap/v1")
public class BitmapController {

    private final BitmapService bitmapService;
    private final SearchValuesListService searchValuesListService;

    @Autowired
    public BitmapController(BitmapService bitmapService, SearchValuesListService searchValuesListService) {
        this.bitmapService = bitmapService;
        this.searchValuesListService = searchValuesListService;
    }

    @GetMapping(value = "/image",produces = "application/json")
    public BitmapResponse getImageMatrix(){
        return bitmapService.getImageMatrix();
    }

    @PostMapping(value = "/search/values",consumes ="application/json", produces = "application/json")
    public SearchValuesListResponse searchValues(@RequestBody ValuesSearchListRequest requestDTO){
        return searchValuesListService.search(requestDTO);
    }
}
