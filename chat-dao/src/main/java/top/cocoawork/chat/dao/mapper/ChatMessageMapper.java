package top.cocoawork.chat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.chat.dao.entity.ChatMessage;

@Component
@Mapper
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
}
