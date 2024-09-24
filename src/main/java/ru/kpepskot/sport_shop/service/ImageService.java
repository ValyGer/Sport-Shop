package ru.kpepskot.sport_shop.service;

import java.io.InputStream;

public interface ImageService {
    void upload(String imagePath, InputStream content);
}
