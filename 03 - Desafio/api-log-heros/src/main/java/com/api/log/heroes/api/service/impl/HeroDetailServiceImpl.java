package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.dto.FinalistDetailRaceDTO;
import com.api.log.heroes.api.model.dto.HeroRaceDTO;
import com.api.log.heroes.api.model.dto.TheBestTurnDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.mappers.HeroDetailMapper;
import com.api.log.heroes.api.model.vo.HeroVO;
import com.api.log.heroes.api.model.vo.LogDetailVO;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.LogService;
import com.api.log.heroes.api.utils.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HeroDetailServiceImpl implements HeroDetailService {

    private final LogService service;
    private final HeroDetailMapper mapper;

    @Autowired
    public HeroDetailServiceImpl(LogService service, HeroDetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<FinalistDetailRaceDTO> getFinalists() throws IOException {
        final var responseLog = this.service.getInformationLog();
        List<LogDetail> listFinalist = new ArrayList<>();
        this.verifyFinalist(responseLog, listFinalist);
        this.orderList(listFinalist);
        return this.mapper.convertHeroDetailRace(listFinalist);
    }

    @Override
    public HeroRaceDTO getInformationEachHero() throws IOException {
        final var theBestTurnDTO = new TheBestTurnDTO();
        List<LogDetail> flash = new ArrayList<>();
        Double averageVelocityAllTurnsFlash = 0.0;
        List<LogDetail> gatoJato = new ArrayList<>();
        Double averageVelocityAllTurnsGatoJato = 0.0;
        List<LogDetail> mercurio = new ArrayList<>();
        Double averageVelocityAllTurnsMercurio = 0.0;
        List<LogDetail> papalegua = new ArrayList<>();
        Double averageVelocityAllTurnsPapalegua = 0.0;
        List<LogDetail> sonic = new ArrayList<>();
        Double averageVelocityAllTurnsSonic = 0.0;
        List<LogDetail> superman = new ArrayList<>();
        Double averageVelocityAllTurnsSuperman = 0.0;
        final var responseLog = this.service.getInformationLog();
        Collections.sort(responseLog, Comparator.comparing(LogDetail::getNameHero));
        for (LogDetail output : responseLog) {
            averageVelocityAllTurnsFlash = processFlash(flash, averageVelocityAllTurnsFlash, output);
            averageVelocityAllTurnsGatoJato = processGatoJato(gatoJato, averageVelocityAllTurnsGatoJato, output);
            averageVelocityAllTurnsMercurio = processMercurio(mercurio, averageVelocityAllTurnsMercurio, output);
            averageVelocityAllTurnsPapalegua = processPapalegua(papalegua, averageVelocityAllTurnsPapalegua, output);
            averageVelocityAllTurnsSonic = processSonic(sonic, averageVelocityAllTurnsSonic, output);
            averageVelocityAllTurnsSuperman = processSuperman(superman, averageVelocityAllTurnsSuperman, output);
        }

        averageVelocityAllTurnsFlash /= flash.size();
        averageVelocityAllTurnsGatoJato /= gatoJato.size();
        averageVelocityAllTurnsMercurio /= mercurio.size();
        averageVelocityAllTurnsPapalegua /= papalegua.size();
        averageVelocityAllTurnsSonic /= sonic.size();
        averageVelocityAllTurnsSuperman /= superman.size();
        orderLists(flash, gatoJato, mercurio, papalegua, sonic, superman);
        HeroRaceDTO responseDefinitive = processResponse(theBestTurnDTO, flash, averageVelocityAllTurnsFlash, gatoJato, averageVelocityAllTurnsGatoJato, mercurio, averageVelocityAllTurnsMercurio, papalegua, averageVelocityAllTurnsPapalegua, sonic, averageVelocityAllTurnsSonic, superman, averageVelocityAllTurnsSuperman);
        return responseDefinitive;
    }

    private static Double processSuperman(List<LogDetail> superman, Double averageVelocityAllTurnsSuperman, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.SUPERMAN)) {
            superman.add(output);
            averageVelocityAllTurnsSuperman += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsSuperman;
    }

    private static Double processSonic(List<LogDetail> sonic, Double averageVelocityAllTurnsSonic, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.SONIC)) {
            sonic.add(output);
            averageVelocityAllTurnsSonic += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsSonic;
    }

    private static Double processPapalegua(List<LogDetail> papalegua, Double averageVelocityAllTurnsPapalegua, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.PAPALEGUA) || output.getNameHero().equals(ConstantsUtils.PAPALEGU)) {
            papalegua.add(output);
            averageVelocityAllTurnsPapalegua += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsPapalegua;
    }

    private static Double processMercurio(List<LogDetail> mercurio, Double averageVelocityAllTurnsMercurio, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.MERCURIO)) {
            mercurio.add(output);
            averageVelocityAllTurnsMercurio += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsMercurio;
    }

    private static Double processGatoJato(List<LogDetail> gatoJato, Double averageVelocityAllTurnsGatoJato, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.GATOAJATO)) {
            gatoJato.add(output);
            averageVelocityAllTurnsGatoJato += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsGatoJato;
    }

    private static Double processFlash(List<LogDetail> flash, Double averageVelocityAllTurnsFlash, LogDetail output) {
        if (output.getNameHero().equals(ConstantsUtils.Flash)) {
            flash.add(output);
            averageVelocityAllTurnsFlash += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsFlash;
    }

    private HeroRaceDTO processResponse(TheBestTurnDTO theBestTurnDTO, List<LogDetail> flash, Double averageVelocityAllTurnsFlash, List<LogDetail> gatoJato, Double averageVelocityAllTurnsGatoJato, List<LogDetail> mercurio, Double averageVelocityAllTurnsMercurio, List<LogDetail> papalegua, Double averageVelocityAllTurnsPapalegua, List<LogDetail> sonic, Double averageVelocityAllTurnsSonic, List<LogDetail> superman, Double averageVelocityAllTurnsSuperman) {
        flash.get(0).setVelocityAverage(averageVelocityAllTurnsFlash);
        gatoJato.get(0).setVelocityAverage(averageVelocityAllTurnsGatoJato);
        mercurio.get(0).setVelocityAverage(averageVelocityAllTurnsMercurio);
        papalegua.get(0).setVelocityAverage(averageVelocityAllTurnsPapalegua);
        sonic.get(0).setVelocityAverage(averageVelocityAllTurnsSonic);
        superman.get(0).setVelocityAverage(averageVelocityAllTurnsSuperman);
        theBestTurnDTO.getResponse().add(new LogDetailVO(flash.get(0)));
        theBestTurnDTO.getResponse().add(new LogDetailVO(gatoJato.get(0)));
        theBestTurnDTO.getResponse().add(new LogDetailVO(mercurio.get(0)));
        theBestTurnDTO.getResponse().add(new LogDetailVO(papalegua.get(0)));
        theBestTurnDTO.getResponse().add(new LogDetailVO(sonic.get(0)));
        theBestTurnDTO.getResponse().add(new LogDetailVO(superman.get(0)));

        Collections.sort(theBestTurnDTO.getResponse(), Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        HeroRaceDTO responseDefinitive = new HeroRaceDTO();
        responseDefinitive.setResponse(theBestTurnDTO.getResponse().stream().map(HeroVO::new).collect(Collectors.toList()));
        return responseDefinitive;
    }

    private void orderLists(List<LogDetail> flash, List<LogDetail> gatoJato, List<LogDetail> mercurio, List<LogDetail> papalegua, List<LogDetail> sonic, List<LogDetail> superman) {
        Collections.sort(flash, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
        Collections.sort(gatoJato, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
        Collections.sort(mercurio, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
        Collections.sort(papalegua, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
        Collections.sort(sonic, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
        Collections.sort(superman, Comparator.comparing(LogDetail::getVelocityAverage).reversed());
    }

    private void verifyFinalist(List<LogDetail> responseLog, List<LogDetail> listFinalist) {
        responseLog.forEach(element -> {
            if (ConstantsUtils.FOUR.equals(element.getTurn())) {
                listFinalist.add(element);
            }
        });
    }

    private void orderList(List<LogDetail> listFinalist) {
        Collections.sort(listFinalist, Comparator.comparing(LogDetail::getTime));
    }
}
