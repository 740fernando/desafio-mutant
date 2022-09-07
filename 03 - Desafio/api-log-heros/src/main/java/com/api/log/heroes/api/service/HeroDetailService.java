package com.api.log.heroes.api.service;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;

import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface HeroDetailService {
    List<HeroDetailRaceDTO> getInformationRace() throws IOException;
}
