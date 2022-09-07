package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.mappers.HeroDetailMapper;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.HeroLogService;
import com.api.log.heroes.api.utils.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HeroDetailServiceImpl implements HeroDetailService {
    private final HeroLogService service;
    private final HeroDetailMapper mapper;

    @Autowired
    public HeroDetailServiceImpl(HeroLogService service, HeroDetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<HeroDetailRaceDTO> getInformationRace() throws IOException {
        final var responseLog = service.getInformationLog();
        List<HeroDetailRaceDTO> responseForController = new ArrayList<>();
        List<LogDetail> listFinalista = new ArrayList<>();
        verifyFinalist(responseLog, listFinalista);
        orderList(listFinalista);
        return mapper.convertHeroDetailRace(listFinalista);
    }

    private static void orderList(List<LogDetail> listFinalista) {
        Collections.sort(listFinalista, Comparator.comparing(LogDetail::getTime));
    }

    private static void verifyFinalist(List<LogDetail> responseLog, List<LogDetail> listFinalista) {
        for (LogDetail output : responseLog) {
            if (ConstantsUtils.FOUR.equals(output.getTurn())) {
                listFinalista.add(output);
            }
        }
    }
}
