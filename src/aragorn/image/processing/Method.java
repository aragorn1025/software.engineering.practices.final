package aragorn.image.processing;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import aragorn.image.processing.Mask.SmoothingMask;

/**
 * @author Aragorn
 */
abstract class Method {
	static Method getInstance(Methods methods) {
		switch (methods) {
			case ARGB2GRAY:
				return new MethodArgbToGrey(20);
			case SMOOTHING_MASK_3x3_M_A_1:
				return new MethodImageMask(Mask.getInstance(Mask.M_A_3));
			case SMOOTHING_MASK_5x5:
				return new MethodImageMask(new SmoothingMask(5, 5));
			case SMOOTHING_MASK_7x7:
				return new MethodImageMask(new SmoothingMask(7, 7));
			case SMOOTHING_MASK_9x9:
				return new MethodImageMask(new SmoothingMask(9, 9));
			case SMOOTHING_MASK_99x99:
				return new MethodImageMask(new SmoothingMask(99, 99));
			case GAUSSIAN_SMOOTHING_MASK_M_A_6_3x3:
				return new MethodImageMask(Mask.getInstance(Mask.M_A_6));
			case GAUSSIAN_SMOOTHING_MASK_M_A_7_3x3:
				return new MethodImageMask(Mask.getInstance(Mask.M_A_7));
			case GAUSSIAN_SMOOTHING_MASK_M_A_8_5x5:
				return new MethodImageMask(Mask.getInstance(Mask.M_A_8));
			case MAXIMUM_FILTERING_3x3:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MAXIMUM, 3);
			case MAXIMUM_FILTERING_5x5:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MAXIMUM, 5);
			case MAXIMUM_FILTERING_7x7:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MAXIMUM, 7);
			case MAXIMUM_FILTERING_9x9:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MAXIMUM, 9);
			case MINIMUM_FILTERING_3x3:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MINIMUM, 3);
			case MINIMUM_FILTERING_5x5:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MINIMUM, 5);
			case MINIMUM_FILTERING_7x7:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MINIMUM, 7);
			case MINIMUM_FILTERING_9x9:
				return MethodExtremaFilter.getInstance(MethodExtremaFilter.TYPE_MINIMUM, 9);
			case MEDIAN_FILTERING_3x3:
				return new MethodMedianFilter(3);
			case MEDIAN_FILTERING_5x5:
				return new MethodMedianFilter(5);
			case MEDIAN_FILTERING_7x7:
				return new MethodMedianFilter(7);
			case MEDIAN_FILTERING_9x9:
				return new MethodMedianFilter(9);
			default:
				return new NullMethod();
		}
	}

	abstract BufferedImage getOutputImage(BufferedImage image) throws OutOfMemoryError;

	private static class NullMethod extends Method {
		NullMethod() {
		}

		@Override
		BufferedImage getOutputImage(BufferedImage image) throws OutOfMemoryError {
			return new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
		}
	}

	protected static BufferedImage toImage(int[][] pixels) {
		for (int i = 1; i < pixels.length; i++) {
			if (pixels[i].length != pixels[0].length) {
				throw new IllegalArgumentException("Mask should be a matrix.");
			}
		}
		int width = pixels[0].length;
		int height = pixels.length;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		image.setRGB(0, 0, width, height, toOneDimensionArray(pixels), 0, width);
		return image;
	}

	private static int[] toOneDimensionArray(int[][] array) {
		int array_size = 0;
		for (int i = 0; i < array.length; i++) {
			array_size += array[i].length;
		}
		int[] val = new int[array_size];
		for (int i = 0, p = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				val[p] = array[i][j];
				p++;
			}
		}
		return val;
	}

	/**
	 * @param image
	 *            input image
	 * @return the pixels data of the image
	 * @see <a target="_blank" href="https://stackoverflow.com/questions/6524196/java-get-pixel-array-from-image">Java - get pixel array from
	 *      image</a>
	 */
	protected static int[][] getPixels(BufferedImage image) {
		byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		int width = image.getWidth();
		int height = image.getHeight();
		boolean has_alpha_channel = image.getAlphaRaster() != null;
		int[][] val = new int[height][width];
		if (has_alpha_channel) {
			int pixel_length = 4;
			for (int pixel_pointer = 0, row = 0, col = 0; pixel_pointer < pixels.length; pixel_pointer += pixel_length) {
				int argb = 0;
				argb += (((int) pixels[pixel_pointer + 0] & 0xff) << 24); // alpha
				argb += (((int) pixels[pixel_pointer + 3] & 0xff) << 16); // red
				argb += (((int) pixels[pixel_pointer + 2] & 0xff) << 8); // green
				argb += (((int) pixels[pixel_pointer + 1] & 0xff)); // blue
				val[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		} else {
			int pixel_length = 3;
			for (int pixel_pointer = 0, row = 0, col = 0; pixel_pointer < pixels.length; pixel_pointer += pixel_length) {
				int argb = 0;
				argb += (0xff << 24); // alpha: 255
				argb += (((int) pixels[pixel_pointer + 2] & 0xff) << 16); // red
				argb += (((int) pixels[pixel_pointer + 1] & 0xff) << 8); // green
				argb += (((int) pixels[pixel_pointer + 0] & 0xff)); // blue
				val[row][col] = argb;
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}
		}
		return val;
	}
}