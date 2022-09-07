package com.api.log.heroes.api.service;
import com.api.log.heroes.api.model.entities.LogDetail;
import com.api.log.heroes.api.model.entities.Respost;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LogService {
    List<LogDetail> getInformationLog() throws FileNotFoundException, IOException;
    List<LogDetail> saveLog(Respost respost) throws FileNotFoundException, IOException;
}
