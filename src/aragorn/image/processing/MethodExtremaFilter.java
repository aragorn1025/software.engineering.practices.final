package aragorn.image.processing;

import java.awt.image.BufferedImage;

/**
 * @author Aragorn
 * @version 1.0
 */
abstract class MethodExtremaFilter extends Method {

	static final int	TYPE_MAXIMUM	= 1;
	static final int	TYPE_MINIMUM	= 2;

	private int side_length;

	private MethodExtremaFilter(int side_length) {
		setSideLength(side_length);
	}

	static MethodExtremaFilter getInstance(int extrema_filter_type, int side_length) {
		switch (extrema_filter_type) {
			case TYPE_MAXIMUM:
				return new MethodMaximumFilter(side_length);
			case TYPE_MINIMUM:
				return new MethodMinimumFilter(side_length);
			default:
				throw new IllegalArgumentException("Illegal extrema filter type.");
		}

	}

	private void setSideLength(int side_length) {
		if ((side_length & 0x1) != 1) {
			throw new IllegalArgumentException("The length of the side should be odd.");
		} else if (side_length > 9 || side_length < 3) {
			throw new IllegalArgumentException("The recommend value range of the length of the side is [3, 9].");
		}
		this.side_length = side_length;
	}

	@Override
	BufferedImage getOutputImage(BufferedImage image) {
		int[][] val = new int[image.getHeight()][image.getWidth()];
		int[][] pixels = getPixels(image);
		int image_w = image.getWidth(), image_h = image.getHeight();
		for (int i = 0, p, a, r, g, b, pea, per, peg, peb; i < image_h; i++) {
			for (int j = 0; j < image_w; j++) {
				p = pixels[i][j];
				pea = (p & 0xff000000) >>> 24; // alpha bitwise value
				per = (p & 0x00ff0000) >>> 16; // red bitwise value
				peg = (p & 0x0000ff00) >>> 8; // green bitwise value
				peb = (p & 0x000000ff) >>> 0; // blue bitwise value
				for (int mi = -(side_length >> 1); mi <= (side_length >> 1); mi++) {
					for (int mj = -(side_length >> 1); mj <= (side_length >> 1); mj++) {
						if (i + mi >= 0 && i + mi < image_h && j + mj >= 0 && j + mj < image_w && (mi != 0 || mj != 0)) {
							p = pixels[i + mi][j + mj]; // pixels bitwise value
							a = (p & 0xff000000) >>> 24; // alpha bitwise value
							r = (p & 0x00ff0000) >>> 16; // red bitwise value
							g = (p & 0x0000ff00) >>> 8; // green bitwise value
							b = (p & 0x000000ff) >>> 0; // blue bitwise value
							pea = getExtrema(pea, a); // select extrema of the alpha bitwise values
							per = getExtrema(per, r); // select extrema of the red bitwise values
							peg = getExtrema(peg, g); // select extrema of the green bitwise values
							peb = getExtrema(peb, b); // select extrema of the blue bitwise values
						}
					}
				}
				val[i][j] = (0xff << 24) | (per << 16) | (peg << 8) | peb;
			}
		}
		return toImage(val);
	}

	protected abstract int getExtrema(int a, int b);

	private static class MethodMaximumFilter extends MethodExtremaFilter {

		MethodMaximumFilter(int side_length) {
			super(side_length);
		}

		@Override
		protected int getExtrema(int a, int b) {
			return (a > b) ? a : b;
		}
	}

	private static class MethodMinimumFilter extends MethodExtremaFilter {

		MethodMinimumFilter(int side_length) {
			super(side_length);
		}

		@Override
		protected int getExtrema(int a, int b) {
			return (a < b) ? a : b;
		}
	}
}