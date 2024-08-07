package org.choongang.global.rests.gov.drug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrRow {
    private String pnsfteamcode;
    private String mgtno;
    private String apvpermymd;
    private String apvcancelymd;
    private String trdstategbn;
    private String trdstatenm;
    private String dtlstategbn;
    private String dtlstatenm;
    private String dcbymd;
    private String clgstdt;
    private String clgenddt;
    private String ropnymd;
    private String sitetel;
    private String sitearea;
    private String sitepostno;
    private String sitewhladdr;
    private String rdnwhladdr;
    private String rdnpostno;
    private String bplcnm;
    private String lastmodts;
    private String updategbn;
    private String updatedt;
    private String uptaenm;
    private String x;
    private String y;
    private String pharmtrdar;
    private String asgnymd;
}
