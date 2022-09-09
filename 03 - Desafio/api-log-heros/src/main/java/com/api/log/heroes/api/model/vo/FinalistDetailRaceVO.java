package com.api.log.heroes.api.model.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalistDetailRaceVO {
    private Integer position;
    private String codeHero;
    private String nameHero;
    private String turn;
    private String time;

    public FinalistDetailRaceVO(LogDetailVO output){
        this.codeHero = output.getCodeHero();
        this.nameHero = output.getNameHero();
        this.turn = output.getTurn();
        this.time = output.getTime();
    }
}
