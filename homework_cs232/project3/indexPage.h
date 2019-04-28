#ifndef INDEXPAGE_H_
#define INDEXPAGE_H_
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

struct trieNode{
int count;
struct trieNode *child[26];
};

//prototypes (indexPage from proj 2)
struct trieNode* indexPage(const char* url, int* totalNumTerms);
int addWordOccurrence(const char* word, struct trieNode* origin);
void printTrieContents(struct trieNode* root, char* str, int level);
int freeTrieMemory(struct trieNode *node);
int getText(const char* srcAddr, char* buffer, const int bufSize);
void wordOccurence(struct trieNode* root, char* string, char* target, int level, int* returnInt);
#endif //endif indexPage