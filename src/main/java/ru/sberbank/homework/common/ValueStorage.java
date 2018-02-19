package ru.sberbank.homework.common;

public class ValueStorage {
    private Double result;
    private boolean runAtFirstTime = true;

   public Double getResult () {
       return result;
   }

   public void setResult (Double result) {
       this.result=result;
   }

   public void setRunAtFirstTime(boolean runAtFirstTime) {
       this.runAtFirstTime = runAtFirstTime;
   }

    public boolean getRunAtFirstTime() {
        return runAtFirstTime;
    }
}

