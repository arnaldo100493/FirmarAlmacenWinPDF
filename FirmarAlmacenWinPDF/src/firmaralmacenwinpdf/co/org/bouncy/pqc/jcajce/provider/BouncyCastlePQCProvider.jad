// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BouncyCastlePQCProvider.java

package co.org.bouncy.pqc.jcajce.provider;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.config.ProviderConfiguration;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import co.org.bouncy.jcajce.provider.util.AsymmetricKeyInfoConverter;
import java.io.IOException;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

public class BouncyCastlePQCProvider extends Provider
    implements ConfigurableProvider
{

    public BouncyCastlePQCProvider()
    {
        super(PROVIDER_NAME, 1.48D, info);
        AccessController.doPrivileged(new PrivilegedAction() {

            public Object run()
            {
                setup();
                return null;
            }

            final BouncyCastlePQCProvider this$0;

            
            {
                this$0 = BouncyCastlePQCProvider.this;
                super();
            }
        }
);
    }

    private void setup()
    {
        loadAlgorithms("co.org.bouncy.pqc.jcajce.provider.", ALGORITHMS);
    }

    private void loadAlgorithms(String packageName, String names[])
    {
        for(int i = 0; i != names.length; i++)
        {
            Class clazz = null;
            try
            {
                ClassLoader loader = getClass().getClassLoader();
                if(loader != null)
                    clazz = loader.loadClass((new StringBuilder()).append(packageName).append(names[i]).append("$Mappings").toString());
                else
                    clazz = Class.forName((new StringBuilder()).append(packageName).append(names[i]).append("$Mappings").toString());
            }
            catch(ClassNotFoundException e) { }
            if(clazz == null)
                continue;
            try
            {
                ((AlgorithmProvider)clazz.newInstance()).configure(this);
            }
            catch(Exception e)
            {
                throw new InternalError((new StringBuilder()).append("cannot create instance of ").append(packageName).append(names[i]).append("$Mappings : ").append(e).toString());
            }
        }

    }

    public void setParameter(String parameterName, Object parameter)
    {
        synchronized(CONFIGURATION) { }
    }

    public boolean hasAlgorithm(String type, String name)
    {
        return containsKey((new StringBuilder()).append(type).append(".").append(name).toString()) || containsKey((new StringBuilder()).append("Alg.Alias.").append(type).append(".").append(name).toString());
    }

    public void addAlgorithm(String key, String value)
    {
        if(containsKey(key))
        {
            throw new IllegalStateException((new StringBuilder()).append("duplicate provider key (").append(key).append(") found").toString());
        } else
        {
            put(key, value);
            return;
        }
    }

    public void addKeyInfoConverter(ASN1ObjectIdentifier oid, AsymmetricKeyInfoConverter keyInfoConverter)
    {
        keyInfoConverters.put(oid, keyInfoConverter);
    }

    public static PublicKey getPublicKey(SubjectPublicKeyInfo publicKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(publicKeyInfo.getAlgorithm().getAlgorithm());
        if(converter == null)
            return null;
        else
            return converter.generatePublic(publicKeyInfo);
    }

    public static PrivateKey getPrivateKey(PrivateKeyInfo privateKeyInfo)
        throws IOException
    {
        AsymmetricKeyInfoConverter converter = (AsymmetricKeyInfoConverter)keyInfoConverters.get(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm());
        if(converter == null)
            return null;
        else
            return converter.generatePrivate(privateKeyInfo);
    }

    private static String info = "BouncyCastle Post-Quantum Security Provider v1.48";
    public static String PROVIDER_NAME = "BCPQC";
    public static final ProviderConfiguration CONFIGURATION = null;
    private static final Map keyInfoConverters = new HashMap();
    private static final String ALGORITHM_PACKAGE = "co.org.bouncy.pqc.jcajce.provider.";
    private static final String ALGORITHMS[] = {
        "Rainbow", "McEliece"
    };


}
