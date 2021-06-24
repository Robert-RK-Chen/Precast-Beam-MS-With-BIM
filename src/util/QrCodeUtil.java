package util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import hibernate.entities.*;
import hibernate.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Robert Chen
 */
public class QrCodeUtil {
    public static Path qrCodePath;

    public Path getQrCode(String id) throws IOException, WriterException {
        BeamInfoEntity beamInfoEntity = new BeamInfoModel().findById(id);

        String urlCode = "预制梁基本信息：\n"
                + "制梁编号：" + beamInfoEntity.getBeamId() + "\n"
                + "制梁种类：" + beamInfoEntity.getBeamKind() + "预制梁\n"
                + "目前状态：" + beamInfoEntity.getBeamState() + "\n"
                + "钢筋型号：" + beamInfoEntity.getSteelType1() + "  "
                             + beamInfoEntity.getSteelType2() + "  "
                             + beamInfoEntity.getSteelType3() + "\n\n" +
                "测量属性：\n";

        if ("长方体".equals(beamInfoEntity.getBeamKind())) {
            urlCode += "长：" + beamInfoEntity.getLength() + "米、"
                     + "宽：" + beamInfoEntity.getWidth() + "米、"
                     + "高：" + beamInfoEntity.getHeight() + "米\n";
        } else {
            urlCode += "半径：" + beamInfoEntity.getRadius() + "米、"
                     + "高：" + beamInfoEntity.getHeight() + "米\n";
        }

        TieInfoEntity tieInfoEntity = new TieInfoModel().findById(id);
        if (tieInfoEntity != null) {
            urlCode += "\n扎钢筋业务信息：\n"
                     + "质检员：" + tieInfoEntity.getWireInspector() + "\n"
                     + "业务开始时间：" + tieInfoEntity.getWireStart() + "\n"
                     + "业务结束时间：" + tieInfoEntity.getWireFinish();
        }

        PouringInfoEntity pouringInfoEntity = new PouringInfoModel().findById(id);
        if (pouringInfoEntity != null) {
            urlCode += "\n\n浇筑业务信息：\n"
                     + "质检员：" + pouringInfoEntity.getPouringInspector() + "\n"
                     + "浇筑开始时间：" + pouringInfoEntity.getPouringStart() + "\n"
                     + "浇筑结束时间：" + pouringInfoEntity.getPouringFinish();
        }

        CuringInfoEntity curingInfoEntity = new CuringInfoModel().findById(id);
        if (curingInfoEntity != null) {
            urlCode += "\n\n养护业务信息：\n"
                     + "质检员：" + curingInfoEntity.getCuringInspector() + "\n"
                     + "养护开始时间：" + curingInfoEntity.getCuringStart() + "\n"
                     + "养护结束时间：" + curingInfoEntity.getCuringFinish();
        }

        BeamStoreEntity beamStoreEntity = new BeamStoreModel().findById(id);
        if (beamStoreEntity != null) {
            urlCode += "\n\n存储业务信息：\n"
                     + "质检员：" + beamStoreEntity.getStoreInspector() + "\n"
                     + "存放开始时间：" + beamStoreEntity.getStoreStart() + "\n"
                     + "预期运出时间：" + beamStoreEntity.getShipmentExpect() + "\n"
                     + "实际运出时间：" + beamStoreEntity.getShipmentActual();
        }

        urlCode = new String(urlCode.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(urlCode, BarcodeFormat.QR_CODE, 600, 600);
        qrCodePath = Files.createTempFile("BeamQRCode", ".PNG");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", qrCodePath);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        return qrCodePath;
    }
}