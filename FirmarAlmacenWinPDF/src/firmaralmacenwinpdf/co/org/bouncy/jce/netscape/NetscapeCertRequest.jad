// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NetscapeCertRequest.java

package co.org.bouncy.jce.netscape;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class NetscapeCertRequest extends ASN1Object
{

    private static ASN1Sequence getReq(byte r[])
        throws IOException
    {
        ASN1InputStream aIn = new ASN1InputStream(new ByteArrayInputStream(r));
        return ASN1Sequence.getInstance(aIn.readObject());
    }

    public NetscapeCertRequest(byte req[])
        throws IOException
    {
        this(getReq(req));
    }

    public NetscapeCertRequest(ASN1Sequence spkac)
    {
        try
        {
            if(spkac.size() != 3)
                throw new IllegalArgumentException((new StringBuilder()).append("invalid SPKAC (size):").append(spkac.size()).toString());
            sigAlg = new AlgorithmIdentifier((ASN1Sequence)spkac.getObjectAt(1));
            sigBits = ((DERBitString)spkac.getObjectAt(2)).getBytes();
            ASN1Sequence pkac = (ASN1Sequence)spkac.getObjectAt(0);
            if(pkac.size() != 2)
                throw new IllegalArgumentException((new StringBuilder()).append("invalid PKAC (len): ").append(pkac.size()).toString());
            challenge = ((DERIA5String)pkac.getObjectAt(1)).getString();
            content = new DERBitString(pkac);
            SubjectPublicKeyInfo pubkeyinfo = new SubjectPublicKeyInfo((ASN1Sequence)pkac.getObjectAt(0));
            X509EncodedKeySpec xspec = new X509EncodedKeySpec((new DERBitString(pubkeyinfo)).getBytes());
            keyAlg = pubkeyinfo.getAlgorithmId();
            pubkey = KeyFactory.getInstance(keyAlg.getObjectId().getId(), "BC").generatePublic(xspec);
        }
        catch(Exception e)
        {
            throw new IllegalArgumentException(e.toString());
        }
    }

    public NetscapeCertRequest(String challenge, AlgorithmIdentifier signing_alg, PublicKey pub_key)
        throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException
    {
        this.challenge = challenge;
        sigAlg = signing_alg;
        pubkey = pub_key;
        ASN1EncodableVector content_der = new ASN1EncodableVector();
        content_der.add(getKeySpec());
        content_der.add(new DERIA5String(challenge));
        try
        {
            content = new DERBitString(new DERSequence(content_der));
        }
        catch(IOException e)
        {
            throw new InvalidKeySpecException((new StringBuilder()).append("exception encoding key: ").append(e.toString()).toString());
        }
    }

    public String getChallenge()
    {
        return challenge;
    }

    public void setChallenge(String value)
    {
        challenge = value;
    }

    public AlgorithmIdentifier getSigningAlgorithm()
    {
        return sigAlg;
    }

    public void setSigningAlgorithm(AlgorithmIdentifier value)
    {
        sigAlg = value;
    }

    public AlgorithmIdentifier getKeyAlgorithm()
    {
        return keyAlg;
    }

    public void setKeyAlgorithm(AlgorithmIdentifier value)
    {
        keyAlg = value;
    }

    public PublicKey getPublicKey()
    {
        return pubkey;
    }

    public void setPublicKey(PublicKey value)
    {
        pubkey = value;
    }

    public boolean verify(String challenge)
        throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException
    {
        if(!challenge.equals(this.challenge))
        {
            return false;
        } else
        {
            Signature sig = Signature.getInstance(sigAlg.getObjectId().getId(), "BC");
            sig.initVerify(pubkey);
            sig.update(content.getBytes());
            return sig.verify(sigBits);
        }
    }

    public void sign(PrivateKey priv_key)
        throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, InvalidKeySpecException
    {
        sign(priv_key, null);
    }

    public void sign(PrivateKey priv_key, SecureRandom rand)
        throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException, InvalidKeySpecException
    {
        Signature sig = Signature.getInstance(sigAlg.getAlgorithm().getId(), "BC");
        if(rand != null)
            sig.initSign(priv_key, rand);
        else
            sig.initSign(priv_key);
        ASN1EncodableVector pkac = new ASN1EncodableVector();
        pkac.add(getKeySpec());
        pkac.add(new DERIA5String(challenge));
        try
        {
            sig.update((new DERSequence(pkac)).getEncoded("DER"));
        }
        catch(IOException ioe)
        {
            throw new SignatureException(ioe.getMessage());
        }
        sigBits = sig.sign();
    }

    private ASN1Primitive getKeySpec()
        throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ASN1Primitive obj = null;
        try
        {
            baos.write(pubkey.getEncoded());
            baos.close();
            ASN1InputStream derin = new ASN1InputStream(new ByteArrayInputStream(baos.toByteArray()));
            obj = derin.readObject();
        }
        catch(IOException ioe)
        {
            throw new InvalidKeySpecException(ioe.getMessage());
        }
        return obj;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector spkac = new ASN1EncodableVector();
        ASN1EncodableVector pkac = new ASN1EncodableVector();
        try
        {
            pkac.add(getKeySpec());
        }
        catch(Exception e) { }
        pkac.add(new DERIA5String(challenge));
        spkac.add(new DERSequence(pkac));
        spkac.add(sigAlg);
        spkac.add(new DERBitString(sigBits));
        return new DERSequence(spkac);
    }

    AlgorithmIdentifier sigAlg;
    AlgorithmIdentifier keyAlg;
    byte sigBits[];
    String challenge;
    DERBitString content;
    PublicKey pubkey;
}
