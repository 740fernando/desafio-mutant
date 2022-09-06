package com.mutant.api.service;

import com.mutant.api.model.entities.request.ValuesSearchListRequest;
import com.mutant.api.model.entities.response.SearchValuesListResponse;

@FunctionalInterface
public interface SearchValuesListService {

    SearchValuesListResponse search(ValuesSearchListRequest request);
}
