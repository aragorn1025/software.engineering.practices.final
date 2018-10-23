package aragorn.image.processing;

import java.awt.image.BufferedImage;

/**
 * @author Aragorn
 * @see <a target="_blank" href="http://atlaboratary.blogspot.tw/2013/08/rgb-g-rey-l-gray-r0.html">RGB To Grey</a>
 */
class MethodArgbToGrey extends Method {
	private int		precision;
	private int[]	w	= new int[3];

	MethodArgbToGrey(int precision) {
		setPrecision(precision);
	}

	void setPrecision(int precision) {
		if (precision >= 2 && precision <= 21) {
			this.precision = precision;
			double[] w = new double[3];
			int bt = 1 << precision;
			double base = (double) bt;
			for (int i = 0, wt; i < 3; i++) {
				w[i] = getWeight(i) * base;
				wt = (int) Math.floor(w[i]);
				this.w[i] = wt;
				w[i] -= wt;
				bt -= wt;
			}
			if (bt >= 0 && bt <= 3) {
				for (; bt > 0; bt--) {
					if (w[1] >= w[0] && w[1] >= w[2]) {
						this.w[1]++;
						w[1]--;
					} else if (w[0] >= w[1] && w[0] >= w[2]) {
						this.w[0]++;
						w[0]--;
					} else {
						this.w[2]++;
						w[2]--;
					}
				}
			} else {
				throw new InternalError("Unknown error at set precision.");
			}
		} else {
			throw new IllegalArgumentException("The value of the precision must be in the range [2, 21].");
		}
	}

	private double getWeight(int index) {
		switch (index) {
			case 0: // weight red
				return 0.299;
			case 1: // weight green
				return 0.587;
			case 2: // weight blue
				return 0.114;
			default:
				throw new IllegalArgumentException("The index should be 0:R, 1:G or 2:B.");
		}
	}

	@Override
	BufferedImage getOutputImage(BufferedImage image) {
		int[][] val = new int[image.getHeight()][image.getWidth()];
		int[][] pixels = getPixels(image);
		for (int i = 0, a, r, g, b, p; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				a = (pixels[i][j] & 0xff000000) >>> 24; // alpha bitwise value
				r = (pixels[i][j] & 0x00ff0000) >>> 16; // red bitwise value
				g = (pixels[i][j] & 0x0000ff00) >>> 8; // green bitwise value
				b = (pixels[i][j] & 0x000000ff) >>> 0; // blue bitwise value
				p = (r * w[0] + g * w[1] + b * w[2]) >> precision; // compute grey level
				if (a < 0xff) {
					p *= a / 0xff;
				}
				val[i][j] = 0xff000000 | (p << 16) | (p << 8) | p;
			}
		}
		return toImage(val);
	}
}