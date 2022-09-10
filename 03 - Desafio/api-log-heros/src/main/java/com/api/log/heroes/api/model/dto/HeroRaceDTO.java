package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.HeroVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeroRaceDTO {
    private List<HeroVO> response = new ArrayList<>();
}
