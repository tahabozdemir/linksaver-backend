package com.proto.linksaver.mapper;

import com.proto.linksaver.model.Link;
import com.proto.linksaver.payload.response.LinkResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LinkMapper {
    LinkMapper INSTANCE = Mappers.getMapper(LinkMapper.class);

    LinkResponse linkToLinkResponse(Link link);
}
