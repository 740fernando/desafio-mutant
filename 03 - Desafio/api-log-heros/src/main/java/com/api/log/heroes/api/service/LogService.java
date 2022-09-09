package com.api.log.heroes.api.service;

import com.api.log.heroes.api.model.dto.InformationLogDTO;
import com.api.log.heroes.api.model.dto.RespostDTO;
import com.api.log.heroes.api.model.dto.SaveLogDTO;

import java.io.IOException;

public interface LogService {
    InformationLogDTO getInformationLog() throws IOException;
    SaveLogDTO saveLog(RespostDTO respostDTO) throws IOException;
}
