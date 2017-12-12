// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMReader, PEMUtilities, PasswordFinder

private class PEMReader$EncryptedPrivateKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        EncryptedPrivateKeyInfo info;
        AlgorithmIdentifier algId;
        try
        {
            info = EncryptedPrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(obj.getContent()));
            algId = info.getEncryptionAlgorithm();
            if(PEMReader.access$400(PEMReader.this) == null)
                throw new PEMException("no PasswordFinder specified");
            if(PEMUtilities.isPKCS5Scheme2(algId.getAlgorithm()))
            {
                PBES2Parameters params = PBES2Parameters.getInstance(algId.getParameters());
                KeyDerivationFunc func = params.getKeyDerivationFunc();
                EncryptionScheme scheme = params.getEncryptionScheme();
                PBKDF2Params defParams = (PBKDF2Params)func.getParameters();
                int iterationCount = defParams.getIterationCount().intValue();
                byte salt[] = defParams.getSalt();
                String algorithm = scheme.getAlgorithm().getId();
                javax.crypto.SecretKey key = PEMReader.generateSecretKeyForPKCS5Scheme2(algorithm, PEMReader.access$400(PEMReader.this).getPassword(), salt, iterationCount);
                Cipher cipher = Cipher.getInstance(algorithm, symProvider);
                AlgorithmParameters algParams = AlgorithmParameters.getInstance(algorithm, symProvider);
                algParams.init(scheme.getParameters().toASN1Primitive().getEncoded());
                cipher.init(2, key, algParams);
                PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
                KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
                return keyFact.generatePrivate(keySpec);
            }
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("problem parsing ENCRYPTED PRIVATE KEY: ").append(e.toString()).toString(), e);
        }
        if(PEMUtilities.isPKCS12(algId.getAlgorithm()))
        {
            PKCS12PBEParams params = PKCS12PBEParams.getInstance(algId.getParameters());
            String algorithm = algId.getAlgorithm().getId();
            PBEKeySpec pbeSpec = new PBEKeySpec(PEMReader.access$400(PEMReader.this).getPassword());
            SecretKeyFactory secKeyFact = SecretKeyFactory.getInstance(algorithm, symProvider);
            PBEParameterSpec defParams = new PBEParameterSpec(params.getIV(), params.getIterations().intValue());
            Cipher cipher = Cipher.getInstance(algorithm, symProvider);
            cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
            PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
            KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
            return keyFact.generatePrivate(keySpec);
        }
        if(PEMUtilities.isPKCS5Scheme1(algId.getAlgorithm()))
        {
            PBEParameter params = PBEParameter.getInstance(algId.getParameters());
            String algorithm = algId.getAlgorithm().getId();
            PBEKeySpec pbeSpec = new PBEKeySpec(PEMReader.access$400(PEMReader.this).getPassword());
            SecretKeyFactory secKeyFact = SecretKeyFactory.getInstance(algorithm, symProvider);
            PBEParameterSpec defParams = new PBEParameterSpec(params.getSalt(), params.getIterationCount().intValue());
            Cipher cipher = Cipher.getInstance(algorithm, symProvider);
            cipher.init(2, secKeyFact.generateSecret(pbeSpec), defParams);
            PrivateKeyInfo pInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(cipher.doFinal(info.getEncryptedData())));
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pInfo.getEncoded());
            KeyFactory keyFact = KeyFactory.getInstance(pInfo.getPrivateKeyAlgorithm().getAlgorithm().getId(), asymProvider);
            return keyFact.generatePrivate(keySpec);
        }
        throw new PEMException((new StringBuilder()).append("Unknown algorithm: ").append(algId.getAlgorithm()).toString());
    }

    private String symProvider;
    private String asymProvider;
    final PEMReader this$0;

    public PEMReader$EncryptedPrivateKeyParser(String symProvider, String asymProvider)
    {
        this$0 = PEMReader.this;
        super();
        this.symProvider = symProvider;
        this.asymProvider = asymProvider;
    }
}
