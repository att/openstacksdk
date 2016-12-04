/*******************************************************************************
 * Copyright (C) 2016 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

/**
 * 
 */
package com.woorea.openstack.common.client;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.slf4j.Logger;

/**
 * Wrapper of a SLF4J logger with a JUL logger implementation to redirect logging calls to SLF4J.
 * 
 * @author dh868g
 * @since Aug 6, 2015
 */
public class JULWrapper extends java.util.logging.Logger {
    private Logger logger;

    /**
     * Create the JUL wrapper around the SLF4J logger
     * 
     * @param logger
     *            The SLF4J logger to be wrapped as a JUL logger
     */
    public JULWrapper(Logger logger) {
        super(logger.getName(), null);
        this.logger = logger;
    }

    /**
     * Retrieve the localization resource bundle for this logger for the current default locale. Note that if the result
     * is null, then the Logger will use a resource bundle inherited from its parent.
     */
    @Override
    public ResourceBundle getResourceBundle() {
        return null;
    }

    /**
     * Retrieve the localization resource bundle name for this logger. Note that if the result is null, then the Logger
     * will use a resource bundle name inherited from its parent.
     */
    @Override
    public String getResourceBundleName() {
        return null;
    }

    /**
     * Set a filter to control output on this Logger.
     * <p>
     * After passing the initial "level" check, the Logger will call this Filter to check if a log record should really
     * be published.
     * </p>
     * 
     * @param newFilter
     *            a filter object (may be null)
     */
    @Override
    public void setFilter(java.util.logging.Filter newFilter) throws SecurityException {
        // unused
    }

    /**
     * Get the current filter for this Logger.
     * 
     * @return the filter, may be null.
     */
    @Override
    public java.util.logging.Filter getFilter() {
        return null;
    }

    /**
     * Log a LogRecord.
     * <p>
     * All the other logging methods in this class call through this method to actually perform any logging. Subclasses
     * can override this single method to capture all log activity.
     * </p>
     * 
     * @param record
     *            the LogRecord to be published
     */
    @Override
    public void log(LogRecord record) {
        super.log(record);
    }

    /**
     * Log a message, with no arguments.
     * <P>
     * If the logger is currently enabled for the given message level then the given message is forwarded to all the
     * registered output Handler objects.
     * </p>
     * 
     * @param level
     *            One of the message level identifiers, e.g., SEVERE
     * @param msg
     *            The string message (or a key in the message catalog)
     */
    @Override
    public void log(Level level, String msg) {
        writeLog(level, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Object)
     */
    @Override
    public void log(Level level, String msg, Object param1) {
        writeLog(level, msg, null, new Object[] {
            param1
        });
    }

    /**
     * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Object[])
     */
    @Override
    public void log(Level level, String msg, Object[] params) {
        writeLog(level, msg, null, params);
    }

    /**
     * @see java.util.logging.Logger#log(java.util.logging.Level, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void log(Level level, String msg, Throwable thrown) {
        writeLog(level, msg, thrown, null);
    }

    /**
     * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void logp(Level level, String sourceClass, String sourceMethod, String msg) {
        writeLog(level, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.Object)
     */
    @Override
    public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object param1) {
        writeLog(level, msg, null, new Object[] {
            param1
        });
    }

    /**
     * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.Object[])
     */
    @Override
    public void logp(Level level, String sourceClass, String sourceMethod, String msg, Object[] params) {
        writeLog(level, msg, null, params);
    }

    /**
     * @see java.util.logging.Logger#logp(java.util.logging.Level, java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.Throwable)
     */
    @Override
    public void logp(Level level, String sourceClass, String sourceMethod, String msg, Throwable thrown) {
        writeLog(level, msg, thrown, null);
    }

