package org.choongang.board.exceptions;

import org.choongang.global.exceptions.script.AlertException;
import org.springframework.http.HttpStatus;

public class BoardNotFoundException extends AlertException {
    public BoardNotFoundException() {
        super("NotFound.board", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
