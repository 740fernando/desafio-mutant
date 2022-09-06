package com.mutant.api.controller;

import com.mutant.api.model.entities.request.OccorrencyRequest;
import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.service.BitmapService;
import com.mutant.api.service.SearchValuesListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class BitmapControllerTest {

    @Mock private BitmapService bitmapService;
    @Mock private SearchValuesListService searchValuesListService;
    @InjectMocks private BitmapController controller;

    @Test
    public void getImageMatrixSuccess() {
        final var result = controller.getImageMatrix();
    }

    @Test
    public void searchValuesSuccess() {
        OccorrencyRequest  occorrency = new OccorrencyRequest();
        occorrency.setOccorrency(1);
        ValuesSearchListRequest request = new ValuesSearchListRequest();
        request.setRequest(Arrays.asList(occorrency));
        final var result = controller.searchValues(request);
    }
}