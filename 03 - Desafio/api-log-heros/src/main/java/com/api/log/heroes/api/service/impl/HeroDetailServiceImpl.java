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
        final var definitve = new HeroRaceDTO();
        final var superman = new HeroRaceDTO();
        final var sonic = new HeroRaceDTO();
        final var mercurio = new HeroRaceDTO();
        final var flash = new HeroRaceDTO();
        final var papalegua = new HeroRaceDTO();
        final var gatoJato = new HeroRaceDTO();
        final var responseLog = this.service.getInformationLog();
        Collections.sort(responseLog.getResponse(), Comparator.comparing(LogDetailVO::getNameHero));

        this.buildInformationHero(superman, responseLog, ConstantsUtils.SUPERMAN);
        this.buildInformationHero(sonic, responseLog, ConstantsUtils.SONIC);
        this.buildInformationHero(mercurio, responseLog, ConstantsUtils.MERCURIO);
        this.buildInformationHero(flash, responseLog, ConstantsUtils.FLASH);
        this.buildInformationHero(responseLog, papalegua);
        this.buildInformationHero(gatoJato, responseLog, ConstantsUtils.GATOAJATO);

        this.buildTheBestTurn(superman, definitve);
        this.buildTheBestTurn(sonic, definitve);
        this.buildTheBestTurn(mercurio, definitve);
        this.buildTheBestTurn(flash, definitve);
        this.buildTheBestTurn(papalegua, definitve);
        this.buildTheBestTurn(gatoJato, definitve);
        this.proccessVelocity(definitve, 0, superman);
        this.proccessVelocity(definitve, 1, sonic);
        this.proccessVelocity(definitve, 2, mercurio);
        this.proccessVelocity(definitve, 3, flash);
        this.proccessVelocity(definitve, 4, papalegua);
        this.proccessVelocity(definitve, 5, gatoJato);
        return definitve;
    }

    private static void buildInformationHero(HeroRaceDTO list, InformationLogDTO responseLog, String constante) {
        list.setResponse(
                responseLog.getResponse()
                        .stream()
                        .sorted(Comparator.comparing(LogDetailVO::getVelocityAverage).reversed())
                        .filter(element -> constante.equals(element.getNameHero()))
                        .map(HeroVO::new)
                        .collect(Collectors.toList()));
    }

    private static void buildInformationHero(InformationLogDTO responseLog, HeroRaceDTO papalegua) {
        papalegua.setResponse(
                responseLog.getResponse()
                        .stream()
                        .sorted(Comparator.comparing(LogDetailVO::getVelocityAverage).reversed())
                        .filter(element -> ConstantsUtils.PAPALEGU.equals(element.getNameHero()) || ConstantsUtils.PAPALEGUA.equals(element.getNameHero()))
                        .map(HeroVO::new)
                        .collect(Collectors.toList()));
    }

    private static void buildTheBestTurn(HeroRaceDTO list, HeroRaceDTO definitve) {
        definitve.getResponse().add(list.getResponse().get(0));
    }

    private static void proccessVelocity(HeroRaceDTO definitve, int index, HeroRaceDTO list) {
        definitve.getResponse()
                .get(index)
                .setVelocityAverageInAllTurns(
                        list.getResponse()
                                .stream()
                                .mapToDouble(HeroVO::getVelocityAverageInAllTurns).average()
                                .getAsDouble());
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
