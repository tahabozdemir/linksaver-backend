package com.proto.linksaver.dto;

public record LinkDto(String userId, String title, String url, Boolean isFavorite, Boolean isDelete) { }
