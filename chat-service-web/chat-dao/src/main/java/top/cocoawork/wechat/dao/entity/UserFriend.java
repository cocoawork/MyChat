package top.cocoawork.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName(value = "user_friend", keepGlobalPrefix = true)
public class UserFriend extends AutoAudit {

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private Long uid;

    @TableField(insertStrategy = FieldStrategy.NOT_NULL)
    private Long fid;

}
