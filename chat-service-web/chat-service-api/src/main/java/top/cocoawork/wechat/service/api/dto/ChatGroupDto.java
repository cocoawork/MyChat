package top.cocoawork.wechat.service.api.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ChatGroupDto {

  private Long id;

  @NotBlank(message = "群组名称不能为空")
  private String name;

  private String desc;

  @NotNull(message = "群组创建者id不能为空")
  private Long owner;

  @NotNull(message = "群组状态不能为空")
  @Min(value = 0)
  private Integer state;

  @NotNull(message = "群组类别不能为空")
  @Min(value = 0)
  private Integer type;

}
