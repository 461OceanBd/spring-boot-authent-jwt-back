package com.rmrtechs.springbootauthentjwtback.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.rmrtechs.springbootauthentjwtback.dto.UserDto;
import com.rmrtechs.springbootauthentjwtback.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class UserMapperTest {

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    
    private LocalDateTime now = LocalDateTime.now();
    
    @Test
    public void testUserToUserDto() {
        User user = User.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .creationDate(now)
            .build();

        UserDto userDto = userMapper.userToUserDto(user);

        assert userDto.getId() == 1L;
        assert "user@example.com".equals(userDto.getEmail());
        assert userDto.getPassword() == null;
        assert "user".equals(userDto.getRole());
        assert now.equals(userDto.getCreationDate());
    }

    

    @Test
    public void testUserToUserDtoWithNulls() {
        User user = User.builder().build();

        UserDto userDto = userMapper.userToUserDto(user);

        assert userDto.getId() == null;
        assert userDto.getEmail() == null;
        assert userDto.getPassword() == null;
        assert userDto.getRole() == null;
        assert userDto.getCreationDate() == null;
    }
    
    @Test
    public void testUserDtoToUser() {
        UserDto userDto = UserDto.builder()
            .id(2L)
            .email("another@example.com")
            .password("password")
        	.role("user")
        	.creationDate(now)
            .build();

        User user = userMapper.userDtoToUser(userDto);

        assert user.getId() == 2L;
        assert "another@example.com".equals(user.getEmail());
        assert "password".equals(user.getPassword());
        assert "user".equals(user.getRole());
        assert now.equals(user.getCreationDate());
    }

    @Test
    public void testUserDtoToUserWithNulls() {
        UserDto userDto = UserDto.builder().build();

        User user = userMapper.userDtoToUser(userDto);

        assert user.getId() == null;
        assert user.getEmail() == null;
        assert user.getPassword() == null;
        assert user.getRole() == null;
        assert user.getCreationDate() == null;
    }

    @Test
    public void testListUsersToUserDtos() {
        User user1 = User.builder()
            .id(1L)
            .email("user1@example.com")
            .password("password1")
            .role("user")
            .creationDate(now)
            .build();

        User user2 = User.builder()
            .id(2L)
            .email("user2@example.com")
            .password("password2")
            .role("user")
            .creationDate(now)
            .build();

        List<User> userList = Arrays.asList(user1, user2);

        List<UserDto> userDtoList = userMapper.usersToUserDtos(userList);

        assert userDtoList.size() == 2;
        assert userDtoList.get(0).getId() == 1L;
        assert "user1@example.com".equals(userDtoList.get(0).getEmail());
        assert userDtoList.get(0).getPassword() == null;
        assert "user".equals(userDtoList.get(0).getRole());
        assert now.equals(userDtoList.get(0).getCreationDate());
        assert userDtoList.get(1).getId() == 2L;
        assert "user2@example.com".equals(userDtoList.get(1).getEmail());
        assert userDtoList.get(1).getPassword() == null;
        assert "user".equals(userDtoList.get(1).getRole());
        assert now.equals(userDtoList.get(1).getCreationDate());
    }

    @Test
    public void testListUsersToUserDtosWithNulls() {
        User userWithNulls = User.builder().build();
        User userNotNull = User.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .creationDate(now)
            .build();

        List<User> userList = Arrays.asList(userWithNulls, userNotNull);

        List<UserDto> userDtoList = userMapper.usersToUserDtos(userList);

        assert userDtoList.size() == 2;

        UserDto userDtoWithNulls = userDtoList.get(0);
        assert userDtoWithNulls.getId() == null;
        assert userDtoWithNulls.getEmail() == null;
        assert userDtoWithNulls.getPassword() == null;
        assert userDtoWithNulls.getRole() == null;
        assert userDtoWithNulls.getCreationDate() == null;

        UserDto userDtoNotNull = userDtoList.get(1);
        assert userDtoNotNull.getId() == 1L;
        assert "user@example.com".equals(userDtoNotNull.getEmail());
        assert userDtoNotNull.getPassword() == null;
        assert "user".equals(userDtoNotNull.getRole());
        assert now.equals(userDtoNotNull.getCreationDate());
    }

    @Test
    public void testListUserDtosToUsers() {
        UserDto userDto1 = UserDto.builder()
            .id(1L)
            .email("user1@example.com")
            .password("password1")
            .role("user")
            .creationDate(now)
            .build();

        UserDto userDto2 = UserDto.builder()
            .id(2L)
            .email("user2@example.com")
            .password("password2")
            .role("user")
            .creationDate(now)
            .build();

        List<UserDto> userDtoList = Arrays.asList(userDto1, userDto2);

        List<User> userList = userMapper.userDtosToUsers(userDtoList);

        assert userList.size() == 2;
        assert userList.get(0).getId() == 1L;
        assert "user1@example.com".equals(userList.get(0).getEmail());
        assert "password1".equals(userList.get(0).getPassword());
        assert "user".equals(userList.get(0).getRole());
        assert now.equals(userList.get(0).getCreationDate());
        assert userList.get(1).getId() == 2L;
        assert "user2@example.com".equals(userList.get(1).getEmail());
        assert "password2".equals(userList.get(1).getPassword());
        assert "user".equals(userList.get(1).getRole());
        assert now.equals(userList.get(1).getCreationDate());
    }

    @Test
    public void testListUserDtosToUsersWithNulls() {
        UserDto userDtoWithNulls = UserDto.builder().build();
        UserDto userDtoNotNull = UserDto.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .creationDate(now)
            .build();

        List<UserDto> userDtoList = Arrays.asList(userDtoWithNulls, userDtoNotNull);

        List<User> userList = userMapper.userDtosToUsers(userDtoList);

        assert userList.size() == 2;

        User userWithNulls = userList.get(0);
        assert userWithNulls.getId() == null;
        assert userWithNulls.getEmail() == null;
        assert userWithNulls.getPassword() == null;
        assert userWithNulls.getRole() == null;
        assert userWithNulls.getCreationDate() == null;

        User userNotNull = userList.get(1);
        assert userNotNull.getId() == 1L;
        assert "user@example.com".equals(userNotNull.getEmail());
        assert "password".equals(userNotNull.getPassword());
        assert "user".equals(userNotNull.getRole());
        assert now.equals(userNotNull.getCreationDate());
    }
}


