// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ZlibExpanderProvider.java

package co.org.bouncy.cms.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.InputExpander;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

// Referenced classes of package co.org.bouncy.cms.jcajce:
//            ZlibExpanderProvider

class ZlibExpanderProvider$1
    implements InputExpander
{

    public AlgorithmIdentifier getAlgorithmIdentifier()
    {
        return val$algorithm;
    }

    public InputStream getInputStream(InputStream comIn)
    {
        InputStream s = new InflaterInputStream(comIn);
        if(ZlibExpanderProvider.access$000(ZlibExpanderProvider.this) >= 0L)
            s = new mitedInputStream(s, ZlibExpanderProvider.access$000(ZlibExpanderProvider.this));
        return s;
    }

    final AlgorithmIdentifier val$algorithm;
    final ZlibExpanderProvider this$0;

    ZlibExpanderProvider$1()
    {
        this$0 = final_zlibexpanderprovider;
        val$algorithm = AlgorithmIdentifier.this;
        super();
    }
}
