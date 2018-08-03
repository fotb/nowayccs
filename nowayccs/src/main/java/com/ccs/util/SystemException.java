package com.ccs.util;

/**
 */
public class SystemException extends BaseException {

	/**
	* 
	*/
	private static final long serialVersionUID = -2382137160878432219L;

	/**
	 * @param :
	 * @return :
	 */
	public SystemException() {
		super("Error occurred in system.");
	}

	/**
	 * @return :
	 */
	public SystemException(String message) {
		super(message);
	}

	/**
	 * @param :
	 * @return :
	 */
	public SystemException(String message, Throwable nested) {
		super(message, nested);
	}

	/**
	 * @param :
	 * @return :
	 */
	public SystemException(Throwable nested) {
		super(nested);
	}
}
