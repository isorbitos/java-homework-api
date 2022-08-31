package com.homework.restapi.model.dto;

import lombok.Data;

@Data
public class MsgStatisticsDto {
    private String username;
    private Integer msgCount;
    private String firstMsgDate;
    private String lastMsgDate;
    private Integer msgLenghtAverage;
    private String lastMsgText;
}
