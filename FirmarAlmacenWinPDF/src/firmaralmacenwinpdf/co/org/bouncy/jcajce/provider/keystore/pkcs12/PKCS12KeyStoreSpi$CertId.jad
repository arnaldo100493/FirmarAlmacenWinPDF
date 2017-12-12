// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12KeyStoreSpi.java

package co.org.bouncy.jcajce.provider.keystore.pkcs12;

import co.org.bouncy.asn1.x509.SubjectKeyIdentifier;
import co.org.bouncy.util.Arrays;
import java.security.PublicKey;

// Referenced classes of package co.org.bouncy.jcajce.provider.keystore.pkcs12:
//            PKCS12KeyStoreSpi

private class PKCS12KeyStoreSpi$CertId
{

    public int hashCode()
    {
        return Arrays.hashCode(id);
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof PKCS12KeyStoreSpi$CertId))
        {
            return false;
        } else
        {
            PKCS12KeyStoreSpi$CertId cId = (PKCS12KeyStoreSpi$CertId)o;
            return Arrays.areEqual(id, cId.id);
        }
    }

    byte id[];
    final PKCS12KeyStoreSpi this$0;

    PKCS12KeyStoreSpi$CertId(PublicKey key)
    {
        this$0 = PKCS12KeyStoreSpi.this;
        super();
        id = PKCS12KeyStoreSpi.access$100(PKCS12KeyStoreSpi.this, key).getKeyIdentifier();
    }

    PKCS12KeyStoreSpi$CertId(byte id[])
    {
        this$0 = PKCS12KeyStoreSpi.this;
        super();
        this.id = id;
    }
}
