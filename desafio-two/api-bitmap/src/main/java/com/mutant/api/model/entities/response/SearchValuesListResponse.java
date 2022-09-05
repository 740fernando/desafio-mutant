package com.mutant.api.model.entities.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchValuesListResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ValuesFoundResponse> response = new ArrayList<>();
}
