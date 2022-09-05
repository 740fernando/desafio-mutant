package com.mutant.api.service.impl;

import com.mutant.api.model.entities.response.BitmapResponse;
import com.mutant.api.service.BitmapService;
import com.mutant.api.utils.ConstantsUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class BitmapServiceImpl implements BitmapService  {

    @Override
    public BitmapResponse getImageMatrix() {
        File logo = new File(ConstantsUtils.LOGO);
        var response = new BitmapResponse();
        try {
            BufferedImage img;
            img = ImageIO.read(logo);
            int[][] imagemBitmap = new int[img.getWidth()][img.getHeight()];
            for (int x = 0; x < img.getHeight(); x++) {
                for (int y = 0; y < img.getWidth(); y++) {
                    imagemBitmap[x][y] = img.getRGB(x, y);
                }
            }
            response.setImageBitmap(imagemBitmap);
            return response;
        } catch (IOException e) {
            throw new RuntimeException(ConstantsUtils.MESSAGE_ERROR);
        }
    }
}
