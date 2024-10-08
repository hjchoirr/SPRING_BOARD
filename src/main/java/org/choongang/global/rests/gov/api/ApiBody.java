package org.choongang.global.rests.gov.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiBody {
    private ApiItems items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
}
