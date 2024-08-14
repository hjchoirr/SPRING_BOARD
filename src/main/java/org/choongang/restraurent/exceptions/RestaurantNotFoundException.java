package org.choongang.restraurent.exceptions;

import org.choongang.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class RestaurantNotFoundException extends CommonException {
    public RestaurantNotFoundException() {
        super("NotFound.restaurant", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
