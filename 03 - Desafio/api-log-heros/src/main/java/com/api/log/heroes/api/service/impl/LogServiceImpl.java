package com.api.log.heroes.api.service.impl;

import com.api.log.heroes.api.model.dto.InformationLogDTO;
import com.api.log.heroes.api.model.dto.RespostDTO;
import com.api.log.heroes.api.model.dto.SaveLogDTO;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.mappers.LogDetailMapper;
import com.api.log.heroes.api.model.vo.LogDetailVO;
import com.api.log.heroes.api.model.vo.SaveLogVO;
import com.api.log.heroes.api.repository.HeroesLogRepository;
import com.api.log.heroes.api.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

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
    public InformationLogDTO getInformationLog() throws IOException {
        InformationLogDTO list = new InformationLogDTO();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("log.txt"))) {
            this.processFile(list, bufferedReader);
            return list;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public SaveLogDTO saveLog(RespostDTO respostDTO) throws IOException {
        return (respostDTO.isRespostSaveLog())
                ? this.processSaveLogSuccess()
                : this.processSaveLogError();
    }

    private void processFile(InformationLogDTO list, BufferedReader bufferedReader) throws IOException {
        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
            String[] vet = line.split(";");
            LogDetailVO testResponse = this.mapper.convertVectorForLogDetailVO(vet);
            list.getResponse().add(testResponse);
            Collections.sort(
                    list.getResponse(),
                    Comparator.comparing(LogDetailVO::getTurn)
                            .thenComparingDouble(LogDetailVO::getVelocityAverage));
        }
    }

    private SaveLogDTO processSaveLogSuccess() {
        SaveLogDTO saveLogDTO = new SaveLogDTO();
        try {
            var output = getInformationLog();
            this.repository.saveAll(output.getResponse().stream().map(LogDetail::new).collect(Collectors.toList()));
            var list = this.repository.findAll();
            saveLogDTO.setResponseSaveLog(list.stream().map(SaveLogVO::new).collect(Collectors.toList()));
            return saveLogDTO;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private SaveLogDTO processSaveLogError() {
        throw new RuntimeException();
    }
}
