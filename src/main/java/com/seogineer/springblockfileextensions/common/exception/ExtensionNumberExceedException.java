package com.seogineer.springblockfileextensions.common.exception;

public class ExtensionNumberExceedException extends RuntimeException {
    private static final String MESSAGE = "Extensions exceed";

    public ExtensionNumberExceedException() {
        super(MESSAGE);
    }
}

