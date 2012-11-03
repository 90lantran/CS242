/* 
 * CS 241
 * The University of Illinois
 */

#include <stdio.h>
#include <stdlib.h>
#include <sys/select.h>
#include <pthread.h>
#include <string.h>
#include <assert.h>
#include <unistd.h>
#include <sys/wait.h>
#include <poll.h>
#include <semaphore.h>
#include "libmapreduce.h"
#include "libdictionary.h"

static const int BUFFER_SIZE = 2048;

// Global Dictionary for Results
dictionary_t dictionary;
int ** pfds;
int map_count;

static void process_key_value(const char *key, const char *value, mapreduce_t *mr)
{
	//printf("process key value\n");
	char* value2 = NULL;
	//printf("PKV: 1\n");
	value2 = dictionary_get(&dictionary, key);
	//printf("PKV: 2\n");
	if(value2 == NULL){
		//printf("PKV: 3\n");
		if(dictionary_add(&dictionary, key, value) != 0)
			printf("ERROR on add of key: value to dictionary, first time key is added\nKey: %s Value: %s\n", key, value);
		//printf("PKV: 4\n");
	}
	else{
		const char* result = mr->reduce(value, value2);
		//printf("PKV: 5\n");
//		free(value);
//		value = NULL;
		value2 = NULL;

		//printf("PKV: 6\n");
		// may be a problem due to remove_free free the key value stored in the dictionary, it may not be the same key though
		if(dictionary_remove_free(&dictionary, key) != 0)
			printf("ERROR on removal of key: value from ditionary \nKey: %s Value: %s\n", key, value2);
		//printf("PKV: 7\n");
		if(dictionary_add(&dictionary, key, result) != 0)
			printf("ERROR on add of key: value to dictionary, post reduce() \nKey: %s Value: %s\n", key, result);
		free((char*) value);
		value = NULL;
		//printf("PKV: 8\n");
	}
	//printf("PKV: 9\n");
	return;	
}


static int read_from_fd(int fd, char *buffer, mapreduce_t *mr)
{
	/* Find the end of the string. */
	int offset = strlen(buffer);

	//printf("Read: offset = %d\n", offset);
	/* Read bytes from the underlying stream. */
	int bytes_read = read(fd, buffer + offset, BUFFER_SIZE - offset);
	//printf("Read: Bytes = %d  FD = %d\n", bytes_read, fd);
	
	if (bytes_read == 0){
		return 0;
	}
	if(bytes_read < 0){
		fprintf(stderr, "error in read.\n");
		return -1;
	}
	buffer[offset + bytes_read] = '\0';

	/* Loop through each "key: value\n" line from the fd. */
	char *line;
	while ((line = strstr(buffer, "\n")) != NULL)
	{
		//printf("loop\n");
		*line = '\0';

		/* Find the key/value split. */
		char *split = strstr(buffer, ": ");
		if (split == NULL)
			continue;

		//assert(split != NULL);

		/* Allocate and assign memory */
		char *key = malloc((split - buffer + 1) * sizeof(char));
		char *value = malloc((strlen(split) - 2 + 1) * sizeof(char));

		strncpy(key, buffer, split - buffer);
		key[split - buffer] = '\0';

		strcpy(value, split + 2);

		/* Process the key/value. */
		process_key_value(key, value, mr);
		//printf("Read: Done with Process Key Value\n");
		/* Shift the contents of the buffer to remove the space used by the processed line. */
		memmove(buffer, line + 1, BUFFER_SIZE - ((line + 1) - buffer));
		buffer[BUFFER_SIZE - ((line + 1) - buffer)] = '\0';
		//printf("Read: Done Modifying the Buffer\n");
	}
	//printf("Read: Done Reading\n");
	return 1;
}

void mapreduce_init(mapreduce_t *mr, 
			void (*mymap)(int, const char *), 
				const char *(*myreduce)(const char *, const char *))
{	
	mr->map = mymap;
	mr->reduce = myreduce;
	dictionary_init( &dictionary );
	mr->worker = 0;
}

