package top.cocoawork.chat.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.BlobInputStreamTypeHandler;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;

@Data
@TableName(value = "message", keepGlobalPrefix = true, autoResultMap = true)
public class ChatMessage extends AutoId {

  private Integer type;

  @TableField(value = "`from`")
  private Long from;

  @TableField(value = "`to`")
  private Long to;

  private LocalDateTime timestamp;

  @TableField(typeHandler = BlobTypeHandler.class, jdbcType = JdbcType.BLOB)
  private byte[] body;

}
