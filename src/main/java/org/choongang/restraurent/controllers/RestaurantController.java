package org.choongang.restraurent.controllers;

import lombok.RequiredArgsConstructor;
import org.choongang.global.ListData;
import org.choongang.global.rests.JSONData;
import org.choongang.global.rests.gov.restaurant.Restaurant;
import org.choongang.restraurent.services.RestaurantInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/restaurent")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantInfoService infoService;

    @GetMapping("/list")
    public JSONData list(@ModelAttribute RestaurantSearch search) {
        ListData<Restaurant> data = infoService.getList(search);
        return new JSONData(data);
    }

    @GetMapping("/info/{rstrId}")
    public JSONData info(@PathVariable("rstrId") Long rstrId) {
        Restaurant data = infoService.get(rstrId);

        return new JSONData(data);
    }

}
