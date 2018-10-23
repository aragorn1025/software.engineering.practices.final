package aragorn.image.processing;

import java.awt.image.BufferedImage;

/**
 * @author Aragorn
 */
class MethodImageMask extends Method {

	private Mask mask;

	MethodImageMask(Mask mask) {
		setMask(mask);
	}

	void setMask(Mask mask) {
		this.mask = mask.clone();
	}

	@Override
	BufferedImage getOutputImage(BufferedImage image) {
		int[][] val = new int[image.getHeight()][image.getWidth()];
		int[][] pixels = getPixels(image);
		int image_w = image.getWidth(), image_h = image.getHeight(), mask_w = mask.getMatrix()[0].length, mask_h = mask.getMatrix().length;
		for (int i = 0, p, m, a, r, g, b, psa, psr, psg, psb, md; i < image_h; i++) {
			for (int j = 0; j < image_w; j++) {
				psa = 0;
				psr = 0;
				psg = 0;
				psb = 0;
				md = 0;
				for (int mi = -(mask_h >> 1); mi <= (mask_h >> 1); mi++) {
					for (int mj = -(mask_w >> 1); mj <= (mask_w >> 1); mj++) {
						if (i + mi >= 0 && i + mi < image_h && j + mj >= 0 && j + mj < image_w) {
							p = pixels[i + mi][j + mj]; // pixels bitwise value
							m = mask.getMatrix()[mi + (mask_h >> 1)][mj + (mask_w >> 1)]; // mask weight value
							a = (p & 0xff000000) >>> 24; // alpha bitwise value
							r = (p & 0x00ff0000) >>> 16; // red bitwise value
							g = (p & 0x0000ff00) >>> 8; // green bitwise value
							b = (p & 0x000000ff) >>> 0; // blue bitwise value
							psa += a * m; // sum the alpha bitwise values
							psr += r * m; // sum the red bitwise values
							psg += g * m; // sum the green bitwise values
							psb += b * m; // sum the blue bitwise values
							md += m; // sum the mask weight values
						}
					}
				}
				psa /= md;
				psr /= md;
				psg /= md;
				psb /= md;
				val[i][j] = (psa << 24) | (psr << 16) | (psg << 8) | psb;
			}
		}
		return toImage(val);
	}
}