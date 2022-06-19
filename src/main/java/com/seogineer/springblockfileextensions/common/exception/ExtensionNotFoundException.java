package com.seogineer.springblockfileextensions.common.exception;

public class ExtensionNotFoundException extends RuntimeException {
    private static final String MESSAGE = "The Extension is not found";

    public ExtensionNotFoundException() {
        super(MESSAGE);
    }
}

