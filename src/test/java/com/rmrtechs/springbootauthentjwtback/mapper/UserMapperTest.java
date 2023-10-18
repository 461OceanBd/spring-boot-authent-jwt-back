package com.rmrtechs.springbootauthentjwtback.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.rmrtechs.springbootauthentjwtback.dto.UserDto;
import com.rmrtechs.springbootauthentjwtback.model.User;

import java.util.Arrays;
import java.util.List;

public class UserMapperTest {

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    
    @Test
    public void testUserToUserDto() {
        User user = User.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .build();

        UserDto userDto = userMapper.userToUserDto(user);

        assert userDto.getId() == 1L;
        assert userDto.getEmail().equals("user@example.com");
        assert userDto.getPassword().equals("password");
        assert userDto.getRole().endsWith("user");
    }
    
    @Test
    public void testUserToUserDtoWithIdAndEmail() {
        User user = User.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .build();

        UserDto userDto = userMapper.userToUserDtoWithIdAndEmail(user);

        assert userDto.getId() == 1L;
        assert userDto.getEmail().equals("user@example.com");
        assert userDto.getPassword() == null;
        assert userDto.getRole() == null;
    }

    

    @Test
    public void testUserToUserDtoWithNulls() {
        User user = User.builder().build();

        UserDto userDto = userMapper.userToUserDto(user);

        assert userDto.getId() == null;
        assert userDto.getEmail() == null;
        assert userDto.getPassword() == null;
        assert userDto.getRole() == null;
    }
    
    @Test
    public void testUserDtoToUser() {
        UserDto userDto = UserDto.builder()
            .id(2L)
            .email("another@example.com")
            .password("password")
        	.role("user")
            .build();

        User user = userMapper.userDtoToUser(userDto);

        assert user.getId() == 2L;
        assert user.getEmail().equals("another@example.com");
        assert user.getPassword().equals("password");
        assert user.getRole().equals("user");
    }

    @Test
    public void testUserDtoToUserWithIdAndEmail() {
        UserDto userDto = UserDto.builder()
            .id(2L)
            .email("another@example.com")
            .build();

        User user = userMapper.userDtoToUser(userDto);

        assert user.getId() == 2L;
        assert user.getEmail().equals("another@example.com");
        assert user.getPassword() == null;
        assert user.getRole() == null;
    }

    @Test
    public void testUserDtoToUserWithNulls() {
        UserDto userDto = UserDto.builder().build();

        User user = userMapper.userDtoToUser(userDto);

        assert user.getId() == null;
        assert user.getEmail() == null;
        assert user.getPassword() == null;
        assert user.getRole() == null;
    }

    @Test
    public void testUsersToUserDtos() {
        User user1 = User.builder()
            .id(1L)
            .email("user1@example.com")
            .password("password1")
            .role("user")
            .build();

        User user2 = User.builder()
            .id(2L)
            .email("user2@example.com")
            .password("password2")
            .role("user")
            .build();

        List<User> userList = Arrays.asList(user1, user2);

        List<UserDto> userDtoList = userMapper.usersToUserDtos(userList);

        assert userDtoList.size() == 2;
        assert userDtoList.get(0).getId() == 1L;
        assert userDtoList.get(0).getEmail().equals("user1@example.com");
        assert userDtoList.get(0).getPassword().equals("password1");
        assert userDtoList.get(0).getRole().equals("user");
        assert userDtoList.get(1).getId() == 2L;
        assert userDtoList.get(1).getEmail().equals("user2@example.com");
        assert userDtoList.get(1).getPassword().equals("password2");
        assert userDtoList.get(1).getRole().equals("user");
    }

    @Test
    public void testUsersToUserDtosWithNulls() {
        User userWithNulls = User.builder().build();
        User userNotNull = User.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .build();

        List<User> userList = Arrays.asList(userWithNulls, userNotNull);

        List<UserDto> userDtoList = userMapper.usersToUserDtos(userList);

        assert userDtoList.size() == 2;

        UserDto userDtoWithNulls = userDtoList.get(0);
        assert userDtoWithNulls.getId() == null;
        assert userDtoWithNulls.getEmail() == null;
        assert userDtoWithNulls.getPassword() == null;
        assert userDtoWithNulls.getRole() == null;

        UserDto userDtoNotNull = userDtoList.get(1);
        assert userDtoNotNull.getId() == 1L;
        assert userDtoNotNull.getEmail().equals("user@example.com");
        assert userDtoNotNull.getPassword().equals("password");
        assert userDtoNotNull.getRole().equals("user");
    }

    @Test
    public void testUserDtosToUsers() {
        UserDto userDto1 = UserDto.builder()
            .id(1L)
            .email("user1@example.com")
            .password("password1")
            .role("user")
            .build();

        UserDto userDto2 = UserDto.builder()
            .id(2L)
            .email("user2@example.com")
            .password("password2")
            .role("user")
            .build();

        List<UserDto> userDtoList = Arrays.asList(userDto1, userDto2);

        List<User> userList = userMapper.userDtosToUsers(userDtoList);

        assert userList.size() == 2;
        assert userList.get(0).getId() == 1L;
        assert userList.get(0).getEmail().equals("user1@example.com");
        assert userList.get(0).getPassword().equals("password1");
        assert userList.get(0).getRole().equals("user");
        assert userList.get(1).getId() == 2L;
        assert userList.get(1).getEmail().equals("user2@example.com");
        assert userList.get(1).getPassword().equals("password2");
        assert userList.get(1).getRole().equals("user");
    }

    @Test
    public void testUserDtosToUsersWithNulls() {
        UserDto userDtoWithNulls = UserDto.builder().build();
        UserDto userDtoNotNull = UserDto.builder()
            .id(1L)
            .email("user@example.com")
            .password("password")
            .role("user")
            .build();

        List<UserDto> userDtoList = Arrays.asList(userDtoWithNulls, userDtoNotNull);

        List<User> userList = userMapper.userDtosToUsers(userDtoList);

        assert userList.size() == 2;

        // Vérifiez que le premier User contient des valeurs nulles
        User userWithNulls = userList.get(0);
        assert userWithNulls.getId() == null;
        assert userWithNulls.getEmail() == null;
        assert userWithNulls.getPassword() == null;
        assert userWithNulls.getRole() == null;

        // Vérifiez que le deuxième User contient les valeurs du deuxième UserDto non null
        User userNotNull = userList.get(1);
        assert userNotNull.getId() == 1L;
        assert userNotNull.getEmail().equals("user@example.com");
        assert userNotNull.getPassword().equals("password");
        assert userNotNull.getRole().equals("user");
    }
}


