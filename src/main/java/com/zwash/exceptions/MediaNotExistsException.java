package com.zwash.exceptions;

public class MediaNotExistsException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = -3267837584889499032L;
    public MediaNotExistsException()
    {

    }
	public MediaNotExistsException(Long id)
	{
		super(" station id "+id +" does not exists in the System");
	}
}
