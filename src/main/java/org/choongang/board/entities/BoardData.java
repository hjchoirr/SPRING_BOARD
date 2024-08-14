package org.choongang.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.choongang.member.entities.BaseEntity;
import org.choongang.member.entities.Member;

@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = @Index(name="idx_board_data", columnList = "notice DESC, createdAt DESC"))
public class BoardData extends BaseEntity {
    @Id
    @GeneratedValue
    private Long seq;

    @Column(length = 65, nullable = false, updatable = false)
    private String gid;

    @JoinColumn(name = "bId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 65)
    private String guestPw;  //비회원이 쓴 글

    private boolean notice;  //공지글 여부

    @Column(length = 40, nullable = false)
    private String poster;

    @Column(nullable = false)
    private String subject;

    @Lob
    @Column(nullable = false)
    private String content;

    private int viewCount; //조회수

    @Column(length = 50, updatable = false)
    private String ip;


    @Column(length = 100, updatable = false)
    private String ua; //User-Agent

    private Long num1; //추가 필드 : 정수

    @Column(length = 100)
    private String text1; //추가 필드 : 한줄텍스트
    @Lob
    private String longText1; //추가 필드 : 여러줄텍스트

}
