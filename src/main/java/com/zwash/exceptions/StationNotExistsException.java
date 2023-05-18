package com.zwash.exceptions;

public class StationNotExistsException extends Exception {
	/**
	 *
	 */
	private static final long serialVersionUID = -3267837584889499032L;
    public StationNotExistsException()
    {

    }
	public StationNotExistsException(Long id)
	{
		super(" station id "+id +" does not exists in the System");
	}
}
