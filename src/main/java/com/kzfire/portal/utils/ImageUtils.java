package com.kzfire.portal.utils;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;



/**
 * ͼƬ���?���ࣺ<br>
 * ���ܣ�����ͼ���и�ͼ��ͼ������ת������ɫת�ڰס�����ˮӡ��ͼƬˮӡ��
 * @author Administrator
 */
public class ImageUtils {


    /**
     * ���ֳ����ͼƬ��ʽ
     */
    public final static String IMAGE_TYPE_GIF = "GIF";// ͼ�ν�����ʽ
    public final static String IMAGE_TYPE_JPG = "JPG";// ������Ƭר����
    public final static String IMAGE_TYPE_JPEG = "JPEG";// ������Ƭר����
    public final static String IMAGE_TYPE_BMP = "BMP";// Ӣ��Bitmap��λͼ���ļ�д������Windows����ϵͳ�еı�׼ͼ���ļ���ʽ
    public final static String IMAGE_TYPE_PNG = "PNG";// ����ֲ����ͼ��
    public final static String IMAGE_TYPE_PSD = "PSD";// Photoshop��ר�ø�ʽPhotoshop


    /**
     * ������ڣ����ڲ���
     * @param args
     */
    public static void main(String[] args) {
        // 1-����ͼ��
        // ����һ������������
    	String aa="e:/aa.jpg";
    	String bb="d:/bb.jpg";
    	long l=System.currentTimeMillis();
//        ImageUtils.scale("d:/aa.jpg", "d:/abc_scale.jpg", 2, true);//����OK
//        // �����������߶ȺͿ������
//        ImageUtils.scale(aa, bb, 500, 300, true);//����OK
//
//
//        // 2-�и�ͼ��
//        // ����һ����ָ��������Ϳ���и�
//        ImageUtils.cut(aa, bb, 0, 0, 400, 400 );//����OK
//        // ��������ָ����Ƭ�����������
//
//
//        // 3-ͼ������ת����
//        ImageUtils.convert("e:/abc.jpg", "jpg", "e:/abc_convert.jpg");//����OK
//
//
//        // 4-��ɫת�ڰף�
//        ImageUtils.gray("e:/abc.jpg", "e:/abc_gray.jpg");//����OK
//
//
//        // 5-��ͼƬ�������ˮӡ��
//        // ����һ��
//        ImageUtils.pressText("����ˮӡ����","e:/abc.jpg","e:/abc_pressText.jpg","����",Font.BOLD,Color.white,80, 0, 0, 0.5f);//����OK
//        // ��������
//        ImageUtils.pressText2("��Ҳ��ˮӡ����", "e:/abc.jpg","e:/abc_pressText2.jpg", "����", 36, Color.white, 80, 0, 0, 0.5f);//����OK
//        
//        // 6-��ͼƬ���ͼƬˮӡ��
//        ImageUtils.pressImage("e:/abc2.jpg", "e:/abc.jpg","e:/abc_pressImage.jpg", 0, 0, 0.5f);//����OK
       String aas= getImageStr("e:/abc.jpg");
       System.out.println(aas.getBytes().length);
    	generateImage(aas, "e:/ab.jpg");
    	long l1=System.currentTimeMillis();
        System.out.println("��ʱ��"+(l1-l));
    }


    
    //ͼƬת����base64�ַ�  
    public static String getImageStr(String imgFile)  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ����������Base64���봦��  
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        Base64 base64=new   Base64() ;
        //���ֽ�����Base64����  
//        BASE64Encoder encoder = new BASE64Encoder();  
//        return encoder.encode(data);//����Base64�������ֽ������ַ�  
        return new String(base64.encode(data));
    }  
    
  //base64�ַ�ת����ͼƬ  
    public static boolean generateImage(String imgStr,String imgFilePath)  
    {   //���ֽ������ַ����Base64���벢���ͼƬ  
        if (imgStr == null) //ͼ�����Ϊ��  
            return false;  
        try   
        {  
        	  Base64 base64=new   Base64() ;
            //Base64����  
            byte[] b =base64.decode(imgStr.getBytes());
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣���  
                    b[i]+=256;  
                }  
            }  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        }   
        catch (Exception e)   
        {  
            return false;  
        }  
    }  
    /**
     * ����ͼ�񣨰��������ţ�
     * @param srcImageFile Դͼ���ļ���ַ
     * @param result ���ź��ͼ���ַ
     * @param scale ���ű���
     * @param flag ����ѡ��:true �Ŵ�; false ��С;
     */
    public  static void scale(String srcImageFile, String result,
            int scale, boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // �����ļ�
            int width = src.getWidth(); // �õ�Դͼ��
            int height = src.getHeight(); // �õ�Դͼ��
            if (flag) {// �Ŵ�
                width = width * scale;
                height = height * scale;
            } else {// ��С
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // ������С���ͼ
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// ������ļ���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * ����ͼ�񣨰��߶ȺͿ�����ţ�
     * @param srcImageFile Դͼ���ļ���ַ
     * @param result ���ź��ͼ���ַ
     * @param height ���ź�ĸ߶�
     * @param width ���ź�Ŀ��
     * @param bb �����ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������;
     */
    public  static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // ���ű���
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);
            // �������
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform
                        .getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {//����
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ͼ���и�(��ָ��������Ϳ���и�)
     * @param srcImageFile Դͼ���ַ
     * @param result ��Ƭ���ͼ���ַ
     * @param x Ŀ����Ƭ������X
     * @param y Ŀ����Ƭ������Y
     * @param width Ŀ����Ƭ���
     * @param height Ŀ����Ƭ�߶�
     */
    public  static void cut(String srcImageFile, String result,
            int x, int y, int width, int height) {
        try {
            // ��ȡԴͼ��
            BufferedImage bi = ImageIO.read(new File(srcImageFile));
            int srcWidth = bi.getHeight(); // Դͼ���
            int srcHeight = bi.getWidth(); // Դͼ�߶�
            if (srcWidth > 0 && srcHeight > 0) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight,
                        Image.SCALE_DEFAULT);
                // �ĸ�����ֱ�Ϊͼ��������Ϳ��
                // ��: CropImageFilter(int x,int y,int width,int height)
                ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
                Image img = Toolkit.getDefaultToolkit().createImage(
                        new FilteredImageSource(image.getSource(),
                                cropFilter));
                BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, width, height, null); // �����и���ͼ
                g.dispose();
                // ���Ϊ�ļ�
                ImageIO.write(tag, "JPEG", new File(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    




    /**
     * ͼ������ת����GIF->JPG��GIF->PNG��PNG->JPG��PNG->GIF(X)��BMP->PNG
     * @param srcImageFile Դͼ���ַ
     * @param formatName ���ʽ����ʽ��Ƶ� String����JPG��JPEG��GIF��
     * @param destImageFile Ŀ��ͼ���ַ
     */
    public  static void convert(String srcImageFile, String formatName, String destImageFile) {
        try {
            File f = new File(srcImageFile);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, formatName, new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ɫתΪ�ڰ� 
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     */
    public  static void gray(String srcImageFile, String destImageFile) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(destImageFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ͼƬ�������ˮӡ
     * @param pressText ˮӡ����
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     * @param fontName ˮӡ���������
     * @param fontStyle ˮӡ��������ʽ
     * @param color ˮӡ��������ɫ
     * @param fontSize ˮӡ�������С
     * @param x ����ֵ
     * @param y ����ֵ
     * @param alpha ͸���ȣ�alpha �����Ƿ�Χ [0.0, 1.0] ֮�ڣ���߽�ֵ����һ����������
     */
    public  static void pressText(String pressText,
            String srcImageFile, String destImageFile, String fontName,
            int fontStyle, Color color, int fontSize,int x,
            int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // ��ָ��������ˮӡ����
            g.drawString(pressText, (width - (getLength(pressText) * fontSize))
                    / 2 + x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));// ������ļ���
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ͼƬ�������ˮӡ
     * @param pressText ˮӡ����
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     * @param fontName �������
     * @param fontStyle ������ʽ
     * @param color ������ɫ
     * @param fontSize �����С
     * @param x ����ֵ
     * @param y ����ֵ
     * @param alpha ͸���ȣ�alpha �����Ƿ�Χ [0.0, 1.0] ֮�ڣ���߽�ֵ����һ����������
     */
    public  static void pressText2(String pressText, String srcImageFile,String destImageFile,
            String fontName, int fontStyle, Color color, int fontSize, int x,
            int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int width = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, width, height, null);
            g.setColor(color);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // ��ָ��������ˮӡ����
            g.drawString(pressText, (width - (getLength(pressText) * fontSize))
                    / 2 + x, (height - fontSize) / 2 + y);
            g.dispose();
            ImageIO.write((BufferedImage) image, "JPEG", new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ��ͼƬ���ͼƬˮӡ
     * @param pressImg ˮӡͼƬ
     * @param srcImageFile Դͼ���ַ
     * @param destImageFile Ŀ��ͼ���ַ
     * @param x ����ֵ�� Ĭ�����м�
     * @param y ����ֵ�� Ĭ�����м�
     * @param alpha ͸���ȣ�alpha �����Ƿ�Χ [0.0, 1.0] ֮�ڣ���߽�ֵ����һ����������
     */
    public  static void pressImage(String pressImg, String srcImageFile,String destImageFile,
            int x, int y, float alpha) {
        try {
            File img = new File(srcImageFile);
            Image src = ImageIO.read(img);
            int wideth = src.getWidth(null);
            int height = src.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(src, 0, 0, wideth, height, null);
            // ˮӡ�ļ�
            Image src_biao = ImageIO.read(new File(pressImg));
            int wideth_biao = src_biao.getWidth(null);
            int height_biao = src_biao.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            g.drawImage(src_biao, (wideth - wideth_biao) / 2,
                    (height - height_biao) / 2, wideth_biao, height_biao, null);
            // ˮӡ�ļ�����
            g.dispose();
            ImageIO.write((BufferedImage) image,  "JPEG", new File(destImageFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ����text�ĳ��ȣ�һ�������������ַ�
     * @param text
     * @return
     */
    private  static int getLength(String text) {
        int length = 0;
        for (int i = 0; i < text.length(); i++) {
            if (new String(text.charAt(i) + "").getBytes().length > 1) {
                length += 2;
            } else {
                length += 1;
            }
        }
        return length / 2;
    }
}