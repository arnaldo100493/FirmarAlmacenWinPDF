// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubjectKeyIdentifier.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension, Extensions, SubjectPublicKeyInfo

public class SubjectKeyIdentifier extends ASN1Object
{

    public static SubjectKeyIdentifier getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1OctetString.getInstance(obj, explicit));
    }

    public static SubjectKeyIdentifier getInstance(Object obj)
    {
        if(obj instanceof SubjectKeyIdentifier)
            return (SubjectKeyIdentifier)obj;
        if(obj != null)
            return new SubjectKeyIdentifier(ASN1OctetString.getInstance(obj));
        else
            return null;
    }

    public static SubjectKeyIdentifier fromExtensions(Extensions extensions)
    {
        return getInstance(extensions.getExtensionParsedValue(Extension.subjectKeyIdentifier));
    }

    public SubjectKeyIdentifier(byte keyid[])
    {
        keyidentifier = keyid;
    }

    protected SubjectKeyIdentifier(ASN1OctetString keyid)
    {
        keyidentifier = keyid.getOctets();
    }

    public byte[] getKeyIdentifier()
    {
        return keyidentifier;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DEROctetString(keyidentifier);
    }

    /**
     * @deprecated Method SubjectKeyIdentifier is deprecated
     */

    public SubjectKeyIdentifier(SubjectPublicKeyInfo spki)
    {
        keyidentifier = getDigest(spki);
    }

    /**
     * @deprecated Method createSHA1KeyIdentifier is deprecated
     */

    public static SubjectKeyIdentifier createSHA1KeyIdentifier(SubjectPublicKeyInfo keyInfo)
    {
        return new SubjectKeyIdentifier(keyInfo);
    }

    /**
     * @deprecated Method createTruncatedSHA1KeyIdentifier is deprecated
     */

    public static SubjectKeyIdentifier createTruncatedSHA1KeyIdentifier(SubjectPublicKeyInfo keyInfo)
    {
        byte dig[] = getDigest(keyInfo);
        byte id[] = new byte[8];
        System.arraycopy(dig, dig.length - 8, id, 0, id.length);
        id[0] &= 0xf;
        id[0] |= 0x40;
        return new SubjectKeyIdentifier(id);
    }

    private static byte[] getDigest(SubjectPublicKeyInfo spki)
    {
        Digest digest = new SHA1Digest();
        byte resBuf[] = new byte[digest.getDigestSize()];
        byte bytes[] = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        return resBuf;
    }

    private byte keyidentifier[];
}
