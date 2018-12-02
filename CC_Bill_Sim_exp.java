/*

                                                A

                              .o                 .     .oooo.
                            o888               .o8   .dP""Y88b
                  .ooooo oo  888  oo.ooooo.  .o888oo       ]8P' oooo d8b oooo    ooo
                 d88' `888   888   888' `88b   888       .d8P'  `888""8P  `88b..8P'
                 888   888   888   888   888   888     .dP'      888        Y888'
                 888   888   888   888   888   888 . .oP     .o  888      .o8"'88b
                 `V8bod888  o888o  888bod8P'   "888" 8888888888 d888b    o88'   888o
                       888.        888
                       8P'        o888o
                       "            `'"

                              .oooo.   oooo    .oooo.             .ooooo.
                            .dP""Y88b  `888   d8P'`Y8b           d88'   `8.
                oooo    ooo       ]8P'  888  888    888 oooo d8b Y88..  .8' oooo    ooo
                 `88.  .8'      .d8P'   888  888    888 `888""8P  `88888b.   `88.  .8'
                  `88..8'     .dP'      888  888    888  888     .8'  ``88b   `88..8'
                   `888'    .oP     .o  888  `88b  d88'  888     `8.   .88P    `888'
                    `8'     8888888888 o888o  `Y8bd8P'  d888b     `boood8'      .8'
                                                                            .o..P'
                                                                            `Y8P'


                                            Production


                             .:""*~-----==~~,,--**--,,~~==-----~*"":.
                            |q|                                    |j|
                            |1|             Written By:            |y|
                            |p|           q1pt2rx v2l0r8y          |2|
                            |t|                                    |i|
                            |2|            Dedicated To:           |x|
                            |r|           jy2ixn0 f7ux3y0          |n|
                            |x|                                    |0|
                             ':,,--------------------------------,,:'


                         "i bet you didn't think it'd get so bad so quick"
*/

import java.util.Scanner;
import java.text.*;


public class CC_Bill_Sim_exp
{
    public static void main( String[] args )
    {
        
        String rep;

        do {

            // initialize objects
            Scanner scan = new Scanner( System.in );
            DecimalFormat Fbal = new DecimalFormat( "$###,###,##0.00" );
            
            
            System.out.println();
            System.out.println();
            
            // placeholder for ascii art
            System.out.println( "Let's pay off some debt!" );

            System.out.println();
            System.out.println();
            
            
            // get principal debt
            System.out.print( "Enter your principal CC balance: " );
            double pBal = scan.nextDouble();

            System.out.println();

 
            // get annual interest rate
            float rate;
            do
            {
                System.out.print( "Enter your annual interest rate (xx.xx%): " );
                rate = scan.nextFloat() / 100.0f / 12;

                if (rate < 0)
                {
                    System.out.println();
                    System.out.println( "Interest rate must be positive" );
                    System.out.println();
                }
            }
            while (rate < 0);

            System.out.println();
            
            
            // display min payment
            double minP = pBal*rate;
            System.out.println( "Your minimum payment is: " + Fbal.format(minP) );

            System.out.println();
            

            // loop getting adequate monthly payment
            double pay;
            do
            { 
                System.out.print( "Enter your monthly payments: " );
                pay = scan.nextDouble();

                if (pay < minP)
                {
                    System.out.println();
                    System.out.println( "You need to at least pay the minimum" );
                    System.out.println();
                }
            } 
            while ( pay < minP );
            

            System.out.println();

            
            // loop getting pay period

            String mPer = new String();
            Integer ImPer = new Integer(0);
            // int mPer = 0;
            do
            {
                System.out.print( "For how many months? " );
                mPer = scan.next();
                
                if ( !mPer.equals( "udf" ) )
                {
                    ImPer = Integer.valueOf(mPer);
                
    
                    if (mPer < 0 /* || !mPer.equals("udf") */ )
                    {
                        System.out.println();
                        System.out.println( "You need to at least make one payment" );
                        System.out.println();
                    }
               // }
            }
            while ( mPer < 0 /* || !mPer.equals( "udf" ) */ );


            System.out.println();
            System.out.println();
        

            // initialize variables
            int month = 0, mCount;
            double bal = pBal;
            double payed = 0;

            do
            {
                mCount = 0;

                System.out.println( "Month    | Balance           | Payments" );
                System.out.println( "---------|-------------------|-----------" );
    

                // loop printing values
                while (bal > 0 && (mCount < mPer || mPer == 0 ))
                {
                    // subtract payment until balance is less than payment
                    if ( bal >= pay )
                    {
                        bal += bal*rate - pay;
                        payed += pay;
                    }
                    else
                    {
                        bal += bal*rate;
                        payed += bal;
                        bal -= bal;
                    }
                
                    month++;
                    mCount++;
                
                 // values for formatting display
                    String Smonth = String.valueOf( month );
                    String Sbal = String.valueOf( Fbal.format(bal) );

                    System.out.print( " " + month );
                    
                    int countM = 0;
                    while (countM < 8 - Smonth.length() )
                    {
                        System.out.print( " " );
                        countM++;
                    }
    
                    System.out.print( "| " + Fbal.format(bal) );
        
                    int countB = 0;
                    while (countB < 18 - Sbal.length() )
                    {
                        System.out.print( " " );
                        countB++;
                    }

                    System.out.println( "| " + Fbal.format( payed ) );
            

                }

                System.out.println();
                System.out.println();
                
                if (bal > 0)
                {

                    mCount = 0;

                    minP = bal*rate;
                    System.out.print( "Your new minimum payment is: " + Fbal.format(minP) );
                
                
                    System.out.println();
                    System.out.println();


                    // get new monthly payment
                    do
                    {

                        System.out.print( "Enter your new monthly payment: " );
                        pay = scan.nextDouble();

                        if (pay < minP)
                        {
                            System.out.println();
                            System.out.println( "You need to at least pay the minimum" );
                            System.out.println();
                        }
                    }
                    while ( pay < minP );

                    System.out.println();

                    // get new pay period
                    do
                    {
                        System.out.print( "for how many more months? " );
                        mPer = scan.nextInt();
                            
                       // ImPer = Integer.valueOf(mPer);

                        if (mPer < 1 /* || !mPer.equals( "udf" ) */ )
                        {
                            System.out.println();
                            System.out.println( "You need to make at least one payment " );
                            System.out.println();
                        }
                    
                    }
                    while ( mPer < 1 );
                }
                
            }
            while (bal > 0 && (mCount < mPer || mPer == 0) );
            
            System.out.println();
            System.out.println();

            System.out.println( "It took " + month/12 + " years and " + month%12 + " months to pay off your debt" );
            System.out.println( "You payed " + Fbal.format(payed-pBal) + " in interest" );

            System.out.println();
            System.out.println();

            System.out.print( "Would you like to repeat the program (y/n)? " );
            rep = new String( scan.next() );
        }
        while (rep.equals("y") || rep.equals("Y"));

    }
}
