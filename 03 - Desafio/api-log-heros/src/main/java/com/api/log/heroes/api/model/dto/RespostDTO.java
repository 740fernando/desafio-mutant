package com.api.log.heroes.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RespostDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("respost")
    private boolean respostSaveLog;
}
