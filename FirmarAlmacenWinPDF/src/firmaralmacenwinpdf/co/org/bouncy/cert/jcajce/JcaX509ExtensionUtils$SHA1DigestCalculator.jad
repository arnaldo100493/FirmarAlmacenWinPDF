// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaX509ExtensionUtils.java

package co.org.bouncy.cert.jcajce;

import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.DigestCalculator;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;

// Referenced classes of package co.org.bouncy.cert.jcajce:
//            JcaX509ExtensionUtils

private static class JcaX509ExtensionUtils$SHA1DigestCalculator
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
        byte bytes[] = digest.digest(bOut.toByteArray());
        bOut.reset();
        return bytes;
    }

    private ByteArrayOutputStream bOut;
    private MessageDigest digest;

    public JcaX509ExtensionUtils$SHA1DigestCalculator(MessageDigest digest)
    {
        bOut = new ByteArrayOutputStream();
        this.digest = digest;
    }
}
