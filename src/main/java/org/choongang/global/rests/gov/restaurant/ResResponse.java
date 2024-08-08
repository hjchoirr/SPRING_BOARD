package org.choongang.global.rests.gov.restaurant;

import lombok.Data;

import java.util.List;

@Data
public class ResResponse {
    private RestHeader restHeader;
    private List<Restaurant> items;
}
