package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.mappers.LogDetailMapper;
import com.api.log.heroes.api.repository.HeroesLogRepository;
import com.api.log.heroes.api.service.HeroLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class HeroLogServiceImpl implements HeroLogService {
    private final HeroesLogRepository repository;
    private final LogDetailMapper mapper;

    @Autowired
    public HeroLogServiceImpl(HeroesLogRepository repository, LogDetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LogDetail> getInformationLog() throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();
        List<LogDetail> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            this.processFile(list, bufferedReader);
            this.repository.saveAll(list);
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void processFile(List<LogDetail> list, BufferedReader bufferedReader) throws IOException {
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            String[] vet = line.split(";");
            LogDetail testResponse = this.mapper.convertVectorForLogDetail(vet);
            list.add(testResponse);
            Collections.sort(list, Comparator.comparing(LogDetail::getTurn));
        }
    }
}
