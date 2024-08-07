package org.choongang.global.rests.gov.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestRestaurant {
    private Integer rstrId;
    private String rstrNm;
    private String rstrRdnmadr;
    private String rstrLnnoAdr;
    private String rstrLa;
    private String rstrLo;
    private String rstrTelno;
    private String bsnsStatmBzcndNm;
    private String bsnsLcncNm;
    private String rstrIntrcnCont;
}
