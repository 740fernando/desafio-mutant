package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.dto.HeroDetailRaceDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.mappers.HeroDetailMapper;
import com.api.log.heroes.api.service.HeroDetailService;
import com.api.log.heroes.api.service.LogService;
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

    private final LogService service;
    private final HeroDetailMapper mapper;

    @Autowired
    public HeroDetailServiceImpl(LogService service, HeroDetailMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<HeroDetailRaceDTO> getInformationRace() throws IOException {
        final var responseLog = this.service.getInformationLog();
        List<LogDetail> listFinalist = new ArrayList<>();
        this.verifyFinalist(responseLog, listFinalist);
        this.orderList(listFinalist);
        return this.mapper.convertHeroDetailRace(listFinalist);
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
