package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.dto.FinalistDetailRaceDTO;
import com.api.log.heroes.api.model.dto.HeroRaceDTO;
import com.api.log.heroes.api.model.dto.InformationLogDTO;
import com.api.log.heroes.api.model.vo.FinalistDetailRaceVO;
import com.api.log.heroes.api.model.vo.HeroVO;
import com.api.log.heroes.api.model.vo.LogDetailVO;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.LogService;
import com.api.log.heroes.api.utils.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class HeroDetailServiceImpl implements HeroDetailService {

    private final LogService service;

    @Autowired
    public HeroDetailServiceImpl(LogService service) {
        this.service = service;
    }

    @Override
    public FinalistDetailRaceDTO getFinalists() throws IOException {
        final var responseLog = this.service.getInformationLog();
        FinalistDetailRaceDTO listFinalist = new FinalistDetailRaceDTO();
        this.verifyFinalist(responseLog, listFinalist);
        this.orderList(listFinalist);
        return listFinalist;
    }

    @Override
    public HeroRaceDTO getInformationEachHero() throws IOException {
        List<Double> averageAllHero = new ArrayList<>();
        HeroRaceDTO heroInformation = new HeroRaceDTO();
        final var responseLog = this.service.getInformationLog();
        Collections.sort(responseLog.getResponse(), Comparator.comparing(LogDetailVO::getNameHero));
        heroInformation.setResponse(responseLog.getResponse().stream().map(HeroVO::new).collect(Collectors.toList()));
        Collections.sort(heroInformation.getResponse(), Comparator.comparing(HeroVO::getVelocityAverageInAllTurns).reversed());
        HeroRaceDTO definitve = new HeroRaceDTO();

        definitve.setResponse(
                heroInformation.getResponse()
                        .stream()
                        .filter(element -> ConstantsUtils.SUPERMAN.equals(element.getNameHero()))
                        .limit(1)
                        .collect(Collectors.toList()));

        return definitve;
    }

    private static Double processSuperman(List<LogDetailVO> superman, Double averageVelocityAllTurnsSuperman, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.SUPERMAN)) {
            superman.add(output);
            averageVelocityAllTurnsSuperman += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsSuperman;
    }

    private static Double processSonic(List<LogDetailVO> sonic, Double averageVelocityAllTurnsSonic, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.SONIC)) {
            sonic.add(output);
            averageVelocityAllTurnsSonic += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsSonic;
    }

    private static Double processPapalegua(List<LogDetailVO> papalegua, Double averageVelocityAllTurnsPapalegua, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.PAPALEGUA) || output.getNameHero().equals(ConstantsUtils.PAPALEGU)) {
            papalegua.add(output);
            averageVelocityAllTurnsPapalegua += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsPapalegua;
    }

    private static Double processMercurio(List<LogDetailVO> mercurio, Double averageVelocityAllTurnsMercurio, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.MERCURIO)) {
            mercurio.add(output);
            averageVelocityAllTurnsMercurio += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsMercurio;
    }

    private static Double processGatoJato(List<LogDetailVO> gatoJato, Double averageVelocityAllTurnsGatoJato, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.GATOAJATO)) {
            gatoJato.add(output);
            averageVelocityAllTurnsGatoJato += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsGatoJato;
    }

    private static Double processFlash(List<LogDetailVO> flash, Double averageVelocityAllTurnsFlash, LogDetailVO output) {
        if (output.getNameHero().equals(ConstantsUtils.FLASH)) {
            flash.add(output);
            averageVelocityAllTurnsFlash += output.getVelocityAverage();
        }
        return averageVelocityAllTurnsFlash;
    }

    private HeroRaceDTO processResponse(InformationLogDTO informationLogDTO, List<LogDetailVO> flash, Double averageVelocityAllTurnsFlash, List<LogDetailVO> gatoJato, Double averageVelocityAllTurnsGatoJato, List<LogDetailVO> mercurio, Double averageVelocityAllTurnsMercurio, List<LogDetailVO> papalegua, Double averageVelocityAllTurnsPapalegua, List<LogDetailVO> sonic, Double averageVelocityAllTurnsSonic, List<LogDetailVO> superman, Double averageVelocityAllTurnsSuperman) {
        flash.get(0).setVelocityAverage(averageVelocityAllTurnsFlash);
        gatoJato.get(0).setVelocityAverage(averageVelocityAllTurnsGatoJato);
        mercurio.get(0).setVelocityAverage(averageVelocityAllTurnsMercurio);
        papalegua.get(0).setVelocityAverage(averageVelocityAllTurnsPapalegua);
        sonic.get(0).setVelocityAverage(averageVelocityAllTurnsSonic);
        superman.get(0).setVelocityAverage(averageVelocityAllTurnsSuperman);
        informationLogDTO.getResponse().add(new LogDetailVO(flash.get(0)));
        informationLogDTO.getResponse().add(new LogDetailVO(gatoJato.get(0)));
        informationLogDTO.getResponse().add(new LogDetailVO(mercurio.get(0)));
        informationLogDTO.getResponse().add(new LogDetailVO(papalegua.get(0)));
        informationLogDTO.getResponse().add(new LogDetailVO(sonic.get(0)));
        informationLogDTO.getResponse().add(new LogDetailVO(superman.get(0)));

        Collections.sort(informationLogDTO.getResponse(), Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        HeroRaceDTO responseDefinitive = new HeroRaceDTO();
        responseDefinitive.setResponse(informationLogDTO.getResponse().stream().map(HeroVO::new).collect(Collectors.toList()));
        return responseDefinitive;
    }

    private void orderLists(List<LogDetailVO> flash, List<LogDetailVO> gatoJato, List<LogDetailVO> mercurio, List<LogDetailVO> papalegua, List<LogDetailVO> sonic, List<LogDetailVO> superman) {
        Collections.sort(flash, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        Collections.sort(gatoJato, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        Collections.sort(mercurio, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        Collections.sort(papalegua, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        Collections.sort(sonic, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
        Collections.sort(superman, Comparator.comparing(LogDetailVO::getVelocityAverage).reversed());
    }

    private void verifyFinalist(InformationLogDTO responseLog, FinalistDetailRaceDTO listFinalist) {
        listFinalist.setResponse(
                responseLog.getResponse()
                        .stream()
                        .filter(element -> ConstantsUtils.FOUR.equals(element.getTurn()))
                        .map(FinalistDetailRaceVO::new)
                        .collect(Collectors.toList()));
    }

    private void orderList(FinalistDetailRaceDTO listFinalist) {
        Collections.sort(listFinalist.getResponse(), Comparator.comparing(FinalistDetailRaceVO::getTime));
        AtomicInteger counter = new AtomicInteger();
        listFinalist.getResponse().forEach(element -> {
            counter.getAndIncrement();
            element.setPosition(counter.get());
        });
    }
}
