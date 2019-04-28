#ifndef APPLICATION_H
#define APPLICATION_H
#include "crawler.h"
#include <math.h>

#define MAX_URL_SIZE 1000
//prototypes
int main(int argc, char *argv[]);
// void pageScore(struct listNode* root, char* string, int pages, double* pIdf, double* pTf);
double tfScore(struct listNode* root, char* string);
double idfScore(struct listNode* root, char* string, int pages);

struct top3Scores{
    double scores[3];
    struct listNode* first;
    struct listNode* second;
    struct listNode* third;
};

// void insertionSort(double arr[], int n) ;
void printList(int level, char* string, double tfidf);

#endif