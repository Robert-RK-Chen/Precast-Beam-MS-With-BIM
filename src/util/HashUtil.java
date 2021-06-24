package util;

import javafx.scene.control.Alert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Zhao GangZheng
 * @author Robert Chen
 */
public class HashUtil {
    public String encryptionHash(String text) {
        String cookiePath = System.getProperties().getProperty("user.home") + "\\AppData\\Local\\PreBeam System";
        try {
            Files.createDirectories(Paths.get(cookiePath));
        } catch (IOException ioException) {
            Alert pathLoadFailed = new Alert(Alert.AlertType.ERROR);
            pathLoadFailed.setTitle("创建快捷登录凭据路径失败！");
            pathLoadFailed.setHeaderText("请联系技术支持以获得更多帮助！");
            pathLoadFailed.show();
        }

        byte[] plainArray = text.getBytes();
        String algorithm = "SHA-512";
        try {
            MessageDigest item = MessageDigest.getInstance(algorithm);
            item.update(plainArray);
            byte[] cipherArray = item.digest();
            String key = new ByteToHex(cipherArray).bytesToHex();
            try {
                FileWriter fileWriter = new FileWriter(cookiePath + "\\login.authority");
                fileWriter.write(key);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException ioException) {
                Alert fileWriteFailed = new Alert(Alert.AlertType.ERROR);
                fileWriteFailed.setTitle("快捷登陆凭据写入失败！");
                fileWriteFailed.setHeaderText("请联系技术支持以获得更多帮助！");
                fileWriteFailed.show();
            }
            return key;
        } catch (NoSuchAlgorithmException e) {
            Alert noSuchAlgorithmAlert = new Alert(Alert.AlertType.ERROR);
            noSuchAlgorithmAlert.setTitle("快捷登陆凭据加密失败！");
            noSuchAlgorithmAlert.setHeaderText("请联系技术支持以获得更多帮助！");
            noSuchAlgorithmAlert.show();
        }
        return null;
    }
}
