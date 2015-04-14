from socket import *
import thread

mylist = []
def cekpubkey(mylist, username):
    for terserah in mylist:
        kata = terserah.split(":")
        if (username[0] == kata[0]):
	    print "masuk"
            return (kata[1])
def handler(clientsocket, clientaddr):
    print "Accepted connection from: ", clientaddr

    while 1:
        data = clientsocket.recv(1024)
        if not data:
            break
        else:
            print data
            t = data.split(":",1)
            if (t[0] == "stor"):
				
				flag=0
				for iteration in mylist:
					user=iteration.split(":")
					tmp=t[1].split(":")
					print user[0],tmp[0]
					if user[0]==tmp[0]:
						mylist.remove(iteration)
						mylist.append(t[1])
						flag=1
				
				if flag==0 :
					mylist.append(t[1])
            elif (t[0] == "get"):
                result = cekpubkey(mylist, t[1].split('\r\n'))
		clientsocket.send(result)
            
            

    
    clientsocket.close()

if __name__ == "__main__":

    host = ''
    port = 5000
    buf = 1024

    addr = (host, port)

    serversocket = socket(AF_INET, SOCK_STREAM)

    serversocket.bind(addr)

    serversocket.listen(2)

    while 1:
        print "Server is listening for connections\n"
        
        clientsocket, clientaddr = serversocket.accept()
        thread.start_new_thread(handler, (clientsocket, clientaddr))
    serversocket.close()
