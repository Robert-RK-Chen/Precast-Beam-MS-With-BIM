package util;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;

/**
 * @author Zhao GangZheng
 * @author Robert Chen
 */
public class HashUtil
{
    public String encryptionHash(String text) throws Exception
    {
        String cookiePath = System.getProperties().getProperty("user.home") + "\\AppData\\Local\\PreBeam System";
        Files.createDirectories(Paths.get(cookiePath));

        byte[] plainArray = text.getBytes();
        String algorithm = "SHA-512";
        MessageDigest item = MessageDigest.getInstance(algorithm);
        item.update(plainArray);
        byte[] cipherArray = item.digest();

        String key = new ByteToHex(cipherArray).bytesToHex();

        FileWriter fileWriter = new FileWriter(cookiePath + "\\login.authority");
        fileWriter.write(key);
        fileWriter.flush();
        fileWriter.close();
        return key;
    }
}
