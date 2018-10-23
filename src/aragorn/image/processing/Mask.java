package aragorn.image.processing;

/**
 * @author Aragorn
 */
class Mask implements Cloneable {
	static final int	M_A_3	= 1;
	static final int	M_A_6	= 2;
	static final int	M_A_7	= 3;
	static final int	M_A_8	= 4;
	static final int	M_US_1	= 5;
	static final int	M_US_2	= 6;

	private int[][] mask;

	private Mask(int width, int height) {
		this.mask = new int[height][width];
	}

	@Override
	protected Mask clone() {
		int width = mask[0].length;
		int height = mask.length;
		Mask val = new Mask(width, height);
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				val.setMatrix(i, j, mask[i][j]);
			}
		}
		return val;
	}

	static Mask getInstance(int mask_id) {
		switch (mask_id) {
			case M_A_3:
				return new SmoothingMask(mask_id);
			case M_A_6:
			case M_A_7:
			case M_A_8:
				return new GaussianSmoothingMask(mask_id);
			default:
				throw new IllegalArgumentException("Illegal value of mask_id.");
		}
	}

	protected void setMatrix(int row, int col, int value) {
		mask[row][col] = value;
	}

	int[][] getMatrix() {
		return mask;
	}

	static class SmoothingMask extends Mask {

		private SmoothingMask(int mask_id) {
			this(get_side_length(mask_id), get_side_length(mask_id));
		}

		SmoothingMask(int width, int height) {
			super(width, height);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					setMatrix(i, j, 1);
				}
			}
		}

		private final static int get_side_length(int mask_id) {
			switch (mask_id) {
				case M_A_3:
					return 3;
				default:
					throw new IllegalArgumentException("Illegal value of mask_id.");
			}
		}
	}

	private static class GaussianSmoothingMask extends Mask {

		private static final int[][]	m_a_6	= new int[][] { { 1, 1, 1 }, { 1, 2, 1 }, { 1, 1, 1 } };
		private static final int[][]	m_a_7	= new int[][] { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };
		private static final int[][]	m_a_8	= new int[][] {	{ 1, 2, 4, 2, 1 }, { 2, 4, 10, 4, 2 }, { 4, 10, 16, 10, 4 }, { 2, 4, 10, 4, 2 },
																{ 1, 2, 4, 2, 1 } };

		private GaussianSmoothingMask(int mask_id) {
			super(get_side_length(mask_id), get_side_length(mask_id));
			int side_length = get_side_length(mask_id);
			for (int i = 0; i < side_length; i++) {
				for (int j = 0; j < side_length; j++) {
					switch (mask_id) {
						case M_A_6:
							setMatrix(i, j, m_a_6[i][j]);
							break;
						case M_A_7:
							setMatrix(i, j, m_a_7[i][j]);
							break;
						case M_A_8:
							setMatrix(i, j, m_a_8[i][j]);
							break;
						default:
							throw new InternalError("Illegal value of mask_id.");
					}
				}
			}
		}

		private final static int get_side_length(int mask_id) {
			switch (mask_id) {
				case M_A_6:
				case M_A_7:
					return 3;
				case M_A_8:
					return 5;
				default:
					throw new IllegalArgumentException("Illegal value of mask_id.");
			}
		}
	}
}