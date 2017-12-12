// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MSOutlookKeyIdCalculator.java

package co.org.bouncy.cert.selector;

import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import java.io.IOException;

class MSOutlookKeyIdCalculator
{

    MSOutlookKeyIdCalculator()
    {
    }

    static byte[] calculateKeyId(SubjectPublicKeyInfo info)
    {
        Digest dig = new SHA1Digest();
        byte hash[] = new byte[dig.getDigestSize()];
        byte spkiEnc[] = new byte[0];
        try
        {
            spkiEnc = info.getEncoded("DER");
        }
        catch(IOException e)
        {
            return new byte[0];
        }
        dig.update(spkiEnc, 0, spkiEnc.length);
        dig.doFinal(hash, 0);
        return hash;
    }
}
