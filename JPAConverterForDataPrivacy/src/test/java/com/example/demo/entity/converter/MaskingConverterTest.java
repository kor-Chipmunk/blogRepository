package com.example.demo.entity.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MaskingConverterTest {

    @InjectMocks
    private MaskingConverter maskingConverter;

    @Nested
    @DisplayName("암호화")
    class EncryptTest {
        @Test
        @DisplayName("데이터베이스 저장할 때 원문 그대로 저장합니다.")
        void shouldEncryptWhenPrivacyDataSave() throws Exception {
            final String privacyData = "1234-5678-1234-5678";
            final String expected = "1234-5678-1234-5678";

            String actual = maskingConverter.convertToDatabaseColumn(privacyData);

            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("복호화")
    class DecryptTest {
        @Test
        @DisplayName("데이터베이스에서 불러올 때 암호화된 민감한 데이터를 복호화합니다.")
        void shouldDecryptWhenPrivacyDataLoad() throws Exception {
            final String privacyData = "1234-5678-1234-5678";
            final String expected = "1234-5678-****-****";

            final String actual = maskingConverter.convertToEntityAttribute(privacyData);

            assertEquals(expected, actual);
        }
    }
}
