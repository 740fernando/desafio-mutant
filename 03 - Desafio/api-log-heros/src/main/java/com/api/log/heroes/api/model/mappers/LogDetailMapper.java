package com.api.log.heroes.api.model.mappers;

import com.api.log.heroes.api.model.vo.LogDetailVO;
import org.springframework.stereotype.Component;

@Component
public class LogDetailMapper {

    public LogDetailVO convertVectorForLogDetailVO(String[] vet) {
        LogDetailVO log = new LogDetailVO();
        log.setHour(vet[0]);
        log.setCodeHero(vet[1].substring(0, 3));
        log.setNameHero(vet[1].substring(4));
        log.setTurn(vet[2]);
        log.setTime(vet[3]);
        log.setVelocityAverage(Double.valueOf(vet[4].replace(",",".")));
        return log;
    }
}
