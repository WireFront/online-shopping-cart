package org.com.xyz.onlineshoppingcart.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartException extends RuntimeException {

    private String resultStatus;
    private String resultCode;

    public CartException(String resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }
}