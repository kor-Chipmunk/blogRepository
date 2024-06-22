package com.example.demo.entity.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EncryptedConverterTest {

    @InjectMocks
    private EncryptedConverter privacyConverter;

    @Mock
    private PrivacyEncryptor privacyEncryptor;

    @Nested
    @DisplayName("암호화")
    class EncryptTest {
        @Test
        @DisplayName("데이터베이스 저장할 때 민감한 데이터를 암호화합니다.")
        void shouldEncryptWhenSave() throws Exception {
            final String privacyData = "사적이고 민감한 데이터입니다.";
            final String expected = "dNd1dns21nAZpa2nxmaA590";

            when(privacyEncryptor.encrypt(privacyData)).thenReturn(expected);

            String actual = privacyConverter.convertToDatabaseColumn(privacyData);

            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("복호화")
    class DecryptTest {
        @Test
        @DisplayName("데이터베이스에서 불러올 때 암호화된 민감한 데이터를 복호화합니다.")
        void shouldDecryptWhenLoad() throws Exception {
            final String encrypted = "dNd1dns21nAZpa2nxmaA590";
            final String expected = "사적이고 민감한 데이터입니다.";

            when(privacyEncryptor.decrypt(encrypted)).thenReturn(expected);

            final String actual = privacyConverter.convertToEntityAttribute(encrypted);

            assertEquals(expected, actual);
        }
    }
}
