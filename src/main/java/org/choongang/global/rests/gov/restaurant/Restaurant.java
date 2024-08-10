package org.choongang.global.rests.gov.restaurant;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.choongang.member.entities.BaseEntity;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
//@JsonNaming(PropertyNamingStrategy.class)
public class Restaurant extends BaseEntity {
    @Id
    //@JsonProperty("RSTR_ID")
    private Long rstrId;

    @Column(length = 100, nullable = false)
    private String rstrNm;

    @Column(length = 150)
    private String rstrRdnmadr;

    @Column(length = 150)
    private String rstrLnnoAdr;

    private Double rstrLa; // 위도
    private Double rstrLo; // 경도

    @Column(length = 20)
    private String rstrTelno;
    @Column(length = 50)
    private String bsnsStatmBzcndNm; // 영업신고증업태명
    @Column(length = 50)
    private String bsnsLcncNm;  // 영업인허가명
    @Lob
    private String rstrIntrcnCont; // 음식점소개내용

    @Column(length = 50)
    private String areaNm;

    @ToString.Exclude
    @OneToMany(mappedBy="restaurant", fetch = FetchType.LAZY)
    private List<RestImage> rstrImg;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Food> food;
}
