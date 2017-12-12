// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReversedLinesFileReader.java

package org.apache.commons.io.input;

import java.io.IOException;
import java.io.RandomAccessFile;

// Referenced classes of package org.apache.commons.io.input:
//            ReversedLinesFileReader

private class ReversedLinesFileReader$FilePart
{

    private ReversedLinesFileReader$FilePart rollOver()
        throws IOException
    {
        if(currentLastBytePos > -1)
            throw new IllegalStateException((new StringBuilder()).append("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=").append(currentLastBytePos).toString());
        if(no > 1L)
            return new ReversedLinesFileReader$FilePart(no - 1L, ReversedLinesFileReader.access$300(ReversedLinesFileReader.this), leftOver);
        if(leftOver != null)
            throw new IllegalStateException((new StringBuilder()).append("Unexpected leftover of the last block: leftOverOfThisFilePart=").append(new String(leftOver, ReversedLinesFileReader.access$500(ReversedLinesFileReader.this))).toString());
        else
            return null;
    }

    private String readLine()
        throws IOException
    {
        String line = null;
        boolean isLastFilePart = no == 1L;
        int i = currentLastBytePos;
        do
        {
            if(i <= -1)
                break;
            if(!isLastFilePart && i < ReversedLinesFileReader.access$600(ReversedLinesFileReader.this))
            {
                createLeftOver();
                break;
            }
            int newLineMatchByteCount;
            if((newLineMatchByteCount = getNewLineMatchByteCount(data, i)) > 0)
            {
                int lineStart = i + 1;
                int lineLengthBytes = (currentLastBytePos - lineStart) + 1;
                if(lineLengthBytes < 0)
                    throw new IllegalStateException((new StringBuilder()).append("Unexpected negative line length=").append(lineLengthBytes).toString());
                byte lineData[] = new byte[lineLengthBytes];
                System.arraycopy(data, lineStart, lineData, 0, lineLengthBytes);
                line = new String(lineData, ReversedLinesFileReader.access$500(ReversedLinesFileReader.this));
                currentLastBytePos = i - newLineMatchByteCount;
                break;
            }
            i -= ReversedLinesFileReader.access$700(ReversedLinesFileReader.this);
            if(i >= 0)
                continue;
            createLeftOver();
            break;
        } while(true);
        if(isLastFilePart && leftOver != null)
        {
            line = new String(leftOver, ReversedLinesFileReader.access$500(ReversedLinesFileReader.this));
            leftOver = null;
        }
        return line;
    }

    private void createLeftOver()
    {
        int lineLengthBytes = currentLastBytePos + 1;
        if(lineLengthBytes > 0)
        {
            leftOver = new byte[lineLengthBytes];
            System.arraycopy(data, 0, leftOver, 0, lineLengthBytes);
        } else
        {
            leftOver = null;
        }
        currentLastBytePos = -1;
    }

    private int getNewLineMatchByteCount(byte data[], int i)
    {
        byte arr$[][] = ReversedLinesFileReader.access$800(ReversedLinesFileReader.this);
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            byte newLineSequence[] = arr$[i$];
            boolean match = true;
            for(int j = newLineSequence.length - 1; j >= 0; j--)
            {
                int k = (i + j) - (newLineSequence.length - 1);
                match &= k >= 0 && data[k] == newLineSequence[j];
            }

            if(match)
                return newLineSequence.length;
        }

        return 0;
    }

    private final long no;
    private final byte data[];
    private byte leftOver[];
    private int currentLastBytePos;
    final ReversedLinesFileReader this$0;



    private ReversedLinesFileReader$FilePart(long no, int length, byte leftOverOfLastFilePart[])
        throws IOException
    {
        this$0 = ReversedLinesFileReader.this;
        super();
        this.no = no;
        int dataLength = length + (leftOverOfLastFilePart == null ? 0 : leftOverOfLastFilePart.length);
        data = new byte[dataLength];
        long off = (no - 1L) * (long)ReversedLinesFileReader.access$300(ReversedLinesFileReader.this);
        if(no > 0L)
        {
            ReversedLinesFileReader.access$400(ReversedLinesFileReader.this).seek(off);
            int countRead = ReversedLinesFileReader.access$400(ReversedLinesFileReader.this).read(data, 0, length);
            if(countRead != length)
                throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
        }
        if(leftOverOfLastFilePart != null)
            System.arraycopy(leftOverOfLastFilePart, 0, data, length, leftOverOfLastFilePart.length);
        currentLastBytePos = data.length - 1;
        leftOver = null;
    }

    ReversedLinesFileReader$FilePart(long x1, int x2, byte x3[], ReversedLinesFileReader._cls1 x4)
        throws IOException
    {
        this(x1, x2, x3);
    }
}
