package aragorn.image.processing;

/**
 * @author Aragorn
 */
enum Methods {
	NULL,
	ARGB2GRAY,
	SMOOTHING_MASK_3x3_M_A_1,
	SMOOTHING_MASK_5x5,
	SMOOTHING_MASK_7x7,
	SMOOTHING_MASK_9x9,
	SMOOTHING_MASK_99x99,
	GAUSSIAN_SMOOTHING_MASK_M_A_6_3x3,
	GAUSSIAN_SMOOTHING_MASK_M_A_7_3x3,
	GAUSSIAN_SMOOTHING_MASK_M_A_8_5x5,
	MAXIMUM_FILTERING_3x3,
	MAXIMUM_FILTERING_5x5,
	MAXIMUM_FILTERING_7x7,
	MAXIMUM_FILTERING_9x9,
	MINIMUM_FILTERING_3x3,
	MINIMUM_FILTERING_5x5,
	MINIMUM_FILTERING_7x7,
	MINIMUM_FILTERING_9x9,
	MEDIAN_FILTERING_3x3,
	MEDIAN_FILTERING_5x5,
	MEDIAN_FILTERING_7x7,
	MEDIAN_FILTERING_9x9;

	static Methods getByIndex(int index) {
		switch (index) {
			case 1:
				return Methods.ARGB2GRAY;
			case 2:
				return Methods.SMOOTHING_MASK_3x3_M_A_1;
			case 3:
				return Methods.SMOOTHING_MASK_5x5;
			case 4:
				return Methods.SMOOTHING_MASK_7x7;
			case 5:
				return Methods.SMOOTHING_MASK_9x9;
			case 6:
				return Methods.SMOOTHING_MASK_99x99;
			case 7:
				return GAUSSIAN_SMOOTHING_MASK_M_A_6_3x3;
			case 8:
				return GAUSSIAN_SMOOTHING_MASK_M_A_7_3x3;
			case 9:
				return GAUSSIAN_SMOOTHING_MASK_M_A_8_5x5;
			case 10:
				return MAXIMUM_FILTERING_3x3;
			case 11:
				return MAXIMUM_FILTERING_5x5;
			case 12:
				return MAXIMUM_FILTERING_7x7;
			case 13:
				return MAXIMUM_FILTERING_9x9;
			case 14:
				return MINIMUM_FILTERING_3x3;
			case 15:
				return MINIMUM_FILTERING_5x5;
			case 16:
				return MINIMUM_FILTERING_7x7;
			case 17:
				return MINIMUM_FILTERING_9x9;
			case 18:
				return MEDIAN_FILTERING_3x3;
			case 19:
				return MEDIAN_FILTERING_5x5;
			case 20:
				return MEDIAN_FILTERING_7x7;
			case 21:
				return MEDIAN_FILTERING_9x9;
			default:
				return Methods.NULL;
		}
	}

	static int getLength() {
		for (int i = 1; i <= 100; i++) {
			if (getByIndex(i).equals(Methods.NULL)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		switch (this) {
			case ARGB2GRAY:
				return "Convert from ARGB to Grey";
			case SMOOTHING_MASK_3x3_M_A_1:
				return "Smoothing Mask 3 x 3 (m_a_1)";
			case SMOOTHING_MASK_5x5:
				return "Smoothing Mask 5 x 5";
			case SMOOTHING_MASK_7x7:
				return "Smoothing Mask 7 x 7";
			case SMOOTHING_MASK_9x9:
				return "Smoothing Mask 9 x 9";
			case SMOOTHING_MASK_99x99:
				return "Smoothing Mask 99 x 99";
			case GAUSSIAN_SMOOTHING_MASK_M_A_6_3x3:
				return "Gaussian Smoothing Mask m_a_6 (3 x 3)";
			case GAUSSIAN_SMOOTHING_MASK_M_A_7_3x3:
				return "Gaussian Smoothing Mask m_a_7 (3 x 3)";
			case GAUSSIAN_SMOOTHING_MASK_M_A_8_5x5:
				return "Gaussian Smoothing Mask m_a_8 (5 x 5)";
			case MAXIMUM_FILTERING_3x3:
				return "Maximum Filtering 3 x 3";
			case MAXIMUM_FILTERING_5x5:
				return "Maximum Filtering 5 x 5";
			case MAXIMUM_FILTERING_7x7:
				return "Maximum Filtering 7 x 7";
			case MAXIMUM_FILTERING_9x9:
				return "Maximum Filtering 9 x 9";
			case MINIMUM_FILTERING_3x3:
				return "Minimum Filtering 3 x 3";
			case MINIMUM_FILTERING_5x5:
				return "Minimum Filtering 5 x 5";
			case MINIMUM_FILTERING_7x7:
				return "Minimum Filtering 7 x 7";
			case MINIMUM_FILTERING_9x9:
				return "Minimum Filtering 9 x 9";
			case MEDIAN_FILTERING_3x3:
				return "Median Filtering 3 x 3";
			case MEDIAN_FILTERING_5x5:
				return "Median Filtering 5 x 5";
			case MEDIAN_FILTERING_7x7:
				return "Median Filtering 7 x 7";
			case MEDIAN_FILTERING_9x9:
				return "Median Filtering 9 x 9";
			default:
				return "Choose the method";
		}
	}

	static String[] toStrings() {
		String[] val = new String[Methods.getLength()];
		for (int i = 0; i < val.length; i++) {
			val[i] = Methods.getByIndex(i).toString();
		}
		return val;
	}
}