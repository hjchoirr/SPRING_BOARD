package org.choongang.restraurent.services;

import lombok.RequiredArgsConstructor;
import org.choongang.global.rests.gov.restaurant.Food;
import org.choongang.global.rests.gov.restaurant.ResResponse;
import org.choongang.global.rests.gov.restaurant.RestImage;
import org.choongang.global.rests.gov.restaurant.Restaurant;
import org.choongang.restraurent.repository.FoodRepository;
import org.choongang.restraurent.repository.RestImageRepository;
import org.choongang.restraurent.repository.RestaurentRepository;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Lazy
@Service
@RequiredArgsConstructor
public class DataTransferService {
    private final RestaurentRepository restaurentRepository;
    private final RestImageRepository restImageRepository;
    private final FoodRepository foodRepository;
    private final RestTemplate restTemplate;
    private final RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    private String servicekey = "8Mu7gNxO98975QV25VMKBnsDC82WaomG1raYEiOXoi3kOTGsi89KCUJBxZI0HNz6";

    public void update1(int pageNo) {
        pageNo = Math.max(pageNo, 1);
        System.out.println("Page=" + pageNo);
        String url = String.format("https://seoul.openapi.redtable.global/api/rstr?serviceKey=%s&pageNo=%d", servicekey, pageNo);
        String url2 = String.format("https://seoul.openapi.redtable.global/api/rstr/oprt?serviceKey=%s&pageNo=%d", servicekey, pageNo);

        //ResponseEntity<String> tmp3 = restTemplate.getForEntity(URI.create(url), String.class);
        //System.out.println(tmp3);

        ResponseEntity<ResResponse> response = restTemplate.getForEntity(URI.create(url), ResResponse.class);
        ResponseEntity<ResResponse> response2 = restTemplate.getForEntity(URI.create(url2), ResResponse.class);
        System.out.println("response.getStatusCode() : " + response.getStatusCode());
        System.out.println("response.getStatusCode().is2xxSuccessful() : " + response.getStatusCode().is2xxSuccessful());

        if(!response.getStatusCode().is2xxSuccessful()) {
            return;
        }
        ResResponse result = response.getBody();
        ResResponse result2 = response2.getBody();

        if(!result.getHeader().get("resultCode").equals("00")) {
            return;
        }
        System.out.println("result.getHeader().get(\"resultCode\") : " + result.getHeader().get("resultCode"));


        System.out.println("====result===");
        System.out.println(result);

        List<Map<String,String>> tmp2 = result2.getBody();
        List<Map<String,String>> tmp = result.getBody();

        System.out.println("====tmp====");
        System.out.println(tmp);


        List<Restaurant> items = tmp.stream().map(d-> {
                    Map<String, String> extra = getExtra(tmp2, d.get("RSTR_ID"));

                    Restaurant rest = Restaurant.builder()
                            .rstrId(Long.valueOf(d.get("RSTR_ID")))
                            .rstrNm(d.get("RSTR_NM"))
                            .rstrRdnmadr(d.get("RSTR_RDNMADR"))
                            .rstrLnnoAdr(d.get("RSTR_LNNO_ADRES"))
                            .rstrLa(Double.valueOf(d.get("RSTR_LA")))
                            .rstrLo(Double.valueOf(d.get("RSTR_LO")))
                            .rstrTelno(d.get("RSTR_TELNO"))
                            .rstrIntrcnCont(d.get("RSTR_INTRCN_CONT"))
                            .bsnsStatmBzcndNm(d.get("BSNS_STATM_BZCND_NM"))
                            .bsnsLcncNm(d.get("BSNS_LCNC_NM"))
                            .build();
                    if (extra == null) {
                        return rest;
                    }
                    rest.setAreaNm(extra.get("AREA_NM"));

                    return rest;
                }).toList();
        //items.forEach(System.out::println);
        if(items == null || items.isEmpty()) {
            return;
        }
        restaurentRepository.saveAllAndFlush(items);


    }

