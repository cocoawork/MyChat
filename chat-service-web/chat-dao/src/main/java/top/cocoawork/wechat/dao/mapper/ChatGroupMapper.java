package top.cocoawork.wechat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.wechat.dao.entity.ChatGroup;

@Component
@Mapper
public interface ChatGroupMapper extends BaseMapper<ChatGroup> {
}
