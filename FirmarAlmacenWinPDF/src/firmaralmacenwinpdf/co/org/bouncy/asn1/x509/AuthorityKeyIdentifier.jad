// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthorityKeyIdentifier.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension, Extensions, GeneralNames, SubjectPublicKeyInfo

public class AuthorityKeyIdentifier extends ASN1Object
{

    public static AuthorityKeyIdentifier getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AuthorityKeyIdentifier getInstance(Object obj)
    {
        if(obj instanceof AuthorityKeyIdentifier)
            return (AuthorityKeyIdentifier)obj;
        if(obj != null)
            return new AuthorityKeyIdentifier(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static AuthorityKeyIdentifier fromExtensions(Extensions extensions)
    {
        return getInstance(extensions.getExtensionParsedValue(Extension.authorityKeyIdentifier));
    }

    protected AuthorityKeyIdentifier(ASN1Sequence seq)
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        Enumeration e = seq.getObjects();
        do
        {
            if(!e.hasMoreElements())
                break;
            ASN1TaggedObject o = DERTaggedObject.getInstance(e.nextElement());
            switch(o.getTagNo())
            {
            case 0: // '\0'
                keyidentifier = ASN1OctetString.getInstance(o, false);
                break;

            case 1: // '\001'
                certissuer = GeneralNames.getInstance(o, false);
                break;

            case 2: // '\002'
                certserno = ASN1Integer.getInstance(o, false);
                break;

            default:
                throw new IllegalArgumentException("illegal tag");
            }
        } while(true);
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo spki)
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        Digest digest = new SHA1Digest();
        byte resBuf[] = new byte[digest.getDigestSize()];
        byte bytes[] = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        keyidentifier = new DEROctetString(resBuf);
    }

    public AuthorityKeyIdentifier(SubjectPublicKeyInfo spki, GeneralNames name, BigInteger serialNumber)
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        Digest digest = new SHA1Digest();
        byte resBuf[] = new byte[digest.getDigestSize()];
        byte bytes[] = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        keyidentifier = new DEROctetString(resBuf);
        certissuer = GeneralNames.getInstance(name.toASN1Primitive());
        certserno = new ASN1Integer(serialNumber);
    }

    public AuthorityKeyIdentifier(GeneralNames name, BigInteger serialNumber)
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        keyidentifier = null;
        certissuer = GeneralNames.getInstance(name.toASN1Primitive());
        certserno = new ASN1Integer(serialNumber);
    }

    public AuthorityKeyIdentifier(byte keyIdentifier[])
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        keyidentifier = new DEROctetString(keyIdentifier);
        certissuer = null;
        certserno = null;
    }

    public AuthorityKeyIdentifier(byte keyIdentifier[], GeneralNames name, BigInteger serialNumber)
    {
        keyidentifier = null;
        certissuer = null;
        certserno = null;
        keyidentifier = new DEROctetString(keyIdentifier);
        certissuer = GeneralNames.getInstance(name.toASN1Primitive());
        certserno = new ASN1Integer(serialNumber);
    }

    public byte[] getKeyIdentifier()
    {
        if(keyidentifier != null)
            return keyidentifier.getOctets();
        else
            return null;
    }

    public GeneralNames getAuthorityCertIssuer()
    {
        return certissuer;
    }

    public BigInteger getAuthorityCertSerialNumber()
    {
        if(certserno != null)
            return certserno.getValue();
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(keyidentifier != null)
            v.add(new DERTaggedObject(false, 0, keyidentifier));
        if(certissuer != null)
            v.add(new DERTaggedObject(false, 1, certissuer));
        if(certserno != null)
            v.add(new DERTaggedObject(false, 2, certserno));
        return new DERSequence(v);
    }

    public String toString()
    {
        return (new StringBuilder()).append("AuthorityKeyIdentifier: KeyID(").append(keyidentifier.getOctets()).append(")").toString();
    }

    ASN1OctetString keyidentifier;
    GeneralNames certissuer;
    ASN1Integer certserno;
}
