package org.choongang.board.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.member.entities.BaseMemberEntity;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Board extends BaseMemberEntity {
    @Id
    @Column(length = 30)
    private String bId;

    @Column(length = 60, nullable = false)
    private String bName;

    private Boolean active = true;
}
