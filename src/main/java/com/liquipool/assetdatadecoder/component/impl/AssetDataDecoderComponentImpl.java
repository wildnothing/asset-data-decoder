package com.liquipool.assetdatadecoder.component.impl;

import com.liquipool.assetdatadecoder.component.AssetDataDecoderComponent;
import com.liquipool.assetdatadecoder.config.Constants;
import com.liquipool.assetdatadecoder.domain.AssetData;
import com.liquipool.assetdatadecoder.domain.ErcType;
import com.liquipool.assetdatadecoder.exception.AssetDataDecoderException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AssetDataDecoderComponentImpl implements AssetDataDecoderComponent {

    private static final String ADDRESS_PREFIX = "0x";

    @Override
    public AssetData getAssetAddress(String assetData) {
        try {
            if (isErc20(assetData)) {
                return getErc20Address(assetData);
            } else if(isErc271(assetData)) {
                return getErc271Address(assetData);
            }
        } catch(Exception e) {
            throw new AssetDataDecoderException("Error while parsing asset data: " + assetData, e);
        }

        throw new AssetDataDecoderException("Unrecognised Asset Proxy ID");
    }

    private boolean isErc20(String assetData) {
        return StringUtils.equals(Constants.ERC20_ASSET_PROXY_ID, assetData.substring(0, 10));
    }

    private boolean isErc271(String assetData) {
        return StringUtils.equals(Constants.ERC271_ASSET_PROXY_ID, assetData.substring(0, 10));
    }

    private AssetData getErc20Address(String assetData) {
        return new AssetData(ADDRESS_PREFIX + assetData.substring(assetData.length() - 40), ErcType.ERC20);
    }

    private AssetData getErc271Address(String assetData) {
        return new AssetData(ADDRESS_PREFIX + assetData.substring(34, 74), getTokenId(assetData), ErcType.ERC271);
    }

    private int getTokenId(String assetData) {
        Byte tokenIdAsByte = Byte.decode(ADDRESS_PREFIX + assetData.substring(assetData.length() - 64));
        return Byte.toUnsignedInt(tokenIdAsByte);
    }
}
