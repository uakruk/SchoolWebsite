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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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

    private MultipartFile multipartFile;

    @Before
    public void setUp() throws IOException, URISyntaxException {

        multipartFile = mock(MultipartFile.class);
        when(multipartFile.getOriginalFilename()).thenReturn("someimage.jpg");
        when(multipartFile.getInputStream()).thenReturn(new FileInputStream(new ClassPathResource("static/images/article_default.jpg").getFile()));
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
}
