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

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "analyst", ignore = true)
    @Mapping(target = "reply", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "finishedAt", ignore = true)
    @Mapping(target = "finished", ignore = true)
    Ticket toEntity(TicketRequestDto requestDto);

    TicketResponseDto toRespondeDto(Ticket ticket);

    List<TicketResponseDto> toRespondeDtoList(List<Ticket> userEntityArray);
}
