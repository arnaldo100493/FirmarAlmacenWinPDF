// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GF2Matrix.java

package co.org.bouncy.pqc.math.linearalgebra;

import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.math.linearalgebra:
//            Matrix, Permutation, GF2Vector, Vector, 
//            LittleEndianConversions, IntUtils

public class GF2Matrix extends Matrix
{

    public GF2Matrix(byte enc[])
    {
        if(enc.length < 9)
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        numRows = LittleEndianConversions.OS2IP(enc, 0);
        numColumns = LittleEndianConversions.OS2IP(enc, 4);
        int n = (numColumns + 7 >>> 3) * numRows;
        if(numRows <= 0 || n != enc.length - 8)
            throw new ArithmeticException("given array is not an encoded matrix over GF(2)");
        length = numColumns + 31 >>> 5;
        matrix = new int[numRows][length];
        int q = numColumns >> 5;
        int r = numColumns & 0x1f;
        int count = 8;
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < q;)
            {
                matrix[i][j] = LittleEndianConversions.OS2IP(enc, count);
                j++;
                count += 4;
            }

            for(int j = 0; j < r; j += 8)
                matrix[i][q] ^= (enc[count++] & 0xff) << j;

        }

    }

    public GF2Matrix(int numColumns, int matrix[][])
    {
        if(matrix[0].length != numColumns + 31 >> 5)
            throw new ArithmeticException("Int array does not match given number of columns.");
        this.numColumns = numColumns;
        numRows = matrix.length;
        length = matrix[0].length;
        int rest = numColumns & 0x1f;
        int bitMask;
        if(rest == 0)
            bitMask = -1;
        else
            bitMask = (1 << rest) - 1;
        for(int i = 0; i < numRows; i++)
            matrix[i][length - 1] &= bitMask;

        this.matrix = matrix;
    }

    public GF2Matrix(int n, char typeOfMatrix)
    {
        this(n, typeOfMatrix, new SecureRandom());
    }

    public GF2Matrix(int n, char typeOfMatrix, SecureRandom sr)
    {
        if(n <= 0)
            throw new ArithmeticException("Size of matrix is non-positive.");
        switch(typeOfMatrix)
        {
        case 90: // 'Z'
            assignZeroMatrix(n, n);
            break;

        case 73: // 'I'
            assignUnitMatrix(n);
            break;

        case 76: // 'L'
            assignRandomLowerTriangularMatrix(n, sr);
            break;

        case 85: // 'U'
            assignRandomUpperTriangularMatrix(n, sr);
            break;

        case 82: // 'R'
            assignRandomRegularMatrix(n, sr);
            break;

        default:
            throw new ArithmeticException("Unknown matrix type.");
        }
    }

    public GF2Matrix(GF2Matrix a)
    {
        numColumns = a.getNumColumns();
        numRows = a.getNumRows();
        length = a.length;
        matrix = new int[a.matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            matrix[i] = IntUtils.clone(a.matrix[i]);

    }

    private GF2Matrix(int m, int n)
    {
        if(n <= 0 || m <= 0)
        {
            throw new ArithmeticException("size of matrix is non-positive");
        } else
        {
            assignZeroMatrix(m, n);
            return;
        }
    }

    private void assignZeroMatrix(int m, int n)
    {
        numRows = m;
        numColumns = n;
        length = n + 31 >>> 5;
        matrix = new int[numRows][length];
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < length; j++)
                matrix[i][j] = 0;

        }

    }

    private void assignUnitMatrix(int n)
    {
        numRows = n;
        numColumns = n;
        length = n + 31 >>> 5;
        matrix = new int[numRows][length];
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < length; j++)
                matrix[i][j] = 0;

        }

        for(int i = 0; i < numRows; i++)
        {
            int rest = i & 0x1f;
            matrix[i][i >>> 5] = 1 << rest;
        }

    }

    private void assignRandomLowerTriangularMatrix(int n, SecureRandom sr)
    {
        numRows = n;
        numColumns = n;
        length = n + 31 >>> 5;
        matrix = new int[numRows][length];
        for(int i = 0; i < numRows; i++)
        {
            int q = i >>> 5;
            int r = i & 0x1f;
            int s = 31 - r;
            r = 1 << r;
            for(int j = 0; j < q; j++)
                matrix[i][j] = sr.nextInt();

            matrix[i][q] = sr.nextInt() >>> s | r;
            for(int j = q + 1; j < length; j++)
                matrix[i][j] = 0;

        }

    }

    private void assignRandomUpperTriangularMatrix(int n, SecureRandom sr)
    {
        numRows = n;
        numColumns = n;
        length = n + 31 >>> 5;
        matrix = new int[numRows][length];
        int rest = n & 0x1f;
        int help;
        if(rest == 0)
            help = -1;
        else
            help = (1 << rest) - 1;
        for(int i = 0; i < numRows; i++)
        {
            int q = i >>> 5;
            int r = i & 0x1f;
            int s = r;
            r = 1 << r;
            for(int j = 0; j < q; j++)
                matrix[i][j] = 0;

            matrix[i][q] = sr.nextInt() << s | r;
            for(int j = q + 1; j < length; j++)
                matrix[i][j] = sr.nextInt();

            matrix[i][length - 1] &= help;
        }

    }

    private void assignRandomRegularMatrix(int n, SecureRandom sr)
    {
        numRows = n;
        numColumns = n;
        length = n + 31 >>> 5;
        matrix = new int[numRows][length];
        GF2Matrix lm = new GF2Matrix(n, 'L', sr);
        GF2Matrix um = new GF2Matrix(n, 'U', sr);
        GF2Matrix rm = (GF2Matrix)lm.rightMultiply(um);
        Permutation perm = new Permutation(n, sr);
        int p[] = perm.getVector();
        for(int i = 0; i < n; i++)
            System.arraycopy(rm.matrix[i], 0, matrix[p[i]], 0, length);

    }

    public static GF2Matrix[] createRandomRegularMatrixAndItsInverse(int n, SecureRandom sr)
    {
        GF2Matrix result[] = new GF2Matrix[2];
        int length = n + 31 >> 5;
        GF2Matrix lm = new GF2Matrix(n, 'L', sr);
        GF2Matrix um = new GF2Matrix(n, 'U', sr);
        GF2Matrix rm = (GF2Matrix)lm.rightMultiply(um);
        Permutation p = new Permutation(n, sr);
        int pVec[] = p.getVector();
        int matrix[][] = new int[n][length];
        for(int i = 0; i < n; i++)
            System.arraycopy(rm.matrix[pVec[i]], 0, matrix[i], 0, length);

        result[0] = new GF2Matrix(n, matrix);
        GF2Matrix invLm = new GF2Matrix(n, 'I');
        for(int i = 0; i < n; i++)
        {
            int rest = i & 0x1f;
            int q = i >>> 5;
            int r = 1 << rest;
            for(int j = i + 1; j < n; j++)
            {
                int b = lm.matrix[j][q] & r;
                if(b == 0)
                    continue;
                for(int k = 0; k <= q; k++)
                    invLm.matrix[j][k] ^= invLm.matrix[i][k];

            }

        }

        GF2Matrix invUm = new GF2Matrix(n, 'I');
        for(int i = n - 1; i >= 0; i--)
        {
            int rest = i & 0x1f;
            int q = i >>> 5;
            int r = 1 << rest;
            for(int j = i - 1; j >= 0; j--)
            {
                int b = um.matrix[j][q] & r;
                if(b == 0)
                    continue;
                for(int k = q; k < length; k++)
                    invUm.matrix[j][k] ^= invUm.matrix[i][k];

            }

        }

        result[1] = (GF2Matrix)invUm.rightMultiply(invLm.rightMultiply(p));
        return result;
    }

    public int[][] getIntArray()
    {
        return matrix;
    }

    public int getLength()
    {
        return length;
    }

    public int[] getRow(int index)
    {
        return matrix[index];
    }

    public byte[] getEncoded()
    {
        int n = numColumns + 7 >>> 3;
        n *= numRows;
        byte enc[] = new byte[n += 8];
        LittleEndianConversions.I2OSP(numRows, enc, 0);
        LittleEndianConversions.I2OSP(numColumns, enc, 4);
        int q = numColumns >>> 5;
        int r = numColumns & 0x1f;
        int count = 8;
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < q;)
            {
                LittleEndianConversions.I2OSP(matrix[i][j], enc, count);
                j++;
                count += 4;
            }

            for(int j = 0; j < r; j += 8)
                enc[count++] = (byte)(matrix[i][q] >>> j & 0xff);

        }

        return enc;
    }

    public double getHammingWeight()
    {
        double counter = 0.0D;
        double elementCounter = 0.0D;
        int rest = numColumns & 0x1f;
        int d;
        if(rest == 0)
            d = length;
        else
            d = length - 1;
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < d; j++)
            {
                int a = matrix[i][j];
                for(int k = 0; k < 32; k++)
                {
                    int b = a >>> k & 1;
                    counter += b;
                    elementCounter++;
                }

            }

            int a = matrix[i][length - 1];
            for(int k = 0; k < rest; k++)
            {
                int b = a >>> k & 1;
                counter += b;
                elementCounter++;
            }

        }

        return counter / elementCounter;
    }

    public boolean isZero()
    {
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < length; j++)
                if(matrix[i][j] != 0)
                    return false;

        }

        return true;
    }

    public GF2Matrix getLeftSubMatrix()
    {
        if(numColumns <= numRows)
            throw new ArithmeticException("empty submatrix");
        int length = numRows + 31 >> 5;
        int result[][] = new int[numRows][length];
        int bitMask = (1 << (numRows & 0x1f)) - 1;
        if(bitMask == 0)
            bitMask = -1;
        for(int i = numRows - 1; i >= 0; i--)
        {
            System.arraycopy(matrix[i], 0, result[i], 0, length);
            result[i][length - 1] &= bitMask;
        }

        return new GF2Matrix(numRows, result);
    }

    public GF2Matrix extendLeftCompactForm()
    {
        int newNumColumns = numColumns + numRows;
        GF2Matrix result = new GF2Matrix(numRows, newNumColumns);
        int ind = (numRows - 1) + numColumns;
        for(int i = numRows - 1; i >= 0;)
        {
            System.arraycopy(matrix[i], 0, result.matrix[i], 0, length);
            result.matrix[i][ind >> 5] |= 1 << (ind & 0x1f);
            i--;
            ind--;
        }

        return result;
    }

    public GF2Matrix getRightSubMatrix()
    {
        if(numColumns <= numRows)
            throw new ArithmeticException("empty submatrix");
        int q = numRows >> 5;
        int r = numRows & 0x1f;
        GF2Matrix result = new GF2Matrix(numRows, numColumns - numRows);
        for(int i = numRows - 1; i >= 0; i--)
            if(r != 0)
            {
                int ind = q;
                for(int j = 0; j < result.length - 1; j++)
                    result.matrix[i][j] = matrix[i][ind++] >>> r | matrix[i][ind] << 32 - r;

                result.matrix[i][result.length - 1] = matrix[i][ind++] >>> r;
                if(ind < length)
                    result.matrix[i][result.length - 1] |= matrix[i][ind] << 32 - r;
            } else
            {
                System.arraycopy(matrix[i], q, result.matrix[i], 0, result.length);
            }

        return result;
    }

    public GF2Matrix extendRightCompactForm()
    {
        GF2Matrix result = new GF2Matrix(numRows, numRows + numColumns);
        int q = numRows >> 5;
        int r = numRows & 0x1f;
        for(int i = numRows - 1; i >= 0; i--)
        {
            result.matrix[i][i >> 5] |= 1 << (i & 0x1f);
            if(r != 0)
            {
                int ind = q;
                for(int j = 0; j < length - 1; j++)
                {
                    int mw = matrix[i][j];
                    result.matrix[i][ind++] |= mw << r;
                    result.matrix[i][ind] |= mw >>> 32 - r;
                }

                int mw = matrix[i][length - 1];
                result.matrix[i][ind++] |= mw << r;
                if(ind < result.length)
                    result.matrix[i][ind] |= mw >>> 32 - r;
            } else
            {
                System.arraycopy(matrix[i], 0, result.matrix[i], q, length);
            }
        }

        return result;
    }

    public Matrix computeTranspose()
    {
        int result[][] = new int[numColumns][numRows + 31 >>> 5];
        for(int i = 0; i < numRows; i++)
        {
            for(int j = 0; j < numColumns; j++)
            {
                int qs = j >>> 5;
                int rs = j & 0x1f;
                int b = matrix[i][qs] >>> rs & 1;
                int qt = i >>> 5;
                int rt = i & 0x1f;
                if(b == 1)
                    result[j][qt] |= 1 << rt;
            }

        }

        return new GF2Matrix(numRows, result);
    }

    public Matrix computeInverse()
    {
        if(numRows != numColumns)
            throw new ArithmeticException("Matrix is not invertible.");
        int tmpMatrix[][] = new int[numRows][length];
        for(int i = numRows - 1; i >= 0; i--)
            tmpMatrix[i] = IntUtils.clone(matrix[i]);

        int invMatrix[][] = new int[numRows][length];
        for(int i = numRows - 1; i >= 0; i--)
        {
            int q = i >> 5;
            int r = i & 0x1f;
            invMatrix[i][q] = 1 << r;
        }

        for(int i = 0; i < numRows; i++)
        {
            int q = i >> 5;
            int bitMask = 1 << (i & 0x1f);
            if((tmpMatrix[i][q] & bitMask) == 0)
            {
                boolean foundNonZero = false;
                for(int j = i + 1; j < numRows; j++)
                    if((tmpMatrix[j][q] & bitMask) != 0)
                    {
                        foundNonZero = true;
                        swapRows(tmpMatrix, i, j);
                        swapRows(invMatrix, i, j);
                        j = numRows;
                    }

                if(!foundNonZero)
                    throw new ArithmeticException("Matrix is not invertible.");
            }
            for(int j = numRows - 1; j >= 0; j--)
                if(j != i && (tmpMatrix[j][q] & bitMask) != 0)
                {
                    addToRow(tmpMatrix[i], tmpMatrix[j], q);
                    addToRow(invMatrix[i], invMatrix[j], 0);
                }

        }

        return new GF2Matrix(numColumns, invMatrix);
    }

    public Matrix leftMultiply(Permutation p)
    {
        int pVec[] = p.getVector();
        if(pVec.length != numRows)
            throw new ArithmeticException("length mismatch");
        int result[][] = new int[numRows][];
        for(int i = numRows - 1; i >= 0; i--)
            result[i] = IntUtils.clone(matrix[pVec[i]]);

        return new GF2Matrix(numRows, result);
    }

    public Vector leftMultiply(Vector vec)
    {
        if(!(vec instanceof GF2Vector))
            throw new ArithmeticException("vector is not defined over GF(2)");
        if(vec.length != numRows)
            throw new ArithmeticException("length mismatch");
        int v[] = ((GF2Vector)vec).getVecArray();
        int res[] = new int[length];
        int q = numRows >> 5;
        int r = 1 << (numRows & 0x1f);
        int row = 0;
        for(int i = 0; i < q; i++)
        {
            int bitMask = 1;
            do
            {
                int b = v[i] & bitMask;
                if(b != 0)
                {
                    for(int j = 0; j < length; j++)
                        res[j] ^= matrix[row][j];

                }
                row++;
                bitMask <<= 1;
            } while(bitMask != 0);
        }

        for(int bitMask = 1; bitMask != r; bitMask <<= 1)
        {
            int b = v[q] & bitMask;
            if(b != 0)
            {
                for(int j = 0; j < length; j++)
                    res[j] ^= matrix[row][j];

            }
            row++;
        }

        return new GF2Vector(res, numColumns);
    }

    public Vector leftMultiplyLeftCompactForm(Vector vec)
    {
        if(!(vec instanceof GF2Vector))
            throw new ArithmeticException("vector is not defined over GF(2)");
        if(vec.length != numRows)
            throw new ArithmeticException("length mismatch");
        int v[] = ((GF2Vector)vec).getVecArray();
        int res[] = new int[numRows + numColumns + 31 >>> 5];
        int words = numRows >>> 5;
        int row = 0;
        for(int i = 0; i < words; i++)
        {
            int bitMask = 1;
            do
            {
                int b = v[i] & bitMask;
                if(b != 0)
                {
                    for(int j = 0; j < length; j++)
                        res[j] ^= matrix[row][j];

                    int q = numColumns + row >>> 5;
                    int r = numColumns + row & 0x1f;
                    res[q] |= 1 << r;
                }
                row++;
                bitMask <<= 1;
            } while(bitMask != 0);
        }

        int rem = 1 << (numRows & 0x1f);
        for(int bitMask = 1; bitMask != rem; bitMask <<= 1)
        {
            int b = v[words] & bitMask;
            if(b != 0)
            {
                for(int j = 0; j < length; j++)
                    res[j] ^= matrix[row][j];

                int q = numColumns + row >>> 5;
                int r = numColumns + row & 0x1f;
                res[q] |= 1 << r;
            }
            row++;
        }

        return new GF2Vector(res, numRows + numColumns);
    }

    public Matrix rightMultiply(Matrix mat)
    {
        if(!(mat instanceof GF2Matrix))
            throw new ArithmeticException("matrix is not defined over GF(2)");
        if(mat.numRows != numColumns)
            throw new ArithmeticException("length mismatch");
        GF2Matrix a = (GF2Matrix)mat;
        GF2Matrix result = new GF2Matrix(numRows, mat.numColumns);
        int rest = numColumns & 0x1f;
        int d;
        if(rest == 0)
            d = length;
        else
            d = length - 1;
        for(int i = 0; i < numRows; i++)
        {
            int count = 0;
            for(int j = 0; j < d; j++)
            {
                int e = matrix[i][j];
                for(int h = 0; h < 32; h++)
                {
                    int b = e & 1 << h;
                    if(b != 0)
                    {
                        for(int g = 0; g < a.length; g++)
                            result.matrix[i][g] ^= a.matrix[count][g];

                    }
                    count++;
                }

            }

            int e = matrix[i][length - 1];
            for(int h = 0; h < rest; h++)
            {
                int b = e & 1 << h;
                if(b != 0)
                {
                    for(int g = 0; g < a.length; g++)
                        result.matrix[i][g] ^= a.matrix[count][g];

                }
                count++;
            }

        }

        return result;
    }

    public Matrix rightMultiply(Permutation p)
    {
        int pVec[] = p.getVector();
        if(pVec.length != numColumns)
            throw new ArithmeticException("length mismatch");
        GF2Matrix result = new GF2Matrix(numRows, numColumns);
        for(int i = numColumns - 1; i >= 0; i--)
        {
            int q = i >>> 5;
            int r = i & 0x1f;
            int pq = pVec[i] >>> 5;
            int pr = pVec[i] & 0x1f;
            for(int j = numRows - 1; j >= 0; j--)
                result.matrix[j][q] |= (matrix[j][pq] >>> pr & 1) << r;

        }

        return result;
    }

    public Vector rightMultiply(Vector vec)
    {
        if(!(vec instanceof GF2Vector))
            throw new ArithmeticException("vector is not defined over GF(2)");
        if(vec.length != numColumns)
            throw new ArithmeticException("length mismatch");
        int v[] = ((GF2Vector)vec).getVecArray();
        int res[] = new int[numRows + 31 >>> 5];
        for(int i = 0; i < numRows; i++)
        {
            int help = 0;
            for(int j = 0; j < length; j++)
                help ^= matrix[i][j] & v[j];

            int bitValue = 0;
            for(int j = 0; j < 32; j++)
                bitValue ^= help >>> j & 1;

            if(bitValue == 1)
                res[i >>> 5] |= 1 << (i & 0x1f);
        }

        return new GF2Vector(res, numRows);
    }

    public Vector rightMultiplyRightCompactForm(Vector vec)
    {
        if(!(vec instanceof GF2Vector))
            throw new ArithmeticException("vector is not defined over GF(2)");
        if(vec.length != numColumns + numRows)
            throw new ArithmeticException("length mismatch");
        int v[] = ((GF2Vector)vec).getVecArray();
        int res[] = new int[numRows + 31 >>> 5];
        int q = numRows >> 5;
        int r = numRows & 0x1f;
        for(int i = 0; i < numRows; i++)
        {
            int help = v[i >> 5] >>> (i & 0x1f) & 1;
            int vInd = q;
            if(r != 0)
            {
                int vw = 0;
                for(int j = 0; j < length - 1; j++)
                {
                    vw = v[vInd++] >>> r | v[vInd] << 32 - r;
                    help ^= matrix[i][j] & vw;
                }

                vw = v[vInd++] >>> r;
                if(vInd < v.length)
                    vw |= v[vInd] << 32 - r;
                help ^= matrix[i][length - 1] & vw;
            } else
            {
                for(int j = 0; j < length; j++)
                    help ^= matrix[i][j] & v[vInd++];

            }
            int bitValue = 0;
            for(int j = 0; j < 32; j++)
            {
                bitValue ^= help & 1;
                help >>>= 1;
            }

            if(bitValue == 1)
                res[i >> 5] |= 1 << (i & 0x1f);
        }

        return new GF2Vector(res, numRows);
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof GF2Matrix))
            return false;
        GF2Matrix otherMatrix = (GF2Matrix)other;
        if(numRows != otherMatrix.numRows || numColumns != otherMatrix.numColumns || length != otherMatrix.length)
            return false;
        for(int i = 0; i < numRows; i++)
            if(!IntUtils.equals(matrix[i], otherMatrix.matrix[i]))
                return false;

        return true;
    }

    public int hashCode()
    {
        int hash = (numRows * 31 + numColumns) * 31 + length;
        for(int i = 0; i < numRows; i++)
            hash = hash * 31 + matrix[i].hashCode();

        return hash;
    }

    public String toString()
    {
        int rest = numColumns & 0x1f;
        int d;
        if(rest == 0)
            d = length;
        else
            d = length - 1;
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < numRows; i++)
        {
            buf.append((new StringBuilder()).append(i).append(": ").toString());
            for(int j = 0; j < d; j++)
            {
                int a = matrix[i][j];
                for(int k = 0; k < 32; k++)
                {
                    int b = a >>> k & 1;
                    if(b == 0)
                        buf.append('0');
                    else
                        buf.append('1');
                }

                buf.append(' ');
            }

            int a = matrix[i][length - 1];
            for(int k = 0; k < rest; k++)
            {
                int b = a >>> k & 1;
                if(b == 0)
                    buf.append('0');
                else
                    buf.append('1');
            }

            buf.append('\n');
        }

        return buf.toString();
    }

    private static void swapRows(int matrix[][], int first, int second)
    {
        int tmp[] = matrix[first];
        matrix[first] = matrix[second];
        matrix[second] = tmp;
    }

    private static void addToRow(int fromRow[], int toRow[], int startIndex)
    {
        for(int i = toRow.length - 1; i >= startIndex; i--)
            toRow[i] = fromRow[i] ^ toRow[i];

    }

    private int matrix[][];
    private int length;
}
