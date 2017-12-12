// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcX509ExtensionUtils.java

package co.org.bouncy.cert.bc;

import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.operator.DigestCalculator;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.bc:
//            BcX509ExtensionUtils

private static class BcX509ExtensionUtils$SHA1DigestCalculator
    implements DigestCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
    }

    public OutputStream getOutputStream()
    {
        return bOut;
    }

    public byte[] getDigest()
    {
        byte bytes[] = bOut.toByteArray();
        bOut.reset();
        Digest sha1 = new SHA1Digest();
        sha1.update(bytes, 0, bytes.length);
        byte digest[] = new byte[sha1.getDigestSize()];
        sha1.doFinal(digest, 0);
        return digest;
    }

    private ByteArrayOutputStream bOut;

    private BcX509ExtensionUtils$SHA1DigestCalculator()
    {
        bOut = new ByteArrayOutputStream();
    }

    BcX509ExtensionUtils$SHA1DigestCalculator(BcX509ExtensionUtils._cls1 x0)
    {
        this();
    }
}
