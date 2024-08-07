package org.choongang.global.rests.gov.drug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocalData_010106_Sm {
    private Integer list_total_count;
    private DrResult drResult;
    private DrRows drRows;
}
