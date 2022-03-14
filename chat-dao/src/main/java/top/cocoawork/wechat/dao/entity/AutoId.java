package top.cocoawork.wechat.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class AutoId extends AutoAudit {

    @TableField
    @TableId(type = IdType.AUTO)
    private Long id;

}
