package xulyanh;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Image_Processing {

    int[] chartGrayscale(BufferedImage image) throws IOException {
        image = grayImage(image);
        int[] arr = new int[255];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int number = color.getRed();
                arr[number]++;
            }
        }
        return arr;
    }

    BufferedImage negativeImage(BufferedImage image) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage negativeGrayImage(BufferedImage image) throws IOException {
        image = grayImage(image);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                Color newColor = new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage thresholdImage(BufferedImage image, int threshold) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int x = (color.getRed() > threshold) ? 255 : 0;
                int y = (color.getGreen() > threshold) ? 255 : 0;
                int z = (color.getBlue() > threshold) ? 255 : 0;
                Color newColor = new Color(x, y, z);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage logaritImage(BufferedImage image, int c) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = c * (int) Math.log(color.getRed() + 1);
                int green = c * (int) Math.log(color.getGreen() + 1);
                int blue = c * (int) Math.log(color.getBlue() + 1);
                Color newColor = new Color(red, green, blue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage power_lawImage(BufferedImage image, int gamma, int c) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = (int) (Math.pow((color.getRed() / 255.), gamma) * c);
                int green = (int) (Math.pow((color.getGreen() / 255.), gamma) * c);
                int blue = (int) (Math.pow((color.getBlue() / 255.), gamma) * c);
                Color newColor = new Color(red, green, blue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }



    BufferedImage bitPlaneSlicing(BufferedImage image, int constant) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = color.getRed() | (int) (Math.pow(2, constant));
                int green = color.getGreen() | (int) (Math.pow(2, constant));
                int blue = color.getBlue() | (int) (Math.pow(2, constant));
                Color newColor = new Color(red, green, blue);
                image.setRGB(i, j, newColor.getRGB());

            }
        }
        return image;
    }


    BufferedImage frequencyBalancingImage(BufferedImage image, float new_lever) throws IOException {
        image = grayImage(image);
        int[] arr_G = chartGrayscale(image); // Mảng biểu đồ mức xám
        float TB = (image.getWidth() * image.getHeight()) / new_lever;
        int[] arr_hG = new int[arr_G.length];
        int[] arr_fG = new int[arr_G.length];

        for (int i = 0; i < arr_G.length; i++) {
            if (arr_G[i] == 0)
                continue;
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += arr_G[j];
            }
            arr_hG[i] = sum;
            arr_fG[i] = (int) Math.max(0, Math.round(arr_hG[i] / TB) - 1);
        }

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int number = color.getRed();
                Color newColor = new Color(arr_fG[number], arr_fG[number], arr_fG[number]);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage grayImage(BufferedImage image) throws IOException {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int red = (int) (color.getRed() * 0.299);
                int green = (int) (color.getGreen() * 0.587);
                int blue = (int) (color.getBlue() * 0.114);
                Color newColor = new Color(red + green + blue, red + green + blue, red + green + blue);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage pointDetection(BufferedImage image,int threshold) throws IOException {
        for (int i = 0; i < image.getWidth() - 2; i++) {
            for (int j = 0; j < image.getHeight() - 2; j++) {
                image.setRGB(i, j, getMatrixPoint(image, i, j).getRGB());
            }
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int x = (color.getRed() > threshold) ? 255 : 0;
                int y = (color.getGreen() > threshold) ? 255 : 0;
                int z = (color.getBlue() > threshold) ? 255 : 0;
                Color newColor = new Color(x, y, z);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }

    BufferedImage lineDectecion(BufferedImage image, int threshold){
        for (int i = 0; i < image.getWidth() - 2; i++) {
            for (int j = 0; j < image.getHeight() - 2; j++) {
                image.setRGB(i, j, getMatrixLine(image, i, j).getRGB());
            }
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                int x = (color.getRed() > threshold) ? 255 : 0;
                int y = (color.getGreen() > threshold) ? 255 : 0;
                int z = (color.getBlue() > threshold) ? 255 : 0;
                Color newColor = new Color(x, y, z);
                image.setRGB(i, j, newColor.getRGB());
            }
        }

        return image;
    }

    BufferedImage robertImage(BufferedImage image) throws IOException {
        for (int i = 0; i < image.getWidth() - 1; i++) {
            for (int j = 0; j < image.getHeight() - 1; j++) {
                image.setRGB(i, j, getColorRobert(image, i, j).getRGB());
            }
        }

        return image;
    }

    BufferedImage sobelImage(BufferedImage image) throws IOException {
        for (int i = 0; i < image.getWidth() - 2; i++) {
            for (int j = 0; j < image.getHeight() - 2; j++) {
                image.setRGB(i, j, getColorSobel(image, i, j).getRGB());
            }
        }
        return image;
    }

    BufferedImage prewittImage(BufferedImage image) throws IOException {
        for (int i = 0; i < image.getWidth() - 2; i++) {
            for (int j = 0; j < image.getHeight() - 2; j++) {
                image.setRGB(i, j, getColorPrewitt(image, i, j).getRGB());
            }
        }
        return image;
    }
    Color getColorRobert(BufferedImage image, int i, int j) {
        Color[][] color = new Color[2][2];
        int[][] hX = { { 0, -1}, { 1, 0 }};
        int[][] hY = { { -1, 0 }, { 0, 1 } };
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 2; u++) {
            for (int v = 0; v < 2; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (hX[u][v]) + color[u][v].getRed() * (hY[u][v]);
                green += color[u][v].getGreen() * (hX[u][v]) + color[u][v].getGreen() * (hY[u][v]);
                blue += color[u][v].getBlue() * (hX[u][v]) + color[u][v].getBlue() * (hY[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }
    Color getColorSobel(BufferedImage image, int i, int j) {
        Color[][] color = new Color[3][3];
        int[][] hX = { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
        int[][] hY = { { -1, -2, -1 }, { 0, 0, 0 }, { 1, 2, 1 } };
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 3; u++) {
            for (int v = 0; v < 3; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (hX[u][v]) + color[u][v].getRed() * (hY[u][v]);
                green += color[u][v].getGreen() * (hX[u][v]) + color[u][v].getGreen() * (hY[u][v]);
                blue += color[u][v].getBlue() * (hX[u][v]) + color[u][v].getBlue() * (hY[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }

    Color getColorPrewitt(BufferedImage image, int i, int j) {
        Color[][] color = new Color[3][3];
        int[][] hX = { { -1, 0, 1 }, { -1, 0, 1 }, { -1, 0, 1 } };
        int[][] hY = { { -1, -1, -1 }, { 0, 0, 0 }, { 1, 1, 1 } };
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 3; u++) {
            for (int v = 0; v < 3; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (hX[u][v]) + color[u][v].getRed() * (hY[u][v]);
                green += color[u][v].getGreen() * (hX[u][v]) + color[u][v].getGreen() * (hY[u][v]);
                blue += color[u][v].getBlue() * (hX[u][v]) + color[u][v].getBlue() * (hY[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }
    BufferedImage laplacian_Gaussian(BufferedImage image){
        for (int i = 0; i < image.getWidth() - 4; i++) {
            for (int j = 0; j < image.getHeight() - 4; j++) {
                image.setRGB(i, j, getColor5X5(image, i, j).getRGB());
            }
        }
        return image;

    }

    Color getMatrixPoint(BufferedImage image, int i, int j) {
        Color[][] color = new Color[3][3];
        int[][] hX = { { -1,-1,-1}, { -1,8,-1}, { -1,-1,-1} };
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 3; u++) {
            for (int v = 0; v < 3; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (hX[u][v]);
                green += color[u][v].getGreen() * (hX[u][v]);
                blue += color[u][v].getBlue() * (hX[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }
    Color getMatrixLine(BufferedImage image, int i, int j) {
        Color[][] color = new Color[3][3];
        int[][] hX = { { 2,-1,-1 }, { -1,2,-1}, { -1,-1,2} };
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 3; u++) {
            for (int v = 0; v < 3; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (hX[u][v]);
                green += color[u][v].getGreen() * (hX[u][v]);
                blue += color[u][v].getBlue() * (hX[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }

    Color getColor5X5(BufferedImage image, int i, int j) {
        Color[][] color = new Color[5][5];
        int[][] m = { { 0, 0, -1,0,0 }, { 0, -1, -2,-1,0 }, { -1,-2,16,-2,-1 },{ 0, -1, -2,-1,0 },{ 0, 0, -1,0,0 }};
        int red = 0;
        int green = 0;
        int blue = 0;

        for (int u = 0; u < 5; u++) {
            for (int v = 0; v < 5; v++) {
                color[u][v] = new Color(image.getRGB(u + i, v + j));
                red += color[u][v].getRed() * (m[u][v]) ;
                green += color[u][v].getGreen() * (m[u][v]);
                blue += color[u][v].getBlue() * (m[u][v]);
            }
        }
        if (Math.abs(red) > 255 || Math.abs(blue) > 255 || Math.abs(green) > 255) {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new Color(Math.abs(red), Math.abs(green), Math.abs(blue));
    }

    BufferedImage autoThreshold(BufferedImage img){

        int thresholdValue = 127;
        int iThreshold;
//         * sum1 giữ tổng giá trị avgOfRGB nhỏ hơn ngưỡng Value.
//         * sum2 giữ tổng giá trị avgOfRGB lớn hơn hoặc bằng ngưỡng Value.
//         * Count1 giữ số lượng pixel có giá trị avgOfRGB nhỏ hơn ngưỡng Value.
//         * Count2 giữ số pixel có giá trị avgOfRGB lớn hơn hoặc bằng ngưỡng Value.

        int sum1, sum2, count1, count2;
        int mean1, mean2;

        /** tinh thresholdValue */
        while(true){
            sum1 = sum2 = count1 = count2 = 0;
            for(int y = 0; y < img.getHeight(); y++){
                for(int x = 0; x < img.getWidth(); x++){
                    Color color= new Color(img.getRGB(x, y));
                    int r = color.getRed() ;
                    int g = color.getGreen();
                    int b = color.getBlue();
                    int avgOfRGB = (r+g+b)/3;

                    if(avgOfRGB < thresholdValue){
                        sum1 += avgOfRGB;
                        count1++;
                    }else{
                        sum2 += avgOfRGB;
                        count2++;
                    }
                }
            }
            mean1 = (count1 > 0)?(int)(sum1/count1):0;
            mean2 = (count2 > 0)?(int)(sum2/count2):0;
            iThreshold = (mean1 + mean2)/2;

            if(thresholdValue != iThreshold){
                thresholdValue = iThreshold;
            }else{
                break;//thoat vòng lặp nếu không còn chênh lệch giữa value và ngưỡng mới
            }
        }


        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                Color color = new Color(img.getRGB(x, y));
                int r = (color.getRed() > thresholdValue) ? 255 : 0;
                int g = (color.getGreen() > thresholdValue) ? 255 : 0;
                int b = (color.getBlue() > thresholdValue) ? 255 : 0;
                Color newColor = new Color(r, g, b);
                img.setRGB(x, y, newColor.getRGB());
            }
        }
        return img;
    }

    BufferedImage adaptiveThreshold_Median(BufferedImage img, int maskSize){

        int width = img.getWidth();
        int height = img.getHeight();

        //variables
        int blue[], median, count;


        /** tìm trung vị và ngưỡng pixel */
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                blue = new int[maskSize*maskSize];
                count = 0;
                for(int r = y - (maskSize / 2); r <= y + (maskSize / 2); r++){
                    for(int c = x - (maskSize / 2); c <= x + (maskSize / 2); c++){
                        if(r < 0 || r >= height || c < 0 || c >= width){
                            continue;
                        }else{
                            try{
                                Color color= new Color(img.getRGB(c, r));
                                blue[count] = color.getBlue();
                                count++;
                            }catch(ArrayIndexOutOfBoundsException e){
                            }
                        }
                    }
                }


                java.util.Arrays.sort(blue);

                /**lấy giá trị pixel trung bình */
                median = blue[count/2];

                Color color= new Color(img.getRGB(x, y));
                int b = (color.getBlue() > median) ? 255 : 0;
                Color newColor = new Color(b, b, b);
                img.setRGB(x,y,newColor.getRGB());
            }
        }
        return img;
    }
    BufferedImage minNeighbourhoodImage(BufferedImage image) throws IOException {
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];

        for(int i=1;i<image.getWidth()-1;i++)
            for(int j=1;j<image.getHeight()-1;j++)
            {
                pixel[0]=new Color(image.getRGB(i-1,j-1));
                pixel[1]=new Color(image.getRGB(i-1,j));
                pixel[2]=new Color(image.getRGB(i-1,j+1));
                pixel[3]=new Color(image.getRGB(i,j+1));
                pixel[4]=new Color(image.getRGB(i+1,j+1));
                pixel[5]=new Color(image.getRGB(i+1,j));
                pixel[6]=new Color(image.getRGB(i+1,j-1));
                pixel[7]=new Color(image.getRGB(i,j-1));
                pixel[8]=new Color(image.getRGB(i,j));
                for(int k=0;k<9;k++){
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                image.setRGB(i,j,new Color(R[0],B[0],G[0]).getRGB());
            }

        return image;
    }

    BufferedImage maxNeighbourhoodImage(BufferedImage image) throws IOException {
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];

        for(int i=1;i<image.getWidth()-1;i++)
            for(int j=1;j<image.getHeight()-1;j++)
            {
                pixel[0]=new Color(image.getRGB(i-1,j-1));
                pixel[1]=new Color(image.getRGB(i-1,j));
                pixel[2]=new Color(image.getRGB(i-1,j+1));
                pixel[3]=new Color(image.getRGB(i,j+1));
                pixel[4]=new Color(image.getRGB(i+1,j+1));
                pixel[5]=new Color(image.getRGB(i+1,j));
                pixel[6]=new Color(image.getRGB(i+1,j-1));
                pixel[7]=new Color(image.getRGB(i,j-1));
                pixel[8]=new Color(image.getRGB(i,j));
                for(int k=0;k<9;k++){
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                image.setRGB(i,j,new Color(R[8],B[8],G[8]).getRGB());
            }

        return image;
    }

    BufferedImage medianImage(BufferedImage image) throws IOException {
        Color[] pixel=new Color[9];
        int[] R=new int[9];
        int[] B=new int[9];
        int[] G=new int[9];

        for(int i=1;i<image.getWidth()-1;i++)
            for(int j=1;j<image.getHeight()-1;j++)
            {
                pixel[0]=new Color(image.getRGB(i-1,j-1));
                pixel[1]=new Color(image.getRGB(i-1,j));
                pixel[2]=new Color(image.getRGB(i-1,j+1));
                pixel[3]=new Color(image.getRGB(i,j+1));
                pixel[4]=new Color(image.getRGB(i+1,j+1));
                pixel[5]=new Color(image.getRGB(i+1,j));
                pixel[6]=new Color(image.getRGB(i+1,j-1));
                pixel[7]=new Color(image.getRGB(i,j-1));
                pixel[8]=new Color(image.getRGB(i,j));
                for(int k=0;k<9;k++){
                    R[k]=pixel[k].getRed();
                    B[k]=pixel[k].getBlue();
                    G[k]=pixel[k].getGreen();
                }
                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                image.setRGB(i,j,new Color(R[4],B[4],G[4]).getRGB());
            }

        return image;
    }

    BufferedImage smoothingImage(BufferedImage image) throws IOException {

        BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        image_origin.setData(image.getData());
        Color[] color = new Color[9];
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = 1; j < image.getHeight() - 1; j++) {
                color = getMatrixColor(image_origin, i, j);
                int smoothing_Red = 0;
                int smoothing_Green = 0;
                int smoothing_Blue = 0;
                for (int u = 0; u < 9; u++) {
                    smoothing_Red += color[u].getRed();
                    smoothing_Green += color[u].getGreen();
                    smoothing_Blue += color[u].getBlue();
                }
                Color newColor = new Color(smoothing_Red / 9, smoothing_Green / 9, smoothing_Blue / 9);
                image.setRGB(i, j, newColor.getRGB());
            }
        }

        return image;

    }

    BufferedImage weightSmoothingImage(BufferedImage image) throws IOException {
        BufferedImage image_origin = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        image_origin.setData(image.getData());
        Color[] color = new Color[9];
        for (int i = 1; i < image.getWidth() - 1; i++) {
            for (int j = 1; j < image.getHeight() - 1; j++) {
                color = getMatrixColor(image_origin, i, j);
                int weight_Smoothing_Red = 0;
                int weight_Smoothing_Green = 0;
                int weight_Smoothing_Blue = 0;
                int heso;
                for (int u = 0; u < 9; u++) {
                    if (u == 4) { // Lấy vị trí trung tâm ma trận 3x3
                        heso = 4;
                    } else if (u % 2 == 0) { // Các vị trí 0 2 6 8
                        heso = 1;
                    } else { // Các vị trí 1 3 5 7
                        heso = 2;
                    }
                    weight_Smoothing_Red += color[u].getRed() * heso;
                    weight_Smoothing_Green += color[u].getGreen() * heso;
                    weight_Smoothing_Blue += color[u].getBlue() * heso;
                }
                Color newColor = new Color(weight_Smoothing_Red / 16, weight_Smoothing_Green / 16,
                        weight_Smoothing_Blue / 16);
                image.setRGB(i, j, newColor.getRGB());
            }
        }
        return image;
    }
    Color[] getMatrixColor(BufferedImage image, int i, int j) {
        Color[] color = new Color[9];
        color[0] = new Color(image.getRGB(i - 1, j - 1));
        color[1] = new Color(image.getRGB(i, j - 1));
        color[2] = new Color(image.getRGB(i + 1, j - 1));
        color[3] = new Color(image.getRGB(i - 1, j));
        color[4] = new Color(image.getRGB(i, j));
        color[5] = new Color(image.getRGB(i + 1, j));
        color[6] = new Color(image.getRGB(i - 1, j + 1));
        color[7] = new Color(image.getRGB(i, j + 1));
        color[8] = new Color(image.getRGB(i + 1, j + 1));
        return color;
    }

    void save_Image(BufferedImage image, String nameImage, String typeImage) throws IOException {
        ImageIO.write(image, typeImage, new File(nameImage + '.' + typeImage));
        JOptionPane.showMessageDialog(null, "Lưu file thành công", "Lưu File", JOptionPane.INFORMATION_MESSAGE);
    }

}
