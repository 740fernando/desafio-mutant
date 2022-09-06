package com.mutant.api.model.entities.request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OccorrencyRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer occorrency;
}
