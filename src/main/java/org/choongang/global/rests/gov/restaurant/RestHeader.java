package org.choongang.global.rests.gov.restaurant;

import lombok.Data;

@Data
public class RestHeader {
    private String resultCode;
    private String resultMsg;
    private String numOfRows;
    private String pageNo;
    private String totalCount;
}
