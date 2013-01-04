package br.com.dextra.expertus.page;

public class TimedOutException extends RuntimeException {

	private static final long serialVersionUID = -8948209160058703170L;

	public TimedOutException(String message) {
		super(message);
	}

}
