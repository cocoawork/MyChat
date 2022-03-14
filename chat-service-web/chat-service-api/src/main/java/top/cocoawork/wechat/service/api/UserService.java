package top.cocoawork.wechat.service.api;

import top.cocoawork.wechat.service.api.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    UserDto insert(@Valid UserDto user);

    boolean deleteById(@NotNull(message = "查询id不能为空") Long id);

    UserDto findById(@NotNull(message = "查询id不能为空") Long id);

    UserDto update(@Valid UserDto userDto);

    boolean addFriend(@NotNull(message = "用户id不能为空")Long uid, @NotNull(message = "好友id不能为空") Long fid);

    List<UserDto> getFriendsById(@NotNull(message = "查询id不能为空") Long id);

    boolean deleteFriend(@NotNull(message = "用户id不能为空")Long uid, @NotNull(message = "好友id不能为空") Long fid);


}
