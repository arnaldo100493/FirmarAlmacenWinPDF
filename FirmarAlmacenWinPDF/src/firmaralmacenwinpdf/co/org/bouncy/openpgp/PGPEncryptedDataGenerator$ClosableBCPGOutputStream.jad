// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPEncryptedDataGenerator.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGOutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPEncryptedDataGenerator

private class PGPEncryptedDataGenerator$ClosableBCPGOutputStream extends BCPGOutputStream
{

    public void close()
        throws IOException
    {
        finish();
    }

    final PGPEncryptedDataGenerator this$0;

    public PGPEncryptedDataGenerator$ClosableBCPGOutputStream(OutputStream out, int symmetricKeyEnc, byte buffer[])
        throws IOException
    {
        this$0 = PGPEncryptedDataGenerator.this;
        super(out, symmetricKeyEnc, buffer);
    }

    public PGPEncryptedDataGenerator$ClosableBCPGOutputStream(OutputStream out, int symmetricKeyEnc, long length, boolean oldFormat)
        throws IOException
    {
        this$0 = PGPEncryptedDataGenerator.this;
        super(out, symmetricKeyEnc, length, oldFormat);
    }

    public PGPEncryptedDataGenerator$ClosableBCPGOutputStream(OutputStream out, int symEncIntegrityPro, long length)
        throws IOException
    {
        this$0 = PGPEncryptedDataGenerator.this;
        super(out, symEncIntegrityPro, length);
    }
}
