package org.choongang.global.rests.gov.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import org.choongang.member.entities.BaseEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food extends BaseEntity {
    @Id
    private Long menuId;

    @Column(nullable = false)
    private String menuNm;

    private Integer menuPrice;
    private Boolean spcltMenuYn; // 지역특산메뉴여부
    private String spcltMenuNm; // 지역특산메뉴명
    @Column(length = 500)
    private String spcltMenuOgnUrl; // 지역특산메뉴출처 URL

    @Column(length = 1000)
    private String menuDscrn; // 메뉴설명(주재료,조리법,소스,옵션)

    @Column(length = 50)
    private String menuCtgryLclasNm; // 메뉴카테고리대분류명
    private String menuCtgrySclasNm; // 메뉴카테고리소분류명

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rstrId")
    private Restaurant restaurant;

}
