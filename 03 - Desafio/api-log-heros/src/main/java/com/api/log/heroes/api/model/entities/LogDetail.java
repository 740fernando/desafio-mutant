package com.api.log.heroes.api.model.entities;

import com.api.log.heroes.api.model.vo.LogDetailVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
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

    public LogDetail(LogDetailVO output) {
        this.hour = output.getHour();
        this.codeHero = output.getCodeHero();
        this.nameHero = output.getNameHero();
        this.turn = output.getTurn();
        this.time = output.getTime();
        this.velocityAverage = output.getVelocityAverage();
    }
}
