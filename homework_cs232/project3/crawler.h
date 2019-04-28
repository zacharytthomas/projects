#ifndef CRAWLER_H_
#define CRAWLER_H_
#include "indexPage.h"
//structs
struct listNode{
    char addr[1000];
    int wordCount;
    double tfidf;
    double tf;
    double idf;
    struct listNode* next;
    struct trieNode* words;
};


//prototypes (crawler from proj 1)
int contains(const struct listNode *pNode, const char *addr);
void insertBack(struct listNode *pNode, const char *addr, struct trieNode* trie, int* number);
void printAddresses(const struct listNode *pNode);
void destroyList(struct listNode *pNode);
int getLink(const char* srcAddr, char* link, const int maxLinkLength);


#endif //CRAWLER_H_ if