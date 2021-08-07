package com.github.gisellevonbingen.glowestplastic.util;

import java.lang.reflect.Field;

import net.minecraftforge.common.data.ExistingFileHelper;

public class ExistingFileHelperExtensions
{
	public static final Field enableField;

	static
	{
		try
		{
			enableField = ExistingFileHelper.class.getDeclaredField("enable");
			enableField.setAccessible(true);
		}
		catch (NoSuchFieldException | SecurityException e)
		{
			throw new IllegalStateException("BARF!", e);
		}

	}

	public static void setEnabled(ExistingFileHelper instance, boolean enabe)
	{
		UnsafeHelper.putBoolean(instance, enableField, enabe);
	}

	private ExistingFileHelperExtensions()
	{

	}

}
