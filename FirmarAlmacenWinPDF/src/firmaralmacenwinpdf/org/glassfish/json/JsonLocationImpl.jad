// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JsonLocationImpl.java

package org.glassfish.json;

import javax.json.stream.JsonLocation;

class JsonLocationImpl
    implements JsonLocation
{

    JsonLocationImpl(long lineNo, long columnNo, long streamOffset)
    {
        this.lineNo = lineNo;
        this.columnNo = columnNo;
        offset = streamOffset;
    }

    public long getLineNumber()
    {
        return lineNo;
    }

    public long getColumnNumber()
    {
        return columnNo;
    }

    public long getStreamOffset()
    {
        return offset;
    }

    public String toString()
    {
        return (new StringBuilder()).append("(line no=").append(lineNo).append(", column no=").append(columnNo).append(", offset=").append(offset).append(")").toString();
    }

    static final JsonLocation UNKNOWN = new JsonLocationImpl(-1L, -1L, -1L);
    private final long columnNo;
    private final long lineNo;
    private final long offset;

}
