package com.api.log.heroes.api.model.mappers;

import com.api.log.heroes.api.model.dto.FinalistDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeroDetailMapper {

    private List<FinalistDetailRaceDTO> responseForController = new ArrayList<>();

    public List<FinalistDetailRaceDTO> convertHeroDetailRace(List<LogDetail> listFinalista) {
        int counter = 1;
        for (LogDetail finalista : listFinalista) {
            FinalistDetailRaceDTO finalistDetailRaceDTO = new FinalistDetailRaceDTO();
            finalistDetailRaceDTO.setPosition(counter++);
            finalistDetailRaceDTO.setCodeHero(finalista.getCodeHero());
            finalistDetailRaceDTO.setNameHero(finalista.getNameHero());
            finalistDetailRaceDTO.setTurn(finalista.getTurn());
            finalistDetailRaceDTO.setTime(finalista.getTime());
            responseForController.add(finalistDetailRaceDTO);
        }
        return this.responseForController;
    }
}
