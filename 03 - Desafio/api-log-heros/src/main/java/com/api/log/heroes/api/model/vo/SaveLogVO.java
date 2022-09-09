package com.api.log.heroes.api.model.vo;

import com.api.log.heroes.api.model.entities.LogDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveLogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nameHero;

    public SaveLogVO(LogDetail output){
        this.id = output.getId();
        this.nameHero = output.getNameHero();
    }
}
