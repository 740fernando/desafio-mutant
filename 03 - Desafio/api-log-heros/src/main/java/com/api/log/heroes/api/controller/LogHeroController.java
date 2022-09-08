package com.api.log.heroes.api.controller;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.entities.Respost;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
    public List<LogDetail> getInformationLog() throws IOException {
        return logService.getInformationLog();
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.POST})
    @PostMapping()
    public List<LogDetail> saveLog(@RequestBody Respost respost) throws IOException {
        return logService.saveLog(respost);
    }

    @CrossOrigin(
            origins = "*",
            methods = {RequestMethod.GET})
    @GetMapping("/information/race")
    public List<HeroDetailRaceDTO> getInformationRace() throws IOException {
        return heroDetailService.getInformationRace();
    }
}
