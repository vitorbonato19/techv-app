package com.techv.vitor.mapper;

import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.Sector;
import com.techv.vitor.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    @Mapping(target = "lastModified", ignore = true)
    @Mapping(target = "sector", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDto requestDto);

    @Mapping(target = "sector", ignore = true)

    UserResponseDto toResponseDto(User userEntity);

    List<UserResponseDto> toResponseDtoList(List<User> userEntityList);
}
