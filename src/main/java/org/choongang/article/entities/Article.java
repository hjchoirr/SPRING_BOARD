package org.choongang.article.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.choongang.member.entities.BaseEntity;

@Data
@Entity
public class Article extends BaseEntity {
    @Id
    private long seq;
    private String title;
    private String content;
    private String author;
}
