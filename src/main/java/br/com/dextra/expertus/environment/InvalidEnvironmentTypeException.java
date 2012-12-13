package br.com.dextra.expertus.environment;

public class InvalidEnvironmentTypeException extends RuntimeException {

	private static final long serialVersionUID = -3534273410868293399L;

	private EnvironmentType type;

	public InvalidEnvironmentTypeException(EnvironmentType type) {
		this.type = type;
	}

	public EnvironmentType getInvalidType() {
		return this.type;
	}

	@Override
	public String toString() {
		return "InvalidEnvironmentTypeException [type=" + type + "]";
	}

}
