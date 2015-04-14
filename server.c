/*
    C socket server example, handles multiple clients using threads
*/

#include < stdio.h > #include < string.h > //strlen
#include < stdlib.h > //strlen
#include < sys / socket.h > #include < arpa / inet.h > //inet_addr
#include < unistd.h > //write
#include < pthread.h > //for threading , link with lpthread

//the thread function
void * connection_handler(void * );
int auth(char * , char * );

struct list_el {
	int sock;
	char userName[100];
	struct list_el * next;
};

typedef struct list_el user;

user * curr, * head, * tmp;


int main(int argc, char * argv[]) {
	int socket_desc, client_sock, c, * new_sock;
	struct sockaddr_in server, client;

	head = NULL;
	//Create socket
	socket_desc = socket(AF_INET, SOCK_STREAM, 0);
	if (socket_desc == -1) {
		printf("Could not create socket");
	}
	puts("Socket created");

	//Prepare the sockaddr_in structure
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY;
	server.sin_port = htons(9000);

	//Bind
	if (bind(socket_desc, (struct sockaddr * ) & server, sizeof(server)) < 0) {
		//print the error message
		perror("bind failed. Error");
		return 1;
	}
	puts("bind done");

	//Listen
	listen(socket_desc, 3);

	//Accept and incoming connection
	puts("Waiting for incoming connections...");
	c = sizeof(struct sockaddr_in);


	//Accept and incoming connection
	puts("Waiting for incoming connections...");
	c = sizeof(struct sockaddr_in);
	while ((client_sock = accept(socket_desc, (struct sockaddr * ) & client, (socklen_t * ) & c))) {
		puts("Connection accepted");

		pthread_t sniffer_thread;
		pthread_t list_user;
		new_sock = malloc(1); * new_sock = client_sock;
		curr = (user * ) malloc(sizeof(user));
		curr - > next = head;
		curr - > sock = client_sock;
		strcpy(curr - > userName, "anonymous");
		head = curr;


		if (pthread_create( & sniffer_thread, NULL, connection_handler, (void * ) new_sock) < 0) {
			perror("could not create thread");
			return 1;
		}



		//Now join the thread , so that we dont terminate before the thread
		//pthread_join( sniffer_thread , NULL);
		puts("Handler assigned");
	}

	if (client_sock < 0) {
		perror("accept failed");
		return 1;
	}

	return 0;
}

int auth(char * userCek, char * passCek) {
	static const char filename[] = "pass";
	char user[128];
	char pass[128];
	FILE * file = fopen(filename, "r");
	if (file != NULL) {
		char line[128]; /* or other suitable maximum line size */
		while (fgets(line, sizeof line, file) != NULL) /* read a line */
		{

			strcpy(user, strtok(line, ":"));
			strcpy(pass, strtok(NULL, "\n"));
			printf("%s %s %s %s\n", user, userCek, pass, passCek);
			if (strcmp(user, userCek) == 0 && strcmp(pass, passCek) == 0) {
				return 1;
			}
		}
		fclose(file);
	} else {
		perror(filename); /* why didn't the file open? */
	}
	return 0;

}

/*
 * This will handle connection for each client
 * */
void * connection_handler(void * socket_desc) {
	//Get the socket descriptor
	int sock = * (int * ) socket_desc;
	int read_size;

	char * message, client_message[2000], msg[2000], body_msg[2000], user[100], user_tujuan[100];
	//Send some messages to the client
	curr = head;

	read_size = read(sock, client_message, 2000);
	curr = head;
	while (curr) {
		if (curr - > sock == sock) {
			char username[128];
			char pass[128];
			client_message[read_size] = '\0';
			strtok(client_message, "\r\n");
			strcpy(username, strtok(client_message, ":"));
			strcpy(pass, strtok(NULL, ":"));
			int cek_login = 0;
			cek_login = auth(username, pass);

			if (cek_login) {
				message = "Selamat Datang\n";
				send(sock, message, strlen(message), 0);
			} else {
				message = "Login gagal, cek kembali username dan passsword anda\n";
				send(sock, message, strlen(message), 0);
				curr = head;
				while (curr) {
					if (curr - > sock == sock) {
						tmp = curr;
						head = curr - > next;
						free(tmp);
						break;
					} else if (curr - > next != NULL && curr - > next - > sock == sock) {
						tmp = curr - > next;
						curr - > next = curr - > next - > next;
						free(tmp);
						break;
					}

					printf("%d\n", curr - > sock);
					curr = curr - > next;
				}

				fflush(stdout);
				shutdown(sock, SHUT_RDWR);
				break;
			}
			strcpy(curr - > userName, client_message);
			strcpy(user, client_message);
		}
		curr = curr - > next;
	}



	int flag_receive = 0;
	int curr_user = 0;
	//Receive a message from client
	while (read_size = read(sock, client_message, 2000)) {

		//printf("%s",client_message);

		client_message[read_size] = '\0';
		strtok(client_message, "\r\n");

		if (client_message[0] == ':') {
			//strtok(,":");	
			strcpy(user_tujuan, strtok(client_message, ":"));
			strcpy(body_msg, strtok(NULL, ":"));
			printf("%s", user_tujuan);
			flag_receive = 1;
		} else if (client_message[0] == '$') {

			curr = head;

			char list[2000];
			strcpy(list, ";");
			while (curr) {
				strcat(list, curr - > userName);
				strcat(list, ";");

				curr = curr - > next;
			}
			flag_receive = 0;

			send(sock, list, strlen(list), 0);
			send(sock, "\r\n", strlen("\r\n"), 0);


		} else {
			flag_receive = 2;
			strcpy(body_msg, client_message);
		}

		if (flag_receive == 1) {

			curr = head;
			while (curr) {
				if (strcmp(user_tujuan, curr - > userName) == 0) {
					curr_user = curr - > sock;
					sprintf(msg, "%s: %s\r\n", user, body_msg);
					send(curr_user, msg, strlen(msg), 0);
					send(curr_user, "\r\n", strlen("\r\n"), 0);

				}
				curr = curr - > next;
			}
		} else if (flag_receive == 2) {
			sprintf(msg, "%s: %s\r\n", user, body_msg);
			write(curr_user, msg, strlen(msg));
		}

		strcpy(client_message, "");

		//write(sock , client_message , strlen(client_message));
	}

	if (read_size == 0) {
		printf("%s: Client disconnected\n", user);
		curr = head;
		while (curr) {
			if (curr - > sock == sock) {
				tmp = curr;
				head = curr - > next;
				free(tmp);
				break;
			} else if (curr - > next != NULL && curr - > next - > sock == sock) {
				tmp = curr - > next;
				curr - > next = curr - > next - > next;
				free(tmp);
				break;
			}

			printf("%d\n", curr - > sock);
			curr = curr - > next;
		}

		fflush(stdout);
	} else if (read_size == -1) {
		perror("recv failed");
	}

	//Free the socket pointer
	free(socket_desc);

	return 0;
}
