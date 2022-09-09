package com.api.log.heroes.api.model.vo;


import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
public class HeroVO {
    private String bestTurn;
    private String codeHero;
    private String nameHero;
    private Double velocityAverageInAllTurns;

    public HeroVO(LogDetailVO output){
        this.bestTurn = output.getTurn();
        this.codeHero = output.getCodeHero();
        this.nameHero = output.getNameHero();
        this.velocityAverageInAllTurns = Double.valueOf(new DecimalFormat("#.###").format(output.getVelocityAverage()).replace(",","."));
    }
}
