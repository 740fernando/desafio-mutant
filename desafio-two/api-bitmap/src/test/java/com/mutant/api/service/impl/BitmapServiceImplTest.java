package com.mutant.api.service.impl;

import com.mutant.api.model.entities.response.BitmapResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BitmapServiceImplTest {
    @Mock private BitmapResponse bitmapResponse;
    @InjectMocks private BitmapServiceImpl service;

    @Test
    public void getImageMatrixSuccess(){
        assertNotNull(service.getImageMatrix());
    }
}