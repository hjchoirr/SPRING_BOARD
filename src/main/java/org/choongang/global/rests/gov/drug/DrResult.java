package org.choongang.global.rests.gov.drug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrResult {
    private String code;
    private String message;
}
