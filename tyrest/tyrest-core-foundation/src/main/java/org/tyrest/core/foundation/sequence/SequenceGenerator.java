package org.tyrest.core.foundation.sequence;

/**
 * 
 * <pre>
 *  Tyrest
 *  File: SequenceGenerator.java
 * 
 *  Tyrest, Inc.
 *  Copyright (C): 2016
 * 
 *  Description:
 *  TODO
 * 
 *  Notes:
 *  $Id: SequenceGenerator.java  Tyrest\magintrursh $ 
 * 
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2016年11月1日		magintrursh		Initial.
 *
 * </pre>
 */
public interface SequenceGenerator
{
    public long getNextValue() throws Exception;
}
