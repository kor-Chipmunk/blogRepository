package com.example.demo.entity.converter;

import com.example.demo.util.StringUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Converter
public class CardMaskingConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String raw) {
        return raw;
    }

    @Override
    public String convertToEntityAttribute(String raw) {
        String masked = raw;
        masked = StringUtil.mask(masked, 10, 14);
        masked = StringUtil.mask(masked, 15, 19);
        return masked;
    }
}
