package com.api.log.heroes.api.service;

import com.api.log.heroes.api.model.dto.FinalistDetailRaceDTO;
import com.api.log.heroes.api.model.dto.HeroRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;

import java.io.IOException;
import java.util.List;

public interface HeroDetailService {
    List<FinalistDetailRaceDTO> getFinalists() throws IOException;
    HeroRaceDTO getInformationEachHero() throws IOException;
}
