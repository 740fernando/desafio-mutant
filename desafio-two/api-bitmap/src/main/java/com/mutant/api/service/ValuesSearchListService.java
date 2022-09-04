package com.mutant.api.service;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.ValuesSearchListResponse;

@FunctionalInterface
public interface ValuesSearchListService {

    ValuesSearchListResponse getOccorencies(ValuesSearchListRequest request);
}
