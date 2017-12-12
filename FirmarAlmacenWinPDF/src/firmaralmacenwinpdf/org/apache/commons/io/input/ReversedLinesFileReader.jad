// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReversedLinesFileReader.java

package org.apache.commons.io.input;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.apache.commons.io.Charsets;

public class ReversedLinesFileReader
    implements Closeable
{
    private class FilePart
    {

        private FilePart rollOver()
            throws IOException
        {
            if(currentLastBytePos > -1)
                throw new IllegalStateException((new StringBuilder()).append("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=").append(currentLastBytePos).toString());
            if(no > 1L)
                return new FilePart(no - 1L, blockSize, leftOver);
            if(leftOver != null)
                throw new IllegalStateException((new StringBuilder()).append("Unexpected leftover of the last block: leftOverOfThisFilePart=").append(new String(leftOver, encoding)).toString());
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
                if(!isLastFilePart && i < avoidNewlineSplitBufferSize)
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
                    line = new String(lineData, encoding);
                    currentLastBytePos = i - newLineMatchByteCount;
                    break;
                }
                i -= byteDecrement;
                if(i >= 0)
                    continue;
                createLeftOver();
                break;
            } while(true);
            if(isLastFilePart && leftOver != null)
            {
                line = new String(leftOver, encoding);
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
            byte arr$[][] = newLineSequences;
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



        private FilePart(long no, int length, byte leftOverOfLastFilePart[])
            throws IOException
        {
            this$0 = ReversedLinesFileReader.this;
            super();
            this.no = no;
            int dataLength = length + (leftOverOfLastFilePart == null ? 0 : leftOverOfLastFilePart.length);
            data = new byte[dataLength];
            long off = (no - 1L) * (long)blockSize;
            if(no > 0L)
            {
                randomAccessFile.seek(off);
                int countRead = randomAccessFile.read(data, 0, length);
                if(countRead != length)
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
            }
            if(leftOverOfLastFilePart != null)
                System.arraycopy(leftOverOfLastFilePart, 0, data, length, leftOverOfLastFilePart.length);
            currentLastBytePos = data.length - 1;
            leftOver = null;
        }

        FilePart(long x1, int x2, byte x3[], _cls1 x4)
            throws IOException
        {
            this(x1, x2, x3);
        }
    }


    public ReversedLinesFileReader(File file)
        throws IOException
    {
        this(file, 4096, Charset.defaultCharset().toString());
    }

    public ReversedLinesFileReader(File file, int blockSize, Charset encoding)
        throws IOException
    {
        trailingNewlineOfFileSkipped = false;
        this.blockSize = blockSize;
        this.encoding = encoding;
        randomAccessFile = new RandomAccessFile(file, "r");
        totalByteLength = randomAccessFile.length();
        int lastBlockLength = (int)(totalByteLength % (long)blockSize);
        if(lastBlockLength > 0)
        {
            totalBlockCount = totalByteLength / (long)blockSize + 1L;
        } else
        {
            totalBlockCount = totalByteLength / (long)blockSize;
            if(totalByteLength > 0L)
                lastBlockLength = blockSize;
        }
        currentFilePart = new FilePart(totalBlockCount, lastBlockLength, null);
        Charset charset = Charsets.toCharset(encoding);
        CharsetEncoder charsetEncoder = charset.newEncoder();
        float maxBytesPerChar = charsetEncoder.maxBytesPerChar();
        if(maxBytesPerChar == 1.0F)
            byteDecrement = 1;
        else
        if(charset == Charset.forName("UTF-8"))
            byteDecrement = 1;
        else
        if(charset == Charset.forName("Shift_JIS"))
            byteDecrement = 1;
        else
        if(charset == Charset.forName("UTF-16BE") || charset == Charset.forName("UTF-16LE"))
            byteDecrement = 2;
        else
        if(charset == Charset.forName("UTF-16"))
            throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
        else
            throw new UnsupportedEncodingException((new StringBuilder()).append("Encoding ").append(encoding).append(" is not supported yet (feel free to submit a patch)").toString());
        newLineSequences = (new byte[][] {
            "\r\n".getBytes(encoding), "\n".getBytes(encoding), "\r".getBytes(encoding)
        });
        avoidNewlineSplitBufferSize = newLineSequences[0].length;
    }

    public ReversedLinesFileReader(File file, int blockSize, String encoding)
        throws IOException
    {
        this(file, blockSize, Charsets.toCharset(encoding));
    }

    public String readLine()
        throws IOException
    {
        String line = currentFilePart.readLine();
        do
        {
            if(line != null)
                break;
            currentFilePart = currentFilePart.rollOver();
            if(currentFilePart == null)
                break;
            line = currentFilePart.readLine();
        } while(true);
        if("".equals(line) && !trailingNewlineOfFileSkipped)
        {
            trailingNewlineOfFileSkipped = true;
            line = readLine();
        }
        return line;
    }

    public void close()
        throws IOException
    {
        randomAccessFile.close();
    }

    private final int blockSize;
    private final Charset encoding;
    private final RandomAccessFile randomAccessFile;
    private final long totalByteLength;
    private final long totalBlockCount;
    private final byte newLineSequences[][];
    private final int avoidNewlineSplitBufferSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private boolean trailingNewlineOfFileSkipped;






}
