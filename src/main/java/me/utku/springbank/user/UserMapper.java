package me.utku.springbank.user;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User> {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    User updateEntity(@MappingTarget User oldEntity, User newEntity);
}
