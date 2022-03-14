package top.cocoawork.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "group_member", keepGlobalPrefix = true)
public class ChatGroupMember extends AutoAudit {

  @TableField(insertStrategy = FieldStrategy.NOT_NULL)
  private Long gid;

  @TableField(insertStrategy = FieldStrategy.NOT_NULL)
  private Long uid;

  private String userNick;

  @TableField(exist = false)
  private String nickName;

  @TableField(exist = false)
  private String phoneNum;

}
