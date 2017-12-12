// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeyFactorySpi.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.pqc.asn1.McEliecePrivateKey;
import co.org.bouncy.pqc.asn1.McEliecePublicKey;
import co.org.bouncy.pqc.jcajce.spec.McEliecePrivateKeySpec;
import co.org.bouncy.pqc.jcajce.spec.McEliecePublicKeySpec;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            BCMcEliecePublicKey, BCMcEliecePrivateKey

public class McElieceKeyFactorySpi extends KeyFactorySpi
{

    public McElieceKeyFactorySpi()
    {
    }

    public PublicKey generatePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        if(keySpec instanceof McEliecePublicKeySpec)
            return new BCMcEliecePublicKey((McEliecePublicKeySpec)keySpec);
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
                return new BCMcEliecePublicKey(new McEliecePublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", t, n, matrixG));
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
        if(keySpec instanceof McEliecePrivateKeySpec)
            return new BCMcEliecePrivateKey((McEliecePrivateKeySpec)keySpec);
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
                byte encSInv[] = ((ASN1OctetString)privKey.getObjectAt(5)).getOctets();
                byte encP1[] = ((ASN1OctetString)privKey.getObjectAt(6)).getOctets();
                byte encP2[] = ((ASN1OctetString)privKey.getObjectAt(7)).getOctets();
                byte encH[] = ((ASN1OctetString)privKey.getObjectAt(8)).getOctets();
                ASN1Sequence qSeq = (ASN1Sequence)privKey.getObjectAt(9);
                byte encQInv[][] = new byte[qSeq.size()][];
                for(int i = 0; i < qSeq.size(); i++)
                    encQInv[i] = ((ASN1OctetString)qSeq.getObjectAt(i)).getOctets();

                return new BCMcEliecePrivateKey(new McEliecePrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", n, k, encFieldPoly, encGoppaPoly, encSInv, encP1, encP2, encH, encQInv));
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
        if(key instanceof BCMcEliecePrivateKey)
        {
            if(java/security/spec/PKCS8EncodedKeySpec.isAssignableFrom(keySpec))
                return new PKCS8EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/McEliecePrivateKeySpec.isAssignableFrom(keySpec))
            {
                BCMcEliecePrivateKey privKey = (BCMcEliecePrivateKey)key;
                return new McEliecePrivateKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", privKey.getN(), privKey.getK(), privKey.getField(), privKey.getGoppaPoly(), privKey.getSInv(), privKey.getP1(), privKey.getP2(), privKey.getH(), privKey.getQInv());
            }
        } else
        if(key instanceof BCMcEliecePublicKey)
        {
            if(java/security/spec/X509EncodedKeySpec.isAssignableFrom(keySpec))
                return new X509EncodedKeySpec(key.getEncoded());
            if(co/org/bouncy/pqc/jcajce/spec/McEliecePublicKeySpec.isAssignableFrom(keySpec))
            {
                BCMcEliecePublicKey pubKey = (BCMcEliecePublicKey)key;
                return new McEliecePublicKeySpec("1.3.6.1.4.1.8301.3.1.3.4.1", pubKey.getN(), pubKey.getT(), pubKey.getG());
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
        if((key instanceof BCMcEliecePrivateKey) || (key instanceof BCMcEliecePublicKey))
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
            McEliecePublicKey key = McEliecePublicKey.getInstance(innerType);
            return new BCMcEliecePublicKey(key.getOID().getId(), key.getN(), key.getT(), key.getG());
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
            McEliecePrivateKey key = McEliecePrivateKey.getInstance(innerType);
            return new BCMcEliecePrivateKey(key.getOID().getId(), key.getN(), key.getK(), key.getField(), key.getGoppaPoly(), key.getSInv(), key.getP1(), key.getP2(), key.getH(), key.getQInv());
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

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
}
