package org.swanix.ds.unionfind;

public class QuickFind {

  private int[] objectArray;

  public QuickFind(int noOfObjects) {
    objectArray = new int[noOfObjects];
    for (int i = 0; i < noOfObjects; i++) {
      objectArray[i] = i;
    }
  }

  public boolean isConnected(int indexOfFirstObject, int indexOfSecondObject) {
    return objectArray[indexOfFirstObject] == objectArray[indexOfSecondObject];
  }

  /*
  When two objects needs to be connected, we basically need to connect their connected components
  So we change the id entries of first object to the id entry of the second object,
  to make them part of the same component represented by the id of second object
   */
  public void union(int indexOfFirstObject, int indexOfSecondObject) {
    int idStoredAtFirstObject = objectArray[indexOfFirstObject];
    int idStoredAtSecondObject = objectArray[indexOfSecondObject];

    for (int i = 0; i < objectArray.length; i++) {
      if (objectArray[i] == idStoredAtFirstObject) {
        objectArray[i] = idStoredAtSecondObject;
      }
    }
  }

}
