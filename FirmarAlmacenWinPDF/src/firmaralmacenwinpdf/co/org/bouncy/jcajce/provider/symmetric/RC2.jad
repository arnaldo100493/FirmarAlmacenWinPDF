// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RC2.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.RC2CBCParameter;
import co.org.bouncy.crypto.CipherKeyGenerator;
import co.org.bouncy.crypto.engines.RC2Engine;
import co.org.bouncy.crypto.engines.RC2WrapEngine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.macs.CFBBlockCipherMac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.jcajce.provider.config.ConfigurableProvider;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameterGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseAlgorithmParameters;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseKeyGenerator;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseWrapCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.PBESecretKeyFactory;
import co.org.bouncy.jcajce.provider.util.AlgorithmProvider;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;

public final class RC2
{
    public static class Mappings extends AlgorithmProvider
    {

        public void configure(ConfigurableProvider provider)
        {
            provider.addAlgorithm("AlgorithmParameterGenerator.RC2", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm("AlgorithmParameterGenerator.1.2.840.113549.3.2", (new StringBuilder()).append(PREFIX).append("$AlgParamGen").toString());
            provider.addAlgorithm("KeyGenerator.RC2", (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            provider.addAlgorithm("KeyGenerator.1.2.840.113549.3.2", (new StringBuilder()).append(PREFIX).append("$KeyGenerator").toString());
            provider.addAlgorithm("AlgorithmParameters.RC2", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("AlgorithmParameters.1.2.840.113549.3.2", (new StringBuilder()).append(PREFIX).append("$AlgParams").toString());
            provider.addAlgorithm("Cipher.RC2", (new StringBuilder()).append(PREFIX).append("$ECB").toString());
            provider.addAlgorithm("Cipher.RC2WRAP", (new StringBuilder()).append(PREFIX).append("$Wrap").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.id_alg_CMSRC2wrap).toString(), "RC2WRAP");
            provider.addAlgorithm("Cipher.1.2.840.113549.3.2", (new StringBuilder()).append(PREFIX).append("$CBC").toString());
            provider.addAlgorithm("Mac.RC2MAC", (new StringBuilder()).append(PREFIX).append("$CBCMAC").toString());
            provider.addAlgorithm("Alg.Alias.Mac.RC2", "RC2MAC");
            provider.addAlgorithm("Mac.RC2MAC/CFB8", (new StringBuilder()).append(PREFIX).append("$CFB8MAC").toString());
            provider.addAlgorithm("Alg.Alias.Mac.RC2/CFB8", "RC2MAC/CFB8");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD2ANDRC2-CBC", "PBEWITHMD2ANDRC2");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHMD5ANDRC2-CBC", "PBEWITHMD5ANDRC2");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.PBEWITHSHA1ANDRC2-CBC", "PBEWITHSHA1ANDRC2");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC).toString(), "PBEWITHMD2ANDRC2");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC).toString(), "PBEWITHMD5ANDRC2");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.SecretKeyFactory.").append(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC).toString(), "PBEWITHSHA1ANDRC2");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
            provider.addAlgorithm("Alg.Alias.SecretKeyFactory.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
            provider.addAlgorithm("SecretKeyFactory.PBEWITHMD2ANDRC2", (new StringBuilder()).append(PREFIX).append("$PBEWithMD2KeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHMD5ANDRC2", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5KeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHA1ANDRC2", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA1KeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND128BITRC2-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128BitKeyFactory").toString());
            provider.addAlgorithm("SecretKeyFactory.PBEWITHSHAAND40BITRC2-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40BitKeyFactory").toString());
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC).toString(), "PBEWITHMD2ANDRC2");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC).toString(), "PBEWITHMD5ANDRC2");
            provider.addAlgorithm((new StringBuilder()).append("Alg.Alias.Cipher.").append(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC).toString(), "PBEWITHSHA1ANDRC2");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.5", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.1.2.840.113549.1.12.1.6", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWithSHAAnd3KeyTripleDES", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.Cipher.1.2.840.113549.1.12.1.5", "PBEWITHSHAAND128BITRC2-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.1.2.840.113549.1.12.1.6", "PBEWITHSHAAND40BITRC2-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND128BITRC2-CBC", "PBEWITHSHAAND128BITRC2-CBC");
            provider.addAlgorithm("Alg.Alias.Cipher.PBEWITHSHA1AND40BITRC2-CBC", "PBEWITHSHAAND40BITRC2-CBC");
            provider.addAlgorithm("Cipher.PBEWITHSHA1ANDRC2", (new StringBuilder()).append(PREFIX).append("$PBEWithSHA1AndRC2").toString());
            provider.addAlgorithm("Cipher.PBEWITHSHAAND128BITRC2-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd128BitRC2").toString());
            provider.addAlgorithm("Cipher.PBEWITHSHAAND40BITRC2-CBC", (new StringBuilder()).append(PREFIX).append("$PBEWithSHAAnd40BitRC2").toString());
            provider.addAlgorithm("Cipher.PBEWITHMD5ANDRC2", (new StringBuilder()).append(PREFIX).append("$PBEWithMD5AndRC2").toString());
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAANDRC2", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHA1ANDRC2-CBC", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND40BITRC2-CBC", "PKCS12PBE");
            provider.addAlgorithm("Alg.Alias.AlgorithmParameters.PBEWITHSHAAND128BITRC2-CBC", "PKCS12PBE");
        }

        private static final String PREFIX = co/org/bouncy/jcajce/provider/symmetric/RC2.getName();


        public Mappings()
        {
        }
    }

    public static class AlgParams extends BaseAlgorithmParameters
    {

        protected byte[] engineGetEncoded()
        {
            return Arrays.clone(iv);
        }

        protected byte[] engineGetEncoded(String format)
            throws IOException
        {
            if(isASN1FormatString(format))
                if(parameterVersion == -1)
                    return (new RC2CBCParameter(engineGetEncoded())).getEncoded();
                else
                    return (new RC2CBCParameter(parameterVersion, engineGetEncoded())).getEncoded();
            if(format.equals("RAW"))
                return engineGetEncoded();
            else
                return null;
        }

        protected AlgorithmParameterSpec localEngineGetParameterSpec(Class paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec == javax/crypto/spec/RC2ParameterSpec && parameterVersion != -1)
                if(parameterVersion < 256)
                    return new RC2ParameterSpec(ekb[parameterVersion], iv);
                else
                    return new RC2ParameterSpec(parameterVersion, iv);
            if(paramSpec == javax/crypto/spec/IvParameterSpec)
                return new IvParameterSpec(iv);
            else
                throw new InvalidParameterSpecException("unknown parameter spec passed to RC2 parameters object.");
        }

        protected void engineInit(AlgorithmParameterSpec paramSpec)
            throws InvalidParameterSpecException
        {
            if(paramSpec instanceof IvParameterSpec)
                iv = ((IvParameterSpec)paramSpec).getIV();
            else
            if(paramSpec instanceof RC2ParameterSpec)
            {
                int effKeyBits = ((RC2ParameterSpec)paramSpec).getEffectiveKeyBits();
                if(effKeyBits != -1)
                    if(effKeyBits < 256)
                        parameterVersion = table[effKeyBits];
                    else
                        parameterVersion = effKeyBits;
                iv = ((RC2ParameterSpec)paramSpec).getIV();
            } else
            {
                throw new InvalidParameterSpecException("IvParameterSpec or RC2ParameterSpec required to initialise a RC2 parameters algorithm parameters object");
            }
        }

        protected void engineInit(byte params[])
            throws IOException
        {
            iv = Arrays.clone(params);
        }

        protected void engineInit(byte params[], String format)
            throws IOException
        {
            if(isASN1FormatString(format))
            {
                RC2CBCParameter p = RC2CBCParameter.getInstance(ASN1Primitive.fromByteArray(params));
                if(p.getRC2ParameterVersion() != null)
                    parameterVersion = p.getRC2ParameterVersion().intValue();
                iv = p.getIV();
                return;
            }
            if(format.equals("RAW"))
            {
                engineInit(params);
                return;
            } else
            {
                throw new IOException("Unknown parameters format in IV parameters object");
            }
        }

        protected String engineToString()
        {
            return "RC2 Parameters";
        }

        private static final short table[] = {
            189, 86, 234, 242, 162, 241, 172, 42, 176, 147, 
            209, 156, 27, 51, 253, 208, 48, 4, 182, 220, 
            125, 223, 50, 75, 247, 203, 69, 155, 49, 187, 
            33, 90, 65, 159, 225, 217, 74, 77, 158, 218, 
            160, 104, 44, 195, 39, 95, 128, 54, 62, 238, 
            251, 149, 26, 254, 206, 168, 52, 169, 19, 240, 
            166, 63, 216, 12, 120, 36, 175, 35, 82, 193, 
            103, 23, 245, 102, 144, 231, 232, 7, 184, 96, 
            72, 230, 30, 83, 243, 146, 164, 114, 140, 8, 
            21, 110, 134, 0, 132, 250, 244, 127, 138, 66, 
            25, 246, 219, 205, 20, 141, 80, 18, 186, 60, 
            6, 78, 236, 179, 53, 17, 161, 136, 142, 43, 
            148, 153, 183, 113, 116, 211, 228, 191, 58, 222, 
            150, 14, 188, 10, 237, 119, 252, 55, 107, 3, 
            121, 137, 98, 198, 215, 192, 210, 124, 106, 139, 
            34, 163, 91, 5, 93, 2, 117, 213, 97, 227, 
            24, 143, 85, 81, 173, 31, 11, 94, 133, 229, 
            194, 87, 99, 202, 61, 108, 180, 197, 204, 112, 
            178, 145, 89, 13, 71, 32, 200, 79, 88, 224, 
            1, 226, 22, 56, 196, 111, 59, 15, 101, 70, 
            190, 126, 45, 123, 130, 249, 64, 181, 29, 115, 
            248, 235, 38, 199, 135, 151, 37, 84, 177, 40, 
            170, 152, 157, 165, 100, 109, 122, 212, 16, 129, 
            68, 239, 73, 214, 174, 46, 221, 118, 92, 47, 
            167, 28, 201, 9, 105, 154, 131, 207, 41, 57, 
            185, 233, 76, 255, 67, 171
        };
        private static final short ekb[] = {
            93, 190, 155, 139, 17, 153, 110, 77, 89, 243, 
            133, 166, 63, 183, 131, 197, 228, 115, 107, 58, 
            104, 90, 192, 71, 160, 100, 52, 12, 241, 208, 
            82, 165, 185, 30, 150, 67, 65, 216, 212, 44, 
            219, 248, 7, 119, 42, 202, 235, 239, 16, 28, 
            22, 13, 56, 114, 47, 137, 193, 249, 128, 196, 
            109, 174, 48, 61, 206, 32, 99, 254, 230, 26, 
            199, 184, 80, 232, 36, 23, 252, 37, 111, 187, 
            106, 163, 68, 83, 217, 162, 1, 171, 188, 182, 
            31, 152, 238, 154, 167, 45, 79, 158, 142, 172, 
            224, 198, 73, 70, 41, 244, 148, 138, 175, 225, 
            91, 195, 179, 123, 87, 209, 124, 156, 237, 135, 
            64, 140, 226, 203, 147, 20, 201, 97, 46, 229, 
            204, 246, 94, 168, 92, 214, 117, 141, 98, 149, 
            88, 105, 118, 161, 74, 181, 85, 9, 120, 51, 
            130, 215, 221, 121, 245, 27, 11, 222, 38, 33, 
            40, 116, 4, 151, 86, 223, 60, 240, 55, 57, 
            220, 255, 6, 164, 234, 66, 8, 218, 180, 113, 
            176, 207, 18, 122, 78, 250, 108, 29, 132, 0, 
            200, 127, 145, 69, 170, 43, 194, 177, 143, 213, 
            186, 242, 173, 25, 178, 103, 54, 247, 15, 10, 
            146, 125, 227, 157, 233, 144, 62, 35, 39, 102, 
            19, 236, 129, 21, 189, 34, 191, 159, 126, 169, 
            81, 75, 76, 251, 2, 211, 112, 134, 49, 231, 
            59, 5, 3, 84, 96, 72, 101, 24, 210, 205, 
            95, 50, 136, 14, 53, 253
        };
        private byte iv[];
        private int parameterVersion;


        public AlgParams()
        {
            parameterVersion = 58;
        }
    }

    public static class KeyGenerator extends BaseKeyGenerator
    {

        public KeyGenerator()
        {
            super("RC2", 128, new CipherKeyGenerator());
        }
    }

    public static class AlgParamGen extends BaseAlgorithmParameterGenerator
    {

        protected void engineInit(AlgorithmParameterSpec genParamSpec, SecureRandom random)
            throws InvalidAlgorithmParameterException
        {
            if(genParamSpec instanceof RC2ParameterSpec)
            {
                spec = (RC2ParameterSpec)genParamSpec;
                return;
            } else
            {
                throw new InvalidAlgorithmParameterException("No supported AlgorithmParameterSpec for RC2 parameter generation.");
            }
        }

        protected AlgorithmParameters engineGenerateParameters()
        {
            AlgorithmParameters params;
            if(spec == null)
            {
                byte iv[] = new byte[8];
                if(random == null)
                    random = new SecureRandom();
                random.nextBytes(iv);
                try
                {
                    params = AlgorithmParameters.getInstance("RC2", "BC");
                    params.init(new IvParameterSpec(iv));
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e.getMessage());
                }
            } else
            {
                try
                {
                    params = AlgorithmParameters.getInstance("RC2", "BC");
                    params.init(spec);
                }
                catch(Exception e)
                {
                    throw new RuntimeException(e.getMessage());
                }
            }
            return params;
        }

        RC2ParameterSpec spec;

        public AlgParamGen()
        {
            spec = null;
        }
    }

    public static class PBEWithMD5KeyFactory extends PBESecretKeyFactory
    {

        public PBEWithMD5KeyFactory()
        {
            super("PBEwithMD5andRC2", PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC, true, 0, 0, 64, 64);
        }
    }

    public static class PBEWithMD2KeyFactory extends PBESecretKeyFactory
    {

        public PBEWithMD2KeyFactory()
        {
            super("PBEwithMD2andRC2", PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC, true, 0, 5, 64, 64);
        }
    }

    public static class PBEWithSHAAnd40BitRC2 extends BaseBlockCipher
    {

        public PBEWithSHAAnd40BitRC2()
        {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithSHAAnd128BitRC2 extends BaseBlockCipher
    {

        public PBEWithSHAAnd128BitRC2()
        {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithSHA1AndRC2 extends BaseBlockCipher
    {

        public PBEWithSHA1AndRC2()
        {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithMD5AndRC2 extends BaseBlockCipher
    {

        public PBEWithMD5AndRC2()
        {
            super(new CBCBlockCipher(new RC2Engine()));
        }
    }

    public static class PBEWithSHAAnd40BitKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd40BitKeyFactory()
        {
            super("PBEwithSHAand40BitRC2-CBC", PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, true, 2, 1, 40, 64);
        }
    }

    public static class PBEWithSHAAnd128BitKeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHAAnd128BitKeyFactory()
        {
            super("PBEwithSHAand128BitRC2-CBC", PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, true, 2, 1, 128, 64);
        }
    }

    public static class PBEWithSHA1KeyFactory extends PBESecretKeyFactory
    {

        public PBEWithSHA1KeyFactory()
        {
            super("PBEwithSHA1andRC2", PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC, true, 0, 1, 64, 64);
        }
    }

    public static class CFB8MAC extends BaseMac
    {

        public CFB8MAC()
        {
            super(new CFBBlockCipherMac(new RC2Engine()));
        }
    }

    public static class CBCMAC extends BaseMac
    {

        public CBCMAC()
        {
            super(new CBCBlockCipherMac(new RC2Engine()));
        }
    }

    public static class Wrap extends BaseWrapCipher
    {

        public Wrap()
        {
            super(new RC2WrapEngine());
        }
    }

    public static class CBC extends BaseBlockCipher
    {

        public CBC()
        {
            super(new CBCBlockCipher(new RC2Engine()), 64);
        }
    }

    public static class ECB extends BaseBlockCipher
    {

        public ECB()
        {
            super(new RC2Engine());
        }
    }


    private RC2()
    {
    }
}
