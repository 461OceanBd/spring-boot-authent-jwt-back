package com.rmrtechs.springbootauthentjwtback.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.rmrtechs.springbootauthentjwtback.dto.UserDto;
import com.rmrtechs.springbootauthentjwtback.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Named("userToUserDto")
	@Mapping(target = "password", ignore = true)
	UserDto userToUserDto(User user);
	
	@IterableMapping(qualifiedByName = "userToUserDto")
	List<UserDto> usersToUserDtos(List<User> users);
	
	@Named("userDtoToUser")
	User userDtoToUser(UserDto userDto);

	@IterableMapping(qualifiedByName = "userDtoToUser")
	List<User> userDtosToUsers(List<UserDto> userDtos);
}
