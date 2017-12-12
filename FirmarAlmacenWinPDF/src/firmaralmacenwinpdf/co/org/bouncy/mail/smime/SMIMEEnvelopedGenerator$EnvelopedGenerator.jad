// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEnvelopedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1EncodableVector;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.cms.CMSEnvelopedDataStreamGenerator;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEEnvelopedGenerator

private class SMIMEEnvelopedGenerator$EnvelopedGenerator extends CMSEnvelopedDataStreamGenerator
{

    protected OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, ASN1EncodableVector recipientInfos, OutputEncryptor encryptor)
        throws IOException
    {
        this.dataType = dataType;
        this.recipientInfos = recipientInfos;
        return super.open(dataType, out, recipientInfos, encryptor);
    }

    OutputStream regenerate(OutputStream out, OutputEncryptor encryptor)
        throws IOException
    {
        return super.open(dataType, out, recipientInfos, encryptor);
    }

    private ASN1ObjectIdentifier dataType;
    private ASN1EncodableVector recipientInfos;
    final SMIMEEnvelopedGenerator this$0;

    private SMIMEEnvelopedGenerator$EnvelopedGenerator()
    {
        this$0 = SMIMEEnvelopedGenerator.this;
        super();
    }

    SMIMEEnvelopedGenerator$EnvelopedGenerator(SMIMEEnvelopedGenerator._cls1 x1)
    {
        this();
    }
}
