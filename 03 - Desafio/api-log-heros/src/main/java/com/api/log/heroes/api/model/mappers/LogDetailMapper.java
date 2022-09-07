package com.api.log.heroes.api.model.mappers;

import com.api.log.heroes.api.model.entities.LogDetail;
import org.springframework.stereotype.Component;

@Component
public class LogDetailMapper {

    private LogDetail log;

    public LogDetail convertVectorForLogDetail(String[] vet) {
        this.log = new LogDetail();
        this.log.setHour(vet[0]);
        this.log.setCodeHero(vet[1].substring(0, 3));
        this.log.setNameHero(vet[1].substring(4));
        this.log.setTurn(vet[2]);
        this.log.setTime(vet[3]);
        this.log.setVelocityAverage(vet[4]);
        return log;
    }
}
