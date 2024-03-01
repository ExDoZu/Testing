package com.mycompany.lab1.task2;

import java.lang.NullPointerException;

class HashNode<K, V> {
    K key;
    V value;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

public class ClosedHash<K, V> {

    public enum Methods {
        LINEAR,
        QUADRATIC,
        DOUBLE_HASHING
    }

    private int hash2(K key) {
        return 7 - keyHashCode(key) % 7;
    }

    private HashNode<K, V>[] bucketArray;

    private int numbucks;

    private Methods method;

    public int getMaxSize() {
        return numbucks;
    }

    @SuppressWarnings("unchecked")
    public ClosedHash(Methods method) {
        this.method = method;
        numbucks = 29;
        bucketArray = new HashNode[numbucks];

        for (int i = 0; i < numbucks; i++)
            bucketArray[i] = null;
    }

    private int keyHashCode(K key) throws NullPointerException {
        if (key == null)
            throw new NullPointerException("Null key");
        return key.hashCode() < 0 ? -key.hashCode() : key.hashCode();
    }

    private boolean tryToRemove(int index, K key) {
        if (bucketArray[index] != null && bucketArray[index].key.equals(key)) {
            bucketArray[index] = null;
            return true;
        }
        return false;
    }

    public void remove(K key) {
        int index = keyHashCode(key) % numbucks;
        final int FIRST_INDEX = index;
        int missedCounter = 0;

        if (tryToRemove(index, key)) {
            return;
        }
        missedCounter++;
        index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        while (index != FIRST_INDEX) {
            if (tryToRemove(index, key)) {
                return;
            }
            missedCounter++;
            index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        }

    }

    public V get(K key) {
        int index = keyHashCode(key) % numbucks;
        final int FIRST_INDEX = index;
        int missedCounter = 0;

        if (bucketArray[index] != null && bucketArray[index].key.equals(key)) {
            return bucketArray[index].value;
        }
        missedCounter++;
        index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        while (index != FIRST_INDEX) {
            if (bucketArray[index] != null && bucketArray[index].key.equals(key)) {
                return bucketArray[index].value;
            }
            missedCounter++;
            index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        }
        return null;

    }

    private int increaseIndex(final int FIRST_INDEX, int index, K key, int missedCounter) {
        switch (method) {
            case QUADRATIC:
                return (FIRST_INDEX + missedCounter * missedCounter) % numbucks;
            case DOUBLE_HASHING:
                return (index + hash2(key)) % numbucks;
            default:
                return (index + 1) % numbucks;

        }

    }

    private boolean tryToAdd(int index, K key, V value) {
        if (bucketArray[index] == null) {
            bucketArray[index] = new HashNode<>(key, value);
            return true;
        }
        return false;
    }

    public void add(K key, V value) {
        int index = keyHashCode(key) % numbucks;
        final int FIRST_INDEX = index;
        int missedCounter = 0;
        if (tryToAdd(index, key, value)) {
            return;
        }
        missedCounter++;
        index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        while (index != FIRST_INDEX) {
            if (tryToAdd(index, key, value)) {
                return;
            }
            missedCounter++;
            index = increaseIndex(FIRST_INDEX, index, key, missedCounter);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < numbucks - 1; i++) {
            if (bucketArray[i] == null) {
                sb.append("null");
            } else {
                sb.append(bucketArray[i].key);
                sb.append(":");
                sb.append(bucketArray[i].value);
            }

            sb.append(", ");
        }
        if (bucketArray[numbucks - 1] == null) {
            sb.append("null");
        } else {
            sb.append(bucketArray[numbucks - 1].key);
            sb.append(":");
            sb.append(bucketArray[numbucks - 1].value);
        }
        sb.append("}");
        return sb.toString();
    }
}
