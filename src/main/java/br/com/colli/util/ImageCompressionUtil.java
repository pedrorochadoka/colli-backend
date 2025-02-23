package br.com.colli.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.IIOImage;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ImageCompressionUtil {

    // Método para compactar uma imagem a partir de InputStream e retornar em byte[]
    public static byte[] compressImage(InputStream imageInputStream, float quality) throws IOException {
        // Usando ImageReader para lidar com o fluxo de entrada
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");  // Tente identificar o formato como PNG
        if (!readers.hasNext()) {
            readers = ImageIO.getImageReadersByFormatName("jpg");  // Caso não seja PNG, tenta JPG
        }

        if (!readers.hasNext()) {
            throw new IOException("Formato de imagem desconhecido.");
        }

        ImageReader reader = readers.next();
        reader.setInput(ImageIO.createImageInputStream(imageInputStream));

        // Lê a imagem usando o ImageReader
        BufferedImage image = reader.read(0);

        // Converte para RGB se necessário (para garantir compatibilidade)
        BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        rgbImage.createGraphics().drawImage(image, 0, 0, null);

        // Cria uma saída em ByteArrayOutputStream para armazenar a imagem compactada
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Verifica o tipo de imagem e aplica a compactação
        String formatName = "jpg";  // Usando JPEG por padrão, pode ser alterado para PNG se necessário
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(formatName);

        if (writers.hasNext()) {
            ImageWriter writer = writers.next();
            ImageWriteParam param = writer.getDefaultWriteParam();

            if ("jpg".equalsIgnoreCase(formatName)) {
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);  // Controla a qualidade da compactação (0.0 a 1.0)
            } else {
                param.setCompressionMode(ImageWriteParam.MODE_DISABLED); // Não aplica compressão para PNG
            }

            // Escreve a imagem compactada para o ByteArrayOutputStream
            try (ImageOutputStream ios = ImageIO.createImageOutputStream(byteArrayOutputStream)) {
                writer.setOutput(ios);
                writer.write(null, new IIOImage(rgbImage, null, null), param);
            }
        }

        // Retorna o array de bytes compactado
        return byteArrayOutputStream.toByteArray();
    }
}
