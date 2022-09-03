package one;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class desafio_one {
    public static void main(String[] args) {
        processImage();
        Integer vectorLength = insertLength();
        verifyLengthIsValid(vectorLength);
        Integer[] vectorValuesForSearch = new Integer[vectorLength];
        for (int x = 0 ; x < vectorLength ; x++){
            vectorValuesForSearch[x] =  Integer.valueOf(JOptionPane.showInputDialog(new StringBuffer(String.valueOf(x+1)).append("º posicao do vetor").toString())) ;
        }
        
    }

    private static void processImage() {
        File logo = new File("download.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(logo);
        } catch (IOException e) {
            throw new RuntimeException("bitmap inexistente");
        }
        int[][] imagemBitmap = new int[img.getWidth()][img.getHeight()];
        insertValueIntoTHeMatrixBitmap(img, imagemBitmap);
    }

    private static void insertValueIntoTHeMatrixBitmap(BufferedImage img, int[][] imagemBitmap) {
        for(int x = 0; x < img.getHeight(); x++){
            for (int y = 0; y < img.getWidth(); y++){
                imagemBitmap[x][y] = img.getRGB(x,y);
            }
        }
    }

    private static Integer insertLength() {
        return Integer.valueOf(inputValueInShowMessage());
    }

    private static String inputValueInShowMessage() {
        String value = JOptionPane.showInputDialog("Digite o tamanho do vetor : min 0, max : 15");
        if( Integer.valueOf(value) >= 0 ){
            return value;
        }
        return inputValueInShowMessage();
    }

    private static boolean isLengthValid(Integer vectorLength) {
        return !(vectorLength > 0 && vectorLength <= 15);
    }

    private static void verifyLengthIsValid(Integer vectorLength) {
        while(isLengthValid(vectorLength)){
            JOptionPane.showMessageDialog(null,"Numero invalido");
            vectorLength = insertLength();
        }
    }
}
