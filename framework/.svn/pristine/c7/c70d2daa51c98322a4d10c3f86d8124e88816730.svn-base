package com.framework.comm.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * 接口或类的说明: 生成图片验证码
 *
 * <br>
 * ========================== <br>
 * 公司：南京壹号家信息科技有限公司 <br>
 * 开发：yisheng.lu@yihaojiaju.com <br>
 * 版本：1.0 <br>
 * 创建时间：2017-11-15 上午9:25:35 <br>
 * ==========================
 *
 */
public class ImageCode {
	private String randString = "";
	private int width = 62; // width = 13 * length + 10
	private int height = 18;
	private int length = 4;
	private int size = 20;
	private int jamLine = 125;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRandString() {
		return this.randString;
	}

	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	private BufferedImage creatImage(int width, int height, int length) {

		BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		Random random = new Random();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		FontMetrics fm = g.getFontMetrics();
		g.setFont(new Font("Times New Roman", Font.TRUETYPE_FONT, this.size));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < this.jamLine; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		int w = 0;
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			randString += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));// ���ú����������ɫ��ͬ����������Ϊ����̫�ӽ�����ֻ��ֱ�����
			// g.drawString(rand, 13 * i + 6, 16);
			int h = fm.getHeight();
			w += fm.stringWidth(rand);
			g.drawString(rand, i * fm.stringWidth(rand) + w, h);
		}
		g.dispose();
		return image;
	}

	public BufferedImage creatImage(int length) {
		return this.creatImage(this.width, this.height, length);
	}

	public BufferedImage createImage() {
		return this.creatImage(this.length);
	}

	public int getJamLine() {
		return jamLine;
	}

	public void setJamLine(int jamLine) {
		this.jamLine = jamLine;
	}
}
