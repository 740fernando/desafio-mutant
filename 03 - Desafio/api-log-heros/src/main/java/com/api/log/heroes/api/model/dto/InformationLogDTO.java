package com.api.log.heroes.api.model.dto;

import com.api.log.heroes.api.model.vo.LogDetailVO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InformationLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private  List<LogDetailVO> response = new ArrayList<>();
}
