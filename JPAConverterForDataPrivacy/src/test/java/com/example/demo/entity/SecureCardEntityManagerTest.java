package com.example.demo.entity;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.Mockito.when;

import com.example.demo.entity.converter.PrivacyEncryptor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SecureCardEntityManagerTest {
    @Autowired
    private TestEntityManager entityManager;

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
        when(privacyEncryptor.decrypt(expectedHolderName)).thenReturn(privacyHolderName);

        // WHEN
        SecureCard secureCard = new SecureCard(null, privacyHolderName, privacyCardNo);

        entityManager.persist(secureCard);
        entityManager.flush();
        entityManager.clear();

        EntityManager em = entityManager.getEntityManager();

        TypedQuery<SecureCard> query = em.createQuery(
                "SELECT id, holderName, cardNo From SecureCard WHERE id = :id",
                SecureCard.class);
        query.setParameter("id", secureCard.getId());
        SecureCard actualCard = query.getSingleResult();

        // THEN
        assertSoftly(it -> {
            it.assertThat(actualCard).isNotNull();
            it.assertThat(actualCard.getHolderName()).isEqualTo(expectedHolderName); // Failed. actual : 다람쥐
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

        entityManager.persist(secureCard);
        entityManager.flush();
        entityManager.clear();

        SecureCard actualCard = entityManager.find(SecureCard.class, secureCard.getId());

        // THEN
        assertSoftly(it -> {
            it.assertThat(actualCard.getCardNo()).isEqualTo(expected);
        });
    }
}
