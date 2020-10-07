#### LocalDate
    An instance of LocalDate is an immutable object representing just a plain date and without the time of day.
    It does not contain any information about the time zone.
    
    LocalDate today = LocalDate.now()               //Current Date
    LocalDate date = LocalDate.parse("2014-03-18"); //2014-03-18
    
    LocalDate date = LocalDate.of(2014, 3, 18);     // 2014-03-18
    int year = date.getYear();                      //2014
    Month month = date.getMonth();                  //MARCH
    int day = date.getDayOfMonth();                 //18
    DayOfWeek dow = date.getDayOfWeek();            //TUESDAY
    int len = date.lengthOfMonth();                 //31 (days in March)
    boolean leap = date.isLeapYear();               //false
    
    We can also same information by passing a TemporalField to the get method.
    
    int year = date.get(ChronoField.YEAR);
    int month = date.get(ChronoField.MONTH_OF_YEAR);
    int day = date.get(ChronoField.DAY_OF_MONTH);
    
##### LocalTime
    Time is represented by LocalTime class.
    It has two static factory methods named of. First one accepts an hour and a minute and the second one also accepts a second.
    
    LocalTime time = LocalTime.parse("13:45:20");   //13:45:20
    LocalTime time = LocalTime.of(13,45,20);        //13:45:20
    int hour = time.getHour();                      //13
    int minute = time.getMinute();                  //45
    int second = time.getSecond();                  //20
    
##### LocalDateTime
    The composite class called LocalDateTime pairs a LocalDate and LocalTime. It represents both Date and Time, without a time zone
    and can be created either directly or by combining a date and time.
    
    // 2014-03-18T13:45:20
    LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
    LocalDateTime dt2 = LocalDateTime.of(date, time);
    LocalDateTime dt3 = date.atTime(13, 45, 20);
    LocalDateTime dt4 = date.atTime(time);
    LocalDateTime dt5 = time.atDate(date);
    
    LocalDate date = dt1.toLocalDate();
    LocalTime time = dt2.toLocalTime();
    
##### Instant 
    Instant class basically represents the number of seconds passed since the Unix Epoch time, set by convention to Midnight of January 1, 1970 UTC.
    
    Instant.now();
    Instant.ofEpochSecond(3);
    Instant.ofEpochSecond(3, 0);
    Instant.ofEpochSecond(2, 1_000_000_000);        // One billion nanoseconds (1 second) after 2 seconds
    Instant.ofEpochSecond(4, -1_000_000_000);       // One billion nanoseconds (1 second) before 4 seconds
    
    Instance is intended for use only by a machine. As a consequence, it doesn't provide any ability to handle units of time that are meaningful for humans.
    
##### Duration
    Duration class can be used to create a duration between two temporal objects.
    
    Duration d1 = Duration.between(time1, time2);
    Duration d1 = Duration.between(dateTime1, dateTime2);
    Duration d2 = Duration.between(instant1, instant2); 
    
    Duration threeMinutes = Duration.ofMinutes(3);
    Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
    
    Because the Duration class is used to represent an amount of time measured in seconds and eventually nanoseconds, you can't pass a LocalDate to the between method.

##### Period
    When we need to model time in terms of years, months and days, you can use Period class. 
    
    Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
    
    Period tenDays = Period.ofDays(10);
    Period threeWeeks = Period.ofWeeks(3);
    Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
    
#### Common Methods of date-time classes representing an Interval    
    Method              Static      Description      
    
    between             Yes         Creates an interval between two points in time
    from                Yes         Creates an interval from a temporal unit
    of                  Yes         Creates an instance of this interval from its constituent parts
    parse               Yes         Creates an instance of this interval from a String
    addTo               No          Creates a copy of this interval adding to it the specified temporal object
    get                 No          Reads part of the state of this interval
    isNegative          No          Checks if this interval is negative, excluding zero
    isZero              No          Checks if this interval is zero length
    minus               No          Creates a copy of this interval with an amount of time subtracted
    multipliedBy        No          Creates a copy of this interval multiplied by the given scalar                                       
    negated             No          Creates a copy of this interval with the length negated
    plus                No          Creates a copy of this interval with an amount of time added
    subtractFrom        No          Subtracts this interval from the specified temporal object    
    
##### Manipulating, Parsing and Formatting Dates
    Easiest way to create a modified version of an existing LocalDate is changing one of its attributes, using one of its withAttribute methods
    
    LocaDate date1 = LocalDate.of(2014, 3, 18);                     //2014-03-18
    LocalDate date2 = date1.withYear(2011);                         //2011-03-18
    LocalDate date3 = date2.withDayOfMonth(25)                      //2011-03-25
    
    LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);     //2011-09-25
    
    It is even possible to manipulate a LocalDate in a declarative manner.
    
    LocalDate date1 = LocalDate.of(2014, 3, 18);        //2014-03-18
    LocalDate date2 = date1.plusWeeks(1);               //2014-03-25
    LocalDate date3 = date2.minusYears(3);              //2011-03-25
    LocalDAte date4 = date3.plus(6, ChronoUnit.MONTHS); //2011-09-25
    
