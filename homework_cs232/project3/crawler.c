#include "crawler.h"
/*
 * returns 1 if the list starting at pNode contains the address addr,
 *    and returns 0 otherwise
 */
int contains(const struct listNode *pNode, const char *addr){
    int n = strlen(addr);
    if(addr == NULL){
      return 0;
    }
    if(pNode == NULL){
      return 0;
    }

    int result = strncmp(pNode->addr,addr, n);

    if (!result){
      return 1;
    }else{
      contains(pNode->next, addr);
    }

    return 0;
}
/**
 * void function to insert a node at the end of the linked list
 */

void insertBack(struct listNode *pNode, const char *addr, struct trieNode* trie, int* number){

    if (pNode->next == NULL){
      struct listNode* back = malloc(sizeof(struct listNode));
      if (back == NULL){
          printf("Couldn't allocate memory.");
          return;
      }


      int i;
      for(i = 0; i < 1000;i++){
        back->addr[i] = ' ';
      }
      back->next = NULL;
      back->words = NULL;
      back->wordCount = 0;
      back->tf = 0.0;
      back->idf = 0.0;
      back->tfidf = 0.0;

      int n = strlen(addr);
      sscanf(addr, "%s", back->addr);
      back->words = trie;
      back->wordCount = *number;
      pNode->next = back;

    }else{
      insertBack(pNode->next, addr, trie, number);
    }

}
/**
 * Prints the addresses of the linked list and the indexed pages.
 *
 *
 *
 */
void printAddresses(const struct listNode *pNode){

  if(pNode != NULL){

    char* buffer = malloc(sizeof(char) * 10000000);
    printf("%s\n", pNode->addr);
    printTrieContents(pNode->words, buffer, 0);
    printf("\n%d\n", pNode->wordCount);
    free(buffer);
    printAddresses(pNode->next);
  }
}
/**
 * Destroys the linked list memory.
 *
 *
 *
 */
void destroyList(struct listNode *pNode){
    if (pNode == NULL){
      return;
    }

    if (pNode->next == NULL){
      freeTrieMemory(pNode->words);
      free(pNode);
    }else{
      destroyList(pNode->next);
      freeTrieMemory(pNode->words);
      free(pNode);
    }

}

int getLink(const char* srcAddr, char* link, const int maxLinkLength){
  const int bufSize = 1000;
  char buffer[bufSize];

  int numLinks = 0;

  FILE *pipe;

  snprintf(buffer, bufSize, "curl -s \"%s\" | python getLinks.py", srcAddr);

  pipe = popen(buffer, "r");
  if(pipe == NULL){
    fprintf(stderr, "ERROR: could not open the pipe for command %s\n",
     buffer);
    return 0;
  }

  fscanf(pipe, "%d\n", &numLinks);

  if(numLinks > 0){
    int linkNum;
    double r = (double)rand() / ((double)RAND_MAX + 1.0);

    for(linkNum=0; linkNum<numLinks; linkNum++){
      fgets(buffer, bufSize, pipe);

      if(r < (linkNum + 1.0) / numLinks){
      break;
      }
    }

    /* copy the address from buffer to link */
    strncpy(link, buffer, maxLinkLength);
    link[maxLinkLength-1] = '\0';

    /* get rid of the newline */
    {
      char* pNewline = strchr(link, '\n');
      if(pNewline != NULL){
      *pNewline = '\0';
      }
    }
  }

  pclose(pipe);

  if(numLinks > 0){
    return 1;
  }
  else{
    return 0;
  }

}
