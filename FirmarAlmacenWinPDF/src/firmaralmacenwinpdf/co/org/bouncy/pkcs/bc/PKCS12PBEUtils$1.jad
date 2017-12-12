// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12PBEUtils.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.MacOutputStream;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.pkcs.bc:
//            PKCS12PBEUtils

static class PKCS12PBEUtils$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(val$digestAlgorithm, val$pbeParams);
    }

    public OutputStream getOutputStream()
    {
        return new MacOutputStream(val$hMac);
    }

    public byte[] getMac()
    {
        byte res[] = new byte[val$hMac.getMacSize()];
        val$hMac.doFinal(res, 0);
        return res;
    }

    public GenericKey getKey()
    {
        return new GenericKey(getAlgorithmIdentifier(), PKCS12ParametersGenerator.PKCS12PasswordToBytes(val$password));
    }

    final ASN1ObjectIdentifier val$digestAlgorithm;
    final PKCS12PBEParams val$pbeParams;
    final HMac val$hMac;
    final char val$password[];

    PKCS12PBEUtils$1(ASN1ObjectIdentifier asn1objectidentifier, PKCS12PBEParams pkcs12pbeparams, HMac hmac, char ac[])
    {
        val$digestAlgorithm = asn1objectidentifier;
        val$pbeParams = pkcs12pbeparams;
        val$hMac = hmac;
        val$password = ac;
        super();
    }
}
