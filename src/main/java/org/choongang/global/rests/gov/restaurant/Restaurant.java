package org.choongang.global.rests.gov.restaurant;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    private Long rstrId;
    @Column(length = 100, nullable = false)
    private String rstrNm;
    @Column(length = 150, nullable = false)
    private String rstrRdnmadr;
    @Column(length = 150, nullable = false)
    private String rstrLnnoAdr;

    private Double rstrLa;
    private Double rstrLo;

    @Column(length = 20)
    private String rstrTelno;
    @Column(length = 50)
    private String bsnsStatmBzcndNm;
    @Column(length = 50)
    private String bsnsLcncNm;
    @Lob
    private String rstrIntrcnCont;

    @Column(length = 10)
    private String rstrAreaClsfNm;

    @ToString.Exclude
    @OneToMany(mappedBy="restaurant", fetch = FetchType.LAZY)
    private List<RestImage> rstrImg;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    private List<Food> food;
}
