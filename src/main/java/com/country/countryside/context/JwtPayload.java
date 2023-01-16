package com.country.countryside.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Payload;
import java.io.Serializable;

/**
 * @author zhoukunlong
 * @description:
 * @date 2023/1/16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtPayload implements Serializable, Payload {

    private static final long serialVersionUID = 4253527541139358523L;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 村庄id
     */
    private String countryId;

    /**
     * 用户姓名
     */
    private String userName;

}
