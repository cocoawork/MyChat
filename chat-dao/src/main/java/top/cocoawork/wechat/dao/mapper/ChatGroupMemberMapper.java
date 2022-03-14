package top.cocoawork.wechat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import top.cocoawork.wechat.dao.entity.ChatGroupMember;

import java.util.List;

@Component
@Mapper
public interface ChatGroupMemberMapper extends BaseMapper<ChatGroupMember> {

    List<ChatGroupMember> getMembersById(Long groupId);

    boolean delete(@Param("gid") Long gid, @Param("uid") Long uid);

    boolean update(@Param("member") ChatGroupMember member);
}
