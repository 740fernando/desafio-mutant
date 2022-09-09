package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.SaveLogVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaveLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("content")
    private List<SaveLogVO> responseSaveLog= new ArrayList<>();

}
