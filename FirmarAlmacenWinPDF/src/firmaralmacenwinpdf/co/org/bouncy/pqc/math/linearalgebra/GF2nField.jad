// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2nField.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.util.Vector;

// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            GF2Polynomial, GF2nPolynomialField, GF2nONBField, GF2nElement, 
//            GF2nONBElement, GF2nPolynomialElement

public abstract class GF2nField
{

    public GF2nField()
    {
    }

    public final int getDegree()
    {
        return mDegree;
    }

    public final GF2Polynomial getFieldPolynomial()
    {
        if(fieldPolynomial == null)
            computeFieldPolynomial();
        return new GF2Polynomial(fieldPolynomial);
    }

    public final boolean equals(Object other)
    {
        if(other == null || !(other instanceof GF2nField))
            return false;
        GF2nField otherField = (GF2nField)other;
        if(otherField.mDegree != mDegree)
            return false;
        if(!fieldPolynomial.equals(otherField.fieldPolynomial))
            return false;
        if((this instanceof GF2nPolynomialField) && !(otherField instanceof GF2nPolynomialField))
            return false;
        return !(this instanceof GF2nONBField) || (otherField instanceof GF2nONBField);
    }

    public int hashCode()
    {
        return mDegree + fieldPolynomial.hashCode();
    }

    protected abstract GF2nElement getRandomRoot(GF2Polynomial gf2polynomial);

    protected abstract void computeCOBMatrix(GF2nField gf2nfield);

    protected abstract void computeFieldPolynomial();

    protected final GF2Polynomial[] invertMatrix(GF2Polynomial matrix[])
    {
        GF2Polynomial a[] = new GF2Polynomial[matrix.length];
        GF2Polynomial inv[] = new GF2Polynomial[matrix.length];
        for(int i = 0; i < mDegree; i++)
            try
            {
                a[i] = new GF2Polynomial(matrix[i]);
                inv[i] = new GF2Polynomial(mDegree);
                inv[i].setBit(mDegree - 1 - i);
            }
            catch(RuntimeException BDNEExc)
            {
                BDNEExc.printStackTrace();
            }

        for(int i = 0; i < mDegree - 1; i++)
        {
            int j;
            for(j = i; j < mDegree && !a[j].testBit(mDegree - 1 - i); j++);
            if(j >= mDegree)
                throw new RuntimeException("GF2nField.invertMatrix: Matrix cannot be inverted!");
            if(i != j)
            {
                GF2Polynomial dummy = a[i];
                a[i] = a[j];
                a[j] = dummy;
                dummy = inv[i];
                inv[i] = inv[j];
                inv[j] = dummy;
            }
            for(j = i + 1; j < mDegree; j++)
                if(a[j].testBit(mDegree - 1 - i))
                {
                    a[j].addToThis(a[i]);
                    inv[j].addToThis(inv[i]);
                }

        }

        for(int i = mDegree - 1; i > 0; i--)
        {
            for(int j = i - 1; j >= 0; j--)
                if(a[j].testBit(mDegree - 1 - i))
                {
                    a[j].addToThis(a[i]);
                    inv[j].addToThis(inv[i]);
                }

        }

        return inv;
    }

    public final GF2nElement convert(GF2nElement elem, GF2nField basis)
        throws RuntimeException
    {
        if(basis == this)
            return (GF2nElement)elem.clone();
        if(fieldPolynomial.equals(basis.fieldPolynomial))
            return (GF2nElement)elem.clone();
        if(mDegree != basis.mDegree)
            throw new RuntimeException("GF2nField.convert: B1 has a different degree and thus cannot be coverted to!");
        int i = fields.indexOf(basis);
        if(i == -1)
        {
            computeCOBMatrix(basis);
            i = fields.indexOf(basis);
        }
        GF2Polynomial COBMatrix[] = (GF2Polynomial[])(GF2Polynomial[])matrices.elementAt(i);
        GF2nElement elemCopy = (GF2nElement)elem.clone();
        if(elemCopy instanceof GF2nONBElement)
            ((GF2nONBElement)elemCopy).reverseOrder();
        GF2Polynomial bs = new GF2Polynomial(mDegree, elemCopy.toFlexiBigInt());
        bs.expandN(mDegree);
        GF2Polynomial result = new GF2Polynomial(mDegree);
        for(i = 0; i < mDegree; i++)
            if(bs.vectorMult(COBMatrix[i]))
                result.setBit(mDegree - 1 - i);

        if(basis instanceof GF2nPolynomialField)
            return new GF2nPolynomialElement((GF2nPolynomialField)basis, result);
        if(basis instanceof GF2nONBField)
        {
            GF2nONBElement res = new GF2nONBElement((GF2nONBField)basis, result.toFlexiBigInt());
            res.reverseOrder();
            return res;
        } else
        {
            throw new RuntimeException("GF2nField.convert: B1 must be an instance of GF2nPolynomialField or GF2nONBField!");
        }
    }

    protected int mDegree;
    protected GF2Polynomial fieldPolynomial;
    protected Vector fields;
    protected Vector matrices;
}
