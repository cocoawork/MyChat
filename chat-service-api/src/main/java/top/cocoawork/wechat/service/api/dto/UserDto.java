package top.cocoawork.wechat.service.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    private Long id;

    @NotBlank(message = "用户昵称不能为空")
    private String nickName;
    private Integer loginState;
    private String lastLoginIp;
    private String phoneNum;

}
