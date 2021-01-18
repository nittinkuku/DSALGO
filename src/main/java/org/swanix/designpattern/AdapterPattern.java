package org.swanix.designpattern;

public class AdapterPattern {
    private PrintNumber printNumber;

    AdapterPattern(PrintNumber printNumber) {
        this.printNumber = printNumber;
    }

    public void callMethod() {
        printNumber.printNumberInWords();
    }
}


interface PrintNumber {
    void printNumberInWords();
}

class PrintNumberImpl implements PrintNumber {
    public void printNumberInWords() {

    }
}

interface PrintNumberWithUnderscore {
    void printNumberInWordsWithUnderscore();
}

class PrintNumberWithUnderscoreImpl implements PrintNumberWithUnderscore {
    public void printNumberInWordsWithUnderscore() {

    }
}

// Object
class PrintNumberWithUnderscoreAdapterObj implements PrintNumber {
    PrintNumberWithUnderscore printNumberWithUnderscore = new PrintNumberWithUnderscoreImpl();

    public void printNumberInWords() {
        printNumberWithUnderscore.printNumberInWordsWithUnderscore();
    }
}

//Class
class PrintNumberWithUnderscoreAdapterClass extends PrintNumberWithUnderscoreImpl implements PrintNumber {
    public void printNumberInWords() {
        printNumberInWordsWithUnderscore();
    }
}