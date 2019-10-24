package com.barbosa.dev.expensecontrol.util;

import com.barbosa.dev.expensecontrol.model.Imagem;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class ImagemUtil {

    public static byte[] getImage(Imagem imagem) {
        try {
            return IOUtils.toByteArray(imagem.getImage().getBinaryStream());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getImage(MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteOutStream);
            return byteOutStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getImage(String imageBase64) {
        try {
            byte[] imageByte = Base64.getDecoder().decode(imageBase64);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageByte);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage, "jpg", byteOutStream);
            byteArrayInputStream.close();

            return byteOutStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
