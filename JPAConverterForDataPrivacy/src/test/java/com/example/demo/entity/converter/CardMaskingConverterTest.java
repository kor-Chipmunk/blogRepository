package com.example.demo.entity.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardMaskingConverterTest {

    @InjectMocks
    private CardMaskingConverter cardMaskingConverter;

    @Nested
    @DisplayName("암호화")
    class EncryptTest {
        @Test
        @DisplayName("데이터베이스 저장할 때 원문 그대로 저장합니다.")
        void shouldSaveOriginalWhenSave() throws Exception {
            final String privacyData = "1234-5678-1234-5678";
            final String expected = "1234-5678-1234-5678";

            String actual = cardMaskingConverter.convertToDatabaseColumn(privacyData);

            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("복호화")
    class DecryptTest {
        @Test
        @DisplayName("데이터베이스에서 불러올 때 카드 정보를 마스킹합니다.")
        void shouldMaskCardNoWhenLoad() throws Exception {
            final String privacyData = "1234-5678-1234-5678";
            final String expected = "1234-5678-****-****";

            final String actual = cardMaskingConverter.convertToEntityAttribute(privacyData);

            assertEquals(expected, actual);
        }
    }
}
