package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.LogDetailVO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TheBestTurnDTO {
    private  List<LogDetailVO> response = new ArrayList<>();
}
