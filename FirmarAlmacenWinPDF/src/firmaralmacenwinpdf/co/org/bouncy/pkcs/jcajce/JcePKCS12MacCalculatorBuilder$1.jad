// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePKCS12MacCalculatorBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.jcajce.io.MacOutputStream;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import java.io.OutputStream;
import javax.crypto.Mac;

// Referenced classes of package co.org.bouncy.pkcs.jcajce:
//            JcePKCS12MacCalculatorBuilder

class JcePKCS12MacCalculatorBuilder$1
    implements MacCalculator
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return new AlgorithmIdentifier(JcePKCS12MacCalculatorBuilder.access$000(JcePKCS12MacCalculatorBuilder.this), new PKCS12PBEParams(val$salt, JcePKCS12MacCalculatorBuilder.access$100(JcePKCS12MacCalculatorBuilder.this)));
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

    final byte val$salt[];
    final Mac val$mac;
    final char val$password[];
    final JcePKCS12MacCalculatorBuilder this$0;

    JcePKCS12MacCalculatorBuilder$1()
    {
        this$0 = final_jcepkcs12maccalculatorbuilder;
        val$salt = abyte0;
        val$mac = mac1;
        val$password = _5B_C.this;
        super();
    }
}
