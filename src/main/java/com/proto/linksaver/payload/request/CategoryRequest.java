package com.proto.linksaver.payload.request;

import java.util.ArrayList;

public record CategoryRequest(String userId, String title, String emoji, ArrayList<String> links) { }