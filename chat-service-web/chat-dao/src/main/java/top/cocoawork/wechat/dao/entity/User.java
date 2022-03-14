package top.cocoawork.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user", keepGlobalPrefix = true)
public class User extends AutoId {

  private String nickName;
  private Integer loginState;
  private String lastLoginIp;
  private String phoneNum;

}
