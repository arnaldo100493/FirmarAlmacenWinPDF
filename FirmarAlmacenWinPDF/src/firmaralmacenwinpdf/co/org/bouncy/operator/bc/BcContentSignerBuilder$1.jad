// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentSignerBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcSignerOutputStream, BcContentSignerBuilder

class BcContentSignerBuilder$1
    implements ContentSigner
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return BcContentSignerBuilder.access$000(BcContentSignerBuilder.this);
    }

    public OutputStream getOutputStream()
    {
        return stream;
    }

    public byte[] getSignature()
    {
        try
        {
            return stream.getSignature();
        }
        catch(CryptoException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private BcSignerOutputStream stream;
    final Signer val$sig;
    final BcContentSignerBuilder this$0;

    BcContentSignerBuilder$1()
    {
        this$0 = final_bccontentsignerbuilder;
        val$sig = Signer.this;
        super();
        stream = new BcSignerOutputStream(val$sig);
    }
}
