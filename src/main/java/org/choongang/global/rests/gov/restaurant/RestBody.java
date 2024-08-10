package org.choongang.global.rests.gov.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestBody {
    private List<Restaurant> restaurants;
}
