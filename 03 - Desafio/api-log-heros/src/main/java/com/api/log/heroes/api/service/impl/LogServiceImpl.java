package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.entities.Respost;
import com.api.log.heroes.api.model.mappers.LogDetailMapper;
import com.api.log.heroes.api.repository.HeroesLogRepository;
import com.api.log.heroes.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private final HeroesLogRepository repository;
    private final LogDetailMapper mapper;

    @Autowired
    public LogServiceImpl(HeroesLogRepository repository, LogDetailMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LogDetail> getInformationLog() throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();
        List<LogDetail> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            this.processFile(list, bufferedReader);
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<LogDetail> saveLog(Respost respost) throws FileNotFoundException, IOException {
        return (respost.isRespostSaveLog())
                ? this.processSaveLogSuccess()
                : this.processSaveLogError();
    }

    private void processFile(List<LogDetail> list, BufferedReader bufferedReader) throws IOException {
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            String[] vet = line.split(";");
            LogDetail testResponse = this.mapper.convertVectorForLogDetail(vet);
            list.add(testResponse);
            Collections.sort(list, Comparator.comparing(LogDetail::getTurn));
        }
    }

    private List<LogDetail> processSaveLogSuccess() {
        List<LogDetail> listInDirectory = new ArrayList<>();
        try {
            listInDirectory = this.getInformationLog();
            this.repository.saveAll(listInDirectory);
            return listInDirectory;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<LogDetail> processSaveLogError() {
        throw new RuntimeException();
    }
}
