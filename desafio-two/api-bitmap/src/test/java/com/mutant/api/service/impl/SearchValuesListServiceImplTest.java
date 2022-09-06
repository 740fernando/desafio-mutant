package com.mutant.api.service.impl;

import com.mutant.api.model.entities.request.OccorrencyRequest;
import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.BitmapResponse;
import com.mutant.api.service.BitmapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchValuesListServiceImplTest {
    @InjectMocks private SearchValuesListServiceImpl service;
    @Mock private BitmapService bitmapService;
    @Mock private ValuesSearchListRequest request;
    private BitmapResponse response;

    @Before
    public void setup() {
        response = new BitmapResponse();
        int[][] imageBitmap = new int[2][5];
        request = setRequestDTO();
        imageBitmap = processImage(imageBitmap);
        response.setImageBitmap(imageBitmap);
    }

    @Test
    public void searchSuccess(){
        when(bitmapService.getImageMatrix()).thenReturn(response);
        assertNotNull(service.search(request));
    }

    private int[][] processImage(int[][] imageBitmap) {
        for (int x = 0; x < imageBitmap.length; x++) {
            for (int y = 0; y < imageBitmap[x].length; y++) {
             imageBitmap[x][y]=1;
            }
        }
        return imageBitmap;
    }

    private ValuesSearchListRequest setRequestDTO() {
        var request = new ValuesSearchListRequest();
        var valueObject =  new OccorrencyRequest();
        valueObject.setOccorrency(1);
        request.setRequest(Arrays.asList(valueObject));
        return  request;
    }
}