##### Common methods of date-time classes representing a point in time
    Method      Static      Description
    
    from        Yes         Creates an instance of this class from the passed temporal object
    now         Yes         Creates a temporal object from the system clock
    of          Yes         Creates an instance of this temporal object from its constituent parts
    parse       Yes         Creates an instance of this temporal object from a String
    atOffset    No          Combines this temporal object with a zone offset
    atZone      No          Combines this temporal object with a time zone
    format      No          Converts this temporal object into a String using the specified formatter (not available for Instant)
    get         No          Reads part of the state of this temporal object
    minus       No          Creates a copy of this temporal object with an amount of time subtracted
    plus        No          Creates a copy of this temporal object with an amount of time added
    with        No          Creates a copy of this temporal object with part of the state changed
    
    LocalDate date = LocalDate.of(2014, 3, 18);
    date = date.with(ChronoField.MONTH_OF_YEAR, 9);
    date = date.plusYears(2).minusDays(10);
    date.withYear(2011);
    
#####Temporal Adjusters
    Temporal Adjusters allow you to perform more complex date manipulations that still read like problem statement. 
    It's relatively simple to create your own custom Temporal Adjuster implementation if you can't find a predefined TemporalAdjuster that fits your need.
    
    @FunctionalInterface
    public interface TemporalAdjuster {    
            Temporal adjustInto(Temporal temporal);
    }
    
    This means an implementation of the TemporalAdjuster interface defines how to convert a Temporal object into another Temporal. 
    You can think of it as being like a UnaryOperator<Temporal>.
    
    If you want to define the TemporalAdjuster with a lambda expression, it’s preferable to do it using the ofDateAdjuster static factory of the TemporalAdjusters class that accepts a UnaryOperator<LocalDate>.
    
    TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
        temporal -> {        
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));        
            int dayToAdd = 1;        
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;        
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;        
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);    
        });
        
        date = date.with(nextWorkingDay);
    
#####Factory Methods of the TemporalAdjusters class    
    Method                          Description
                                    
    dayOfWeekInMonth                Creates a new date in the same month with the ordinal day of week
    firstDayOfMonth                 Creates a new date set to the first day of the current month
    firstDayOfNextMonth             Creates a new date set to the first day of the next month
    firstDayOfNextYear              Creates a new date set to the first day of the next year
    firstDayOfYear                  Creates a new date set to the first day of the current year
    firstInMonth                    Creates a new date in the same month with the first matching day of week
    lastDayOfMonth                  Creates a new date set to the last day of the current month
    lastDayOfNextMonth              Creates a new date set to the last day of the next month
    lastDayOfNextYear               Creates a new date set to the last day of the next year
    lastDayOfYear                   Creates a new date set to the last day of the current year
    lastInMonth                     Creates a new date in the same month with the last matching day of week
    next previous                   Creates a new date set to the first occurrence of the specified day of week after/before the date being adjusted                                       
    nextOrSame previousOrSame       Creates a new date set to the first occurrence of the specified day of week after/before the date being adjusted unless 
                                    it’s already on that day, in which case the same object is returned
                                    
##### DateTimeFormatters
    DateTimeFormatters can be used to create a String representing a given date or time in a specific format.
    
    LocalDate date = LocalDate.of(2014, 3, 18);
    String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);      //20140318
    String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);      //2014-03-18
    
    LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
    LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);
    
    DateTimeFormatter class also supports a static factory method that lets you create a formatter from a specific pattern
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date1 = LocalDate.of(2014, 3, 18);
    String formattedDate = date1.format(formatter);
    LocalDate date2 = LocalDate.parse(formattedDate, formatter);
    
    The ofPattern method also has an overloaded version allowing you to create a formatter for a given Locale.
    
    DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
    LocalDate date1 = LocalDate.of(2014, 3, 18);
    String formattedDate = date.format(italianFormatter);                   // 18. marzo 2014
    LocalDate date2 = LocalDate.parse(formattedDate, italianFormatter);
    
    In case you need even more control, the DateTimeFormatterBuilder class lets you define complex formatters step by step using meaningful methods.
    
    DateTimeFormatter italianFormatter = new DateTimeFormatterBuilder()        
            .appendText(ChronoField.DAY_OF_MONTH)        
            .appendLiteral(". ")        
            .appendText(ChronoField.MONTH_OF_YEAR)        
            .appendLiteral(" ")        
            .appendText(ChronoField.YEAR)        
            .parseCaseInsensitive()        
            .toFormatter(Locale.ITALIAN);
            
##### Time Zone
    A time zone is aset of rules corresponding to a region in which the standard time is the same.
    There are about 40 of them held in instances of the ZoneRule class. You can simply calls getRules() on a ZoneId to obtain the rules for that given zone.            
    
    The region IDs are all in the format “{area}/{city}”.
    
    ZoneId romeZone = ZoneId.of("Europe/Rome");

    Once you have a ZoneId object, you can combine it with a LocalDate, a LocalDateTime, or an Instant, to transform it into ZonedDateTime instances, which represent points in time relative to the specified time zone.
    
    ZoneId zoneId = TimeZone.getDefault().toZoneId();
    
    LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
    ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    ZonedDateTime zdt2 = dateTime.atZone(romeZone);
    Instant instant = Instant.now();
    ZonedDateTime zdt3 = instant.atZone(romeZone);
    
    You can also convert a LocalDateTime to an Instant by using a ZoneId:            
    
    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    Instant instantFromDateTime = dateTime.toInstant(romeZone);            
    
    Or you can do it the other way around:                  
    
    Instant instant = Instant.now();
    LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
    
    Fixed Offset:
    
    ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
    LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
    OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(date, newYorkOffset);


















    