package com.example.deeseecomics.converter;

import com.example.deeseecomics.domain.model.Superpower;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class StringToSuperpowerConverter implements Converter<String, Superpower> {
    @Override
    public Superpower convert(String source) {
        return Superpower.valueOf(source.toUpperCase());
    }
}