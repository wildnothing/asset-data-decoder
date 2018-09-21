package com.liquipool.assetdatadecoder.exception;

public class AssetDataDecoderException extends RuntimeException {

    public AssetDataDecoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssetDataDecoderException(String message) {
        super(message);
    }
}
