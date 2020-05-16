package org.swanix.ds.unionfind;

public class QuickUnion {

  private int[] objectArray;

  public QuickUnion(int noOfObjects) {
    objectArray = new int[noOfObjects];
    for (int i = 0; i < noOfObjects; i++) {
      objectArray[i] = i;
    }
  }

  private int getRoot(int idOfObject) {
    while (idOfObject != objectArray[idOfObject]) {
      idOfObject = objectArray[idOfObject];
    }
    return idOfObject;
  }

  public boolean isConnected(int idOfFirstObject, int idOfSecondObject) {
    return getRoot(idOfFirstObject) == getRoot(idOfSecondObject);
  }

  /*
  Object part of one tree is part of one connected component
  When we want to join two components, we change the root of one to the root of another
  making their root same and as a result part of same connected component
   */
  public void union(int idOfFirstObject, int idOfSecondObject) {
    int rootOfFirstObject = getRoot(idOfFirstObject);
    int rootOfSecondObject = getRoot(idOfSecondObject);
    objectArray[rootOfFirstObject] = rootOfSecondObject;
  }

}
