package Shop.Shop.model;

import lombok.Data;

import java.util.Map;

@Data
public class Email {
    String to;
    String from;
    String subject;
    String text;
    String template;
    String attachmentPath;
    Map<String, Object> properties;

}