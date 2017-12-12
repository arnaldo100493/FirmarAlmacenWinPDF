// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BidiOrder.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;

public final class BidiOrder
{

    public BidiOrder(byte types[])
    {
        paragraphEmbeddingLevel = -1;
        validateTypes(types);
        initialTypes = (byte[])(byte[])types.clone();
        runAlgorithm();
    }

    public BidiOrder(byte types[], byte paragraphEmbeddingLevel)
    {
        this.paragraphEmbeddingLevel = -1;
        validateTypes(types);
        validateParagraphEmbeddingLevel(paragraphEmbeddingLevel);
        initialTypes = (byte[])(byte[])types.clone();
        this.paragraphEmbeddingLevel = paragraphEmbeddingLevel;
        runAlgorithm();
    }

    public BidiOrder(char text[], int offset, int length, byte paragraphEmbeddingLevel)
    {
        this.paragraphEmbeddingLevel = -1;
        initialTypes = new byte[length];
        for(int k = 0; k < length; k++)
            initialTypes[k] = rtypes[text[offset + k]];

        validateParagraphEmbeddingLevel(paragraphEmbeddingLevel);
        this.paragraphEmbeddingLevel = paragraphEmbeddingLevel;
        runAlgorithm();
    }

    public static final byte getDirection(char c)
    {
        return rtypes[c];
    }

    private void runAlgorithm()
    {
        textLength = initialTypes.length;
        resultTypes = (byte[])(byte[])initialTypes.clone();
        if(paragraphEmbeddingLevel == -1)
            determineParagraphEmbeddingLevel();
        resultLevels = new byte[textLength];
        setLevels(0, textLength, paragraphEmbeddingLevel);
        determineExplicitEmbeddingLevels();
        textLength = removeExplicitCodes();
        byte prevLevel = paragraphEmbeddingLevel;
        int limit;
        for(int start = 0; start < textLength; start = limit)
        {
            byte level = resultLevels[start];
            byte prevType = typeForLevel(Math.max(prevLevel, level));
            for(limit = start + 1; limit < textLength && resultLevels[limit] == level; limit++);
            byte succLevel = limit >= textLength ? paragraphEmbeddingLevel : resultLevels[limit];
            byte succType = typeForLevel(Math.max(succLevel, level));
            resolveWeakTypes(start, limit, level, prevType, succType);
            resolveNeutralTypes(start, limit, level, prevType, succType);
            resolveImplicitLevels(start, limit, level, prevType, succType);
            prevLevel = level;
        }

        textLength = reinsertExplicitCodes(textLength);
    }

    private void determineParagraphEmbeddingLevel()
    {
        byte strongType = -1;
        int i = 0;
        do
        {
            if(i >= textLength)
                break;
            byte t = resultTypes[i];
            if(t == 0 || t == 4 || t == 3)
            {
                strongType = t;
                break;
            }
            i++;
        } while(true);
        if(strongType == -1)
            paragraphEmbeddingLevel = 0;
        else
        if(strongType == 0)
            paragraphEmbeddingLevel = 0;
        else
            paragraphEmbeddingLevel = 1;
    }

    private void determineExplicitEmbeddingLevels()
    {
        embeddings = processEmbeddings(resultTypes, paragraphEmbeddingLevel);
        for(int i = 0; i < textLength; i++)
        {
            byte level = embeddings[i];
            if((level & 0x80) != 0)
            {
                level &= 0x7f;
                resultTypes[i] = typeForLevel(level);
            }
            resultLevels[i] = level;
        }

    }

    private int removeExplicitCodes()
    {
        int w = 0;
        for(int i = 0; i < textLength; i++)
        {
            byte t = initialTypes[i];
            if(t != 1 && t != 5 && t != 2 && t != 6 && t != 7 && t != 14)
            {
                embeddings[w] = embeddings[i];
                resultTypes[w] = resultTypes[i];
                resultLevels[w] = resultLevels[i];
                w++;
            }
        }

        return w;
    }

    private int reinsertExplicitCodes(int textLength)
    {
        for(int i = initialTypes.length; --i >= 0;)
        {
            byte t = initialTypes[i];
            if(t == 1 || t == 5 || t == 2 || t == 6 || t == 7 || t == 14)
            {
                embeddings[i] = 0;
                resultTypes[i] = t;
                resultLevels[i] = -1;
            } else
            {
                textLength--;
                embeddings[i] = embeddings[textLength];
                resultTypes[i] = resultTypes[textLength];
                resultLevels[i] = resultLevels[textLength];
            }
        }

        if(resultLevels[0] == -1)
            resultLevels[0] = paragraphEmbeddingLevel;
        for(int i = 1; i < initialTypes.length; i++)
            if(resultLevels[i] == -1)
                resultLevels[i] = resultLevels[i - 1];

        return initialTypes.length;
    }

