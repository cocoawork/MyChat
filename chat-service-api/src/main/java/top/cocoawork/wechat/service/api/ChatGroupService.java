package top.cocoawork.wechat.service.api;

import top.cocoawork.wechat.service.api.dto.ChatGroupDto;
import top.cocoawork.wechat.service.api.dto.ChatGroupMemberDto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ChatGroupService {

    ChatGroupDto insert(@Valid ChatGroupDto chatGroupDto);

    ChatGroupDto update(@Valid ChatGroupDto chatGroupDto);

    boolean delete(@NotNull(message = "群组id不能为空") Long groupId);

    List<ChatGroupMemberDto> getMembersById(@NotNull(message = "群组id不能为空") Long groupId);

    ChatGroupMemberDto addMember(@NotNull(message = "群组id不能为空") Long groupId, @NotNull(message = "用户id不能为空") Long uid);

    boolean deleteMember(@NotNull(message = "群组id不能为空") Long groupId, @NotNull(message = "用户id不能为空") Long uid);

    ChatGroupMemberDto updateUserNick(@NotNull(message = "群组id不能为空") Long groupId,
                                      @NotNull(message = "用户id不能为空") Long uid,
                                      @NotBlank(message = "用户昵称不为空") String userNick);


}
