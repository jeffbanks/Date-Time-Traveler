import static org.junit.Assert.*;

import org.junit.Test;

import com.jjbanks.learning.util.SpaceUtil;

/**
 * Provides unit test coverage for the space utilities.
 * 
 * @author jjbanks.com
 *
 */
public class SpaceUtilTest {

    @Test
    public void verifyRandomLatLon() {

        double lat1 = SpaceUtil.generateRandomLatitude();
        double lat2 = SpaceUtil.generateRandomLatitude();

        assertTrue(lat1 != lat2);

        double lon1 = SpaceUtil.generateRandomLongitude();
        double lon2 = SpaceUtil.generateRandomLongitude();

        assertTrue(lon1 != lon2);

        assertTrue(SpaceUtil.isLatLongValid(lat1, lon1));
        assertTrue(SpaceUtil.isLatLongValid(lat2, lon2));

    }

    @Test
    public void verifyRandomLatLonOutOfBounds() {

        double lat = 360;
        double lon = 180;
        assertFalse(SpaceUtil.isLatLongValid(lat, lon));

        double lat2 = 90;
        double lon2 = -181;
        assertFalse(SpaceUtil.isLatLongValid(lat2, lon2));

        double lat3 = 1111;
        double lon3 = 2222;
        assertFalse(SpaceUtil.isLatLongValid(lat3, lon3));

        double lat4 = 92;
        double lon4 = -180;
        assertFalse(SpaceUtil.isLatLongValid(lat4, lon4));

    }

}
