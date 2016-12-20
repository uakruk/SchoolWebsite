package ua.edu.kordelschool.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.edu.kordelschool.KodreslchoolApplication;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.entity.Attachment;
import ua.edu.kordelschool.entity.AttachmentType;
import ua.edu.kordelschool.service.ArticleService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashSet;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = KodreslchoolApplication.class)
@WebAppConfiguration
public class ArticlesEndpointTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createArticleTest() throws Exception {
        Article article = new Article();
        article.setCaption("caption");
        article.setAuthor("author");
        article.setText("text");
        article.setId(0L);
        article.setDate(new GregorianCalendar());
        HashSet<Attachment> attachments = new HashSet<>();
        Attachment attachment = new Attachment("someGoogle.img", AttachmentType.IMAGE);
        attachment.setId(0L);
        attachments.add(attachment);
        article.setAttachments(attachments);
        String articleJson = json(article);

        mockMvc.perform(post("/article/add")
                .contentType(contentType)
                .content(articleJson))
                .andExpect(status().isOk());
    }

    @Test
    public void editArticleTest() {

    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
