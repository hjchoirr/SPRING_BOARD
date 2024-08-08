package org.choongang.global.rests.gov.restaurant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestImage {
    @Id
    @GeneratedValue
    private Long seq;
    private String rstrImgUrl;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rstrId" )
    private Restaurant restaurant;

}
