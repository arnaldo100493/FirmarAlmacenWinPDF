// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEEnvelopedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.cms.RecipientInfoGenerator;
import co.org.bouncy.cms.jcajce.*;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Provider;
import java.util.Iterator;
import java.util.List;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEStreamingProcessor, SMIMEEnvelopedGenerator

private class SMIMEEnvelopedGenerator$ContentEncryptor
    implements SMIMEStreamingProcessor
{

    public void write(OutputStream out)
        throws IOException
    {
        try
        {
            OutputStream encrypted;
            if(_firstTime)
            {
                encrypted = SMIMEEnvelopedGenerator.access$200(SMIMEEnvelopedGenerator.this).open(out, _encryptor);
                _firstTime = false;
            } else
            {
                encrypted = SMIMEEnvelopedGenerator.access$200(SMIMEEnvelopedGenerator.this).regenerate(out, _encryptor);
            }
            _content.getDataHandler().setCommandMap(SMIMEEnvelopedGenerator.access$300(CommandMap.getDefaultCommandMap()));
            _content.writeTo(encrypted);
            encrypted.close();
        }
        catch(MessagingException e)
        {
            throw new on(e.toString(), e);
        }
        catch(CMSException e)
        {
            throw new on(e.toString(), e);
        }
    }

    private final MimeBodyPart _content;
    private OutputEncryptor _encryptor;
    private boolean _firstTime;
    final SMIMEEnvelopedGenerator this$0;

    SMIMEEnvelopedGenerator$ContentEncryptor(MimeBodyPart content, ASN1ObjectIdentifier encryptionOid, int keySize, Provider provider)
        throws CMSException
    {
        this$0 = SMIMEEnvelopedGenerator.this;
        super();
        _firstTime = true;
        _content = content;
        if(keySize == 0)
            _encryptor = (new JceCMSContentEncryptorBuilder(encryptionOid)).setProvider(provider).build();
        else
            _encryptor = (new JceCMSContentEncryptorBuilder(encryptionOid, keySize)).setProvider(provider).build();
        if(provider != null)
        {
            Iterator it = SMIMEEnvelopedGenerator.access$100(SMIMEEnvelopedGenerator.this).iterator();
            do
            {
                if(!it.hasNext())
                    break;
                RecipientInfoGenerator rd = (RecipientInfoGenerator)it.next();
                if(rd instanceof JceKeyTransRecipientInfoGenerator)
                    ((JceKeyTransRecipientInfoGenerator)rd).setProvider(provider);
                else
                if(rd instanceof JceKEKRecipientInfoGenerator)
                    ((JceKEKRecipientInfoGenerator)rd).setProvider(provider);
            } while(true);
        }
    }

    SMIMEEnvelopedGenerator$ContentEncryptor(MimeBodyPart content, OutputEncryptor encryptor)
    {
        this$0 = SMIMEEnvelopedGenerator.this;
        super();
        _firstTime = true;
        _content = content;
        _encryptor = encryptor;
    }
}
