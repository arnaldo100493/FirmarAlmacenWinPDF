// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMESignedGenerator.java

package co.org.bouncy.mail.smime;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.cms.*;
import co.org.bouncy.mail.smime.util.CRLFOutputStream;
import co.org.bouncy.util.Store;
import co.org.bouncy.x509_.X509Store;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.util.*;
import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;

// Referenced classes of package co.org.bouncy.mail.smime:
//            SMIMEStreamingProcessor, SMIMESignedGenerator, SMIMEUtil

private class SMIMESignedGenerator$ContentSigner
    implements SMIMEStreamingProcessor
{

    protected CMSSignedDataStreamGenerator getGenerator()
        throws CMSException, CertStoreException, InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException
    {
        CMSSignedDataStreamGenerator gen = new CMSSignedDataStreamGenerator();
        for(Iterator it = SMIMESignedGenerator.access$000(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addCertificatesAndCRLs((CertStore)it.next()));
        for(Iterator it = SMIMESignedGenerator.access$100(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addCertificates((Store)it.next()));
        for(Iterator it = SMIMESignedGenerator.access$200(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addCRLs((Store)it.next()));
        for(Iterator it = SMIMESignedGenerator.access$300(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addAttributeCertificates((Store)it.next()));
        for(Iterator it = SMIMESignedGenerator.access$400(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addAttributeCertificates((X509Store)it.next()));
        for(Iterator it = SMIMESignedGenerator.access$500(SMIMESignedGenerator.this).iterator(); it.hasNext();)
        {
            SMIMESignedGenerator.Signer signer = (SMIMESignedGenerator.Signer)it.next();
            if(signer.getEncryptionOID() != null)
                gen.addSigner(signer.getKey(), signer.getCert(), signer.getEncryptionOID().getId(), signer.getDigestOID().getId(), signer.getSignedAttr(), signer.getUnsignedAttr(), provider);
            else
                gen.addSigner(signer.getKey(), signer.getCert(), signer.getDigestOID().getId(), signer.getSignedAttr(), signer.getUnsignedAttr(), provider);
        }

        for(Iterator it = SMIMESignedGenerator.access$600(SMIMESignedGenerator.this).iterator(); it.hasNext(); gen.addSignerInfoGenerator((SignerInfoGenerator)it.next()));
        gen.addSigners(new SignerInformationStore(SMIMESignedGenerator.access$700(SMIMESignedGenerator.this)));
        return gen;
    }

    private void writeBodyPart(OutputStream out, MimeBodyPart bodyPart)
        throws IOException, MessagingException
    {
        if(bodyPart.getContent() instanceof Multipart)
        {
            Multipart mp = (Multipart)bodyPart.getContent();
            ContentType contentType = new ContentType(mp.getContentType());
            String boundary = (new StringBuilder()).append("--").append(contentType.getParameter("boundary")).toString();
            SMIMEUtil.LineOutputStream lOut = new SMIMEUtil.LineOutputStream(out);
            for(Enumeration headers = bodyPart.getAllHeaderLines(); headers.hasMoreElements(); lOut.writeln((String)headers.nextElement()));
            lOut.writeln();
            SMIMEUtil.outputPreamble(lOut, bodyPart, boundary);
            for(int i = 0; i < mp.getCount(); i++)
            {
                lOut.writeln(boundary);
                writeBodyPart(out, (MimeBodyPart)mp.getBodyPart(i));
                lOut.writeln();
            }

            lOut.writeln((new StringBuilder()).append(boundary).append("--").toString());
        } else
        {
            if(SMIMEUtil.isCanonicalisationRequired(bodyPart, SMIMESignedGenerator.access$800(SMIMESignedGenerator.this)))
                out = new CRLFOutputStream(out);
            bodyPart.writeTo(out);
        }
    }

    public void write(OutputStream out)
        throws IOException
    {
        try
        {
            CMSSignedDataStreamGenerator gen = getGenerator();
            OutputStream signingStream = gen.open(out, encapsulate);
            if(content != null)
                if(!encapsulate)
                {
                    writeBodyPart(signingStream, content);
                } else
                {
                    content.getDataHandler().setCommandMap(SMIMESignedGenerator.access$900(CommandMap.getDefaultCommandMap()));
                    content.writeTo(signingStream);
                }
            signingStream.close();
            SMIMESignedGenerator.access$1002(SMIMESignedGenerator.this, gen.getGeneratedDigests());
        }
        catch(MessagingException e)
        {
            throw new IOException(e.toString());
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IOException(e.toString());
        }
        catch(NoSuchProviderException e)
        {
            throw new IOException(e.toString());
        }
        catch(CMSException e)
        {
            throw new IOException(e.toString());
        }
        catch(InvalidKeyException e)
        {
            throw new IOException(e.toString());
        }
        catch(CertStoreException e)
        {
            throw new IOException(e.toString());
        }
    }

    private final MimeBodyPart content;
    private final boolean encapsulate;
    private final Provider provider;
    private final boolean noProvider;
    final SMIMESignedGenerator this$0;

    SMIMESignedGenerator$ContentSigner(MimeBodyPart content, boolean encapsulate, Provider provider)
    {
        this$0 = SMIMESignedGenerator.this;
        super();
        this.content = content;
        this.encapsulate = encapsulate;
        this.provider = provider;
        noProvider = false;
    }

    SMIMESignedGenerator$ContentSigner(MimeBodyPart content, boolean encapsulate)
    {
        this$0 = SMIMESignedGenerator.this;
        super();
        this.content = content;
        this.encapsulate = encapsulate;
        provider = null;
        noProvider = true;
    }
}
