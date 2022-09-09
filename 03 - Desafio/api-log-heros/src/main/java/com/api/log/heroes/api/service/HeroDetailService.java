package com.api.log.heroes.api.service;

import com.api.log.heroes.api.model.dto.FinalistDetailRaceDTO;
import com.api.log.heroes.api.model.dto.HeroRaceDTO;

import java.io.IOException;

public interface HeroDetailService {
    FinalistDetailRaceDTO getFinalists() throws IOException;
    HeroRaceDTO getInformationEachHero() throws IOException;
}