    private Map<String, String> getExtra(List<Map<String, String>> items, String rstrId) {
        if (items == null || items.isEmpty()) return null;

        return items.stream()
                .filter(d -> d.get("RSTR_ID").equals(rstrId))
                .findFirst().orElse(null);
    }

    public void update2(int pageNo) {
        pageNo = Math.max(pageNo, 1);

        String url = String.format("https://seoul.openapi.redtable.global/api/rstr/img?serviceKey=%s&pageNo=%d", servicekey, pageNo);

        ResponseEntity<ResResponse> response = restTemplate.getForEntity(URI.create(url), ResResponse.class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            return;
        }
        ResResponse result = response.getBody();

        if(!result.getHeader().get("resultCode").equals("00")) {
            return;
        }
        System.out.println("result.getHeader().get(\"resultCode\") : " + result.getHeader().get("resultCode"));

        List<Map<String,String>> tmp = result.getBody();
        if(tmp == null || tmp.isEmpty()) return;

        List<RestImage> items = tmp.stream()
                .map(d -> {
                   Restaurant rest = restaurentRepository.findById(Long.valueOf(d.get("RSTR_ID"))).orElse(null);

                   return RestImage.builder()
                           .restaurant(rest)
                           .rstrImgUrl(d.get("RSTR_IMG_URL")).build();

        }).toList();
        //items.forEach(System.out::println);

        restImageRepository.saveAllAndFlush(items);

    }

    public void update3(int pageNo) {
        pageNo = Math.max(pageNo, 1);

        String url = String.format("https://seoul.openapi.redtable.global/api/menu/korean?serviceKey=%s&pageNo=%d", servicekey, pageNo);
        String url2 = String.format("https://seoul.openapi.redtable.global/api/menu-dscrn/korean?serviceKey=%s&pageNo=%d", servicekey, pageNo);

        ResponseEntity<ResResponse> response = restTemplate.getForEntity(URI.create(url), ResResponse.class);
        ResponseEntity<ResResponse> response2 = restTemplate.getForEntity(URI.create(url2), ResResponse.class);

        if(!response.getStatusCode().is2xxSuccessful()) {
            return;
        }
        ResResponse result = response.getBody();
        ResResponse result2 = response2.getBody();

        if(!result.getHeader().get("resultCode").equals("00")) {
            return;
        }

        List<Map<String,String>> tmp = result.getBody();
        List<Map<String,String>> tmp2 = result2.getBody();
        //System.out.println(tmp2);
        if(tmp == null || tmp.isEmpty()) return;

        List<Food> items = tmp.stream().map(d-> {
            Restaurant restaurant = restaurentRepository.findById(Long.valueOf(d.get("RSTR_ID"))).orElse(null);
            Map<String, String> extra = getExtraMenu(tmp2, d.get("MENU_ID"));
            Food food =  Food.builder()
                    .menuId(Long.valueOf(d.get("MENU_ID")))
                    .menuNm(d.get("MENU_NM"))
                    .menuPrice(Integer.valueOf(d.get("MENU_PRICE")))
                    .spcltMenuYn(d.get("SPCLT_MENU_YN").equals("Y"))
                    .spcltMenuNm(d.get("SPCLT_MENU_NM"))
                    .spcltMenuOgnUrl(d.get("SPCLT_MENU_OGN_URL"))
                    .restaurant(restaurant)
                    .build();
            if (extra == null) {
                return food;
            }
            food.setMenuDscrn(extra.get("MENU_DSCRN"));
            food.setMenuCtgryLclasNm(extra.get("MENU_CTGRY_LCLAS_NM"));
            food.setMenuCtgrySclasNm(extra.get("MENU_CTGRY_SCLAS_NM"));

            return food;
        }).toList();

        //items.forEach(System.out::println);
        foodRepository.saveAllAndFlush(items);
    }
    private Map<String, String> getExtraMenu(List<Map<String, String>> items, String menuId) {
        if (items == null || items.isEmpty()) return null;

        return items.stream()
                .filter(d -> d.get("MENU_ID").equals(menuId))
                .findFirst().orElse(null);
    }
}
