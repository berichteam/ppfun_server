import java.awt.Color;
import java.io.IOException;

import com.pipi.common.util.VisualQRCode;
import org.junit.Test;


/**
 * @Title: TestVisualQRCode.java
 * @Package com.boat.visualqrcode
 * @Description: TODO(用一句话描述该文件做什么)
 * @author 黄本豪
 * @date 2016年12月1日 下午3:29:53
 * @version V1.0
 */

public class TestVisualQRCode {

    private final String outPutPath = "C:\\Users\\Administrator\\Desktop\\picture\\";

    @Test
    public void testPOSITIONRECTANGLE() {
        String url = "www.baidu.com";
        try {
            VisualQRCode.createQRCode(url, "C:\\Users\\Administrator\\Desktop\\picture\\2.jpg", outPutPath + "POSITIONRECTANGLE.png", 'M', new Color(2, 85, 43), null, null, null, true,
                    VisualQRCode.POSITION_DETECTION_SHAPE_MODEL_RECTANGLE, VisualQRCode.FILL_SHAPE_MODEL_RECTANGLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFILLCIRCLE() {
        String url = "www.baidu.com";
        try {
            VisualQRCode.createQRCode(url, "C:\\Users\\Administrator\\Desktop\\picture\\2.jpg", outPutPath+"FILLCIRCLE.png", 'M', new Color(2, 85, 43), null, null, null, true,
                    VisualQRCode.POSITION_DETECTION_SHAPE_MODEL_ROUND_RECTANGLE, VisualQRCode.FILL_SHAPE_MODEL_CIRCLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLARGEIMG(){
        String url = "www.baidu.com";
        try {
            VisualQRCode.createQRCode(url, "C:\\Users\\Administrator\\Desktop\\picture\\2.jpg", outPutPath+"LARGEIMG.png", 'M', new Color(170, 24, 67), 800, 420, 200, false,
                    VisualQRCode.POSITION_DETECTION_SHAPE_MODEL_ROUND_RECTANGLE, VisualQRCode.FILL_SHAPE_MODEL_RECTANGLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}