// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GMSSPrivateKeyParameters.java

package co.org.bouncy.pqc.crypto.gmss;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.pqc.crypto.gmss.util.GMSSRandom;
import co.org.bouncy.pqc.crypto.gmss.util.WinternitzOTSignature;
import co.org.bouncy.util.Arrays;
import java.io.PrintStream;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.pqc.crypto.gmss:
//            GMSSKeyParameters, GMSSRootCalc, GMSSLeaf, GMSSRootSig, 
//            GMSSParameters, GMSSDigestProvider, Treehash

public class GMSSPrivateKeyParameters extends GMSSKeyParameters
{

    public GMSSPrivateKeyParameters(byte currentSeed[][], byte nextNextSeed[][], byte currentAuthPath[][][], byte nextAuthPath[][][], Treehash currentTreehash[][], Treehash nextTreehash[][], Vector currentStack[], 
            Vector nextStack[], Vector currentRetain[][], Vector nextRetain[][], byte nextRoot[][], byte currentRootSig[][], GMSSParameters gmssParameterset, GMSSDigestProvider digestProvider)
    {
        this(null, currentSeed, nextNextSeed, currentAuthPath, nextAuthPath, (byte[][][])null, currentTreehash, nextTreehash, currentStack, nextStack, currentRetain, nextRetain, null, null, null, null, nextRoot, null, currentRootSig, null, gmssParameterset, digestProvider);
    }

