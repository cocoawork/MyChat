package top.cocoawork.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import top.cocoawork.wechat.dao.entity.User;
import top.cocoawork.wechat.dao.entity.UserFriend;
import top.cocoawork.wechat.dao.mapper.UserFriendMapper;
import top.cocoawork.wechat.dao.mapper.UserMapper;
import top.cocoawork.wechat.service.api.UserService;
import top.cocoawork.wechat.service.api.dto.UserDto;
import top.cocoawork.wechat.service.api.exception.ChatServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserFriendMapper userFriendMapper;

    public UserDto insert(@Valid UserDto user) {
        //id为空，执行插入操作
        if (null == user.getId()) {
            User userDao = dto2d(user);
            userMapper.insert(userDao);
            user.setId(userDao.getId());
            return user;
        }else {
            //id不为空，执行更新操作
            return update(user);
        }
    }

    public boolean deleteById(@NotNull(message = "查询id不能为空") Long id) {
        return userMapper.deleteById(id) > 0;
    }

    public UserDto findById(@NotNull(message = "查询id不能为空") Long id) {
        User user = userMapper.selectById(id);
        return d2dto(user);
    }

    public UserDto update(@Valid UserDto userDto) {
        if (null == userDto.getId()) {
            throw new ChatServiceException("更新对象id为空");
        }
        User user = dto2d(userDto);
        userMapper.updateById(user);
        return d2dto(user);
    }

    @Override
    public boolean addFriend(@NotNull(message = "用户id不能为空") Long uid, @NotNull(message = "好友id不能为空") Long fid) {
        UserFriend uf = new UserFriend();
        uf.setFid(fid);
        uf.setUid(uid);
        return userFriendMapper.insert(uf) > 0;
    }

    @Override
    public List<UserDto> getFriendsById(@NotNull(message = "查询id不能为空") Long id) {
        List<User> friends = userFriendMapper.getFriendsById(id);
        return friends.parallelStream().map(this::d2dto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteFriend(@NotNull(message = "用户id不能为空") Long uid, @NotNull(message = "好友id不能为空") Long fid) {
        return userFriendMapper.delete(uid, fid);
    }
}
