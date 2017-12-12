// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PasswordRecipientInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.PasswordRecipientInfo;
import co.org.bouncy.asn1.cms.RecipientInfo;
import co.org.bouncy.asn1.pkcs.PBKDF2Params;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.PBEParametersGenerator;
import co.org.bouncy.crypto.generators.PKCS5S2ParametersGenerator;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.GenericKey;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInfoGenerator, CMSException, PasswordRecipientInformation

public abstract class PasswordRecipientInfoGenerator
    implements RecipientInfoGenerator
{

    protected PasswordRecipientInfoGenerator(ASN1ObjectIdentifier kekAlgorithm, char password[])
    {
        this(kekAlgorithm, password, getKeySize(kekAlgorithm), ((Integer)PasswordRecipientInformation.BLOCKSIZES.get(kekAlgorithm)).intValue());
    }

    protected PasswordRecipientInfoGenerator(ASN1ObjectIdentifier kekAlgorithm, char password[], int keySize, int blockSize)
    {
        this.password = password;
        schemeID = 1;
        this.kekAlgorithm = kekAlgorithm;
        this.keySize = keySize;
        this.blockSize = blockSize;
    }

    private static int getKeySize(ASN1ObjectIdentifier kekAlgorithm)
    {
        Integer size = (Integer)PasswordRecipientInformation.KEYSIZES.get(kekAlgorithm);
        if(size == null)
            throw new IllegalArgumentException((new StringBuilder()).append("cannot find key size for algorithm: ").append(kekAlgorithm).toString());
        else
            return size.intValue();
    }

    public PasswordRecipientInfoGenerator setPasswordConversionScheme(int schemeID)
    {
        this.schemeID = schemeID;
        return this;
    }

    public PasswordRecipientInfoGenerator setSaltAndIterationCount(byte salt[], int iterationCount)
    {
        keyDerivationAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(salt, iterationCount));
        return this;
    }

    public PasswordRecipientInfoGenerator setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public RecipientInfo generate(GenericKey contentEncryptionKey)
        throws CMSException
    {
        byte iv[] = new byte[blockSize];
        if(random == null)
            random = new SecureRandom();
        random.nextBytes(iv);
        if(keyDerivationAlgorithm == null)
        {
            byte salt[] = new byte[20];
            random.nextBytes(salt);
            keyDerivationAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(salt, 1024));
        }
        PBKDF2Params params = PBKDF2Params.getInstance(keyDerivationAlgorithm.getParameters());
        byte derivedKey[];
        if(schemeID == 0)
        {
            PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator();
            gen.init(PBEParametersGenerator.PKCS5PasswordToBytes(password), params.getSalt(), params.getIterationCount().intValue());
            derivedKey = ((KeyParameter)gen.generateDerivedParameters(keySize)).getKey();
        } else
        {
            PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator();
            gen.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(password), params.getSalt(), params.getIterationCount().intValue());
            derivedKey = ((KeyParameter)gen.generateDerivedParameters(keySize)).getKey();
        }
        AlgorithmIdentifier kekAlgorithmId = new AlgorithmIdentifier(kekAlgorithm, new DEROctetString(iv));
        byte encryptedKeyBytes[] = generateEncryptedBytes(kekAlgorithmId, derivedKey, contentEncryptionKey);
        co.org.bouncy.asn1.ASN1OctetString encryptedKey = new DEROctetString(encryptedKeyBytes);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(kekAlgorithm);
        v.add(new DEROctetString(iv));
        AlgorithmIdentifier keyEncryptionAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_PWRI_KEK, new DERSequence(v));
        return new RecipientInfo(new PasswordRecipientInfo(keyDerivationAlgorithm, keyEncryptionAlgorithm, encryptedKey));
    }

    protected abstract byte[] generateEncryptedBytes(AlgorithmIdentifier algorithmidentifier, byte abyte0[], GenericKey generickey)
        throws CMSException;

    private char password[];
    private AlgorithmIdentifier keyDerivationAlgorithm;
    private ASN1ObjectIdentifier kekAlgorithm;
    private SecureRandom random;
    private int schemeID;
    private int keySize;
    private int blockSize;
}
