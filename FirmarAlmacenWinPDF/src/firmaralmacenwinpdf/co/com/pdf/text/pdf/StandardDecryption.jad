// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StandardDecryption.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.pdf.crypto.AESCipher;
import co.com.pdf.text.pdf.crypto.ARCFOUREncryption;

public class StandardDecryption
{

    public StandardDecryption(byte key[], int off, int len, int revision)
    {
        iv = new byte[16];
        aes = revision == 4 || revision == 5;
        if(aes)
        {
            this.key = new byte[len];
            System.arraycopy(key, off, this.key, 0, len);
        } else
        {
            arcfour = new ARCFOUREncryption();
            arcfour.prepareARCFOURKey(key, off, len);
        }
    }

    public byte[] update(byte b[], int off, int len)
    {
        if(aes)
        {
            if(initiated)
                return cipher.update(b, off, len);
            int left = Math.min(iv.length - ivptr, len);
            System.arraycopy(b, off, iv, ivptr, left);
            off += left;
            len -= left;
            ivptr += left;
            if(ivptr == iv.length)
            {
                cipher = new AESCipher(false, key, iv);
                initiated = true;
                if(len > 0)
                    return cipher.update(b, off, len);
            }
            return null;
        } else
        {
            byte b2[] = new byte[len];
            arcfour.encryptARCFOUR(b, off, len, b2, 0);
            return b2;
        }
    }

    public byte[] finish()
    {
        if(aes)
            return cipher.doFinal();
        else
            return null;
    }

    protected ARCFOUREncryption arcfour;
    protected AESCipher cipher;
    private byte key[];
    private static final int AES_128 = 4;
    private static final int AES_256 = 5;
    private boolean aes;
    private boolean initiated;
    private byte iv[];
    private int ivptr;
}
