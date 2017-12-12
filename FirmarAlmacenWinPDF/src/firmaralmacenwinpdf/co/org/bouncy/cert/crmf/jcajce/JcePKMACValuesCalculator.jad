// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKMACValuesCalculator.java

package co.org.bouncy.cert.crmf.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.crmf.CRMFException;
import co.org.bouncy.cert.crmf.PKMACValuesCalculator;
import co.org.bouncy.jcajce.*;
import java.security.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

// Referenced classes of package co.org.bouncy.cert.crmf.jcajce:
//            CRMFHelper

public class JcePKMACValuesCalculator
    implements PKMACValuesCalculator
{

    public JcePKMACValuesCalculator()
    {
        helper = new CRMFHelper(new DefaultJcaJceHelper());
    }

    public JcePKMACValuesCalculator setProvider(Provider provider)
    {
        helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePKMACValuesCalculator setProvider(String providerName)
    {
        helper = new CRMFHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public void setup(AlgorithmIdentifier digAlg, AlgorithmIdentifier macAlg)
        throws CRMFException
    {
        digest = helper.createDigest(digAlg.getAlgorithm());
        mac = helper.createMac(macAlg.getAlgorithm());
    }

    public byte[] calculateDigest(byte data[])
    {
        return digest.digest(data);
    }

    public byte[] calculateMac(byte pwd[], byte data[])
        throws CRMFException
    {
        try
        {
            mac.init(new SecretKeySpec(pwd, mac.getAlgorithm()));
            return mac.doFinal(data);
        }
        catch(GeneralSecurityException e)
        {
            throw new CRMFException((new StringBuilder()).append("failure in setup: ").append(e.getMessage()).toString(), e);
        }
    }

    private MessageDigest digest;
    private Mac mac;
    private CRMFHelper helper;
}
