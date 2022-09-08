package com.api.log.heroes.api.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "log_detail")
public class LogDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hour;
    private String codeHero;
    private String nameHero;
    private String turn;
    private String time;
    private Double velocityAverage;
}
