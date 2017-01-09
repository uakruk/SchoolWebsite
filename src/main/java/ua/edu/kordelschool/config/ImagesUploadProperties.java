package ua.edu.kordelschool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * @author Yaroslav Kruk on 12/29/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
@Configuration
public class ImagesUploadProperties {

    @Value("${upload.images.path}")
    private Resource uploadPath;

    @Value("${upload.images.default.article}")
    private Resource defaultArticleImage;

    public Resource getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(Resource uploadPath) {
        this.uploadPath = uploadPath;
    }

    public Resource getDefaultArticleImage() {
        return defaultArticleImage;
    }

    public void setDefaultArticleImage(Resource defaultArticleImage) {
        this.defaultArticleImage = defaultArticleImage;
    }
}
