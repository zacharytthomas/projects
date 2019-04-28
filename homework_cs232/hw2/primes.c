/* File: primes.c */
/* Author: Britton Wolfe */
/* Date: July 16, 2009 */
/* This program outputs all the primes in the range given */
/* by the command line arguments */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

int main(int argc, const char** argv){

    int lowerBound, upperBound;

    if(argc != 3){
        fprintf(stderr, "USAGE: %s lowerBound upperBound\n", argv[0]);
        return -1;
    }

    lowerBound = atoi(argv[1]);
    upperBound = atoi(argv[2]);

    if(lowerBound < 1 || upperBound < 1){
        fprintf(stderr, "ERROR: both the lowerBound (%d) and the upperBound (%d)"
	          " must be positive.\n", lowerBound, upperBound);
        return -2;
    }

        /* TODO: fill in the code that outputs the prime numbers */
        /*   in the range [lowerBound,upperBound] in ascending order */
        int i;

        int numberCheck = atoi(argv[1]);

        int index;
        int prime = 1;

        for (i = 0; i <= upperBound; i++){

            int oneCheck = numberCheck - 1;
            if (i >= lowerBound && i <= upperBound){

                for (index = 2; index < numberCheck; index++){

                    if ((numberCheck % index) == 0){
                        prime = 0;
                    }//end if
                } //end primeCheck loop

                if (oneCheck == 0){
                    prime = 0;
                }

                if (prime == 1){
                    //print out int
                     printf("%d\n", numberCheck);
                }

                numberCheck++;
                prime = 1;
            }
        }
           return 0;

}