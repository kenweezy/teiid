/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package org.teiid.jdbc;

import org.teiid.client.RequestMessage;

/**
 * These execution properties can 
 * be set via the {@link TeiidStatement#setExecutionProperty(String, String)}
 * method.  They affect the subsequent execution of all commands on that Statement
 * instance.  
 * 
 * They can also be set using a SET statement via JDBC and take effect for the
 * duration of the session.
 */
public interface ExecutionProperties {

    /** Execution property name for XML format */
    public static final String PROP_XML_FORMAT = "XMLFormat"; //$NON-NLS-1$
    
    /** Execution property name for XML validation */
    public static final String PROP_XML_VALIDATION = "XMLValidation"; //$NON-NLS-1$

    /** Execution property name for transaction auto wrap mode */
    public static final String PROP_TXN_AUTO_WRAP = "autoCommitTxn"; //$NON-NLS-1$

    /** Execution property name for partial results mode */
    public static final String PROP_PARTIAL_RESULTS_MODE = "partialResultsMode"; //$NON-NLS-1$

    /** XML results format:  XML results displayed as a formatted tree */
    public static final String XML_TREE_FORMAT = "Tree"; //$NON-NLS-1$

    /** XML results format:  XML results displayed in compact form */
    public static final String XML_COMPACT_FORMAT = "Compact"; //$NON-NLS-1$

    /** Transaction auto wrap constant - never wrap a command execution in a transaction */
    public static final String TXN_WRAP_OFF = RequestMessage.TXN_WRAP_OFF;

    /** Transaction auto wrap constant - always wrap commands in a transaction. */
    public static final String TXN_WRAP_ON = RequestMessage.TXN_WRAP_ON;

    /**
     * Transaction auto wrap constant - checks if a command
     * requires a transaction and will be automatically wrap it.
     */
    public static final String TXN_WRAP_DETECT = RequestMessage.TXN_WRAP_DETECT;

    /** 
     * Whether to use result set cache if it is available 
     * @since 4.2 
     */
    public static final String RESULT_SET_CACHE_MODE = "resultSetCacheMode"; //$NON-NLS-1$
    
    /**
     * Default fetch size to use on Statements if the fetch size is not explicitly set.
     * The default is 500.  
     * @since 4.2
     */
    public static final String PROP_FETCH_SIZE = "fetchSize";   //$NON-NLS-1$ 
    
    /**
     * If true, will ignore autocommit for local transactions.
     * @since 5.5.2
     */
    public static final String DISABLE_LOCAL_TRANSACTIONS = "disableLocalTxn";  //$NON-NLS-1$
    
    /**
	 * Overrides the handling of double quoted identifiers to allow them to be strings.
	 * @since 4.3 
     */
    public static final String ANSI_QUOTED_IDENTIFIERS = "ansiQuotedIdentifiers"; //$NON-NLS-1$   
    
    /**
     * Can be one of ON|OFF|DEBUG
     */
    public static final String SQL_OPTION_SHOWPLAN = "SHOWPLAN"; //$NON-NLS-1$
    
    /**
     * Can be one of ON|OFF
     */
    public static final String NOEXEC = "NOEXEC"; //$NON-NLS-1$
    
    public static final String NEWINSTANCE = "NEWINSTANCE"; //$NON-NLS-1$
    
    public static final String QUERYTIMEOUT = "QUERYTIMEOUT"; //$NON-NLS-1$
    
	/**
	 * TEIID-1651
	 * A change was made in JDBC4 so that when an 'Alias' is used it will
	 * now be returned as the label.   Prior to this, it was returned as
	 * the name.   Setting this property to <code>false</code> will enable
	 * backwards compatibility when JDBC3 and older support is still required.
	 * 
	 * Default is <code>true</code>
	 * @since 7.4.1
	 */
    
    public static final String JDBC4COLUMNNAMEANDLABELSEMANTICS = "useJDBC4ColumnNameAndLabelSemantics"; //$NON-NLS-1$
    
    
}
    