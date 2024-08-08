package org.choongang.tour.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.member.entities.BaseEntity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourPlace2 extends BaseEntity {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 150, nullable = false)
    private String title;

    private Double latitude;  //위도
    private Double longitude; //경도

    @Column(length = 50)
    private String tel;

    @Column(length = 150)
    private String address;

    @Lob
    private String description;
    private String photoUrl;
    private Integer tourDays;
}
