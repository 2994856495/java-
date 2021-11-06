package exercise2.java04;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class MedianFilter {


	public static void filer(String fileIn, String fileOut){
		BufferedImage image = loadImage(fileIn);
		int[] filter = medianFilter(0, image.getHeight(), 0, image.getWidth(), image);
		createNewPng(image,fileOut,filter);
	}


	public static BufferedImage loadImage(String fileName){
		try {

			InputStream input = new FileInputStream(fileName);
			return ImageIO.read(input);
		} catch (IOException e) {
			System.out.println("加载错误");
			return loadImage(fileName);
		}
	}
//高斯滤波
	public static int[] gaussianBlur(int startY,int endY,int startX,int endX,BufferedImage image){
		int h=endY;
		int w=endX;
		int[] pix = new int[w*h];
		image.getRGB(0, 0, w, h, pix, 0, w);
		System.out.println(pix[0]);

		int[] newPix=new int[endX*endY];
		ColorModel cm=ColorModel.getRGBdefault();
		int[][] temp=new int[3][9];
		int[] tempR=new int[9];
		int[] tempG=new int[9];
		int[] tempB=new int[9];
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				if(i!=0&&i!=w-1&&j!=0&&j!=h-1){
					tempR[0] = cm.getRed(pix[i-1+(j-1)*w]);
					tempR[1] = cm.getRed(pix[i+(j-1)*w]);
					tempR[2] = cm.getRed(pix[i+1+(j-1)*w]);
					tempR[3] = cm.getRed(pix[i-1+(j)*w]);
					tempR[4] = cm.getRed(pix[i+(j)*w]);
					tempR[5] = cm.getRed(pix[i+1+(j)*w]);
					tempR[6] = cm.getRed(pix[i-1+(j+1)*w]);
					tempR[7] = cm.getRed(pix[i+(j+1)*w]);
					tempR[8] = cm.getRed(pix[i+1+(i+1)*w]);
					int a=0;
					a=(tempR[0]+tempR[2]+tempR[6]+tempR[8])*(1/16)+(tempR[1]+tempR[3]+tempR[5]+tempR[7])*(1/8)+tempR[4]*(1/4);
					tempG[0] = cm.getGreen(pix[i-1+(j-1)*w]);
					tempG[1] = cm.getGreen(pix[i+(j-1)*w]);
					tempG[2] = cm.getGreen(pix[i+1+(j-1)*w]);
					tempG[3] = cm.getGreen(pix[i-1+(j)*w]);
					tempG[4] = cm.getGreen(pix[i+(j)*w]);
					tempG[5] = cm.getGreen(pix[i+1+(j)*w]);
					tempG[6] = cm.getGreen(pix[i-1+(j+1)*w]);
					tempG[7] = cm.getGreen(pix[i+(j+1)*w]);
					tempG[8] = cm.getGreen(pix[i+1+(i+1)*w]);
					int b;
					b=(tempG[0]+tempG[2]+tempG[6]+tempG[8])*(1/16)+(tempG[1]+tempG[3]+tempG[5]+tempG[7])*(1/8)+tempG[4]*(1/4);
					tempB[0] = cm.getBlue(pix[i-1+(j-1)*w]);
					tempB[1] = cm.getBlue(pix[i+(j-1)*w]);
					tempB[2] = cm.getBlue(pix[i+1+(j-1)*w]);
					tempB[3] = cm.getBlue(pix[i-1+(j)*w]);
					tempB[4] = cm.getBlue(pix[i+(j)*w]);
					tempB[5] = cm.getBlue(pix[i+1+(j)*w]);
					tempB[6] = cm.getBlue(pix[i-1+(j+1)*w]);
					tempB[7] = cm.getBlue(pix[i+(j+1)*w]);
					tempB[8] = cm.getBlue(pix[i+1+(i+1)*w]);
					int c;
					c=(tempB[0]+tempB[2]+tempB[6]+tempB[8])*(1/16)+(tempB[1]+tempB[3]+tempB[5]+tempB[7])*(1/8)+tempB[4]*(1/4);
					newPix[j*w+i] = 255<<24 | a<<16 | b<<8 |c;
				}
				else{
					newPix[j*w+i]=pix[j*w+i];
				}
			}
		}
		return newPix;

	}

