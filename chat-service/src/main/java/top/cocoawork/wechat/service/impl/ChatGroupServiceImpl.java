package top.cocoawork.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.wechat.common.util.BeanUtil;
import top.cocoawork.wechat.dao.entity.ChatGroup;
import top.cocoawork.wechat.dao.entity.ChatGroupMember;
import top.cocoawork.wechat.dao.mapper.ChatGroupMapper;
import top.cocoawork.wechat.dao.mapper.ChatGroupMemberMapper;
import top.cocoawork.wechat.service.api.ChatGroupService;
import top.cocoawork.wechat.service.api.dto.ChatGroupDto;
import top.cocoawork.wechat.service.api.dto.ChatGroupMemberDto;
import top.cocoawork.wechat.service.api.exception.ChatServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class ChatGroupServiceImpl extends BaseServiceImpl<ChatGroup, ChatGroupDto> implements ChatGroupService {

    @Autowired
    private ChatGroupMapper groupMapper;

    @Autowired
    private ChatGroupMemberMapper groupMemberMapper;

    @Override
    public ChatGroupDto insert(@Valid ChatGroupDto chatGroupDto) {
        if (null == chatGroupDto.getId()) {
            ChatGroup group = dto2d(chatGroupDto);
            groupMapper.insert(group);
            chatGroupDto.setId(group.getId());
            return chatGroupDto;
        }else {
            return update(chatGroupDto);
        }
    }

    @Override
    public ChatGroupDto update(@Valid ChatGroupDto chatGroupDto) {
        if (null == chatGroupDto.getId()) {
            throw new ChatServiceException("更新对象id为空");
        }
        ChatGroup group = dto2d(chatGroupDto);
        groupMapper.updateById(group);
        return chatGroupDto;
    }

    @Override
    public boolean delete(@NotNull(message = "群组id不能为空") Long groupId) {
        return groupMapper.deleteById(groupId) > 0;
    }

    @Override
    public List<ChatGroupMemberDto> getMembersById(@NotNull(message = "群组id不能为空") Long groupId) {
        List<ChatGroupMember> members = groupMemberMapper.getMembersById(groupId);
        return members.parallelStream().map(member -> {
            ChatGroupMemberDto memberDto = new ChatGroupMemberDto();
            BeanUtil.copyProperties(member, memberDto);
            return memberDto;
        }).collect(Collectors.toList());
    }

    @Override
    public ChatGroupMemberDto addMember(@NotNull(message = "群组id不能为空") Long groupId, @NotNull(message = "用户id不能为空") Long uid) {
        ChatGroupMember member = new ChatGroupMember();
        member.setGid(groupId);
        member.setUid(uid);
        groupMemberMapper.insert(member);
        ChatGroupMemberDto memberDto = new ChatGroupMemberDto();
        BeanUtil.copyProperties(member, memberDto);
        return memberDto;
    }

    @Override
    public boolean deleteMember(@NotNull(message = "群组id不能为空") Long groupId, @NotNull(message = "用户id不能为空") Long uid) {
        return groupMemberMapper.delete(groupId, uid);
    }

    @Override
    public ChatGroupMemberDto updateUserNick(@NotNull(message = "群组id不能为空") Long groupId, @NotNull(message = "用户id不能为空") Long uid, @NotBlank(message = "用户昵称不为空") String userNick) {
        ChatGroupMember member = new ChatGroupMember();
        member.setUserNick(userNick);
        member.setGid(groupId);
        member.setUid(uid);
        groupMemberMapper.update(member);
        ChatGroupMemberDto memberDto = new ChatGroupMemberDto();
        BeanUtil.copyProperties(member, memberDto);
        return memberDto;
    }
}
