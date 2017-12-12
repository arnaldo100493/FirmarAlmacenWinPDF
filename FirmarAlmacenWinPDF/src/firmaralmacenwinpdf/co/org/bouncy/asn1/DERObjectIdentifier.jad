// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERObjectIdentifier.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1ObjectIdentifier, ASN1Encodable, OIDTokenizer, 
//            ASN1TaggedObject, ASN1OctetString, StreamUtil, ASN1OutputStream

public class DERObjectIdentifier extends ASN1Primitive
{

    public static ASN1ObjectIdentifier getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1ObjectIdentifier))
            return (ASN1ObjectIdentifier)obj;
        if(obj instanceof DERObjectIdentifier)
            return new ASN1ObjectIdentifier(((DERObjectIdentifier)obj).getId());
        if((obj instanceof ASN1Encodable) && (((ASN1Encodable)obj).toASN1Primitive() instanceof ASN1ObjectIdentifier))
            return (ASN1ObjectIdentifier)((ASN1Encodable)obj).toASN1Primitive();
        if(obj instanceof byte[])
            return ASN1ObjectIdentifier.fromOctetString((byte[])(byte[])obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1ObjectIdentifier getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERObjectIdentifier))
            return getInstance(o);
        else
            return ASN1ObjectIdentifier.fromOctetString(ASN1OctetString.getInstance(obj.getObject()).getOctets());
    }

    DERObjectIdentifier(byte bytes[])
    {
        StringBuffer objId = new StringBuffer();
        long value = 0L;
        BigInteger bigValue = null;
        boolean first = true;
        for(int i = 0; i != bytes.length; i++)
        {
            int b = bytes[i] & 0xff;
            if(value <= 0xffffffffffff80L)
            {
                value += b & 0x7f;
                if((b & 0x80) == 0)
                {
                    if(first)
                    {
                        if(value < 40L)
                            objId.append('0');
                        else
                        if(value < 80L)
                        {
                            objId.append('1');
                            value -= 40L;
                        } else
                        {
                            objId.append('2');
                            value -= 80L;
                        }
                        first = false;
                    }
                    objId.append('.');
                    objId.append(value);
                    value = 0L;
                } else
                {
                    value <<= 7;
                }
                continue;
            }
            if(bigValue == null)
                bigValue = BigInteger.valueOf(value);
            bigValue = bigValue.or(BigInteger.valueOf(b & 0x7f));
            if((b & 0x80) == 0)
            {
                if(first)
                {
                    objId.append('2');
                    bigValue = bigValue.subtract(BigInteger.valueOf(80L));
                    first = false;
                }
                objId.append('.');
                objId.append(bigValue);
                bigValue = null;
                value = 0L;
            } else
            {
                bigValue = bigValue.shiftLeft(7);
            }
        }

        identifier = objId.toString();
        body = Arrays.clone(bytes);
    }

    public DERObjectIdentifier(String identifier)
    {
        if(identifier == null)
            throw new IllegalArgumentException("'identifier' cannot be null");
        if(!isValidIdentifier(identifier))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("string ").append(identifier).append(" not an OID").toString());
        } else
        {
            this.identifier = identifier;
            return;
        }
    }

    DERObjectIdentifier(DERObjectIdentifier oid, String branchID)
    {
        if(!isValidBranchID(branchID, 0))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("string ").append(branchID).append(" not a valid OID branch").toString());
        } else
        {
            identifier = (new StringBuilder()).append(oid.getId()).append(".").append(branchID).toString();
            return;
        }
    }

    public String getId()
    {
        return identifier;
    }

    private void writeField(ByteArrayOutputStream out, long fieldValue)
    {
        byte result[] = new byte[9];
        int pos = 8;
        for(result[pos] = (byte)((int)fieldValue & 0x7f); fieldValue >= 128L; result[--pos] = (byte)((int)fieldValue & 0x7f | 0x80))
            fieldValue >>= 7;

        out.write(result, pos, 9 - pos);
    }

    private void writeField(ByteArrayOutputStream out, BigInteger fieldValue)
    {
        int byteCount = (fieldValue.bitLength() + 6) / 7;
        if(byteCount == 0)
        {
            out.write(0);
        } else
        {
            BigInteger tmpValue = fieldValue;
            byte tmp[] = new byte[byteCount];
            for(int i = byteCount - 1; i >= 0; i--)
            {
                tmp[i] = (byte)(tmpValue.intValue() & 0x7f | 0x80);
                tmpValue = tmpValue.shiftRight(7);
            }

            tmp[byteCount - 1] &= 0x7f;
            out.write(tmp, 0, tmp.length);
        }
    }

    private void doOutput(ByteArrayOutputStream aOut)
    {
        OIDTokenizer tok = new OIDTokenizer(identifier);
        int first = Integer.parseInt(tok.nextToken()) * 40;
        String secondToken = tok.nextToken();
        if(secondToken.length() <= 18)
            writeField(aOut, (long)first + Long.parseLong(secondToken));
        else
            writeField(aOut, (new BigInteger(secondToken)).add(BigInteger.valueOf(first)));
        while(tok.hasMoreTokens()) 
        {
            String token = tok.nextToken();
            if(token.length() <= 18)
                writeField(aOut, Long.parseLong(token));
            else
                writeField(aOut, new BigInteger(token));
        }
    }

    protected synchronized byte[] getBody()
    {
        if(body == null)
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            doOutput(bOut);
            body = bOut.toByteArray();
        }
        return body;
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
        throws IOException
    {
        int length = getBody().length;
        return 1 + StreamUtil.calculateBodyLength(length) + length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        byte enc[] = getBody();
        out.write(6);
        out.writeLength(enc.length);
        out.write(enc);
    }

    public int hashCode()
    {
        return identifier.hashCode();
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERObjectIdentifier))
            return false;
        else
            return identifier.equals(((DERObjectIdentifier)o).identifier);
    }

    public String toString()
    {
        return getId();
    }

    private static boolean isValidBranchID(String branchID, int start)
    {
        boolean periodAllowed = false;
        for(int pos = branchID.length(); --pos >= start;)
        {
            char ch = branchID.charAt(pos);
            if('0' <= ch && ch <= '9')
                periodAllowed = true;
            else
            if(ch == '.')
            {
                if(!periodAllowed)
                    return false;
                periodAllowed = false;
            } else
            {
                return false;
            }
        }

        return periodAllowed;
    }

    private static boolean isValidIdentifier(String identifier)
    {
        if(identifier.length() < 3 || identifier.charAt(1) != '.')
            return false;
        char first = identifier.charAt(0);
        if(first < '0' || first > '2')
            return false;
        else
            return isValidBranchID(identifier, 2);
    }

    static ASN1ObjectIdentifier fromOctetString(byte enc[])
    {
        int idx1;
        if(enc.length < 3)
            return new ASN1ObjectIdentifier(enc);
        idx1 = enc[enc.length - 2] & 0xff;
        int idx2 = enc[enc.length - 1] & 0x7f;
        ASN1ObjectIdentifier aasn1objectidentifier[][] = cache;
        JVM INSTR monitorenter ;
        ASN1ObjectIdentifier possibleMatch;
        ASN1ObjectIdentifier first[];
        first = cache[idx1];
        if(first == null)
            first = cache[idx1] = new ASN1ObjectIdentifier[128];
        possibleMatch = first[idx2];
        if(possibleMatch == null)
            return first[idx2] = new ASN1ObjectIdentifier(enc);
        if(!Arrays.areEqual(enc, possibleMatch.getBody())) goto _L2; else goto _L1
_L1:
        possibleMatch;
        aasn1objectidentifier;
        JVM INSTR monitorexit ;
        return;
_L2:
        idx1 = idx1 + 1 & 0xff;
        first = cache[idx1];
        if(first == null)
            first = cache[idx1] = new ASN1ObjectIdentifier[128];
        possibleMatch = first[idx2];
        if(possibleMatch != null) goto _L4; else goto _L3
_L3:
        first[idx2] = new ASN1ObjectIdentifier(enc);
        aasn1objectidentifier;
        JVM INSTR monitorexit ;
        return;
_L4:
        if(!Arrays.areEqual(enc, possibleMatch.getBody())) goto _L6; else goto _L5
_L5:
        possibleMatch;
        aasn1objectidentifier;
        JVM INSTR monitorexit ;
        return;
_L6:
        idx2 = idx2 + 1 & 0x7f;
        possibleMatch = first[idx2];
        if(possibleMatch != null) goto _L8; else goto _L7
_L7:
        first[idx2] = new ASN1ObjectIdentifier(enc);
        aasn1objectidentifier;
        JVM INSTR monitorexit ;
        return;
_L8:
        aasn1objectidentifier;
        JVM INSTR monitorexit ;
          goto _L9
        Exception exception;
        exception;
        throw exception;
_L9:
        if(Arrays.areEqual(enc, possibleMatch.getBody()))
            return possibleMatch;
        else
            return new ASN1ObjectIdentifier(enc);
    }

    String identifier;
    private byte body[];
    private static final long LONG_LIMIT = 0xffffffffffff80L;
    private static ASN1ObjectIdentifier cache[][] = new ASN1ObjectIdentifier[256][];

}
