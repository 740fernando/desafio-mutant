package com.api.log.heroes.api.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroDetailRaceDTO {
    private Integer position;
    private String codeHero;
    private String nameHero;
    private String turn;
    private String time;
}
