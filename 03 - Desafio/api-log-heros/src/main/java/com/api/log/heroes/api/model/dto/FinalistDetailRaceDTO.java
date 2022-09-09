package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.FinalistDetailRaceVO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FinalistDetailRaceDTO {
    private List<FinalistDetailRaceVO> response = new ArrayList<>();
}
