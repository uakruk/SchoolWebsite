package ua.edu.kordelschool.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.kordelschool.config.ImagesUploadProperties;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageUploadTest {

    @Autowired
    private ImagesUploadProperties imagesUploadProperties;

    @Autowired
    private ImageService imageService;

    private volatile MultipartFile multipartFile;

    private volatile InputStream saved;

    @Before
    public void setUp() throws IOException, URISyntaxException {

        multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("someimage.jpg");
        when(multipartFile.getInputStream()).thenReturn(new FileInputStream(new ClassPathResource("static/images/article_default.jpg").getFile()))
                                            .thenThrow(new IOException())
                                            .thenThrow(new IOException())
                                            .thenThrow(new IOException())
                                            .thenThrow(new IOException())
                                            .thenThrow(new IOException())
                .thenReturn(new FileInputStream(new ClassPathResource("static/images/article_default.jpg").getFile()));

      //  DemonThread dt = new DemonThread(multipartFile);
      //  dt.start();
    }

    @Test
    public void testWrite() throws IOException {
        String response = imageService.uploadStaticImage(multipartFile);

        assertThat(response).doesNotContain("article");
        assertThat(response).contains("img").endsWith("jpg");

    }

    @Test
    public void testDefault() throws IOException {
        when(multipartFile.isEmpty()).thenReturn(true);

        String response = imageService.uploadStaticImage(multipartFile);

        System.out.println(response);

        assertThat(response).isEqualTo(imageService.getDefaultFilePath());
    }

    @Test
    public void testBackoffDelay() throws IOException, InterruptedException {
        InputStream in = multipartFile.getInputStream();


        System.err.println(" -- Try to write open an input stream");

        String response = imageService.uploadStaticImage(multipartFile);


    }

    private InputStream exceptionPodvaliator() {
        InputStream resp = null;
        try {
            resp = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saved = resp;
        return resp;
    }

    private void doBad() {
        try {
            when(multipartFile.getInputStream()).thenThrow(new IOException());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doGood() {
        try {
            when(multipartFile.getInputStream()).thenReturn(saved);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class DemonThread extends Thread {

        private volatile MultipartFile file;

        public DemonThread(MultipartFile file) {
            this.file = file;
        }

        public void run() {
            InputStream in = exceptionPodvaliator();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                doBad();
                TimeUnit.SECONDS.sleep(2);
                doGood();
                System.err.println("Restored input stream");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
