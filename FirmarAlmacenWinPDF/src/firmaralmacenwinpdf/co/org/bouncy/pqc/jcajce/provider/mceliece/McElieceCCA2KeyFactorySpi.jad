// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2KeyFactorySpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.pqc.asn1.McElieceCCA2PrivateKey;
import co.org.bouncy.pqc.asn1.McElieceCCA2PublicKey;
import co.org.bouncy.pqc.jcajce.spec.McElieceCCA2PrivateKeySpec;
import co.org.bouncy.pqc.jcajce.spec.McElieceCCA2PublicKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            BCMcElieceCCA2PublicKey, BCMcElieceCCA2PrivateKey

public class McElieceCCA2KeyFactorySpi extends KeyFactorySpi
{

    public McElieceCCA2KeyFactorySpi()
    {
    }

    public PublicKey generatePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof McElieceCCA2PublicKeySpec)
            return new BCMcElieceCCA2PublicKey((McElieceCCA2PublicKeySpec)keySpec);
        if(keySpec instanceof X509EncodedKeySpec)
        {
            byte encKey[] = ((X509EncodedKeySpec)keySpec).getEncoded();
            SubjectPublicKeyInfo pki;
            try
            {
                pki = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(encKey));
            }
            catch(IOException e)
            {
                throw new InvalidKeySpecException(e.toString());
            }
            try
            {
                ASN1Primitive innerType = pki.parsePublicKey();
                ASN1Sequence publicKey = (ASN1Sequence)innerType;
                String oidString = ((ASN1ObjectIdentifier)publicKey.getObjectAt(0)).toString();
                BigInteger bigN = ((ASN1Integer)publicKey.getObjectAt(1)).getValue();
                int n = bigN.intValue();
                BigInteger bigT = ((ASN1Integer)publicKey.getObjectAt(2)).getValue();
                int t = bigT.intValue();
                byte matrixG[] = ((ASN1OctetString)publicKey.getObjectAt(3)).getOctets();
                return new BCMcElieceCCA2PublicKey(new McElieceCCA2PublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.2", n, t, matrixG));
            }
            catch(IOException cce)
            {
                throw new InvalidKeySpecException((new StringBuilder()).append("Unable to decode X509EncodedKeySpec: ").append(cce.getMessage()).toString());
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key specification: ").append(keySpec.getClass()).append(".").toString());
        }
    }

    public PrivateKey generatePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof McElieceCCA2PrivateKeySpec)
            return new BCMcElieceCCA2PrivateKey((McElieceCCA2PrivateKeySpec)keySpec);
        if(keySpec instanceof PKCS8EncodedKeySpec)
        {
            byte encKey[] = ((PKCS8EncodedKeySpec)keySpec).getEncoded();
            PrivateKeyInfo pki;
            try
            {
                pki = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(encKey));
            }
            catch(IOException e)
            {
                throw new InvalidKeySpecException((new StringBuilder()).append("Unable to decode PKCS8EncodedKeySpec: ").append(e).toString());
            }
            try
            {
                ASN1Primitive innerType = pki.parsePrivateKey().toASN1Primitive();
                ASN1Sequence privKey = (ASN1Sequence)innerType;
                String oidString = ((ASN1ObjectIdentifier)privKey.getObjectAt(0)).toString();
                BigInteger bigN = ((ASN1Integer)privKey.getObjectAt(1)).getValue();
                int n = bigN.intValue();
                BigInteger bigK = ((ASN1Integer)privKey.getObjectAt(2)).getValue();
                int k = bigK.intValue();
                byte encFieldPoly[] = ((ASN1OctetString)privKey.getObjectAt(3)).getOctets();
                byte encGoppaPoly[] = ((ASN1OctetString)privKey.getObjectAt(4)).getOctets();
                byte encP[] = ((ASN1OctetString)privKey.getObjectAt(5)).getOctets();
                byte encH[] = ((ASN1OctetString)privKey.getObjectAt(6)).getOctets();
                ASN1Sequence qSeq = (ASN1Sequence)privKey.getObjectAt(7);
                byte encQInv[][] = new byte[qSeq.size()][];
                for(int i = 0; i < qSeq.size(); i++)
                    encQInv[i] = ((ASN1OctetString)qSeq.getObjectAt(i)).getOctets();

                return new BCMcElieceCCA2PrivateKey(new McElieceCCA2PrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.2", n, k, encFieldPoly, encGoppaPoly, encP, encH, encQInv));
            }
            catch(IOException cce)
            {
                throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec.");
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key specification: ").append(keySpec.getClass()).append(".").toString());
        }
    }

    public KeySpec getKeySpec(Key key, Class keySpec)
        throws InvalidKeySpecException
    {
        if(key instanceof BCMcElieceCCA2PrivateKey)
        {
            if(java/security/spec/PKCS8EncodedKeySpec.isAssignableFrom(keySpec))
                return new PKCS8EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/McElieceCCA2PrivateKeySpec.isAssignableFrom(keySpec))
            {
                BCMcElieceCCA2PrivateKey privKey = (BCMcElieceCCA2PrivateKey)key;
                return new McElieceCCA2PrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.2", privKey.getN(), privKey.getK(), privKey.getField(), privKey.getGoppaPoly(), privKey.getP(), privKey.getH(), privKey.getQInv());
            }
        } else
        if(key instanceof BCMcElieceCCA2PublicKey)
        {
            if(java/security/spec/X509EncodedKeySpec.isAssignableFrom(keySpec))
                return new X509EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/McElieceCCA2PublicKeySpec.isAssignableFrom(keySpec))
            {
                BCMcElieceCCA2PublicKey pubKey = (BCMcElieceCCA2PublicKey)key;
                return new McElieceCCA2PublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.2", pubKey.getN(), pubKey.getT(), pubKey.getG());
            }
        } else
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("Unsupported key type: ").append(key.getClass()).append(".").toString());
        }
        throw new InvalidKeySpecException((new StringBuilder()).append("Unknown key specification: ").append(keySpec).append(".").toString());
    }

    public Key translateKey(Key key)
        throws InvalidKeyException
    {
        if((key instanceof BCMcElieceCCA2PrivateKey) || (key instanceof BCMcElieceCCA2PublicKey))
            return key;
        else
            throw new InvalidKeyException("Unsupported key type.");
    }

    public PublicKey generatePublic(SubjectPublicKeyInfo pki)
        throws InvalidKeySpecException
    {
        try
        {
            ASN1Primitive innerType = pki.parsePublicKey();
            McElieceCCA2PublicKey key = McElieceCCA2PublicKey.getInstance((ASN1Sequence)innerType);
            return new BCMcElieceCCA2PublicKey(key.getOID().getId(), key.getN(), key.getT(), key.getG());
        }
        catch(IOException cce)
        {
            throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec");
        }
    }

    public PrivateKey generatePrivate(PrivateKeyInfo pki)
        throws InvalidKeySpecException
    {
        try
        {
            ASN1Primitive innerType = pki.parsePrivateKey().toASN1Primitive();
            McElieceCCA2PrivateKey key = McElieceCCA2PrivateKey.getInstance(innerType);
            return new BCMcElieceCCA2PrivateKey(key.getOID().getId(), key.getN(), key.getK(), key.getField(), key.getGoppaPoly(), key.getP(), key.getH(), key.getQInv());
        }
        catch(IOException cce)
        {
            throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec");
        }
    }

    protected PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        return null;
    }

    protected PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        return null;
    }

    protected KeySpec engineGetKeySpec(Key key, Class tClass)
        throws InvalidKeySpecException
    {
        return null;
    }

    protected Key engineTranslateKey(Key key)
        throws InvalidKeyException
    {
        return null;
    }

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";
}
