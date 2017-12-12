// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PasswordRecipientInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.PasswordRecipientInfo;
import co.org.bouncy.asn1.pkcs.PBKDF2Params;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceAlgorithmIdentifierConverter;
import co.org.bouncy.cms.jcajce.JcePasswordAuthenticatedRecipient;
import co.org.bouncy.cms.jcajce.JcePasswordEnvelopedRecipient;
import co.org.bouncy.cms.jcajce.JcePasswordRecipient;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.generators.PKCS5S2ParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.util.Integers;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformation, PasswordRecipientId, CMSPBEKey, PKCS5Scheme2UTF8PBEKey, 
//            CMSException, PasswordRecipient, CMSUtils, CMSEnvelopedHelper, 
//            CMSAlgorithm, CMSSecureReadable, AuthAttributesProvider, CMSTypedStream, 
//            Recipient, RecipientOperator

public class PasswordRecipientInformation extends RecipientInformation
{

    PasswordRecipientInformation(PasswordRecipientInfo info, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        super(info.getKeyEncryptionAlgorithm(), messageAlgorithm, secureReadable, additionalData);
        this.info = info;
        rid = new PasswordRecipientId();
    }

    public String getKeyDerivationAlgOID()
    {
        if(info.getKeyDerivationAlgorithm() != null)
            return info.getKeyDerivationAlgorithm().getAlgorithm().getId();
        else
            return null;
    }

    public byte[] getKeyDerivationAlgParams()
    {
        try
        {
            if(info.getKeyDerivationAlgorithm() != null)
            {
                ASN1Encodable params = info.getKeyDerivationAlgorithm().getParameters();
                if(params != null)
                    return params.toASN1Primitive().getEncoded();
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
        return null;
    }

    public AlgorithmIdentifier getKeyDerivationAlgorithm()
    {
        return info.getKeyDerivationAlgorithm();
    }

    /**
     * @deprecated Method getKeyDerivationAlgParameters is deprecated
     */

    public AlgorithmParameters getKeyDerivationAlgParameters(String provider)
        throws NoSuchProviderException
    {
        return getKeyDerivationAlgParameters(CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method getKeyDerivationAlgParameters is deprecated
     */

    public AlgorithmParameters getKeyDerivationAlgParameters(Provider provider)
    {
        try
        {
            return (new JceAlgorithmIdentifierConverter()).setProvider(provider).getAlgorithmParameters(info.getKeyDerivationAlgorithm());
        }
        catch(Exception e)
        {
            throw new RuntimeException((new StringBuilder()).append("exception getting encryption parameters ").append(e).toString());
        }
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

    public CMSTypedStream getContentStream(Key key, String prov)
        throws CMSException, NoSuchProviderException
    {
        return getContentStream(key, CMSUtils.getProvider(prov));
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

    public CMSTypedStream getContentStream(Key key, Provider prov)
        throws CMSException
    {
        try
        {
            CMSPBEKey pbeKey = (CMSPBEKey)key;
            JcePasswordRecipient recipient;
            if(secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable)
                recipient = new JcePasswordEnvelopedRecipient(pbeKey.getPassword());
            else
                recipient = new JcePasswordAuthenticatedRecipient(pbeKey.getPassword());
            recipient.setPasswordConversionScheme((pbeKey instanceof PKCS5Scheme2UTF8PBEKey) ? 1 : 0);
            if(prov != null)
                recipient.setProvider(prov);
            return getContentStream(((Recipient) (recipient)));
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("encoding error: ").append(e.getMessage()).toString(), e);
        }
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient)
        throws CMSException, IOException
    {
        PasswordRecipient pbeRecipient = (PasswordRecipient)recipient;
        AlgorithmIdentifier kekAlg = AlgorithmIdentifier.getInstance(info.getKeyEncryptionAlgorithm());
        AlgorithmIdentifier kekAlgParams = AlgorithmIdentifier.getInstance(kekAlg.getParameters());
        byte passwordBytes[] = getPasswordBytes(pbeRecipient.getPasswordConversionScheme(), pbeRecipient.getPassword());
        PBKDF2Params params = PBKDF2Params.getInstance(info.getKeyDerivationAlgorithm().getParameters());
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator();
        gen.init(passwordBytes, params.getSalt(), params.getIterationCount().intValue());
        int keySize = ((Integer)KEYSIZES.get(kekAlgParams.getAlgorithm())).intValue();
        byte derivedKey[] = ((KeyParameter)gen.generateDerivedParameters(keySize)).getKey();
        return pbeRecipient.getRecipientOperator(kekAlgParams, messageAlgorithm, derivedKey, info.getEncryptedKey().getOctets());
    }

    protected byte[] getPasswordBytes(int scheme, char password[])
    {
        if(scheme == 0)
            return PBEParametersGenerator.PKCS5PasswordToBytes(password);
        else
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password);
    }

    static Map KEYSIZES;
    static Map BLOCKSIZES;
    private PasswordRecipientInfo info;

    static 
    {
        KEYSIZES = new HashMap();
        BLOCKSIZES = new HashMap();
        BLOCKSIZES.put(CMSAlgorithm.DES_EDE3_CBC, Integers.valueOf(8));
        BLOCKSIZES.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(16));
        BLOCKSIZES.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(16));
        BLOCKSIZES.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(16));
        KEYSIZES.put(CMSAlgorithm.DES_EDE3_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSAlgorithm.AES128_CBC, Integers.valueOf(128));
        KEYSIZES.put(CMSAlgorithm.AES192_CBC, Integers.valueOf(192));
        KEYSIZES.put(CMSAlgorithm.AES256_CBC, Integers.valueOf(256));
    }
}