void mapreduce_map_all(mapreduce_t *mr, const char **values)
{
	map_count = 0;
	while( values[map_count] != NULL){
		map_count++;
	}
	//map_count++;
	int i;
	pfds = malloc(sizeof(void*) * map_count);
	for(i = 0; i < map_count; i++){
		pfds[i] = malloc(sizeof(int)*2);
		pipe(pfds[i]);
		// Configure Pipe here
		//printf("Pipe%d Read: %d  Write: %d\n", i, pfds[i][0], pfds[i][1]);
	}
	for(i = 0; i < map_count; i++){
		
		if(!fork()){ //child process
			int j;
			for(j = 0; j < map_count; j++){
				close(pfds[j][0]);
				if(j != i)
					close(pfds[j][1]);
			}
			mr->map(pfds[i][1], values[i]);
			//printf("mapped %d\n", i);
			exit(0);
		}
	}
	pthread_t pid;
	
	if( pthread_create(&pid, NULL, worker_thread, (void*)mr) == -1)
		printf("ERROR on creation of Worker Thread \n");
	mr->worker = pid;
	/*for(i = 0; i < map_count; i ++){
		close(pfds[i][0]);
		close(pfds[i][1]);
	}*/
	
}

void mapreduce_reduce_all(mapreduce_t *mr)
{
	pthread_join(mr->worker, NULL);
	return;
}

const char *mapreduce_get_value(mapreduce_t *mr, const char *result_key)
{
	const char* value = dictionary_get(&dictionary, result_key);
	return value;
}

void mapreduce_destroy(mapreduce_t *mr)
{
	int i;
	for(i = 0; i < map_count; i++){
		close(pfds[i][0]);
		free(pfds[i]);
		
		pfds[i] = NULL;
	
	}
	free(pfds);
	pfds = NULL;
	dictionary_destroy_free(&dictionary);
	mr->worker = 0;
}
void* worker_thread(void* info)
{
	//printf("Starting Worker Thread\n");
	int fdsDone[map_count];
	int fdsStarted[map_count];
	char ** buffers = malloc(sizeof(void*) * map_count);
	struct pollfd * toPoll = malloc(sizeof(struct pollfd)*map_count);
	int i;
	for(i = 0 ; i < map_count; i++){
		buffers[i] = malloc(BUFFER_SIZE + 1);
		buffers[i][0] = '\0';
		fdsDone[i] = 0;
		toPoll[i].fd = pfds[i][0];
		toPoll[i].events = POLLIN;
		toPoll[i].events = toPoll[i].events | POLLHUP;
		close(pfds[i][1]);
		fdsStarted[i] = 0;
	}
	i = 0;
	int j = 0;
	//printf("Worker: Done initializing Values\n");
	while(1){
		//printf("Worker:Polling\n");
		poll(toPoll, map_count, 25);
		for(i = 0; i < map_count; i ++){
			if(fdsDone[i] == 0 && (((toPoll[i].revents & POLLIN) != 0) || fdsStarted[i] || ((toPoll[i].revents & POLLHUP) != 0))){
				fdsStarted[i] = 1;
				//printf("Worker: Process %d is ready for reading... Reading Now\n", i);
				int result = read_from_fd(pfds[i][0], buffers[i], (mapreduce_t*)info);
				//printf("Worker: Result from read = %d\n", result);
				if(result == 0){
					fdsDone[i] = 1;
					close(pfds[i][0]);
					//printf("Worker: Process %d is all read\n",i);
				}
				else if(result == -1)
					printf("ERROR in read_from_fd() \n");
			}
		}
		// increment and cycle around
		//i = (i + 1)%map_count;
		//printf("Worker: Checking if we are done...\n");
		// Check to see if completed
		for(j = 0; (j < map_count) && fdsDone[j]; j++);
		if((j == map_count) && fdsDone[j-1])
			break;
	}
	
	//printf("Worker: Done reading\n");
	for(i = 0; i < map_count; i++){
		free(buffers[i]);
		buffers[i] = NULL;
		//toPoll[i] = NULL;
		//fdsDone[i] = NULL;
	} 	
	free(buffers);
	buffers = NULL;
	free(toPoll);
	toPoll = NULL;
	//free(fdsDone);
	//fdsDone = NULL;
	pthread_exit(NULL);
}
