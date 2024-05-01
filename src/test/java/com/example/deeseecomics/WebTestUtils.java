package com.example.deeseecomics;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.model.Superpower;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.deeseecomics.util.Dto2ModelMapper.mapSuperpowerDtosToModels;

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

        String queryParamKey = "", queryParamValue;
        for (String keyOrValue : keysAndValues) {
            if (valueIsMissing) {
                queryParamValue = keyOrValue;
                uriBuilder.queryParam(queryParamKey, queryParamValue);
                valueIsMissing = false;
            } else {
                queryParamKey = keyOrValue;
                valueIsMissing = true;
            }
        }

        if (valueIsMissing) {
            throw new RuntimeException("%s key has no associated value".formatted(queryParamKey));
        }

        return uriBuilder.toUriString();
    }

    public static String concatSuperpowers(SuperpowerDTO... superpowerDTOS) {
        return concatSuperpowers(mapSuperpowerDtosToModels(EnumSet.copyOf(Set.of(superpowerDTOS))));
    }

    public static String concatSuperpowers(EnumSet<Superpower> superpowers) {
        return EnumSet.copyOf(superpowers).
                stream().
                map(superpower -> superpower.toString().toLowerCase()).
                collect(Collectors.joining(","));
    }


}
