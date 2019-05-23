package cn.com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class createImage {

	public void create() {
		BufferedImage bufferedImage = new BufferedImage(80,32,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = bufferedImage.createGraphics();
		graphics.setColor(Color.LIGHT_GRAY);
		graphics.fillRect(0, 0, 80, 32);
		graphics.setColor(Color.BLACK);
		graphics.drawRect(0, 0, 79, 31);
		graphics.setColor(Color.gray);
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			int x = random.nextInt(79);
			int y = random.nextInt(31);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			graphics.drawLine(x,y,x+x1,y+y1);
		}
		int coceCount = 4;
		int fountH,coceX,coceY;
		coceX = (80-4)/(coceCount+1);
		fountH = 32 - 10;
		coceY = 32 - 7;
		char[] IMAGECHARS = {
	            '2', '3', '4', '5', '6', '7', '8', '9',
	            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'k', 'm', 'n',
	            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
	            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'K', 'M', 'N',
	            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		Font font = new Font("Fixedsys", Font.PLAIN, fountH);
		graphics.setFont(font);
		for (int i = 0; i <coceCount; i++) {
			String code = String.valueOf(IMAGECHARS[random.nextInt(IMAGECHARS.length)]);
			int r = random.nextInt(255);
			int g = random.nextInt(255);
			int b = random.nextInt(255);
			graphics.setColor(new Color(r,g,b));
			graphics.drawString(code, (i + 1) * coceX, coceY);
		}
		try {
			ImageIO.write(bufferedImage, "jpg", new File("/Users/cgh/Desktop/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
