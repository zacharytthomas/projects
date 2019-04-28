#include "webSearch.h"

/**
 * Main function of the webSearch, takes a given input of a formatted file containing urls and hop numbers, a maximum number of hops, and a long seed
 * to help navigate the web pages.
 * indexs the pages, prints the indexed content, and the amount of words on the page
 *
 */
int main(int argc, char** argv){
  int i;

  if(argc != 4){
    printf("Input must match: application, file name, numJumps, long seed.");
    return -1;
  }

  char* file = argv[1];
  int MAX_N = atoi(argv[2]);
  long seed = atol(argv[3]);

  srand(seed);

  FILE* fp = fopen(file, "r");
  if(fp == NULL){
    printf("File could not be opened.");
    return -1;
  }

  char url[MAX_URL_SIZE] = "";
  char destURL[MAX_URL_SIZE] = "";
  char buffer[MAX_URL_SIZE] = "";

  int hopsLimit = 0, hopNum, n = 0, wordCount = 0;


  struct listNode* baseRoot = malloc(sizeof(struct listNode));
  if(baseRoot == NULL){
    printf("You ran out of memory.");
    fclose(fp);
    return -5;
  }

  for(i = 0; i < 1000; i++){
    baseRoot->addr[i] = ' ';
  }
  baseRoot->next = NULL;
  baseRoot->wordCount = 0;
  baseRoot->idf = 0.0;
  baseRoot->tfidf = 0.0;
  baseRoot->tf = 0.0;
  baseRoot->words = NULL;
  printf("Indexing...\n");

  while(fgets(url, MAX_URL_SIZE, fp) != NULL && n < MAX_N){
    sscanf(url, "%s %d", buffer, &hopsLimit);
    hopNum = 0;
    int length = strlen(buffer);
    buffer[length] = '\0';
    struct trieNode* root;
    while (1){
      if(!(contains(baseRoot, buffer))){
        root = indexPage(buffer, &wordCount);
        insertBack(baseRoot, buffer, root, &wordCount);
        n++;
        wordCount = 0;
      }

      hopNum++;
      int res = getLink(url, destURL, MAX_URL_SIZE);

      if(hopNum <= hopsLimit && n < MAX_N){

        if (!(res)){
          break;
        }

      }else{
        break;
      }
    }
  }

  struct listNode* cursor = baseRoot;
  int pageCount = 0;
  while(cursor != NULL){
    if(cursor->addr != NULL){
      pageCount++;
    }
    cursor = cursor->next;
  }



/**
 * part 2
 * Takes the data stored in the tries created and searches them for a query, calculates the TFIDF scores for all the pages and the tries, returns the top 3 reulting web pages.
 */
  char userInput[MAX_URL_SIZE];
  char query[MAX_URL_SIZE];
  char* current;
  int charsRead = 0;
  int result = 0;
  printf("Enter a web query: ");
  fgets(userInput, MAX_URL_SIZE, stdin);//solicits user input
  do{
    if(userInput[0] == '\n')//checks if the user only hit enter, exits the program
      break;
    int stringLen = strlen(userInput);

    for(i = 0; i < stringLen; i++){//checks the given input for being lowercase and alpha
      if(!(isalpha(userInput[i])) && !(islower(userInput[i]))){
        if(userInput[i] == '\n' || userInput[i] == ' ')
          continue;
      printf("Please enter a query containing only lower-case letters.\n");
      break;
      }
    }
    if(i != stringLen){//if the check fails, returns to the top of the do
      printf("Enter a web query: ");
      continue;
    }

    userInput[stringLen - 1] = '\0';
    printf("Query is \"%s\".\nIDF scores are\n", userInput);//prints the query
    current = userInput;
    while(1){
      result = sscanf(current, "%s%n", query, &charsRead);

      if(result != 1){
        break;
      }

      printf("IDF(%s): %.*lf\n", query, 4, idfScore(baseRoot->next, query, pageCount));//calculates the idf scores for the given query
      double currentIDF = idfScore(baseRoot->next, query, pageCount);//holds the idf of the current query
      cursor = baseRoot;
      while(cursor != NULL){
        cursor->tfidf += (currentIDF * tfScore(cursor, query));//assigns the tfidf scores for the entire list of pages
        cursor = cursor->next;
      }

      current += charsRead;
    }

      cursor = baseRoot;
      struct top3Scores* list = malloc(sizeof(struct top3Scores));//a struct to hold a reference to the linked list and a tfidf score
      list->first = NULL;
      list->second = NULL;
      list->third = NULL;
      for (i = 0; i < 3; i++){
        list->scores[i] = 0.0;
      }

      while(cursor != NULL){//loops through the linked list and assigns the struct information uses a partial insertion sort
          if(cursor->tfidf > list->scores[0]){
            if(list->scores[0] != 0.0) {
              double temp = list->scores[0];
              list->scores[0] = cursor->tfidf;
              list->scores[1] = temp;
              list->second = list->first;
              list->first = cursor;
              cursor = cursor->next;
              break;
            }

            list->scores[0] = cursor->tfidf;
            list->first = cursor;
            break;
          }else if(cursor->tfidf > list->scores[1]){

            if(list->scores[1] != 0.0) {
              double temp1 = list->scores[1];
              list->scores[1] = cursor->tfidf;
              list->scores[2] = temp1;
              list->third = list->second;
              list->second = cursor;
              cursor = cursor->next;
              break;
            }

            list->scores[1] = cursor->tfidf;
            list->second = cursor;

          }else if(cursor->tfidf > list->scores[2]){

            list->scores[2] = cursor->tfidf;
            list->third = cursor;
          }else{
            cursor = cursor->next;
          }
      }
      printf("Web pages:\n");

      if(list->first != NULL){//prints the resulting urls and scores
        printList(1, list->first->addr, list->scores[0]);
      }

      if(list->second != NULL){
        printList(2, list->second->addr, list->scores[1]);
      }

      if(list->third != NULL){
        printList(3, list->third->addr, list->scores[2]);
      }

      cursor = baseRoot;
      while(cursor != NULL){
        cursor->tfidf = 0.0;
        cursor = cursor->next;
      }
    free(list);
    printf("\nEnter a web query: ");

  }while(fgets(userInput, MAX_URL_SIZE, stdin) != NULL);

  printf("Exiting the program\n");
  destroyList(baseRoot);
  fclose(fp);

  return 0;
}
/**
 * calculates the tf score
 *
 */
