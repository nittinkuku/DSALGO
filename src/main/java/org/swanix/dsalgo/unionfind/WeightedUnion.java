package org.swanix.dsalgo.unionfind;

public class WeightedUnion {

  private int[] objectArray;
  private int[] sizeArray;

  public WeightedUnion(int noOfObjects) {
    objectArray = new int[noOfObjects];
    sizeArray = new int[noOfObjects];
    for (int i = 0; i < noOfObjects; i++) {
      objectArray[i] = i;
      sizeArray[i] = 1;
    }
  }

  private int getRoot(int idOfObject) {
    while (idOfObject != objectArray[idOfObject]) {
      /*
        Improvement to the Algorithm:
        Path Compression:
        while searching for the root of a tree, make it point to its grandparent
        with just one extra line
        and it will flatten the tree
      */
      objectArray[idOfObject] = objectArray[objectArray[idOfObject]];
      idOfObject = objectArray[idOfObject];
    }
    return idOfObject;
  }

  public boolean isConnected(int idOfFirstObject, int idOfSecondObject) {
    return getRoot(idOfFirstObject) == getRoot(idOfSecondObject);
  }

  /*
  Similar to QuickUnion, but put smaller tree in bigger tree
  need extra Array (size Array) to contain size of the tree
   */
  public void union(int idOfFirstObject, int idOfSecondObject) {
    int rootOfFirstObject = getRoot(idOfFirstObject);
    int rootOfSecondObject = getRoot(idOfSecondObject);

    if (rootOfFirstObject == rootOfSecondObject) {
      return;
    }

    if (sizeArray[rootOfFirstObject] < sizeArray[rootOfSecondObject]) {
      objectArray[rootOfFirstObject] = rootOfSecondObject;
      sizeArray[rootOfSecondObject] = sizeArray[rootOfSecondObject] + sizeArray[rootOfFirstObject];
    } else {
      objectArray[rootOfSecondObject] = rootOfFirstObject;
      sizeArray[rootOfFirstObject] = sizeArray[rootOfFirstObject] + sizeArray[rootOfSecondObject];
    }
  }
}
