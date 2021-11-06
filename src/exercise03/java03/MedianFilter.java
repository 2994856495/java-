package exercise03.java03;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Arrays;

public class MedianFilter {
	private static final ColorModel cm=ColorModel.getRGBdefault();
//R 0   G 1  B  2
	private static int getNewPix(int i,int j,int w,int[] index,int[] pix){
		int r=get(i,j,w,index,pix,0);
		int g=get(i,j,w,index,pix,1);
		int b=get(i,j,w,index,pix,2);
		return ( 255<<24 | r<<16 | g<<8 |b);
	}
	private static int get(int i,int j,int w,int[] index,int[] pix,int mode){
		int[] data={i-1+(j-1)*w,i+(j-1)*w,i+1+(j-1)*w,
				i-1+(j)*w,i+(j)*w,i+1+(j)*w,
				i-1+(j+1)*w,i+(j+1)*w,i+1+(j+1)*w};
		int length=index.length;
		int[] ints=new int[length];
		switch (mode){
			case 0:
				for (int m=0;m<length;m++){
					ints[m]=cm.getRed(pix[data[index[m]]]);
				}
				break;
			case 1:
				for (int m=0;m<length;m++){
					ints[m]=cm.getGreen(pix[data[index[m]]]);
				}
				break;
			case 2:
				for (int m=0;m<length;m++){
					ints[m]=cm.getBlue(pix[data[index[m]]]);
				}
				break;
			default:break;
		}
		Arrays.sort(ints);
		if (length%2==0){
			return (ints[length/2]+ints[length/2-1])/2;
		}
		else {
			return ints[length/2];
		}
	}


//中值滤波

	public static int[] medianFilter(int startX,int startY,int w,int h,BufferedImage image){
		int[] pix =new int[w*h];
		image.getRGB(startX, startY, w, h, pix, 0, w);
		int[] newPix=new int[w*h];
		for(int i=0;i<w;i++){
			for(int j=0;j<h;j++){
				//上
				if (i!=0&&i!=w-1&&j==0){
					int[] index={3,5,6,7,8,4};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				//下
				else if (i!=0&&i!=w-1&&j==h-1){
					int[] index={0,1,2,3,4,5};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				//左
				else if(i==0&&j!=0&&j!=h-1){
					int[] index={1,2,4,5,7,8};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				//右
				else if(i==w-1&&j!=0&&j!=h-1){
					int[] index={0,1,3,4,6,7};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				//中
				else if(i!=0&&i!=w-1&&j!=0&&j!=h-1){
					int[] index={0,1,2,3,4,5,6,7,8};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}//四个节点
				else if(i==0&&j==0){
					int[] index={4,5,7,8};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				else if(j==0&&i==w-1){
					int[] index={3,4,6,7};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				else if(j==h-1&&i==0){
					int[] index={1,2,4,5};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				else if(j==h-1&&i==w-1){
					int[] index={0,1,3,4};
					newPix[j*w+i] = getNewPix(i,j,w,index,pix);
				}
				else{
					newPix[j*w+i]=pix[j*w+i];
				}
			}
		}
		return newPix;
	}





}
