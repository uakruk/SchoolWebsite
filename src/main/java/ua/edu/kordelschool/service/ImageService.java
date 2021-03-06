package ua.edu.kordelschool.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.kordelschool.config.ImagesUploadProperties;

import java.io.*;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Service
public class ImageService {

    private static long time = System.currentTimeMillis();

    @Autowired
    private ImagesUploadProperties imagesUploadProperties;

    @Retryable(maxAttempts = 10, value= IOException.class, backoff = @Backoff(multiplier = 2, delay = 200))
    public String uploadStaticImage(MultipartFile image) throws IOException {
        System.err.println("Called method for backoff - " + (System.currentTimeMillis() - time));


        if (!image.isEmpty()) {
            String fileExtension = getFileExtension(image.getOriginalFilename());
            File tempFile = File.createTempFile("img", fileExtension,
                    imagesUploadProperties.getUploadPath().getFile());

            try (InputStream in = image.getInputStream(); OutputStream out = new FileOutputStream(tempFile)) {
                IOUtils.copy(in, out);
                return tempFile.getPath().substring(tempFile.getPath().indexOf("/static/") + "/static".length());
            }
        }
        return getDefaultFilePath();
    }

    @Retryable(maxAttempts = 10, value= IOException.class, backoff = @Backoff(multiplier = 2, delay = 200))
    public String getDefaultFilePath() throws IOException {
        String filename = imagesUploadProperties.getDefaultArticleImage().getURI().getPath();

        return filename.substring(filename.indexOf("/static/") + "/static".length());
    }

    @Retryable(maxAttempts = 10, value= IOException.class, backoff = @Backoff(multiplier = 2, delay = 200))
    public String uploadEncodedImage(MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            String fileExtension = "data:image/" + getFileExtension(image.getOriginalFilename()) + ";base64," ;
            String result = fileExtension + Base64.encodeBase64String(image.getBytes());

            return result;
        }
        return getDefaultFilePath();
    }


    String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
