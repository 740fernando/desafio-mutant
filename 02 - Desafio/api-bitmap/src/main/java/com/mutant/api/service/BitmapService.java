package com.mutant.api.service;

import com.mutant.api.model.entities.response.BitmapResponse;

@FunctionalInterface
public interface BitmapService {
    BitmapResponse getImageMatrix();
}