    /**
     * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg) {
        writeLog(level, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.Object)
     */
    @Override
    public void
        logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg, Object param1) {
        writeLog(level, msg, null, new Object[] {
            param1
        });
    }

    /**
     * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.Object[])
     */
    @Override
    public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg,
        Object[] params) {
        writeLog(level, msg, null, params);
    }

    /**
     * @see java.util.logging.Logger#logrb(java.util.logging.Level, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.Throwable)
     */
    @Override
    public void logrb(Level level, String sourceClass, String sourceMethod, String bundleName, String msg,
        Throwable thrown) {
        writeLog(level, msg, thrown, null);
    }

    /**
     * Log a method entry.
     * <p>
     * This is a convenience method that can be used to log entry to a method. A LogRecord with message "ENTRY", log
     * level FINER, and the given sourceMethod and sourceClass is logged.
     * </p>
     */
    @SuppressWarnings("nls")
    @Override
    public void entering(String sourceClass, String sourceMethod) {
        writeLog(Level.FINER, "ENTRY {0}.{1}", null, new Object[] {
            sourceClass, sourceMethod
        });
    }

    /**
     * @see java.util.logging.Logger#entering(java.lang.String, java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("nls")
    @Override
    public void entering(String sourceClass, String sourceMethod, Object param1) {
        writeLog(Level.FINER, "ENTRY {0}.{1} - {2}", null, new Object[] {
            sourceClass, sourceMethod, param1
        });
    }

    /**
     * @see java.util.logging.Logger#entering(java.lang.String, java.lang.String, java.lang.Object[])
     */
    @SuppressWarnings("nls")
    @Override
    public void entering(String sourceClass, String sourceMethod, Object[] params) {
        Object[] args = new Object[params.length + 2];
        args[0] = sourceClass;
        args[1] = sourceMethod;
        System.arraycopy(params, 0, args, 2, params.length);
        writeLog(Level.FINER, "ENTRY {0}.{1} - {2}", null, args);
    }

    /**
     * @see java.util.logging.Logger#exiting(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("nls")
    @Override
    public void exiting(String sourceClass, String sourceMethod) {
        writeLog(Level.FINER, "RETURN {0}.{1}", null, new Object[] {
            sourceClass, sourceMethod
        });
    }

    /**
     * @see java.util.logging.Logger#exiting(java.lang.String, java.lang.String, java.lang.Object)
     */
    @SuppressWarnings("nls")
    @Override
    public void exiting(String sourceClass, String sourceMethod, Object result) {
        writeLog(Level.FINER, "RETURN {0}.{1} - {2}", null, new Object[] {
            sourceClass, sourceMethod, result
        });
    }

    /**
     * @see java.util.logging.Logger#throwing(java.lang.String, java.lang.String, java.lang.Throwable)
     */
    @SuppressWarnings("nls")
    @Override
    public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
        writeLog(Level.FINER, "THROW {0}.{1}", thrown, new Object[] {
            sourceClass, sourceMethod
        });
    }

    /**
     * @see java.util.logging.Logger#severe(java.lang.String)
     */
    @Override
    public void severe(String msg) {
        writeLog(Level.SEVERE, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#warning(java.lang.String)
     */
    @Override
    public void warning(String msg) {
        writeLog(Level.WARNING, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#info(java.lang.String)
     */
    @Override
    public void info(String msg) {
        writeLog(Level.INFO, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#config(java.lang.String)
     */
    @Override
    public void config(String msg) {
        writeLog(Level.CONFIG, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#fine(java.lang.String)
     */
    @Override
    public void fine(String msg) {
        writeLog(Level.FINE, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#finer(java.lang.String)
     */
    @Override
    public void finer(String msg) {
        writeLog(Level.FINER, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#finest(java.lang.String)
     */
    @Override
    public void finest(String msg) {
        writeLog(Level.FINEST, msg, null, null);
    }

    /**
     * @see java.util.logging.Logger#setLevel(java.util.logging.Level)
     */
    @Override
    public void setLevel(Level newLevel) throws SecurityException {
        // nop
    }

    /**
     * @see java.util.logging.Logger#getLevel()
     */
    @Override
    public Level getLevel() {
        if (logger.isTraceEnabled()) {
            return Level.FINEST;
        } else if (logger.isDebugEnabled()) {
            return Level.FINER;
        } else if (logger.isInfoEnabled()) {
            return Level.FINE;
        } else if (logger.isWarnEnabled()) {
            return Level.WARNING;
        } else if (logger.isErrorEnabled()) {
            return Level.SEVERE;
        } else {
            return Level.OFF;
        }
    }

    /**
     * @see java.util.logging.Logger#isLoggable(java.util.logging.Level)
     */
    @Override
    public boolean isLoggable(Level level) {
        if ((Level.FINEST.equals(level) || Level.CONFIG.equals(level) || Level.ALL.equals(level))
            && logger.isTraceEnabled()) {
            return true;
        } else if (Level.FINER.equals(level) && logger.isDebugEnabled()) {
            return true;
        } else if (Level.FINE.equals(level) && logger.isInfoEnabled()) {
            return true;
        } else if (Level.WARNING.equals(level) && logger.isWarnEnabled()) {
            return true;
        } else if (Level.SEVERE.equals(level) && logger.isErrorEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @see java.util.logging.Logger#getName()
     */
    @Override
    public String getName() {
        return logger.getName();
    }

    /**
     * @see java.util.logging.Logger#addHandler(java.util.logging.Handler)
     */
    @Override
    public void addHandler(Handler handler) throws SecurityException {
        super.addHandler(handler);
    }

    /**
     * @see java.util.logging.Logger#removeHandler(java.util.logging.Handler)
     */
    @Override
    public void removeHandler(Handler handler) throws SecurityException {
        super.removeHandler(handler);
    }

    /**
     * @see java.util.logging.Logger#getHandlers()
     */
    @Override
    public Handler[] getHandlers() {
        return super.getHandlers();
    }

    /**
     * @see java.util.logging.Logger#setUseParentHandlers(boolean)
     */
    @Override
    public void setUseParentHandlers(boolean useParentHandlers) {
        super.setUseParentHandlers(useParentHandlers);
    }

    /**
     * @see java.util.logging.Logger#getUseParentHandlers()
     */
    @Override
    public boolean getUseParentHandlers() {
        return super.getUseParentHandlers();
    }

    /**
     * @see java.util.logging.Logger#getParent()
     */
    @Override
    public java.util.logging.Logger getParent() {
        return super.getParent();
    }

    /**
     * @see java.util.logging.Logger#setParent(java.util.logging.Logger)
     */
    @Override
    public void setParent(java.util.logging.Logger parent) {
        super.setParent(parent);
    }

    /**
     * This method is called by each logging method to determine if the specified level is active, format the message,
     * and write it to the slf4j logger.
     * 
     * @param level
     *            The level as defined by java.util.logging.Level.
     * @param msg
     *            The message to be written, possibly formatted with parameters
     * @param t
     *            Any throwable to be recorded as part of the logging event, or null
     * @param params
     *            The optional format parameters for the message
     */
    private void writeLog(Level level, String msg, Throwable t, Object[] params) {
        if (level.equals(Level.FINEST)) {
            if (logger.isTraceEnabled()) {
                if (t != null) {
                    if (params == null || params.length == 0) {
                        logger.trace(msg, t);
                    } else {
                        logger.trace(MessageFormat.format(msg, params), t);
                    }
                } else {
                    if (params == null || params.length == 0) {
                        logger.trace(msg);
                    } else {
                        logger.trace(MessageFormat.format(msg, params));
                    }
                }
            }
        } else if (level.equals(Level.CONFIG) || level.equals(Level.FINER)) {
            if (logger.isDebugEnabled()) {
                if (t != null) {
                    if (params == null || params.length == 0) {
                        logger.debug(msg, t);
                    } else {
                        logger.debug(MessageFormat.format(msg, params), t);
                    }
                } else {
                    if (params == null || params.length == 0) {
                        logger.debug(msg);
                    } else {
                        logger.debug(MessageFormat.format(msg, params));
                    }
                }
            }
        } else if (level.equals(Level.INFO) || level.equals(Level.FINE)) {
            if (logger.isInfoEnabled()) {
                if (t != null) {
                    if (params == null || params.length == 0) {
                        logger.info(msg, t);
                    } else {
                        logger.info(MessageFormat.format(msg, params), t);
                    }
                } else {
                    if (params == null || params.length == 0) {
                        logger.info(msg);
                    } else {
                        logger.info(MessageFormat.format(msg, params));
                    }
                }
            }
        } else if (level.equals(Level.WARNING)) {
            if (logger.isWarnEnabled()) {
                if (t != null) {
                    if (params == null || params.length == 0) {
                        logger.warn(msg, t);
                    } else {
                        logger.warn(MessageFormat.format(msg, params), t);
                    }
                } else {
                    if (params == null || params.length == 0) {
                        logger.warn(msg);
                    } else {
                        logger.warn(MessageFormat.format(msg, params));
                    }
                }
            }
        } else if (level.equals(Level.SEVERE)) {
            if (logger.isErrorEnabled()) {
                if (t != null) {
                    if (params == null || params.length == 0) {
                        logger.error(msg, t);
                    } else {
                        logger.error(MessageFormat.format(msg, params), t);
                    }
                } else {
                    if (params == null || params.length == 0) {
                        logger.error(msg);
                    } else {
                        logger.error(MessageFormat.format(msg, params));
                    }
                }
            }
        }
    }
}
