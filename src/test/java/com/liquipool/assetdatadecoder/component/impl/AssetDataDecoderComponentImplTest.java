package com.liquipool.assetdatadecoder.component.impl;

import com.liquipool.assetdatadecoder.component.AssetDataDecoderComponent;
import com.liquipool.assetdatadecoder.domain.AssetData;
import com.liquipool.assetdatadecoder.domain.ErcType;
import com.liquipool.assetdatadecoder.exception.AssetDataDecoderException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssetDataDecoderComponentImplTest {

    private static final String ERC20_ASSET_DATA =
            "0xf47261b0000000000000000000000000e41d2489571d322189246dafa5ebde1f4699f498";

    private static final String ERC271_ASSET_DATA =
            "0x02571792000000000000000000000000371b13d97f4bf77d724e78c16b7dc74099f40e84" +
                    "0000000000000000000000000000000000000000000000000000000000000063";

    private final AssetDataDecoderComponent decoder = new AssetDataDecoderComponentImpl();

    @Test
    public void shouldGetErc20AssetData() {
        AssetData result = decoder.getAssetData(ERC20_ASSET_DATA);

        assertThat(result.getAddress()).isEqualTo("0xe41d2489571d322189246dafa5ebde1f4699f498");
        assertThat(result.getErcType()).isEqualTo(ErcType.ERC20);
    }

    @Test
    public void shouldGetErc271AssetData() {
        AssetData result = decoder.getAssetData(ERC271_ASSET_DATA);

        assertThat(result.getAddress()).isEqualTo("0x371b13d97f4bf77d724e78c16b7dc74099f40e84");
        assertThat(result.getTokenId()).isEqualTo(99);
        assertThat(result.getErcType()).isEqualTo(ErcType.ERC271);
    }

    @Test(expected = AssetDataDecoderException.class)
    public void shouldThrowExceptionWhenUnableToParseAssetData() {
        decoder.getAssetData(null);
    }

    @Test(expected = AssetDataDecoderException.class)
    public void shouldThrowExceptionWhenUnrecognisedAssetProxyId() {
        decoder.getAssetData("dodgyAssetProxyId");
    }
}