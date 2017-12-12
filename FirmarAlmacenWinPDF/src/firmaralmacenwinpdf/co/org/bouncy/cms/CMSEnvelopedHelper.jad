// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedHelper.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1Set;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.util.Integers;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.*;
import javax.crypto.KeyGenerator;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformationStore, KeyTransRecipientInformation, KEKRecipientInformation, PasswordRecipientInformation, 
//            KeyAgreeRecipientInformation, CMSEnvelopedGenerator, CMSSecureReadable, AuthAttributesProvider, 
//            CMSException, CMSReadable

class CMSEnvelopedHelper
{
    static class CMSEnvelopedSecureReadable
        implements CMSSecureReadable
    {

        public InputStream getInputStream()
            throws IOException, CMSException
        {
            return readable.getInputStream();
        }

        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSEnvelopedSecureReadable(AlgorithmIdentifier algorithm, CMSReadable readable)
        {
            this.algorithm = algorithm;
            this.readable = readable;
        }
    }

    static class CMSAuthenticatedSecureReadable
        implements CMSSecureReadable
    {

        public InputStream getInputStream()
            throws IOException, CMSException
        {
            return readable.getInputStream();
        }

        private AlgorithmIdentifier algorithm;
        private CMSReadable readable;

        CMSAuthenticatedSecureReadable(AlgorithmIdentifier algorithm, CMSReadable readable)
        {
            this.algorithm = algorithm;
            this.readable = readable;
        }
    }

    static class CMSDigestAuthenticatedSecureReadable
        implements CMSSecureReadable
    {

        public InputStream getInputStream()
            throws IOException, CMSException
        {
            return new FilterInputStream(readable.getInputStream()) {

                public int read()
                    throws IOException
                {
                    int b = in.read();
                    if(b >= 0)
                        digestCalculator.getOutputStream().write(b);
                    return b;
                }

                public int read(byte inBuf[], int inOff, int inLen)
                    throws IOException
                {
                    int n = in.read(inBuf, inOff, inLen);
                    if(n >= 0)
                        digestCalculator.getOutputStream().write(inBuf, inOff, n);
                    return n;
                }

                final CMSDigestAuthenticatedSecureReadable this$0;

                
                {
                    this$0 = CMSDigestAuthenticatedSecureReadable.this;
                    super(x0);
                }
            }
;
        }

        public byte[] getDigest()
        {
            return digestCalculator.getDigest();
        }

        private DigestCalculator digestCalculator;
        private CMSReadable readable;


        public CMSDigestAuthenticatedSecureReadable(DigestCalculator digestCalculator, CMSReadable readable)
        {
            this.digestCalculator = digestCalculator;
            this.readable = readable;
        }
    }


    CMSEnvelopedHelper()
    {
    }

    KeyGenerator createSymmetricKeyGenerator(String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException
    {
        try
        {
            return createKeyGenerator(encryptionOID, provider);
        }
        catch(NoSuchAlgorithmException e)
        {
            try
            {
                String algName = (String)BASE_CIPHER_NAMES.get(encryptionOID);
                if(algName != null)
                    return createKeyGenerator(algName, provider);
            }
            catch(NoSuchAlgorithmException ex) { }
            if(provider != null)
                return createSymmetricKeyGenerator(encryptionOID, null);
            else
                throw e;
        }
    }

    int getKeySize(String oid)
    {
        Integer keySize = (Integer)KEYSIZES.get(oid);
        if(keySize == null)
            throw new IllegalArgumentException((new StringBuilder()).append("no keysize for ").append(oid).toString());
        else
            return keySize.intValue();
    }

    private KeyGenerator createKeyGenerator(String algName, Provider provider)
        throws NoSuchAlgorithmException
    {
        if(provider != null)
            return KeyGenerator.getInstance(algName, provider);
        else
            return KeyGenerator.getInstance(algName);
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set recipientInfos, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable)
    {
        return buildRecipientInformationStore(recipientInfos, messageAlgorithm, secureReadable, null);
    }

    static RecipientInformationStore buildRecipientInformationStore(ASN1Set recipientInfos, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        List infos = new ArrayList();
        for(int i = 0; i != recipientInfos.size(); i++)
        {
            RecipientInfo info = RecipientInfo.getInstance(recipientInfos.getObjectAt(i));
            readRecipientInfo(infos, info, messageAlgorithm, secureReadable, additionalData);
        }

        return new RecipientInformationStore(infos);
    }

    private static void readRecipientInfo(List infos, RecipientInfo info, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        ASN1Encodable recipInfo = info.getInfo();
        if(recipInfo instanceof KeyTransRecipientInfo)
            infos.add(new KeyTransRecipientInformation((KeyTransRecipientInfo)recipInfo, messageAlgorithm, secureReadable, additionalData));
        else
        if(recipInfo instanceof KEKRecipientInfo)
            infos.add(new KEKRecipientInformation((KEKRecipientInfo)recipInfo, messageAlgorithm, secureReadable, additionalData));
        else
        if(recipInfo instanceof KeyAgreeRecipientInfo)
            KeyAgreeRecipientInformation.readRecipientInfo(infos, (KeyAgreeRecipientInfo)recipInfo, messageAlgorithm, secureReadable, additionalData);
        else
        if(recipInfo instanceof PasswordRecipientInfo)
            infos.add(new PasswordRecipientInformation((PasswordRecipientInfo)recipInfo, messageAlgorithm, secureReadable, additionalData));
    }

    static final CMSEnvelopedHelper INSTANCE = new CMSEnvelopedHelper();
    private static final Map KEYSIZES;
    private static final Map BASE_CIPHER_NAMES;
    private static final Map CIPHER_ALG_NAMES;
    private static final Map MAC_ALG_NAMES;

    static 
    {
        KEYSIZES = new HashMap();
        BASE_CIPHER_NAMES = new HashMap();
        CIPHER_ALG_NAMES = new HashMap();
        MAC_ALG_NAMES = new HashMap();
        KEYSIZES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSEnvelopedGenerator.AES128_CBC, Integers.valueOf(128));
        KEYSIZES.put(CMSEnvelopedGenerator.AES192_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSEnvelopedGenerator.AES256_CBC, Integers.valueOf(256));
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, "AES");
        BASE_CIPHER_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, "AES");
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, "AES/CBC/PKCS5Padding");
        CIPHER_ALG_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, "AES/CBC/PKCS5Padding");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.DES_EDE3_CBC, "DESEDEMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES128_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES192_CBC, "AESMac");
        MAC_ALG_NAMES.put(CMSEnvelopedGenerator.AES256_CBC, "AESMac");
    }
}
