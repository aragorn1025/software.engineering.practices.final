package aragorn.gui.ver3_0;

public enum FileIOConstraints {
	INPUT,
	OUTPUT;

	@Override
	public String toString() {
		switch (this) {
			case INPUT:
				return "Input";
			case OUTPUT:
				return "Output";
			default:
				throw new InternalError("Unknown io type.");
		}
	}

	public String getActionString() {
		switch (this) {
			case INPUT:
				return "Open";
			case OUTPUT:
				return "Save";
			default:
				throw new InternalError("Unknown io type.");
		}
	}
}