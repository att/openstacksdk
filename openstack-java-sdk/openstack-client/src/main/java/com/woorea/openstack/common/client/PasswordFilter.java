/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.common.client;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
/**
 * This class filters passwords from the log messages.
 * @author tp163u
 *
 */
public class PasswordFilter extends JULWrapper {

	/**
	 * @param logger
	 */
	public PasswordFilter(org.slf4j.Logger logger) {
		super(logger);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.LogRecord)
	 */
	@Override
	public void log(LogRecord record) {
		record.setMessage(maskPassword(record.getMessage()));
		super.log(record);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String)
	 */
	@Override
	public void log(Level level, String msg) {
		
		super.log(level, maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.util.function.Supplier)
	 */
	@Override
	public void log(Level level, Supplier<String> msgSupplier) {
		
		super.log(level, maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Object)
	 */
	@Override
	public void log(Level level, String msg, Object param1) {
		
		super.log(level, maskPassword(msg), param1);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void log(Level level, String msg, Object[] params) {
		
		super.log(level, maskPassword(msg), params);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void log(Level level, String msg, Throwable thrown) {
		
		super.log(level, maskPassword(msg), thrown);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.Throwable, java.util.function.Supplier)
	 */
	@Override
	public void log(Level level, Throwable thrown, Supplier<String> msgSupplier) {
		
		super.log(level, thrown, maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, String msg) {
		
		super.logp(level, sourceClass, sourceMethod, maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.util.function.Supplier)
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, Supplier<String> msgSupplier) {
		
		super.logp(level, sourceClass, sourceMethod, maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object param1) {
		
		super.logp(level, sourceClass, sourceMethod, maskPassword(msg), param1);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {
		
		super.logp(level, sourceClass, sourceMethod, maskPassword(msg), params);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, String msg, Throwable thrown) {
		
		super.logp(level, sourceClass, sourceMethod, maskPassword(msg), thrown);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.Throwable, java.util.function.Supplier)
	 */
	@Override
	public void logp(Level level, String sourceClass, String sourceMethod, Throwable thrown,
			Supplier<String> msgSupplier) {
		
		super.logp(level, sourceClass, sourceMethod, thrown, maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg) {
		
		super.logrb(level, sourceClass, sourceMethod, bundleName,  maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg,
			Object param1) {
		
		super.logrb(level, sourceClass, sourceMethod, bundleName, maskPassword(msg), param1);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg,
			Object[] params) {
		
		super.logrb(level, sourceClass, sourceMethod, bundleName, maskPassword(msg), params);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.util.ResourceBundle, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg,
			Object... params) {
		
		super.logrb(level, sourceClass, sourceMethod, bundle, maskPassword(msg), params);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg,
			Throwable thrown) {
		
		super.logrb(level, sourceClass, sourceMethod, bundleName, maskPassword(msg), thrown);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String, java.util.ResourceBundle, java.lang.String, java.lang.Throwable)
	 */
	@Override
	public void logrb(Level level, String sourceClass, String sourceMethod, ResourceBundle bundle, String msg,
			Throwable thrown) {
		
		super.logrb(level, sourceClass, sourceMethod, bundle, maskPassword(msg), thrown);
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#severe(java.lang.String)
	 */
	@Override
	public void severe(String msg) {
		
		super.severe(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#warning(java.lang.String)
	 */
	@Override
	public void warning(String msg) {
		
		super.warning(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#info(java.lang.String)
	 */
	@Override
	public void info(String msg) {
		
		super.info(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#fine(java.lang.String)
	 */
	@Override
	public void fine(String msg) {
		
		super.fine(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#finer(java.lang.String)
	 */
	@Override
	public void finer(String msg) {
		
		super.finer(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#finest(java.lang.String)
	 */
	@Override
	public void finest(String msg) {
		
		super.finest(maskPassword(msg));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#severe(java.util.function.Supplier)
	 */
	@Override
	public void severe(Supplier<String> msgSupplier) {
		
		super.severe(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#warning(java.util.function.Supplier)
	 */
	@Override
	public void warning(Supplier<String> msgSupplier) {
		
		super.warning(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#info(java.util.function.Supplier)
	 */
	@Override
	public void info(Supplier<String> msgSupplier) {
		
		super.info(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#config(java.util.function.Supplier)
	 */
	@Override
	public void config(Supplier<String> msgSupplier) {
		
		super.config(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#fine(java.util.function.Supplier)
	 */
	@Override
	public void fine(Supplier<String> msgSupplier) {
		
		super.fine(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#finer(java.util.function.Supplier)
	 */
	@Override
	public void finer(Supplier<String> msgSupplier) {
		
		super.finer(maskPassword(msgSupplier));
	}

	/* (non-Javadoc)
	 * @see java.util.logging.Logger#finest(java.util.function.Supplier)
	 */
	@Override
	public void finest(Supplier<String> msgSupplier) {
		
		super.finest(maskPassword(msgSupplier));
	}

	
	
	private static final String PASSWORD_PATTERN = "\"password\".*:.*\"(.*)\"";
	
	
	/**
	 * @param supplier
	 * @return
	 */
	private static Supplier<String> maskPassword(Supplier<String> supplier)
	{
		final String msg = supplier.get();
		Supplier<String> newSupplier = new Supplier<String>() {
			
			@Override
			public String get() {
				return maskPassword(msg);
			}
		};
		
		return newSupplier;
	}
	/**
	 * @param msg
	 * @return
	 */
	private static String maskPassword(String msg)
	{
		if(msg!=null)
			msg=msg.replaceAll(PASSWORD_PATTERN, "\"password\" : \"********\"");
		return msg;
	}

}
