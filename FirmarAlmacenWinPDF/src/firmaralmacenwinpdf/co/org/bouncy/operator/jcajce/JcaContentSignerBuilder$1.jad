// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentSignerBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.ContentSigner;
import co.org.bouncy.operator.RuntimeOperatorException;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            JcaContentSignerBuilder

class JcaContentSignerBuilder$1
    implements ContentSigner
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return JcaContentSignerBuilder.access$000(JcaContentSignerBuilder.this);
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
        catch(SignatureException e)
        {
            throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private gnatureOutputStream stream;
    final Signature val$sig;
    final JcaContentSignerBuilder this$0;

    JcaContentSignerBuilder$1()
    {
        this$0 = final_jcacontentsignerbuilder;
        val$sig = Signature.this;
        super();
        stream = new gnatureOutputStream(JcaContentSignerBuilder.this, val$sig);
    }
}
