package org.choongang.board.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class RequestBoardConfig {
    private String mode = "add";
    private String bId;
    private String bName;
    private Boolean active;
}
