/* File: indexPage.c */
/* Author: Britton Wolfe */
/* Date: September 3rd, 2010 */

/* This program indexes a web page, printing out the counts of words on that page */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>


/* TODO: structure definitions */
struct trieNode{
int count;
struct trieNode *child[26];
};

/* NOTE: int return values can be used to indicate errors (typically non-zero)
   or success (typically zero return value) */

/* TODO: change this return type */
int indexPage(const char* url, struct trieNode* root);

int addWordOccurrence(const char* word, struct trieNode* origin);

int isAlpha(char c);

void printTrieContents(struct trieNode* root, char* str, int level);

int freeTrieMemory(struct trieNode *node);

int getText(const char* srcAddr, char* buffer, const int bufSize);

int main(int argc, char** argv){
  /* TODO: write the (simple) main function

  /* argv[1] will be the URL to index, if argc > 1 */
  if(argc > 1){
    struct trieNode* root = (struct trieNode*)malloc(sizeof(struct trieNode));
    if(root == NULL){
      printf("Error: Not enough memory.");
      return -1;
    }
    else{
      root->count = 0;
      int i = 0;
      for(i = 0; i < 26; i++){
        root->child[i] = NULL;
      }
      int error = indexPage(argv[1], root);
        if(error != 0){
          freeTrieMemory(root);
          return error;
        }

      char* buffer = malloc(sizeof(char) * 300000);
      if(buffer == NULL){
        printf("Error: Not enough memory.");
        return -1;
      }
      printTrieContents(root, buffer, 0);
      freeTrieMemory(root);
      free(buffer);
      return 0;
    }
  }
  else{
    printf("Error: URL not found.");
    return 1;
  }
}

/* TODO: define the functions corresponding to the above prototypes */

//returns 1 if c is part of the alphabet
int isAlpha(char c){
  if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
    return 1;
  }
  else{
    return 0;
  }
}

/**
* Indexes the given URL collecting all the text on the page, converts the text to lower case and discards all non alphabetical characters.
* Tokenizes the  text and passes them to the trie.
* @params: char* url, trieNode* root
* returns: root
*/
int indexPage(const char* url, struct trieNode* root)
{
  const int MAX_ARR_LEN = 300000;
  int i = 0;
  int j = 0;
  char* str = malloc(sizeof(char) * MAX_ARR_LEN);
    if(str == NULL){
      printf("Out of memory.");
      return -1;
    }
  int bufferSize = getText(url, str, MAX_ARR_LEN);

  printf("%s\n" , url);

  for (i = 0; i < bufferSize; i++){
    if(!isAlpha(str[i])) {
      str[i] = ' ';
    }
  }

  str[i+1] = '\0';

  while(str[j] != '\0'){
    //converts c to lowercase if uppercase
    if (str[j] >= 'A' && str[j] <= 'Z')
      str[j]= (str[j] - 'A') + 'a';
    j++;
  }

  char* token = strtok(str , " ");
  if(token == NULL){
    return -2;
  }
  while(token != '\0'){
    printf("\t%s\n" , token);
    int error = addWordOccurrence(token, root); //adds buffer to the trieNode
      if(error != 0){
        return error;
      }
    token = strtok(NULL, " ");
  }
    free(str);
    return 0;
}
/**
* Adds the word to the trie,
* @params: char* word, trieNode* origin
* return: ints depending on success(0 if passed)
*/
int addWordOccurrence(const char* word, struct trieNode* origin)
{
  struct trieNode* currentNode = origin;
  const int wordLength = strlen(word);
  int index = 0;
  char c = word[index];

  while(index < wordLength && c != '\0'){
    if(isAlpha(c)){
      int charIndex = c - 'a';
      //Creates child node if it doesn't exist
      if(currentNode->child[charIndex] == NULL){
        struct trieNode* newNode = (struct trieNode*)malloc(sizeof(struct trieNode));
        //Returns 1 if not enough space
        if(newNode == NULL){
          return 1;
        }
        //Initializes the newNode
        else{
          currentNode->child[charIndex] = newNode;
          newNode->count = 0;
          int i = 0;
          for(i = 0; i < 26; i++){
            newNode->child[i] = NULL;
          }
        }
      }

      currentNode = currentNode->child[charIndex];
    }

    index++;
    c = word[index];
  }
    currentNode->count++; //indicates the end of the word
    return 0;
}

/**
* Traverses the trie finding the left-most word first, prints that and then the amount of times it occurs.
* The trie data type is basically a very fancy 2D array so the level param is neccessary to keep track of where everything is occuring.
* @params: trieNode* root, char* str, int level
*return: N/A
*/
void printTrieContents(struct trieNode* root, char* str, int level)
{
  if (root->count > 0){
    str[level] = '\0';
    printf("%s: %d\n", str, root->count);
  }

  int i;
  for(i = 0; i < 26; i++){
    if (root->child[i] != NULL)
    {
      //the plus 'a' shifts the ascii code from a to whatever i's count is
      str[level] = i + 'a';
      printTrieContents(root->child[i], str, (level + 1));
    }
  }
}

/**
 *Frees the memory previously allocated to the nodes of the trie.
 *@params trieNode* node
 *return: int depending on success(0 if success)
 */
int freeTrieMemory(struct trieNode *node)
{
  int i = 0;
  for(i = 0; i < 26; i++){
    if(node->child[i] != NULL){
      freeTrieMemory(node->child[i]);
    }
  }
  free(node);
  return 0;
}



/* You should not need to modify this function */
int getText(const char* srcAddr, char* buffer, const int bufSize){
  FILE *pipe;
  int bytesRead;

  snprintf(buffer, bufSize, "curl -s \"%s\" | python getText.py", srcAddr);

  pipe = popen(buffer, "r");
  if(pipe == NULL){
    fprintf(stderr, "ERROR: could not open the pipe for command %s\n",
	    buffer);
    return 0;
  }

  bytesRead = fread(buffer, sizeof(char), bufSize-1, pipe);
  buffer[bytesRead] = '\0';

  pclose(pipe);

  return bytesRead;
}