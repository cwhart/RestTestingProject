package com.sg.superhero.util;

public class PagingUtils {

    public static int[] getPageNumbers(Integer numberOfPages, Integer selectedPage) {
        int[] pageNumbers = new int[numberOfPages];

        for (int i = 0; i < 5; i++) {
            pageNumbers[i] = selectedPage + i;
        }

        return pageNumbers;
    }

    public static int getSelectedPage(Integer offset, Integer limit) {
        return offset/limit + 1;
    }
}

