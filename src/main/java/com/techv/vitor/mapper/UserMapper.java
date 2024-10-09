package com.techv.vitor.mapper;

import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.controller.dto.UserResponseDto;
import com.techv.vitor.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    User toEntity(UserRequestDto requestDto);

    UserResponseDto toResponseDto(User userEntity);

    List<UserResponseDto> toResponseDtoList(List<User> userEntityList);
}
