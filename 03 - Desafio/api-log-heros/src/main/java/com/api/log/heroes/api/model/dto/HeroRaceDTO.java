package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.HeroVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HeroRaceDTO {
    private List<HeroVO> response;
}
