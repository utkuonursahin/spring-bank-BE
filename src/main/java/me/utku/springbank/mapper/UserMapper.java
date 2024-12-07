package me.utku.springbank.mapper;

import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.User;
import me.utku.springbank.dto.user.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper extends BaseMapper<User> {
    UserDto toDto(User user);

    User toEntity(UserDto userDto);

    User updateEntity(@MappingTarget User oldEntity, User newEntity);
}
