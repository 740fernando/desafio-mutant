package com.api.log.heroes.api.controller;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.HeroLogService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/log/hero/v1")
public class LogHeroController {

    private final HeroLogService heroLogService;
    private final HeroDetailService heroDetailService;

    public LogHeroController(HeroLogService heroLogService, HeroDetailService heroDetailService) {
        this.heroLogService = heroLogService;
        this.heroDetailService = heroDetailService;
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping()
    public List<LogDetail> getInformationLog() throws IOException {
        return heroLogService.getInformationLog();
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping("/information/race")
    public List<HeroDetailRaceDTO> getInformationRace() throws IOException {
        return heroDetailService.getInformationRace();
    }
}
