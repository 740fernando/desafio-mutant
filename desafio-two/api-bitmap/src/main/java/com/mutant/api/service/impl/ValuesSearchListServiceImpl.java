package com.mutant.api.service.impl;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.ValuesFoundResponse;
import com.mutant.api.model.entities.response.ValuesSearchListResponse;
import com.mutant.api.service.BitmapService;
import com.mutant.api.service.ValuesSearchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ValuesSearchListServiceImpl implements ValuesSearchListService {

    private final BitmapService bitmapService;

    @Autowired
    public ValuesSearchListServiceImpl(BitmapService bitmapService) {
        this.bitmapService = bitmapService;
    }

    @Override
    public ValuesSearchListResponse getOccorencies(ValuesSearchListRequest request) {
        int[][] matrix = bitmapService.getImageMatrix().getImageBitmap();
        var responseValuesFindedList = new ValuesSearchListResponse();
        List<ValuesFoundResponse> valuesEncontrados = new ArrayList<>();
        for (int i = 0; i < request.getRequest().size(); i++){
            int counter = 0 ;
            var valuesSearchListResponseVO = new ValuesFoundResponse();
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[x].length; y++) {
                    if (request.getRequest().get(i).getOccorrency() == matrix[x][y]) {
                        counter++;
                        valuesSearchListResponseVO.setQuantitiesIncident(String.valueOf(counter));
                        if(Objects.isNull(valuesSearchListResponseVO.getValue())){
                            valuesSearchListResponseVO.setValue(String.valueOf(request.getRequest().get(i).getOccorrency()));
                        }
                    }else{
                        if(Objects.isNull(valuesSearchListResponseVO.getQuantitiesIncident())){
                            valuesSearchListResponseVO.setQuantitiesIncident(String.valueOf(counter));
                            valuesSearchListResponseVO.setValue(String.valueOf(request.getRequest().get(i).getOccorrency()));
                        }
                    }
                }
            }
            valuesEncontrados.add(valuesSearchListResponseVO);
        }
        responseValuesFindedList.setResponse(valuesEncontrados);
        return responseValuesFindedList;
    }
}
