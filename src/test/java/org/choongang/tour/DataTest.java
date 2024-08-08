package org.choongang.tour;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.choongang.tour.entities.TourPlace2;
import org.choongang.tour.repository.TourPlace2Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
//@ActiveProfiles("test")
public class DataTest {
    @Autowired
    private ObjectMapper om;

    @Autowired
    private TourPlace2Repository repository;

    @Test
    void test1() throws Exception {
        File file = new File("D:/test/data1.json");
        List<Map<String, String>> tmp = om.readValue(file, new TypeReference<>() {});
        System.out.println(tmp);

        Map<String, String> fileds = new HashMap<>();
    }

    @Test
    void test2() throws Exception {
        File file = new File("D:/test/data2.json");
        List<Map<String, String>> tmp = om.readValue(file, new TypeReference<>() {});
        System.out.println(tmp);

        Map<String, String> fileds = new HashMap<>();
    }
    @Test
    void test3() throws Exception {
        File file = new File("D:/test/data1.json");
        List<Map<String, String>> tmp = om.readValue(file, new TypeReference<>() {});

        List<TourPlace2> items = tmp.stream()
            .map(d -> TourPlace2.builder()
                .seq(Long.valueOf(d.get("순번")))
                .title(d.get("여행지명"))
                .latitude(Double.valueOf(d.get("위도")))
                .longitude(Double.valueOf(d.get("경도")))
                .tel(d.get("연락처"))
                .address(d.get("주소"))
                .description(d.get("설명"))
                .photoUrl(d.get("사진파일"))
                .tourDays(Integer.valueOf(d.get("여행일")))
                .build()).toList();

        repository.saveAllAndFlush(items);

    }
}
