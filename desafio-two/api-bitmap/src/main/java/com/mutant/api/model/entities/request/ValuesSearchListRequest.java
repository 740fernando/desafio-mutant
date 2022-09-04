package com.mutant.api.model.entities.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValuesSearchListRequest {

    private List<OccorrencyRequest> request = new ArrayList<>();
}
