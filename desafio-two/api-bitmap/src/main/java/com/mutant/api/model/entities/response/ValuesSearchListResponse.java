package com.mutant.api.model.entities.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValuesSearchListResponse {

    private List<ValuesFoundResponse> response = new ArrayList<>();
}
