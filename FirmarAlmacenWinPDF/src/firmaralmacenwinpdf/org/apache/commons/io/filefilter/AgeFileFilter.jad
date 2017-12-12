// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AgeFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.io.FileUtils;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter

public class AgeFileFilter extends AbstractFileFilter
    implements Serializable
{

    public AgeFileFilter(long cutoff)
    {
        this(cutoff, true);
    }

    public AgeFileFilter(long cutoff, boolean acceptOlder)
    {
        this.acceptOlder = acceptOlder;
        this.cutoff = cutoff;
    }

    public AgeFileFilter(Date cutoffDate)
    {
        this(cutoffDate, true);
    }

    public AgeFileFilter(Date cutoffDate, boolean acceptOlder)
    {
        this(cutoffDate.getTime(), acceptOlder);
    }

    public AgeFileFilter(File cutoffReference)
    {
        this(cutoffReference, true);
    }

    public AgeFileFilter(File cutoffReference, boolean acceptOlder)
    {
        this(cutoffReference.lastModified(), acceptOlder);
    }

    public boolean accept(File file)
    {
        boolean newer = FileUtils.isFileNewer(file, cutoff);
        return acceptOlder ? !newer : newer;
    }

    public String toString()
    {
        String condition = acceptOlder ? "<=" : ">";
        return (new StringBuilder()).append(super.toString()).append("(").append(condition).append(cutoff).append(")").toString();
    }

    private final long cutoff;
    private final boolean acceptOlder;
}
