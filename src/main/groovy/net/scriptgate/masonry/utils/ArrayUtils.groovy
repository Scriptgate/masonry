package net.scriptgate.masonry.utils

class ArrayUtils {

    static int indexOf(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i
            }
        }
        return -1
    }

}
