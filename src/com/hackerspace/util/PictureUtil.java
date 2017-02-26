package com.hackerspace.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.collections.map.StaticBucketMap;

import com.hackerspace.model.Picture;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PictureUtil {
	
	public static boolean submitPhoto(Picture p,double boxSize) {
		BufferedImage bi;
		String imgPath =p.getOldPictureUrl();
		String savePath =p.getPictureUrl();

		String filePath=savePath.substring(0, savePath.lastIndexOf(File.separator));
		File file=new File(filePath);
		if(!file.exists()) {
			file.mkdir();
		}
		double x=p.getX();
		double y=p.getY();
		double w=p.getW();
		double h=p.getH();
		try {
			bi = ImageIO.read(new File(imgPath));
			if(bi.getHeight()>bi.getWidth()) {
				x=x*bi.getHeight()/boxSize;
				y=y*bi.getHeight()/boxSize;
				w=w*bi.getHeight()/boxSize;
				h=h*bi.getHeight()/boxSize;
			} else {
				x=x*bi.getWidth()/boxSize;
				y=y*bi.getWidth()/boxSize;
				w=w*bi.getWidth()/boxSize;
				h=h*bi.getWidth()/boxSize;
			}
			Image image=bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_DEFAULT);
			BufferedImage newBi=new BufferedImage((int)w, (int)h, BufferedImage.TYPE_INT_RGB);
			
			 CropImageFilter cropFilter = new CropImageFilter((int)x, (int)y, (int)w, (int)h);  
			 Image img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));  
			 Graphics g=newBi.getGraphics();
			g.drawImage(img,0,0,null);//绘制缩略图
			g.dispose();
			File newImage=new File(savePath);//保存文件的路径
			ImageIO.write(newBi, "JPEG", newImage);
		
			//压缩图片  
	        try {  
	            /** 对服务器上的临时文件进行处理 */  
	            Image srcFile = ImageIO.read(newImage);  
	            int width = srcFile.getWidth(null);  
	        //  System.out.println(w);  
	            int height = srcFile.getHeight(null);  
	        //  System.out.println(h);  
	  
	            /** 宽,高设定 */  
	           final int big=3;//适当的放大
	           /* BufferedImage tag = new BufferedImage((int)bi.getWidth()*big,(int) bi.getHeight()*big,BufferedImage.TYPE_INT_RGB);  
	            tag.getGraphics().drawImage(srcFile, 0, 0, (int)bi.getWidth()*big, (int)bi.getHeight()*big, null);  */
	           BufferedImage tag=new BufferedImage(width*big,height*big,BufferedImage.TYPE_INT_RGB);
	           tag.getGraphics().drawImage(srcFile, 0, 0, width*big, height*big, null);
	            /** 压缩之后临时存放位置 */  
	            FileOutputStream out = new FileOutputStream(newImage);  
	  
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
	            /** 压缩质量 */  
	            jep.setQuality(1.0f, true);  
	            encoder.encode(tag, jep);  
	            out.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
