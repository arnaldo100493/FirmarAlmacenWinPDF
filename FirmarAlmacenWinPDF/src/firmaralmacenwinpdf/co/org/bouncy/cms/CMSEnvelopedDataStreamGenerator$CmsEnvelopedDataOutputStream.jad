// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.AttributeTable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

// Referenced classes of package co.org.bouncy.cms:
//            CMSEnvelopedDataStreamGenerator, CMSAttributeTableGenerator

private class CMSEnvelopedDataStreamGenerator$CmsEnvelopedDataOutputStream extends OutputStream
{

    public void write(int b)
        throws IOException
    {
        _out.write(b);
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        _out.write(bytes, off, len);
    }

    public void write(byte bytes[])
        throws IOException
    {
        _out.write(bytes);
    }

    public void close()
        throws IOException
    {
        _out.close();
        _eiGen.close();
        if(unprotectedAttributeGenerator != null)
        {
            AttributeTable attrTable = unprotectedAttributeGenerator.getAttributes(new HashMap());
            co.org.bouncy.asn1.ASN1Set unprotectedAttrs = new BERSet(attrTable.toASN1EncodableVector());
            _envGen.addObject(new DERTaggedObject(false, 1, unprotectedAttrs));
        }
        _envGen.close();
        _cGen.close();
    }

    private OutputStream _out;
    private BERSequenceGenerator _cGen;
    private BERSequenceGenerator _envGen;
    private BERSequenceGenerator _eiGen;
    final CMSEnvelopedDataStreamGenerator this$0;

    public CMSEnvelopedDataStreamGenerator$CmsEnvelopedDataOutputStream(OutputStream out, BERSequenceGenerator cGen, BERSequenceGenerator envGen, BERSequenceGenerator eiGen)
    {
        this$0 = CMSEnvelopedDataStreamGenerator.this;
        super();
        _out = out;
        _cGen = cGen;
        _envGen = envGen;
        _eiGen = eiGen;
    }
}
