package com.yyb.gcquan.db.utility;

public interface MethodInterface {

	public long insert(Object object);

	public long update(Object object);

	public long delete(Object object);

	public long deleteAll();

	public Object query();
	
	public void close();

}