double tfScore(struct listNode* root, char* string){
  // if(root->next != NULL){
  //   tfScore(root->next, string, (result + 1));
  // }
  if(root->words != NULL){
    int d = 0, t = 0;
    char buffer[MAX_URL_SIZE];
    d = root->wordCount;
    wordOccurence(root->words, buffer, string, 0, &t);
    return (double)(t * 1.0)/d;
  }
    return 0.0;
}
/**
 * calculates the idf scores
 */
double idfScore(struct listNode* root, char* string, int pages){
  // if(root->next != NULL){
  //   idfScore(root->next, string, (result + 1));
  // }
  if(root->words != NULL){
    struct listNode* cursor = root;
    char buffer[MAX_URL_SIZE];
    int pageWithT = 0;
    int words = 0;
    while(cursor != NULL){
      words = 0;
      wordOccurence(cursor->words, buffer, string, 0, &words);
      if(words > 0){
        pageWithT++;
      }
      cursor = cursor->next;
    }
    return log(((double)pages)/(pageWithT + 1.0));
  }
  return 0.0;
  }

// void insertionSort(double arr[], int n){
//     int i, key, j;
//     for (i = 1; i < n; i++) {
//         key = arr[i];
//         j = i - 1;

//         /* Move elements of arr[0..i-1], that are
//           greater than key, to one position ahead
//           of their current position */
//         while (j >= 0 && arr[j] > key) {
//             arr[j + 1] = arr[j];
//             j = j - 1;
//         }
//         arr[j + 1] = key;
//     }
// }



 /* utility method to print list struct*/
void printList(int level, char* string, double tfidf){
  printf("%d. %s (score: %f)\n", level, string, tfidf);
}