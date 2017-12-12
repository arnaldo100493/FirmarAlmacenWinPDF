// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LineIterator.java

package org.apache.commons.io;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package org.apache.commons.io:
//            IOUtils

public class LineIterator
    implements Iterator
{

    public LineIterator(Reader reader)
        throws IllegalArgumentException
    {
        finished = false;
        if(reader == null)
            throw new IllegalArgumentException("Reader must not be null");
        if(reader instanceof BufferedReader)
            bufferedReader = (BufferedReader)reader;
        else
            bufferedReader = new BufferedReader(reader);
    }

    public boolean hasNext()
    {
        if(cachedLine != null)
            return true;
        if(finished)
            return false;
_L2:
        String line;
        try
        {
            line = bufferedReader.readLine();
            if(line == null)
            {
                finished = true;
                return false;
            }
        }
        catch(IOException ioe)
        {
            close();
            throw new IllegalStateException(ioe);
        }
        if(!isValidLine(line)) goto _L2; else goto _L1
_L1:
        cachedLine = line;
        return true;
    }

    protected boolean isValidLine(String line)
    {
        return true;
    }

    public String next()
    {
        return nextLine();
    }

    public String nextLine()
    {
        if(!hasNext())
        {
            throw new NoSuchElementException("No more lines");
        } else
        {
            String currentLine = cachedLine;
            cachedLine = null;
            return currentLine;
        }
    }

    public void close()
    {
        finished = true;
        IOUtils.closeQuietly(bufferedReader);
        cachedLine = null;
    }

    public void remove()
    {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

    public static void closeQuietly(LineIterator iterator)
    {
        if(iterator != null)
            iterator.close();
    }

    public volatile Object next()
    {
        return next();
    }

    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished;
}
