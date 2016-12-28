package ua.edu.kordelschool.dto;

/**
 * @author Yaroslav Kruk on 12/11/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.8
 */
public class AttachmentDto {

    private String uri;

    private String type;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AttachmentDto(String uri, String type) {
        this.uri = uri;
        this.type = type;
    }

    public AttachmentDto() {}
}
