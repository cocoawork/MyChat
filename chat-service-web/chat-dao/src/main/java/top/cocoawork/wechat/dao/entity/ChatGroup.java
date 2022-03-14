package top.cocoawork.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "group", keepGlobalPrefix = true)
public class ChatGroup extends AutoId {

  private String name;
  private String desc;
  private Long owner;
  private Integer state;
  private Integer type;


}
