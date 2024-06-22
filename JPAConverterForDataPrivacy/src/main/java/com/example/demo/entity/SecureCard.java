package com.example.demo.entity;

import com.example.demo.entity.converter.EncryptedConverter;
import com.example.demo.entity.converter.MaskingConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SecureCard {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = EncryptedConverter.class)
    private String holderName;

    @Convert(converter = MaskingConverter.class)
    private String cardNo;
}
