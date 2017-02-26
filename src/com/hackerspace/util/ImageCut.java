package com.hackerspace.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hackerspace.model.Picture;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCut {  
    
	/**
	 * @param request
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param map
	 * @return
	 */
    public boolean cutImage(Picture p){  
        try {  
            Image img;  
            int x1,y1,w1,h1;//存图实际的起点和宽高
            ImageFilter imageFilter;//过滤格式
            
            //原图的路径
    		String opu=p.getOldPictureUrl();
    		//存放的路径
    		String pu=p.getPictureUrl();
            // 读取源图像  
    		if(opu==null) return false;
            BufferedImage bi = ImageIO.read(new File(opu));   
            int srcWidth = bi.getWidth();      // 源图宽度  
            int srcHeight = bi.getHeight();    // 源图高度  
              
            //若原图大小大于切片大小，则进行切割  
            if (srcWidth >= p.getW() && srcHeight >= p.getH()) {  
                Image image = bi.getScaledInstance(srcWidth, srcHeight,Image.SCALE_DEFAULT);  
                	
         
                if(p.getW()<=1||p.getH()<=1)
                {
                	x1=0;
                	y1=0;
                	w1=p.getWid();
                	h1=p.getHei();
                	
                } else {
					x1 = p.getX()*srcWidth/p.getWid();  
					y1 = p.getY()*srcHeight/p.getHei();  
					w1 = p.getW()*srcWidth/p.getWid();  
					h1 = p.getH()*srcHeight/p.getHei();                
                }
                imageFilter = new CropImageFilter(x1, y1, w1, h1);  
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imageFilter));  
                BufferedImage tag = new BufferedImage(w1, h1,BufferedImage.TYPE_INT_RGB);  
                Graphics g = tag.getGraphics();  
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图  
                g.dispose();  
                // 重新进行压缩
                File t=new File(pu);
                ImageIO.write(tag, "JPEG", t);  
                zipWidthHeightImageFile(t, t, p.getW(), p.getH(), 100);
            }  else {
            	x1=0;
            	y1=0;
            	w1=p.getWid();
            	h1=p.getHei();
            }
            return true;
        } catch (IOException e) {  
            e.printStackTrace();
            return false;
        }       
    }  
    public static String zipWidthHeightImageFile(File oldFile,File newFile, int width, int height,  
            float quality) {  
        if (oldFile == null) {  
            return null;  
        }  
        String newImage = null;  
        try {  
            /** 对服务器上的临时文件进行处理 */  
            Image srcFile = ImageIO.read(oldFile);  
            int w = srcFile.getWidth(null);  
        //  System.out.println(w);  
            int h = srcFile.getHeight(null);  
        //  System.out.println(h);  
  
            /** 宽,高设定 */  
            BufferedImage tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
            tag.getGraphics().drawImage(srcFile, 0, 0, width, height, null);  
            //String filePrex = oldFile.substring(0, oldFile.indexOf('.'));  
            /** 压缩后的文件名 */  
            //newImage = filePrex + smallIcon+ oldFile.substring(filePrex.length());  
  
            /** 压缩之后临时存放位置 */  
            FileOutputStream out = new FileOutputStream(newFile);  
  
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);  
            /** 压缩质量 */  
            jep.setQuality(quality, true);  
            encoder.encode(tag, jep);  
            out.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        //清空缓存
        
        return newImage;  
    }  
}  