// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Dump.java

package co.org.bouncy.asn1.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.encoders.Hex;
import java.io.IOException;
import java.util.Enumeration;

public class ASN1Dump
{

    public ASN1Dump()
    {
    }

    static void _dumpAsString(String indent, boolean verbose, ASN1Primitive obj, StringBuffer buf)
    {
        String nl = System.getProperty("line.separator");
        if(obj instanceof ASN1Sequence)
        {
            Enumeration e = ((ASN1Sequence)obj).getObjects();
            String tab = (new StringBuilder()).append(indent).append("    ").toString();
            buf.append(indent);
            if(obj instanceof BERSequence)
                buf.append("BER Sequence");
            else
            if(obj instanceof DERSequence)
                buf.append("DER Sequence");
            else
                buf.append("Sequence");
            buf.append(nl);
            while(e.hasMoreElements()) 
            {
                Object o = e.nextElement();
                if(o == null || o.equals(DERNull.INSTANCE))
                {
                    buf.append(tab);
                    buf.append("NULL");
                    buf.append(nl);
                } else
                if(o instanceof ASN1Primitive)
                    _dumpAsString(tab, verbose, (ASN1Primitive)o, buf);
                else
                    _dumpAsString(tab, verbose, ((ASN1Encodable)o).toASN1Primitive(), buf);
            }
        } else
        if(obj instanceof ASN1TaggedObject)
        {
            String tab = (new StringBuilder()).append(indent).append("    ").toString();
            buf.append(indent);
            if(obj instanceof BERTaggedObject)
                buf.append("BER Tagged [");
            else
                buf.append("Tagged [");
            ASN1TaggedObject o = (ASN1TaggedObject)obj;
            buf.append(Integer.toString(o.getTagNo()));
            buf.append(']');
            if(!o.isExplicit())
                buf.append(" IMPLICIT ");
            buf.append(nl);
            if(o.isEmpty())
            {
                buf.append(tab);
                buf.append("EMPTY");
                buf.append(nl);
            } else
            {
                _dumpAsString(tab, verbose, o.getObject(), buf);
            }
        } else
        if(obj instanceof ASN1Set)
        {
            Enumeration e = ((ASN1Set)obj).getObjects();
            String tab = (new StringBuilder()).append(indent).append("    ").toString();
            buf.append(indent);
            if(obj instanceof BERSet)
                buf.append("BER Set");
            else
                buf.append("DER Set");
            buf.append(nl);
            while(e.hasMoreElements()) 
            {
                Object o = e.nextElement();
                if(o == null)
                {
                    buf.append(tab);
                    buf.append("NULL");
                    buf.append(nl);
                } else
                if(o instanceof ASN1Primitive)
                    _dumpAsString(tab, verbose, (ASN1Primitive)o, buf);
                else
                    _dumpAsString(tab, verbose, ((ASN1Encodable)o).toASN1Primitive(), buf);
            }
        } else
        if(obj instanceof ASN1OctetString)
        {
            ASN1OctetString oct = (ASN1OctetString)obj;
            if((obj instanceof BEROctetString) || (obj instanceof BERConstructedOctetString))
                buf.append((new StringBuilder()).append(indent).append("BER Constructed Octet String").append("[").append(oct.getOctets().length).append("] ").toString());
            else
                buf.append((new StringBuilder()).append(indent).append("DER Octet String").append("[").append(oct.getOctets().length).append("] ").toString());
            if(verbose)
                buf.append(dumpBinaryDataAsString(indent, oct.getOctets()));
            else
                buf.append(nl);
        } else
        if(obj instanceof ASN1ObjectIdentifier)
            buf.append((new StringBuilder()).append(indent).append("ObjectIdentifier(").append(((ASN1ObjectIdentifier)obj).getId()).append(")").append(nl).toString());
        else
        if(obj instanceof DERBoolean)
            buf.append((new StringBuilder()).append(indent).append("Boolean(").append(((DERBoolean)obj).isTrue()).append(")").append(nl).toString());
        else
        if(obj instanceof ASN1Integer)
            buf.append((new StringBuilder()).append(indent).append("Integer(").append(((ASN1Integer)obj).getValue()).append(")").append(nl).toString());
        else
        if(obj instanceof DERBitString)
        {
            DERBitString bt = (DERBitString)obj;
            buf.append((new StringBuilder()).append(indent).append("DER Bit String").append("[").append(bt.getBytes().length).append(", ").append(bt.getPadBits()).append("] ").toString());
            if(verbose)
                buf.append(dumpBinaryDataAsString(indent, bt.getBytes()));
            else
                buf.append(nl);
        } else
        if(obj instanceof DERIA5String)
            buf.append((new StringBuilder()).append(indent).append("IA5String(").append(((DERIA5String)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERUTF8String)
            buf.append((new StringBuilder()).append(indent).append("UTF8String(").append(((DERUTF8String)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERPrintableString)
            buf.append((new StringBuilder()).append(indent).append("PrintableString(").append(((DERPrintableString)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERVisibleString)
            buf.append((new StringBuilder()).append(indent).append("VisibleString(").append(((DERVisibleString)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERBMPString)
            buf.append((new StringBuilder()).append(indent).append("BMPString(").append(((DERBMPString)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERT61String)
            buf.append((new StringBuilder()).append(indent).append("T61String(").append(((DERT61String)obj).getString()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERUTCTime)
            buf.append((new StringBuilder()).append(indent).append("UTCTime(").append(((DERUTCTime)obj).getTime()).append(") ").append(nl).toString());
        else
        if(obj instanceof DERGeneralizedTime)
            buf.append((new StringBuilder()).append(indent).append("GeneralizedTime(").append(((DERGeneralizedTime)obj).getTime()).append(") ").append(nl).toString());
        else
        if(obj instanceof BERApplicationSpecific)
            buf.append(outputApplicationSpecific("BER", indent, verbose, obj, nl));
        else
        if(obj instanceof DERApplicationSpecific)
            buf.append(outputApplicationSpecific("DER", indent, verbose, obj, nl));
        else
        if(obj instanceof DEREnumerated)
        {
            DEREnumerated en = (DEREnumerated)obj;
            buf.append((new StringBuilder()).append(indent).append("DER Enumerated(").append(en.getValue()).append(")").append(nl).toString());
        } else
        if(obj instanceof DERExternal)
        {
            DERExternal ext = (DERExternal)obj;
            buf.append((new StringBuilder()).append(indent).append("External ").append(nl).toString());
            String tab = (new StringBuilder()).append(indent).append("    ").toString();
            if(ext.getDirectReference() != null)
                buf.append((new StringBuilder()).append(tab).append("Direct Reference: ").append(ext.getDirectReference().getId()).append(nl).toString());
            if(ext.getIndirectReference() != null)
                buf.append((new StringBuilder()).append(tab).append("Indirect Reference: ").append(ext.getIndirectReference().toString()).append(nl).toString());
            if(ext.getDataValueDescriptor() != null)
                _dumpAsString(tab, verbose, ext.getDataValueDescriptor(), buf);
            buf.append((new StringBuilder()).append(tab).append("Encoding: ").append(ext.getEncoding()).append(nl).toString());
            _dumpAsString(tab, verbose, ext.getExternalContent(), buf);
        } else
        {
            buf.append((new StringBuilder()).append(indent).append(obj.toString()).append(nl).toString());
        }
    }

    private static String outputApplicationSpecific(String type, String indent, boolean verbose, ASN1Primitive obj, String nl)
    {
        DERApplicationSpecific app = (DERApplicationSpecific)obj;
        StringBuffer buf = new StringBuffer();
        if(app.isConstructed())
        {
            try
            {
                ASN1Sequence s = ASN1Sequence.getInstance(app.getObject(16));
                buf.append((new StringBuilder()).append(indent).append(type).append(" ApplicationSpecific[").append(app.getApplicationTag()).append("]").append(nl).toString());
                for(Enumeration e = s.getObjects(); e.hasMoreElements(); _dumpAsString((new StringBuilder()).append(indent).append("    ").toString(), verbose, (ASN1Primitive)e.nextElement(), buf));
            }
            catch(IOException e)
            {
                buf.append(e);
            }
            return buf.toString();
        } else
        {
            return (new StringBuilder()).append(indent).append(type).append(" ApplicationSpecific[").append(app.getApplicationTag()).append("] (").append(new String(Hex.encode(app.getContents()))).append(")").append(nl).toString();
        }
    }

    public static String dumpAsString(Object obj)
    {
        return dumpAsString(obj, false);
    }

    public static String dumpAsString(Object obj, boolean verbose)
    {
        StringBuffer buf = new StringBuffer();
        if(obj instanceof ASN1Primitive)
            _dumpAsString("", verbose, (ASN1Primitive)obj, buf);
        else
        if(obj instanceof ASN1Encodable)
            _dumpAsString("", verbose, ((ASN1Encodable)obj).toASN1Primitive(), buf);
        else
            return (new StringBuilder()).append("unknown object type ").append(obj.toString()).toString();
        return buf.toString();
    }

    private static String dumpBinaryDataAsString(String indent, byte bytes[])
    {
        String nl = System.getProperty("line.separator");
        StringBuffer buf = new StringBuffer();
        indent = (new StringBuilder()).append(indent).append("    ").toString();
        buf.append(nl);
        for(int i = 0; i < bytes.length; i += 32)
        {
            if(bytes.length - i > 32)
            {
                buf.append(indent);
                buf.append(new String(Hex.encode(bytes, i, 32)));
                buf.append("    ");
                buf.append(calculateAscString(bytes, i, 32));
                buf.append(nl);
                continue;
            }
            buf.append(indent);
            buf.append(new String(Hex.encode(bytes, i, bytes.length - i)));
            for(int j = bytes.length - i; j != 32; j++)
                buf.append("  ");

            buf.append("    ");
            buf.append(calculateAscString(bytes, i, bytes.length - i));
            buf.append(nl);
        }

        return buf.toString();
    }

    private static String calculateAscString(byte bytes[], int off, int len)
    {
        StringBuffer buf = new StringBuffer();
        for(int i = off; i != off + len; i++)
            if(bytes[i] >= 32 && bytes[i] <= 126)
                buf.append((char)bytes[i]);

        return buf.toString();
    }

    private static final String TAB = "    ";
    private static final int SAMPLE_SIZE = 32;
}
