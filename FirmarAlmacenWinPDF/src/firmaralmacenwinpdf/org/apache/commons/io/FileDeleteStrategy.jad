// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileDeleteStrategy.java

package org.apache.commons.io;

import java.io.File;
import java.io.IOException;

// Referenced classes of package org.apache.commons.io:
//            FileUtils

public class FileDeleteStrategy
{
    static class ForceFileDeleteStrategy extends FileDeleteStrategy
    {

        protected boolean doDelete(File fileToDelete)
            throws IOException
        {
            FileUtils.forceDelete(fileToDelete);
            return true;
        }

        ForceFileDeleteStrategy()
        {
            super("Force");
        }
    }


    protected FileDeleteStrategy(String name)
    {
        this.name = name;
    }

    public boolean deleteQuietly(File fileToDelete)
    {
        if(fileToDelete == null || !fileToDelete.exists())
            return true;
        try
        {
            return doDelete(fileToDelete);
        }
        catch(IOException ex)
        {
            return false;
        }
    }

    public void delete(File fileToDelete)
        throws IOException
    {
        if(fileToDelete.exists() && !doDelete(fileToDelete))
            throw new IOException((new StringBuilder()).append("Deletion failed: ").append(fileToDelete).toString());
        else
            return;
    }

    protected boolean doDelete(File fileToDelete)
        throws IOException
    {
        return fileToDelete.delete();
    }

    public String toString()
    {
        return (new StringBuilder()).append("FileDeleteStrategy[").append(name).append("]").toString();
    }

    public static final FileDeleteStrategy NORMAL = new FileDeleteStrategy("Normal");
    public static final FileDeleteStrategy FORCE = new ForceFileDeleteStrategy();
    private final String name;

}
