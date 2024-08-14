package org.choongang.board.exceptions;

import org.choongang.global.exceptions.script.AlertException;
import org.springframework.http.HttpStatus;

public class BoardDataNotFoundException extends AlertException {
    public BoardDataNotFoundException() {
        super("NotFound.BoardData", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
