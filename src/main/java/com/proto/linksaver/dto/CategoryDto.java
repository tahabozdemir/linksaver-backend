package com.proto.linksaver.dto;

import java.util.ArrayList;

public record CategoryDto(String title, String emoji, ArrayList<String> links) { }
