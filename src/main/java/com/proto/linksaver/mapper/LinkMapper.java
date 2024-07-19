package com.proto.linksaver.mapper;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.model.Link;
import com.proto.linksaver.payload.response.LinkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LinkMapper {
    LinkMapper INSTANCE = Mappers.getMapper(LinkMapper.class);

    LinkResponse linkToLinkResponse(Link link);

    @Mapping(target = "userId", ignore = true)
    void updateLinkFromDto(LinkDto linkDto, @MappingTarget Link link);
}
