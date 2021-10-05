package com.odedoyinakindele.customerdatabaseapi.exceptions;

import com.odedoyinakindele.customerdatabaseapi.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<Object> exception(Exception e) {
        ApiResponse response = new ApiResponse.ResponseBuilder()
                .setResponseCode(HttpStatus.NOT_FOUND.value()).setData(null).setMessage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
