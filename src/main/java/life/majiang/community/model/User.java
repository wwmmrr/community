package life.majiang.community.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

}
