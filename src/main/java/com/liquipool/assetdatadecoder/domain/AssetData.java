package com.liquipool.assetdatadecoder.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class AssetData {

    private final String address;
    private int tokenId;
    private final ErcType ercType;

    public AssetData(String address, ErcType ercType) {
        this.address = address;
        this.ercType = ercType;
    }

    public AssetData(String address, int tokenId, ErcType ercType) {
        this.address = address;
        this.tokenId = tokenId;
        this.ercType = ercType;
    }
}
