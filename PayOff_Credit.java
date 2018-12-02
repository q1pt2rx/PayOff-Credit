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


public class PayOff_Credit 
{
    public static void main( String[] args )
    {
        
        String rep;

        do {

        //  initialize objects
            Scanner scan = new Scanner( System.in );
            DecimalFormat Fbal = new DecimalFormat( "$###,###,##0.00" );
            
        //  placeholder for ascii art
            System.out.println( "\n\nLet's pay off some debt!" );

            
        //  get principal debt
            System.out.print( "\n\nEnter your principal CC balance: " );
            double pBal = scan.nextDouble();

      ////  get period of interest rate
            System.out.print( "\n\nEnter your # of yearly payments: " );
            int nPay = scan.nextInt();

        //  get annual interest rate
            float rate;
            do
            {
                System.out.print( "\nEnter your annual interest rate (xx.xx%): " );
                rate = scan.nextFloat() / 100.0f / nPay;

                if (rate < 0)
                {
                    System.out.println();
                    System.out.println( "Interest rate must be positive" );
                    System.out.println();
                }
            }
            while (rate < 0);

            
        //  display min payment
            double minP = pBal*rate;
            System.out.println( "\nYour minimum payment is: " + Fbal.format(minP) );


        //  loop getting adequate monthly payment
            double pay;
            do
            { 
                System.out.print( "\nEnter your periodic payments: " );
                pay = scan.nextDouble();

                if (pay < minP)
                {
                    System.out.println();
                    System.out.println( "You need to at least pay the minimum" );
                    System.out.println();
                }
            } 
            while ( pay < minP );
            
            
        //  loop getting pay period

            int nPer = 0;
            do
            {
                System.out.print( "\nFor how many periods? " );
                nPer = scan.nextInt();
    
                if (nPer < 0 )
                {
                    System.out.println();
                    System.out.println( "You need to at least make one payment" );
                    System.out.println();
                }
            }
            while ( nPer < 0 );


        //  initialize variables
            int period = 0, pCount;
            double bal = pBal;
            double payed = 0;

            do
            {
                pCount = 0;

                System.out.println( "\n" );

                System.out.println( "Period   | Balance           | Payments" );
                System.out.println( "---------|-------------------|-----------" );
    

            //  loop printing values
                while (bal > 0 && (pCount < nPer || nPer == 0 ))
                {

                //  subtract payment until balance is less than payment
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
                
                    period++;
                    pCount++;
                
                 // values for formatting display
                    String Speriod = String.valueOf( period );
                    String Sbal = String.valueOf( Fbal.format(bal) );

                    System.out.print( " " + period );
                    
                    int countM = 0;
                    while (countM < 8 - Speriod.length() )
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
               
            //  repeat asking for information until balance is zero
                if (bal > 0)
                {

                    pCount = 0;
                    
                //  get new interest rate
                    do
                    {
                        System.out.print( "\n\nEnter your new annual interest rate (xx.xx%): " );
                        rate = scan.nextFloat() / 100.0f / nPay;

                        if (rate < 0)
                        {
                            System.out.println();
                            System.out.println( "Interest rate must be positive" );
                            System.out.println();
                        }
                    }
                    while (rate < 0);
                
                //  calculate and display new minimum payment
                    minP = bal*rate;
                    System.out.print( "\nYour new minimum payment is: " + Fbal.format(minP) );
                

                //  get new Periodic payment
                    do
                    {

                        System.out.print( "\nEnter your new periodic payment: " );
                        pay = scan.nextDouble();

                        if (pay < minP)
                        {
                            System.out.println();
                            System.out.println( "You need to at least pay the minimum" );
                            System.out.println();
                        }
                    }
                    while ( pay < minP );

                //  get new pay period
                    do
                    {
                        System.out.print( "\nFor how many more periods? " );
                        nPer = scan.nextInt();
                            
                        if (nPer < 0 )
                        {
                            System.out.println();
                            System.out.println( "You need to make at least one payment " );
                            System.out.println();
                        }
                    
                    }
                    while ( nPer < 0 );

                }
                
            }
            while (bal > 0 && (pCount < nPer || nPer == 0) );
            
            
            System.out.println( "\n" );

            System.out.println( "It took " + period/nPay + " years and " + period%nPay + " payments to pay off your debt" );
            System.out.println( "You payed " + Fbal.format(payed-pBal) + " in interest" );

            System.out.println( "\n" );

            System.out.print( "Would you like to repeat the program (y/n)? " );
            rep = new String( scan.next() );
        }
        while (rep.equals("y") || rep.equals("Y"));

    }
}
