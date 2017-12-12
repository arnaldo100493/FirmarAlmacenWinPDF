// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPEncryptedData.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import co.org.bouncy.util.Arrays;
import java.io.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException

public abstract class PGPEncryptedData
    implements SymmetricKeyAlgorithmTags
{
    protected class TruncatedStream extends InputStream
    {

        public int read()
            throws IOException
        {
            int ch = in.read();
            if(ch >= 0)
            {
                int c = lookAhead[bufPtr];
                lookAhead[bufPtr] = ch;
                bufPtr = (bufPtr + 1) % lookAhead.length;
                return c;
            } else
            {
                return -1;
            }
        }

        int[] getLookAhead()
        {
            int tmp[] = new int[lookAhead.length];
            int count = 0;
            for(int i = bufPtr; i != lookAhead.length; i++)
                tmp[count++] = lookAhead[i];

            for(int i = 0; i != bufPtr; i++)
                tmp[count++] = lookAhead[i];

            return tmp;
        }

        int lookAhead[];
        int bufPtr;
        InputStream in;
        final PGPEncryptedData this$0;

        TruncatedStream(InputStream in)
            throws IOException
        {
            this$0 = PGPEncryptedData.this;
            super();
            lookAhead = new int[22];
            for(int i = 0; i != lookAhead.length; i++)
                if((lookAhead[i] = in.read()) < 0)
                    throw new EOFException();

            bufPtr = 0;
            this.in = in;
        }
    }


    PGPEncryptedData(InputStreamPacket encData)
    {
        this.encData = encData;
    }

    public InputStream getInputStream()
    {
        return encData.getInputStream();
    }

    public boolean isIntegrityProtected()
    {
        return encData instanceof SymmetricEncIntegrityPacket;
    }

    public boolean verify()
        throws PGPException, IOException
    {
        if(!isIntegrityProtected())
            throw new PGPException("data not integrity protected.");
        while(encStream.read() >= 0) ;
        int lookAhead[] = truncStream.getLookAhead();
        OutputStream dOut = integrityCalculator.getOutputStream();
        dOut.write((byte)lookAhead[0]);
        dOut.write((byte)lookAhead[1]);
        byte digest[] = integrityCalculator.getDigest();
        byte streamDigest[] = new byte[digest.length];
        for(int i = 0; i != streamDigest.length; i++)
            streamDigest[i] = (byte)lookAhead[i + 2];

        return Arrays.constantTimeAreEqual(digest, streamDigest);
    }

    InputStreamPacket encData;
    InputStream encStream;
    TruncatedStream truncStream;
    PGPDigestCalculator integrityCalculator;
}
