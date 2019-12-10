/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int year;
  private int month;
  private int day;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    
    boolean validDate;

    validDate = isValidDate(month, day, year);
    if (validDate){
      this.year = year;
      this.month = month;
      this.day = day;
      System.out.println("This is a valid date, and date has been constructed.");
    } else{
      System.out.println("This is not an valid date. Program exits.");
      System.exit(0);
    }
    
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {

    String[] splitedString;  // splitedString[0]: month; splitedString[1]: day; splitedString[2]: year.
    int parsedMonth = 0, parsedDay = 0, parsedYear = 0;
    boolean validDate;

    splitedString = s.split("/", 3);

    try {
      parsedMonth = Integer.parseInt(splitedString[0]);
      parsedDay = Integer.parseInt(splitedString[1]);
      parsedYear = Integer.parseInt(splitedString[2]);
    }
    catch (NumberFormatException e)
    {
       System.out.println("Incorrect date! Program exits.");
    }

    validDate = isValidDate(parsedMonth, parsedDay, parsedYear);

    if (validDate){
      year = parsedYear;
      month = parsedMonth;
      day = parsedDay;
      System.out.println("This is a valid date, and date has been constructed.");
    } else{
      System.out.println("This is not an valid date. Program exits.");
      System.exit(0);
    }


  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year){
    if (year % 400 == 0){
      return true;
    } else if (year % 4 == 0 && year % 100 != 0){
      return true;
    } else return false;                        // replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    
    boolean leapYear = isLeapYear(year);

    if (leapYear){
      switch(month){
        case 2:
          return 29;
        case 4:
        case 6:
        case 9:
        case 11:
          return 30;
        default:
          return 31;
      }
    } else{
      switch(month){
        case 2:
          return 28;
        case 4:
        case 6:
        case 9:
        case 11:
          return 30;
        default:
          return 31;
      }
    }                         
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    
    boolean leapYear = isLeapYear(year);
    int days = daysInMonth(month, year);

    if (year < 0){
      return false;
    } else{
      if (month >= 1 && month <= 12 && (day >= 1 && day <= days)){
        return true;
      } else {
        return false;
      }
    }
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    
    String dateString;

    dateString = month + "/" + day + "/" + year;

    return dateString;
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
    
    if (year < d.year) {
      return true;
    } else if (year == d.year) {
      if (month < d.month) {
        return true;
      } else if (month == d.month) {
        if (day < d.day) {
          return true;
        } else {return false;}
      } else {return false;}
    } else {return false;}
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    if (year > d.year) {
      return true;
    } else if (year == d.year) {
      if (month > d.month) {
        return true;
      } else if (month == d.month) {
        if (day > d.day) {
          return true;
        } else {return false;}
      } else {return false;}
    } else {return false;}
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    
    int daysInYear;       // Declare the variables
    boolean leapYear;

    daysInYear = 0;       // Also need to initialize the variables!
    leapYear = isLeapYear(year);

    if (leapYear) {
      switch(month){
        case 1:
          daysInYear = day;
          break;
        case 2:
          daysInYear = 31 + day;
          break;
        case 3:
          daysInYear = 31 + 29 + day;
          break;
        case 4:
          daysInYear = 31 + 29 + 31 + day;
          break;
        case 5:
          daysInYear = 31 + 29 + 31 + 30 + day;
          break;
        case 6:
          daysInYear = 31 + 29 + 31 + 30 + 31 + day;
          break;
        case 7:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + day;
          break;
        case 8:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + 31 + day;
          break;
        case 9:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + day;
          break;
        case 10:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + day;
          break;
        case 11:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + day;
          break;
        case 12:
          daysInYear = 31 + 29 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + day;
          break;
      }
    } else {
      switch(month){
        case 1:
          daysInYear = day;
          break;
        case 2:
          daysInYear = 31 + day;
          break;
        case 3:
          daysInYear = 31 + 28 + day;
          break;
        case 4:
          daysInYear = 31 + 28 + 31 + day;
          break;
        case 5:
          daysInYear = 31 + 28 + 31 + 30 + day;
          break;
        case 6:
          daysInYear = 31 + 28 + 31 + 30 + 31 + day;
          break;
        case 7:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + day;
          break;
        case 8:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + 31 + day;
          break;
        case 9:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + day;
          break;
        case 10:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + day;
          break;
        case 11:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + day;
          break;
        case 12:
          daysInYear = 31 + 28 + 31 + 30 + 31 + 30 + 31 + 31 + 30 + 31 + 30 + day;
          break;
      }
    }
    return daysInYear;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
    
    int numberOfDaysFrom;
    int numberOfDaysTo;

    numberOfDaysFrom = d.dayInYear();
    numberOfDaysTo = this.dayInYear();

    return numberOfDaysTo - numberOfDaysFrom;

  }

  public static void main(String[] argv) {
    /*
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    
    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("4/31/2110");
    */

    /* I recommend you write code to test the isLeapYear function! */
    /* Test isLeapYear and daysInMonth functions */
    /*
    System.out.println("Year 1800 is leap year is " + isLeapYear(1800) + ", and its May has " + daysInMonth(5, 1800) + "Days");
    System.out.println("Year 1900 is leap year is " + isLeapYear(1900) + ", and its Feb has " + daysInMonth(2, 1900) + "Days");
    System.out.println("Year 2000 is leap year is " + isLeapYear(2000) + ", and its Feb has " + daysInMonth(2, 2000) + "Days");
    System.out.println("Year 2004 is leap year is " + isLeapYear(2004) + ", and its Jan has " + daysInMonth(1, 2004) + "Days");
    */

    /* Test if a given date is valid */
    /*
    Date date1 = new Date(13,1,2017);
    System.out.println("13/01/2017 is a valid date is " + isValidDate(13,1,2017));
    */
    
    Date date2 = new Date(2,29,2004);
    Date date4 = new Date(2,28,2004);
    Date date5 = new Date(3,1,2004);
    Date date6 = new Date(7,31,2014);
    Date date7 = new Date("7/31/2014");
    
    /* Test difference() function */
    /*
    System.out.println("The difference in day between " + date2 + " and " + date4 + " is " + date2.difference(date4));
    System.out.println("The difference in day between " + date5 + " and " + date4 + " is " + date5.difference(date4));
    System.out.println("The difference in day between " + date4 + " and " + date5 + " is " + date4.difference(date5));
    System.out.println("The difference in day between " + date6 + " and " + date7 + " is " + date6.difference(date7));
    */
    

    /* Test dayInYear() function */
    /*
    System.out.println("The number of days of '02/29/2004' is " + date2.dayInYear());
    System.out.println("The number of days of '03/01/2004' is " + date5.dayInYear());
    */
    /* Test isBefore/isAfter function */
    
    System.out.println("02/29/2004 is a valid date is " + isValidDate(2,29,2004));
    System.out.println(date2.toString());
    System.out.println("The statement that '02/29/2004 is before 02/28/2004 is true' is " + date2.isBefore(date4));
    System.out.println("The statement that '02/29/2004 is before 03/01/2004 is true' is " + date2.isBefore(date5));
    System.out.println("The statement that '03/01/2004 is after 02/29/2004 is true' is " + date5.isAfter(date2));
    System.out.println("The statement that '03/01/2004 is after 07/31/2014 is true' is " + date5.isAfter(date6));
    
    /*
    Date date3 = new Date(2,29,2003);
    System.out.println("02/29/2003 is a valid date is " + isValidDate(2,29,2003));
    System.out.println("12/45/1 is a valid date is " + isValidDate(12,45,1));
    System.out.println("13/01/-1000 is a valid date is " + isValidDate(13,1,-1000));
    */



    /*
    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
    */
  }
}
