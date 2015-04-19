import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AssetUtilTest {

    final List<Asset> assets = Arrays.asList(
            new Asset(Asset.AssetType.BOND, 1000),
            new Asset(Asset.AssetType.BOND, 2000),
            new Asset(Asset.AssetType.STOCK, 3000),
            new Asset(Asset.AssetType.STOCK, 4000)
    );

    @Test
    public void testTotal() throws Exception {


        System.out.println("Total of all assets: " + AssetUtil.totalAssetValues(assets));

    }

    @Test
    public void testTotalAssets() throws Exception {
        System.out.println("Total of all assets: " +
                AssetUtil.totalAssetValues(assets, asset -> true));

        System.out.println("Total of bonds: " +
                AssetUtil.totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.BOND));
        System.out.println("Total of stocks: " +
                AssetUtil.totalAssetValues(assets, asset -> asset.getType() == Asset.AssetType.STOCK));
    }


}