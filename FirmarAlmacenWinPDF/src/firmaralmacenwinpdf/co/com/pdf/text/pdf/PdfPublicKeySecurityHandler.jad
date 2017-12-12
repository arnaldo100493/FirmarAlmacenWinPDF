// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPublicKeySecurityHandler.java

package co.com.pdf.text.pdf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import javax.crypto.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPublicKeyRecipient, PdfArray, PdfLiteral, PdfContentByte

public class PdfPublicKeySecurityHandler
{

    public PdfPublicKeySecurityHandler()
    {
        recipients = null;
        seed = new byte[20];
        try
        {
            KeyGenerator key = KeyGenerator.getInstance("AES");
            key.init(192, new SecureRandom());
            SecretKey sk = key.generateKey();
            System.arraycopy(sk.getEncoded(), 0, seed, 0, 20);
        }
        catch(NoSuchAlgorithmException e)
        {
            seed = SecureRandom.getSeed(20);
        }
        recipients = new ArrayList();
    }

    public void addRecipient(PdfPublicKeyRecipient recipient)
    {
        recipients.add(recipient);
    }

    protected byte[] getSeed()
    {
        return (byte[])seed.clone();
    }

    public int getRecipientsSize()
    {
        return recipients.size();
    }

    public byte[] getEncodedRecipient(int index)
        throws IOException, GeneralSecurityException
    {
        PdfPublicKeyRecipient recipient = (PdfPublicKeyRecipient)recipients.get(index);
        byte cms[] = recipient.getCms();
        if(cms != null)
        {
            return cms;
        } else
        {
            Certificate certificate = recipient.getCertificate();
            int permission = recipient.getPermission();
            int revision = 3;
            permission |= revision != 3 ? -64 : 0xfffff0c0;
            permission &= -4;
            permission++;
            byte pkcs7input[] = new byte[24];
            byte one = (byte)permission;
            byte two = (byte)(permission >> 8);
            byte three = (byte)(permission >> 16);
            byte four = (byte)(permission >> 24);
            System.arraycopy(seed, 0, pkcs7input, 0, 20);
            pkcs7input[20] = four;
            pkcs7input[21] = three;
            pkcs7input[22] = two;
            pkcs7input[23] = one;
            ASN1Primitive obj = createDERForRecipient(pkcs7input, (X509Certificate)certificate);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DEROutputStream k = new DEROutputStream(baos);
            k.writeObject(obj);
            cms = baos.toByteArray();
            recipient.setCms(cms);
            return cms;
        }
    }

    public PdfArray getEncodedRecipients()
        throws IOException, GeneralSecurityException
    {
        PdfArray EncodedRecipients = new PdfArray();
        byte cms[] = null;
        for(int i = 0; i < recipients.size(); i++)
            try
            {
                cms = getEncodedRecipient(i);
                EncodedRecipients.add(new PdfLiteral(PdfContentByte.escapeString(cms)));
            }
            catch(GeneralSecurityException e)
            {
                EncodedRecipients = null;
            }
            catch(IOException e)
            {
                EncodedRecipients = null;
            }

        return EncodedRecipients;
    }

    private ASN1Primitive createDERForRecipient(byte in[], X509Certificate cert)
        throws IOException, GeneralSecurityException
    {
        String s = "1.2.840.113549.3.2";
        AlgorithmParameterGenerator algorithmparametergenerator = AlgorithmParameterGenerator.getInstance(s);
        AlgorithmParameters algorithmparameters = algorithmparametergenerator.generateParameters();
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(algorithmparameters.getEncoded("ASN.1"));
        ASN1InputStream asn1inputstream = new ASN1InputStream(bytearrayinputstream);
        ASN1Primitive derobject = asn1inputstream.readObject();
        KeyGenerator keygenerator = KeyGenerator.getInstance(s);
        keygenerator.init(128);
        SecretKey secretkey = keygenerator.generateKey();
        Cipher cipher = Cipher.getInstance(s);
        cipher.init(1, secretkey, algorithmparameters);
        byte abyte1[] = cipher.doFinal(in);
        DEROctetString deroctetstring = new DEROctetString(abyte1);
        KeyTransRecipientInfo keytransrecipientinfo = computeRecipientInfo(cert, secretkey.getEncoded());
        DERSet derset = new DERSet(new RecipientInfo(keytransrecipientinfo));
        AlgorithmIdentifier algorithmidentifier = new AlgorithmIdentifier(new ASN1ObjectIdentifier(s), derobject);
        EncryptedContentInfo encryptedcontentinfo = new EncryptedContentInfo(PKCSObjectIdentifiers.data, algorithmidentifier, deroctetstring);
        co.org.bouncy.asn1.ASN1Set set = null;
        EnvelopedData env = new EnvelopedData(null, derset, encryptedcontentinfo, set);
        ContentInfo contentinfo = new ContentInfo(PKCSObjectIdentifiers.envelopedData, env);
        return contentinfo.toASN1Primitive();
    }

    private KeyTransRecipientInfo computeRecipientInfo(X509Certificate x509certificate, byte abyte0[])
        throws GeneralSecurityException, IOException
    {
        ASN1InputStream asn1inputstream = new ASN1InputStream(new ByteArrayInputStream(x509certificate.getTBSCertificate()));
        TBSCertificateStructure tbscertificatestructure = TBSCertificateStructure.getInstance(asn1inputstream.readObject());
        AlgorithmIdentifier algorithmidentifier = tbscertificatestructure.getSubjectPublicKeyInfo().getAlgorithm();
        IssuerAndSerialNumber issuerandserialnumber = new IssuerAndSerialNumber(tbscertificatestructure.getIssuer(), tbscertificatestructure.getSerialNumber().getValue());
        Cipher cipher = Cipher.getInstance(algorithmidentifier.getAlgorithm().getId());
        try
        {
            cipher.init(1, x509certificate);
        }
        catch(InvalidKeyException e)
        {
            cipher.init(1, x509certificate.getPublicKey());
        }
        DEROctetString deroctetstring = new DEROctetString(cipher.doFinal(abyte0));
        RecipientIdentifier recipId = new RecipientIdentifier(issuerandserialnumber);
        return new KeyTransRecipientInfo(recipId, algorithmidentifier, deroctetstring);
    }

    static final int SEED_LENGTH = 20;
    private ArrayList recipients;
    private byte seed[];
}
