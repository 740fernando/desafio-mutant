package com.api.log.heroes.api.model.mappers;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HeroDetailMapper {

    private List<HeroDetailRaceDTO> responseForController = new ArrayList<>();

    public List<HeroDetailRaceDTO> convertHeroDetailRace(List<LogDetail> listFinalista) {
        int counter = 1;
        for (LogDetail finalista : listFinalista) {
            HeroDetailRaceDTO heroDetailRaceDTO = new HeroDetailRaceDTO();
            heroDetailRaceDTO.setPosition(counter++);
            heroDetailRaceDTO.setCodeHero(finalista.getCodeHero());
            heroDetailRaceDTO.setNameHero(finalista.getNameHero());
            heroDetailRaceDTO.setTurn(finalista.getTurn());
            heroDetailRaceDTO.setTime(finalista.getTime());
            responseForController.add(heroDetailRaceDTO);
        }
        return this.responseForController;
    }
}
