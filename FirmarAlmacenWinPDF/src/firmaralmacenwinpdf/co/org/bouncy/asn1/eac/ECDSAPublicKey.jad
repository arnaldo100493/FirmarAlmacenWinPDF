// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECDSAPublicKey.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.math.BigInteger;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            PublicKeyDataObject, UnsignedInteger

public class ECDSAPublicKey extends PublicKeyDataObject
{

    ECDSAPublicKey(ASN1Sequence seq)
        throws IllegalArgumentException
    {
        Enumeration en = seq.getObjects();
        usage = ASN1ObjectIdentifier.getInstance(en.nextElement());
        options = 0;
        do
        {
            if(!en.hasMoreElements())
                break MISSING_BLOCK_LABEL_236;
            Object obj = en.nextElement();
            if(!(obj instanceof ASN1TaggedObject))
                break MISSING_BLOCK_LABEL_223;
            ASN1TaggedObject to = (ASN1TaggedObject)obj;
            switch(to.getTagNo())
            {
            case 1: // '\001'
                setPrimeModulusP(UnsignedInteger.getInstance(to).getValue());
                break;

            case 2: // '\002'
                setFirstCoefA(UnsignedInteger.getInstance(to).getValue());
                break;

            case 3: // '\003'
                setSecondCoefB(UnsignedInteger.getInstance(to).getValue());
                break;

            case 4: // '\004'
                setBasePointG(ASN1OctetString.getInstance(to, false));
                break;

            case 5: // '\005'
                setOrderOfBasePointR(UnsignedInteger.getInstance(to).getValue());
                break;

            case 6: // '\006'
                setPublicPointY(ASN1OctetString.getInstance(to, false));
                break;

            case 7: // '\007'
                setCofactorF(UnsignedInteger.getInstance(to).getValue());
                break;

            default:
label0:
                {
                    options = 0;
                    break label0;
                }
                break;
            }
        } while(true);
        break MISSING_BLOCK_LABEL_223;
        throw new IllegalArgumentException("Unknown Object Identifier!");
        throw new IllegalArgumentException("Unknown Object Identifier!");
        if(options != 32 && options != 127)
            throw new IllegalArgumentException("All options must be either present or absent!");
        else
            return;
    }

    public ECDSAPublicKey(ASN1ObjectIdentifier usage, byte ppY[])
        throws IllegalArgumentException
    {
        this.usage = usage;
        setPublicPointY(new DEROctetString(ppY));
    }

    public ECDSAPublicKey(ASN1ObjectIdentifier usage, BigInteger p, BigInteger a, BigInteger b, byte basePoint[], BigInteger order, byte publicPoint[], 
            int cofactor)
    {
        this.usage = usage;
        setPrimeModulusP(p);
        setFirstCoefA(a);
        setSecondCoefB(b);
        setBasePointG(new DEROctetString(basePoint));
        setOrderOfBasePointR(order);
        setPublicPointY(new DEROctetString(publicPoint));
        setCofactorF(BigInteger.valueOf(cofactor));
    }

    public ASN1ObjectIdentifier getUsage()
    {
        return usage;
    }

    public byte[] getBasePointG()
    {
        if((options & 8) != 0)
            return basePointG;
        else
            return null;
    }

    private void setBasePointG(ASN1OctetString basePointG)
        throws IllegalArgumentException
    {
        if((options & 8) == 0)
        {
            options |= 8;
            this.basePointG = basePointG.getOctets();
        } else
        {
            throw new IllegalArgumentException("Base Point G already set");
        }
    }

    public BigInteger getCofactorF()
    {
        if((options & 0x40) != 0)
            return cofactorF;
        else
            return null;
    }

    private void setCofactorF(BigInteger cofactorF)
        throws IllegalArgumentException
    {
        if((options & 0x40) == 0)
        {
            options |= 0x40;
            this.cofactorF = cofactorF;
        } else
        {
            throw new IllegalArgumentException("Cofactor F already set");
        }
    }

    public BigInteger getFirstCoefA()
    {
        if((options & 2) != 0)
            return firstCoefA;
        else
            return null;
    }

    private void setFirstCoefA(BigInteger firstCoefA)
        throws IllegalArgumentException
    {
        if((options & 2) == 0)
        {
            options |= 2;
            this.firstCoefA = firstCoefA;
        } else
        {
            throw new IllegalArgumentException("First Coef A already set");
        }
    }

    public BigInteger getOrderOfBasePointR()
    {
        if((options & 0x10) != 0)
            return orderOfBasePointR;
        else
            return null;
    }

    private void setOrderOfBasePointR(BigInteger orderOfBasePointR)
        throws IllegalArgumentException
    {
        if((options & 0x10) == 0)
        {
            options |= 0x10;
            this.orderOfBasePointR = orderOfBasePointR;
        } else
        {
            throw new IllegalArgumentException("Order of base point R already set");
        }
    }

    public BigInteger getPrimeModulusP()
    {
        if((options & 1) != 0)
            return primeModulusP;
        else
            return null;
    }

    private void setPrimeModulusP(BigInteger primeModulusP)
    {
        if((options & 1) == 0)
        {
            options |= 1;
            this.primeModulusP = primeModulusP;
        } else
        {
            throw new IllegalArgumentException("Prime Modulus P already set");
        }
    }

    public byte[] getPublicPointY()
    {
        if((options & 0x20) != 0)
            return publicPointY;
        else
            return null;
    }

    private void setPublicPointY(ASN1OctetString publicPointY)
        throws IllegalArgumentException
    {
        if((options & 0x20) == 0)
        {
            options |= 0x20;
            this.publicPointY = publicPointY.getOctets();
        } else
        {
            throw new IllegalArgumentException("Public Point Y already set");
        }
    }

    public BigInteger getSecondCoefB()
    {
        if((options & 4) != 0)
            return secondCoefB;
        else
            return null;
    }

    private void setSecondCoefB(BigInteger secondCoefB)
        throws IllegalArgumentException
    {
        if((options & 4) == 0)
        {
            options |= 4;
            this.secondCoefB = secondCoefB;
        } else
        {
            throw new IllegalArgumentException("Second Coef B already set");
        }
    }

    public boolean hasParameters()
    {
        return primeModulusP != null;
    }

    public ASN1EncodableVector getASN1EncodableVector(ASN1ObjectIdentifier oid, boolean publicPointOnly)
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(oid);
        if(!publicPointOnly)
        {
            v.add(new UnsignedInteger(1, getPrimeModulusP()));
            v.add(new UnsignedInteger(2, getFirstCoefA()));
            v.add(new UnsignedInteger(3, getSecondCoefB()));
            v.add(new DERTaggedObject(false, 4, new DEROctetString(getBasePointG())));
            v.add(new UnsignedInteger(5, getOrderOfBasePointR()));
        }
        v.add(new DERTaggedObject(false, 6, new DEROctetString(getPublicPointY())));
        if(!publicPointOnly)
            v.add(new UnsignedInteger(7, getCofactorF()));
        return v;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return new DERSequence(getASN1EncodableVector(usage, false));
    }

    private ASN1ObjectIdentifier usage;
    private BigInteger primeModulusP;
    private BigInteger firstCoefA;
    private BigInteger secondCoefB;
    private byte basePointG[];
    private BigInteger orderOfBasePointR;
    private byte publicPointY[];
    private BigInteger cofactorF;
    private int options;
    private static final int P = 1;
    private static final int A = 2;
    private static final int B = 4;
    private static final int G = 8;
    private static final int R = 16;
    private static final int Y = 32;
    private static final int F = 64;
}
