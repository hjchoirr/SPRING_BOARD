package org.choongang.global.rests.gov.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestHeader {
    private String resultCode;
    private String resultMsg;
    private Long numOfRows;
    private Long pageNo;
    private Long totalCount;
}
