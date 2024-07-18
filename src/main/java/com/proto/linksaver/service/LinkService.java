package com.proto.linksaver.service;

import com.proto.linksaver.dto.LinkDto;
import com.proto.linksaver.exception.ResourceNotFoundException;
import com.proto.linksaver.model.Category;
import com.proto.linksaver.model.Link;
import com.proto.linksaver.payload.request.LinkRequest;
import com.proto.linksaver.payload.response.LinkResponse;
import com.proto.linksaver.repository.CategoryRepository;
import com.proto.linksaver.mapper.LinkMapper;
import com.proto.linksaver.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkService {
    private final CategoryRepository categoryRepository;
    private final LinkRepository linkRepository;

    @Transactional
    public LinkResponse create(LinkRequest linkRequest) {
        Link link = Link.builder()
                .userId(linkRequest.userId())
                .categoryId(linkRequest.categoryId())
                .title(linkRequest.title())
                .url(linkRequest.url())
                .isFavorite(linkRequest.isFavorite())
                .build();

        Category category = categoryRepository.findById(linkRequest.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));

        ArrayList<String> links = category.getLinks();
        linkRepository.save(link);
        links.add(link.getId());
        category.setLinks(links);
        categoryRepository.save(category);
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }

    public LinkResponse update(String id, LinkDto linkDto) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.LINK_NOT_FOUND));

        LinkMapper.INSTANCE.updateLinkFromDto(linkDto, link);
        linkRepository.save(link);
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }

    @Transactional
    public void delete(String linkId) {
        Link deletedLink = linkRepository.findById(linkId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.LINK_NOT_FOUND));

        Category category = categoryRepository.findById(deletedLink.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));

        category.getLinks().removeIf(link -> link.equals(linkId));
        linkRepository.delete(deletedLink);
        categoryRepository.save(category);
    }

    public List<LinkResponse> getAllByCategoryId(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.CATEGORY_NOT_FOUND));

        List<String> linkIds = category.getLinks();
        List<Link> links = linkRepository.findAllById(linkIds);
        return mapToLinkResponses(links);
    }

    public LinkResponse getById(String id) {
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ResourceNotFoundException.ResourceNotFoundExceptionCodeEnum.LINK_NOT_FOUND));
        return LinkMapper.INSTANCE.linkToLinkResponse(link);
    }

    public List<LinkResponse> getFavoritesByUserId(String userId) {
        List<Link> links = linkRepository.findByUserIdAndIsFavorite(userId, true);
        return mapToLinkResponses(links);
    }

    public List<LinkResponse> getAllByUserId(String userId) {
        List<Link> links = linkRepository.findByUserId(userId);
        return mapToLinkResponses(links);
    }

    public List<LinkResponse> searchByTitle(String userId, String title) {
        List<Link> links = linkRepository.findByUserIdAndTitleLikeIgnoreCase(userId, title);
        return mapToLinkResponses(links);
    }

    public List<LinkResponse> searchFavoriteByTitle(String userId, String title) {
        List<Link> links = linkRepository.findByUserIdAndIsFavoriteAndTitleLikeIgnoreCase(userId, true, title);
        return mapToLinkResponses(links);
    }

    private List<LinkResponse> mapToLinkResponses(List<Link> links) {
        return links.stream()
                .map(LinkMapper.INSTANCE::linkToLinkResponse)
                .toList();
    }
}
