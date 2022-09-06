package com.mutant.api.model.entities.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValuesSearchListRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<OccorrencyRequest> request = new ArrayList<>();
}
