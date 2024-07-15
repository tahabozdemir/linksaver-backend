package com.proto.linksaver.payload.request;

public record LinkRequest(String categoryId, String title, String url, Boolean isFavorite, Boolean isDelete) {
}
