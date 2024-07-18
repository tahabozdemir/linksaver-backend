package com.proto.linksaver.payload.request;

public record LinkRequest(String userId, String categoryId, String title, String url, Boolean isFavorite) {
}
