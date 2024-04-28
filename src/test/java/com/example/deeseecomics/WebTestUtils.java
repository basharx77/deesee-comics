package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class WebTestUtils {

    public static String preformMockMvc(MockMvc mockMvc,
                                        MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                        MediaType mediaType,
                                        ResultMatcher resultMatcher) throws Exception {

        return mockMvc.perform(mockHttpServletRequestBuilder.contentType(mediaType))
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    public static String getUrlWithQueryParams(String url, String... keysAndValues) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);
        boolean valueIsMissing = false;

        String queryParamKey = "",queryParamValue;
        for(String keyOrValue : keysAndValues){
            if (valueIsMissing){
                queryParamValue=keyOrValue;
                uriBuilder.queryParam(queryParamKey,queryParamValue);
                valueIsMissing=false;
            } else {
                queryParamKey=keyOrValue;
                valueIsMissing=true;
            }
        }

        if(valueIsMissing){
            throw new RuntimeException("");
        }

        return uriBuilder.toUriString();
    }

    public static String concatSuperpowers(SuperpowerDTO... superpowerDTOS) {
        return EnumSet.copyOf(List.of(superpowerDTOS)).
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                collect(Collectors.joining(","));
    }
    
}
