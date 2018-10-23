package aragorn.image.processing;

import java.awt.image.BufferedImage;
import java.util.Arrays;

/**
 * @author Aragorn
 */
class MethodMedianFilter extends Method {

	private int side_length;

	MethodMedianFilter(int side_length) {
		setSideLength(side_length);
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
		int filter_size = side_length * side_length;
		int[] aa = new int[filter_size], ar = new int[filter_size], ag = new int[filter_size], ab = new int[filter_size];
		for (int i = 0, p, ap, pa, pr, pg, pb; i < image_h; i++) {
			for (int j = 0; j < image_w; j++) {
				ap = 0;
				for (int mi = -(side_length >> 1); mi <= (side_length >> 1); mi++) {
					for (int mj = -(side_length >> 1); mj <= (side_length >> 1); mj++) {
						if (i + mi >= 0 && i + mi < image_h && j + mj >= 0 && j + mj < image_w) {
							p = pixels[i + mi][j + mj]; // pixels bitwise value
							aa[ap] = (p & 0xff000000) >>> 24; // alpha bitwise value
							ar[ap] = (p & 0x00ff0000) >>> 16; // red bitwise value
							ag[ap] = (p & 0x0000ff00) >>> 8; // green bitwise value
							ab[ap] = (p & 0x000000ff) >>> 0; // blue bitwise value
							ap++;
						}
					}
				}
				Arrays.sort(aa, 0, ap);
				Arrays.sort(ar, 0, ap);
				Arrays.sort(ag, 0, ap);
				Arrays.sort(ab, 0, ap);
				pa = (aa[ap >> 1] + aa[(ap - 1) >> 1]) >> 1;
				pr = (ar[ap >> 1] + ar[(ap - 1) >> 1]) >> 1;
				pg = (ag[ap >> 1] + ag[(ap - 1) >> 1]) >> 1;
				pb = (ab[ap >> 1] + ab[(ap - 1) >> 1]) >> 1;
				val[i][j] = (pa << 24) | (pr << 16) | (pg << 8) | pb;
			}
		}
		return toImage(val);
	}
}