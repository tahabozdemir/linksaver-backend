package com.proto.linksaver.service;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.model.Category;
import com.proto.linksaver.model.Link;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.repository.CategoryRepository;
import com.proto.linksaver.mapper.LinkMapper;
import com.proto.linksaver.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final CategoryRepository categoryRepository;
    private final LinkRepository linkRepository;

    public LinkResponse create(String categoryId, LinkDto linkDto) {
        Link link = new Link(categoryId, linkDto.title(), linkDto.url(), linkDto.isFavorite(), linkDto.isDelete());
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));
        ArrayList<String> links = category.getLinks();
        linkRepository.save(link);
        links.add(link.getId());
        category.setLinks(links);
        categoryRepository.save(category);
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }

    public LinkResponse update(String categoryId, String id, LinkDto linkDto) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.LINK_NOT_FOUND));

        link.setTitle(linkDto.title());
        link.setUrl(linkDto.url());
        link.setIsFavorite(linkDto.isFavorite());
        link.setIsDelete(linkDto.isDelete());
        linkRepository.save(link);
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }

    public void delete(String categoryId, String linkId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));
        category.getLinks().removeIf(link -> link.equals(linkId));
        linkRepository.deleteById(linkId);
        categoryRepository.save(category);
    }

    public List<LinkResponse> getAll() {
        List<LinkResponse> response = linkRepository
                .findAll()
                .stream()
                .map(LinkMapper.INSTANCE::linkToLinkResponse)
                .toList();

        return response;
    }

    public LinkResponse getById(String id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.LINK_NOT_FOUND));
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }
}
