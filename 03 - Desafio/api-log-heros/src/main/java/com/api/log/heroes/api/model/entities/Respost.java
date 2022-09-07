package com.api.log.heroes.api.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Respost {

    @JsonProperty("respost")
    private boolean respostSaveLog;
}