    public GMSSPrivateKeyParameters(int index[], byte currentSeeds[][], byte nextNextSeeds[][], byte currentAuthPaths[][][], byte nextAuthPaths[][][], byte keep[][][], Treehash currentTreehash[][], 
            Treehash nextTreehash[][], Vector currentStack[], Vector nextStack[], Vector currentRetain[][], Vector nextRetain[][], GMSSLeaf nextNextLeaf[], GMSSLeaf upperLeaf[], 
            GMSSLeaf upperTreehashLeaf[], int minTreehash[], byte nextRoot[][], GMSSRootCalc nextNextRoot[], byte currentRootSig[][], GMSSRootSig nextRootSig[], GMSSParameters gmssParameterset, 
            GMSSDigestProvider digestProvider)
    {
        super(true, gmssParameterset);
        used = false;
        messDigestTrees = digestProvider.get();
        mdLength = messDigestTrees.getDigestSize();
        gmssPS = gmssParameterset;
        otsIndex = gmssParameterset.getWinternitzParameter();
        K = gmssParameterset.getK();
        heightOfTrees = gmssParameterset.getHeightOfTrees();
        numLayer = gmssPS.getNumOfLayers();
        if(index == null)
        {
            this.index = new int[numLayer];
            for(int i = 0; i < numLayer; i++)
                this.index[i] = 0;

        } else
        {
            this.index = index;
        }
        this.currentSeeds = currentSeeds;
        this.nextNextSeeds = nextNextSeeds;
        this.currentAuthPaths = currentAuthPaths;
        this.nextAuthPaths = nextAuthPaths;
        if(keep == null)
        {
            this.keep = new byte[numLayer][][];
            for(int i = 0; i < numLayer; i++)
                this.keep[i] = new byte[(int)Math.floor(heightOfTrees[i] / 2)][mdLength];

        } else
        {
            this.keep = keep;
        }
        if(currentStack == null)
        {
            this.currentStack = new Vector[numLayer];
            for(int i = 0; i < numLayer; i++)
                this.currentStack[i] = new Vector();

        } else
        {
            this.currentStack = currentStack;
        }
        if(nextStack == null)
        {
            this.nextStack = new Vector[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
                this.nextStack[i] = new Vector();

        } else
        {
            this.nextStack = nextStack;
        }
        this.currentTreehash = currentTreehash;
        this.nextTreehash = nextTreehash;
        this.currentRetain = currentRetain;
        this.nextRetain = nextRetain;
        this.nextRoot = nextRoot;
        this.digestProvider = digestProvider;
        if(nextNextRoot == null)
        {
            this.nextNextRoot = new GMSSRootCalc[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
                this.nextNextRoot[i] = new GMSSRootCalc(heightOfTrees[i + 1], K[i + 1], this.digestProvider);

        } else
        {
            this.nextNextRoot = nextNextRoot;
        }
        this.currentRootSig = currentRootSig;
        numLeafs = new int[numLayer];
        for(int i = 0; i < numLayer; i++)
            numLeafs[i] = 1 << heightOfTrees[i];

        gmssRandom = new GMSSRandom(messDigestTrees);
        if(numLayer > 1)
        {
            if(nextNextLeaf == null)
            {
                this.nextNextLeaf = new GMSSLeaf[numLayer - 2];
                for(int i = 0; i < numLayer - 2; i++)
                    this.nextNextLeaf[i] = new GMSSLeaf(digestProvider.get(), otsIndex[i + 1], numLeafs[i + 2], this.nextNextSeeds[i]);

            } else
            {
                this.nextNextLeaf = nextNextLeaf;
            }
        } else
        {
            this.nextNextLeaf = new GMSSLeaf[0];
        }
        if(upperLeaf == null)
        {
            this.upperLeaf = new GMSSLeaf[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
                this.upperLeaf[i] = new GMSSLeaf(digestProvider.get(), otsIndex[i], numLeafs[i + 1], this.currentSeeds[i]);

        } else
        {
            this.upperLeaf = upperLeaf;
        }
        if(upperTreehashLeaf == null)
        {
            this.upperTreehashLeaf = new GMSSLeaf[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
                this.upperTreehashLeaf[i] = new GMSSLeaf(digestProvider.get(), otsIndex[i], numLeafs[i + 1]);

        } else
        {
            this.upperTreehashLeaf = upperTreehashLeaf;
        }
        if(minTreehash == null)
        {
            this.minTreehash = new int[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
                this.minTreehash[i] = -1;

        } else
        {
            this.minTreehash = minTreehash;
        }
        byte dummy[] = new byte[mdLength];
        byte OTSseed[] = new byte[mdLength];
        if(nextRootSig == null)
        {
            this.nextRootSig = new GMSSRootSig[numLayer - 1];
            for(int i = 0; i < numLayer - 1; i++)
            {
                System.arraycopy(currentSeeds[i], 0, dummy, 0, mdLength);
                gmssRandom.nextSeed(dummy);
                OTSseed = gmssRandom.nextSeed(dummy);
                this.nextRootSig[i] = new GMSSRootSig(digestProvider.get(), otsIndex[i], heightOfTrees[i + 1]);
                this.nextRootSig[i].initSign(OTSseed, nextRoot[i]);
            }

        } else
        {
            this.nextRootSig = nextRootSig;
        }
    }

    private GMSSPrivateKeyParameters(GMSSPrivateKeyParameters original)
    {
        super(true, original.getParameters());
        used = false;
        index = Arrays.clone(original.index);
        currentSeeds = Arrays.clone(original.currentSeeds);
        nextNextSeeds = Arrays.clone(original.nextNextSeeds);
        currentAuthPaths = Arrays.clone(original.currentAuthPaths);
        nextAuthPaths = Arrays.clone(original.nextAuthPaths);
        currentTreehash = original.currentTreehash;
        nextTreehash = original.nextTreehash;
        currentStack = original.currentStack;
        nextStack = original.nextStack;
        currentRetain = original.currentRetain;
        nextRetain = original.nextRetain;
        keep = Arrays.clone(original.keep);
        nextNextLeaf = original.nextNextLeaf;
        upperLeaf = original.upperLeaf;
        upperTreehashLeaf = original.upperTreehashLeaf;
        minTreehash = original.minTreehash;
        gmssPS = original.gmssPS;
        nextRoot = Arrays.clone(original.nextRoot);
        nextNextRoot = original.nextNextRoot;
        currentRootSig = original.currentRootSig;
        nextRootSig = original.nextRootSig;
        digestProvider = original.digestProvider;
        heightOfTrees = original.heightOfTrees;
        otsIndex = original.otsIndex;
        K = original.K;
        numLayer = original.numLayer;
        messDigestTrees = original.messDigestTrees;
        mdLength = original.mdLength;
        gmssRandom = original.gmssRandom;
        numLeafs = original.numLeafs;
    }

    public boolean isUsed()
    {
        return used;
    }

    public void markUsed()
    {
        used = true;
    }

    public GMSSPrivateKeyParameters nextKey()
    {
        GMSSPrivateKeyParameters nKey = new GMSSPrivateKeyParameters(this);
        nKey.nextKey(gmssPS.getNumOfLayers() - 1);
        return nKey;
    }

    private void nextKey(int layer)
    {
        if(layer == numLayer - 1)
            index[layer]++;
        if(index[layer] == numLeafs[layer])
        {
            if(numLayer != 1)
            {
                nextTree(layer);
                index[layer] = 0;
            }
        } else
        {
            updateKey(layer);
        }
    }

    private void nextTree(int layer)
    {
        if(layer > 0)
        {
            index[layer - 1]++;
            boolean lastTree = true;
            int z = layer;
            do
            {
                z--;
                if(index[z] < numLeafs[z])
                    lastTree = false;
            } while(lastTree && z > 0);
            if(!lastTree)
            {
                gmssRandom.nextSeed(currentSeeds[layer]);
                nextRootSig[layer - 1].updateSign();
                if(layer > 1)
                    nextNextLeaf[layer - 1 - 1] = nextNextLeaf[layer - 1 - 1].nextLeaf();
                upperLeaf[layer - 1] = upperLeaf[layer - 1].nextLeaf();
                if(minTreehash[layer - 1] >= 0)
                {
                    upperTreehashLeaf[layer - 1] = upperTreehashLeaf[layer - 1].nextLeaf();
                    byte leaf[] = upperTreehashLeaf[layer - 1].getLeaf();
                    try
                    {
                        currentTreehash[layer - 1][minTreehash[layer - 1]].update(gmssRandom, leaf);
                        if(!currentTreehash[layer - 1][minTreehash[layer - 1]].wasFinished());
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
                updateNextNextAuthRoot(layer);
                currentRootSig[layer - 1] = nextRootSig[layer - 1].getSig();
                for(int i = 0; i < heightOfTrees[layer] - K[layer]; i++)
                {
                    currentTreehash[layer][i] = nextTreehash[layer - 1][i];
                    nextTreehash[layer - 1][i] = nextNextRoot[layer - 1].getTreehash()[i];
                }

                for(int i = 0; i < heightOfTrees[layer]; i++)
                {
                    System.arraycopy(nextAuthPaths[layer - 1][i], 0, currentAuthPaths[layer][i], 0, mdLength);
                    System.arraycopy(nextNextRoot[layer - 1].getAuthPath()[i], 0, nextAuthPaths[layer - 1][i], 0, mdLength);
                }

                for(int i = 0; i < K[layer] - 1; i++)
                {
                    currentRetain[layer][i] = nextRetain[layer - 1][i];
                    nextRetain[layer - 1][i] = nextNextRoot[layer - 1].getRetain()[i];
                }

                currentStack[layer] = nextStack[layer - 1];
                nextStack[layer - 1] = nextNextRoot[layer - 1].getStack();
                nextRoot[layer - 1] = nextNextRoot[layer - 1].getRoot();
                byte OTSseed[] = new byte[mdLength];
                byte dummy[] = new byte[mdLength];
                System.arraycopy(currentSeeds[layer - 1], 0, dummy, 0, mdLength);
                OTSseed = gmssRandom.nextSeed(dummy);
                OTSseed = gmssRandom.nextSeed(dummy);
                OTSseed = gmssRandom.nextSeed(dummy);
                nextRootSig[layer - 1].initSign(OTSseed, nextRoot[layer - 1]);
                nextKey(layer - 1);
            }
        }
    }

    private void updateKey(int layer)
    {
        computeAuthPaths(layer);
        if(layer > 0)
        {
            if(layer > 1)
                nextNextLeaf[layer - 1 - 1] = nextNextLeaf[layer - 1 - 1].nextLeaf();
            upperLeaf[layer - 1] = upperLeaf[layer - 1].nextLeaf();
            int t = (int)Math.floor((double)(getNumLeafs(layer) * 2) / (double)(heightOfTrees[layer - 1] - K[layer - 1]));
            if(index[layer] % t == 1)
            {
                if(index[layer] > 1 && minTreehash[layer - 1] >= 0)
                {
                    byte leaf[] = upperTreehashLeaf[layer - 1].getLeaf();
                    try
                    {
                        currentTreehash[layer - 1][minTreehash[layer - 1]].update(gmssRandom, leaf);
                        if(!currentTreehash[layer - 1][minTreehash[layer - 1]].wasFinished());
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
                minTreehash[layer - 1] = getMinTreehashIndex(layer - 1);
                if(minTreehash[layer - 1] >= 0)
                {
                    byte seed[] = currentTreehash[layer - 1][minTreehash[layer - 1]].getSeedActive();
                    upperTreehashLeaf[layer - 1] = new GMSSLeaf(digestProvider.get(), otsIndex[layer - 1], t, seed);
                    upperTreehashLeaf[layer - 1] = upperTreehashLeaf[layer - 1].nextLeaf();
                }
            } else
            if(minTreehash[layer - 1] >= 0)
                upperTreehashLeaf[layer - 1] = upperTreehashLeaf[layer - 1].nextLeaf();
            nextRootSig[layer - 1].updateSign();
            if(index[layer] == 1)
                nextNextRoot[layer - 1].initialize(new Vector());
            updateNextNextAuthRoot(layer);
        }
    }

    private int getMinTreehashIndex(int layer)
    {
        int minTreehash = -1;
        for(int h = 0; h < heightOfTrees[layer] - K[layer]; h++)
        {
            if(!currentTreehash[layer][h].wasInitialized() || currentTreehash[layer][h].wasFinished())
                continue;
            if(minTreehash == -1)
            {
                minTreehash = h;
                continue;
            }
            if(currentTreehash[layer][h].getLowestNodeHeight() < currentTreehash[layer][minTreehash].getLowestNodeHeight())
                minTreehash = h;
        }

        return minTreehash;
    }

    private void computeAuthPaths(int layer)
    {
        int Phi = index[layer];
        int H = heightOfTrees[layer];
        int K = this.K[layer];
        for(int i = 0; i < H - K; i++)
            currentTreehash[layer][i].updateNextSeed(gmssRandom);

        int Tau = heightOfPhi(Phi);
        byte OTSseed[] = new byte[mdLength];
        OTSseed = gmssRandom.nextSeed(currentSeeds[layer]);
        int L = Phi >>> Tau + 1 & 1;
        byte tempKeep[] = new byte[mdLength];
        if(Tau < H - 1 && L == 0)
            System.arraycopy(currentAuthPaths[layer][Tau], 0, tempKeep, 0, mdLength);
        byte help[] = new byte[mdLength];
        if(Tau == 0)
        {
            if(layer == numLayer - 1)
            {
                WinternitzOTSignature ots = new WinternitzOTSignature(OTSseed, digestProvider.get(), otsIndex[layer]);
                help = ots.getPublicKey();
            } else
            {
                byte dummy[] = new byte[mdLength];
                System.arraycopy(currentSeeds[layer], 0, dummy, 0, mdLength);
                gmssRandom.nextSeed(dummy);
                help = upperLeaf[layer].getLeaf();
                upperLeaf[layer].initLeafCalc(dummy);
            }
            System.arraycopy(help, 0, currentAuthPaths[layer][0], 0, mdLength);
        } else
        {
            byte toBeHashed[] = new byte[mdLength << 1];
            System.arraycopy(currentAuthPaths[layer][Tau - 1], 0, toBeHashed, 0, mdLength);
            System.arraycopy(keep[layer][(int)Math.floor((Tau - 1) / 2)], 0, toBeHashed, mdLength, mdLength);
            messDigestTrees.update(toBeHashed, 0, toBeHashed.length);
            currentAuthPaths[layer][Tau] = new byte[messDigestTrees.getDigestSize()];
            messDigestTrees.doFinal(currentAuthPaths[layer][Tau], 0);
            for(int i = 0; i < Tau; i++)
            {
                if(i < H - K)
                    if(currentTreehash[layer][i].wasFinished())
                    {
                        System.arraycopy(currentTreehash[layer][i].getFirstNode(), 0, currentAuthPaths[layer][i], 0, mdLength);
                        currentTreehash[layer][i].destroy();
                    } else
                    {
                        System.err.println((new StringBuilder()).append("Treehash (").append(layer).append(",").append(i).append(") not finished when needed in AuthPathComputation").toString());
                    }
                if(i < H - 1 && i >= H - K && currentRetain[layer][i - (H - K)].size() > 0)
                {
                    System.arraycopy(currentRetain[layer][i - (H - K)].lastElement(), 0, currentAuthPaths[layer][i], 0, mdLength);
                    currentRetain[layer][i - (H - K)].removeElementAt(currentRetain[layer][i - (H - K)].size() - 1);
                }
                if(i >= H - K)
                    continue;
                int startPoint = Phi + 3 * (1 << i);
                if(startPoint < numLeafs[layer])
                    currentTreehash[layer][i].initialize();
            }

        }
        if(Tau < H - 1 && L == 0)
            System.arraycopy(tempKeep, 0, keep[layer][(int)Math.floor(Tau / 2)], 0, mdLength);
        if(layer == numLayer - 1)
        {
            for(int tmp = 1; tmp <= (H - K) / 2; tmp++)
            {
                int minTreehash = getMinTreehashIndex(layer);
                if(minTreehash >= 0)
                    try
                    {
                        byte seed[] = new byte[mdLength];
                        System.arraycopy(currentTreehash[layer][minTreehash].getSeedActive(), 0, seed, 0, mdLength);
                        byte seed2[] = gmssRandom.nextSeed(seed);
                        WinternitzOTSignature ots = new WinternitzOTSignature(seed2, digestProvider.get(), otsIndex[layer]);
                        byte leaf[] = ots.getPublicKey();
                        currentTreehash[layer][minTreehash].update(gmssRandom, leaf);
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
            }

        } else
        {
            this.minTreehash[layer] = getMinTreehashIndex(layer);
        }
    }

    private int heightOfPhi(int Phi)
    {
        if(Phi == 0)
            return -1;
        int Tau = 0;
        for(int modul = 1; Phi % modul == 0;)
        {
            modul *= 2;
            Tau++;
        }

        return Tau - 1;
    }

    private void updateNextNextAuthRoot(int layer)
    {
        byte OTSseed[] = new byte[mdLength];
        OTSseed = gmssRandom.nextSeed(nextNextSeeds[layer - 1]);
        if(layer == numLayer - 1)
        {
            WinternitzOTSignature ots = new WinternitzOTSignature(OTSseed, digestProvider.get(), otsIndex[layer]);
            nextNextRoot[layer - 1].update(nextNextSeeds[layer - 1], ots.getPublicKey());
        } else
        {
            nextNextRoot[layer - 1].update(nextNextSeeds[layer - 1], nextNextLeaf[layer - 1].getLeaf());
            nextNextLeaf[layer - 1].initLeafCalc(nextNextSeeds[layer - 1]);
        }
    }

    public int[] getIndex()
    {
        return index;
    }

    public int getIndex(int i)
    {
        return index[i];
    }

    public byte[][] getCurrentSeeds()
    {
        return Arrays.clone(currentSeeds);
    }

    public byte[][][] getCurrentAuthPaths()
    {
        return Arrays.clone(currentAuthPaths);
    }

    public byte[] getSubtreeRootSig(int i)
    {
        return currentRootSig[i];
    }

    public GMSSDigestProvider getName()
    {
        return digestProvider;
    }

    public int getNumLeafs(int i)
    {
        return numLeafs[i];
    }

    private int index[];
    private byte currentSeeds[][];
    private byte nextNextSeeds[][];
    private byte currentAuthPaths[][][];
    private byte nextAuthPaths[][][];
    private Treehash currentTreehash[][];
    private Treehash nextTreehash[][];
    private Vector currentStack[];
    private Vector nextStack[];
    private Vector currentRetain[][];
    private Vector nextRetain[][];
    private byte keep[][][];
    private GMSSLeaf nextNextLeaf[];
    private GMSSLeaf upperLeaf[];
    private GMSSLeaf upperTreehashLeaf[];
    private int minTreehash[];
    private GMSSParameters gmssPS;
    private byte nextRoot[][];
    private GMSSRootCalc nextNextRoot[];
    private byte currentRootSig[][];
    private GMSSRootSig nextRootSig[];
    private GMSSDigestProvider digestProvider;
    private boolean used;
    private int heightOfTrees[];
    private int otsIndex[];
    private int K[];
    private int numLayer;
    private Digest messDigestTrees;
    private int mdLength;
    private GMSSRandom gmssRandom;
    private int numLeafs[];
}
