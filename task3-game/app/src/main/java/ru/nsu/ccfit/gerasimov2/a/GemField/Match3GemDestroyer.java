package ru.nsu.ccfit.gerasimov2.a.GemField;

public class Match3GemDestroyer implements GemDestroyer{

    int countSame(int row, int col, GemField gemField) {
        Gem base = gemField.at(row, col);
        int cnt = 1;

        for (int i = col + 1;  i < gemField.cols; i++) {
            Gem curr = gemField.at(row, i);
            if (curr.color != base.color) break;
            cnt++;
        }
        return cnt;
    }

    void breakGemsOnLine(int row, int col, int count, GemField gemField) {
    int sz = Math.min(col + count, gemField.cols);
        for (int i = col; i < sz; i++) {
            Gem gem = gemField.at(row, i);
            gem.destroy();
        }
    }

    static void printField(GemField gf) {
        for (int i = 0; i < gf.rows; i++) {
            for (int j = 0; j < gf.cols; j++) {
                Gem gem = gf.at(i, j);
                String gemView = gem.isDestroyed() ? "x" : Integer.valueOf(gem.color).toString();
                System.out.print(gemView + " ");
            }
            System.out.println();
        }
    }


    void destroyGemsReal(GemField gemField) {
        for (int i = 0; i < gemField.rows; i++) {
            for (int j = 0; j < gemField.cols; j++) {
                int cnt = countSame(i, j, gemField);
                if (cnt >= 3)  {
                    breakGemsOnLine(i, j, cnt, gemField);
                }
                j += (cnt - 1);
            }
        }
    }

    @Override
    public void destroyGems(GemField gemField) {
        destroyGemsReal(gemField);
        gemField.transposeIndexes();
        destroyGemsReal(gemField);
        gemField.transposeIndexes();
    }

    boolean canDestroyReal(GemField gemField) {
        for (int i = 0; i < gemField.rows; i++) {
            for (int j = 0; j < gemField.cols; j++) {
                int cnt = countSame(i, j, gemField);
                if (cnt >= 3)  {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canDestroy(GemField gemField) {
        boolean result = canDestroyReal(gemField);
        if (result) return true;

        gemField.transposeIndexes();
        canDestroyReal(gemField);
        gemField.transposeIndexes();
        return result;
    }

}