    private static byte[] processEmbeddings(byte resultTypes[], byte paragraphEmbeddingLevel)
    {
        int EXPLICIT_LEVEL_LIMIT = 62;
        int textLength = resultTypes.length;
        byte embeddings[] = new byte[textLength];
        byte embeddingValueStack[] = new byte[62];
        int stackCounter = 0;
        int overflowAlmostCounter = 0;
        int overflowCounter = 0;
        byte currentEmbeddingLevel = paragraphEmbeddingLevel;
        byte currentEmbeddingValue = paragraphEmbeddingLevel;
        for(int i = 0; i < textLength; i++)
        {
            embeddings[i] = currentEmbeddingValue;
            byte t = resultTypes[i];
            switch(t)
            {
            case 3: // '\003'
            case 4: // '\004'
            case 8: // '\b'
            case 9: // '\t'
            case 10: // '\n'
            case 11: // '\013'
            case 12: // '\f'
            case 13: // '\r'
            case 14: // '\016'
            default:
                break;

            case 1: // '\001'
            case 2: // '\002'
            case 5: // '\005'
            case 6: // '\006'
                if(overflowCounter == 0)
                {
                    byte newLevel;
                    if(t == 5 || t == 6)
                        newLevel = (byte)(currentEmbeddingLevel + 1 | 1);
                    else
                        newLevel = (byte)(currentEmbeddingLevel + 2 & -2);
                    if(newLevel < 62)
                    {
                        embeddingValueStack[stackCounter] = currentEmbeddingValue;
                        stackCounter++;
                        currentEmbeddingLevel = newLevel;
                        if(t == 2 || t == 6)
                            currentEmbeddingValue = (byte)(newLevel | 0x80);
                        else
                            currentEmbeddingValue = newLevel;
                        embeddings[i] = currentEmbeddingValue;
                        break;
                    }
                    if(currentEmbeddingLevel == 60)
                    {
                        overflowAlmostCounter++;
                        break;
                    }
                }
                overflowCounter++;
                break;

            case 7: // '\007'
                if(overflowCounter > 0)
                {
                    overflowCounter--;
                    break;
                }
                if(overflowAlmostCounter > 0 && currentEmbeddingLevel != 61)
                {
                    overflowAlmostCounter--;
                    break;
                }
                if(stackCounter > 0)
                {
                    stackCounter--;
                    currentEmbeddingValue = embeddingValueStack[stackCounter];
                    currentEmbeddingLevel = (byte)(currentEmbeddingValue & 0x7f);
                }
                break;

            case 15: // '\017'
                stackCounter = 0;
                overflowCounter = 0;
                overflowAlmostCounter = 0;
                currentEmbeddingLevel = paragraphEmbeddingLevel;
                currentEmbeddingValue = paragraphEmbeddingLevel;
                embeddings[i] = paragraphEmbeddingLevel;
                break;
            }
        }

        return embeddings;
    }

    private void resolveWeakTypes(int start, int limit, byte level, byte sor, byte eor)
    {
        byte preceedingCharacterType = sor;
        for(int i = start; i < limit; i++)
        {
            byte t = resultTypes[i];
            if(t == 13)
                resultTypes[i] = preceedingCharacterType;
            else
                preceedingCharacterType = t;
        }

label0:
        for(int i = start; i < limit; i++)
        {
            if(resultTypes[i] != 8)
                continue;
            int j = i - 1;
            do
            {
                if(j < start)
                    continue label0;
                byte t = resultTypes[j];
                if(t == 0 || t == 3 || t == 4)
                {
                    if(t == 4)
                        resultTypes[i] = 11;
                    continue label0;
                }
                j--;
            } while(true);
        }

        for(int i = start; i < limit; i++)
            if(resultTypes[i] == 4)
                resultTypes[i] = 3;

        for(int i = start + 1; i < limit - 1; i++)
        {
            if(resultTypes[i] != 9 && resultTypes[i] != 12)
                continue;
            byte prevSepType = resultTypes[i - 1];
            byte succSepType = resultTypes[i + 1];
            if(prevSepType == 8 && succSepType == 8)
            {
                resultTypes[i] = 8;
                continue;
            }
            if(resultTypes[i] == 12 && prevSepType == 11 && succSepType == 11)
                resultTypes[i] = 11;
        }

        for(int i = start; i < limit; i++)
        {
            if(resultTypes[i] != 10)
                continue;
            int runstart = i;
            int runlimit = findRunLimit(runstart, limit, new byte[] {
                10
            });
            byte t = runstart != start ? resultTypes[runstart - 1] : sor;
            if(t != 8)
                t = runlimit != limit ? resultTypes[runlimit] : eor;
            if(t == 8)
                setTypes(runstart, runlimit, (byte)8);
            i = runlimit;
        }

        for(int i = start; i < limit; i++)
        {
            byte t = resultTypes[i];
            if(t == 9 || t == 10 || t == 12)
                resultTypes[i] = 18;
        }

        for(int i = start; i < limit; i++)
        {
            if(resultTypes[i] != 8)
                continue;
            byte prevStrongType = sor;
            int j = i - 1;
            do
            {
                if(j < start)
                    break;
                byte t = resultTypes[j];
                if(t == 0 || t == 3)
                {
                    prevStrongType = t;
                    break;
                }
                j--;
            } while(true);
            if(prevStrongType == 0)
                resultTypes[i] = 0;
        }

    }

    private void resolveNeutralTypes(int start, int limit, byte level, byte sor, byte eor)
    {
        for(int i = start; i < limit; i++)
        {
            byte t = resultTypes[i];
            if(t != 17 && t != 18 && t != 15 && t != 16)
                continue;
            int runstart = i;
            int runlimit = findRunLimit(runstart, limit, new byte[] {
                15, 16, 17, 18
            });
            byte leadingType;
            if(runstart == start)
            {
                leadingType = sor;
            } else
            {
                leadingType = resultTypes[runstart - 1];
                if(leadingType != 0 && leadingType != 3)
                    if(leadingType == 11)
                        leadingType = 3;
                    else
                    if(leadingType == 8)
                        leadingType = 3;
            }
            byte trailingType;
            if(runlimit == limit)
            {
                trailingType = eor;
            } else
            {
                trailingType = resultTypes[runlimit];
                if(trailingType != 0 && trailingType != 3)
                    if(trailingType == 11)
                        trailingType = 3;
                    else
                    if(trailingType == 8)
                        trailingType = 3;
            }
            byte resolvedType;
            if(leadingType == trailingType)
                resolvedType = leadingType;
            else
                resolvedType = typeForLevel(level);
            setTypes(runstart, runlimit, resolvedType);
            i = runlimit;
        }

    }

