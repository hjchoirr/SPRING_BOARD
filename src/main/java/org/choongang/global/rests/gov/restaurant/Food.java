package org.choongang.global.rests.gov.restaurant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    @Id
    private Long menuId;
    private String menuNm;
    private Integer menuPrice;
    private Boolean spcltMenuYn; // 지역특산메뉴여부
    private String spcltMenuNm; // 지역특산메뉴명
    private String spcltMenuOgnUrl; // 지역특산메뉴출처 URL

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rstrId")
    private Restaurant restaurant;

}
