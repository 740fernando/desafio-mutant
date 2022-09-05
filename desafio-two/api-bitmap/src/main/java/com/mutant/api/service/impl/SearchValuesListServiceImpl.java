package com.mutant.api.service.impl;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.ValuesFoundResponse;
import com.mutant.api.model.entities.response.SearchValuesListResponse;
import com.mutant.api.service.BitmapService;
import com.mutant.api.service.SearchValuesListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SearchValuesListServiceImpl implements SearchValuesListService {

    private final BitmapService bitmapService;

    @Autowired
    public SearchValuesListServiceImpl(BitmapService bitmapService) {
        this.bitmapService = bitmapService;
    }

    @Override
    public SearchValuesListResponse search(ValuesSearchListRequest request) {
        final var matrix = bitmapService.getImageMatrix().getImageBitmap();
        final var responseValuesFindedList = new SearchValuesListResponse();
        List<ValuesFoundResponse> valuesFound = new ArrayList<>();
        this.processOccurrences(request, matrix, valuesFound);
        responseValuesFindedList.setResponse(valuesFound);
        return responseValuesFindedList;
    }

    private void processOccurrences(
            ValuesSearchListRequest request,
            int[][] matrix,
            List<ValuesFoundResponse> valuesFound) {
        for (int i = 0; i < request.getRequest().size(); i++) {
            int counter = 0;
            var VO = new ValuesFoundResponse();
            for (int x = 0; x < matrix.length; x++) {
                for (int y = 0; y < matrix[x].length; y++) {
                    if (request.getRequest().get(i).getOccorrency() == matrix[x][y]) {
                        counter++;
                        VO.setIncidentQuantity(String.valueOf(counter));
                        if (Objects.isNull(VO.getValue())) {
                            VO.setValue(String.valueOf(request.getRequest().get(i).getOccorrency()));
                        }
                    } else {
                        if (Objects.isNull(VO.getIncidentQuantity())) {
                            VO.setIncidentQuantity(String.valueOf(counter));
                            VO.setValue(String.valueOf(request.getRequest().get(i).getOccorrency()));
                        }
                    }
                }
            }
            valuesFound.add(VO);
        }
    }
}
