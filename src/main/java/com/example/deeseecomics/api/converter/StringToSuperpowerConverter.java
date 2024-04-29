package com.example.deeseecomics.api.converter;

import com.example.deeseecomics.api.dto.SuperpowerDTO;
import com.example.deeseecomics.domain.model.Superpower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class StringToSuperpowerConverter implements Converter<String, SuperpowerDTO> {
    @Override
    public SuperpowerDTO convert(String source) {
        return SuperpowerDTO.valueOf(source.toUpperCase());
    }
}