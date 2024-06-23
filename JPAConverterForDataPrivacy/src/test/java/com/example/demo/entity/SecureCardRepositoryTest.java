package com.example.demo.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import com.example.demo.entity.converter.PrivacyEncryptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SecureCardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @MockBean
    private PrivacyEncryptor privacyEncryptor;

    @Test
    @DisplayName("민감한 데이터는 암호화된다.")
    void shouldEncryptPrivacyData() throws Exception {
        // GIVEN
        String privacyHolderName = "다람쥐";
        String privacyCardNo = "1234-5678-1234-5678";

        String expectedHolderName = "nAdnq1Ns0dxn02Snasx2nx";

        when(privacyEncryptor.encrypt(privacyHolderName)).thenReturn(expectedHolderName);

        // WHEN
        SecureCard secureCard = new SecureCard(null, privacyHolderName, privacyCardNo);
        cardRepository.saveAndFlush(secureCard);

        SecureCard actualCard = cardRepository.findById(secureCard.getId()).orElse(null);

        // THEN
        assertAll(() -> {
            assertThat(actualCard).isNotNull();
            assertThat(actualCard.getHolderName()).isEqualTo(expectedHolderName); // Failed. actual : 다람쥐
        });
    }

    @Test
    @DisplayName("민감한 데이터는 마스킹된다.")
    void shouldMaskPrivacyData() throws Exception {
        // GIVEN
        String holderName = "다람쥐";
        String cardNo = "1234-5678-1234-5678";

        String expected = "1234-5678-****-****";

        // WHEN
        SecureCard secureCard = new SecureCard(null, holderName, cardNo);
        cardRepository.saveAndFlush(secureCard);

        SecureCard actualCard = cardRepository.findById(secureCard.getId())
                .orElse(null);

        // THEN
        assertSoftly(it -> {
            it.assertThat(actualCard.getCardNo()).isEqualTo(expected); // Failed. actual : 1234-5678-1234-5678
        });
    }
}
