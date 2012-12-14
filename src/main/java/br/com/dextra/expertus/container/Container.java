package br.com.dextra.expertus.container;

/**
 * This interface represents a container that should be used by a Environment.
 * You can implement your own Container. Expertus implements a myContainer and a
 * myContainerGae containers.
 * 
 * @author leandro.guimaraes
 */
public interface Container {

	public void start();

	public void stop();

}
