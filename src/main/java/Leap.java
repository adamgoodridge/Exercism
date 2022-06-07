class Leap {

    boolean isLeapYear(int year) {
		if(year % 4 == 0)
			return (year % 100 == 0 && year % 400 !=0)? false: true;
		else 
			return false;
     }

}
