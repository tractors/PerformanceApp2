package com.will.performanceapp2.bean;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Objects;

public class Pixabay {
    private int totalHits;
    private PhotoItem[] hits;
    private int total;

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public PhotoItem[] getHits() {
        return hits;
    }

    public void setHits(PhotoItem[] hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixabay pixabay = (Pixabay) o;
        return totalHits == pixabay.totalHits &&
                total == pixabay.total &&
                Arrays.equals(hits, pixabay.hits);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        int result = Objects.hash(totalHits, total);
        result = 31 * result + Arrays.hashCode(hits);
        return result;
    }
}