    private void resolveImplicitLevels(int start, int limit, byte level, byte sor, byte eor)
    {
        if((level & 1) == 0)
        {
            for(int i = start; i < limit; i++)
            {
                byte t = resultTypes[i];
                if(t != 0)
                    if(t == 3)
                        resultLevels[i]++;
                    else
                        resultLevels[i] += 2;
            }

        } else
        {
            for(int i = start; i < limit; i++)
            {
                byte t = resultTypes[i];
                if(t != 3)
                    resultLevels[i]++;
            }

        }
    }

    public byte[] getLevels()
    {
        return getLevels(new int[] {
            textLength
        });
    }

    public byte[] getLevels(int linebreaks[])
    {
        validateLineBreaks(linebreaks, textLength);
        byte result[] = (byte[])(byte[])resultLevels.clone();
        for(int i = 0; i < result.length; i++)
        {
            byte t = initialTypes[i];
            if(t != 15 && t != 16)
                continue;
            result[i] = paragraphEmbeddingLevel;
            for(int j = i - 1; j >= 0 && isWhitespace(initialTypes[j]); j--)
                result[j] = paragraphEmbeddingLevel;

        }

        int start = 0;
        for(int i = 0; i < linebreaks.length; i++)
        {
            int limit = linebreaks[i];
            for(int j = limit - 1; j >= start && isWhitespace(initialTypes[j]); j--)
                result[j] = paragraphEmbeddingLevel;

            start = limit;
        }

        return result;
    }

    public int[] getReordering(int linebreaks[])
    {
        validateLineBreaks(linebreaks, textLength);
        byte levels[] = getLevels(linebreaks);
        return computeMultilineReordering(levels, linebreaks);
    }

    private static int[] computeMultilineReordering(byte levels[], int linebreaks[])
    {
        int result[] = new int[levels.length];
        int start = 0;
        for(int i = 0; i < linebreaks.length; i++)
        {
            int limit = linebreaks[i];
            byte templevels[] = new byte[limit - start];
            System.arraycopy(levels, start, templevels, 0, templevels.length);
            int temporder[] = computeReordering(templevels);
            for(int j = 0; j < temporder.length; j++)
                result[start + j] = temporder[j] + start;

            start = limit;
        }

        return result;
    }

    private static int[] computeReordering(byte levels[])
    {
        int lineLength = levels.length;
        int result[] = new int[lineLength];
        for(int i = 0; i < lineLength; i++)
            result[i] = i;

        byte highestLevel = 0;
        byte lowestOddLevel = 63;
        for(int i = 0; i < lineLength; i++)
        {
            byte level = levels[i];
            if(level > highestLevel)
                highestLevel = level;
            if((level & 1) != 0 && level < lowestOddLevel)
                lowestOddLevel = level;
        }

        for(int level = highestLevel; level >= lowestOddLevel; level--)
        {
            for(int i = 0; i < lineLength; i++)
            {
                if(levels[i] < level)
                    continue;
                int start = i;
                int limit;
                for(limit = i + 1; limit < lineLength && levels[limit] >= level; limit++);
                int j = start;
                for(int k = limit - 1; j < k; k--)
                {
                    int temp = result[j];
                    result[j] = result[k];
                    result[k] = temp;
                    j++;
                }

                i = limit;
            }

        }

        return result;
    }

    public byte getBaseLevel()
    {
        return paragraphEmbeddingLevel;
    }

