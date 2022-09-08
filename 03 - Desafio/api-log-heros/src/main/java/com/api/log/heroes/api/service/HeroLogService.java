package com.api.log.heroes.api.service;
import com.api.log.heroes.api.model.entities.LogDetail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@FunctionalInterface
public interface HeroLogService {

    List<LogDetail> getInformationLog() throws FileNotFoundException, IOException;

}
