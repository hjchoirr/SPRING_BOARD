package org.choongang.global.rests.gov.drug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrRows {
    List<DrRow> drRows;
}
