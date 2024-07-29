package org.choongang.category.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.choongang.member.entities.BaseEntity;

@Data
@Entity
public class Category {
    @Id
    private String categoryCcode;
    private String categoryName;
}
