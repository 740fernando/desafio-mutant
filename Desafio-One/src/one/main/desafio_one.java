package one.main;

import one.utils.ConstantsUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class desafio_one {
    public static void main(String[] args) {
        processPrincipal();
    }

    private static void processPrincipal() {
        try{
            processInternalSystem();
        }catch (NumberFormatException e){
            processError();
        }
    }

    private static void processInternalSystem() {
        int[][] imageBitmap = processImage();
        Integer vectorLength = insertLength();
        verifyLengthIsValid(vectorLength);
        Integer[] vectorValuesForSearch = new Integer[vectorLength];
        inputValueInVector(vectorLength, vectorValuesForSearch);
        Integer[] counter = new Integer[vectorLength];
        processValuesInVectorEqualsValuesInMatrix(imageBitmap, vectorLength, vectorValuesForSearch, counter);
        printResult(vectorLength, vectorValuesForSearch, counter);
    }

    private static void processError() {
        int response = JOptionPane.showConfirmDialog (
                null,
                ConstantsUtils.MESSAGE_ERROR_QUESTION,
                ConstantsUtils.MESSAGE_TITTLE_ERROR_QUESTION,
                JOptionPane.YES_NO_OPTION);
        if ((response == JOptionPane.YES_OPTION)) {
            processPrincipal();
        } else {
            System.out.println(ConstantsUtils.MESSAGE_FINISH);
        }
    }

    private static int[][] processImage() {
        File logo = new File(ConstantsUtils.LOGO);
        BufferedImage img;
        try {
            img = ImageIO.read(logo);
            int[][] imagemBitmap = new int[img.getWidth()][img.getHeight()];
            for (int x = 0; x < img.getHeight(); x++) {
                for (int y = 0; y < img.getWidth(); y++) {
                    imagemBitmap[x][y] = img.getRGB(x, y);
                }
            }
            return imagemBitmap;
        } catch (IOException e) {
            throw new RuntimeException(ConstantsUtils.MESSAGE_ERROR);
        }
    }

    private static Integer insertLength() {
        String valueString = JOptionPane.showInputDialog(ConstantsUtils.MESSAGE_INPUT);
        Integer valueInteger = Integer.valueOf(valueString);
        if (valueInteger >= 0) {
            return valueInteger;
        }
        return insertLength();
    }

    private static void verifyLengthIsValid(Integer vectorLength) {
        while (isLengthValid(vectorLength)) {
            JOptionPane.showMessageDialog(null, ConstantsUtils.MESSAGE_NUMBER_INVALID);
            vectorLength = insertLength();
        }
    }

    private static void inputValueInVector(
            Integer vectorLength,
            Integer[] vectorValuesForSearch) {
        for (int x = 0; x < vectorLength; x++) {
            vectorValuesForSearch[x] =
                    Integer.valueOf(
                            JOptionPane.showInputDialog(
                                    new StringBuffer(String.valueOf(x))
                                            .append(ConstantsUtils.MESSAGE_POSITION).toString()));
        }
    }

    private static void processValuesInVectorEqualsValuesInMatrix(
            int[][] imageBitmap,
            Integer vectorLength,
            Integer[] vectorValuesForSearch,
            Integer[] counter) {
        for (int x = 0; x < vectorLength; x++) {
            for (int y = 0; y < imageBitmap.length; y++) {
                for (int z = 0; z < imageBitmap[x].length; z++) {
                    if (vectorValuesForSearch[x].equals(imageBitmap[x][y])) {
                        counter[x] = (Objects.isNull(counter[x]))
                                ? 1
                                : counter[x] + 1;
                    } else if (Objects.isNull(counter[x])) {
                        counter[x] = 0;
                    }
                }
            }
        }
    }

    private static void printResult(
            Integer vectorLength,
            Integer[] vectorValuesForSearch,
            Integer[] counter) {
        for (int i = 0; i < vectorLength; i++) {
            JOptionPane.showMessageDialog(null,
                    new StringBuffer(ConstantsUtils.MESSAGE_VECTOR_POSITION)
                            .append(i)
                            .append(ConstantsUtils.MESSAGE_WITH_VALUE)
                            .append(vectorValuesForSearch[i])
                            .append(ConstantsUtils.MESSAGE_TOTAL)
                            .append(counter[i])
                            .append(ConstantsUtils.MESSAGE_OCCURRENCES).toString());
        }
    }

    private static boolean isLengthValid(Integer vectorLength) {
        return !(vectorLength > 0 && vectorLength <= 15);
    }
}
