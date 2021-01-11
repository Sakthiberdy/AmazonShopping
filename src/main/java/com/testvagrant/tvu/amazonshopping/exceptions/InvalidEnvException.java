package com.testvagrant.tvu.amazonshopping.exceptions;

public class InvalidEnvException extends RuntimeException {

    public InvalidEnvException(String env) {
        super(String.format("Cannot load env %s. Supported envs are qa", env));
    }
}
