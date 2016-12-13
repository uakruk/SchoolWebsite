package ua.edu.kordelschool.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ua.edu.kordelschool.dto.ArticleDto;
import ua.edu.kordelschool.entity.Article;
import ua.edu.kordelschool.service.ArticleService;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class ArticlesEndpointTest {

    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleServiceMock;

    //Add WebApplicationContext field here.

    //The setUp() method is omitted.

    @Test
    public void createArticleTest() throws Exception {
        ArticleDto dto = new ArticleDto();
        dto.setAuthor("author");
        dto.setCaption("caption");

        Article added = new Article();
        dto.setAuthor("author");
        dto.setCaption("caption");
        dto.setId(2L);

        when(articleServiceMock.createArticle(any(ArticleDto.class))).thenReturn(added);

        mockMvc.perform(post("/add"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.author", is("author")))
                .andExpect(jsonPath("$.caption", is("caption")));

        ArgumentCaptor<ArticleDto> dtoCaptor = ArgumentCaptor.forClass(ArticleDto.class);
        verify(articleServiceMock, times(1)).createArticle(dtoCaptor.capture());
        verifyNoMoreInteractions(articleServiceMock);

        ArticleDto dtoArgument = dtoCaptor.getValue();
        assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getAuthor(), is("author"));
        assertThat(dtoArgument.getCaption(), is("caption"));
    }
}
