package top.cocoawork.wechat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import top.cocoawork.wechat.dao.entity.User;

@Component
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