    private static boolean isWhitespace(byte biditype)
    {
        switch(biditype)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 14: // '\016'
        case 17: // '\021'
            return true;

        case 3: // '\003'
        case 4: // '\004'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 15: // '\017'
        case 16: // '\020'
        default:
            return false;
        }
    }

    private static byte typeForLevel(int level)
    {
        return ((byte)((level & 1) != 0 ? 3 : 0));
    }

    private int findRunLimit(int index, int limit, byte validSet[])
    {
label0:
        {
            index--;
label1:
            do
            {
                if(++index >= limit)
                    break label0;
                byte t = resultTypes[index];
                int i = 0;
                do
                {
                    if(i >= validSet.length)
                        break label1;
                    if(t == validSet[i])
                        continue label1;
                    i++;
                } while(true);
            } while(true);
            return index;
        }
        return limit;
    }

    private int findRunStart(int index, byte validSet[])
    {
label0:
        {
label1:
            do
            {
                if(--index < 0)
                    break label0;
                byte t = resultTypes[index];
                int i = 0;
                do
                {
                    if(i >= validSet.length)
                        break label1;
                    if(t == validSet[i])
                        continue label1;
                    i++;
                } while(true);
            } while(true);
            return index + 1;
        }
        return 0;
    }

    private void setTypes(int start, int limit, byte newType)
    {
        for(int i = start; i < limit; i++)
            resultTypes[i] = newType;

    }

    private void setLevels(int start, int limit, byte newLevel)
    {
        for(int i = start; i < limit; i++)
            resultLevels[i] = newLevel;

    }

    private static void validateTypes(byte types[])
    {
        if(types == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("types.is.null", new Object[0]));
        for(int i = 0; i < types.length; i++)
            if(types[i] < 0 || types[i] > 18)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.type.value.at.1.2", new Object[] {
                    String.valueOf(i), String.valueOf(types[i])
                }));

        for(int i = 0; i < types.length - 1; i++)
            if(types[i] == 15)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("b.type.before.end.of.paragraph.at.index.1", i));

    }

    private static void validateParagraphEmbeddingLevel(byte paragraphEmbeddingLevel)
    {
        if(paragraphEmbeddingLevel != -1 && paragraphEmbeddingLevel != 0 && paragraphEmbeddingLevel != 1)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("illegal.paragraph.embedding.level.1", paragraphEmbeddingLevel));
        else
            return;
    }

    private static void validateLineBreaks(int linebreaks[], int textLength)
    {
        int prev = 0;
        for(int i = 0; i < linebreaks.length; i++)
        {
            int next = linebreaks[i];
            if(next <= prev)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("bad.linebreak.1.at.index.2", new Object[] {
                    String.valueOf(next), String.valueOf(i)
                }));
            prev = next;
        }

        if(prev != textLength)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("last.linebreak.must.be.at.1", textLength));
        else
            return;
    }

    private byte initialTypes[];
    private byte embeddings[];
    private byte paragraphEmbeddingLevel;
    private int textLength;
    private byte resultTypes[];
    private byte resultLevels[];
    public static final byte L = 0;
    public static final byte LRE = 1;
    public static final byte LRO = 2;
    public static final byte R = 3;
    public static final byte AL = 4;
    public static final byte RLE = 5;
    public static final byte RLO = 6;
    public static final byte PDF = 7;
    public static final byte EN = 8;
    public static final byte ES = 9;
    public static final byte ET = 10;
    public static final byte AN = 11;
    public static final byte CS = 12;
    public static final byte NSM = 13;
    public static final byte BN = 14;
    public static final byte B = 15;
    public static final byte S = 16;
    public static final byte WS = 17;
    public static final byte ON = 18;
    public static final byte TYPE_MIN = 0;
    public static final byte TYPE_MAX = 18;
    private static final byte rtypes[];
    private static char baseTypes[] = {
        '\0', '\b', '\016', '\t', '\t', '\020', '\n', '\n', '\017', '\013', 
        '\013', '\020', '\f', '\f', '\021', '\r', '\r', '\017', '\016', '\033', 
        '\016', '\034', '\036', '\017', '\037', '\037', '\020', ' ', ' ', '\021', 
        '!', '"', '\022', '#', '%', '\n', '&', '*', '\022', '+', 
        '+', '\n', ',', ',', '\f', '-', '-', '\n', '.', '.', 
        '\f', '/', '/', '\t', '0', '9', '\b', ':', ':', '\f', 
        ';', '@', '\022', 'A', 'Z', '\0', '[', '`', '\022', 'a', 
        'z', '\0', '{', '~', '\022', '\177', '\204', '\016', '\205', '\205', 
        '\017', '\206', '\237', '\016', '\240', '\240', '\f', '\241', '\241', '\022', 
        '\242', '\245', '\n', '\246', '\251', '\022', '\252', '\252', '\0', '\253', 
        '\257', '\022', '\260', '\261', '\n', '\262', '\263', '\b', '\264', '\264', 
        '\022', '\265', '\265', '\0', '\266', '\270', '\022', '\271', '\271', '\b', 
        '\272', '\272', '\0', '\273', '\277', '\022', '\300', '\326', '\0', '\327', 
        '\327', '\022', '\330', '\366', '\0', '\367', '\367', '\022', '\370', '\u02B8', 
        '\0', '\u02B9', '\u02BA', '\022', '\u02BB', '\u02C1', '\0', '\u02C2', '\u02CF', '\022', 
        '\u02D0', '\u02D1', '\0', '\u02D2', '\u02DF', '\022', '\u02E0', '\u02E4', '\0', '\u02E5', 
        '\u02ED', '\022', '\u02EE', '\u02EE', '\0', '\u02EF', '\u02FF', '\022', '\u0300', '\u0357', 
        '\r', '\u0358', '\u035C', '\0', '\u035D', '\u036F', '\r', '\u0370', '\u0373', '\0', 
        '\u0374', '\u0375', '\022', '\u0376', '\u037D', '\0', '\u037E', '\u037E', '\022', '\u037F', 
        '\u0383', '\0', '\u0384', '\u0385', '\022', '\u0386', '\u0386', '\0', '\u0387', '\u0387', 
        '\022', '\u0388', '\u03F5', '\0', '\u03F6', '\u03F6', '\022', '\u03F7', '\u0482', '\0', 
        '\u0483', '\u0486', '\r', '\u0487', '\u0487', '\0', '\u0488', '\u0489', '\r', '\u048A', 
        '\u0589', '\0', '\u058A', '\u058A', '\022', '\u058B', '\u0590', '\0', '\u0591', '\u05A1', 
        '\r', '\u05A2', '\u05A2', '\0', '\u05A3', '\u05B9', '\r', '\u05BA', '\u05BA', '\0', 
        '\u05BB', '\u05BD', '\r', '\u05BE', '\u05BE', '\003', '\u05BF', '\u05BF', '\r', '\u05C0', 
        '\u05C0', '\003', '\u05C1', '\u05C2', '\r', '\u05C3', '\u05C3', '\003', '\u05C4', '\u05C4', 
        '\r', '\u05C5', '\u05CF', '\0', '\u05D0', '\u05EA', '\003', '\u05EB', '\u05EF', '\0', 
        '\u05F0', '\u05F4', '\003', '\u05F5', '\u05FF', '\0', '\u0600', '\u0603', '\004', '\u0604', 
        '\u060B', '\0', '\u060C', '\u060C', '\f', '\u060D', '\u060D', '\004', '\u060E', '\u060F', 
        '\022', '\u0610', '\u0615', '\r', '\u0616', '\u061A', '\0', '\u061B', '\u061B', '\004', 
        '\u061C', '\u061E', '\0', '\u061F', '\u061F', '\004', '\u0620', '\u0620', '\0', '\u0621', 
        '\u063A', '\004', '\u063B', '\u063F', '\0', '\u0640', '\u064A', '\004', '\u064B', '\u0658', 
        '\r', '\u0659', '\u065F', '\0', '\u0660', '\u0669', '\013', '\u066A', '\u066A', '\n', 
        '\u066B', '\u066C', '\013', '\u066D', '\u066F', '\004', '\u0670', '\u0670', '\r', '\u0671', 
        '\u06D5', '\004', '\u06D6', '\u06DC', '\r', '\u06DD', '\u06DD', '\004', '\u06DE', '\u06E4', 
        '\r', '\u06E5', '\u06E6', '\004', '\u06E7', '\u06E8', '\r', '\u06E9', '\u06E9', '\022', 
        '\u06EA', '\u06ED', '\r', '\u06EE', '\u06EF', '\004', '\u06F0', '\u06F9', '\b', '\u06FA', 
        '\u070D', '\004', '\u070E', '\u070E', '\0', '\u070F', '\u070F', '\016', '\u0710', '\u0710', 
        '\004', '\u0711', '\u0711', '\r', '\u0712', '\u072F', '\004', '\u0730', '\u074A', '\r', 
        '\u074B', '\u074C', '\0', '\u074D', '\u074F', '\004', '\u0750', '\u077F', '\0', '\u0780', 
        '\u07A5', '\004', '\u07A6', '\u07B0', '\r', '\u07B1', '\u07B1', '\004', '\u07B2', '\u0900', 
        '\0', '\u0901', '\u0902', '\r', '\u0903', '\u093B', '\0', '\u093C', '\u093C', '\r', 
        '\u093D', '\u0940', '\0', '\u0941', '\u0948', '\r', '\u0949', '\u094C', '\0', '\u094D', 
        '\u094D', '\r', '\u094E', '\u0950', '\0', '\u0951', '\u0954', '\r', '\u0955', '\u0961', 
        '\0', '\u0962', '\u0963', '\r', '\u0964', '\u0980', '\0', '\u0981', '\u0981', '\r', 
        '\u0982', '\u09BB', '\0', '\u09BC', '\u09BC', '\r', '\u09BD', '\u09C0', '\0', '\u09C1', 
        '\u09C4', '\r', '\u09C5', '\u09CC', '\0', '\u09CD', '\u09CD', '\r', '\u09CE', '\u09E1', 
        '\0', '\u09E2', '\u09E3', '\r', '\u09E4', '\u09F1', '\0', '\u09F2', '\u09F3', '\n', 
        '\u09F4', '\u0A00', '\0', '\u0A01', '\u0A02', '\r', '\u0A03', '\u0A3B', '\0', '\u0A3C', 
        '\u0A3C', '\r', '\u0A3D', '\u0A40', '\0', '\u0A41', '\u0A42', '\r', '\u0A43', '\u0A46', 
        '\0', '\u0A47', '\u0A48', '\r', '\u0A49', '\u0A4A', '\0', '\u0A4B', '\u0A4D', '\r', 
        '\u0A4E', '\u0A6F', '\0', '\u0A70', '\u0A71', '\r', '\u0A72', '\u0A80', '\0', '\u0A81', 
        '\u0A82', '\r', '\u0A83', '\u0ABB', '\0', '\u0ABC', '\u0ABC', '\r', '\u0ABD', '\u0AC0', 
        '\0', '\u0AC1', '\u0AC5', '\r', '\u0AC6', '\u0AC6', '\0', '\u0AC7', '\u0AC8', '\r', 
        '\u0AC9', '\u0ACC', '\0', '\u0ACD', '\u0ACD', '\r', '\u0ACE', '\u0AE1', '\0', '\u0AE2', 
        '\u0AE3', '\r', '\u0AE4', '\u0AF0', '\0', '\u0AF1', '\u0AF1', '\n', '\u0AF2', '\u0B00', 
        '\0', '\u0B01', '\u0B01', '\r', '\u0B02', '\u0B3B', '\0', '\u0B3C', '\u0B3C', '\r', 
        '\u0B3D', '\u0B3E', '\0', '\u0B3F', '\u0B3F', '\r', '\u0B40', '\u0B40', '\0', '\u0B41', 
        '\u0B43', '\r', '\u0B44', '\u0B4C', '\0', '\u0B4D', '\u0B4D', '\r', '\u0B4E', '\u0B55', 
        '\0', '\u0B56', '\u0B56', '\r', '\u0B57', '\u0B81', '\0', '\u0B82', '\u0B82', '\r', 
        '\u0B83', '\u0BBF', '\0', '\u0BC0', '\u0BC0', '\r', '\u0BC1', '\u0BCC', '\0', '\u0BCD', 
        '\u0BCD', '\r', '\u0BCE', '\u0BF2', '\0', '\u0BF3', '\u0BF8', '\022', '\u0BF9', '\u0BF9', 
        '\n', '\u0BFA', '\u0BFA', '\022', '\u0BFB', '\u0C3D', '\0', '\u0C3E', '\u0C40', '\r', 
        '\u0C41', '\u0C45', '\0', '\u0C46', '\u0C48', '\r', '\u0C49', '\u0C49', '\0', '\u0C4A', 
        '\u0C4D', '\r', '\u0C4E', '\u0C54', '\0', '\u0C55', '\u0C56', '\r', '\u0C57', '\u0CBB', 
        '\0', '\u0CBC', '\u0CBC', '\r', '\u0CBD', '\u0CCB', '\0', '\u0CCC', '\u0CCD', '\r', 
        '\u0CCE', '\u0D40', '\0', '\u0D41', '\u0D43', '\r', '\u0D44', '\u0D4C', '\0', '\u0D4D', 
        '\u0D4D', '\r', '\u0D4E', '\u0DC9', '\0', '\u0DCA', '\u0DCA', '\r', '\u0DCB', '\u0DD1', 
        '\0', '\u0DD2', '\u0DD4', '\r', '\u0DD5', '\u0DD5', '\0', '\u0DD6', '\u0DD6', '\r', 
        '\u0DD7', '\u0E30', '\0', '\u0E31', '\u0E31', '\r', '\u0E32', '\u0E33', '\0', '\u0E34', 
        '\u0E3A', '\r', '\u0E3B', '\u0E3E', '\0', '\u0E3F', '\u0E3F', '\n', '\u0E40', '\u0E46', 
        '\0', '\u0E47', '\u0E4E', '\r', '\u0E4F', '\u0EB0', '\0', '\u0EB1', '\u0EB1', '\r', 
        '\u0EB2', '\u0EB3', '\0', '\u0EB4', '\u0EB9', '\r', '\u0EBA', '\u0EBA', '\0', '\u0EBB', 
        '\u0EBC', '\r', '\u0EBD', '\u0EC7', '\0', '\u0EC8', '\u0ECD', '\r', '\u0ECE', '\u0F17', 
        '\0', '\u0F18', '\u0F19', '\r', '\u0F1A', '\u0F34', '\0', '\u0F35', '\u0F35', '\r', 
        '\u0F36', '\u0F36', '\0', '\u0F37', '\u0F37', '\r', '\u0F38', '\u0F38', '\0', '\u0F39', 
        '\u0F39', '\r', '\u0F3A', '\u0F3D', '\022', '\u0F3E', '\u0F70', '\0', '\u0F71', '\u0F7E', 
        '\r', '\u0F7F', '\u0F7F', '\0', '\u0F80', '\u0F84', '\r', '\u0F85', '\u0F85', '\0', 
        '\u0F86', '\u0F87', '\r', '\u0F88', '\u0F8F', '\0', '\u0F90', '\u0F97', '\r', '\u0F98', 
        '\u0F98', '\0', '\u0F99', '\u0FBC', '\r', '\u0FBD', '\u0FC5', '\0', '\u0FC6', '\u0FC6', 
        '\r', '\u0FC7', '\u102C', '\0', '\u102D', '\u1030', '\r', '\u1031', '\u1031', '\0', 
        '\u1032', '\u1032', '\r', '\u1033', '\u1035', '\0', '\u1036', '\u1037', '\r', '\u1038', 
        '\u1038', '\0', '\u1039', '\u1039', '\r', '\u103A', '\u1057', '\0', '\u1058', '\u1059', 
        '\r', '\u105A', '\u167F', '\0', '\u1680', '\u1680', '\021', '\u1681', '\u169A', '\0', 
        '\u169B', '\u169C', '\022', '\u169D', '\u1711', '\0', '\u1712', '\u1714', '\r', '\u1715', 
        '\u1731', '\0', '\u1732', '\u1734', '\r', '\u1735', '\u1751', '\0', '\u1752', '\u1753', 
        '\r', '\u1754', '\u1771', '\0', '\u1772', '\u1773', '\r', '\u1774', '\u17B6', '\0', 
        '\u17B7', '\u17BD', '\r', '\u17BE', '\u17C5', '\0', '\u17C6', '\u17C6', '\r', '\u17C7', 
        '\u17C8', '\0', '\u17C9', '\u17D3', '\r', '\u17D4', '\u17DA', '\0', '\u17DB', '\u17DB', 
        '\n', '\u17DC', '\u17DC', '\0', '\u17DD', '\u17DD', '\r', '\u17DE', '\u17EF', '\0', 
        '\u17F0', '\u17F9', '\022', '\u17FA', '\u17FF', '\0', '\u1800', '\u180A', '\022', '\u180B', 
        '\u180D', '\r', '\u180E', '\u180E', '\021', '\u180F', '\u18A8', '\0', '\u18A9', '\u18A9', 
        '\r', '\u18AA', '\u191F', '\0', '\u1920', '\u1922', '\r', '\u1923', '\u1926', '\0', 
        '\u1927', '\u192B', '\r', '\u192C', '\u1931', '\0', '\u1932', '\u1932', '\r', '\u1933', 
        '\u1938', '\0', '\u1939', '\u193B', '\r', '\u193C', '\u193F', '\0', '\u1940', '\u1940', 
        '\022', '\u1941', '\u1943', '\0', '\u1944', '\u1945', '\022', '\u1946', '\u19DF', '\0', 
        '\u19E0', '\u19FF', '\022', '\u1A00', '\u1FBC', '\0', '\u1FBD', '\u1FBD', '\022', '\u1FBE', 
        '\u1FBE', '\0', '\u1FBF', '\u1FC1', '\022', '\u1FC2', '\u1FCC', '\0', '\u1FCD', '\u1FCF', 
        '\022', '\u1FD0', '\u1FDC', '\0', '\u1FDD', '\u1FDF', '\022', '\u1FE0', '\u1FEC', '\0', 
        '\u1FED', '\u1FEF', '\022', '\u1FF0', '\u1FFC', '\0', '\u1FFD', '\u1FFE', '\022', '\u1FFF', 
        '\u1FFF', '\0', '\u2000', '\u200A', '\021', '\u200B', '\u200D', '\016', '\u200E', '\u200E', 
        '\0', '\u200F', '\u200F', '\003', '\u2010', '\u2027', '\022', '\u2028', '\u2028', '\021', 
        '\u2029', '\u2029', '\017', '\u202A', '\u202A', '\001', '\u202B', '\u202B', '\005', '\u202C', 
        '\u202C', '\007', '\u202D', '\u202D', '\002', '\u202E', '\u202E', '\006', '\u202F', '\u202F', 
        '\021', '\u2030', '\u2034', '\n', '\u2035', '\u2054', '\022', '\u2055', '\u2056', '\0', 
        '\u2057', '\u2057', '\022', '\u2058', '\u205E', '\0', '\u205F', '\u205F', '\021', '\u2060', 
        '\u2063', '\016', '\u2064', '\u2069', '\0', '\u206A', '\u206F', '\016', '\u2070', '\u2070', 
        '\b', '\u2071', '\u2073', '\0', '\u2074', '\u2079', '\b', '\u207A', '\u207B', '\n', 
        '\u207C', '\u207E', '\022', '\u207F', '\u207F', '\0', '\u2080', '\u2089', '\b', '\u208A', 
        '\u208B', '\n', '\u208C', '\u208E', '\022', '\u208F', '\u209F', '\0', '\u20A0', '\u20B1', 
        '\n', '\u20B2', '\u20CF', '\0', '\u20D0', '\u20EA', '\r', '\u20EB', '\u20FF', '\0', 
        '\u2100', '\u2101', '\022', '\u2102', '\u2102', '\0', '\u2103', '\u2106', '\022', '\u2107', 
        '\u2107', '\0', '\u2108', '\u2109', '\022', '\u210A', '\u2113', '\0', '\u2114', '\u2114', 
        '\022', '\u2115', '\u2115', '\0', '\u2116', '\u2118', '\022', '\u2119', '\u211D', '\0', 
        '\u211E', '\u2123', '\022', '\u2124', '\u2124', '\0', '\u2125', '\u2125', '\022', '\u2126', 
        '\u2126', '\0', '\u2127', '\u2127', '\022', '\u2128', '\u2128', '\0', '\u2129', '\u2129', 
        '\022', '\u212A', '\u212D', '\0', '\u212E', '\u212E', '\n', '\u212F', '\u2131', '\0', 
        '\u2132', '\u2132', '\022', '\u2133', '\u2139', '\0', '\u213A', '\u213B', '\022', '\u213C', 
        '\u213F', '\0', '\u2140', '\u2144', '\022', '\u2145', '\u2149', '\0', '\u214A', '\u214B', 
        '\022', '\u214C', '\u2152', '\0', '\u2153', '\u215F', '\022', '\u2160', '\u218F', '\0', 
        '\u2190', '\u2211', '\022', '\u2212', '\u2213', '\n', '\u2214', '\u2335', '\022', '\u2336', 
        '\u237A', '\0', '\u237B', '\u2394', '\022', '\u2395', '\u2395', '\0', '\u2396', '\u23D0', 
        '\022', '\u23D1', '\u23FF', '\0', '\u2400', '\u2426', '\022', '\u2427', '\u243F', '\0', 
        '\u2440', '\u244A', '\022', '\u244B', '\u245F', '\0', '\u2460', '\u249B', '\b', '\u249C', 
        '\u24E9', '\0', '\u24EA', '\u24EA', '\b', '\u24EB', '\u2617', '\022', '\u2618', '\u2618', 
        '\0', '\u2619', '\u267D', '\022', '\u267E', '\u267F', '\0', '\u2680', '\u2691', '\022', 
        '\u2692', '\u269F', '\0', '\u26A0', '\u26A1', '\022', '\u26A2', '\u2700', '\0', '\u2701', 
        '\u2704', '\022', '\u2705', '\u2705', '\0', '\u2706', '\u2709', '\022', '\u270A', '\u270B', 
        '\0', '\u270C', '\u2727', '\022', '\u2728', '\u2728', '\0', '\u2729', '\u274B', '\022', 
        '\u274C', '\u274C', '\0', '\u274D', '\u274D', '\022', '\u274E', '\u274E', '\0', '\u274F', 
        '\u2752', '\022', '\u2753', '\u2755', '\0', '\u2756', '\u2756', '\022', '\u2757', '\u2757', 
        '\0', '\u2758', '\u275E', '\022', '\u275F', '\u2760', '\0', '\u2761', '\u2794', '\022', 
        '\u2795', '\u2797', '\0', '\u2798', '\u27AF', '\022', '\u27B0', '\u27B0', '\0', '\u27B1', 
        '\u27BE', '\022', '\u27BF', '\u27CF', '\0', '\u27D0', '\u27EB', '\022', '\u27EC', '\u27EF', 
        '\0', '\u27F0', '\u2B0D', '\022', '\u2B0E', '\u2E7F', '\0', '\u2E80', '\u2E99', '\022', 
        '\u2E9A', '\u2E9A', '\0', '\u2E9B', '\u2EF3', '\022', '\u2EF4', '\u2EFF', '\0', '\u2F00', 
        '\u2FD5', '\022', '\u2FD6', '\u2FEF', '\0', '\u2FF0', '\u2FFB', '\022', '\u2FFC', '\u2FFF', 
        '\0', '\u3000', '\u3000', '\021', '\u3001', '\u3004', '\022', '\u3005', '\u3007', '\0', 
        '\u3008', '\u3020', '\022', '\u3021', '\u3029', '\0', '\u302A', '\u302F', '\r', '\u3030', 
        '\u3030', '\022', '\u3031', '\u3035', '\0', '\u3036', '\u3037', '\022', '\u3038', '\u303C', 
        '\0', '\u303D', '\u303F', '\022', '\u3040', '\u3098', '\0', '\u3099', '\u309A', '\r', 
        '\u309B', '\u309C', '\022', '\u309D', '\u309F', '\0', '\u30A0', '\u30A0', '\022', '\u30A1', 
        '\u30FA', '\0', '\u30FB', '\u30FB', '\022', '\u30FC', '\u321C', '\0', '\u321D', '\u321E', 
        '\022', '\u321F', '\u324F', '\0', '\u3250', '\u325F', '\022', '\u3260', '\u327B', '\0', 
        '\u327C', '\u327D', '\022', '\u327E', '\u32B0', '\0', '\u32B1', '\u32BF', '\022', '\u32C0', 
        '\u32CB', '\0', '\u32CC', '\u32CF', '\022', '\u32D0', '\u3376', '\0', '\u3377', '\u337A', 
        '\022', '\u337B', '\u33DD', '\0', '\u33DE', '\u33DF', '\022', '\u33E0', '\u33FE', '\0', 
        '\u33FF', '\u33FF', '\022', '\u3400', '\u4DBF', '\0', '\u4DC0', '\u4DFF', '\022', '\u4E00', 
        '\uA48F', '\0', '\uA490', '\uA4C6', '\022', '\uA4C7', '\uFB1C', '\0', '\uFB1D', '\uFB1D', 
        '\003', '\uFB1E', '\uFB1E', '\r', '\uFB1F', '\uFB28', '\003', '\uFB29', '\uFB29', '\n', 
        '\uFB2A', '\uFB36', '\003', '\uFB37', '\uFB37', '\0', '\uFB38', '\uFB3C', '\003', '\uFB3D', 
        '\uFB3D', '\0', '\uFB3E', '\uFB3E', '\003', '\uFB3F', '\uFB3F', '\0', '\uFB40', '\uFB41', 
        '\003', '\uFB42', '\uFB42', '\0', '\uFB43', '\uFB44', '\003', '\uFB45', '\uFB45', '\0', 
        '\uFB46', '\uFB4F', '\003', '\uFB50', '\uFBB1', '\004', '\uFBB2', '\uFBD2', '\0', '\uFBD3', 
        '\uFD3D', '\004', '\uFD3E', '\uFD3F', '\022', '\uFD40', '\uFD4F', '\0', '\uFD50', '\uFD8F', 
        '\004', '\uFD90', '\uFD91', '\0', '\uFD92', '\uFDC7', '\004', '\uFDC8', '\uFDEF', '\0', 
        '\uFDF0', '\uFDFC', '\004', '\uFDFD', '\uFDFD', '\022', '\uFDFE', '\uFDFF', '\0', '\uFE00', 
        '\uFE0F', '\r', '\uFE10', '\uFE1F', '\0', '\uFE20', '\uFE23', '\r', '\uFE24', '\uFE2F', 
        '\0', '\uFE30', '\uFE4F', '\022', '\uFE50', '\uFE50', '\f', '\uFE51', '\uFE51', '\022', 
        '\uFE52', '\uFE52', '\f', '\uFE53', '\uFE53', '\0', '\uFE54', '\uFE54', '\022', '\uFE55', 
        '\uFE55', '\f', '\uFE56', '\uFE5E', '\022', '\uFE5F', '\uFE5F', '\n', '\uFE60', '\uFE61', 
        '\022', '\uFE62', '\uFE63', '\n', '\uFE64', '\uFE66', '\022', '\uFE67', '\uFE67', '\0', 
        '\uFE68', '\uFE68', '\022', '\uFE69', '\uFE6A', '\n', '\uFE6B', '\uFE6B', '\022', '\uFE6C', 
        '\uFE6F', '\0', '\uFE70', '\uFE74', '\004', '\uFE75', '\uFE75', '\0', '\uFE76', '\uFEFC', 
        '\004', '\uFEFD', '\uFEFE', '\0', '\uFEFF', '\uFEFF', '\016', '\uFF00', '\uFF00', '\0', 
        '\uFF01', '\uFF02', '\022', '\uFF03', '\uFF05', '\n', '\uFF06', '\uFF0A', '\022', '\uFF0B', 
        '\uFF0B', '\n', '\uFF0C', '\uFF0C', '\f', '\uFF0D', '\uFF0D', '\n', '\uFF0E', '\uFF0E', 
        '\f', '\uFF0F', '\uFF0F', '\t', '\uFF10', '\uFF19', '\b', '\uFF1A', '\uFF1A', '\f', 
        '\uFF1B', '\uFF20', '\022', '\uFF21', '\uFF3A', '\0', '\uFF3B', '\uFF40', '\022', '\uFF41', 
        '\uFF5A', '\0', '\uFF5B', '\uFF65', '\022', '\uFF66', '\uFFDF', '\0', '\uFFE0', '\uFFE1', 
        '\n', '\uFFE2', '\uFFE4', '\022', '\uFFE5', '\uFFE6', '\n', '\uFFE7', '\uFFE7', '\0', 
        '\uFFE8', '\uFFEE', '\022', '\uFFEF', '\uFFF8', '\0', '\uFFF9', '\uFFFB', '\016', '\uFFFC', 
        '\uFFFD', '\022', '\uFFFE', '\uFFFF', '\0'
    };

    static 
    {
        rtypes = new byte[0x10000];
        for(int k = 0; k < baseTypes.length; k++)
        {
            int start = baseTypes[k];
            int end = baseTypes[++k];
            byte b = (byte)baseTypes[++k];
            while(start <= end) 
                rtypes[start++] = b;
        }

    }
}
