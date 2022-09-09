package com.api.log.heroes.api.controller;

import com.api.log.heroes.api.model.dto.*;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/log/hero/v1")
public class LogHeroController {

    private final LogService logService;
    private final HeroDetailService heroDetailService;

    @Autowired
    public LogHeroController(LogService logService, HeroDetailService heroDetailService) {
        this.logService = logService;
        this.heroDetailService = heroDetailService;
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping()
    public InformationLogDTO getInformationLog() throws IOException {
        return logService.getInformationLog();
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.POST})
    @PostMapping()
    public SaveLogDTO saveLog(@RequestBody RespostDTO respostDTO) throws IOException {
        return logService.saveLog(respostDTO);
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping("/information/race")
    public FinalistDetailRaceDTO getFinalists() throws IOException {
        return heroDetailService.getFinalists();
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping("/information/race/heros")
    public HeroRaceDTO getTheBestTurnEachHeroAndVelocityAverageAllTurns() throws IOException {
        return heroDetailService.getInformationEachHero();
    }
}
