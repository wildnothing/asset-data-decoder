package com.liquipool.assetdatadecoder.component;

import com.liquipool.assetdatadecoder.domain.AssetData;

public interface AssetDataDecoderComponent {

    AssetData getAssetData(String assetData);
}
