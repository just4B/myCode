
package com.util.parser;

import java.util.ArrayList;
import java.util.LinkedList;


class NumberParser {
    
    int parseRomaFormat (String romanFormat) throws RomanNumberFormatException{
        char[] roman = romanFormat.toCharArray();
        ArrayList<Integer> arabicNumbers = new ArrayList<Integer>();
        
        int stringLength = roman.length;
        int iterate = 0;
        while (iterate < stringLength) {
               int inRow = 1;
               int maxInRow = RomanNumRules.rightRules.get(roman[iterate]);
               Integer curretValue = RomanNumRules.decValues.get(roman[iterate]);
               if (curretValue == null) {throw new RomanNumberFormatException("Roman number wrongly formated");}
               while (iterate + inRow < stringLength) {
                      Integer nextValue = RomanNumRules.decValues.get(roman[iterate + inRow]);
                      if (nextValue == null) {throw new RomanNumberFormatException("Roman number wrongly formated");}
                      if (nextValue >  curretValue || nextValue < curretValue) {
                          break;
                      } else if (nextValue == curretValue && inRow >= maxInRow) {
                          throw new RomanNumberFormatException("Roman number wrongly formated");
                      }
                      inRow++;
               }
               arabicNumbers.add(curretValue * inRow);
               iterate += inRow;
        }
        iterate = 0;
        int listLength = arabicNumbers.size();
        ArrayList<Integer> recountedList = new ArrayList<Integer>();
        int tmpVal = 0;
        int skip = 1;
        int borderValue = 0;
        while (iterate < listLength) {
               skip = 1;
               int currentValue = arabicNumbers.get(iterate);
               if (iterate < (listLength - 1)) {
                   int nextValue = arabicNumbers.get(iterate + 1);
                   if (nextValue > currentValue) {
                       tmpVal = nextValue - currentValue;
                       tmpVal = (int)( ( ( (float)tmpVal) / (float)nextValue) * 10);
                       if (tmpVal == 8 || tmpVal == 9) {
                           currentValue = nextValue - currentValue;
                           skip = skip + 1;
                       } else {
                           throw new RomanNumberFormatException("Roman number wrongly formated");
                       }
                   }
               }
               recountedList.add(currentValue);
               iterate = iterate + skip;
        }
        int result = 0;
        listLength = recountedList.size();
        for (int i = 0; i < listLength; i++) {
             if (i < (listLength - 1) && recountedList.get(i) <= recountedList.get(i + 1)) {
                 throw new RomanNumberFormatException("Roman number wrongly formated");
             }
             result = result + recountedList.get(i);
        }
       
        return result;
    }
    
    String parseArabicFormat (int value) {
        LinkedList<Integer> countedValues = new LinkedList<Integer>();
        int tmp = value;
        for (Integer row : RomanNumRules.relations) {
             int iterate = 0;
             while (tmp > 0) {
                    tmp = tmp - row;
                    if (tmp < 0) {
                        tmp = tmp + row;
                        break;
                    }
                    countedValues.add(row);
                    iterate++;
             }
        } 
        
        LinkedList<Integer> afterFormat = new LinkedList<Integer>();
        int rule = RomanNumRules.rightRules.get('M');
        int listSize = countedValues.size();
        int previousValue = RomanNumRules.decValues.get('M');
        int currentValue = 0;
        int inRow = 1;
        int postfixValue = 0;
        for (int i = 0; i < listSize; i = i + inRow) {
             inRow = 1;
             currentValue = countedValues.get(i);
             if (currentValue != previousValue) {
                 previousValue = currentValue;
                 rule = RomanNumRules.rightRules.get(RomanNumRules.romanNum.get(currentValue));
             }    
             if ((i < (listSize - 1) && currentValue > countedValues.get(i + inRow)) || i == (listSize - 1)) {
                 afterFormat.add(currentValue);
                 continue;
             } 
             while (i + inRow <= listSize) {
                    if (inRow > rule) {
                        postfixValue = afterFormat.get(afterFormat.size() - 1);
                        
                        if (postfixValue == (currentValue * 5)) {
                            postfixValue = currentValue * 10;
                            afterFormat.remove(afterFormat.size() - 1);
                        } else {
                            postfixValue = currentValue * 5;
                        }
                        afterFormat.add(currentValue);
                        afterFormat.add(postfixValue);
                        break;
                    }

                    if (i + inRow >= listSize || countedValues.get(i) > countedValues.get(i + inRow)) {
                        for (int j = 0; j < inRow; j++) {
                             afterFormat.add(currentValue);
                        }
                        break;
                    }
                    
                    inRow++;
             }
             
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (Integer single : afterFormat) {
             resultBuilder.append(RomanNumRules.romanNum.get(single));
        }
        return resultBuilder.toString();
    }
    
    
    
    
    
    
    
}
