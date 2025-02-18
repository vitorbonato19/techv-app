package com.techv.vitor.mapper;

import com.techv.vitor.controller.dto.TicketRequestDto;
import com.techv.vitor.controller.dto.TicketResponseDto;
import com.techv.vitor.controller.dto.UserRequestDto;
import com.techv.vitor.entity.Ticket;
import com.techv.vitor.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "analyst", ignore = true)
    @Mapping(target = "reply", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "finishedAt", ignore = true)
    @Mapping(target = "finished", ignore = true)
    @Mapping(target = "users", ignore = true)
    Ticket toEntity(TicketRequestDto requestDto);

    TicketResponseDto toResponseDto(Ticket ticket);

    List<TicketResponseDto> toResponseDtoList(List<Ticket> userEntityArray);
}
