package com.api.log.heroes.api.model.vo;

import com.api.log.heroes.api.model.entities.LogDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class LogDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String hour;
    private String codeHero;
    private String nameHero;
    private String turn;
    private String time;
    private Double velocityAverage;

    public LogDetailVO(LogDetailVO output){
        this.hour = output.getHour();
        this.codeHero = output.getCodeHero();
        this.nameHero = output.getNameHero();
        this.turn = output.getTurn();
        this.time = output.getTime();
        this.velocityAverage = output.getVelocityAverage();
    }
    public LogDetailVO(LogDetail output){
        this.hour = output.getHour();
        this.codeHero = output.getCodeHero();
        this.nameHero = output.getNameHero();
        this.turn = output.getTurn();
        this.time = output.getTime();
        this.velocityAverage = output.getVelocityAverage();
    }
}
