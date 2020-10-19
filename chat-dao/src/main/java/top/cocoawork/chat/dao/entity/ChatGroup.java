package top.cocoawork.chat.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "group", keepGlobalPrefix = true)
public class ChatGroup extends AutoId {

  private String name;
  private String desc;
  private long owner;
  private int state;
  private int type;


}
