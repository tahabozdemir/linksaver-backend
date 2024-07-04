package com.proto.linksaver.payload.response;

import java.util.ArrayList;

public record CategoryResponse(String id, String title, String emoji, ArrayList<String> links) { }
