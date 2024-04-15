package com.example.deeseecomics;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.MultiValueMap;

public class MockMvcTestUtils {

    public static String preformMockMvc(MockMvc mockMvc,
                                        MockHttpServletRequestBuilder mockHttpServletRequestBuilder,
                                        MultiValueMap<String, String> params,
                                        MediaType mediaType,
                                        ResultMatcher resultMatcher) throws Exception {

        return mockMvc.perform(mockHttpServletRequestBuilder
                        .params(params)
                        .contentType(mediaType))
                .andExpect(resultMatcher)
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

}
