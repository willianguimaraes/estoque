package com.willian.Estoque.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StandartError implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer status;
    private String msg;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Long timeStamp;

    public StandartError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }


}
