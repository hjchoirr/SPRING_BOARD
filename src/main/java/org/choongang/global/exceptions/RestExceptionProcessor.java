package org.choongang.global.exceptions;

import org.choongang.global.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

public interface RestExceptionProcessor {
    @ExceptionHandler(Exception.class)
    default ResponseEntity<JSONData> errorHandler(Exception e) {

        Object message = e.getMessage(); //일반 예외

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        if (e instanceof CommonException commonException) { // 커맨드 객체에 대한 예외
            status = commonException.getStatus();

            Map<String, List<String>> errorMessages = commonException.getErrMessages();
            if (errorMessages != null) message = errorMessages;
        }

        JSONData data = new JSONData();
        data.setSuccess(false);
        data.setMessage(message);
        data.setStatus(status);

        e.printStackTrace();

        return ResponseEntity.status(status).body(data);
    }
}
