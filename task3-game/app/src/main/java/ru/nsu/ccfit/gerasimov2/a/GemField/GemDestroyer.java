package ru.nsu.ccfit.gerasimov2.a.GemField;

public interface GemDestroyer {

    /**
     * Destroy gem based on gem type (match3, or destroy all gems on field with the same color?);
     * @param gemField
     */
    public void destroyGems(GemField gemField);
    public boolean canDestroy(GemField gemField);
} 
