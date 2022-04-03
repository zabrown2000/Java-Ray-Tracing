package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Color;
import renderer.ImageWriter;

class ImageWriterTests {

	/**
	 * Test method for
	 * {@link renderer.ImageWriter#writeToImage(int, int, primitives.color)}.
	 */
	@Test
	void testWriteToImage() { 
		
		int nX = 800;
		int jColumns = 16;
		int nY = 500;
		int iRows = 10;
		Color grid = new Color(237, 63, 179); //hot pink
		Color square = new Color(97, 233, 233); //teal
		ImageWriter im = new ImageWriter("gridImage", nX, nY);
		
		for (int i = 0; i < nY; i++) {
			for (int j = 0; j < nX; j++) {
				if (((i % (nY/iRows)) == 0) || ((j % (nX/jColumns)) == 0)) { //grid lines
					im.writePixel(j, i, grid);
				} else {
					im.writePixel(j,  i, square);
				}
			}
		}
		im.writeToImage();
	}

}

