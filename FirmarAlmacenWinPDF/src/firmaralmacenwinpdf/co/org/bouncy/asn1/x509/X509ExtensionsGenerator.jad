// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509ExtensionsGenerator.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            X509Extension, X509Extensions

/**
 * @deprecated Class X509ExtensionsGenerator is deprecated
 */

public class X509ExtensionsGenerator
{

    public X509ExtensionsGenerator()
    {
        extensions = new Hashtable();
        extOrdering = new Vector();
    }

    public void reset()
    {
        extensions = new Hashtable();
        extOrdering = new Vector();
    }

    /**
     * @deprecated Method addExtension is deprecated
     */

    public void addExtension(DERObjectIdentifier oid, boolean critical, ASN1Encodable value)
    {
        addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    /**
     * @deprecated Method addExtension is deprecated
     */

    public void addExtension(DERObjectIdentifier oid, boolean critical, byte value[])
    {
        addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable value)
    {
        try
        {
            addExtension(oid, critical, value.toASN1Primitive().getEncoded("DER"));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("error encoding value: ").append(e).toString());
        }
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, byte value[])
    {
        if(extensions.containsKey(oid))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("extension ").append(oid).append(" already added").toString());
        } else
        {
            extOrdering.addElement(oid);
            extensions.put(oid, new X509Extension(critical, new DEROctetString(value)));
            return;
        }
    }

    public boolean isEmpty()
    {
        return extOrdering.isEmpty();
    }

    public X509Extensions generate()
    {
        return new X509Extensions(extOrdering, extensions);
    }

    private Hashtable extensions;
    private Vector extOrdering;
}
