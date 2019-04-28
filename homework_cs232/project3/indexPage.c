#include "indexPage.h"
/**
* Indexes the given URL collecting all the text on the page, converts the text to lower case and discards all non alphabetical characters.
* Tokenizes the  text and passes them to the trie.
* @params: char* url, trieNode* root
* returns: root
*/
struct trieNode* indexPage(const char* url, int* pTotalNumTerms)
{
  struct trieNode* root = malloc(sizeof(struct trieNode));
  if(root == NULL){
    printf("found it");
    return NULL;
  }
  int i = 0;
  root->count = 0;
  for (; i < 26; i++){
    root->child[i] = NULL;
  }
  const int MAX_ARR_LEN = 300000;
  int wordCount = 0;
  int j = 0;
  // char* str = malloc(sizeof(char) * MAX_ARR_LEN);
  char str[MAX_ARR_LEN];
    // if(str == NULL){
    //   printf("Out of memory.");
    //   return;
    // }

  int bufferSize = getText(url, str, MAX_ARR_LEN);


  for (i = 0; i < bufferSize; i++){
    if(!isalpha(str[i])) {
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
  printf("%s\n", url);
  char* token = strtok(str , " ");
  if(token == NULL){
    return;
  }
  while(token != '\0'){
    printf("\t%s\n" , token);
    int error = addWordOccurrence(token, root); //adds buffer to the trieNode
      if(error != 0){
        printf("wrong\n");
        free(str);
        free(root);
        return;
      }
    wordCount++;
    token = strtok(NULL, " ");
  }
    *pTotalNumTerms = wordCount;
    // free(str);

    return root;
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
    if(isalpha(c)){
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
    printf("%s\n", str);
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
  if(node == NULL){
    return -1;
  }
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

void wordOccurence(struct trieNode* root, char* string, char* target, int level, int* returnInt){
  int res = 0;
    if(root->count){
    string[level] = '\0';
    res = strncmp(target, string, strlen(target));
    if(!res){
      *returnInt = root->count;
      return;
    }
  }

  int i = 0;
  for(; i < 26; i++){
    if (root->child[i] != NULL)
    {
      //the plus 'a' shifts the ascii code from a to whatever i's count is
      string[level] = i + 'a';
      wordOccurence(root->child[i], string, target, (level + 1), returnInt);
    }
  }
}