// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StreamUtil.java

package co.org.bouncy.asn1;

import java.io.*;
import java.nio.channels.FileChannel;

// Referenced classes of package co.org.bouncy.asn1:
//            LimitedInputStream, ASN1InputStream

class StreamUtil
{

    StreamUtil()
    {
    }

    static int findLimit(InputStream in)
    {
        if(in instanceof LimitedInputStream)
            return ((LimitedInputStream)in).getRemaining();
        if(in instanceof ASN1InputStream)
            return ((ASN1InputStream)in).getLimit();
        if(in instanceof ByteArrayInputStream)
            return ((ByteArrayInputStream)in).available();
        if(in instanceof FileInputStream)
            try
            {
                FileChannel channel = ((FileInputStream)in).getChannel();
                long size = channel == null ? 0x7fffffffL : channel.size();
                if(size < 0x7fffffffL)
                    return (int)size;
            }
            catch(IOException e) { }
        if(MAX_MEMORY > 0x7fffffffL)
            return 0x7fffffff;
        else
            return (int)MAX_MEMORY;
    }

    static int calculateBodyLength(int length)
    {
        int count = 1;
        if(length > 127)
        {
            int size = 1;
            for(int val = length; (val >>>= 8) != 0;)
                size++;

            for(int i = (size - 1) * 8; i >= 0; i -= 8)
                count++;

        }
        return count;
    }

    static int calculateTagLength(int tagNo)
        throws IOException
    {
        int length = 1;
        if(tagNo >= 31)
            if(tagNo < 128)
            {
                length++;
            } else
            {
                byte stack[] = new byte[5];
                int pos = stack.length;
                stack[--pos] = (byte)(tagNo & 0x7f);
                do
                {
                    tagNo >>= 7;
                    stack[--pos] = (byte)(tagNo & 0x7f | 0x80);
                } while(tagNo > 127);
                length += stack.length - pos;
            }
        return length;
    }

    private static final long MAX_MEMORY = Runtime.getRuntime().maxMemory();

}
