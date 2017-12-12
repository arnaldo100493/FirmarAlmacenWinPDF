// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCS12MacCalculatorBuilderProvider.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.jcajce.JcaJceHelper;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.*;
import co.org.bouncy.pkcs.PKCS12MacCalculatorBuilder;
import java.io.OutputStream;
import java.math.BigInteger;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.pkcs.jcajce:
//            JcePKCS12MacCalculatorBuilderProvider

class JcePKCS12MacCalculatorBuilderProvider$1$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(val$algorithm, val$pbeParams);
    }

    public OutputStream getOutputStream()
    {
        return new MacOutputStream(val$mac);
    }

    public byte[] getMac()
    {
        return val$mac.doFinal();
    }

    public GenericKey getKey()
    {
        return new GenericKey(getAlgorithmIdentifier(), PKCS12ParametersGenerator.PKCS12PasswordToBytes(val$password));
    }

    final ASN1ObjectIdentifier val$algorithm;
    final PKCS12PBEParams val$pbeParams;
    final Mac val$mac;
    final char val$password[];
    final JcePKCS12MacCalculatorBuilderProvider._cls1 this$1;

    JcePKCS12MacCalculatorBuilderProvider$1$1()
    {
        this$1 = final__pcls1;
        val$algorithm = asn1objectidentifier;
        val$pbeParams = pkcs12pbeparams;
        val$mac = mac1;
        val$password = _5B_C.this;
        super();
    }

    // Unreferenced inner class co/org/bouncy/pkcs/jcajce/JcePKCS12MacCalculatorBuilderProvider$1

/* anonymous class */
    class JcePKCS12MacCalculatorBuilderProvider._cls1
        implements PKCS12MacCalculatorBuilder
    {

        public MacCalculator build(char password[])
            throws OperatorCreationException
        {
            final PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            try
            {
                final ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                final Mac mac = JcePKCS12MacCalculatorBuilderProvider.access$000(JcePKCS12MacCalculatorBuilderProvider.this).createMac(algorithm.getId());
                SecretKeyFactory keyFact = JcePKCS12MacCalculatorBuilderProvider.access$000(JcePKCS12MacCalculatorBuilderProvider.this).createSecretKeyFactory(algorithm.getId());
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                PBEKeySpec pbeSpec = new PBEKeySpec(password);
                javax.crypto.SecretKey key = keyFact.generateSecret(pbeSpec);
                mac.init(key, defParams);
                return password. new JcePKCS12MacCalculatorBuilderProvider._cls1._cls1();
            }
            catch(Exception e)
            {
                throw new OperatorCreationException((new StringBuilder()).append("unable to create MAC calculator: ").append(e.getMessage()).toString(), e);
            }
        }

        public AlgorithmIdentifier getDigestAlgorithmIdentifier()
        {
            return new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE);
        }

        final AlgorithmIdentifier val$algorithmIdentifier;
        final JcePKCS12MacCalculatorBuilderProvider this$0;

            
            {
                this$0 = final_jcepkcs12maccalculatorbuilderprovider;
                algorithmIdentifier = AlgorithmIdentifier.this;
                super();
            }
    }

}
