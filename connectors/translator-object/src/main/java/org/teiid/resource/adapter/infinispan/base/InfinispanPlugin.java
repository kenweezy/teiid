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
package org.teiid.resource.adapter.infinispan.base;

import java.util.ResourceBundle;

import org.teiid.core.BundleUtil;

public class InfinispanPlugin { 

    public static final String PLUGIN_ID = InfinispanPlugin.class.getPackage().getName();

    /**
     * Provides access to the plugin's log and to it's resources.
     */
    public static final BundleUtil Util = new BundleUtil(PLUGIN_ID, PLUGIN_ID + ".i18n", ResourceBundle.getBundle(PLUGIN_ID + ".i18n")); //$NON-NLS-1$ //$NON-NLS-2$

    public static enum Event implements BundleUtil.Event{
    	TEIID21000,
    	
		TEIID25000,
		TEIID25001,
		TEIID25002,
		TEIID25003,
		TEIID25004,
		TEIID25005,
		TEIID25006,
		TEIID25007,
		TEIID25008,
		TEIID25009,
		TEIID25010,
	
		TEIID25020,
		TEIID25021,
		TEIID25022,
		TEIID25023,
		TEIID25024,
		TEIID25025,
		TEIID25026,
		TEIID25027,
		TEIID25028,
		TEIID25029,
		TEIID25030,		
		TEIID25031,
		
		TEIID25040,		

		TEIID25050,		
		TEIID25051,		
		TEIID25052,		
		TEIID25053,	
		TEIID25054,
		
		TEIID25060		
	}
}