//中值滤波
	public static int[] medianFilter(int startY,int endY,int startX,int endX,BufferedImage image){
		int h=endY;
		int w=endX;
		int[] pix = new int[w*h];
		image.getRGB(0, 0, w, h, pix, 0, w);
		System.out.println(pix[0]);
//"C:\Program Files\Java\jdk-15.0.1\bin\java.exe" "-javaagent:D:\IDEA\IntelliJ IDEA 2020.1\lib\idea_rt.jar=14801:D:\IDEA\IntelliJ IDEA 2020.1\bin" -Dfile.encoding=UTF-8 -classpath "F:\study\java\out\production\java_code;D:\IDEA\IntelliJ IDEA 2020.1\lib\junit-4.12.jar;D:\IDEA\IntelliJ IDEA 2020.1\lib\hamcrest-core-1.3.jar;D:\mysql-connector-java-8.0.17.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\el-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\jasper.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\jsp-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\catalina.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\ecj-4.6.3.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\jasper-el.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\jaspic-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-jni.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\catalina-ha.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\servlet-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-dbcp.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-jdbc.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-util.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\catalina-ant.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-coyote.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\websocket-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-de.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-es.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-fr.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-ja.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-ko.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-ru.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\annotations-api.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\catalina-tribes.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-util-scan.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-websocket.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\tomcat-i18n-zh-CN.jar;D:\apache-tomcat-10.0.5-windows-x64\apache-tomcat-8.5.65\lib\catalina-storeconfig.jar" exercise03.java03.Main
		//-8355712
		//-9934744
		//-8158333
		//-10987432
		int[] newPix=new int[endX*endY];
		ColorModel cm=ColorModel.getRGBdefault();
		int[][] temp=new int[3][9];
		int[] tempR=new int[9];
		int[] tempG=new int[9];
		int[] tempB=new int[9];
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				if(i!=0&&i!=w-1&&j!=0&&j!=h-1){
					tempR[0] = cm.getRed(pix[i-1+(j-1)*w]);
                    tempR[1] = cm.getRed(pix[i+(j-1)*w]);
                    tempR[2] = cm.getRed(pix[i+1+(j-1)*w]);
                    tempR[3] = cm.getRed(pix[i-1+(j)*w]);
                    tempR[4] = cm.getRed(pix[i+(j)*w]);
                    tempR[5] = cm.getRed(pix[i+1+(j)*w]);
                    tempR[6] = cm.getRed(pix[i-1+(j+1)*w]);
                    tempR[7] = cm.getRed(pix[i+(j+1)*w]);
                    tempR[8] = cm.getRed(pix[i+1+(i+1)*w]);
                    Arrays.sort(tempR);
					tempG[0] = cm.getGreen(pix[i-1+(j-1)*w]);
					tempG[1] = cm.getGreen(pix[i+(j-1)*w]);
					tempG[2] = cm.getGreen(pix[i+1+(j-1)*w]);
					tempG[3] = cm.getGreen(pix[i-1+(j)*w]);
					tempG[4] = cm.getGreen(pix[i+(j)*w]);
					tempG[5] = cm.getGreen(pix[i+1+(j)*w]);
					tempG[6] = cm.getGreen(pix[i-1+(j+1)*w]);
					tempG[7] = cm.getGreen(pix[i+(j+1)*w]);
					tempG[8] = cm.getGreen(pix[i+1+(i+1)*w]);
					Arrays.sort(tempG);
					tempB[0] = cm.getBlue(pix[i-1+(j-1)*w]);
					tempB[1] = cm.getBlue(pix[i+(j-1)*w]);
					tempB[2] = cm.getBlue(pix[i+1+(j-1)*w]);
					tempB[3] = cm.getBlue(pix[i-1+(j)*w]);
					tempB[4] = cm.getBlue(pix[i+(j)*w]);
					tempB[5] = cm.getBlue(pix[i+1+(j)*w]);
					tempB[6] = cm.getBlue(pix[i-1+(j+1)*w]);
					tempB[7] = cm.getBlue(pix[i+(j+1)*w]);
					tempB[8] = cm.getBlue(pix[i+1+(i+1)*w]);
					Arrays.sort(tempB);
					newPix[j*w+i] = 255<<24 | tempR[4]<<16 | tempG[4]<<8 |tempB[4];
				}
				else{
					newPix[j*w+i]=pix[j*w+i];
				}
			}
		}
		return newPix;
	}

	public static void createNewPng(BufferedImage bufferedImage,String newFileName,int[] pix){
		BufferedImage outBufferedImage =new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		outBufferedImage.setRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), pix, 0, bufferedImage.getWidth());
		try {
			ImageIO.write(outBufferedImage, "png", new File(newFileName));
		} catch (IOException e) {
			createNewPng(bufferedImage,newFileName,pix);
		}
	}

	public static void main(String[] args) {
		String fileName="src\\exercise2\\java04\\ship.png";
		String fileOut="src\\exercise2\\java04\\ship2.png";
		filer(fileName,fileOut);
	}
}
