// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CVCertificateRequest.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.io.IOException;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            CertificateBody, PublicKeyDataObject

public class CVCertificateRequest extends ASN1Object
{

    private CVCertificateRequest(DERApplicationSpecific request)
        throws IOException
    {
        innerSignature = null;
        outerSignature = null;
        signOid = null;
        keyOid = null;
        certificate = null;
        overSignerReference = null;
        iso7816PubKey = null;
        if(request.getApplicationTag() == 103)
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(request.getObject(16));
            initCertBody(DERApplicationSpecific.getInstance(seq.getObjectAt(0)));
            outerSignature = DERApplicationSpecific.getInstance(seq.getObjectAt(seq.size() - 1)).getContents();
        } else
        {
            initCertBody(request);
        }
    }

    private void initCertBody(DERApplicationSpecific request)
        throws IOException
    {
        if(request.getApplicationTag() == 33)
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(request.getObject(16));
            Enumeration en = seq.getObjects();
            do
            {
                if(!en.hasMoreElements())
                    break;
                DERApplicationSpecific obj = DERApplicationSpecific.getInstance(en.nextElement());
                switch(obj.getApplicationTag())
                {
                case 78: // 'N'
                    certificateBody = CertificateBody.getInstance(obj);
                    valid |= bodyValid;
                    break;

                case 55: // '7'
                    innerSignature = obj.getContents();
                    valid |= signValid;
                    break;

                default:
                    throw new IOException((new StringBuilder()).append("Invalid tag, not an CV Certificate Request element:").append(obj.getApplicationTag()).toString());
                }
            } while(true);
        } else
        {
            throw new IOException((new StringBuilder()).append("not a CARDHOLDER_CERTIFICATE in request:").append(request.getApplicationTag()).toString());
        }
    }

    public static CVCertificateRequest getInstance(Object obj)
    {
        if(obj instanceof CVCertificateRequest)
            return (CVCertificateRequest)obj;
        if(obj != null)
            try
            {
                return new CVCertificateRequest(DERApplicationSpecific.getInstance(obj));
            }
            catch(IOException e)
            {
                throw new ASN1ParsingException((new StringBuilder()).append("unable to parse data: ").append(e.getMessage()).toString(), e);
            }
        else
            return null;
    }

    public CertificateBody getCertificateBody()
    {
        return certificateBody;
    }

    public PublicKeyDataObject getPublicKey()
    {
        return certificateBody.getPublicKey();
    }

    public byte[] getInnerSignature()
    {
        return innerSignature;
    }

    public byte[] getOuterSignature()
    {
        return outerSignature;
    }

    public boolean hasOuterSignature()
    {
        return outerSignature != null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certificateBody);
        try
        {
            v.add(new DERApplicationSpecific(false, 55, new DEROctetString(innerSignature)));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("unable to convert signature!");
        }
        return new DERApplicationSpecific(33, v);
    }

    private CertificateBody certificateBody;
    private byte innerSignature[];
    private byte outerSignature[];
    private int valid;
    private static int bodyValid = 1;
    private static int signValid = 2;
    ASN1ObjectIdentifier signOid;
    ASN1ObjectIdentifier keyOid;
    public static byte ZeroArray[] = {
        0
    };
    String strCertificateHolderReference;
    byte encodedAuthorityReference[];
    int ProfileId;
    byte certificate[];
    protected String overSignerReference;
    byte encoded[];
    PublicKeyDataObject iso7816PubKey;

}
